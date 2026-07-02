package rogue.model.creature;

/**
 * An implementation for a {@link Special}. 
 *
 */
public class SpecialImpl implements Special {

    private final boolean flying;
    private final boolean greedy;
    private final boolean flyingRandom;
    private final boolean poisonous;
    private final boolean drainLife;
    private boolean hostile;


    public SpecialImpl(final boolean hostile, final boolean flying, final boolean greedy,
            final boolean flyingRandom, final boolean poisonous, final boolean drainLife) {
        super();
        this.hostile = hostile;
        this.flying = flying;
        this.greedy = greedy;
        this.flyingRandom = flyingRandom;
        this.poisonous = poisonous;
        this.drainLife = drainLife;
    }

    public SpecialImpl(final boolean hostile) {
        super();
        this.hostile = hostile;
        this.flying = false;
        this.greedy = false;
        this.flyingRandom = false;
        this.poisonous = false;
        this.drainLife = false;
    }

    public SpecialImpl() {
        super();
        this.hostile = false;
        this.flying = false;
        this.greedy = false;
        this.flyingRandom = false;
        this.poisonous = false;
        this.drainLife = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isHostile() {
        return this.hostile;
   }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFlying() {
        return this.flying;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isGreedy() {
        return this.greedy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFlyingRandom() {
        return this.flyingRandom;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPoisonous() {
        return this.poisonous;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDrainLife() {
        return this.drainLife;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void becomeHostile() {
        this.hostile = true;
    }

}
