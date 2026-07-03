package user.debug.testgame;

/**
 * Credits button in the main menu.
 */
public class ObjMenuButtonCredits extends ObjAbstractMenuButton {

    @Override
    public void setAspect() {
        setSpriteIndex("debug/testGame/buttonCredits");
    }

    @Override
    public void performAction() {
        z().roomGoto("debug/roomCredits");
    }

}
