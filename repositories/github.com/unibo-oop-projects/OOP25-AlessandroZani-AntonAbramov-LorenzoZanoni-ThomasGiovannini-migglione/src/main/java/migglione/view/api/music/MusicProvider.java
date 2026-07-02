package migglione.view.api.music;

/**
 * Functional interface to return a Strategy.
 * 
 * <p>
 * This interface makes so every scene can return it's
 * implementation of the MusicPlayer, with the music
 * chosen for that scene.
 * 
 * <p>
 * Its true use however is seen in the implementation
 * of SwingViewImpl, since it is responsible for helping
 * choosing the music to play of the new scene and eventually
 * stopping the previous one (check SetScene)
 */
@FunctionalInterface
public interface MusicProvider {
    /**
     * Method used to return the scene's MusicPlayer.
     * 
     * @return the MusicPlayer chosen among its 
     *         implementations for the scene
     */
    MusicPlayer getMusic();
}
