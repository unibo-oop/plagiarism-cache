package controller;

import java.util.List;
import model.people.Person;
import model.people.PersonImpl;
import model.places.HomeImpl;
import model.places.HospitalImpl;
import model.places.HospitalizationOutcome;
import model.places.MeetingPlaceImpl;
import model.virus.Virus;
import view.ChartsImpl;
import view.InitialSettings;
import view.SimulationView;
import view.View;

/**
 * Simulation controller.
 *
 */
public class SimulationControllerImpl implements SimulationController {
    private static final int DAY = 10;
    private static final int MAXSPEED = 1000;
    private EnvironmentManager controllerEnvironment;
    private SimulationThread thread;
    private ChartsImpl chart;
    private InfectedManager infectManager;
    private PeopleManager peopleManager;
    private ChartsManager chartsManager;
    private InfectionObserver infectionObserver;
    private SimulationView simulationView;
    private InitialSettings initialSetting;
    private final VirusManager virusManager;
    private int quantiumOfTime;
    private int speedOfSimulation;
    private boolean isRunning;
    private final View view;

    /**
     * Constructor method.
     * @param view Main view.
     */
    public SimulationControllerImpl(final View view) {
        this.speedOfSimulation = 1;
        this.isRunning = false;
        this.view = view;
        virusManager = new VirusManagerImpl(view.getVirusSetup());
        virusManager.readVirus();
    }

    /**
     * Start.
     */
    @Override
    public void start() {
        this.thread = new SimulationThread();
        thread.start();
    }

    /**
     * Stop.
     */
    @Override
    public void stop() {
        thread.notifyStopped();
    }

    /**
     * New simulation.
     * 
     * @param initS 
     *       Initial settings
     */
    public void newSimulation(final InitialSettings initS) {
        this.initialSetting = initS;
        this.peopleManager = new PeopleManagerImpl(this.initialSetting.getBirthRate(),
                this.initialSetting.getDeathRate(), this.initialSetting.getTendencyToHome());
        this.infectManager = new InfectedManagerImpl();
        this.infectionObserver = new InfectionObserverImpl();
        this.simulationView = view.getSimulationView();
        this.controllerEnvironment = new EnvironmentManagerImpl(
                new HomeImpl(this.initialSetting.getTendencyToHome()), new HospitalImpl());
        this.chartsManager = new ChartsManagerImpl(this.initialSetting.getnInitialPeople());
        this.chart = view.getCharts();
        this.quantiumOfTime = 1;
        initEnviroment();
        this.simulationView.initializeMap(this.controllerEnvironment.getHomeFixture(),
                this.controllerEnvironment.getHospitalFixture(), this.controllerEnvironment.getMeetingPlaceFixtures());
    }

    private void initEnviroment() {
        for (int i = 0; i < this.initialSetting.getnInitialPeople()
                - this.initialSetting.getnInitialInfected(); i++) {
            this.controllerEnvironment.addPerson(new PersonImpl());
        }
        final Virus virus = virusManager.initializeVirus();
        for (int i = 0; i < this.initialSetting.getnInitialInfected(); i++) {
            final Person infectPerson = new PersonImpl();
            infectPerson.infect(virus.duplicate());
            this.controllerEnvironment.addPerson(infectPerson);
            this.infectManager.addInfected(this.quantiumOfTime, infectPerson);
        }
        this.chartsManager.addInfect(this.initialSetting.getnInitialInfected(), 0);

        for (int i = 0; i < this.initialSetting.getnMeetingPlace(); i++) {
            this.controllerEnvironment.addMeetingPlace(new MeetingPlaceImpl());
        }
    }

    /**
     * Set alert state on.
     */
    @Override
    public void notifyAlert(final double homeTendency) {
        synchronized (this) {
            this.controllerEnvironment.setAlertState(homeTendency);
            this.peopleManager.setHomeTendencyIncrement(homeTendency);
        }
    }

    /**
     * Set alert state off.
     */
    @Override
    public void notifyAlertOff() {
        synchronized (this) {
            this.controllerEnvironment.unsetAlertState();
            this.peopleManager.setHomeTendencyIncrement(0);
        }
    }

    /**
     * Set speedSimulation.
     * 
     * @param speed
     */
    @Override
    public void setSimulationSpeed(final int speed) {
        if (speed == 0) {
            throw new IllegalArgumentException();
        }
        if (!this.isRunning) {
            this.speedOfSimulation = speed;
        } else {
            this.thread.speedSimulation(speed);
        }
    }
    /**
     * Returns the instance of virusManager.
     * @return virusManager
     */
    @Override
    public VirusManager getVirusManager() {
       return this.virusManager;
    }
    /**
     * Method to return the size of the map.
     * @return mapSize 
     */
    @Override
    public double getMapSize() {
        synchronized (this) {
            return this.controllerEnvironment.getMapSize();
        }
    }

