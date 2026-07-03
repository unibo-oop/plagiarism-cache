package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import controller.player.User;
import utility.Driver;
import controller.CircuitPlayable;
import controller.ControllerImpl;
import controller.player.Player;

/**
 * A class to test the controller methods.
 */
public class TestController {

    //private static final Set<Driver> DRIVER = Stream.of(Driver.values()).collect(Collectors.toSet());
    //private static final int FIVE = 5;

    /**
     * 
     */
    @Test
    public void testCreationDriver() {
        //Adding some user
        final ControllerImpl example = this.startController();
        example.addPlayer("Pippo Giovanni", Driver.ALO);
        example.addPlayer("Mara Maionchi", Driver.BOT);
        example.addPlayer("Spider-man", Driver.VAN);

        List<Driver> list = example.getPlayerList().stream()
                                                .map(x -> x.getDriver())
                                                .collect(Collectors.toList());
        assertEquals(list.size(), 3);
        assertFalse(list.contains(Driver.HAM));
        assertTrue(list.contains(Driver.ALO));
        /*
        example.startWeekend();
        list = example.getPlayerList().stream()
                                    .map(x -> x.getDriver())
                                    .collect(Collectors.toList());
        assertEquals(DRIVER.size(), list.size());
        assertTrue(list.contains(Driver.HAM));
        */

        //This time there won't be users, only AI players
        final ControllerImpl example2 = this.startController();
        list = example2.getPlayerList().stream()
                                     .map(x -> x.getDriver())
                                     .collect(Collectors.toList());
        assertEquals(list.size(), 0);
        assertFalse(list.contains(Driver.HAM));
        assertFalse(list.contains(Driver.ALO));
        /*
        example2.startWeekend();
        list = example2.getPlayerList().stream()
                     .map(x -> x.getDriver())
                     .collect(Collectors.toList());
        assertEquals(DRIVER.size(), list.size());
        assertTrue(list.contains(Driver.HAM));
        */
    }

    /**
     * 
     */
    @Test
    public void testCreationUser() {
        final ControllerImpl example = this.startController();
        example.setCircuit(CircuitPlayable.MELBOURNE);
        example.addPlayer("Pippo Giovanni", Driver.ALO);
        example.addPlayer("Mara Maionchi", Driver.BOT);
        example.addPlayer("Spider-man", Driver.VAN);

        final List<Player> list = example.getPlayerList();
        assertTrue(list.get(0) instanceof User);
        assertEquals(list.get(1).getDriver(), Driver.BOT);
        /*
        example.startWeekend();
        list = example.getPlayerList();
        assertTrue(list.get(FIVE) instanceof AI);
        assertTrue(list.get(2) instanceof User);
        assertEquals(list.get(1).getDriver(), Driver.BOT);
        assertFalse(list.get(3).getDriver().equals(Driver.VAN));
        assertEquals("Spider-man", ((User) list.get(2)).getName());
        */

        final ControllerImpl example2 = this.startController();
        example2.addPlayer("Hulk", Driver.ALO);
        try {
            example2.addPlayer("Iron-Man", Driver.ALO);
            fail("You can't add two users with the same driver!!!");
        } catch (IllegalArgumentException e) {
            System.out.println("Ok");
        }
    }

    private ControllerImpl startController() {
        final ControllerImpl ctr = new ControllerImpl(new ViewTest());
        ctr.setCircuit(CircuitPlayable.MELBOURNE);
        return ctr;
    }

}
