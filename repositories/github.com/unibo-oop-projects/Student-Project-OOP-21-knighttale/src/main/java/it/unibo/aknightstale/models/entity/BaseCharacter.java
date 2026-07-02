package it.unibo.aknightstale.models.entity;

import it.unibo.aknightstale.utils.Borders;
import it.unibo.aknightstale.utils.Point2D;

public abstract class BaseCharacter extends EntityImpl implements Character {

    @SuppressWarnings("unused")
    private static final long serialVersionUID = 1L;    //required to serialize the class
    /**
     * The entity damage.
     */
    private double damage;
    /**
     * The entity current health.
     */
    private double health;
    /**
     * The entity defense percentage.
     * This value is used to calculate the damage received by the entity: the damage received will be reduced by this percentage.
     */
    private double defense;
    /**
     * The entity maximum health.
     */
    private final double maxHealth;
    /**
     * The entity speed.
     */
    private double speed;
    /**
     * The entity direction.
     */
    private Direction direction;

    public BaseCharacter(final Borders borders, final EntityType type, final boolean collidable, final Direction dir,
                         final double dmg, final double health, final double speed, final double defense) {
        super(borders, type, collidable);
        this.damage = dmg;
        this.health = health;
        this.maxHealth = health;
        this.speed = speed;
        this.direction = dir;
        this.defense = defense;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getDamage() {
        return this.damage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDamage(final double dmg) {
        this.damage = dmg;
    }

    @Override
    public abstract double getAttackRange();

    /**
     * {@inheritDoc}
     */
    @Override
    public void attack(final LifeEntity e) {
        e.setHealth(e.getHealth() - (this.getDamage() * (e.getDefense() / 100)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setHealth(final double health) {
        this.health = health;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getHealth() {
        return this.health;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getMaxHealth() {
        return this.maxHealth;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDead() {
        return this.health <= 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getSpeed() {
        return this.speed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSpeed(final double speed) {
        this.speed = speed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getDefense() {
        return defense;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDefense(final double defense) {
        this.defense = defense;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Direction getDirection() {
        return this.direction;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDirection(final Direction dir) {
        this.direction = dir;
    }

    private void updatePosition(final double x, final double y) {
        final var pos = super.getPosition();
        super.setPosition(new Point2D(pos.getX() + x, pos.getY() + y));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void goUp() {
        this.updatePosition(0, -speed);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void goDown() {
        this.updatePosition(0, speed);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void goLeft() {
        this.updatePosition(-speed, 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void goRight() {
        this.updatePosition(speed, 0);
    }

}
