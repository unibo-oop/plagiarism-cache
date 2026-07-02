package model.ranking;

import java.util.Objects;
import com.google.gson.annotations.Expose;
import model.components.GameTimerImpl;

/**
 * 
 * Models a player.
 * Remembers the nickname, the time of the game and the final score.
 *
 */
public class PlayerImpl implements Player {

    @Expose
    private final String nickname;
    //HINT FOR THE FUTURE: trovare il modo corretto per deserializzare un'interfaccia tramite un interface adapter
    @Expose
    private final GameTimerImpl gameTimer;
    @Expose
    private final int finalScore;

    /**
     * Builds a new player.
     * 
     * @param nickname
     *      nickname of the player
     * @param gameTimer
     *      time of the game
     * @param finalScore
     *      the final score
     */
    public PlayerImpl(final String nickname, final GameTimerImpl gameTimer, final int finalScore) {
        final String nn = Objects.requireNonNull(nickname, "Every player needs a nickname");
        final GameTimerImpl gt = Objects.requireNonNull(gameTimer, "It's absolutely impossible that the timer is null");
        final int score = Objects.requireNonNull(finalScore, "The minimum score is zero and that means it can't be null");
        this.nickname = nn;
        this.gameTimer = gt;
        this.finalScore = score;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNickname() {
        return this.nickname;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameTimerImpl getGameTimer() {
        return this.gameTimer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getFinalScore() {
        return this.finalScore;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPosition() {
        final Leaderboard leaderboard = new LeaderboardImpl();
        return leaderboard.getSortedPlayersList().indexOf(this) + 1;
    }

    @Override
    public final int compareTo(final Player player) {
        return (player.getFinalScore() != this.finalScore)
                    ? player.getFinalScore() - this.finalScore
                        : this.gameTimer.compareTo(player.getGameTimer());
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + finalScore;
        return result;
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PlayerImpl other = (PlayerImpl) obj;
        return this.nickname.equals(other.nickname) && this.gameTimer.equals(other.gameTimer) && this.finalScore == other.finalScore;
    }
}
