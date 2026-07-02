package giocoscudetto.model.impl.match;

import giocoscudetto.model.api.match.Scoreboard;

/**
 * This class represents the scoreboard of the match.
 */
public class ScoreboardImpl implements Scoreboard {

    private int homeScore;
    private int guestScore;
    private boolean played;

    /**
     * Constructor of the ScoreboardImpl class,
     * it initializes the home and guest score to 0.
     */
    public ScoreboardImpl() {
        this.homeScore = 0;
        this.guestScore = 0;
        this.played = false;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public int getGuestScore() {
        played = true;
        return guestScore;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public int getHomeScore() {
        played = true;
        return homeScore;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void setHomeScore(final int newScore) {
        played = true;
        this.homeScore = newScore;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void setGuestScore(final int newScore) {
        played = true;
        this.guestScore = newScore;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void increaseHomeScore() {
        played = true;
        this.homeScore = homeScore + 1;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void increaseGuestScore() {
        played = true;
        this.guestScore = guestScore + 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void decreaseHomeScore() {
        played = true;
        this.homeScore = this.homeScore - 1;
        if (this.homeScore < 0) {
            this.homeScore = 0;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void decreaseGuestScore() {
        played = true;
        this.guestScore = this.guestScore - 1;
        if (this.guestScore < 0) {
            this.guestScore = 0;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return played ? homeScore + " - " + guestScore : " vs";
    }
}
