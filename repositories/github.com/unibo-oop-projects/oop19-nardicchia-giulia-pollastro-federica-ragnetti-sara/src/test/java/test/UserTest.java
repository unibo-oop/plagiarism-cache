package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import model.UserPlayer;
import model.UserPlayerImpl;

/**
 * Class to test if {@link UserPlayer} works correctly.
 *
 */
public class UserTest {

    @Test
    public void testUser() {
        final UserPlayer player = new UserPlayerImpl.PlayerBuilder()
                .username("user")
                .password("pass")
                .build();
        assertEquals("user", player.getUsername());
        assertEquals("pass", player.getPassword());
    }

}
