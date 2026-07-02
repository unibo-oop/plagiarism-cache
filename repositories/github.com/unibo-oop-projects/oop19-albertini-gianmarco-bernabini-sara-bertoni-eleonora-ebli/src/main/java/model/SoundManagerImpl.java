package model;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundManagerImpl implements SoundManager {
    private Clip clip;

    public SoundManagerImpl(final String fileName) {
       try {

            final URL soundUrl = ClassLoader.getSystemResource(fileName);
            final AudioInputStream sound = AudioSystem.getAudioInputStream(soundUrl);
            clip = AudioSystem.getClip();
            clip.open(sound);

        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println("Sound: Malformed URL: " + e);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
            System.out.println("Sound: Unsupported Audio File: " + e);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Sound: Input/Output Error: " + e);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            System.out.println("Sound: Line Unavailable Exception Error: " + e);
        }
    }

    /**
    * Plays the effect sound.
    */
    @Override
    public final void play() {
        clip.setFramePosition(0);
        clip.start();
    }

    /**
     * Plays the background music.
     */
    @Override
    public final void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Stops the current clip.
     */
    @Override
    public final void stop() {
        clip.stop();
    }
}
