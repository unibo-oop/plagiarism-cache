package unibo.exiled;

import org.junit.jupiter.api.Test;


/**
 * Tests the opening of the game.
 */
class TestGameLauncher {
    @Test
    void testGameInit() {
        GameLauncher.main(new String[]{"Arg1", "Arg2"});
    }
}
