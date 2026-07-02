package model.ship;

import java.io.Serializable;

/**
 *  Models a player ship: a SpaceShip associated with a certain name
 *  and the progress made within the game, represented by PlayerScore.
 */
public interface PlayerShip extends SpaceShip {

    /**
     * Returns the PlayerScore of this player, alias his name and his progress
     * made within the game.
     * @return the PlayerScore of this player.
     */
    PlayerScore getPlayerScore();

    /**
     * The model of the player score: bounds each score to a name 
     * of some significance.
     */
    class PlayerScore implements Serializable {

        /** Defines the name of a default score in the leaderboard. */
        public static final String DEFAULT_NAME = "OverGit_Bot";

        private static final long serialVersionUID = 4160579278796847063L;

        private String playerName;
        private int totalScore;
        private int levelBeaten;

        public PlayerScore() {
            this(DEFAULT_NAME, 0, 0);
        }
        public PlayerScore(final String playerName) {
            this(playerName, 0, 0);
        }
        public PlayerScore(final String playerName, final int totalScore, final int levelBeaten) {
            this.playerName = playerName;
            this.totalScore = totalScore;
            this.levelBeaten = levelBeaten;
        }

        /**
         * Adds the specified score points to the total score points of this score.
         * @param scorePoints : the specified score points.
         */
        public void addScorePoints(final int scorePoints) {
            this.totalScore += scorePoints;
        }

        /**
         * Increments by one the number of level beaten by the owner of this score.
         */
        public void incrementLevelBeaten() {
            this.levelBeaten++;
        }

        /*SETTERS & GETTERS------------------------------------------------*/
        /**
         * Returns the total score points of this score.
         * @return the total score points of this score.
         */
        public int getTotalScore() {
            return totalScore;
        }
        /**
         * Returns the number of level beaten by the owner of this score.
         * @return the number of level beaten by the owner of this score.
         */
        public int getLevelBeaten() {
            return levelBeaten;
        }
        /**
         * Returns the name of the owner of this score.
         * @return the name of the owner of this score.
         */
        public String getPlayerName() {
            return playerName;
        }
        /*------------------------------------------------------------------*/
    }

}
