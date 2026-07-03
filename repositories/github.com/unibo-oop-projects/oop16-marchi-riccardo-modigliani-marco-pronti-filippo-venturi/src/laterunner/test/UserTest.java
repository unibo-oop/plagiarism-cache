package laterunner.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import laterunner.model.user.User;

/**
 * Tests User's methods.
 */
public class UserTest {

    private static User user = User.getUser();
    private static final double BAD_DOUBLE = 0.9;

    /**
     * Tests User's initial fields.
     */
    @Test
    public void testInitialFields() {
        user.reset();
        assertEquals(user.getLevelReached(), 1);
        assertEquals(user.getMoney(), 1000);
        assertTrue(user.getSpeedMul() == 1.0);
        assertEquals(user.getUserLives(), 3);
        user.setUserLives(-1);
        assertEquals(user.getUserLives(), 0);
    }

    /**
     * Tests if methods throw exceptions on particular situations.
     */
    @Test
    public void checkException() {
        try {
            user.setLevelReached(-1);
            fail("IllegalArgumentException expected!");
        } catch (final IllegalArgumentException e) { }

        try {
            user.setMoney(-1);
            fail("IllegalArgumentException expected!");
        } catch (final IllegalArgumentException e) { }

        try {
            user.setSpeedMul(BAD_DOUBLE);
            fail("IllegalArgumentException expected!");
        } catch (final IllegalArgumentException e) { }

        try {
            User.Statistic.getStatistic().setGamesPlayed(-1);
            fail("IllegalStateException expected!");
        } catch (final IllegalStateException e) { }
    }
}
