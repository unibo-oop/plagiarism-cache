package maingame.entity.projectile;

import java.awt.Rectangle;

import maingame.graphics.AnimatedSprite;
import maingame.graphics.SpriteImpl;
import util.Vector2;

/** Classe estesa Super Projectile. */

public class SuperProjectile extends Projectile {

    /** Tempo di frequenza di sparo del Super Projectile. */
    public static final int FIRE_RATE = 40;
    private final AnimatedSprite animProj = SpriteImpl.SUPERPROJECTILE;
    private static final int RANGE = 300;
    private static final int SPEED = 3;
    private static final int DAMAGE = 55;
    private static final int HITBOX = 6;
    private static final int RECTANGLEDIM = 12;

    /**
     * Costruttore di super projectile.
     * 
     * @param position
     *            posizione.
     * @param dir
     *            direzione.
     */
    public SuperProjectile(final Vector2<Integer> position, final double dir) {
        super(position);
        setRange(RANGE);
        setDamage(DAMAGE);
        setParticleSprite(SpriteImpl.SUPERPARTICLE);
        setSprite(animProj);
        getMovement().set(SPEED * Math.cos(dir), SPEED * Math.sin(dir));
        getHitboxOffset().set(-HITBOX, -HITBOX);
        setHitbox(new Rectangle(position.getX() + getHitboxOffset().getX(), position.getY() + getHitboxOffset().getY(),
                RECTANGLEDIM, RECTANGLEDIM));
    }

    @Override
    public void update() {
        animProj.update();
        super.update();
    }
}
