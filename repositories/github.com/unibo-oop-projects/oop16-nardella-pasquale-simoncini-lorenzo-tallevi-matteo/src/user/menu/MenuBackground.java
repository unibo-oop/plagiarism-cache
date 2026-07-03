package user.menu;

import user.menu.stagelevel.StageBackground;

/**
 * This class represent the common background for the game menus rooms.
 */
public class MenuBackground extends StageBackground {
    private static final int SHIFT = 150;

    @Override
    public void update() {
        // shifts the position based on the mouse cursor and the window
        setX(-SHIFT / 2 + ((double) z().windowMouseX() / z().windowGetWidth()) * SHIFT);
        setY(-SHIFT / 2 + ((double) z().windowMouseY() / z().windowGetHeight()) * SHIFT);
    }
}
