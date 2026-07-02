package tests;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import model.activity.Fair;
import model.person.ticket.PersonTicket;
import view.model.activity.ActivityType;

public class ActivityFairTest {

    private final Fair fair1 = new Fair("Katun", 12, ActivityType.FAIR);
    private final Fair fair3 = new Fair("Bruco", 10, ActivityType.BABYFAIR);
    private final PersonTicket per1 = new PersonTicket();
    private final PersonTicket per2 = new PersonTicket();

    @Test
    public final void profitType() {
        assertNotEquals(fair1.getActivityType(), fair3.getActivityType());
    }

    @Test
    public final void peopleEntraceFair() {
        fair1.addPerson(per1);
        fair1.addPerson(per2);
        assertEquals(2, fair1.getTotPeople());
    }

    @Test
    public final void exitFair() {
        fair1.removePerson();
        assertEquals(0, fair1.getPeopleList().size());
    }

    @Test
    public final void controlAgeTest() {
        assertTrue(fair3.controlAge(46));
        assertFalse(fair3.controlAge(11));
    }
}
