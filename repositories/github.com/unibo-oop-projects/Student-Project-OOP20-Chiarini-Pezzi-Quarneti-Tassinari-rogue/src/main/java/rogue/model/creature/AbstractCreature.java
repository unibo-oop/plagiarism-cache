package rogue.model.creature;

/**
 * An abstract implementation for {@link Creature} common either for 
 * Monsters and player.
 *
 * @param <L> the creature's life
 */
public abstract class AbstractCreature<L extends Life> implements Creature<L> {

    private final L life;

    protected AbstractCreature(final L life) {
        this.life = life;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public L getLife() {
        return this.life;
    }

}
