package view.game;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import controller.Controller;
import controller.file.ViewFile;
import utility.Direction;
import utility.Driver;
import utility.Pair;
import utility.Position;
import utility.TyreType;

/**
 * Interface of controller of fxml of game panel.
 *
 */
public interface ViewGame {

    /**
     * Set the game with setting inserted in menu.
     * @param ctr controller of application
     * @param fileContainer file with all data that game view need
     */
    void setControllerAndFile(Controller ctr, ViewFile fileContainer);

    /**
     * Wait that user start the race.
     */
    void startRace();

    /**
     * Game say to player the number of dice and wait the direction choose by user.
     * @param number number of dice
     * @param canDir which direction user can choose
     * @param canBox if user can go to the box
     * @return direction chose by user
     */
    Direction throwDice(int number, Pair<Boolean, Boolean> canDir, boolean canBox);

    /**
     * Update position of car in the map.
     * @param drv Driver
     * @param pos Destination
     * @param block If player had blocked for turn
     */
    void update(Driver drv, Position pos, boolean block);

    /**
     * Display the info of driver that it's its round.
     * @param info Info of player that it's its round
     */
    void updateRound(InfoDriver info);

    /**
     * Advise that some driver crashed.
     * @param crashedPlayers Map of driver that was crashed
     */
    void crash(Map<Driver, Optional<String>> crashedPlayers);

    /**
     * Advise that some driver retired.
     * @param retiredPlayers Map of driver that was retired
     */
    void retire(Map<Driver, Optional<String>> retiredPlayers);

    /**
     * Method to inform that a player has disqualified.
     * @param disqualifiedPlayers Map of disqualified drivers
     */
    void disqualify(Map<Driver, Optional<String>> disqualifiedPlayers);

    /**
     * Update the ranking on game panel.
     * @param rank Ranking
     */
    void rankUpdate(List<Driver> rank);

    /**
     * Show the final qualifying's ranking.
     * @param rank Ranking of the qualifying
     */
    void rankQualifying(List<Pair<Driver, Integer>> rank);

    /**
     * Show the final ranking and ask if player want do another race.
     * @param rank Ranking of race.
     * @return If the user want to do another race
     */
    boolean rankRace(List<Driver> rank);

    /**
     * Ask to player which tyre want.
     * @param name Name of player that must choose
     * @return Type of tyre chose by player
     */
    TyreType box(String name);

}