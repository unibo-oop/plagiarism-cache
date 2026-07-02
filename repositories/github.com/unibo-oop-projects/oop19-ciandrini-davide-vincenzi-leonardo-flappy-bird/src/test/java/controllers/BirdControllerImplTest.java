package controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import javafx.scene.shape.Rectangle;
import model.bird.Bird;
import model.bird.BirdImpl;


class BirdControllerImplTest {

    private static final int HEIGHT_WINDOWS = 335;

    @Test
    void floorCollision() {
        int num;
        final Rectangle rectangle = new Rectangle();
        final Bird bird = new BirdImpl();

        num = HEIGHT_WINDOWS - bird.getHeightBird();
        rectangle.setY(num);
        bird.setPosY(num);
        assertEquals(bird.getPosY(), rectangle.getY());
    }
}
