package vg.sound.manager.effect;

import vg.sound.Sound;
import vg.sound.factory.StaticFactorySoundEffect;
import vg.sound.manager.ESoundEffect;

import java.util.HashMap;
import java.util.Map;

public class SoundEffectManagerImpl implements SoundEffectManager {
    private final Map<ESoundEffect, Sound> mapEffect;
    public SoundEffectManagerImpl() {
        this.mapEffect = new HashMap<>();
        this.mapEffect.put(ESoundEffect.GAMEOVER, StaticFactorySoundEffect.createGameOver());
        this.mapEffect.put(ESoundEffect.PICK_BOX, StaticFactorySoundEffect.createPickUp());
        this.mapEffect.put(ESoundEffect.CLOSE_BORDER, StaticFactorySoundEffect.createCloseBoard());
    }

    @Override
    public void play(final ESoundEffect effect) {
        this.mapEffect.get(effect).play();
    }
}
