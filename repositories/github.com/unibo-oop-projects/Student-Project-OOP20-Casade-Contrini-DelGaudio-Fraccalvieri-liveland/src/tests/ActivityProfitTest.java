package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import model.activity.Profit;
import model.person.ticket.PersonTicket;
import view.model.activity.ActivityType;

public class ActivityProfitTest {

    private final Profit rest1 = new Profit("Pizza", 10, 50, ActivityType.REST);
    private final Profit shop1 = new Profit("cartoline", 10, 50, ActivityType.SHOP);
    private final PersonTicket per1 = new PersonTicket();
    private final PersonTicket per2 = new PersonTicket();

    @Test
    public final void profitType() {
        assertNotEquals(rest1.getActivityType(), shop1.getActivityType());
    }

    @Test
    public final void peopleEntraceShop() {
        shop1.addPerson(per1);
        shop1.addPerson(per2);
        assertEquals(2, shop1.getTotPeople());
    }

    @Test
    public final void exitFair() {
        shop1.removePerson();
        assertEquals(0, shop1.getPeopleList().size());
    }

    @Test
    public final void totalPeople() {
        assertEquals(0, shop1.getTotPeople());
    }

    @Test
    public final void totalProfit() {
        shop1.amount();
        shop1.getProfit();
        assertNotEquals(0, shop1.getProfit());
    }

}

