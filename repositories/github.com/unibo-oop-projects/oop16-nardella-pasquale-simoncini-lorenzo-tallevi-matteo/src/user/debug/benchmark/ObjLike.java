package user.debug.benchmark;

import zengine.constants.ZengineMouseConstant;
import zengine.core.GameObject;

/**
 * This class is an element used to test how many of them the Zengine can handle
 * at once.
 */
public class ObjLike extends GameObject {

    private double direction;
    private double speed;
    private double xoffset;
    private double yoffset;
    private static final double SPEEDMAX = 10;

    @Override
    public void create() {
        setSpriteIndex("debug/benchmark/like");
        direction = z().random(360);
        speed = z().random(SPEEDMAX);
        setHitbox(generateHitboxFromCurrentSettings(0, 0));
        addMouseClickedInteraction(ZengineMouseConstant.MB_LEFT);

        xoffset = z().spriteGetXoffset(getSpriteIndex());
        yoffset = z().spriteGetYoffset(getSpriteIndex());
    }

    @Override
    public void destroy() {
    }

    @Override
    public void update() {
        moveX(z().lengthDirX(speed, direction));
        moveY(z().lengthDirY(speed, direction));

        wrapToRoomBoundaries();
        getHitbox().setX(getX() - xoffset);
        getHitbox().setY(getY() - yoffset);
    }

    @Override
    public void draw() {
        drawSelf();
        // getHitbox().drawSelf(ZengineColor.C_RED, false);
    }

    @Override
    public void collide(final GameObject other) {
    }

    @Override
    public void mouseClicked(final ZengineMouseConstant button) {
        z().instanceDestroy(this);
    }

}
