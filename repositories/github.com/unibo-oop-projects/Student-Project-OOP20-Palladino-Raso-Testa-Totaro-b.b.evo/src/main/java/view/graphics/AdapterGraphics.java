package view.graphics;

import javafx.scene.image.Image;
import model.entities.Ball;
import model.entities.Brick;
import model.entities.Paddle;
import model.entities.PowerUp;

public interface AdapterGraphics {

    /**
     * allows to draw the ball with the characteristics of the graphic component.
     * @param ball the object to draw
     * @param imageBall the image to use
     */
    void drawBall(Ball ball, Image imageBall);

    /**
     * allows to draw the paddle with the characteristics of the graphic component.
     * @param paddle the object to draw
     * @param imagePaddle the image to use
     */
    void drawPaddle(Paddle paddle, Image imagePaddle);

    /**
     * allows to draw the brick with the characteristics of the graphic component.
     * @param brick the object to draw
     * @param imageBrick the image to use
     */
    void drawBrick(Brick brick, Image imageBrick);

    /**
     * allows to draw the brick with the characteristics of the graphic component.
     * @param pwup the object to draw
     * @param pwupImage the image to use
     */
    void drawPowerUp(PowerUp pwup, Image pwupImage);
}
