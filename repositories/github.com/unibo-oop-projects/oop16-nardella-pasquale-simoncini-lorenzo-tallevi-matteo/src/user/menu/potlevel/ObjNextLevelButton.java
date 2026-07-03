package user.menu.potlevel;

import user.enums.MenuSprites;
import user.enums.RecurrentNumbers;
import user.menu.AbstractMenuButton;
import user.watcher.OverallWatcher;

/**
 * This class represent the next damage button.
 */
public class ObjNextLevelButton extends AbstractMenuButton {

    private final OverallWatcher overWatch = OverallWatcher.getWatcher();

    @Override
    public void draw() {
        if (isSelected()) {
            z().drawSpriteExt(MenuSprites.NEXT_LEVEL.getValue(), 1, RecurrentNumbers.POT_ROOM_NEXT_LEVEL_BUTTON_X.getValue(),
                    RecurrentNumbers.POT_ROOM_NEXT_LEVEL_BUTTON_Y.getValue(), getImageXscale(), getImageYscale(), 0, 1);
        } else {
            drawSelf();
        }
    }

    @Override
    public void setAspect() {
        setSpriteIndex(MenuSprites.NEXT_LEVEL.getValue());
        setPotMenuButtonScale();
    }

    @Override
    public void performAction() {
        z().soundPlay("levelStart");
        overWatch.setCurrentLevel(overWatch.getCurrentLevel() + 1);
        z().roomGoto("roomLevel" + (overWatch.getCurrentLevel()));
    }
}
