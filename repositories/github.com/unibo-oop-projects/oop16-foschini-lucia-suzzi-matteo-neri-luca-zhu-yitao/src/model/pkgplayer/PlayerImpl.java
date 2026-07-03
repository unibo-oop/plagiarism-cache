package model.pkgplayer;

/**
 * 
 * Player class that implements the interface.
 *
 */
public class PlayerImpl implements Player {
    private String name; // username
    private String pass; // password
    private int level; // reached level
    private int bestScore = 0; // best score

    /**
     * Constructor of the class.
     * 
     * @param user
     *            username
     * @param pw
     *            password
     * @param lvl
     *            reached level
     * @param score
     *            best score
     */
    public PlayerImpl(final String user, final String pw, final int lvl, final int score) {
        this.name = user;
        this.pass = pw;
        this.level = lvl;
        this.bestScore = score;
    }

    @Override
    public String getName() {

        return this.name;
    }

    @Override
    public String getPass() {

        return this.pass;
    }

    @Override
    public int getLevel() {

        return this.level;
    }

    @Override
    public void setLevel(final int newLevel) {
        this.level = newLevel;
    }

    private void setScore(final int newScore) {
        this.bestScore = newScore;
    }

    @Override
    public int getBestScore() {
        return this.bestScore;
    }

    @Override
    public void updateScore(final int value) {
        if (this.bestScore < value) {
            this.setScore(value);
        }
    }

}
