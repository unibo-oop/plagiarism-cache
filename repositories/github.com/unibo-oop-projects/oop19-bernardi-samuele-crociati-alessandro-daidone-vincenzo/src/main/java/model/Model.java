package model;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javafx.util.Pair;
import model.entities.Entity;
import model.entities.Hero;
import model.entities.Obstacle;
import model.utilities.ActionPerformed;
import model.utilities.GameStatus;
import model.utilities.PlayerTurn;
import model.utilities.TerrainType;
import model.utilities.exceptions.MismatchedPlayerTurnException;
import model.utilities.exceptions.TeamAlreadyFullException;

/**
 * Interface for a generic model.<br>
 * Defines the main methods of the class model, that's effectively the game
 * itself with all active entities.
 */
public interface Model {
    /**
     * Starts the game in model.
     */
    void startGame();

    /**
     * Returns heroes in team one.
     *
     * @return List of heroes in team one
     */
    List<Hero> getTeamOne();

    /**
     * Returns heroes in team two.
     *
     * @return List of heroes in team two
     */
    List<Hero> getTeamTwo();

    /**
     * Returns obstacles in the game.
     *
     * @return List of obstacles
     */
    List<Obstacle> getObstacles();

    /**
     * Whether team one is full or not.
     *
     * @return true if team one is full
     */
    boolean isTeamOneFull();

    /**
     * Whether team two is full or not.
     *
     * @return true if team two is full
     */
    boolean isTeamTwoFull();

    /**
     * Adds an hero to team one.
     *
     * @param hero
     *            the hero to add to the team
     * @throws TeamAlreadyFullException
     * @throws MismatchedPlayerTurnException
     */
    void addToTeamOne(Hero hero) throws TeamAlreadyFullException, MismatchedPlayerTurnException;

    /**
     * Adds an hero to team two.
     *
     * @param hero
     *            the hero to add to the team
     * @throws MismatchedPlayerTurnException
     * @throws TeamAlreadyFullException
     */
    void addToTeamTwo(Hero hero) throws TeamAlreadyFullException, MismatchedPlayerTurnException;

    /**
     * Adds an obstacle to the list of obstacles.
     * 
     * @param obstacle
     *            the obstacle to add
     */
    void addObstacle(Obstacle obstacle);

    /**
     * Removes the specified hero from team one if it's present.
     *
     * @param hero
     *            the hero to be removed
     * @throws MismatchedPlayerTurnException
     */
    void removeFromTeamOne(Hero hero) throws MismatchedPlayerTurnException;

    /**
     * Removes the specified hero from team two if it's present.
     *
     * @param hero
     *            the hero to be removed
     * @throws MismatchedPlayerTurnException
     */
    void removeFromTeamTwo(Hero hero) throws MismatchedPlayerTurnException;

    /**
     * Getter of the hero pool.
     * 
     * @return The list of heroes and their availability in the hero pool as a
     *         defensive list
     */
    List<Pair<Hero, Boolean>> getHeroPool();

    /**
     * Sets the provided hero pool as the hero pool the model will use.
     *
     * @param heroPool
     *            the hero pool to be used
     */
    void setHeroPool(Collection<Hero> heroPool);

    /**
     * Changes turn during the picker in model. (P1->P2 - P2->P1)
     */
    void changePickerTurn();

    /**
     * Getter for the player turn during the picker phase of the model.
     *
     * @return the player turn during picker
     */
    PlayerTurn getPickerPlayerTurn();

    /**
     * Changes the player turn during the game phase in the model. (P1->P2 - P2->P1)
     */
    void changeGameTurn();

    /**
     * Getter for the player turn during the game phase of the model.
     *
     * @return the player turn during game phase
     */
    PlayerTurn getGamePlayerTurn();

    /**
     * Getter for the arena map.
     *
     * @return the arena as a map
     */
    Map<Pair<Integer, Integer>, TerrainType> getArenaMap();

    /**
     * Setter for the team size (standard size will be used if not specified).
     *
     * @param teamSize
     *            the size to be used for teams
     */
    void setTeamSize(int teamSize);

    /**
     * Generates an arena map in the model created by the given parameter.
     *
     * @param arenaMap
     *            the arena map to be created
     * @param height
     *            the height of the map
     * @param width
     *            the width of the map
     */
    void generateArenaMap(List<TerrainType> arenaMap, int height, int width);

    /**
     * Moves the picker selection steps amount of times.
     *
     * @param index
     *            the new index
     */
    void setPickerSelection(int index);

    /**
     * Performs the selection command in the picker.
     *
     * @return the action that got performed
     * @throws MismatchedPlayerTurnException
     * @throws TeamAlreadyFullException
     */
    ActionPerformed selectPickerSelection() throws TeamAlreadyFullException, MismatchedPlayerTurnException;

    /**
     * Getter for the picker selection position.
     *
     * @return the position the picker selection is currently at
     */
    int getPickerSelectionPosition();

    /**
     * Gets the info of the selection in hero picker.
     *
     * @return the info of the selection
     */
    List<String> getPickerSelectionInfo();

    /**
     * Getter for the game selection position.
     *
     * @return the position of the current game selection
     */
    Optional<Pair<Integer, Integer>> getGameSelectionPosition();

    /**
     * Getter for the game cursor position.
     *
     * @return the current position of the game cursor
     */
    Pair<Integer, Integer> getGameCursorSelectionPosition();

    /**
     * Gives the selection command to the cursor at its current position.
     * 
     * @return the action performed
     */
    ActionPerformed selectGameCursorSelection();

    /**
     * Getter for the entity info of the current selection.
     *
     * @return the info of the hero currently selected
     */
    List<String> getGameSelectionInfo();

    /**
     * Getter for the coordinates the current game selection can attack.
     *
     * @return the list of coordinates
     */
    List<Pair<Integer, Integer>> getGameSelectionAttackCandidates();

    /**
     * Getter for the coordinates the current game selection can move to.
     *
     * @return the list of coordinates
     */
    List<Pair<Integer, Integer>> getGameSelectionMovementCandidates();

    /**
     * Getter for the entity info at the current cursor position.
     *
     * @return the info of the entity the cursor currently points to
     */
    List<String> getGameCursorSelectionInfo();

    /**
     * Getter for the coordinates the current game cursor selection can attack.
     *
     * @return the list of coordinates
     */
    List<Pair<Integer, Integer>> getGameCursorSelectionAttackCandidates();

    /**
     * Getter for the coordinates the current game cursor selection can move to.
     *
     * @return the list of coordinates
     */
    List<Pair<Integer, Integer>> getGameCursorSelectionMovementCandidates();

    /**
     * Return the full list of alive entities.
     *
     * @return List of entire collection of entities currently alive in the model
     */
    List<Entity> getAliveEntities();

    /**
     * Getter for the game status.
     *
     * @return GameStatus Current game status
     */
    GameStatus getGameStatus();

    /**
     * 
     * @param newPosition
     *            the new position
     */
    void setGameCursorPosition(Pair<Integer, Integer> newPosition);

    /**
     * 
     * @param newPosition
     *            the new position
     */
    void setGameSelectionPosition(Pair<Integer, Integer> newPosition);

    /**
     * 
     * @return the old game selection coordinates
     */
    Pair<Integer, Integer> getOldGameSelection();
}
