package it.unibo.oop.supermario.world;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import it.unibo.oop.supermario.gamemanager.GameManager;

/**
 * 
 * FlagControllerImpl class.
 *
 */
public class FlagControllerImpl implements FlagController {

    private final Flag flagModel;
    private final FlagView flagView;
    private final AssetManager assetManager;
    private boolean clearWorldSound;
    private boolean endGame;

    /**
     * FlagControllerImpl constructor.
     * 
     * @param fModel   flag model
     * @param flagView flag view
     */
    public FlagControllerImpl(final Flag fModel, final FlagView flagView) {
        this.flagModel = fModel;
        this.flagView = flagView;
        this.flagView.setFlag(fModel);
        assetManager = GameManager.instance.getAssetManager();
        clearWorldSound = false;
        endGame = false;
    }

    @Override
    public final void update(final float dt) {
        flagView.update(dt);
    }

    @Override
    public final boolean isFlagFalling() {
        return flagModel.isFlagFalling();
    }

    @Override
    public final void setFlagDown() {
        if (flagView.getY() >= flagView.getFinalY()) {
            flagView.setFlagDown();
        } else {
            flagModel.setFlagFalling(false);
            endGame = true;
            assetManager.get("world_clear.wav", Sound.class).play();
        }
    }

    @Override
    public final void worldClearSound() {
        if (!clearWorldSound) {
            assetManager.get("audio/items/flagpole.wav", Sound.class).play();
            clearWorldSound = true;
        }
    }

    @Override
    public final boolean isEndGame() {
        return endGame;
    }

    @Override
    public final void draw(final Batch batch) {
        flagView.draw(batch);
    }
}
