package user.menu.potlevel;

import user.enums.MenuSprites;
import user.enums.RecurrentNumbers;
import user.menu.AbstractMenuButton;
import user.watcher.OverallWatcher;
import zengine.constants.ZengineColor;

/**
 * This class represent the increment life button.
 */
public class ObjIncLifeButton extends AbstractMenuButton {

    private final OverallWatcher overWatch = OverallWatcher.getWatcher();

    @Override
    public void draw() {
        z().drawSpriteExt(MenuSprites.LABEL_LIFE.getValue(), 0, RecurrentNumbers.POT_ROOM_RIGHT_LABEL_X.getValue(),
                RecurrentNumbers.POT_ROOM_UPPER_LABEL_Y.getValue(), getImageXscale(), getImageYscale(), 0, 1);
        z().drawText(String.valueOf(overWatch.getLife()), RecurrentNumbers.POT_ROOM_RIGHT_BAR_X.getValue(),
                RecurrentNumbers.POT_ROOM_UPPER_BAR_Y.getValue(), "emulogicSmall", ZengineColor.C_RED);
        if (isSelected()) {
            z().drawSpriteExt(MenuSprites.LIFE.getValue(), 1, RecurrentNumbers.POT_ROOM_RIGHT_BUTTON_X.getValue(),
                    RecurrentNumbers.POT_ROOM_UPPER_BUTTON_Y.getValue(), getImageXscale(), getImageYscale(), 0, 1);
        } else {
            drawSelf();
        }
    }

    @Override
    public void setAspect() {
        setSpriteIndex(MenuSprites.LIFE.getValue());
        setPotMenuButtonScale();
    }

    @Override
    public void performAction() {
        if (overWatch.getExpendablePoints() > 0) {
            z().soundPlay("blipPowerUp");
            overWatch.setLife(overWatch.getLife() + 1);
            overWatch.setExpendablePoints(overWatch.getExpendablePoints() - 1);
        }
    }

}
