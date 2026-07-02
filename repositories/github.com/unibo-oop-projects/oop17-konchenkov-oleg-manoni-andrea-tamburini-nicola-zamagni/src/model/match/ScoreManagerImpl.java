package model.match;

/**
 * 
 * @author Andrea Manoni
 *
 */
public class ScoreManagerImpl implements ScoreManager {

    private static final int KILL_POINTS = 100;
    private static final int TURN_SURVIVED_POINTS = 10;
    private static final int WON_MATCH_POINTS = 300;
    private int kills;
    private int score;
    private int ranking;

    /**
     * Constructor.
     */
    public ScoreManagerImpl() {
        this.kills = 0;
        this.score = 0;
        this.ranking = 0;
    }
    /**
     * Copy Constructor.
     * @param scoreManager
     * scoreManager
     */
    public ScoreManagerImpl(final ScoreManager scoreManager) {
        this.kills = scoreManager.getKill();
        this.score = scoreManager.getScore();
        this.ranking = scoreManager.getRanking();
    }

    @Override
    public final int getKill() {
        return this.kills;
    }

    @Override
    public final int getScore() {
        return this.score;
    }

    @Override
    public final int getRanking() {
        return this.ranking;
    }

    @Override
    public final void setKill(final int kills) {
        this.kills += kills;
        setScore(kills);
    }

    private void setScore(final int kills) {
        this.score += kills * KILL_POINTS;
    }

    @Override
    public final void setRanking(final int rank) {
        this.ranking = rank;
    }

    @Override
    public final void survivedNewTurn() {
        this.score += TURN_SURVIVED_POINTS;
    }

    @Override
    public final void wonMatch() {
        this.score += WON_MATCH_POINTS;
    }

}
