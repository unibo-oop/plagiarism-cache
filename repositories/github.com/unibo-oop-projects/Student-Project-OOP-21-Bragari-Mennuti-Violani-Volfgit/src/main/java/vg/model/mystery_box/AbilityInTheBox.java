package vg.model.mystery_box;

import javafx.geometry.Dimension2D;
import vg.model.Stage;
import vg.model.entity.Entity;
import vg.utils.V2D;

/**
 * This interface represents the ability of the game.
 */
public interface AbilityInTheBox extends Entity {
    /**
     *   This method is used to identify the ability.
     *   @return the id of the ability.
     */
    EAbility getIdAbility();
    /**
     * This method is used to get the type of the ability. (Instant and durable)
     * @return the dimension of the box.
     */
    ETypeAbility getTypeAbility();
    /**
     * This method is used to get the dimension of the box.
     * @return the dimension of the box.
     */
    Dimension2D getDimension();
    /**
     * This method is used to get the path of the image of the ability, when is taken.
     * @return the path of the image of the ability.
     */
    String getPathReveled();
    /**
     * This method is used to get the radius.
     * @return the radius.
     */
    int getRadius();
    /**
     * This method show if the box is blinking.
     * @param isBlinking defines if the ability is blinking.
     */
    void setBlinking(boolean isBlinking);
    /**
     * This method is used to set the position.
     * @param position defines the position of the box.
     */
    void setPosition(V2D position);
    /**
     * This method is used to update the blinking.
     * @param elapsedTime defines the time elapsed.
     */
    void updateBlinking(long elapsedTime);
    /**
     * This method is used to active the blink when the box is picked up.
     */
    void setActiveBlinkPickUp();
    /**
     * This method is used to update the blink when the box is picked up.
     * The blink is active for a short time.
     * @param elapsedTime defines the time elapsed.
     */
    void updateBlinkingPickUp(long elapsedTime);
    /**
     * This method is used if the box is shown.
     * @return true if the box is shown, false otherwise.
     */
    boolean isShow();
    /**
     * Active the ability.
     * @param stage defines the stage.
     */
    void activate(Stage<V2D> stage);
    /**
     * This method is used to verify if the ability has been actived.
     * @return true if the activation is true, false otherwise.
     */
    boolean isActivated();
    /**
     * Show the box.
     */
    void show();
    /**
     * Hide the box.
     */
    void hide();
}

