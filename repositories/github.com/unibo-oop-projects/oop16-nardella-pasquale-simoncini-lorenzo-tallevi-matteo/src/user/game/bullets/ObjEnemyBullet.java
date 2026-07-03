package user.game.bullets;

import user.enums.GameSprites;
import zengine.constants.ZengineMouseConstant;
import zengine.core.GameObject;
import zengine.geometry.HitboxCircle;

/**
 * This class represents an enemy bullet.
 */
public final class ObjEnemyBullet extends ObjBullet {

    private static final double SPEED = 10;
    private static final double DAMAGE = 1;

    private static final double SCALE = 4;

    @Override
    public void create() {
        // aspect parameters
        setSpriteIndex(GameSprites.BULLET_1.getValue());
        setImageIndex(1);
        setImageXscale(SCALE);
        setImageYscale(SCALE);
        this.setDepth(-1);
        // sets the hitbox
        setHitbox(new HitboxCircle(getX(), getY(), 8));
        this.setSpeed(SPEED);
        this.setDamage(DAMAGE);
    }

    @Override
    public void collide(final GameObject other) {
    }

    @Override
    public void mouseClicked(final ZengineMouseConstant button) {
        // TODO Auto-generated method stub

    }

}
