package user.menu.creditsmenu;

import user.enums.MenuSprites;
import user.enums.RoomsNames;
import user.menu.AbstractMenuButton;
import user.watcher.LevelWatcher;
import user.watcher.OverallWatcher;

/**
 * This class represent the credits button.
 */
public class ObjCredits extends AbstractMenuButton {

    @Override
    public void setAspect() {
        setSpriteIndex(MenuSprites.CREDITS_MENU.getValue());
    }

    /**
     * resets the watchers to their initials states and goes to main menu.
     */
    @Override
    public void performAction() {
        OverallWatcher.getWatcher().reset();
        LevelWatcher.getWatcher().reset();
        z().roomGoto(RoomsNames.MAIN_MENU.getValue());
    }

}
