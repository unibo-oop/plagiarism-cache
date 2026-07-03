package maingame.entity.projectile;

import maingame.entity.EntityImpl;
import maingame.entity.mob.Mob;
import maingame.entity.particle.Particle;
import maingame.graphics.ScreenImpl;
import maingame.graphics.Sprite;
import util.Vector2;
import util.Vector2Impl;

/**
 * Classe astratta Projectile. Estende Entity
 */
public abstract class Projectile extends EntityImpl {

    private final Vector2<Integer> origin;
    private final Vector2<Double> position;
    private final Vector2<Double> movement = new Vector2Impl<Double>(0.0, 0.0);
    private final Vector2<Integer> hitboxOffset = new Vector2Impl<Integer>(0, 0);
    private double range;
    private int damage;
    private Sprite particleSprite;
    private static final int PARTICLELIFE = 30;
    private static final int PARTICLEN = 50;
    private static final int OFFSETMOVE = 3;

    /**
     * Costruttore di proiettile.
     * 
     * @param position
     *            posizione.
     */
    public Projectile(final Vector2<Integer> position) {
        this.position = new Vector2Impl<Double>(position.getX().doubleValue(), position.getY().doubleValue());
        origin = new Vector2Impl<Integer>(position.getX(), position.getY());
    }

    /**
     * Movimento del proiettile sullo schermo, superato il range viene rimosso.
     * 
     */
    protected void move() {
        position.setX(position.getX() + movement.getX());
        position.setY(position.getY() + movement.getY());
        if (Vector2Impl.getDistance(new Vector2Impl<Integer>(origin.getX(), origin.getY()),
                new Vector2Impl<Double>(position.getX(), position.getY())) > range) {
            setRemove();
        }
    }

    @Override
    public void update() {
        if (tileCollision(new Vector2Impl<Integer>(movement.getX().intValue(), movement.getY().intValue()))
                || itemCollision(
                        new Vector2Impl<Integer>(movement.getX().intValue(), movement.getY().intValue())) != null) {
            for (int i = 0; i < PARTICLEN; i++) {
                getLevel().add(
                        new Particle(new Vector2Impl<Integer>(position.getX().intValue(), position.getY().intValue()),
                                PARTICLELIFE, particleSprite));
            }
            setRemove();
        }
        final Mob mob = mobCollision(new Vector2Impl<Integer>(movement.getX().intValue(), movement.getY().intValue()), true);
        if (mob != null) {
            mob.setHealth(mob.getHealth() - damage);
            mob.setDamaged(true);
            mob.setDamagedTimer(10);
            mob.move(new Vector2Impl<Integer>(movement.getX() > 0 ? OFFSETMOVE : -OFFSETMOVE,
                    movement.getY() > 0 ? OFFSETMOVE : -OFFSETMOVE), true);
            setRemove();
        }
        move();

        getHitbox().setLocation(position.getX().intValue() + hitboxOffset.getX(),
                position.getY().intValue() + hitboxOffset.getY());
    }

    @Override
    public Vector2<Integer> getPosition() {
        return new Vector2Impl<Integer>(position.getX().intValue(), position.getY().intValue());
    }

    /**
     * Render del proiettile sullo schermo.
     * 
     */
    public void render() {
        ScreenImpl.getScreen().render(
                new Vector2Impl<Integer>(position.getX().intValue() - (int) getSprite().getDimension().getWidth() / 2,
                        position.getY().intValue() - (int) getSprite().getDimension().getHeight() / 2),
                this.getSprite(), 1.0, false, false);
    }

    /**
     * Ritorna il vector a due coordinate di movement.
     * 
     * @return vector2 di movement.
     */
    public Vector2<Double> getMovement() {
        return movement;
    }

    /**
     * Setta la sprite del Particle.
     * 
     * @param particleSprite
     *            sprite di particle.
     */
    public void setParticleSprite(final Sprite particleSprite) {
        this.particleSprite = particleSprite;
    }

    /**
     * Ritorna l'hitboxoffset.
     * 
     * @return l'offeset.
     */
    public Vector2<Integer> getHitboxOffset() {
        return hitboxOffset;
    }

    /**
     * Setta il range.
     * 
     * @param range
     *            range massimo che raggiunge.
     */
    public void setRange(final double range) {
        this.range = range;
    }

    /**
     * Setta il danno.
     * 
     * @param damage
     *            Danno proiettile.
     */
    public void setDamage(final int damage) {
        this.damage = damage;
    }
}
