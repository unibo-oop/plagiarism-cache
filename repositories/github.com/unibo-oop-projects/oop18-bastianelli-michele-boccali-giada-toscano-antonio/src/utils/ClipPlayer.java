package utils;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

import common.CommonStrings;

public final class ClipPlayer {

    private ClipPlayer() {
    }

    public static void playClip(final String filename) {
        try {
            final Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(CommonStrings.MUSIC_PATH + filename)));
            clip.setFramePosition(0);
            clip.start();
            clip.addLineListener(new LineListener() {

                @Override
                public void update(final LineEvent event) {
                    if (event.getType().equals(LineEvent.Type.CLOSE)) {
                        clip.close();
                    }
                }
            });

        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }

}
