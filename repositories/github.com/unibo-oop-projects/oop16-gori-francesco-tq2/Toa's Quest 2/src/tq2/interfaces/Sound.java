package tq2.interfaces;

/**
 * The Interface Sound.
 * 
 * @author Francesco Gori
 */
public interface Sound {

	/**
	 * Resets the sound clip.
	 */
	public abstract void reset();

	/**
	 * Plays the sound clip.
	 */
	public abstract void play();

	/**
	 * Plays an instance of the sound clip which will close when over.
	 */
	public abstract void playInstance();

	/**
	 * Loops the sound clip for the specified number of times, from the specified
	 * frame to the specified .
	 *
	 * @param loops the number of loops (0 will loop continuously)
	 * @param firstFrame the first frame of the loop
	 * @param lastFrame the last frame of the loop
	 */
	public abstract void loop(Integer loops, Integer firstFrame, Integer lastFrame);

	/**
	 * Loops the sound clip for the specified number of times.
	 *
	 * @param loops the number of loops
	 */
	public abstract void loop(Integer loops);

	/**
	 * Loops the sound clip until stopped.
	 */
	public abstract void loop();

	/**
	 * Closes the sound clip, releasing its resources.
	 */
	public abstract void close();

	/**
	 * Stops the sound clip.
	 */
	public abstract void stop();

	/**
	 * Gets the path of the sound contained in this object.
	 *
	 * @return the path
	 */
	public abstract String getPath();

}