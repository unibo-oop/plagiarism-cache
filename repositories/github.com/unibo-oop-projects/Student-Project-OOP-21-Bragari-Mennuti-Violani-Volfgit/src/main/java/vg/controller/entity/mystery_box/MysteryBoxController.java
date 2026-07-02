package vg.controller.entity.mystery_box;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import vg.controller.gameBoard.GameBoardController;
import vg.model.Stage;
import vg.model.mystery_box.AbilityDurable;
import vg.model.mystery_box.ETypeAbility;
import vg.model.mystery_box.data_round.DataRound;
import vg.sound.manager.SoundManager;
import vg.utils.V2D;

import java.util.List;

/*
 * This interface the controller of the mystery box.
 */
public interface MysteryBoxController {
    /**
     * Get the durability object.
     * @return Durable object.
     */
    AbilityDurable getDurability();
    /**
     * Return the position of the entity.
     * @return the position of the entity.
     */
    V2D getPosition();
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
     * Set the entity in the parent node.
     * @param gameAreaNode the list of node of the game area.
     */
    void setInParentNode(ObservableList<Node> gameAreaNode);
    /**
     * Set list of path of the animation.
     * @param animationPathList the list of path of the animation.
     */
    void setAnimation(List<String> animationPathList);
    /**
     * Set data of round.
     * @param dataRound data of round.
     */
    void setDataRound(DataRound dataRound);
    /**
     * This method is used to update the blinking.
     * @param elapsedTime defines the time elapsed.
     */
    void updateBlinking(long elapsedTime);
    /**
     * Check if type ability.
     * @param type type ability.
     * @return true if type ability is equal to type.
     */
    boolean isType(ETypeAbility type);
    /**
     * This method is used if the box is shown.
     * @return true if the box is shown, false otherwise.
     */
    boolean isShow();
    /**
     * This check if mystery Box on border and active.
     * @param stage stage.
     * @param gameController gameBoardController.
     * @param soundManager soundManager.
     */
    void checkOnBorder(Stage<V2D> stage, GameBoardController gameController, SoundManager soundManager);
    /**
     * This method is used to verify if the ability has been actived.
     * @return true if the activation is true, false otherwise.
     */
    boolean isActivated();
    /**
     * This method is used to update the blink when the box is picked up.
     * The blink is active for a short time.
     * @param elapsedTime defines the time elapsed.
     */
    void showPickUpMysteryBox(long elapsedTime);
    /**
     * Hide the box.
     */
    void hide();
    /**
     * Update the next image of the animation.
     */
    void updateAnimation();
}
