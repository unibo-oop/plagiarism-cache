package zengine.core;

import java.util.HashSet;
import java.util.Set;

import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import zengine.interfaces.GameFunctionsAudio;

final class ZengineAudio implements ZengineComponent, GameFunctionsAudio {
    private static ZengineAudio audio = new ZengineAudio();

    // contains a list of soundNames that ended in the current update()
    private final Set<String> soundEndedList = new HashSet<String>();
    // if the music has ended/stopped in the current update();
    private boolean musicEndd; // = false;
    private boolean musicStoppd; // = false;

    private final GameMusic player = new GameMusic();

    // last music played
    private String currentMusic = "";

    private ZengineAudio() {
    }

    public static ZengineAudio getAudio() {
        return audio;
    }

    @Override
    public ZengineComponent getComponent() {
        return audio;
    }

    @Override
    public void initialize() {
        // nothing to do
    }

    @Override
    public void reinitialize() {
        restoreDefaultValues();
    }

    @Override
    public void link() {
        // build some refs? maybe in future
    }

    @Override
    public void update() {
        audioMapUpdate();
    }

    @Override
    public void restoreDefaultValues() {
        playerStop();
        currentMusic = "";
        audioMapUpdate();
    }

    private void audioMapUpdate() {
        musicEndd = false;
        musicStoppd = false;
        soundEndedList.clear();
    }

    @Override
    public void soundPlay(final String soundName) {
        playWAV(soundName);
    }

    @Override
    public void soundStop(final String soundName) {
        clearWAV(soundName);
    }

    @Override
    public void musicPlay(final String musicName) {
        if (ZengineAssets.getAssets().musicExists(musicName)) {
            playerPlay(musicName);
        } else {
            ZengineLogger.getLogger().loggerWarning("warning: trying to play nonexistent music " + musicName);
        }
    }

    @Override
    public void musicStop() {
        playerStop();
    }

    private boolean playerIsPlaying() {
        // checks if the player is playing
        return player.getStatus() == BasicPlayer.PLAYING;
    }

    private boolean playerPlay(final String musicName) {
        // tells the player to load and play the given music
        boolean ret = false;
        final String fullName = "/" + Zengine.USR_MUSIC_PATH + musicName + Zengine.USR_MUSIC_EXT;
        try {
            playerStop();
            player.open(fullName);
            player.play();

            currentMusic = musicName;
            ret = true;
        } catch (BasicPlayerException e) {
            ret = false;
            ZengineLogger.getLogger().loggerWarning("malformed or invalid file " + fullName);
            e.printStackTrace();
        }
        return ret;
    }

    private void playerStop() {
        // stops everything from the player
        if (playerIsPlaying()) {
            try {
                player.stop();
                musicStopped();
            } catch (BasicPlayerException e) {
                ZengineLogger.getLogger().loggerWarning("warning: could not stop current music");
                e.printStackTrace();
            }
        }
    }

    private void playWAV(final String name) {
        // loads and plays a wav indicated by fullName
        if (soundExists(name)) {
            // soundGet() does not return null if I already check if
            // soundExists()
            ZengineAssets.getAssets().soundGet(name).play();
        } else {
            ZengineLogger.getLogger().loggerWarning("warning: trying to play nonexistent sound " + name);
        }
    }

    // should have been package private, but public is fine as well since the
    // class is package private
    public void soundEnded(final String soundName) {
        // this is called when the sound described by that name has
        // ended
        soundEndedList.add(soundName);
    }

    private void musicStopped() {
        // this is called when the current music stops
        musicStoppd = true;
    }

    // called by GameMusic
    // should have been package private, but public is fine as well since the
    // class is package private
    public void musicEnded() {
        // this is called when the current music ends naturally
        musicEndd = true;
    }

    private void clearWAV(final String soundName) {
        // stops and clears all wavs with the given name
        // does not trigger soundhasEnded
        ZengineAssets.getAssets().stopAllSounds(soundName);
    }

    private boolean soundExists(final String soundName) {
        return ZengineAssets.getAssets().soundExists(soundName);
    }

    @Override
    public boolean soundIsPlaying(final String soundName) {
        if (soundExists(soundName)) {
            return soundNumber(soundName) > 0;
        } else {
            ZengineLogger.getLogger().loggerWarning("warning: nonexistent sound " + soundName);
            return false;
        }
    }

    @Override
    public boolean musicIsPlaying(final String musicName) {
        if (musicName.equals("")) {
            return false;
        } else {
            return (musicName.equals(currentMusic) && playerIsPlaying());
        }
    }

    @Override
    public int soundNumber(final String soundName) {
        if (soundExists(soundName)) {
            return ZengineAssets.getAssets().numberOfPlayingSounds(soundName);
        } else {
            ZengineLogger.getLogger().loggerWarning("warning: nonexistent sound " + soundName);
            return 0;
        }
    }

    @Override
    public String musicCurrent() {
        if (playerIsPlaying()) {
            return currentMusic;
        } else {
            return "";
        }
    }

    @Override
    public String musicLast() {
        return currentMusic;
    }

    @Override
    public boolean soundHasEnded(final String soundName) {
        return soundEndedList.contains(soundName);
    }

    @Override
    public boolean musicIsPlaying() {
        return playerIsPlaying();
    }

    @Override
    public boolean musicHasStopped() {
        return musicStoppd;
    }

    @Override
    public boolean musicHasEnded() {
        return musicEndd;
    }
}
