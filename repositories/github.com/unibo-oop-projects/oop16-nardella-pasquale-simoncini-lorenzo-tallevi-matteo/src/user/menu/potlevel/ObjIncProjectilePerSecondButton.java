package user.menu.potlevel;

import user.enums.MenuSprites;
import user.enums.PowerupRanks;
import user.enums.RecurrentNumbers;
import user.menu.AbstractMenuButton;
import user.watcher.OverallWatcher;

/**
 * This class represent the increment attack speed (projectile per second)
 * button.
 */
public class ObjIncProjectilePerSecondButton extends AbstractMenuButton {

    private final OverallWatcher overWatch = OverallWatcher.getWatcher();

    @Override
    public void draw() {
        z().drawSpriteExt(MenuSprites.LABEL_PPS.getValue(), 0, RecurrentNumbers.POT_ROOM_RIGHT_LABEL_X.getValue(),
                RecurrentNumbers.POT_ROOM_MEDIUM_LABEL_Y.getValue(), getImageXscale(), getImageYscale(), 0, 1);
        z().drawSpriteExt(MenuSprites.BAR.getValue(), overWatch.getProjectilePerSecond(),
                RecurrentNumbers.POT_ROOM_RIGHT_BAR_X.getValue(), RecurrentNumbers.POT_ROOM_MEDIUM_BAR_Y.getValue(),
                getImageXscale(), getImageYscale(), 0, 1);
        if (isSelected()) {
            z().drawSpriteExt(MenuSprites.PROJECTILE_PER_SECOND.getValue(), 1,
                    RecurrentNumbers.POT_ROOM_RIGHT_BUTTON_X.getValue(), RecurrentNumbers.POT_ROOM_MEDIUM_BUTTON_Y.getValue(),
                    getImageXscale(), getImageYscale(), 0, 1);
        } else {
            drawSelf();
        }
    }

    @Override
    public void setAspect() {
        setSpriteIndex(MenuSprites.PROJECTILE_PER_SECOND.getValue());
        setPotMenuButtonScale();
    }

    @Override
    public void performAction() {
        if ((overWatch.getProjectilePerSecond() < PowerupRanks.RANK_5.getValue()) && (overWatch.getExpendablePoints() > 0)) {
            z().soundPlay("blipPowerUp");
            overWatch.setProjectilePerSecond(overWatch.getProjectilePerSecond() + 1);
            overWatch.setExpendablePoints(overWatch.getExpendablePoints() - 1);
        }
    }
}
