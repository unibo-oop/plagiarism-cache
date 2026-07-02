package zombieversity.model.score;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * A basic {@link Score}.
 */
public class ScoreImpl implements Score {

    private long time;
    private int kills;
    private Optional<Integer> position;
    private Optional<String> timePlayed;
    private Optional<String> nickname;

    /**
     * @param kills
     *          The number of kills done by the time of the constructor call, that can be incremented.
     * @param nickname
     *          The nickname of the player.
     */
    public ScoreImpl(final int kills, final String nickname) {
        this.kills = kills;
        this.timePlayed = Optional.empty();
        this.position = Optional.empty();
        this.nickname = Optional.ofNullable(nickname);
        this.setGameStart();
    }

    /**
     * Basic creation with the standard values for a new game.
     */
    public ScoreImpl() {
        this(0, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int getKills() {
        return this.kills;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String getNickname() {
        return this.nickname.orElse("Not set");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String getTimePlayed() {
        return this.timePlayed.orElse("Not set");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setTimePlayed(final String time) {
        this.timePlayed = Optional.of(time);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setKills(final int kills) {
        this.kills = kills;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setNickname(final String nickname) {
        this.nickname = Optional.ofNullable(nickname);
    }

    /**
     * Used to internally set the beginning of the game through the constructor.
     */
    private void setGameStart() {
        this.time = System.currentTimeMillis();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setGameEnd() {
        this.time = System.currentTimeMillis() - this.time;
        final long hour = TimeUnit.MILLISECONDS.toHours(this.time);
        final long min = TimeUnit.MILLISECONDS.toMinutes(this.time) - TimeUnit.HOURS.toMinutes(hour);
        final long sec = TimeUnit.MILLISECONDS.toSeconds(this.time) - TimeUnit.MINUTES.toSeconds(min);
        this.timePlayed = Optional.of(String.format("%02d:%02d:%02d", hour, min, sec));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void addKill() {
        this.kills++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setPosition(final int position) {
        this.position = Optional.of(position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int getPosition() {
        return this.position.orElse(-1);
    }

}
