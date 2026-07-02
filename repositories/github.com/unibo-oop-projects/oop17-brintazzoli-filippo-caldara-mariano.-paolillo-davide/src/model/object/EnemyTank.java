package model.object;

import model.utility.Calculate;
import model.utility.Pair;

/**
 * EnemyTank that is the enemy of player, extends Tank. The one difference
 * between them are the position of Cannon, and shot that is influenced by
 * different position of Tank.
 * 
 * @see Tank
 */
public class EnemyTank extends PlayerTank implements Tank {
    /**
     * Constructor
     * {@link PlayerTank#PlayerTank(Pair, int, double, double)}.
     * @param position initial position of tank
     * @param lifes of tank
     * @param speed initial speed
     * @param projectileSpeed projectile's speed
     */
    public EnemyTank(final Pair<Double, Double> position, final int lifes, final double speed, final double projectileSpeed) {
        super(position, lifes, speed, projectileSpeed);
        super.setCannon(new EnemyTank.Cannon());
    }

    /**
     * 
     * Cannon class for enemy Tank {@linkplain PlayerTank#cannon}
     * 
     * <p>
     * He allows the Tank to shoot.
     */
    private class Cannon extends PlayerTank.Cannon {
        private static final double ANGLE_30 = 30;
        private static final double ANGLE_90 = 90;
        private static final double ANGLE_330 = 330;
        private static final double ANGLE_359 = 359;
        private static final double ANGLE_270 = 270;
        @Override
        public void update(final Pair<Double, Double> position, final Pair<Double, Double> target) {
            setCannonPosition(new Pair<Double, Double>(position.getFirst() - Tank.getDimension().getFirst() / 2,
                    position.getSecond() + Tank.getDimension().getSecond() / 2 - this.getCannonDimension().getSecond() / 2));
            setAngle(Calculate.angle(
                    new Pair<>(getCannonPosition().getFirst(), getCannonPosition().getSecond() + this.getCannonDimension().getSecond() / 2),
                    target));
        }
        @Override
        public Projectile shot() {
            if (getAngle() >= ANGLE_30 && getAngle() < ANGLE_90) {
                return new ProjectileImpl(new Pair<Double, Double>(
                       getCannonPosition().getFirst() + super.getCannonDimension().getFirst() / 2
                                + (Tank.getDimension().getSecond() * Math.tan(Math.toRadians(90 - getAngle()))),
                        getPosition().getSecond() + Tank.getDimension().getSecond()), getAngle(), getProjectileSpeed());
            } else if (getAngle() >= 0 && getAngle() < ANGLE_30) {
                return new ProjectileImpl(
                        new Pair<Double, Double>(getPosition().getFirst() + this.getCannonDimension().getFirst(),
                                getPosition().getSecond() + Tank.getDimension().getSecond() / 2
                                        + (this.getCannonDimension().getFirst() * Math.tan(Math.toRadians(getAngle())))),
                        getAngle(), getProjectileSpeed());
            } else if (getAngle() >= ANGLE_330 && getAngle() <= ANGLE_359) {
                return new ProjectileImpl(
                        new Pair<Double, Double>(getPosition().getFirst() + this.getCannonDimension().getFirst(),
                                getPosition().getSecond() + Tank.getDimension().getSecond() / 2
                                        - (this.getCannonDimension().getFirst() * Math.tan(Math.toRadians(360 - getAngle())))),
                        getAngle(), getProjectileSpeed());
            } else if (getAngle() >= ANGLE_270 && getAngle() < ANGLE_330) {
                return new ProjectileImpl(new Pair<Double, Double>(
                        getCannonPosition().getFirst() + this.getCannonDimension().getFirst() / 2
                                + (Tank.getDimension().getSecond() * Math.tan(Math.toRadians(getAngle() - ANGLE_270))),
                        getPosition().getSecond() - Cannon.this.getMarginalDistance()), getAngle(), getProjectileSpeed());
            } else {
                return new ProjectileImpl(
                        new Pair<Double, Double>(
                                getPosition().getFirst() - this.getCannonDimension().getFirst() / 2
                                        + this.getCannonDimension().getFirst() / 2 * Math.cos(Math.toRadians(getAngle())),
                               getCannonPosition().getSecond()
                                        + this.getCannonDimension().getFirst() / 2 * Math.sin(Math.toRadians(getAngle()))),
                        getAngle(), getProjectileSpeed());
            }

        }

    }

}
