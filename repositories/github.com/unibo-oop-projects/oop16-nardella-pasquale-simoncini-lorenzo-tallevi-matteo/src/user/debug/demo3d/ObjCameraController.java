package user.debug.demo3d;

import static zengine.constants.ZengineKeyboardConstant.VK_A;
import static zengine.constants.ZengineKeyboardConstant.VK_D;
import static zengine.constants.ZengineKeyboardConstant.VK_DOWN;
import static zengine.constants.ZengineKeyboardConstant.VK_LEFT;
import static zengine.constants.ZengineKeyboardConstant.VK_R;
import static zengine.constants.ZengineKeyboardConstant.VK_RIGHT;
import static zengine.constants.ZengineKeyboardConstant.VK_S;
import static zengine.constants.ZengineKeyboardConstant.VK_T;
import static zengine.constants.ZengineKeyboardConstant.VK_UP;
import static zengine.constants.ZengineKeyboardConstant.VK_X;
import static zengine.constants.ZengineKeyboardConstant.VK_Z;

import zengine.constants.ZengineColor;
import zengine.constants.ZengineMouseConstant;
import zengine.core.GameObject;

/**
 * moves camera with keyboard.
 */
public class ObjCameraController extends GameObject {

    private static final int SPEED = 15;
    private static final double ZOOM_SPEED = 1.04;

    @Override
    public void create() {
        setSpriteIndex("debug/demo3D/cameraController");
        setX(z().roomWidth() / 2);
        setY(z().roomHeight() / 2);

        setDepth(-1);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void update() {
        if (z().keyboardCheckPressed(VK_A)) {
            z().musicPlay("YMCK - 52 Futures");
            // z().musicPlay("levelMusic");
        }
        if (z().keyboardCheckPressed(VK_S)) {
            z().musicStop();
        }
        if (z().keyboardCheckPressed(VK_D)) {
            System.out.println(z().musicLast());
        }
        if (z().musicHasEnded()) {
            z().musicPlay("YMCK - 52 Futures");
        }

        if (z().keyboardCheck(VK_UP)) {
            moveY(-SPEED);
        }
        if (z().keyboardCheck(VK_DOWN)) {
            moveY(SPEED);
        }
        if (z().keyboardCheck(VK_LEFT)) {
            moveX(-SPEED);
        }
        if (z().keyboardCheck(VK_RIGHT)) {
            moveX(SPEED);
        }

        if (z().keyboardCheck(VK_Z)) {
            z().viewSetScale(z().viewGetScale() * ZOOM_SPEED);
        }
        if (z().keyboardCheck(VK_X)) {
            z().viewSetScale(z().viewGetScale() / ZOOM_SPEED);
        }

        z().viewSetPosition(getX(), getY());

        if (z().keyboardCheckPressed(VK_R)) {
            z().gameRestart();
        }

        if (z().keyboardCheckPressed(VK_T)) {
            gotoNext();
        }
    }

    @Override
    public void draw() {
        drawSelf();
        z().drawTextHUD("Z and X to zoom, arrow keys to move", 10, 10, "eurostile", ZengineColor.C_RED);
        z().drawTextHUD("R to restart the application, T to go to next room", 10, 10 + 10 + 10, "eurostile", ZengineColor.C_RED);
        z().drawTextHUD("A to play music, S to stop it", 10, 10 + 10 + 10 + 10 + 10, "eurostile", ZengineColor.C_RED);

    }

    @Override
    public void collide(final GameObject other) {
    }

    @Override
    public void mouseClicked(final ZengineMouseConstant button) {
    }

    private void gotoNext() {
        if (z().roomCurrent().equals("debug/demo3D/room0")) {
            z().roomGoto("debug/demo3D/room1");
        } else {
            z().roomGoto("debug/demo3D/room0");
        }
    }
}
