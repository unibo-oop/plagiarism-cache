package model.ai;

import java.util.Collection;

import model.entities.Bullet;
import model.hitbox.Hitbox;
import model.strategies.ChaseTheTarget;
import model.strategies.ShootCross;
import model.strategies.ShootRotatingCross;
import model.strategies.ShootTheTarget;
import model.strategies.Stactionary;

/**
 * 
 */
public class BossAI extends StandardAI {

    /**
     * 
     */
    private static final long serialVersionUID = 3243280974483954876L;
    private static final int PHASE2 = 50;
    private static final int SECOND = 60;
    private static final int SECONDPLUS = 120;
    private static final double FIRE_RATE_PHASE1 = 0.2;
    private double moltiplicator;
    private int c;

    /**
     * Constructs a new instance of Boss AI.
     */
    public BossAI() {
        super(new Stactionary(), new ShootRotatingCross());
        moltiplicator = FIRE_RATE_PHASE1;
    }

    @Override
    public void decide(final int currentHealth) {
        if (currentHealth <= PHASE2) {

            moltiplicator = 1.0;

            if (c == 0) {
                setMovement(new ChaseTheTarget());
                setShoot(new ShootTheTarget());

            } else if (c == SECOND) {
                setMovement(new ChaseTheTarget());
                setShoot(new ShootCross());
            }
            c++;

            if (c == SECONDPLUS) {
                c = 0;
            }
        }
    }

    @Override
    public Collection<Bullet> shoot(final Hitbox from, final double delta, final double fireRate, final double damage,
            final double range, final double steps) {

        return super.shoot(from, delta, fireRate * moltiplicator, damage, range, steps);
    }

}
