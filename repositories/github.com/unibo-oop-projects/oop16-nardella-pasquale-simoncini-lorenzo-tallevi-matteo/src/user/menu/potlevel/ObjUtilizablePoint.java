package user.menu.potlevel;

import user.enums.MenuSprites;
import user.enums.RecurrentNumbers;
import user.watcher.OverallWatcher;
import zengine.constants.ZengineColor;
import zengine.constants.ZengineMouseConstant;
import zengine.core.GameObject;

/**
 * This class represent the utilizable points.
 */
public class ObjUtilizablePoint extends GameObject {

    private final OverallWatcher overWatch = OverallWatcher.getWatcher();

    @Override
    public void create() {
        setSpriteIndex(MenuSprites.POINTS.getValue());
        setImageXscale(4);
        setImageYscale(4);
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
        z().drawText(String.valueOf(overWatch.getExpendablePoints()), RecurrentNumbers.POT_ROOM_RIGHT_BUTTON_X.getValue(),
                RecurrentNumbers.POT_ROOM_LOWER_BAR_Y.getValue(), "emulogicSmall", ZengineColor.C_CYAN);
    }

    @Override
    public void collide(final GameObject other) {

    }

    @Override
    public void mouseClicked(final ZengineMouseConstant button) {

    }

}