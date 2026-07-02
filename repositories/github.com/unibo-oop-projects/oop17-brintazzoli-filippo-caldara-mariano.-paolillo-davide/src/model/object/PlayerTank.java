package model.object;

import java.util.Map;
import java.util.stream.Collectors;

import exceptions.TankDeadException;
import model.input.Input;
import model.utility.Calculate;
import model.utility.Direction;
import model.utility.Pair;
/**
 * Main tank controlled by Player.
 */
public class PlayerTank implements Tank {
    private static final double ANGLE_MOVEMENT = 45;
    private int lifes;
    private Pair<Double, Double> position;
    private final double speed;
    private double speedX;
    private double speedY;
    private Cannon cannon = new Cannon();
    private final double projectileSpeed;

    /**
     * Constructor.
     * 
     * @param position
     *            initial position in {@linkplain Pair} of tank
     * @param lifes
     *            initial nr of lifes of tank
     * @param speed
     *            of tank
     * @param projectileSpeed
     *            speed of {@linkplain Projectile}
     */
    public PlayerTank(final Pair<Double, Double> position, final int lifes, final double speed, final double projectileSpeed) {
        this.position = position;
        this.lifes = lifes;
        this.speed = speed;
        this.projectileSpeed = projectileSpeed;
        this.getCannon().update(this.position, new Pair<Double, Double>(0.0, 0.0));
    }

    @Override
    public final boolean isAlive() {
        return this.lifes != 0;
    }

    @Override
    public final int getLifes() {
        return this.lifes;
    }

    @Override
    public final void setPosition(final Pair<Double, Double> position) {
        this.position = position;
    }

    @Override
    public final Pair<Double, Double> getPosition() {
        return this.position;
    }

    @Override
    public final void damage(final int damage) {
        this.lifes -= damage;
    }

    @Override
    public final Projectile shot() throws TankDeadException {
        if (!isAlive()) {
            throw new TankDeadException("This Tank is dead, cannot shot");
        }
        return this.getCannon().shot();
    }

    @Override
    public final void update(final Input i) throws TankDeadException {
        if (!isAlive()) {
            throw new TankDeadException("This Tank is dead, cannot updated");
        }
        this.setDirection(i.getMovement());
        this.updatePosition();
        this.updateCannon(i.getTargetPosition());
    }
    /**
     * Getter Cannon.
     * @return cannon
     */
    protected Cannon getCannon() {
        return cannon;
    }
    /**
     * Setter for cannon.
     * @param cannon new cannon to set
     */
    protected void setCannon(final Cannon cannon) {
        this.cannon = cannon;
    }
    /**
     * Getter for projectile speed.
     * @return double speed's projectile
     */
    protected double getProjectileSpeed() {
        return projectileSpeed;
    }

    @Override
    public final Pair<Double, Double> getCannonPosition() {
        return this.getCannon().getCannonPosition();
    }

    @Override
    public final Pair<Double, Double> getCannonDimension() {
        return this.getCannon().getCannonDimension();
    }

    @Override
    public final double getAngle() {
        return this.getCannon().getAngle();
    }

    /**
     * Set {@linkplain AbstractTank#speedX} and {@linkplain AbstractTank#speedY}
     * according {@linkplain AbstractTank#speed}.
     * 
     * @param movement
     *            the couple Direction and true, is the direction which the tank
     *            have to go
     */
    private void setDirection(final Map<Direction, Boolean> movement) {
        this.speedY = movement.keySet().stream().filter(d -> d.equals(Direction.UP) || d.equals(Direction.DOWN))
                .map(d -> movement.get(d) ? d.getSign() * speed : 0).collect(Collectors.summingDouble(x -> x))
                .doubleValue();
        this.speedX = movement.keySet().stream().filter(d -> d.equals(Direction.LEFT) || d.equals(Direction.RIGHT))
                .map(d -> movement.get(d) ? d.getSign() * speed : 0).collect(Collectors.summingDouble(x -> x))
                .doubleValue();
        if (this.speedX != 0 && this.speedY != 0) {
            this.speedX *= Math.cos(Math.toRadians(ANGLE_MOVEMENT));
            this.speedY *= Math.sin(Math.toRadians(ANGLE_MOVEMENT));
        }
    }

    /**
     * Update position in accordance to speed.
     */
    private void updatePosition() {
        this.position.setFirst(this.position.getFirst() + speedX);
        this.position.setSecond(this.position.getSecond() + speedY);
    }

    /**
     * Update angle of {@link Cannon}.
     * 
     * @param target
     *            target to aim.
     * @see InputImpl
     */
    private void updateCannon(final Pair<Double, Double> target) {
        this.getCannon().update(this.position, target);
    }
    /**
     * Cannon of Tank, allows to shoot.
     */
    protected class Cannon {
        private static final double WIDHT = 25.0;
        private static final double HEIGHT = 5.0;
        private static final double MARGINAL_DISTANCE = 10;
        private static final double ANGLE_150 = 150;
        private static final double ANGLE_180 = 180;
        private static final double ANGLE_210 = 210;
        private static final double ANGLE_270 = 270;
        private final Pair<Double, Double> cannonDimension = new Pair<Double, Double>(WIDHT, HEIGHT);
        private Pair<Double, Double> cannonPosition;
        private double angle;

