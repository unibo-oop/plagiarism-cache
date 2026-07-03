package maingame.entity.projectile;

import java.awt.Rectangle;

import maingame.graphics.SpriteImpl;
import util.Vector2;

/** Classe estesa Normal Projectile. */

public class NormalProjectile extends Projectile {

    /** Tempo di frequenza di sparo del Normal Projectile. */
    public static final int FIRE_RATE = 10;
    private static final int RANGE = 200;
    private static final int SPEED = 4;
    private static final int DAMAGE = 30;
    private static final int HITBOX = 2;
    private static final int RECTANGLEDIM = 5;

    /**
     * Costruttore di normal projectile.
     * 
     * @param position
     *            posizione.
     * @param dir
     *            direzione del proiettile.
     */
    public NormalProjectile(final Vector2<Integer> position, final double dir) {
        super(position);
        setRange(RANGE);
        setDamage(DAMAGE);
        setParticleSprite(SpriteImpl.PARTICLE);
        setSprite(SpriteImpl.PROJECTILE);
        getMovement().set(SPEED * Math.cos(dir), SPEED * Math.sin(dir));
        getHitboxOffset().set(-HITBOX, -HITBOX);
        setHitbox(new Rectangle(position.getX() + getHitboxOffset().getX(), position.getY() + getHitboxOffset().getY(),
                RECTANGLEDIM, RECTANGLEDIM));
    }


}
