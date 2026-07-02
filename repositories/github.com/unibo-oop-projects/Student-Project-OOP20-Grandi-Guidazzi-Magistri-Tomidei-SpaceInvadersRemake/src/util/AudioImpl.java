package util;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;



public class AudioImpl implements Audio{
	
	private Clip clip;
	private AudioInputStream audioIn;
	private float gain;
	private float volume;
	private boolean isPlaying = false;

	/**
	 * The constructor creates a new Audio for each audio track.
	 */
    public AudioImpl() {
		this.volume = Constants.AudioConstants.VOLUME_LEVEL_START;

    }
    
	@Override
	public void play(AudioTrack music, boolean inLoop) {
		this.isPlaying = true;
			try {
				audioIn =AudioSystem.getAudioInputStream(ClassLoader.getSystemResource(music.getPath()));
				this.clip = AudioSystem.getClip();
				this.clip.open(audioIn);
				this.clip.start();
				this.setVolume(this.volume);
				if(inLoop) {
					this.clip.loop(Clip.LOOP_CONTINUOUSLY);
				}
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				System.out.println("error in the sound files");
			}	
	}

	@Override
	public void stop() {
		if(this.isPlaying) {
			this.clip.stop();
		}
	}

	@Override
	public void setVolume(float volume) {
			FloatControl floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			float range = floatControl.getMaximum() - floatControl.getMinimum();
			this.gain = (range * volume) + floatControl.getMinimum();
			this.volume = volume;
			floatControl.setValue(gain);
	}

	@Override
	public float getVolume() {
		return this.volume;
	}

	@Override
	public boolean isPlaying() {
		return this.isPlaying;
	}
	
}
