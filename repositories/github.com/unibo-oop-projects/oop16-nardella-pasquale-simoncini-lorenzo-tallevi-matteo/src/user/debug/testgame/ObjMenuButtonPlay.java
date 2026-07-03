package user.debug.testgame;

/**
 * Play button in the main menu.
 */
public class ObjMenuButtonPlay extends ObjAbstractMenuButton {

    @Override
    public void setAspect() {
        setSpriteIndex("debug/testGame/buttonPlay");
    }

    @Override
    public void performAction() {
        z().roomGoto("debug/roomGame");
    }

}
