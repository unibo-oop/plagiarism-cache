package user.menu.stagelevel;

import user.enums.RecurrentNumbers;
import user.watcher.LevelWatcher;
import zengine.constants.ZengineColor;
import zengine.constants.ZengineMouseConstant;
import zengine.core.GameObject;

/**
 * This class represent the life counter on screen during stage levels.
 */
public class ObjLife extends GameObject {
    private static final int DEPTH = -10;
    private final LevelWatcher watcher = LevelWatcher.getWatcher();

    @Override
    public void create() {
        setDepth(DEPTH);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void update() {

    }

    @Override
    public void draw() {
        if (watcher.getLife() >= 10) {
            z().drawTextHUD("life: " + watcher.getLife(), RecurrentNumbers.STAGE_LEVEL_RIGHT_BUTTON_X.getValue() - 10,
                    RecurrentNumbers.STAGE_LEVEL_BUTTON_Y.getValue(), "emulogicExtraSmall", ZengineColor.C_CYAN);
        } else {
            z().drawTextHUD("life: " + watcher.getLife(), RecurrentNumbers.STAGE_LEVEL_RIGHT_BUTTON_X.getValue(),
                    RecurrentNumbers.STAGE_LEVEL_BUTTON_Y.getValue(), "emulogicExtraSmall", ZengineColor.C_CYAN);
        }
    }

    @Override
    public void collide(final GameObject other) {

    }

    @Override
    public void mouseClicked(final ZengineMouseConstant button) {

    }

}
