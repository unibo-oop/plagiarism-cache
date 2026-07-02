package person.environment.controller;

import java.util.Random;

import controller.EnvironmentControllerImpl;
import model.person.environment.EnvironmentImpl;
import model.person.ticket.PersonTicket;
import person.environment.motion.PeopleRecirculationGui;

public final class PeopleRecirculation {

    private final Random rand = new Random();
    private static final double MAX_EXIT = 0.3;
    private final EnvironmentImpl environment;
    private final EnvironmentControllerImpl controller;
    private final PersonIntoPark park;
    private final PeopleRecirculationGui recirculationGui;

    public PeopleRecirculation(final EnvironmentImpl environment, final EnvironmentControllerImpl controller, final PersonIntoPark park, final PeopleRecirculationGui recirculationGui) {
        super();
        this.environment = environment;
        this.controller = controller;
        this.park = park;
        this.recirculationGui = recirculationGui;
    }

    /**
     * This method generates tow number random that represents the number of people that enter and exit for the park.
     */
    public void recirculation() {
        int randPeopleExit;
        final int personCanEnter = controller.getVisitorsNumber() - park.getPeopleIntoPark();
        final int randPeopleEntrance = rand.nextInt(personCanEnter);
        for (int i = 0; i < randPeopleEntrance; i++) {
            final PersonTicket person = new PersonTicket();
            environment.peopleEntrance(person);
            recirculationGui.peopleEntrance(person);
            park.incPeopleIntoPark();
        }
        final int numExit = (int) (controller.getVisitorsNumber() * MAX_EXIT);
        do {
            randPeopleExit = rand.nextInt(numExit);
        }
        while (randPeopleExit > environment.getPersonList().size() * MAX_EXIT);
        for (int i = 0; i < randPeopleExit; i++) {
            recirculationGui.peopleExit(environment.getPersonList().get(0));
            environment.exitPeople();
            park.decPeopleIntoPark();
        }
    }
}
