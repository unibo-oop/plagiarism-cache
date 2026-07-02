package bubbleshooter.controller.sound;

import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


/**
 * The Class which manage the Sound feature of the Game.
 */
public class SoundManager {

    private final Clip clip;
    private int framePos;

    /**
     * In the constructor are loaded the resources of the sounds.
     * @throws LineUnavailableException 
     * @throws IOException 
     * @throws UnsupportedAudioFileException 
     */
    public SoundManager() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        this.clip = AudioSystem.getClip();
        this.framePos = 0;
        this.loadGameSounds();
    }

    /**
     * Method used to load the resources and to initialize the MediaPlayer.
     * @throws IOException 
     * @throws UnsupportedAudioFileException 
     * @throws LineUnavailableException 
     */
    private void loadGameSounds() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.clip.open(AudioSystem.getAudioInputStream(getClass().getResource(SoundNames.BACKGROUND.getPath())));
    }

    /**
     * Method used to start the Background music in the game.
     */
    public final void startBackgroundSound() {
        this.clip.setFramePosition(this.framePos);
        this.clip.loop(Clip.LOOP_CONTINUOUSLY);
        this.clip.start();
     }

    /**
     * Method used to stop the Background music in the game.
     */
    public final void stopBackgroundSound() {
        this.clip.stop();
        this.framePos = 0;
    }

    /**
     * Method used to pause the Background music in the game.
     */
    public final void pauseBackgroundSound() {
        this.framePos = this.clip.getFramePosition();
        this.clip.stop();
    }

    /**
     * Method used to resume the Background music in the game.
     */
    public final void resumeSound() {
        this.clip.setFramePosition(this.framePos);
        this.clip.start();
    }
}
