package user.menu.gameovermenu;

import user.enums.MenuSprites;
import user.enums.RecurrentNumbers;
import user.enums.RoomsNames;
import user.menu.AbstractMenuButton;
import user.watcher.LevelWatcher;
import user.watcher.OverallWatcher;
import zengine.constants.ZengineMouseConstant;
import zengine.geometry.Hitbox;
import zengine.geometry.HitboxRectangle;

/**
 * This class represent the game over button.
 */
public class ObjGameOverButton extends AbstractMenuButton {

    @Override
    public void create() {
        final Hitbox hitbox = new HitboxRectangle(0, 0, RecurrentNumbers.RESOLUTION_X.getValue(),
                RecurrentNumbers.RESOLUTION_Y.getValue());
        setAspect();
        setHitbox(hitbox);
        addMouseClickedInteraction(ZengineMouseConstant.MB_LEFT);
    }

    @Override
    public void setAspect() {
        setSpriteIndex(MenuSprites.GAME_OVER.getValue());
        setImageXscale(4);
        setImageYscale(4);
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
