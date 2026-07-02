package view;

import java.util.List;
import java.util.Map;

import javafx.util.Pair;
import uicontrollers.BattleController;
import uicontrollers.HeroPickerController;

/**
 * Interface for a generic View.
 */
public interface View {

    /**
     * Start the View of the application.
     */
    void startView();

    /**
     * Show the prepicker screen.
     * 
     * @param arenaNames
     *            the arena names
     */
    void showPrePickerScreen(List<String> arenaNames);

    /**
     * Show the Hero Picker screen.
     * 
     * @param heroPool
     *            list of pickable heroes
     * @param initialPlayerTurn
     *            the initial player turn
     */
    void showPickerScreen(List<String> heroPool, String initialPlayerTurn);

    /**
     * Show the game screen.
     * 
     * @param p
     *            pair contaning the maximum number of cols and rows of the arena
     */
    void showGameScreen(Pair<Integer, Integer> p);

    /**
     * Show the Map editor screen.
     */
    void showEditorScreen();

    /**
     * Add the hero to team one.
     * 
     * @param heroImageName
     *            the image name
     * @param identifier
     *            the identifier
     * @param hpc
     *            the controller of the scene
     */
    void addTeamOne(String heroImageName, int identifier, HeroPickerController hpc);

    /**
     * Add the hero to team two.
     * 
     * @param heroImageName
     *            the image name
     * @param identifier
     *            the identifier
     * @param hpc
     *            the controller of the scene
     */
    void addTeamTwo(String heroImageName, int identifier, HeroPickerController hpc);

    /**
     * Removes the hero from team one.
     * 
     * @param hpc
     *            the controller
     * @param identifier
     *            the identifier
     * 
     */
    void removeTeamOne(HeroPickerController hpc, int identifier);

    /**
     * Removes the hero from team one.
     * 
     * @param hpc
     *            the controller
     * @param identifier
     *            the identifier
     * 
     */
    void removeTeamTwo(HeroPickerController hpc, int identifier);

    /**
     * Updates the hero pool picker.
     * 
     * @param isPickable
     *            wether the hero is pickable or not
     * 
     * @param index
     *            the index
     * @param hpc
     *            the controller
     */
    void updatePickerHeroPool(boolean isPickable, int index, HeroPickerController hpc);

    /**
     * Udates the turn of the player who has to pick an hero.
     * 
     * @param playerTurn
     *            the name of the player who has to pick.
     * @param hpc
     *            the controller
     */
    void updatePickerTurn(String playerTurn, HeroPickerController hpc);

    /**
     * Draw the terrain blocks of the arena.
     * 
     * @param arenaMap
     *            the Map wich each element has a pair of x and y coordinates and
     *            the name of the image of the terrain
     * @param bc
     *            the controller
     */
    void drawArenaTerrain(Map<Pair<Integer, Integer>, String> arenaMap, BattleController bc);

    /**
     * Draw the alive heroes in the arena.
     * 
     * @param aliveEntities
     *            the Map wich each element has a pair of x and y coordinates and
     *            the name of the image of the hero
     * @param bc
     *            the controller
     */
    void drawAliveEntities(Map<Pair<Integer, Integer>, String> aliveEntities, BattleController bc);

    /**
     * Shows in the arena grid the cells candidates where the selected hero can
     * attack.
     * 
     * @param positions
     *            list of position of the cells where the hero can attack
     * @param bc
     *            the controller
     */
    void showSelectionAttackCandidates(List<Pair<Integer, Integer>> positions, BattleController bc);

    /**
     * Shows in the arena grid the cells candidates where the selected hero can
     * move.
     * 
     * @param positions
     *            list of position of the cells where the hero can move
     * @param bc
     *            the controller
     */
    void showSelectionMovementCandidates(List<Pair<Integer, Integer>> positions, BattleController bc);

    /**
     * Shows in the arena grid the cells candidates where the hero hovered by the
     * arena cursor can attack.
     *
     * @param positions
     *            list of position of the cells where the hovered hero can attack
     * @param bc
     *            the controller
     */
    void showCursorSelectionAttackCandidates(List<Pair<Integer, Integer>> positions, BattleController bc);

    /**
     * Shows in the arena grid the cells candidates where the hero hovered by the
     * arena cursor can move.
     * 
     * @param positions
     *            list of position of the cells where the hovered hero can move
     * @param bc
     *            the controller
     */
    void showCursorSelectionMovementCandidates(List<Pair<Integer, Integer>> positions, BattleController bc);

    /**
     * Shows the info of the current hero in the hero picker screen.
     * 
     * @param name
     *            name of the hero
     * @param maxHp
     *            number of maximuim health points of the hero
     * @param attack
     *            attack points of the hero
     * @param defence
     *            defence points of the hero
     * @param movementRange
     *            movement range of the hero
     * @param attackRange
     *            attack range of the hero
     * @param hpc
     *            the controller
     */
    void showPickerSelectionInfo(String name, String maxHp, String attack, String defence, String movementRange,
            String attackRange, HeroPickerController hpc);

    /**
     * Shows the info of the current selected hero in the battle screen.
     * 
     * @param name
     *            name of the hero
     * @param maxHP
     *            number of maximuim health points of the hero
     * @param currentHP
     *            number of current health points of the hero
     * @param attack
     *            attack points of the hero
     * @param defence
     *            defence points of the hero
     * @param movementType
     *            movement type of the hero
     * @param attackType
     *            attack type of the hero
     * @param attackStatus
     *            status of the attack of the hero
     * @param movementStatus
     *            status of the movement of the hero
     * @param bc
     *            the controller
     */
    void showGameSelectionInfo(String name, String maxHP, String currentHP, String attack, String defence,
            String movementType, String attackType, String attackStatus, String movementStatus, BattleController bc);

    /**
     * Shows the info of the current hovered hero in the battle screen.
     * 
     * @param name
     *            name of the hero
     * @param maxHP
     *            number of maximuim health points of the hero
     * @param currentHP
     *            number of current health points of the hero
     * @param attack
     *            attack points of the hero
     * @param defence
     *            defence points of the hero
     * @param movementType
     *            movement type of the hero
     * @param attackType
     *            attack type of the hero
     * @param attackStatus
     *            status of the attack of the hero
     * @param movementStatus
     *            status of the movement of the hero
     * @param bc
     *            the controller
     */
    void showGameCursorInfo(String name, String maxHP, String currentHP, String attack, String defence,
            String movementType, String attackType, String attackStatus, String movementStatus, BattleController bc);

    /**
     * Update the position of the moved hero.
     * 
     * @param bc
     *            the controller
     * @param newPos
     *            new position for the entity
     * @param oldPos
     *            old position of the entity
     */
    void updateEntityPosition(BattleController bc, Pair<Integer, Integer> newPos, Pair<Integer, Integer> oldPos);

    /**
     * Update the turn in the battle scene.
     * 
     * @param bc
     *            the controller
     * @param turn
     *            String with the new player turn
     */
    void updateGameTurn(BattleController bc, String turn);

    /**
     * Shows the victory message.
     * 
     * @param bc
     *            the controller
     * @param victoryMessage
     *            String containing the winning player
     */
    void showVictoryMessage(BattleController bc, String victoryMessage);

    /**
     * Shows the character editor screen.
     */
    void showCharacterEditorScreen();
}
