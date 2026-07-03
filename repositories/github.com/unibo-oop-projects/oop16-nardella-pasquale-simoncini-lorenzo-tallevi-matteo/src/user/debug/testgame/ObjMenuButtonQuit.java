package user.debug.testgame;

/**
 * Quit button in the main menu.
 */
public class ObjMenuButtonQuit extends ObjAbstractMenuButton {

    @Override
    public void setAspect() {
        setSpriteIndex("debug/testGame/buttonQuit");
    }

    @Override
    public void performAction() {
        z().gameTerminate(0);
    }

}
