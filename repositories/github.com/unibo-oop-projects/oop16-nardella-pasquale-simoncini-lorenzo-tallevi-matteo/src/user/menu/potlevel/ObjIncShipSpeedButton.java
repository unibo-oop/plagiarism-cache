package user.menu.potlevel;

import user.enums.MenuSprites;
import user.enums.PowerupRanks;
import user.enums.RecurrentNumbers;
import user.menu.AbstractMenuButton;
import user.watcher.OverallWatcher;

/**
 * This class represent the increment speed button.
 */
public class ObjIncShipSpeedButton extends AbstractMenuButton {

    private final OverallWatcher overWatch = OverallWatcher.getWatcher();

    @Override
    public void draw() {
        z().drawSpriteExt(MenuSprites.LABEL_SPEED.getValue(), 0, RecurrentNumbers.POT_ROOM_LEFT_LABEL_X.getValue(),
                RecurrentNumbers.POT_ROOM_LOWER_LABEL_Y.getValue(), getImageXscale(), getImageYscale(), 0, 1);
        z().drawSpriteExt(MenuSprites.BAR.getValue(), overWatch.getSpeed(), RecurrentNumbers.POT_ROOM_LEFT_BAR_X.getValue(),
                RecurrentNumbers.POT_ROOM_LOWER_BAR_Y.getValue(), getImageXscale(), getImageYscale(), 0, 1);
        if (isSelected()) {
            z().drawSpriteExt(MenuSprites.SHIP_SPEED.getValue(), 1, RecurrentNumbers.POT_ROOM_LEFT_BUTTON_X.getValue(),
                    RecurrentNumbers.POT_ROOM_LOWER_BUTTON_Y.getValue(), getImageXscale(), getImageYscale(), 0, 1);
        } else {
            drawSelf();
        }
    }

    @Override
    public void setAspect() {
        setSpriteIndex(MenuSprites.SHIP_SPEED.getValue());
        setPotMenuButtonScale();
    }

    @Override
    public void performAction() {
        if (overWatch.getSpeed() < PowerupRanks.RANK_5.getValue() && (overWatch.getExpendablePoints() > 0)) {
            z().soundPlay("blipPowerUp");
            overWatch.setSpeed(overWatch.getSpeed() + 1);
            overWatch.setExpendablePoints(overWatch.getExpendablePoints() - 1);
        }
    }
}
