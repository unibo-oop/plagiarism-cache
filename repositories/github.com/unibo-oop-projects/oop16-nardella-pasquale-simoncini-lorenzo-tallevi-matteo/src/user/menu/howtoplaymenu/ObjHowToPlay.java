package user.menu.howtoplaymenu;

import user.enums.MenuSprites;
import user.enums.RoomsNames;
import user.menu.AbstractMenuButton;


/**
 * This class represent the howtoplay button.
 */
public class ObjHowToPlay extends AbstractMenuButton {

    @Override
    public void setAspect() {
        setSpriteIndex(MenuSprites.HOWTOPLAY_MENU.getValue());
    }

    @Override
    public void performAction() {
        z().roomGoto(RoomsNames.MAIN_MENU.getValue());
    }

}
