package test.paganelli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import spacesurvival.controller.sound.CallerAudio;
import spacesurvival.model.sound.category.SoundLoop;
import spacesurvival.utilities.CommandAudioType;
import spacesurvival.utilities.path.SoundPath;

public class TestCallerAudio {

    /**
     * Test the command on and off executed from the caller.
     */
    @Test
    public void testAudioOnOff() {
        final CallerAudio caller = new CallerAudio(new SoundLoop(SoundPath.GAME));
        caller.execute(CommandAudioType.AUDIO_ON);
        assertTrue(caller.getSound().isPlaying());
        caller.execute(CommandAudioType.AUDIO_OFF);
        assertFalse(caller.getSound().isPlaying());
    }

    /**
     * Test the command reset timing executed from the caller.
     */
    @Test
    public void testResetTiming() {
        final CallerAudio caller = new CallerAudio(new SoundLoop(SoundPath.GAME));
        caller.execute(CommandAudioType.RESET_TIMING);
        final Long currentTiming = caller.getSound().getClip().getMicrosecondPosition();
        final Long expectedTiming = 0L;
        assertEquals(expectedTiming, currentTiming);
    }

    /**
     * Test the command change volume executed from the caller.
     */
    @Test
    public void testChangeVolume() {

        final int testingVolume = 50;
        final CallerAudio caller = new CallerAudio(new SoundLoop(SoundPath.GAME));
        caller.changeVolume(testingVolume);

        Double currentVolume = caller.getSound().getVolume();
        assertEquals(Double.valueOf(0.5), currentVolume);

        caller.changeVolume(100);
        currentVolume = caller.getSound().getVolume();
        assertEquals(Double.valueOf(1.0), currentVolume);
    }
}
