package migglione.view.api.music;

/**
 * Interface used to to apply the Strategy method.
 * 
 * <p>
 * This way, different types of music can be played and also
 * the way it behaves can be changed in the different scenes,
 * upholding to the Strategy method.
 */
public interface MusicPlayer {
    /**
     * Functional method to start the music.
     */
    void playMusic();

    /**
     * Functional method to stop the music.
     */
    void stopMusic();

    /**
     * Getter for the path of the track.
     * 
     * @return the String rapresenting the path
     */
    String getPath();
}
