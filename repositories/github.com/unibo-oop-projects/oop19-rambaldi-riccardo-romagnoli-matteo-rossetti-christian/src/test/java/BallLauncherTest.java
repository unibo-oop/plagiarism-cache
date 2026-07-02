import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import model.balllaucher.BallLauncher;
import model.balllaucher.BallLauncherImpl;
import model.world.WorldSettings;

class BallLauncherTest {

    private static final double TOLLERANCE = 0.01;
    private Pair<Double, Double> expectedPosition;

    @Test
    void updateLauncher() {
        final BallLauncher launcher = new BallLauncherImpl();
        launcher.update(Pair.of(1., 1.));
        expectedPosition = Pair.of(launcher.getHeight() * Math.sin(Math.PI / 4), WorldSettings.WORLD_HEIGHT - launcher.getHeight() * Math.sin(Math.PI / 4));
        assertTrue(
                launcher.getPosition().getLeft() - expectedPosition.getLeft() < TOLLERANCE
                && launcher.getPosition().getRight() - expectedPosition.getRight() < TOLLERANCE
        );
        launcher.update(Pair.of(-1., 1.));
        expectedPosition = Pair.of(launcher.getHeight() * Math.sin(Math.PI / 4), WorldSettings.WORLD_HEIGHT - launcher.getHeight() * Math.sin(Math.PI / 4));
        assertTrue(
                launcher.getPosition().getLeft() - expectedPosition.getLeft() < TOLLERANCE
                && launcher.getPosition().getRight() - expectedPosition.getRight() < TOLLERANCE
        );
        launcher.update(Pair.of(1., 0.));
        expectedPosition = Pair.of(launcher.getHeight(), WorldSettings.WORLD_HEIGHT);
        assertEquals(expectedPosition, launcher.getPosition());
        launcher.update(Pair.of(-1., 0.));
        expectedPosition = Pair.of(-launcher.getHeight(), WorldSettings.WORLD_HEIGHT);
        assertEquals(expectedPosition, launcher.getPosition());
    }

}
