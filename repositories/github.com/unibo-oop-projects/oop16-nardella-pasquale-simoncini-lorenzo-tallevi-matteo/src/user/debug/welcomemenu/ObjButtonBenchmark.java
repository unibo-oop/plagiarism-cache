package user.debug.welcomemenu;

import user.debug.testgame.ObjAbstractMenuButton;

/**
 * debug menu button to go to automated test.
 */
public class ObjButtonBenchmark extends ObjAbstractMenuButton {

    @Override
    public void setAspect() {
        setSpriteIndex("debug/mainButtons/menuButtons");
        setImageIndex(1);
    }

    @Override
    public void performAction() {
        z().roomGoto("debug/roomBenchmark");
    }
}
