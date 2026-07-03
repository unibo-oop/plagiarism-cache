package user.menu.stagelevel;

import user.enums.RecurrentNumbers;
import user.watcher.LevelWatcher;
import zengine.constants.ZengineColor;
import zengine.constants.ZengineMouseConstant;
import zengine.core.GameObject;

/**
 * This class represent the time counter on screen during stage levels.
 */
public class ObjTime extends GameObject {
    private static final int DEPTH = -10;
    private static final int FRAMES = 30;
    private final LevelWatcher watcher = LevelWatcher.getWatcher();
    private int seconds; // = 0;
    private int count; // = 0;

    @Override
    public void create() {
        setDepth(DEPTH);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void update() {
        count++;
        if (count >= FRAMES) {
            count = 0;
            this.seconds++;
            watcher.setTime(this.seconds);
        }
    }

    @Override
    public void draw() {
        z().drawTextHUD("time: " + watcher.getTime(), RecurrentNumbers.STAGE_LEVEL_CENTRAL_BUTTON_X.getValue(),
                RecurrentNumbers.STAGE_LEVEL_BUTTON_Y.getValue(), "emulogicExtraSmall", ZengineColor.C_CYAN);
    }

    @Override
    public void collide(final GameObject other) {

    }

    @Override
    public void mouseClicked(final ZengineMouseConstant button) {

    }

}