        /**
         * Update the position, it follows the tank position.
         * 
         * @param position
         *            the position of cannon, it depends by {@link TankImpl#position}.
         * @param target
         *            the position where tank have to aim.
         */
        public void update(final Pair<Double, Double> position, final Pair<Double, Double> target) {
            this.cannonPosition = new Pair<Double, Double>(position.getFirst() + Tank.getDimension().getFirst() / 2,
                    position.getSecond() + Tank.getDimension().getSecond() / 2 - cannonDimension.getSecond() / 2);
            this.angle = Calculate.angle(new Pair<>(cannonPosition.getFirst() + cannonDimension.getFirst() / 2,
                    cannonPosition.getSecond() + cannonDimension.getSecond() / 2), target);

        }

        /**
         * Shot according angle and return the projectile. This method is always called
         * by tank in {@link TankImpl#shot()}.
         * 
         * @return the projectile just shooted
         */
        public Projectile shot() {
            if (this.angle >= 90 && this.angle < ANGLE_150) {
                return new ProjectileImpl(new Pair<Double, Double>(
                        this.cannonPosition.getFirst() + this.cannonDimension.getFirst() / 2
                                - (Tank.getDimension().getSecond() * Math.tan(Math.toRadians(this.angle - 90))),
                        position.getSecond() + Tank.getDimension().getSecond()), this.angle, projectileSpeed);
            } else if (this.angle >= ANGLE_150 && this.angle < ANGLE_180) {
                return new ProjectileImpl(
                        new Pair<Double, Double>(position.getFirst() - MARGINAL_DISTANCE,
                                position.getSecond() + Tank.getDimension().getSecond() / 2
                                        + (this.cannonDimension.getFirst()
                                                * Math.tan(Math.toRadians(180 - this.angle)))),
                        this.angle, projectileSpeed);
            } else if (this.angle >= 180 && this.angle < ANGLE_210) {
                return new ProjectileImpl(
                        new Pair<Double, Double>(position.getFirst() - MARGINAL_DISTANCE,
                                position.getSecond() + Tank.getDimension().getSecond() / 2
                                        - (this.cannonDimension.getFirst()
                                                * Math.tan(Math.toRadians(this.angle - 180)))),
                        this.angle, projectileSpeed);
            } else if (this.angle >= ANGLE_210 && this.angle < ANGLE_270) {
                return new ProjectileImpl(new Pair<Double, Double>(
                        this.cannonPosition.getFirst() + +this.cannonDimension.getFirst() / 2
                                - (Tank.getDimension().getSecond() * Math.tan(Math.toRadians(ANGLE_270 - this.angle))),
                        position.getSecond() - 10), this.angle, projectileSpeed);
            } else {
                return new ProjectileImpl(
                        new Pair<Double, Double>(
                                this.cannonPosition.getFirst() + this.cannonDimension.getFirst() / 2
                                        + this.cannonDimension.getFirst() / 2 * Math.cos(Math.toRadians(this.angle)),
                                this.cannonPosition.getSecond()
                                        + this.cannonDimension.getFirst() / 2 * Math.sin(Math.toRadians(this.angle))),
                        this.angle, projectileSpeed);
            }
        }
        /**
         * Setter for angle.
         * @param angle the angle to set
         */
        protected void setAngle(final double angle) {
            this.angle = angle;
        }
        /**
         * Getter of angle.
         * 
         * @return double of angle
         */
        public double getAngle() {
            return this.angle;
        }
        /**
         * Setter for cannon position.
         * @param cannonPosition the new position
         */
        protected void setCannonPosition(final Pair<Double, Double> cannonPosition) {
            this.cannonPosition = cannonPosition;
        }
        /**
         * Getter for position of cannon's tank.
         * 
         * @return {@linkplain Pair} with coordinates of cannon's tank
         */
        public Pair<Double, Double> getCannonPosition() {
            return new Pair<Double, Double>(this.cannonPosition.getFirst(), this.cannonPosition.getSecond());
        }
        /**
         * Getter for dimension of cannon's tank.
         * 
         * @return {@linkplain Pair}
         */
        public Pair<Double, Double> getCannonDimension() {
            return new Pair<Double, Double>(this.cannonDimension.getFirst(), this.cannonDimension.getSecond());
        }
        /**
         * Getter for marginal distance.
         * @return distance between border and cannon
         */
        protected double getMarginalDistance() {
            return Cannon.MARGINAL_DISTANCE;
        }
    }
}