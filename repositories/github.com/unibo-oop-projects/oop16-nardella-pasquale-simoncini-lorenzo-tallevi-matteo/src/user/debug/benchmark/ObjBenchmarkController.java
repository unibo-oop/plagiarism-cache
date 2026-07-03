package user.debug.benchmark;

import static zengine.constants.ZengineKeyboardConstant.VK_R;
import static zengine.constants.ZengineKeyboardConstant.VK_T;
import static zengine.constants.ZengineKeyboardConstant.VK_S;

import zengine.constants.ZengineColor;
import zengine.constants.ZengineMouseConstant;
import zengine.core.GameObject;

/**
 * This class counts how many likes are on the screen and creates more likes
 * when required.
 */
public class ObjBenchmarkController extends GameObject {

    private int likesToSpawn = 1;
    private static final int LIKES_INC = 100;
    private int countdown = COUNTDOWN_MAX;
    private static final int COUNTDOWN_MAX = 30;
    private static final int DEPTH = -10;

    private static final int RECT_HEIGHT = 90;

    @Override
    public void create() {
        setDepth(DEPTH);
    }

    @Override
    public void destroy() {
    }

    @Override
    public void update() {
        if (countdown > 0) {
            countdown--;
        } else {
            countdown = COUNTDOWN_MAX;
            z().instanceCreate(z().random(z().roomWidth()), z().random(z().roomWidth()), "debug.benchmark.ObjLike");
        }

        if (z().keyboardCheckPressed(VK_T)) {
            spawnLikes();
        }
        if (z().keyboardCheckPressed(VK_S)) {
            clearLikes();
        }
        if (z().keyboardCheckPressed(VK_R)) {
            z().gameRestart();
        }
    }

    @Override
    public void draw() {
        z().drawRectangle(z().viewGetCornerX(), z().viewGetCornerY(), z().viewGetWidth() / 2, RECT_HEIGHT,
                ZengineColor.C_DARKGRAY, true);
        z().drawTextHUD("press T to spawn " + likesToSpawn + " more likes, S to clear everything", 10, 10, "eurostile",
                ZengineColor.C_RED);
        z().drawTextHUD("R to restart the application. Click a like to destroy it", 10, 10 + 10 + 10, "eurostile", ZengineColor.C_RED);
        z().drawTextHUD("number of instances (including me): " + z().instanceNumberGeneral(), 10, 10 + 10 + 10 + 10 + 10,
                "eurostile", ZengineColor.C_RED);

    }

    @Override
    public void collide(final GameObject other) {
    }

    @Override
    public void mouseClicked(final ZengineMouseConstant button) {
    }

    private void spawnLikes() {
        int count = 0;
        for (int i = 0; i < likesToSpawn; i++) {
            z().instanceCreate(z().random(z().roomWidth()), z().random(z().roomWidth()), "debug.benchmark.ObjLike");
            count++;
        }
        likesToSpawn += LIKES_INC;
        z().loggerMessage("spawned " + count + " likes");
    }

    private void clearLikes() {
        z().instanceDestroyEverything(this);
    }
}
