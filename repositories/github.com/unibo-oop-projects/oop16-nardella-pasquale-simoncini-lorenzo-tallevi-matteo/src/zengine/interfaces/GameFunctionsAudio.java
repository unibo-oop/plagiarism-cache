package zengine.interfaces;

/**
 * This interface represents the Zengine component that handles audio. It
 * contains methods that allow to play and stop sound effects and music.
 */
public interface GameFunctionsAudio {

    // difference between sound and music is that multiple musics cannot be
    // played at the same time.

    /**
     * Plays the given sound effect.
     * 
     * @param soundName
     *            name of the sound effect to be played
     */
    void soundPlay(String soundName);

    /**
     * stops every instance of the given sound effect from playing.
     * 
     * @param soundName
     *            the sound effect to be stopped
     */
    void soundStop(String soundName);

    /**
     * plays the given music. It also stops every other music currently playing
     * (not the sound effects).
     * 
     * @param musicName
     *            name of the music to be played
     */
    void musicPlay(String musicName);

    /**
     * stops the current music from playing, if it's playing.
     */
    void musicStop();

    /**
     * returns true if at least one instance of the given sound is being played.
     * 
     * @param soundName
     *            name of the sound to check
     * @return true if at least one sound is playing
     */
    boolean soundIsPlaying(String soundName);

    /**
     * returns true if the given music is currently being played.
     * 
     * @param musicName
     *            name of the music to check
     * @return true if the music is currently playing
     */
    boolean musicIsPlaying(String musicName);

    /**
     * returns true if some music is currently being played.
     * 
     * @return true if some music is currently being played
     */
    boolean musicIsPlaying();

    /**
     * counts how many instances of the given sound are actually being played.
     * 
     * @param soundName
     *            name of the sound to check
     * @return how many times the sound is being played
     */
    int soundNumber(String soundName);

    /**
     * returns the name of the music currently being played, or an empty string
     * if no music is being played.
     * 
     * @return the name of the music currently being played, or an empty string
     *         if no music is being played
     */
    String musicCurrent();

    /**
     * returns the name of the most recently played music, or an empty string if
     * no music has been played yet.
     * 
     * @return the name of the most recently played music, or an empty string if
     *         no music has been played yet
     */
    String musicLast();

    /**
     * returns true only in the instant when at least one of the given sound
     * effect ended without being interrupted.
     * 
     * @param soundName
     *            name of the sound to check
     * @return true during the quantum of time when at least one sound stopped
     *         playing
     */
    boolean soundHasEnded(String soundName);

    /**
     * returns true only in the instant when the current music stopped playing.
     * This happens every time the music is stopped manually with musicStop() or
     * it stopped because another one started playing.
     * 
     * @return true during the quantum of time when the current music stopped
     *         playing
     */
    boolean musicHasStopped();

    /**
     * returns true only in the instant when the current music ended naturally.
     * This happens when the music ended without being interrupted. This is
     * useful if you want to know when the current music has ended, so you can
     * replay it again or play a new one.
     * 
     * @return true during the quantum of time when the current music ended
     */
    boolean musicHasEnded();
}
