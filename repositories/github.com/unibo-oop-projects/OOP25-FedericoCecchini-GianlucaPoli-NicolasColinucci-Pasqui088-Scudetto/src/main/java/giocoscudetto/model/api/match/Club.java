package giocoscudetto.model.api.match;

import giocoscudetto.model.api.Pawn;

/**
 * Inteface to create and manage clubs information.
 */
public interface Club {

    /**
     * @return the name of the club.
     */
    String getName();

    /**
     * @return the point of the club.
     */
    int getPoints();

    /**
     * @return the difference between net scored and taken during the matches.
     */
    int getNetDiff();

    /**
     * @return the pawn selected by the club.
     */
    Pawn getPawn();

    /**
     * Increments the points of the club.
     * 
     * @param pointsReceived to be summed to the actual total.
     */
    void incrementPoints(int pointsReceived);

    /**
     * Subtracting goalScored and goalConceded and then setting the new netDiff value.
     * 
     * @param goalScored in the match that just ended.
     * @param goalConceded in the match that just ended.
     */
    void changeNetDiffs(int goalScored, int goalConceded);

}
