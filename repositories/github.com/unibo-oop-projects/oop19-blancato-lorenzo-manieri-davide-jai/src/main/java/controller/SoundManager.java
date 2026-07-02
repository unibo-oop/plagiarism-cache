package controller;


import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * 
 * 
 * Class for sound effects 
 *
 */
public class SoundManager {
	private final static double gain = 0.05;   
	private final static float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
	private static Clip clipFire = null;
	private static Clip clipExplosion = null;
	
	/**
	 * method to initialize, open and adjust volume audioClip
	 * 
	 */
	public static void initSoundManager() {

		try {
			clipFire = AudioSystem.getClip();
			clipFire.open(AudioSystem.getAudioInputStream(ClassLoader.getSystemResource("FireSound.wav")));
			FloatControl volumeFireClip = (FloatControl) clipFire.getControl(FloatControl.Type.MASTER_GAIN);
			volumeFireClip.setValue(dB);
			
			clipExplosion = AudioSystem.getClip();
			clipExplosion.open(AudioSystem.getAudioInputStream(ClassLoader.getSystemResource("Explosion.wav")));
			FloatControl volumeExplosionClip = (FloatControl) clipExplosion.getControl(FloatControl.Type.MASTER_GAIN);
			volumeExplosionClip.setValue(dB);
			
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * method to reset fire clip in order to play it again
	 * 
	 */
	private static void  resetFireClip() {
	
			try {
				clipFire = AudioSystem.getClip();
				clipFire.open(AudioSystem.getAudioInputStream(ClassLoader.getSystemResource("FireSound.wav")));
				FloatControl volumeFireClip = (FloatControl) clipFire.getControl(FloatControl.Type.MASTER_GAIN);
				volumeFireClip.setValue(dB);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * method to reset explosion clip in order to play it again
	 * 
	 */
	private static void  resetExplosionClip() {
		
		try {
			clipExplosion = AudioSystem.getClip();
			clipExplosion.open(AudioSystem.getAudioInputStream(ClassLoader.getSystemResource("Explosion.wav")));
			FloatControl volumeExplosionClip = (FloatControl) clipExplosion.getControl(FloatControl.Type.MASTER_GAIN);
			volumeExplosionClip.setValue(dB);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	
	}
	
	/**
	 * method to play fire clip 
	 * 
	 */
	public static void playFireClip() {
		resetFireClip();
		clipFire.start();
	}
	
	/**
	 * method to play explosion clip 
	 * 
	 */
	public static void playExplosionClip() {
		resetExplosionClip();
		clipExplosion.start();
	}
}
