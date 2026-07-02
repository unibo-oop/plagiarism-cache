package tmw.controller.level;

import javafx.scene.image.Image;
import tmw.controller.entities.MilkController;
import tmw.controller.world.WorldDispenser;

/**
 * Interface that controls action related to the fxml file
 * {@link SelectLevelView}.
 *
 */
public interface SelectLevelController {

    /**
     * Method that set the level scene in the stage.
     */
    void start();

    /**
     * Set the tutorial view and start the game with the tutorial level.
     */
    void toTutorial();

    /**
     * Set the level one view and start the game with the first level.
     */
    void toLevelOne();

    /**
     * 
     * This method is the core of game switching room feature. Allows to set a new
     * room to play.
     * <p>
     * Its work is to change worldController {@link WorldController} references. In
     * fact a playerController is passed as a parameter such as a worldDispenser.
     * This happens because switch to another room means use the same player and
     * load the right enemies, items etc. present in a specific room.
     *
     * 
     *
     * @param playerController {@link MilkController} playerController to use in new
     *                         room
     * @param dispenser        {@link WorldDispenser} dispenser form which load all
     *                         entities
     * @param roomBackground   {@link Image} new room background
     */
    void changeRoom(MilkController playerController, WorldDispenser dispenser, Image roomBackground);

    /**
     * Allows to go to gameOver.
     */
    void goToGamerOver();

    /**
     * This method sets up worldController {@link WorldController} in order to make
     * it ready to manage any room. For example some listeners are added
     * {@link Observer} to make worldController reactive.
     */
    void initializeWorldProperties();

    /**
     * Set the boss level view and start the game with the boss and last level.
     */
    void toBossLevel();

    /**
     * Return to the main menu.
     */
    void goBack();

    /**
     * Allows to go to end level view.
     */
    void goToEndLevel();

}
