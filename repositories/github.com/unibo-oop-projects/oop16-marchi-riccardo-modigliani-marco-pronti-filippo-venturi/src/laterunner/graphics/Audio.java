package laterunner.graphics;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Audio is an utility class that provides methods to play music during the game.
 *
 */
public class Audio {

    private Clip clip;

/**
 * Common Audio constructor.
 */
   public Audio() {

      try {
         URL url = this.getClass().getClassLoader().getResource("LateRunner_Theme.wav");
         AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
         clip = AudioSystem.getClip();
         clip.open(audioIn);
      } catch (UnsupportedAudioFileException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (LineUnavailableException e) {
         e.printStackTrace();
      }
   }

   /**
    * Method to play audio.
    */
   public void play() {
       clip.loop(Clip.LOOP_CONTINUOUSLY);
   }

   /**
    * Method to stop audio.
    */
   public void stop() {
       clip.stop();
       clip.setFramePosition(0);
   }

}
