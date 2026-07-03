package model.strategies;

import java.util.ArrayList;
import java.util.Collection;

import model.entities.Bullet;
import model.hitbox.Hitbox;

/**
 * Doesn't shoot anything.
 */
public class Shootless implements ShootingType {

    /**
     * 
     */
    private static final long serialVersionUID = 5770088761886065619L;

    @Override
    public Collection<Bullet> shoot(final Hitbox from, final double delta, final double fireRate, final double damage,
            final double range, final double steps) {
        return new ArrayList<Bullet>();
    }

}
