package person.environment.controller;

import java.util.Random;

import controller.EnvironmentControllerImpl;
import model.environment.open.OpenImpl;
import model.person.environment.EnvironmentImpl;
import person.environment.motion.PeopleMovingIntoPark;
import person.environment.motion.PeopleRecirculationGui;
import view.controller.ViewController;


public final class PersonIntoPark extends Thread {
    private static final int PERSON_RECIRCULATION = 1000;
    private int peopleIntoPark;
    private final EnvironmentControllerImpl controller;
    private final EnvironmentImpl environment;
    private final PeopleRecirculation recirculation;
    private final ActivityRide ride;
    private final PeopleMovingIntoPark peopleMoving;
    private final PeopleRecirculationGui recirculationGui;

    public PersonIntoPark(final EnvironmentControllerImpl controller, final ViewController viewController) {
        super();
        this.environment = new EnvironmentImpl();
        this.controller = controller;
        this.peopleMoving = new PeopleMovingIntoPark(viewController.getPeopleMap());
        this.recirculationGui = new PeopleRecirculationGui(viewController.getPeopleMap());
        this.recirculation = new PeopleRecirculation(this.environment, this.controller, this, this.recirculationGui);
        this.ride = new ActivityRide(this.controller, this.environment, this.peopleMoving);
        this.start();
    }

    /**
     * 
     * @return the number of people that enter into the park.
     */
    public int getPeopleIntoPark() {
        return peopleIntoPark;
    }

    /**
     * increments the number of people.
     */
    public void incPeopleIntoPark() {
        peopleIntoPark++; 
    }

    /**
     * decrements the number of people.
     */
    public void decPeopleIntoPark() {
        peopleIntoPark--; 
    }

    /**
     * At the open of the park it inserts a random number of people.
     */
    public void run() {
        final int maxFirstEntrance = (int) (this.controller.getVisitorsNumber() * 0.5);
        final Random rand = new Random();
        final int randomFirstEntrance = rand.nextInt(maxFirstEntrance);
        final OpenImpl open = new OpenImpl(randomFirstEntrance, this.environment);
        open.firstEntrance();
        peopleIntoPark = randomFirstEntrance;
        environment.getPersonList().forEach(p -> {
            recirculationGui.peopleEntrance(p); });
        System.out.print("main thread started");
    }

    /**
     * Activates the ride and recirculation methods.
     */
    public void logics() {
        ride.ride();
        try {
            Thread.sleep(PERSON_RECIRCULATION);
        } catch (InterruptedException ex) {
        }
        System.out.println("people:" + this.peopleIntoPark);
        recirculation.recirculation();
    }

    /**
     * 
     * @return the environment
     */
    public EnvironmentImpl getEnvironment() {
        return this.environment;
    }
}
