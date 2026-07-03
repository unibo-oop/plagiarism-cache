package user.menu.mainmenu;

import user.enums.MenuSprites;
import user.enums.RoomsNames;
import user.menu.AbstractMenuButton;

/**
 * This class represent the how to play button.
 */
public class ObjButtonHowToPlay extends AbstractMenuButton {

    @Override
    public void draw() {
        if (isSelected()) {
            z().drawSpriteExt(MenuSprites.INDICATOR.getValue(), 0, 0, getY(), getImageXscale(), getImageYscale(), 0, 1);
        }
        drawSelf();
    }

    @Override
    public void setAspect() {
        setSpriteIndex(MenuSprites.HOWTOPLAY.getValue());
        setMainMenuButtonScale();
    }

    @Override
    public void performAction() {
        z().soundPlay("menuSelect");
        z().roomGoto(RoomsNames.HOWTOPLAY.getValue());
    }
}
