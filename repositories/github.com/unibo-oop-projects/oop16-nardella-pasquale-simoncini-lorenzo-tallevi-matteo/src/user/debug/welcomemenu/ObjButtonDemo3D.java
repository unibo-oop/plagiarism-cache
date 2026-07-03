package user.debug.welcomemenu;

import user.debug.testgame.ObjAbstractMenuButton;

/**
 * debug menu button to go to automated test.
 */
public class ObjButtonDemo3D extends ObjAbstractMenuButton {

    @Override
    public void setAspect() {
        setSpriteIndex("debug/mainButtons/menuButtons");
        setImageIndex(2);
    }

    @Override
    public void performAction() {
        z().roomGoto("debug/demo3D/room0");
    }
}
