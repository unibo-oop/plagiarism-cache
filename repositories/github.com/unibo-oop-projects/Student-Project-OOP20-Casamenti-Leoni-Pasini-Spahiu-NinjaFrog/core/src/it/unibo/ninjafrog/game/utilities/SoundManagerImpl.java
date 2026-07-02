package it.unibo.ninjafrog.game.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.Disposable;

public final class SoundManagerImpl implements SoundManager, Disposable {

    private static final float MUSIC_VOL = 0.1f;
    private Music musicMenu;
    private Music musicGame;
    private boolean state;

    /**
     * public constructor of the sound manager.
     */
    public SoundManagerImpl() {
        this.state = true;
        loadSong();
    }

    @Override
    public void loadSong() {
        musicMenu = (Music) Gdx.audio.newMusic(Gdx.files.internal("introSong.mp3"));
        musicGame = (Music) Gdx.audio.newMusic(Gdx.files.internal("playSong.mp3"));
    }

    @Override
    public void changeState() {
        if (state) {
            this.state = false;
            musicGame.stop();
            musicMenu.stop();
        } else {
            this.state = true;
            playMenuSong();
        }
    }

    @Override
    public void playMenuSong() {
        if (state) {
            musicGame.stop();
            musicMenu.setVolume(MUSIC_VOL);
            musicMenu.play();
            musicMenu.setLooping(true);
        }
    }

    @Override
    public void playGameSong() {
        if (state) {
            musicMenu.stop();
            musicGame.setVolume(MUSIC_VOL);
            musicGame.play();
            musicGame.setLooping(true);
        }
    }

    @Override
    public void dispose() {
        musicMenu.dispose();
        musicGame.dispose();
    }

    @Override
    public boolean isState() {
        return this.state;
    }

    @Override
    public void pauseGameSong() {
        this.musicGame.pause();
    }
}
