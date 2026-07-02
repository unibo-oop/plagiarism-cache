package util;

/**
 *this interface was created beacuse in future this player will be able to play not only mp3 songs
 *but other type of music file
 */

public interface IPlayer {
	
	/**
	 * play song from path
	 * @param songPath String
	 */
	public void play(String songPath);
	
	/**
	 * pause song
	 */
	public void pause();
	/**
	 * resume song
	 */
	public void resume();

}
