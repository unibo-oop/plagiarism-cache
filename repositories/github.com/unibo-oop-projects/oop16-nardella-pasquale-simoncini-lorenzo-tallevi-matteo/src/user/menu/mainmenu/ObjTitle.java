package user.menu.mainmenu;

import user.enums.MenuSprites;
import zengine.constants.ZengineMouseConstant;
import zengine.core.GameObject;

/**
 * This class represent the title.
 */
public class ObjTitle extends GameObject {

    private static final double SCALE = 3;

    @Override
    public void create() {
        setSpriteIndex(MenuSprites.TITLE.getValue());
        setImageXscale(SCALE);
        setImageYscale(SCALE);
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
