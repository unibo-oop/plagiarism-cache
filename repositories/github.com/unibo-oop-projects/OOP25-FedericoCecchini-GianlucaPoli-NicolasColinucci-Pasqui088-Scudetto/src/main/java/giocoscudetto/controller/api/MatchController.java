package giocoscudetto.controller.api;

import giocoscudetto.view.api.GameObserver;

/**
 * This interface represents the controller of the match.
 */
public interface MatchController {

    /**
     * this is a method to check the box and execute the event of the box.
     */
    void checkBox();

    /**
     * this method is for get the image of a box.
     * 
     * @param i the index of the box.
     * @return the image of the box.
     */
    String getBoxImage(int i);

    /**
     *  this method is for get the score of the game.
     * 
     * @return a string that contains the score of the game.
     */
    String getScore();
 
    /**
     * this method is for get the position of the home team pawn.
     * 
     * @return the position of the pawn of the home team.
     */
    int getHomePosition();

    /**
     * this method set the goalkeeper postion in the goalnetimpl.
     * 
     * @param i the position occupied by the keeper.
     */
    void setKeeperPosition(int i);

    /**
     * this method is for get the position of the guest team pawn.
     * 
     * @return the position of the pawn of the guest team.
     */
    int getGuestPosition();

    /**
     * this method is for kick the penalty and check if it's a goal or not.
     * 
     * @return true if it's a goal, false otherwise.
     */
    boolean kickPenalty();

    /**
     * this method is for get the current player,who has to move.
     * 
     * @return a string that contains the name of the current player.
     */
    String getCurrentPlayer();

    /**
     * this method is for move the pawn of the current player.
     * 
     * @return the number of the box where the pawn has to move.
     */
    int move();

    /**
     * This method is for get the description of the current box.
     *
     * @return a string that contains the description of the current box.
     */
    String getDescription();

    /**
     * This method sets the match with the next fixture match.
     */
    void setMatch();

    /**
     * This method is for get the name of the current game mode.
     * 
     * @return the name of the current game mode.
     */
    String getGameMode();

    /**
     * This method is called when the game mode is finished and sets the game mode to the default one.
     */
    void gameModeFinished();

    /**
     * This method is for add an observer to the match controller.
     * 
     * @param ob the observer to add.
     */
    void addObserver(GameObserver ob);

    /**
     * This method is for remove an observer from the match controller.
     * 
     * @param ob the observer to remove.
     */
    void removeObserver(GameObserver ob);

    /**
     * This method is for get the RGB of the home team pawn.
     * 
     * @return the RGB of the home team pawn.
     */
    int getHomePawnRGB();

    /**
     * This method is for get the RGB of the guest team pawn.
     * 
     * @return the RGB of the guest team pawn.
     */
    int getGuestPawnRGB();

    /**
     * @return true if the current box is the last one, false otherwise.
     */
    boolean isLastBox();

    /**
     * This method is called when the last box is reached and sets the score of the current match in the fixture.
     */
    void lastBox();

    /**
     * @return true if the current match is the last one of the fixture, false otherwise.
     */
    boolean isLastMatch();

    /**
     * This method adds the points to the clubs at the end of the match.
     */
    void addPoints();

    /**
     * @return the match dice event.
     */
    int diceEvent();

    /**
     * @return the name of the home team.
     */
    String getHomeName();

    /**
     * @return the name of the guest team.
     */
    String getGuestName();

    /**
     * Set the help flag to the value of selected.
     * 
     * @param selected the value to set the help flag to.
     */
    void setHelpFlag(boolean selected);

    /**
     * @return true if the help flag is set, false otherwise.
     */
    boolean isHelpFlag();

    /**
     * Notify all observers of a state change.
     */
    void notifyViews();

    /**
     * @return the name of the current box.
     */
    String getBoxName();

    /**
     * @return the description of the current box.
     */
    String getBoxDescript();

    /**
     * @return the last position of the ball that was shot.
     */
    int getLastShootPosition();

    /**
     * @return the name of the player that is not the current player.
     */
    String getNotCurrentPlayer();

}
