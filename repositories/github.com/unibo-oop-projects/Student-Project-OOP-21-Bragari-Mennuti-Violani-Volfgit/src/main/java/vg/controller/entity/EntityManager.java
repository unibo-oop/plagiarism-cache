package vg.controller.entity;

import vg.controller.entity.boss.BossController;
import vg.controller.entity.mystery_box.MysteryBoxController;
import vg.controller.gameBoard.GameBoardController;
import vg.model.Stage;
import vg.sound.manager.SoundManager;
import vg.utils.V2D;

import java.util.List;
/**
 * Interface for the entity manager.
 */
public interface EntityManager {
    /*
     * This method is responsible for creating entity.
     */
    void initializeRound(GameBoardController gameController);
    /*
     * This method is responsible for creating entity for round.
     */
    void initializeNewRound(GameBoardController gameController);
    /*
     * This method is used to get the list of MysteryBoxController.
     * @return the list of MysteryBoxController.
     */
    List<MysteryBoxController> getMysteryBoxList();
    /*
     * Get Boss Controller.
     * @return BossController.
     */
    BossController getBoss();
    /*
     * Set Sound Manager.
     * @param soundManager soundManager.
     */
    void setSoundManager(SoundManager soundManager);
    /**
     * This method is used to update timing of the entity.
     * @param elapsedTime defines the time elapsed.
     * @param stage defines the stage.
     */
    void countingTimeMysteryBox(long elapsedTime, Stage<V2D> stage);
    /**
     * This check if mystery Box on border and active.
     * @param stage stage.
     * @param gameController gameBoardController.
     */
    void checkMysteryBoxOnBorder(Stage<V2D> stage, GameBoardController gameController);
    /**
     * Update movement of the boss.
     * @param elapsedTime defines the time elapsed.
     */
    void moveEntityBoss(long elapsedTime);
    /**
     * Update the next image of the animation.
     */
    void updateAnimation();
}