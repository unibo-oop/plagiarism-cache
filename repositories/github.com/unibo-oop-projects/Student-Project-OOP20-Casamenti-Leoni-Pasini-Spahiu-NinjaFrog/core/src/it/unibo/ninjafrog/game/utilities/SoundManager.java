package it.unibo.ninjafrog.game.utilities;

public interface SoundManager {
    /**
     * load the mp3 file in the game.
     */
    void loadSong();

    /**
     * change the state of the music.
     */
    void changeState();

    /**
     * @return the state.
     */
    boolean isState();

    /**
     * play the menu song.
     */
    void playMenuSong();

    /**
     * play the menu song.
     */
    void playGameSong();

    /**
     * stop the game song.
     */
    void pauseGameSong();
}
