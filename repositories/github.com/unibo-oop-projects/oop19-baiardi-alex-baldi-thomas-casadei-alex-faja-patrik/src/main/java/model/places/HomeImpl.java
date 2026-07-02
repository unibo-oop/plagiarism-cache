package model.places;

import java.util.Random;
import model.people.Person;

/**
 *
 */
public class HomeImpl extends AbstractPlace implements Home {
    private static final int MAX_TIME = 48;
    private final double homeTendency;
    private double homeTendencyIncrement;

    /**
     * Constructor method for the home.
     * 
     * @param homeTendency The percentage of time that a people spend at home.
     */
    public HomeImpl(final double homeTendency) {
        this.homeTendency = homeTendency;
        this.homeTendencyIncrement = 0;
    }

    /**
     * 
     */
    @Override
    public void enter(final Person person, final int time) {
        final int exitTime = time + this.calculateTime();
        super.enter(person, exitTime);
    }

    /**
     * 
     */
    @Override
    public boolean checkPresence(final Person person) {
        return getAllPeople().contains(person);
    }

    /**
     * 
     */
    @Override
    public void setHomeTendencyIncrement(final double increment) {
        this.homeTendencyIncrement = increment;
    }

    private int calculateTime() {
        final Random r = new Random();
        return r.nextInt((int) Math.round(1 + 2 * MAX_TIME * (this.homeTendency + homeTendencyIncrement) / 100));
    }
}
