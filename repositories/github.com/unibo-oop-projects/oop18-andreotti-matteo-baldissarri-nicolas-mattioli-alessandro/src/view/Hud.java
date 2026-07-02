package view;

import java.awt.Toolkit;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import view.elements.HeartView;

/**
 * 
 * @author mannaro
 *
 */
public class Hud extends Canvas {

    private static final double TEXT_WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 15;
    private static final double TEXT_X = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 100;
    private static final double TEXT_Y = Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 1.02;

    private final GraphicsContext gc = this.getGraphicsContext2D();

    /**
     * 
     * @param width  Horizontal dimension of the frame.
     * @param height Vertical dimension of the frame.
     */
    public Hud(final double width, final double height) {
        super(width, height);
    }

    /**
     * 
     * @param actualLife The number of remaining lifes.
     * @param heart      The representation of life.
     */
    public void drawLife(final HeartView heart, final int actualLife) {
        for (int i = 0; i < actualLife; i++) {
            gc.drawImage(heart.getSprites().get(0), heart.getDimension().getWidth() * i, 0);
        }
    }

    /**
     * 
     * @param points Points accumulated so far.
     */
    public void drawPoints(final int points) {
        gc.setFont(new Font(TEXT_WIDTH));
        gc.fillText(Integer.toString(points), TEXT_X, TEXT_Y);
    }

    /**
     * Method to clear lifes and score.
     */
    public void clearHUD() {
        gc.clearRect(0, 0, this.getWidth(), this.getHeight());
    }
}
