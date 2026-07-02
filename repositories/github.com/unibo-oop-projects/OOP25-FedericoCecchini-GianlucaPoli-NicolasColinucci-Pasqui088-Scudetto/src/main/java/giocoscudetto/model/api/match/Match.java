package giocoscudetto.model.api.match;

import java.util.Optional;

/**
 * Interface that represents a match between two clubs, it keeps track of the score and of the clubs that are playing.
 */
public interface Match {

    /**
     * Enum that rappresent the game mode of the match.
     */
    enum GameMode {
        CORNER, FREE_KICK,
        RESULT, PENALTY,
        NONE
    }

    /**
     * Method that returns the club that has the turn to play.
     * 
     * @return the club that has the turn to play
     */
    Club turn();

    /**
     * Method that increase the amount of goals scored by the home team.
     */
    void goalHome();

    /**
     * Method that increase the amount of goals scored by the away team.
     */
    void goalAway();

    /**
     * Method that decrease the amount of goals scored by the home team.
     */
    void removeGoalHome();

    /**
     * Method that decrease the amount of goals scored by the away team.
     */
    void removeGoalAway();

    /**
     * sets the number of goals of the home Club to goal.
     * 
     * @param goal the number of goals to set for the home club
     */
    void setGoalHome(int goal);

    /**
     * sets the number of goals of the away Club to goal.
     * 
     * @param goal the number of goals to set for the away club
     */
    void setGoalAway(int goal);

    /**
     * @return the home club
     */
    Club getClubHome();

    /**
     * @return the away club
     */
    Club getClubAway();

    /**
     * @return the score of the match
     */
    Scoreboard getScore();

    /**
     * @return the club that has won the match, if there is a draw it returns Optional.empty().
     */
    Optional<Club> getWinnerClub();

    /**
     * @return the club that has lost the match, if there is a draw it returns Optional.empty().
     */
    Optional<Club> getLoserClub();

    /**
     * @return the club that is currently playing.
     */
    Club getCurrentPlayer();

    /**
     * Method that simulates the throw of a dice, the value of the dice is used for moving the pawn on the board.
     * 
     * @return the value of the dice throw.
     */
    int rollDice();

    /**
     * This method set the penalty mode.
     * 
     * @param mode the gamemode of the match.
     */
    void setGameMode(GameMode mode);

    /**
     * This method is for controll the state of the penalty mode.
     * 
     * @return a boolean rappresenting if is active the penalty mode.
     */
    String getGameMode();

    /**
     * Method that set the skip turn for the club that is passed as parameter, this method is used for the corner event.
     * 
     * @param club the club that has to skip the turn
     */
    void setSkipTurn(Club club);

    /**
     * Method responsible for the throws of dice during the match events.
     * 
     * @return the value of the dice
     */
    int diceEvent();

    /**
     * Method that set the position of the keeper for the free kick event.
     * 
     * @param i the position of the keeper
     */
    void setKeeperPosition(int i);

    /**
     * Method that manage the event mode, it check the game mode and the value of the event 
     * dices and update the score accordingly.
     */
    void eventMode();

    /**
     * This method returns the last position of the ball that was shot.
     * 
     * @return the last position of the ball that was shot.
     */
    int getLastShootPosition();

    /**
     * This method returns the name of the player that is not the current player.
     * 
     * @return the name of the player that is not the current player.
     */
    String getNotCurrentPlayer();

}
