package it.unibo.roguekong.test;

import it.unibo.roguekong.controller.SoundManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SoundManagerTest {
    SoundManager soundManager;

    @BeforeEach
    public void setup() {
        this.soundManager = new SoundManager("/assets/sound/jump.wav", -20.0f);
    }

    @Test
    public void checkIfSoundManagerIsNotNull() {
        assertNotNull(this.soundManager);
    }

    @Test
    public void checkIsSoundIsHearable() {
        assertTrue(this.soundManager.getVolume() < 0);
    }

    @Test
    public void checkIfSoundManagerIsNotNullAfterPlay() {
        this.soundManager.play();
        assertNotNull(this.soundManager);
    }

    @Test
    public void ClipMustNotBeNullAfterPlay() {
        this.soundManager.play();
        assertNotNull(this.soundManager.getClip());
    }

    @Test
    public void clipMustBeOnPauseAfterPause() {
        this.soundManager.play();
        this.soundManager.stop();
        assertFalse(this.soundManager.getClip().isRunning());
    }
}
