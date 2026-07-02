package util;

public class User {

    private String name;
    private String score;

    /**
     * This is the constructor method that initializes the name and the score of the user.
     * @param name User name
     * @param score UserScore
     */
    public User(final String name, final String score) {
        this.name = name;
        this.score = score;
    }

    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }


    /**
     * @param name the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }



    /**
     * @return the score
     */
    public String getScore() {
        return this.score;
    }


    /**
     * @param score the score to set
     */
    public void setScore(final String score) {
        this.score = score;
    }

}
