package jAlienInvasion;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.junit.Test;

public class SoundTest {
	
	Clip clip1 = null;
	Clip clip2 = null;
	
	@Test
	public void testFireSound() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		clip1 = AudioSystem.getClip();
		clip1.open(AudioSystem.getAudioInputStream(ClassLoader.getSystemResource("FireSound.wav")));
		assertTrue(clip1.isOpen());	
		
	}
	
	@Test
	public void testExplosionSound() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		clip2 = AudioSystem.getClip();
		clip2.open(AudioSystem.getAudioInputStream(ClassLoader.getSystemResource("Explosion.wav")));
		assertTrue(clip2.isOpen());
	}

}
