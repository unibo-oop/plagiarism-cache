package controller;

import java.util.List;

import javafx.stage.Stage;
import model.navy.Navy;
import model.player.Player;
/**
 * Interface for the principal controller of the application.
 */
public interface MasterController {
    /**
     * Method to add a new player.
     * @param name the name of the player to add
     */
    void addNewPlayer(String name);
    /**
     * Method to confirm the data of the player chose to play.
     * @param player the player to confirm data
     * @param password the password for the player {@link #p}
     */
    void confirmPlayerData(Player player, String password);

    /**
     * Setter for the specification of the common builder.
     * @param specification is the sizes of the ship.
     * @param gridSize is the size of the grid.
     */
    void setNavyBuilderSpecification(List<Integer> specification, int gridSize);

    /**
     * Sets the navy for the current {@link Player}.
     * @param navy is the {@link Navy} to set.
     */
    void confirmNavy(Navy navy);

    /**
    * Triggers the global statistic scene.
    */
    void showGobalStatistics();
    /**
     * Triggers the pop-up to view information from development team members.
     */
    void credits();

    /**
     * Notifies the {@link MasterControllerImpl} that a shot has been performed.
     */
    void shootUndergo();

    /**
     * Manages the end of the game. 
     */
    void endGame();

    /**
     * Getter for the primary stage.
     * @return the primary {@link Stage} of the application\
     */
    Stage getPrimaryStage();

    /**
     * Sets the prompted path into the model.
     * @param playerFile path for the player
     * @param statisticsFile path for the statistics
     */
    void setFilePath(String playerFile, String statisticsFile);

}
