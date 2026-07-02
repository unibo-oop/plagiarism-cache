package view.renderer;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model.obstacle.Obstacle;
import model.obstacle.ObstacleType;
import view.ViewSettings;

/**
 * The Obstacle Renderer class renders the obstacles.
 */

public class ObstacleRenderer {

    /**
     * Renders the given obstacle, based on its shape.
     * @param obstacle to render.
     * @return the rendered obstacle ({@link Circle} or {@link Rectangle).
     */

    public final Node render(final Obstacle obstacle) {
        if (obstacle.getType().equals(ObstacleType.RECTANGLE)) {
            return this.rectangleRenderer(obstacle);
        } else {
            return this.circleRenderer(obstacle);
        }
    }

    /**
     * Method to render the given rectangle.
     * @param obstacle the rectangular obstacle to render.
     * @return the rendered rectangle.
     */
    private Rectangle rectangleRenderer(final Obstacle obstacle) {
        final double width = obstacle.getMeasures().get(0) * ViewSettings.SCALE;
        final double height = obstacle.getMeasures().get(1) * ViewSettings.SCALE;
        final double angle = obstacle.getMeasures().get(2);
        final Rectangle rectangle = new Rectangle(width, height, this.getColor(obstacle));
        rectangle.setX(obstacle.getPosition().getLeft() * ViewSettings.SCALE - width / 2);
        rectangle.setY(obstacle.getPosition().getRight() * ViewSettings.SCALE - height / 2);
        rectangle.setRotate(angle);
        rectangle.setStroke(this.getColor(obstacle).darker());
        return rectangle;
    }

    /**
     * Method to render the given circle.
     * @param obstacle the circular obstacle to render.
     * @return the rendered circle.
     */
    private Circle circleRenderer(final Obstacle obstacle) {
        final Circle circle = new Circle(obstacle.getPosition().getLeft() * ViewSettings.SCALE, obstacle.getPosition().getRight() * ViewSettings.SCALE,
                obstacle.getMeasures().get(0) * ViewSettings.SCALE, this.getColor(obstacle));
        circle.setStroke(this.getColor(obstacle).darker());
        return circle;
    }

    private Color getColor(final Obstacle obstacle) {
        Color color = null;
        switch (obstacle.getBehavior()) {
            case BLU: 
                if (!obstacle.hit()) {
                    color = Color.BLUE;
                } else { 
                    color = Color.DODGERBLUE;
                }
            break;
            case GREEN:
                if (!obstacle.hit()) {
                    color = Color.GREEN;
                } else { 
                    color = Color.LIMEGREEN;
                }
            break;
            case PURPLE: 
                if (!obstacle.hit()) {
                    color = Color.PURPLE;
                } else { 
                    color = Color.MEDIUMPURPLE;
                }
            break;
            case RED: 
                if (!obstacle.hit()) {
                    color = Color.RED;
                } else { 
                    color = Color.DARKORANGE;
                }
            break;
            default:
                break;
      }
      return color;
    }
}
