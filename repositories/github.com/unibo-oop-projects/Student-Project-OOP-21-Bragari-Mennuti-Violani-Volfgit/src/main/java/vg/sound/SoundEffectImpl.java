package vg.sound;

public class SoundEffectImpl extends AbstractSound implements Sound {
    public SoundEffectImpl(final String pathSound) {
        super(pathSound);
    }

    @Override
    public void play() {
        super.playOne();
    }
}
