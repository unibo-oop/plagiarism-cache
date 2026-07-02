package it.unibo.oop.supermario.world;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import it.unibo.oop.supermario.character.Mario;
import it.unibo.oop.supermario.gamemanager.GameManager;
import it.unibo.oop.supermario.powerup.Item;
import it.unibo.oop.supermario.screens.Hud;
import it.unibo.oop.supermario.screens.PlayScreen;

/**
 * Class that define Brick object.
 */
public class Brick extends TiledObject implements Item {

    private final AssetManager assetManager;
    private static final int BRICK_SCORE = 100;
    private static final int BREAK_TILE = 16;
    private final Hud hud;

    /**
     * Brick constructor.
     * 
     * @param playScreen Playscreen object
     * @param x          x object's coordinate
     * @param y          y object's coordinate
     * @param obj        object of tiled map
     */
    public Brick(final PlayScreen playScreen, final float x, final float y, final MapObject obj) {
        super(playScreen, x, y, obj);
        hud = playScreen.getHud();
        setCategoryFilter(GameManager.BRICK_BIT);
        getFixture().setUserData(this);
        assetManager = GameManager.instance.getAssetManager();
    }

    @Override
    public final void onCollide(final Mario mario) {
        if (mario.isGrownUp() || mario.isFireMario() && !isToDestroy()) {
            assetManager.get("audio/items/break_block.wav", Sound.class).play();
            cell().setTile(getTileSet().getTile(BREAK_TILE));
            getPS().addBrickToBreak(this);
            queueDestroy();
            hud.addScore(BRICK_SCORE);
        } else {
            assetManager.get("audio/items/bump.wav", Sound.class).play();
        }
    }

    /**
     * Wait 0.1 seconds and set brick tile null.
     */
    public final void waitToSetNull() {
        final float delay = 0.1f;
        final Task destroy = new Task() {
            public void run() {
                cell().setTile(null);
            }
        };
        Timer.schedule(destroy, delay);
    }
}
