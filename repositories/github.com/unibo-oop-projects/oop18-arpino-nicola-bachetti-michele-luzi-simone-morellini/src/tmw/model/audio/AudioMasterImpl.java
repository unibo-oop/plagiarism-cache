package tmw.model.audio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

/**
 * AudioMaster implementation class. {@link AudioMaster}.
 * 
 * @version 1.3
 */

public class AudioMasterImpl implements AudioMaster {

    private static final double MINVOLUME = 0.00;
    private static final double MAXVOLUME = 1.00;
    private static Optional<MediaPlayer> player = Optional.empty();
    private Optional<Double> currentVolume;
    private final List<AudioClip> currentSFX = new ArrayList<AudioClip>();

    /**
     * Public constructor. 
     */
    public AudioMasterImpl() {
            initialize();
    }

    @Override
    public final void initialize() {

        this.currentVolume = Optional.of(MINVOLUME);
        this.playBackMusic(AudioTracks.MAINMENU_TRACK);
        this.setVoulme(MINVOLUME);
        player.get().setCycleCount(MediaPlayer.INDEFINITE);
        player.get().play();
    }

    @Override
    public final void setVoulme(final double value) {

        if (!(value < MINVOLUME || value > MAXVOLUME)) {
            this.currentVolume = Optional.ofNullable(value);
            player.get().setVolume(value);
        }
    }

    @Override
    public final void stopPlaying() {

        if (player.isPresent()) {
            if (!currentSFX.isEmpty()) {
                currentSFX.forEach(e -> e.stop());
            }
            player.get().stop();
        }
    }

    @Override
    public final void pauseAll() {

        if (player.isPresent()) {
            if (!currentSFX.isEmpty()) {
                currentSFX.forEach(e -> e.stop());
            }
            player.get().pause();
        }
    }

    @Override
    public final void resumePlaying() {

        if (player.isPresent()) {
            player.get().play();
        }
    }

    @Override
    public final void playSFX(final AudioSfx sound) {
        new AudioClip(sound.getPath()).play(currentVolume.get());
    }

    @Override
    public final void run() {
        initialize();
    }

    @Override
    public final void playBackMusic(final AudioTracks track) {
        stopPlaying();
        player = Optional.of(new MediaPlayer(new Media(track.getPath())));
        player.get().play();
    }

    @Override
    public final boolean isPlaying() {
        if (player.isPresent()) {
            return player.get().getStatus() == Status.PLAYING ? true : false;
        }
        return false;
    }

    @Override
    public final void mutePlayer() {
        player.get().setMute(true);
    }

    @Override
    public final void unMute() {
        if (player.isPresent() || (player.get().isMute())) {
            player.get().setMute(false);

        }
    }

    @Override
    public final MediaPlayer getPlayer() {
        return player.orElse(null);
    }
}

