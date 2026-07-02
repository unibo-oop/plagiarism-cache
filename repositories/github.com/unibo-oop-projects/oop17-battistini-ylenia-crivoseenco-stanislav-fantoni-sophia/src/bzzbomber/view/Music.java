package bzzbomber.view;

import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * This class set game's Music.
 *
 */
public class Music {

    private Clip clip;
    private Clip win;
    private Clip lose;
    private boolean isOn;
    private boolean musicON;

    /**
     * The constructor create AudioInputStream for all game's song .
     * 
     */
    public Music() {
        final AudioInputStream audio;
        final AudioInputStream audioWin;
        final AudioInputStream audioLose;

        try {
            audio = AudioSystem.getAudioInputStream(this.getClass().getResource("/preferences/buono.wav"));
            audioLose = AudioSystem.getAudioInputStream(this.getClass().getResource("/preferences/LoseSound.wav"));
            audioWin = AudioSystem.getAudioInputStream(this.getClass().getResource("/preferences/WinSound.wav"));
            try {
                this.clip = AudioSystem.getClip();
                this.clip.open(audio);
                this.win = AudioSystem.getClip();
                this.win.open(audioWin);
                this.lose = AudioSystem.getClip();
                this.lose.open(audioLose);

            } catch (LineUnavailableException e1) {
                e1.printStackTrace();
            }
        } catch (UnsupportedAudioFileException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    /**
     * This method'll played general music .
     */
    public void musicPlay() {
        this.clip.start();
        this.isOn = true;
        this.musicON = true;
        this.clip.loop(1);
    }

    /**
     * This method'll stopped general music .
     */
    public void musicStop() {
        if (this.isOn) {
            this.clip.stop();
            this.isOn = false;
            this.musicON = false;
            this.clip.setFramePosition(0);
        }

    }

    /**
     * This method'll played lose music .
     */
    public void musicLoose() {
        if (this.musicON) {
            if (this.isOn) {
                this.clip.stop();
                this.isOn = false;

            }
            this.lose.setFramePosition(0);
            this.lose.start();

        }

    }

    /**
     * This method'll played win music .
     */
    public void musicWin() {
        if (this.musicON) {
            if (this.isOn) {
                this.clip.stop();
                this.isOn = false;
            }
            this.win.setFramePosition(0);
            this.win.start();
        }
    }

    /**
     * This method set parameter musicOn .
     */
    public void setMusicOn() {
        this.musicON = false;

    }

    /**
     * This method stop all song .
     */
    public void stopAll() {
        this.clip.stop();
        this.lose.stop();
        this.win.stop();
        this.isOn = false;

    }

    /**
     * This method stop all songs .
     */
    public void restart() {
        if (this.musicON) {
            this.clip.start();

        } else {
            this.stopAll();
            this.musicON = false;
        }
    }
}
