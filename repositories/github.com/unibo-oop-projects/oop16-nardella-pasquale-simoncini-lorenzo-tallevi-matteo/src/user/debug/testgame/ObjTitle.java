package user.debug.testgame;

import zengine.constants.ZengineMouseConstant;
import zengine.core.GameObject;

/**
 * This class just draws the title image.
 */
public class ObjTitle extends GameObject {

    @Override
    public void create() {
        setSpriteIndex("debug/testGame/title");
    }

    @Override
    public void destroy() {
    }

    @Override
    public void update() {
    }

    @Override
    public void draw() {
        drawSelf();
    }

    @Override
    public void collide(final GameObject other) {
    }

    @Override
    public void mouseClicked(final ZengineMouseConstant button) {
    }

}
