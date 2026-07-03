package maingame.entity.projectile;

import java.awt.Rectangle;

import maingame.graphics.AnimatedSprite;
import maingame.graphics.SpriteImpl;
import util.Vector2;

/** Classe estesa Super-Proiettile Rosso. */
public class RedSuperProjectile extends Projectile {
    /** Tempo di frequenza di sparo del Normal Projectile. */
    public static final int FIRE_RATE = 35;
    private final AnimatedSprite animProj = SpriteImpl.REDSUPERPROJECTILE;
    private static final int RANGE = 400;
    private static final int SPEED = 3;
    private static final int DAMAGE = 70;
    private static final int HITBOX = 6;
    private static final int RECTANGLEDIM = 12;

    /**
     * Costruttore del super proiettile rosso (per la skin 2).
     * 
     * @param position
     *            posizione.
     * @param dir
     *            direzione.
     */
    public RedSuperProjectile(final Vector2<Integer> position, final double dir) {
        super(position);
        setRange(RANGE);
        setDamage(DAMAGE);
        setParticleSprite(SpriteImpl.REDSUPERPARTICLE);
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