package zengine.core;

import java.io.IOException;
import java.net.URL;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

/**
 * This class is a custom (and less complex) implementation of javazoom's
 * BasicPlayer, which seems to be causing trouble when decoding mp3 files from
 * inside a jar file. This class should work from inside the IDE and the jar
 * independently, if nothing weird happens.
 */
final class GameMusic {

    private String name;

    private volatile int status = BasicPlayer.STOPPED;
    // changes everytime a different music is opened
    private AdvancedPlayer pl;
    // references the thread playing the opened music
    private Thread musicalThread;
    // if already tried to interrupt the musical thread
    private volatile boolean triedToInteruptMusicalThread;

    // if the last music was interrupted by force
    private volatile boolean forcedInterrupt;

    // the same for every player
    private final PlaybackListener listener;

    /**
     * builds a new GameMusic player set to a "ready" state.
     */
    GameMusic() {
        pl = null;
        status = BasicPlayer.STOPPED;
        triedToInteruptMusicalThread = false;
        forcedInterrupt = false;
        name = ""; // just in case

        listener = new PlaybackListener() {
            @Override
            public void playbackFinished(final PlaybackEvent arg0) {
                if (musicalThread != null) {
                    if (forcedInterrupt) {
                        ZengineLogger.getLogger().loggerMessage("stopped music " + musicalThread.getName());
                    } else {
                        ZengineLogger.getLogger().loggerMessage("reached end of music " + musicalThread.getName());
                    }
                }
                status = BasicPlayer.STOPPED;
                pl = null;
                // inform that a music has ended peacefully
                if (!forcedInterrupt) {
                    ZengineAudio.getAudio().musicEnded();
                    forcedInterrupt = false;
                }
                // try to interrupt the musical thread
                tryToInterruptMusicalThread();
            }
        };
    }

    /**
     * returns an integer constant value that describes the status of the
     * player.
     * 
     * @return BasicPlayer.STOPPED if stopped and ready to open a new audio
     *         file, BasicPlayer.OPENED if an audio file is loaded and ready to
     *         be played, BasicPlayer.PLAYING if an audio file is being played.
     */
    public int getStatus() {
        return status;
    }

    /**
     * Loads a music into the player. The music must be opened before it can be
     * played or stopped.
     * 
     * @param fullName
     *            full path of the music to be loaded
     * @throws BasicPlayerException
     *             exception that would be thrown by BasicPlayer as well
     */
    public void open(final String fullName) throws BasicPlayerException {
        if (ZengineAssets.getAssets().pathExists(fullName)) {
            final URL url = getClass().getResource(fullName);
            try {
                if (pl != null) {
                    pl.close();
                }
                // refresh pl with a new player loaded with another url
                pl = new AdvancedPlayer(url.openStream());
                pl.setPlayBackListener(listener);
                status = BasicPlayer.OPENED;
                name = fullName;
                ZengineLogger.getLogger().loggerMessage("opened music " + fullName);
            } catch (JavaLayerException e) {
                throw new BasicPlayerException("JavaLayerException detected for " + fullName);
            } catch (IOException e) {
                throw new BasicPlayerException("IOException detected for " + fullName);
            }
        } else {
            ZengineLogger.getLogger().loggerWarning("cannot open nonexistent music " + fullName);
        }
    }

    /**
     * Once opened, this methods tells the player to start reproducing the audio
     * in a separate thread.
     * 
     * @throws BasicPlayerException
     *             exception that would be thrown by BasicPlayer as well
     */
    public void play() throws BasicPlayerException {
        if (pl != null && status == BasicPlayer.OPENED) {
            musicalThread = new Thread() {
                @Override
                public void run() {
                    try {
                        forcedInterrupt = false;
                        status = BasicPlayer.PLAYING;
                        triedToInteruptMusicalThread = false;
                        // blocking action, do everything before this
                        pl.play();
                    } catch (JavaLayerException e) {
                        e.printStackTrace();
                    }
                }
            };
            musicalThread.setName(name);
            musicalThread.start();
            ZengineLogger.getLogger().loggerMessage("musical thread started, playing music");
        } else {
            ZengineLogger.getLogger().loggerWarning("unable to playback music: open a music first or stop the current one");
        }
    }

    /**
     * Stops the player from playing any music that is currently playing. If no
     * music is playing, nothing happens.
     * 
     * @throws BasicPlayerException
     *             exception that would be thrown by BasicPlayer as well
     */
    public void stop() throws BasicPlayerException {
        if (pl != null && status == BasicPlayer.PLAYING) {
            forcedInterrupt = true;
            pl.stop();
            // pl.close();
            status = BasicPlayer.STOPPED;
            tryToInterruptMusicalThread();
        }
    }

    /**
     * tries to interrupt the musical thread, returns true if successfull.
     * 
     * @return true if the musical thread was interrupted
     */
    private boolean tryToInterruptMusicalThread() {
        if (musicalThread != null) {
            try {
                if (!musicalThread.isInterrupted() && !triedToInteruptMusicalThread) {
                    triedToInteruptMusicalThread = true;
                    musicalThread.interrupt();
                    ZengineLogger.getLogger().loggerMessage("musical thread interrupted");
                    return true;
                }
            } catch (SecurityException e) {
                return false;
            }
        } else {
            return false;
        }
        return false;
    }
}
