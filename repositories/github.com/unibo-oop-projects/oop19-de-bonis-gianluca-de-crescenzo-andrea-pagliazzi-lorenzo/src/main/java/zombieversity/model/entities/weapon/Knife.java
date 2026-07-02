package zombieversity.model.entities.weapon;

import java.util.Optional;

import javafx.geometry.Point2D;

/**
 * Representation of a standard {@link ShortRangeWeapon}.
 */
public class Knife extends AbstractShortRangeWeapon {

    private static final double WIDTH = 1;
    private static final double HEIGHT = 1;
    private static final int DAMAGE = 1000;
    private static final String NAME = "Knife";
    private static final long ATTACK_RATE = 1000;
    private boolean canAttack;
    private long lastAttack;

    public Knife(final Point2D p2d) {
        super(p2d);
        this.canAttack = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDamage() {
        return DAMAGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Optional<Attack> attack(final Point2D towards) {
        if (canAttack) {
            this.canAttack = false;
            this.lastAttack = System.currentTimeMillis();
            return Optional.of(new KnifeAttack(this.getPosition(), towards, DAMAGE));
        }

        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final double getWidth() {
        return WIDTH;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final double getHeight() {
        return HEIGHT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void update() {
        if (!canAttack && System.currentTimeMillis() - this.lastAttack > ATTACK_RATE) {
            canAttack = true;
        }
    }

}
