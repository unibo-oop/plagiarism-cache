package user.menu;

import zengine.constants.ZengineMouseConstant;
import zengine.core.GameObject;

/**
 * This class represent the music looper object; this object just restart the
 * music when it ends.
 */
public class MusicLooper extends GameObject {

    @Override
    public void create() {
        // starts the music, if not already playing
        if (z().musicExists(z().musicCurrent()) && !z().musicIsPlaying()) {
            z().musicPlay(z().musicLast());
        }
    }

    @Override
    public void destroy() {
    }

    @Override
    public void update() {
        // when the music ends, play it again
        if (z().musicHasEnded()) {
            z().musicPlay(z().musicLast());
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
