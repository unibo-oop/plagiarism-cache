package person.environment.controller;

import java.util.Random;

import controller.EnvironmentControllerImpl;
import model.activity.Fair;
import model.activity.Profit;
import model.person.environment.EnvironmentImpl;
import person.environment.motion.PeopleMovingIntoPark;
import view.model.activity.ActivityType;

public final class ActivityRide {
    private final EnvironmentControllerImpl controller;
    private final EnvironmentImpl environment;
    private final Random rand = new Random();
    private static final int FIRST_PERSON = 0;
    private static final int SLEEP = 500;
    private static final int MAX_PROFIT = 50;
    private final PeopleMovingIntoPark peopleMoving;

    public ActivityRide(final EnvironmentControllerImpl controller, final EnvironmentImpl environment, final PeopleMovingIntoPark peopleMoving) {
        super();
        this.controller = controller;
        this.environment = environment;
        this.peopleMoving = peopleMoving;
    }

    /**
     * it generates a random number of every activity and moves the person into the activity for the ride.
     */
    public void ride() {
        //System.out.print("Ride thread started");
        int randParticipant;
        for (final Fair f : controller.getFairList()) {
            if (environment.getPersonList().size() == 0) {
                break;
            }
            do {
                randParticipant = rand.nextInt(f.getCapacity());
            } while (randParticipant >= environment.getPersonList().size());
                for (int i = 0; i < randParticipant; i++) {
                    if (f.getActivityType() == ActivityType.BABYFAIR) {
                        f.addPerson(environment.getPersonList().get(FIRST_PERSON));
                        peopleMoving.goToFair(environment.getPersonList().get(FIRST_PERSON), f);
                        environment.exitPeople();
                    } else {
                        if (f.controlAge(environment.getPersonList().get(FIRST_PERSON).getAge())) {
                            f.addPerson(environment.getPersonList().get(FIRST_PERSON));
                            peopleMoving.goToFair(environment.getPersonList().get(FIRST_PERSON), f);
                            environment.exitPeople();
                        }
                    }
                }
        }

        for (final Profit p : controller.getProfitList()) {
            do {
                randParticipant = rand.nextInt(MAX_PROFIT);
            } while (randParticipant >= environment.getPersonList().size());
            for (int i = 0; i < randParticipant; i++) {
                p.addPerson(environment.getPersonList().get(FIRST_PERSON));
                peopleMoving.goToProfit(environment.getPersonList().get(FIRST_PERSON), p);
                environment.exitPeople();
                }
        }
        try {
            Thread.sleep(SLEEP);
        } catch (InterruptedException ex) {
        }

        for (final Fair f: controller.getFairList()) {
            environment.addPersonList(f.getPeopleList());
            f.removePerson();
        }
        for (final Profit p: controller.getProfitList()) {
            environment.addPersonList(p.getPeopleList());
            p.removePerson();
        }

    }

}
