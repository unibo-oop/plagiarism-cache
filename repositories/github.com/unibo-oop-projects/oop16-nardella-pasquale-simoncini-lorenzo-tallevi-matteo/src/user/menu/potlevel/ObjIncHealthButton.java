package user.menu.potlevel;

import user.enums.MenuSprites;
import user.enums.PowerupRanks;
import user.enums.RecurrentNumbers;
import user.menu.AbstractMenuButton;
import user.watcher.OverallWatcher;

/**
 * This class represent the increment health button.
 */
public class ObjIncHealthButton extends AbstractMenuButton {

    private final OverallWatcher overWatch = OverallWatcher.getWatcher();

    @Override
    public void draw() {
        z().drawSpriteExt(MenuSprites.LABEL_HEALTH.getValue(), 0, RecurrentNumbers.POT_ROOM_LEFT_LABEL_X.getValue(),
                RecurrentNumbers.POT_ROOM_UPPER_LABEL_Y.getValue(), getImageXscale(), getImageYscale(), 0, 1);
        z().drawSpriteExt(MenuSprites.BAR.getValue(), overWatch.getHealth(), RecurrentNumbers.POT_ROOM_LEFT_BAR_X.getValue(),
                RecurrentNumbers.POT_ROOM_UPPER_BAR_Y.getValue(), getImageXscale(), getImageYscale(), 0, 1);
        if (isSelected()) {
            z().drawSpriteExt(MenuSprites.HEALTH.getValue(), 1, RecurrentNumbers.POT_ROOM_LEFT_BUTTON_X.getValue(),
                    RecurrentNumbers.POT_ROOM_UPPER_BUTTON_Y.getValue(), getImageXscale(), getImageYscale(), 0, 1);
        } else {
            drawSelf();
        }
    }

    @Override
    public void setAspect() {
        setSpriteIndex(MenuSprites.HEALTH.getValue());
        setPotMenuButtonScale();
    }

    @Override
    public void performAction() {
        if ((overWatch.getHealth() < PowerupRanks.RANK_5.getValue()) && (overWatch.getExpendablePoints() > 0)) {
            z().soundPlay("blipPowerUp");
            overWatch.setHealth(overWatch.getHealth() + 1);
            overWatch.setExpendablePoints(overWatch.getExpendablePoints() - 1);
        }
    }
}
