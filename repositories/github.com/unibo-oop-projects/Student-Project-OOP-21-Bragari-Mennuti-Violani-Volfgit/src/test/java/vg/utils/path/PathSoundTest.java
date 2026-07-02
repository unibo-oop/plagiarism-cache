package vg.utils.path;

import static org.junit.jupiter.api.Assertions.*;

class PathSoundTest {

    @org.junit.jupiter.api.Test
    void getPath() {
        assertEquals("sound/gameover.wav", PathSound.GAMEOVER);
        assertEquals("sound/box.wav", PathSound.PICK_UP_BOX);
        assertEquals("sound/changing.wav", PathSound.CLOSE_BOARD);
    }
}