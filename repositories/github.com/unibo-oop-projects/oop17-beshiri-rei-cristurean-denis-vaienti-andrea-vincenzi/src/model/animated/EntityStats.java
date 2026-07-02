package model.animated;

/**
 * Enumeration that represent all type of entities.
 *
 */
public enum EntityStats {

    /**
     * Player statistics.
     */
    PLAYER(6, 8, 0, 30, 2, 400, 10, 10, 24),

    /**
     * Static enemy statistics.
     */
    STATIC_ENEMY(4, 0, 3000, 45, 1, 350, 10, 7, 22),

    /**
     * Movable enemy statistics.
     */
    MOVEABLE_ENEMY(4, 5, 5000, 50, 1, 400, 10, 8, 22),

    /**
     * Boss statistics.
     */
    BOSS(30, 0, 50000, 60, 1, 600, 10, 7, 120);

    private int life;
    private double vel;
    private int points;
    private double shootRatio;
    private int bulletDamage;
    private double bulletRange;
    private double bulletRadius;
    private double bulletVel;
    private double radius;

    /**
     * Constructor for enumeration.
     * 
     * @param l
     *            life.
     * @param v
     *            velocity.
     * @param points
     *            point.
     * @param sR
     *            shoot ratio.
     * @param bulletDamage
     *            bullet damage.
     * @param bulletRange
     *            bullet range.
     * @param bulletRadius
     *            bullets radius.
     * @param bulletVel
     *            bullet velocity.
     * @param radius
     *            bullet radius.
     */
    EntityStats(final int l, final double v, final int points, final double sR, final int bulletDamage,
            final double bulletRange, final double bulletRadius, final double bulletVel, final double radius) {
        life = l;
        vel = v;
        this.points = points;
        shootRatio = sR;
        this.bulletDamage = bulletDamage;
        this.bulletRange = bulletRange;
        this.bulletRadius = bulletRadius;
        this.bulletVel = bulletVel;
        this.radius = radius;
    }

    /**
     * @return Life of entity.
     */
    public int getLife() {
        return life;
    }

    /**
     * @return Vel of entity.
     */
    public double getVel() {
        return vel;
    }

    /**
     * @return Vel of entity.
     */
    public int getPoints() {
        return points;
    }

    /**
     * @return Shoot ratio.
     */
    public double getShotRatio() {
        return shootRatio;
    }

    /**
     * @return bullet range.
     */
    public double getBulletRange() {
        return bulletRange;
    }

    /**
     * @return bullet damage.
     */
    public int getBulletDamage() {
        return bulletDamage;
    }

    /**
     * @return bullet radius.
     */
    public double getBulletRadius() {
        return bulletRadius;
    }

    /**
     * @return bullet velocity.
     */
    public double getBulletVel() {
        return bulletVel;
    }

    /**
     * @return radius of entity.
     */
    public double getEntityRadius() {
        return radius;
    }
}
