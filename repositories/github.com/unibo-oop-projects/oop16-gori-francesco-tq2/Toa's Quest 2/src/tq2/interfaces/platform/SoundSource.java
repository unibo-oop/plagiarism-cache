package tq2.interfaces.platform;

import tq2.interfaces.Entity;
import tq2.interfaces.Sound;

/**
 * The Interface SoundSource is meant for Entities that can produce sounds.
 * 
 * @author Francesco Gori
 */

public interface SoundSource extends Entity {

	/**
	 * Play sound.
	 *
	 * @param sound the sound
	 */
	public abstract void playSound(Sound sound);

	/**
	 * Play the sound that corresponds to the specified name.
	 *
	 * @param soundName the name of the sound 
	 */
	public abstract void playSound(String soundName);

	/**
	 * Loop the sound that corresponds to the specified name, for the specified number of times.
	 *
	 * @param soundName the name of the sound
	 * @param loops the number of loops
	 */
	public abstract void loopSound(String soundName, Integer loops);

	/**
	 * Loop the sound that corresponds to the specified name until stopped.
	 *
	 * @param soundName the name of the sound
	 */
	public abstract void loopSound(String soundName);

	/**
	 * Stop the sound that corresponds to the specified name until stopped.
	 *
	 * @param soundName the sound name
	 */
	public abstract void stopSound(String soundName);

	/**
	 * Play an instance of the sound that corresponds to the specified name. The instance will close itself when it's over.
	 *
	 * @param soundName the sound name
	 */
	public abstract void playSoundInstance(String soundName);
}
