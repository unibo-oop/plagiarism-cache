package zombieversity.model.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import zombieversity.model.collisions.CollisionsUtils;
import zombieversity.model.entities.weapon.LongRangeWeapon;
import zombieversity.model.entities.weapon.ShortRangeWeapon;
import zombieversity.model.entities.weapon.WeaponFactory;
import zombieversity.model.entities.weapon.WeaponFactory.WeaponName;

/**
 * 
 * Implementation of {@link Player}.
 *
 */
public class PlayerImpl extends AbstractActiveLivingEntity implements Player {

    private static final int MAX_HP = 100;

    private final LongRangeWeapon gun;
    private final ShortRangeWeapon knife;
    private Set<BoundingBox> obstacles;
    private final List<Boolean> iscolliding;

    /**
     * Construct a {@link Player}.
     */
    public PlayerImpl() {
        super(Point2D.ZERO, MAX_HP, Point2D.ZERO, EntityType.PLAYER);
        this.iscolliding = new ArrayList<>();
        this.gun = new WeaponFactory().getLongRangeWeapon(this.getPosition(), WeaponName.RIFLE);
        this.knife = new WeaponFactory().getShortRangeWeapon(this.getPosition(), WeaponName.KNIFE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setObstacles(final Set<BoundingBox> obstacles) {
        this.obstacles = obstacles;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final LongRangeWeapon getFirstWeapon() {
        return this.gun;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final ShortRangeWeapon getSecondWeapon() {
        return this.knife;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hitPlayer(final int damage) {
        getLifeManager().decreaseHP(damage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void checkCollision(final Point2D nextPos) {
        final BoundingBox bb = new BoundingBox(getPosition().getX() + nextPos.getX(),
                getPosition().getY() + nextPos.getY(), getWidth(), getHeight());

        this.obstacles.forEach(o -> {
            if (CollisionsUtils.isColliding(bb, o)) {
                this.iscolliding.add(true);
            }
        });

        if (!this.iscolliding.isEmpty()) {
            setVelocity(Point2D.ZERO);
        } else {
            setVelocity(nextPos);
        }
        this.iscolliding.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void computeAngle(final Point2D pos, final Point2D offset) {
        final Point2D disMov = pos
                .subtract(getPosition().subtract(offset).subtract(new Point2D(getWidth() / 2, getHeight() / 2)));
        final double angle = Math.toDegrees(Math.atan2(disMov.getY(), disMov.getX()));

        setDirection(angle);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void update() {
        super.update();
        this.updateWeapons();
    }

    private void updateWeapons() {
        this.gun.update();
        this.knife.update();
        this.gun.setPosition(this.getPosition());
        this.knife.setPosition(this.getPosition());
    }
}
