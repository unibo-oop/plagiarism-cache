package user.debug.testgame;

import zengine.constants.ZengineMouseConstant;
import zengine.core.GameObject;

/**
 * this class is responsible for playing MUSIC within levels and menus. When
 * created, it starts the MUSIC. While alive, it keeps the MUSIC playing
 */
public class ObjMusicLooper extends GameObject {

    // name of the only MUSIC to play
    private static final String MUSIC = "YMCK - 52 Futures";

    @Override
    public void create() {
        // starts the MUSIC, if not already playing
        if (!z().musicIsPlaying(MUSIC)) {
            z().musicPlay(MUSIC);
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public void update() {
        // when the MUSIC ends, play it again
        if (z().musicHasEnded()) {
            z().musicPlay(MUSIC);
        }
    }

    @Override
    public void draw() {
        // i am invisible
    }

    @Override
    public void collide(final GameObject other) {
    }

    @Override
    public void mouseClicked(final ZengineMouseConstant button) {
    }

}
