package zombieversity.controller.entities;

import static javafx.scene.input.KeyCode.A;
import static javafx.scene.input.KeyCode.D;
import static javafx.scene.input.KeyCode.LEFT;
import static javafx.scene.input.KeyCode.RIGHT;
import static javafx.scene.input.KeyCode.UP;
import static javafx.scene.input.KeyCode.W;

import java.util.Set;

import static javafx.scene.input.KeyCode.S;
import static javafx.scene.input.KeyCode.DOWN;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;

/**
 * 
 * Used to manage keyboard input.
 *
 */
public final class PlayerKeyboardInputUtils {

    private static Point2D point;
    private PlayerKeyboardInputUtils() {
    }

    /**
     * Used to calculate velocity based on keys pressed.
     * 
     * @param keys the set of keys pressed.
     * @return the velocity.
     */
    public static Point2D calculateVelocity(final Set<KeyCode> keys) {
        point = Point2D.ZERO;

        keys.stream().forEach(key -> {
            if (key.equals(A) || key.equals(LEFT)) {
                point = point.add(-1, 0);
            }
            if (key.equals(D) || key.equals(RIGHT)) {
                point = point.add(1, 0);
            }
            if (key.equals(W) || key.equals(UP)) {
                point = point.add(0, -1);
            }
            if (key.equals(S) || key.equals(DOWN)) {
                point = point.add(0, 1);
            }
        });
        return point;
    }
}
