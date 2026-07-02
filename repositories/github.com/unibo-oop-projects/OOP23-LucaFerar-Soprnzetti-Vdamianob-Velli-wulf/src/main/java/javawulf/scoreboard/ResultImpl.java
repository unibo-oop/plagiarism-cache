package javawulf.scoreboard;

/**
 * Result Impl is the representatation of a result of a finished game.
 */
public final class ResultImpl implements Result {

    private final String username;
    private final int score;
    private final boolean won;

    /**
     * Creates a Result.
     * @param username Of the player
     * @param score Of the player
     * @param won True if the player won
     */
    public ResultImpl(final String username, final int score, final boolean won) {
        this.username = username;
        this.score = score;
        this.won = won;
    }

    @Override
    public String getUserName() {
        return this.username;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public boolean hasWon() {
        return this.won;
    }

    @Override
    public String toString() {
        return "[username=" + this.getUserName() + ", score=" + this.getScore() + ", won=" + this.hasWon() + "]";
    }

}
