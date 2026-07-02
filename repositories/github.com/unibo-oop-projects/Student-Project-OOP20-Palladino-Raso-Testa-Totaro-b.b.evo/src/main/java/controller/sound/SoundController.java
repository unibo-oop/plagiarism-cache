package controller.sound;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineEvent.Type;

import controller.utilities.IOSettings;
import model.utilities.GameUtilities;
/**
 * This class define an easy method to play effect or music in the game.*/
public final class SoundController {

    private static Clip clip;
    private static boolean clipIsActived;
    private static boolean canPermiseMusic = IOSettings.readSettings(GameUtilities.SETTINGS_PATH).isEnableMusic();
    private static boolean canPermiseFX = IOSettings.readSettings(GameUtilities.SETTINGS_PATH).isEnableSoundFx();


    private SoundController() {

    }

    public static void playSoundFx(final URL path) {
        if (canPermiseFX) {
            try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(path)) {
                final Clip effectClip = AudioSystem.getClip();
                effectClip.open(audioIn);
                effectClip.start();
                effectClip.addLineListener(new LineListener() {
                    @Override
                    public void update(final LineEvent event) {
                        if (event.getType().equals(Type.STOP)) {
                            effectClip.close();
                        }
                    }
                });
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }
    }

    public static void stopFx() {
        canPermiseFX = false;
    }

    public static void enableSoundFx() {
        canPermiseFX = true;
    }

    public static void playMusic(final URL url) {
            try {
                    if (!clipIsActived && canPermiseMusic)  {
                        //final File musicPath = new File(url);
                        final var audio = AudioSystem.getAudioInputStream(url);
                        clip = AudioSystem.getClip();
                        clip.open(audio);
                        clip.start();
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                        clipIsActived = true;
                    }


            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
    }

    public static void playSound(final URL path) {
        try {
            if (!clipIsActived && canPermiseMusic)  {
                //final File musicPath = new File(path);
                final var audio = AudioSystem.getAudioInputStream(path);
                clip = AudioSystem.getClip();
                clip.open(audio);
                clip.start();
                clipIsActived = true;
            }
    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
        e.printStackTrace();
    }
}

    public static void stopMusic() {
        if (clipIsActived) {
            clip.stop();
        }
        clipIsActived = false;
    }

    public static void enableMusic() {
        canPermiseMusic = true;
    }

    public static void disableMusic() {
        canPermiseMusic = false;
    }

}
