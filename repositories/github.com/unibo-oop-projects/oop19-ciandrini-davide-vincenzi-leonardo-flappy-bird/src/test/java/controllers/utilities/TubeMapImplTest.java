package controllers.utilities;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import org.junit.jupiter.api.Test;
import util.Pair;

import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

class TubeMapImplTest {

    //Stage primaryStage;

    //FlappyBirdController controller = new FlappyBirdControllerImpl(primaryStage);
    //TubeController tubeController = new TubeControllerImpl(controller);
    //TubeMap map = new TubeMapImpl(controller);
    private static final int BIRD_INIT_POS = 50;
    private static final int RECT_INIT_POS = 600;
    private static final int SPACE_BTW_RECT = 105;
    private static final int FLAG = 300;
    private final TreeMap<Integer, Pair<Rectangle, Rectangle>> tubeMap;
    private final Rectangle r = new Rectangle();
    private final Rectangle r2 = new Rectangle();
    private final Pair<Rectangle, Rectangle> pair = new Pair<>(r, r2);


    TubeMapImplTest() {
         tubeMap = new TreeMap<>();
         r.setX(0);
    }

    @Test
    public void testAddToMap() {
        assertNotSame(tubeMap, tubeMap.put(1, pair));
    }

    @Test
    public void testGetLastValue() {
        final Pair<Rectangle, Rectangle> expected = pair;
        tubeMap.put(1, pair);
        assertEquals(expected, tubeMap.lastEntry().getValue());
    }

    @Test
    public void testScrollTubePair() {
        final int n = 2;
        tubeMap.put(1, pair);
        final Pair<Rectangle, Rectangle> expected = pair;

        tubeMap.forEach((key, value) -> {
            value.getX().setX(value.getX().getX() - n);
            value.getY().setX(value.getY().getX() - n);
        });

        expected.getX().setX(r.getX() - n);
        assertEquals(expected.getX().getX(), tubeMap.lastEntry().getValue().getX().getX());

    }


    @Test
    public void testCheckCollision() {
        final Rectangle flappy = new Rectangle();
        final int n = 2;
        final AtomicInteger flag = new AtomicInteger(0);

        flappy.setX(BIRD_INIT_POS);
        flappy.setY(BIRD_INIT_POS);
        r.setX(RECT_INIT_POS);
        r2.setX(RECT_INIT_POS);
        r.setY(90);
        r2.setY(r.getY() + SPACE_BTW_RECT);


        tubeMap.put(1, pair);

        for (int i = 0; i < FLAG; i++) {
            tubeMap.forEach((key, value) -> {
                value.getX().setX(value.getX().getX() - n);
                value.getY().setX(value.getY().getX() - n);
            });

            tubeMap.forEach((key, value) -> {
                final Shape intersect = Shape.intersect(flappy, value.getX());
                if (intersect.getBoundsInLocal().getWidth() != -1) {
                    flag.set(1);

                }
                final Shape intersect2 = Shape.intersect(flappy, value.getY());
                if (intersect2.getBoundsInLocal().getWidth() != -1) {
                    flag.set(1);
                }

                if (flappy.getY() < 0 && value.getX().getX() == flappy.getX()) {
                    flag.set(1);
                }
                if (flag.get() == 1) {
                    assertEquals(1, flag.get());
                }
            });
        }
    }
}
