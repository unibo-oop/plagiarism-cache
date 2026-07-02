package vg.sound;

public class SoundBackgroundImpl extends AbstractSound implements Sound {

    public SoundBackgroundImpl(final String pathSound) {
        super(pathSound);
    }

    @Override
    public void play() {
        super.playLoop();
    }
}
