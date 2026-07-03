package user.debug.testgame.game;

import zengine.constants.ZengineColor;
import zengine.constants.ZengineMouseConstant;
import zengine.core.GameObject;

/**
 * This class is linked to the plane, it checks win and losing conditions but
 * could also store informations about score, lives etc.
 */
public class ObjWatcher extends GameObject {

    private static final int COUNTDOWN_START = 60;
    private int countdown = COUNTDOWN_START;
    private boolean win; // = false;
    private boolean lost; // = false;

    private static final int INTERVAL_MAX = 30;
    private int interval = INTERVAL_MAX;

    @Override
    public void create() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void update() {
        // sometimes, check if win conditions are met
        interval -= 1;
        if (interval <= 0 && !(win || lost)) {
            interval = INTERVAL_MAX;
            if (checkForVictory()) {
                youWin();
            }
        }
        // if it's win or defeat, start a countdown
        if (win || lost) {
            countdown -= 1;
        }
        // if countdown reaches 0, do something
        if (countdown <= 0) {
            if (win) {
                z().roomGoto("debug/roomTitle");
            }
            if (lost) {
                z().roomRestart();
            }
        }
    }

    @Override
    public void draw() {
        if (win) {
            z().drawTextHUD("you win!", 100, 100, "emulogic", ZengineColor.C_WHITE);
        }
        if (lost) {
            z().drawTextHUD("you lost!", 100, 100, "emulogic", ZengineColor.C_WHITE);
        }
    }

    @Override
    public void collide(final GameObject other) {
    }

    @Override
    public void mouseClicked(final ZengineMouseConstant button) {
    }

    /**
     * this is called when you win.
     */
    public void youWin() {
        win = true;

    }

    /**
     * this is called when you lost.
     */
    public void youLost() {
        lost = true;
    }

    /**
     * check if win conditions are met
     * 
     * @return
     */
    private boolean checkForVictory() {
        return (z().instanceExists("debug.testgame.game.ObjPlane") && !z().instanceExists("debug.testgame.game.ObjAsteroid"));
    }
}
