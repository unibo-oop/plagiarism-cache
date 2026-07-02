package zombieversity.controller.sound;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Implementation of {@link SoundController}.
 */
public class SoundControllerImpl implements SoundController {

    private static final String MEDIA_DIR = "/media/";
    private static final String DEFAULT = "background.mp3";
    private final Map<String, MediaPlayer> sounds = new HashMap<>();

    /**
     * Constructs a {@link SoundController}.
     */
    public SoundControllerImpl() {
        this.createSound(DEFAULT);
    }

    /**
     * 
     * @param name
     * @return
     */
    private String getNameNoExtension(final String name) {
        return name.substring(0, name.indexOf('.'));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean checkIsMedia(final String name) {
        return name.contains(".m4a") || name.contains(".mp3") || name.contains(".wav");
    }

    /**
     * {@inheritDoc}
     */
    @Override

    public final void addSound(final String name, final String url) {
        this.sounds.put(name, new MediaPlayer(this.loadSound(url)));
    }

    /**
     * {@inheritDoc}
     */
    @Override

    public final void stopGeneral() {
        sounds.forEach((k, v) -> v.pause());
    }

    /**
     * {@inheritDoc}
     */
    @Override

    public final void startGeneral() {
        sounds.forEach((k, v) -> v.play());
    }

    private void createSound(final String f) {
        final String name = getNameNoExtension(f);
        this.sounds.put(name, new MediaPlayer(this.loadSound(MEDIA_DIR + f)));
    }

    /**
     * {@inheritDoc}
     */
    @Override

    public final void startSound(final String name) {
        this.sounds.get(name).play();
    }

    /**
     * {@inheritDoc}
     */
    @Override

    public final void stopSound(final String name) {
        this.sounds.get(name).stop();
    }

    private Media loadSound(final String url) {
        try {
            return new Media(getClass().getResource(url).toURI().toString());
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void addSound(final String name, final Media media) {
        this.sounds.put(name, new MediaPlayer(media));
    }
}
