package breakout.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertSame;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import breakout.model.AdvancedMode;
import breakout.model.Model;
import breakout.model.entities.Ball;
import breakout.model.entities.Brick;
import breakout.model.entities.BrickType;
import breakout.model.levels.Grid;
import breakout.model.levels.LevelBuilder;
import breakout.model.levels.LevelImpl;
import breakout.model.physics.GameObject;
import breakout.model.physics.MyBoundingBox;
import breakout.view.graphics.Colors;
import javafx.util.Pair;

/**
 * Test bounding box.
 *
 */
public class BoundingBoxTest {

    /**
     * Test.
     */
    public void boundingBoxTest() {
        final MyBoundingBox b1 = new MyBoundingBox(0, 9, 7, 1);
        final MyBoundingBox b2 = new MyBoundingBox(5, 0, 3, 10);
        assertSame(b1.getWidth(), 7);
        assertSame(b2.getHeight(), 10);
        assertSame(b1.overlapX(b2), 2);
        assertSame(b1.overlapY(b2), 1);
        assertSame(b1.overlapX(b2), b2.overlapX(b1));
    }
    @Test
    public void simpleBricksBounceTest() {

        // Crea un livello con 2 mattoncini in posizione centrale
        final LevelBuilder b = new LevelBuilder();
        final Grid<Pair<BrickType, Colors>> grid = new Grid<>(12, 20);
        grid.add(0, 10, new Pair<BrickType, Colors>(BrickType.SIMPLE_ADVANCED, null));
        grid.add(0, 11, new Pair<BrickType, Colors>(BrickType.SIMPLE_ADVANCED, null));
        b.list(grid);
        final LevelImpl level = b.build();

        // Crea una partita
        final AdvancedMode model = Model.createAdvancedGame(Arrays.asList(level));
        final Ball mainBall = model.getBalls().get(0);
        final Brick brick1 = model.getBricks().get(0);
        final Brick brick2 = model.getBricks().get(1);
        mainBall.setVelocity(0, -1); // Cambio la velocità per facilitare i
                                     // conti
        mainBall.setPosition(645, 300);
        model.start();

        // Test
        model.updateAll(280);

        // La palla collide con i 2 mattoncini
        assertTrue(mainBall.collidedWith(brick1));
        assertTrue(mainBall.collidedWith(brick2));

        // Il mattoncino di destra ha meno superficie di contatto

        assertTrue(mainBall.getBounds().intersectingArea(brick1.getBounds()) > mainBall.getBounds().intersectingArea(
                brick2.getBounds()));

        // in caso di collisione con 2 mattoncini viene distrutto quello che
        // ha la maggior superficie di contatto con la palla

        final List<GameObject> collisions = model.checkCollisions();
        assertSame(collisions.size(), 2);
        model.updateAll(1);
        assertTrue(model.getBricks().contains(brick2) && !model.getBricks().contains(brick1));

        // La palla deve rimbalzare verso il basso
        assertTrue(mainBall.getVelocity().getY() > 0);

    }

    @Test
    public void unbreakableBricksBounceTest() {

        // Crea un livello con 2 mattoncini in posizione centrale
        final LevelBuilder b = new LevelBuilder();
        final Grid<Pair<BrickType, Colors>> grid = new Grid<>(12, 20);
        grid.add(0, 10, new Pair<BrickType, Colors>(BrickType.UNBREAKABLE_ADVANCED, null));
        grid.add(0, 11, new Pair<BrickType, Colors>(BrickType.UNBREAKABLE_ADVANCED, null));

        // Se non creiamo questo la partita non può iniziare perchè abbiamo già
        // vinto
        grid.add(0, 0, new Pair<BrickType, Colors>(BrickType.HARD_ADVANCED, null));
        b.list(grid);
        final LevelImpl level = b.build();

        // Crea una partita
        final AdvancedMode model = Model.createAdvancedGame(Arrays.asList(level));
        final Ball mainBall = model.getBalls().get(0);
        final Brick brick1 = model.getBricks().get(1);
        final Brick brick2 = model.getBricks().get(2);
        mainBall.setVelocity(0, -1); // Cambio la velocità per facilitare i
                                     // conti
        mainBall.setPosition(645, 300);

        model.start();

        // la palla rimbalza ma i mattoncini non vengono distrutti
        model.updateAll(280);

        // La palla collide con i 2 mattoncini
        assertTrue(mainBall.collidedWith(brick1));
        assertTrue(mainBall.collidedWith(brick2));

        // Il mattoncino di destra ha meno superficie di contatto

        assertTrue(mainBall.getBounds().intersectingArea(brick1.getBounds()) > mainBall.getBounds().intersectingArea(
                brick2.getBounds()));

        final List<GameObject> collisions = model.checkCollisions();
        assertSame(collisions.size(), 2);
        model.updateAll(1);
        assertTrue(model.getBricks().contains(brick2) && model.getBricks().contains(brick1));

        // la palla deve andare verso il basso
        assertTrue(mainBall.getVelocity().getY() > 0);

    }
}
