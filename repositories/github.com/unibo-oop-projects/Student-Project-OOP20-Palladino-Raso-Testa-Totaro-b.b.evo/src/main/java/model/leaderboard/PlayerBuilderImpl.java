package model.leaderboard;

public class PlayerBuilderImpl implements PlayerBuilder {

    private static final long serialVersionUID = -7344646435858952139L;
    private String alias;
    private int score;
    private int life;
    private int maxLife;

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public PlayerBuilder alias(final String alias) {
        this.alias = alias;
        return this;
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public PlayerBuilder score(final int score) {
        this.score = score;
        return this;
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public PlayerBuilder life(final int life) {
        this.life = life;
        return this;
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public PlayerBuilder maxLife(final int value) {
        this.maxLife = value;
        return this;
    }
    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public Player build() {
        if (this.alias == null 
            || this.score < 0 
            || this.life < 0 
            || this.life > this.maxLife
            || this.maxLife < 0) {
            throw new IllegalStateException();
        }
        return new PlayerImpl(this.alias, this.score, this.life, this.maxLife);
    }

}
