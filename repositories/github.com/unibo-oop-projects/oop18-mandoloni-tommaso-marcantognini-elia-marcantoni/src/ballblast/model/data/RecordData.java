package ballblast.model.data;

/**
 * The class that represents a Record for a {@link Leaderboard}, keeping
 * information about user.
 */
public class RecordData {

    private String name;
    private int score;

    /**
     * Empty constructor to serialize the object in a xml file.
     */
//    public RecordData() {
//    }

    /**
     * Getter of the User score.
     * 
     * @return the score in the last played game.
     */
    public int getScore() {
        return score;
    }

    /**
     * Getter of the User name.
     * 
     * @return the name of the User.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the {@link RecordData} user name.
     * 
     * @param name the user name who submit the record.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Setter for the {@link RecordData} user score.
     * 
     * @param score the score of the record.
     */
    public void setScore(final int score) {
        this.score = score;
    }

}
