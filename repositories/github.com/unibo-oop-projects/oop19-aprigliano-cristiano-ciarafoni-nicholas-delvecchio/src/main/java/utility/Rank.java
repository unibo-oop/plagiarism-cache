package utility;

/**
 * This class is used to create the Rank object, composed by rank, name and score.
 */
public class Rank {

    private int rank;
    private String name;
    private String score;

    /**
     * Method to get the Rank.
     * @return the Rank
     */
    public int getRank() {
        return this.rank;
    }

    /**
     * Method to get the Name.
     * @return the Name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method to get the Score.
     * @return the Score
     */
    public String getScore() {
        return this.score;
    }

    /**
     * Method to set the Rank.
     * @param rank
     */
    public void setRank(final int rank) {
        this.rank = rank;
    }

    /**
     * Method to set the name.
     * @param name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Method to set the Score.
     * @param score
     */
    public void setScore(final String score) {
        this.score = score;
    }
}
