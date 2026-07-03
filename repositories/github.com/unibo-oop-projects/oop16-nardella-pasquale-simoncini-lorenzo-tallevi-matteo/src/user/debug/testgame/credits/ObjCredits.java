package user.debug.testgame.credits;

import zengine.constants.ZengineColor;
import zengine.constants.ZengineMouseConstant;
import zengine.core.GameObject;
import zengine.constants.ZengineKeyboardConstant;

/**
 * This class just draws to the screen some credits info.
 */
public class ObjCredits extends GameObject {

    private static final int X_OF_TEXT0 = 30;
    private static final int Y_OF_TEXT0 = 30;
    private static final int X_OF_TEXT1 = 30;
    private static final int Y_OF_TEXT1 = 100;
    private static final int X_OF_TEXT2 = 30;
    private static final int Y_OF_TEXT2 = 130;
    private static final int X_OF_LOGO = 30;
    private static final int Y_OF_LOGO = 570;

    @Override
    public void create() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void update() {
        // if the user presses enter, return to the title screen
        if (z().keyboardCheckPressed(ZengineKeyboardConstant.VK_ENTER)) {
            z().roomGoto("debug/roomTitle");
        }
    }

    @Override
    public void draw() {
        // display some informations
        z().drawTextHUD("Thank you for playing!", X_OF_TEXT0, Y_OF_TEXT0, "emulogicSmall", ZengineColor.C_WHITE);
        z().drawTextHUD("Created by Matteo Tallevi, powered by Z Engine", X_OF_TEXT1, Y_OF_TEXT1, "eurostile",
                ZengineColor.C_ORANGE);
        z().drawTextHUD("Press enter to return", X_OF_TEXT2, Y_OF_TEXT2, "eurostile", ZengineColor.C_ORANGE);
        z().drawSpriteHUD("debug/testGame/logo", 0, X_OF_LOGO, Y_OF_LOGO, 1, 1, 0, 1);
    }

    @Override
    public void collide(final GameObject other) {
    }

    @Override
    public void mouseClicked(final ZengineMouseConstant button) {
    }

}
