package tests.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

import model.aim.Aim;
import model.aim.DoubleGarrisonAim;
import model.aim.StateAim;
import model.player.Player;
import model.player.PlayerImpl;
import utils.enumerations.Color;
import utils.enumerations.ControlType;

/**
 * Test for methods related to aims in class Player.
 */
public class TestAimInPlayer {
    private static final int DEFAULT_STATES = 3;
    private static final int DEFAULT_STATES_GARRISON = 2;

    /**
     * Test the correctness of exception thrown in method getAim().
     */
    @Test
    public void testGetAimException() {
        final Player tay = new PlayerImpl("Taylor Swift", Color.RED, ControlType.HUMAN, 100);
        try {
            tay.getAim();
            fail("call getAim() without having set n aim before should raise an exception!");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            fail("wrong exception thrown");
        }

    }

    /**
     * Test the correctness of exception thrown in method setAim(Aim aim).
     */
    @Test
    public void testSetAimException() {
        final Player tay = new PlayerImpl("Taylor Swift", Color.RED, ControlType.HUMAN, 100);
        final Aim a = new StateAim(DEFAULT_STATES, tay);
        final Aim b = new DoubleGarrisonAim(DEFAULT_STATES_GARRISON, tay);
        try {
            tay.setAim(null);
            fail("call setAim(Aim aim) passing a null aim should raise an exception!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            fail("wrong exception thrown");
        }

        tay.setAim(a);

        try {
            tay.setAim(b);
            fail("call setAim(Aim aim) when an aim has already been assigned should raise an exception!");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            fail("wrong exception thrown");
        }
    }

    /**
     * Test the correctness of methods getAim() setAim(Aim aim).
     */
    @Test
    public void testGetAndSetAimException() {
        final Player tay = new PlayerImpl("Taylor Swift", Color.RED, ControlType.HUMAN, 100);
        final Aim a = new StateAim(DEFAULT_STATES, tay);
        tay.setAim(a);
        assertEquals(tay.getAim(), a);

    }
}
