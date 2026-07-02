package vg.controller.entity.mystery_box.manager;

import vg.controller.entity.mystery_box.MysteryBoxController;
import vg.controller.gameBoard.GameBoardController;
import vg.model.Stage;
import vg.sound.manager.SoundManager;
import vg.utils.V2D;

import java.util.List;
/*
 * This interface is responsible for managing the MysteryBoxController objects.
 */
public interface MysteryBoxManager {
    /*
     * This method is responsible for creating a new MysteryBoxController for round.
     */
    void initializeRound(GameBoardController gameBoard);
    /*
     * This method is used to get the list of MysteryBoxController.
     * @return the list of MysteryBoxController.
     */
    List<MysteryBoxController> getMysteryBoxList();
    /*
     * This method is used to get the list of MysteryBoxController that are active and durable.
     * @return the list of MysteryBoxController that are active and durable.
     */
    List<MysteryBoxController> getMysteryBoxActiveAndDurableList();
    /**
     * This method is used to update the blinking all mystery box.
     * @param elapsedTime defines the time elapsed.
     */
    void updateTimeBlinking(long elapsedTime);
    /**
     * This method update the visibility of the mystery box after take.
     * @param elapsedTime defines the time elapsed.
     */
    void showPickUpMysteryBox(long elapsedTime);
    /**
     * This method update the ability mystery box.
     * @param elapsedTime defines the time elapsed.
     * @param stage defines the stage.
     */
    void updateTimeIfAbilityActive(long elapsedTime, Stage<V2D> stage);
    /**
     * This check if mystery Box on border and active.
     * @param stage stage.
     * @param gameController gameBoardController.
     * @param soundManager soundManager.
     */
    void checkMysteryBoxOnBorder(Stage<V2D> stage, GameBoardController gameController, SoundManager soundManager);
    /**
     * Update the next image of the animation.
     */
    void updateAnimation();
}
