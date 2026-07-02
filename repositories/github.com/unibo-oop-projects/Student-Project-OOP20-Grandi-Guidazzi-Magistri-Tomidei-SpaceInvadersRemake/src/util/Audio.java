package util;

/**
 * An interface that is used to control all audio aspects.
 */
public interface Audio {
	
	/**
	 * A method that starts the audio tracks.
	 * 
	 * @param music is the track ready to start.
	 * @param loop is used to repeat the sound or not.
	 */
	void play(AudioTrack music, boolean loop);
	
	/**
	 * A method that stops the sound.
	 */
	void stop();
	
	/**
	 * A method by which can change the volume level.
	 * 
	 * @param volume is the volume of a new audio track's volume.
	 */
	void setVolume(float volume);
	
	/**
	 * @return the volume of an audio track.
	 */
	float getVolume();
	
	/**
	 * 
	 * @return if the music is playing
	 */
	boolean isPlaying();
}
