package view;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import utility.Direction;
import utility.Driver;
import utility.Pair;
import utility.Position;
import utility.TyreType;
import view.game.InfoDriver;

/**
 * Interface for the view of the game.
 */
public interface View extends ViewSettings {

    /**
     * Start the race weekend.
     */
    void start();

    /**
     * Wait for the race's start.
     */
    void startRace();

    /**
     * Method where the user has to throw the dice.
     * The user is the same of the one whose informations are on screen.
     * @param number the number that will be shown after
     * @param canDir a pair that indicates which direction the car can take
     * @param canBox if the actual player can box or not
     * @return the direction the player decide to take
     */
    Direction throwDice(int number, Pair<Boolean, Boolean> canDir, boolean canBox);

    /**
     * Update the position of the current driver.
     * @param drv the driver that has just moved on the field
     * @param pos the new position the car has reached
     * @param block if the current player has blocked or not
     */
    void update(Driver drv, Position pos, boolean block);

    /**
     * 
     * @param info Information to display
     */
    void updatePlayer(InfoDriver info);

    /**
     * Method to inform the users that an accident between two or more driver has occurred.
     * @param crashedPlayers map of the players who had the accident
     */
    void crash(Map<Driver, Optional<String>> crashedPlayers);

    /**
     * Method to inform that a player has retired after an incident.
     * @param retiredPlayers map of the players who retired after an accident
     */
    void retire(Map<Driver, Optional<String>> retiredPlayers);

    /**
     * Method to inform that the qualifying session is over.
     * @param rank the rank of all the drivers with their correspondent lap time
     * @param isEnded true if qualifying is ended
     */
    void rankQualifying(List<Pair<Driver, Integer>> rank, boolean isEnded);

    /**
     * Method to inform that the race session is over.
     * @param rank the rank of all the drivers
     * @param isEnded true if race is ended
     */
    void rankRace(List<Driver> rank, boolean isEnded);

    /**
     * A user decided to stop when it wasn't his turn, now he has to say what tyre
     * he wants to mount on the car.
     * @param name the user name.
     * @return the tyre chosen
     */
    TyreType box(String name);

    /**
     * Method to inform that a player has disqualified.
     * @param disqualifiedPlayers Map of disqualified drivers
     */
    void disqualify(Map<Driver, Optional<String>> disqualifiedPlayers);
}
