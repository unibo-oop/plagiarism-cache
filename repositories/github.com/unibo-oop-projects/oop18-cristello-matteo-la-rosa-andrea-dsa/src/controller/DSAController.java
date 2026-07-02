package controller;

import java.util.List;
import java.util.Map;

import model.board.GameBoard;
import model.board.Tile;
import model.players.Player;
import model.utils.Direction;
import model.utils.PossibleActionAfterMove;
import view.GameView;

/**
 * This is the controller of the boardgame.
 */

public interface DSAController { //NOPMD
    /**
     * This method is called to give at any player his name the players of the games.
     * 
     * @param playerList
     *                       the param list need to create players in game
     */
    void setPlayers(Map<String, String> playerList);

    /**
     * This method return the list of possible direction for player before move.
     * 
     * @return List<String>
     */
    List<String> getMovementPossibleAction();

    /**
     * This method return the list of possible actions after move.
     * 
     * @return List<String>
     */
    List<String> getPoassibleActionAfterMove();

    /**
     * This method allow to move the player in turn.
     * 
     * @param direction
     *                      the direction of player in game.
     */
    void movePlayer(Direction direction);

    /**
     * This method allow the player to manage reasure.
     * 
     * @param action
     *                   the possible action of player after movement.
     */
    void manageAction(PossibleActionAfterMove action);

    /**
     * This method is called to know who is the player in turn.
     * 
     * @return player entity that is in turn
     */

    Player getPlayerInTurn();

    /**
     * this method return the value of rolled dices for movement.
     * 
     * @return int value of dices.
     */

    int getDicesValue();

    /**
     * This method ad a {@link InPlay} view for Controller.
     * 
     * @param gameFrame
     *                      .
     */
    void setView(GameView gameFrame);

    /**
     * This method allow to view players Statistic.
     * 
     * @return a list of string that contain the players statistic
     */

    List<String> getPlayerStatistic();

    /**
     * This method allow to set the end of the actual player turn.
     */
    void endPlayerTurn();

    /**
     * This method allow to set the end of actual game turn.
     */

    void endGameTurn();

    /**
     * @return
     */
    void play();

    /**
     * This method test if the game turn need to end.
     * 
     * @return true or false depending if the turn has to end
     */
    boolean testEndTurn();

    /**
     * This method return the remaining Oxygen of the game.
     * 
     * @return Oxygen remaining
     */
    Integer getRemainingOxigen();

    /**
     * This method return the tileLine managed by model.
     * 
     * @return the TileLine
     */
    GameBoard getTileline();

    /**
     * This method return the player in turn treasure tile group.
     * 
     * @return the list of treasure of player in turn
     */
    List<Tile> getPlayerInTurnTreasureTile();

    /**
     * This method set the game for the next player in turn.
     * 
     * @return
     */
    void startNextPlayerTurn();

    /**
     * This method set a new game turn.
     * 
     * @return
     */
    void startGameTurn();

    /**
     * @return the game turn index
     */
    Integer getGameTurnIndex();

    /**
     * @param action
     *                    the action to do
     * @param nPickUp
     *                    the eventually tile to release
     */
    void manageAction(PossibleActionAfterMove action, Integer nPickUp);

    /**
     * @param message
     *                    the end game message to show the winner
     */
    void gameMatchMessage(String message);

    /**
     * This method manage the end of the game (winners, scores, etc..).
     */
    void manageEndOfGame();

    /**
     * This method manage the end of the game (winners, scores, etc..).
     */
    void setPlayerInTurn();

    /**
     * This method test if is reached end of the game.
     * 
     * @return true if game has to be ended, false if the game need to continue.
     */

    boolean testEndGame();

    /**
     * This method manage the end of the game (winners, scores, etc..).
     * 
     * @return true if is the last turn, false if isn't last turn.
     */

    boolean isLastTurn();

    /**
     * This method menage a sound lanch during the game.
     * 
     * @param soundAddr
     *                      The address of the sound (file type .wav)
     */
    void soundLaunch(String soundAddr);

}
