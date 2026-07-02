package view.renderer;

import java.util.List;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import model.ball.Ball;
import view.ViewSettings;

/**
 * Class used to render the Ball.
 */
public class BallRenderer {

    private static final Color BALL_COLOUR = Color.BLACK;

    /**
     * @param ball the {@link Ball} to be rendered
     * @return the {@link Node}s that represents the Ball in JavaFX
     */
    public final List<Node> render(final Ball ball) {
        return List.of(new Circle(ball.getPosition().getLeft() * ViewSettings.SCALE, ball.getPosition().getRight() * ViewSettings.SCALE, ball.getRadius() * ViewSettings.SCALE, BALL_COLOUR));
    }

}
