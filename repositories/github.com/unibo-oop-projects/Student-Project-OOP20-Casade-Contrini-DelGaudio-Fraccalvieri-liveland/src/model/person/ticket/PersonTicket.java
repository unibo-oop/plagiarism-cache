package model.person.ticket;
import java.util.Random;

import model.ticket.Ticket;

/*Class that assign to each person the age and the ticket*/
public final class PersonTicket implements PersonTicketInterface {

    private static final int AGE_MIN = 2;
    private static final int AGE_MAX = 95;
    private static final int AGE_MAX_REDUCED = 65;
    private static final int AGE_MIN_REDUCED = 12;
    private final int age;
    private final Ticket ticket;

    public PersonTicket() {
        this.age = this.randAge();
        this.ticket = this.randTicket();
    }

    /**
     * {@inheritDoc}
     */
    public int randAge() {
        final Random rand = new Random();
        return rand.nextInt((AGE_MAX - AGE_MIN) + 1) + AGE_MIN;
    }

    /**
     * {@inheritDoc}
     */
    public int getAge() {
        return age;
    }

    /**
     * {@inheritDoc}
     */
    public Ticket getTicket() {
        return ticket;
    }

    /**
     * {@inheritDoc}
     */
    public Ticket randTicket() {
        final Random rand = new Random();
        final int randomTicket = rand.nextInt(4);
        if (randomTicket == 3) {
            return Ticket.SEASON_PASS;
            } else if (this.getAge() < AGE_MIN_REDUCED || this.getAge() > AGE_MAX_REDUCED) {
                return Ticket.REDUCED;
            } else {
                    return Ticket.ADULT;
                }
    }

    public String toString() {
        return "PersonTicket [age=" + age + ", ticket=" + ticket + "]";
        }
}
