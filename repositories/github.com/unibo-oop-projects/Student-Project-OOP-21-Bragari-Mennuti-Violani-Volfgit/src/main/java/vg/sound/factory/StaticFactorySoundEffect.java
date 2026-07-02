package vg.sound.factory;

import vg.sound.Sound;
import vg.sound.SoundEffectImpl;
import vg.utils.path.PathSound;

public class StaticFactorySoundEffect {

    public static Sound createGameOver() {
        return new SoundEffectImpl(PathSound.GAMEOVER);
    }

    public static Sound createPickUp() {
        return new SoundEffectImpl(PathSound.PICK_UP_BOX);
    }

    public static Sound createCloseBoard() {
        return new SoundEffectImpl(PathSound.CLOSE_BOARD);
    }
}
