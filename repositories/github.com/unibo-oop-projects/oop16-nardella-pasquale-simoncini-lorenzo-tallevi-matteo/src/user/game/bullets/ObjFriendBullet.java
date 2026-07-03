package user.game.bullets;

import user.enums.GameSprites;
import zengine.constants.ZengineMouseConstant;
import zengine.core.GameObject;
import zengine.geometry.HitboxCircle;

/**
 * This class represents an ally bullet.
 */
public class ObjFriendBullet extends ObjBullet {

    private static final double SPEED = 22;

    private static final double SCALE = 4;

    @Override
    public void create() {
        // aspect parameters
        setSpriteIndex(GameSprites.BULLET_1.getValue());
        setImageIndex(0);
        setImageXscale(SCALE);
        setImageYscale(SCALE);
        // sets the hitbox
        setHitbox(new HitboxCircle(getX(), getY(), 8));
        this.setSpeed(SPEED);
    }

    @Override
    public void collide(final GameObject other) {
    }

    @Override
    public void mouseClicked(final ZengineMouseConstant button) {
    }

}