    private class SimulationThread extends Thread {

        private volatile boolean stopped;

        /**
         * Constructor of the SimulationThread.
         */
        SimulationThread() {
            this.stopped = false;
        }

        /**
         * Running the thread.
         */
        public void run() {
            isRunning = true;
            while (!stopped && !isOver()) {
                final long startTime = System.currentTimeMillis();
                synchronized (SimulationControllerImpl.this) {
                    controllerEnvironment.movePeople();

                    final List<Person> infectedOutside = infectionObserver.detectInfectionBeetweenPeople(controllerEnvironment.getPeopleFixtures());
                    infectedOutside.forEach(p -> infectManager.addInfected(quantiumOfTime, p));

                    final List<Person> infectedMPlace = infectionObserver.tryToEnterAndDetectInfectionInMeetingPlace(
                            controllerEnvironment.getPeopleFixtures(), controllerEnvironment.getMeetingPlaceFixtures(), quantiumOfTime);
                    infectedMPlace.forEach(p -> infectManager.addInfected(quantiumOfTime, p));


                    peopleManager.goHome(controllerEnvironment.getPeopleOutside()).
                    forEach(p -> controllerEnvironment.moveToHome(p, quantiumOfTime));

                    infectManager.changeStatus(quantiumOfTime)
                           .forEach(p -> controllerEnvironment.moveToHospital(p, quantiumOfTime));

                    controllerEnvironment.exitFromHome(quantiumOfTime);

                    controllerEnvironment.exitFromMeetingPlaces(quantiumOfTime);
                    if (quantiumOfTime % DAY == 0) {
                        final List<Person> naturalDeath = peopleManager.death(controllerEnvironment.getPeopleOutside());
                        naturalDeath.forEach(p -> controllerEnvironment.removePerson(p));
                        chartsManager.addDeath(naturalDeath.size(), quantiumOfTime);

                        final List<Person> birth = peopleManager.birth(controllerEnvironment.getAllPeople().size());
                        birth.forEach(p -> controllerEnvironment.addPerson(p));
                        chartsManager.addBirth(birth.size(), quantiumOfTime);
                    }

                    final HospitalizationOutcome exitFromHospital = controllerEnvironment.exitFromHospital(quantiumOfTime);
                    chartsManager.addInfect(infectedOutside.size() + infectedMPlace.size(), quantiumOfTime);
                    chartsManager.addVirusDeath(exitFromHospital.getDeadPeople().size(), quantiumOfTime);
                    chartsManager.addRecovered(exitFromHospital.getRecoveredPeople().size(), quantiumOfTime);
                    chartsManager.addRateInfectivity(controllerEnvironment.getAllPeople().size(), quantiumOfTime);
                    chartsManager.addHealthy(quantiumOfTime);

                    simulationView.updatePeoplePosition(controllerEnvironment.getPeopleFixtures());
                    final DataGetter simulationData =  chartsManager.getData();
                    simulationView.updateSimulationInfo(controllerEnvironment.getPeopleHome(), controllerEnvironment.getPeopleOutside().size(),
                            controllerEnvironment.getPeopleMeetingPlace(),
                            controllerEnvironment.getPeopleHospital(),
                            simulationData.getTotalInfect().getYValue().intValue(),
                            simulationData.getHealthy().getYValue().intValue(),
                            simulationData.getTotalVirusDeath().getYValue().intValue(),
                            simulationData.getTotalRecovered().getYValue().intValue());
                    chart.update(simulationData);
                }
                quantiumOfTime++;
                final long endTime = System.currentTimeMillis();
                final long timeElapsed = endTime - startTime;
                if (timeElapsed < (MAXSPEED / speedOfSimulation)) {
                    try {
                         sleep((MAXSPEED / speedOfSimulation) - timeElapsed);
                    } catch (InterruptedException e) {
                         e.printStackTrace();
                    }
                }
            }
            synchronized (SimulationControllerImpl.this) {
            if (isOver()) {
                    view.simulationResult(controllerEnvironment.getAllPeople().size() > 0);
                }
            }
        }

        /**
         * Method to stop execution.
         */
        public void notifyStopped() {
            this.stopped = true;
        }

        /**
         * Simulation speed.
         * 
         * @param speedS
         */
        public void speedSimulation(final int speedS) {
            speedOfSimulation = speedS;
        }

        private boolean isOver() {
            synchronized (SimulationControllerImpl.this) {
                return controllerEnvironment.isHospitalEmpty() && !infectManager.isAnyoneInfected();
            }
        }
    }
}
