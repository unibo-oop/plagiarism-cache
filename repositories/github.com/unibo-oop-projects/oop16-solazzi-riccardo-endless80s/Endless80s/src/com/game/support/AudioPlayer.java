package com.game.support;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

public class AudioPlayer {

	private Clip clip;
	public void music() {
		
		try {
		URL url = this.getClass().getClassLoader().getResource("bgNew.wav");
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
			// Get a clip resource.
	         clip = AudioSystem.getClip();
	         // Open audio clip and load samples from the audio input stream.
	         clip.open(audioInputStream);
	      } catch (UnsupportedAudioFileException e) {
	        e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (LineUnavailableException e) {
	         e.printStackTrace();
	      }
		clip.start();
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		
	}
}
