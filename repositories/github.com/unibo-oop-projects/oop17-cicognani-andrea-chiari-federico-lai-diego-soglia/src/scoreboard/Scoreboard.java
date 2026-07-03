package scoreboard;

/**
 *
 * @author Nicola
 *
 * A simple class to save a string with player name and an integer for the score
 *
 */
public class Scoreboard {


	 	private final String name;
	    private final int score;

	    /**
	     *
	     * @param name taken from player profile
	     * @param highScore
	     */
	    public Scoreboard(String name, int highScore) {
	        this.name = name;
	        this.score = highScore;
	    }

	    /**
	     *
	     * @return player name
	     */
	    public String getName() {
	        return name;
	    }

	    /**
	     *
	     * @return player's highscore
	     */
	    public int getHighScore() {
	        return score;
	    }

	    /**
	     * toString method
	     */
	    public String toString(){
	        String tot = this.name + ". Score:" + this.score ;
	        return tot;
	    }

}




