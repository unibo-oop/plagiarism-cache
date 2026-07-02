package it.unibo.biscia.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import it.unibo.biscia.core.Controller;
import it.unibo.biscia.core.ControllerImpl;
import it.unibo.biscia.core.Direction;
import it.unibo.biscia.core.Entity;
import it.unibo.biscia.core.Level;
import it.unibo.biscia.core.Player;
import it.unibo.biscia.events.StateObserver;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

/**
 * gloabl test.
 *
 */
public class TestBiscia {

    /**
     * test if code generate error.
     * 
     * @param runnable operation to test errors
     * @param error    error to expect
     * @param messages detailed message
     */
    public static void checkErrors(final Runnable runnable, final Throwable error, final String messages) {
        try {
            runnable.run();
            fail("Expected error absent: " + messages);
        } catch (Exception e) {
            if (e.getClass().isAssignableFrom(error.getClass())) {
                System.out.println(e.getClass() + " isAssignableFrom " + error.getClass());

            } else {
                fail("Unexpected error: " + e.getClass().getName() + " " + messages);
            }
        }
    }

    /**
     * test for controller.
     */
    @Test
    public void testController() {

        try {
            List<Player> players;
            // test for initial parameters
            // names null
            // checkErrors(() -> new ControllerImpl(null, 0, false), new
            // NullPointerException(), "player names null");
            // no names
            checkErrors(() -> new ControllerImpl(Collections.emptyList(), 0, false), new InvalidParameterException(),
                    "player names empty");
            // not uniqueness name

            checkErrors(() -> new ControllerImpl(Arrays.asList("Biscia", "Biscia"), 0, false),
                    new InvalidParameterException(), "player names uniquenes");
            final var names = Arrays.asList("Biscia", "Serpente", "Vipera");
            final Controller controller = // new ControllerDebug(
                    new ControllerImpl(names, 1, false
                    // )
                    );
            // test for players
            players = controller.getPlayers();
            System.out.println(players);
            assertEquals(names.size(), players.size(), "number of players");
            players.forEach(player -> {
                assertEquals(Player.INITIAL_LIVES, player.getLives(), "initial player lives");
                assertEquals(1L, names.stream().filter(name -> name.equals(player.getName())).count(),
                        "not find one player");
                assertEquals(0, player.getPoints(), "initial player points");
                assertEquals(Player.INITIAL_LIVES, player.getLives(), "initial player lives");
            });

            final var stateObs = new StateObserver() {
                private volatile boolean iniziato;
                private volatile boolean pause = true;
                private Level level;

                public boolean inPause() {
                    return this.pause;
                }

                @Override
                public void update(final List<Entity> entities) {
                    System.out.println("test update " + entities);
                    assertFalse("update entity in pause", this.pause);
                }

                @Override
                public void gameResume() {
                    System.out.println("test gameResume");
                    assertTrue("resume not started", this.iniziato);
                    assertTrue("resume not in pause", this.pause);
                    this.pause = false;
                }

                @Override
                public void remove(final List<Entity> entities) {
                    System.out.println("test remove " + entities);
                    assertFalse("remove entity in pause", this.pause);
                }

                @Override
                public void gamePause() {
                    System.out.println("gamePause");
                    if (this.iniziato) {
                        assertFalse("pause in pause", this.pause);
                    }
                    this.pause = true;
                }

                @Override
                public void newLevel(final Level level) {
                    System.out.println("newLevel " + level);
                    this.pause = true;
                    this.iniziato = true;
                    this.level = level;
                }

                @Override
                public void gameOver() {
                    System.out.println("gameOve");
                    this.pause = true;
                }

                @Override
                public void add(final List<Entity> entities) {
                    System.out.println("test add " + entities + " Level=" + this.level);
                }

                @Override
                public void updatePlayer(final Player player) {
                    System.out.println("test updatePlayer " + player + " players=" + players);
                }
            };
            controller.attachStateObserver(stateObs);
            controller.start();
            controller.pauseAndResume();
            assertFalse("Resume fail", stateObs.inPause());
            controller.pauseAndResume();
            assertTrue("Pause fail", stateObs.inPause());
            controller.pauseAndResume();
            controller.move(players.get(0), Direction.UP);
            controller.move(players.get(1), Direction.DOWN);
            final int sleepXSec = 10_000;
            Thread.sleep(sleepXSec);
        } catch (Exception e) {
            e.printStackTrace();
            fail(e.toString());
        }
    }
}
