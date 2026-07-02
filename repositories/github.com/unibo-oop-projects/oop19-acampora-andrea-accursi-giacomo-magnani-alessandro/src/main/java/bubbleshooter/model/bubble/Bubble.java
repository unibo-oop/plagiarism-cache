package bubbleshooter.model.bubble;

import java.util.Optional;

import bubbleshooter.model.Model;
import bubbleshooter.model.component.Component;
import bubbleshooter.model.component.ComponentType;
import bubbleshooter.model.game.level.Level;
import javafx.geometry.Point2D;

/**
 * The class which represent the main entity of the game.
 *
 */
public interface Bubble {

    /**
     * The width of the {@link Bubble} in the game.
     */
    double WIDTH = Model.WORLD_WIDTH / (Level.NUM_BUBBLES_PER_ROW + 0.5);

    /**
     * The radius of the {@link Bubble} in the game.
     */
    double RADIUS = WIDTH / 2;

    /**
     * Method to update the {@link Bubble} every GameLoop cycle.
     * 
     * @param elapsed The time elapsed every {@link GameLoop} cycle.
     */
    void update(double elapsed);

    /**
     * 
     * @return True if the bubble is exploded.
     */
    boolean isDestroyed();

    /**
     * Method to set the position of the {@link Bubble} in the game.
     * 
     * @param position The position of the bubble.
     */
    void setPosition(Point2D position);

    /**
     * Method to set the direction of the {@link ShootingBubble} in the game.
     * 
     * @param direction The direction of the {@link ShootingBubble}.
     */
    void setDirection(Point2D direction);

    /**
     * Method to set the type of the {@link Bubble}.
     * 
     * @param type The type of the bubble.
     */
    void setType(BubbleType type);

    /**
     * Method to destroy a {@link Bubble} and remove it from the grid.
     */
    void destroy();

    /**
     * 
     * @param color The {@link BubbleColor} of the bubble.
     */
    void setColor(BubbleColor color);

    /**
     * Method to add a {@link Component} to a {@link Bubble}.
     * 
     * @param component The Component to add.
     */
    void addComponent(Component component);

    /**
     * @return The radius of the bubble.
     */
    double getRadius();

    /**
     * @return The width of the bubble.
     */
    double getWidth();

    /**
     * @return The height of the bubble.
     */
    double getHeight();

    /**
     * 
     * @return The {@link BubbleType} of the {@link Bubble}.
     */
    BubbleType getType();

    /**
     * 
     * @return The {@link BubbleColor} of the {@link Bubble}.
     */
    BubbleColor getColor();

    /**
     * 
     * @param type The {@link ComponentType}
     * @return The {@link Component} if is present or Optional.empty if not.
     */
    Optional<Component> getComponent(ComponentType type);

    /**
     * @return The position of the {@link Bubble} in the grid.
     */
    Point2D getPosition();

    /**
     * @return The direction of the {@link ShootingBubble}.
     */
    Optional<Point2D> getDirection();
}
