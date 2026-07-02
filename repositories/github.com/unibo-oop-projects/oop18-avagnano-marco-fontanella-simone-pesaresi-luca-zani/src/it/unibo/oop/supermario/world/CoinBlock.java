package it.unibo.oop.supermario.world;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.MapObject;

import it.unibo.oop.supermario.character.Mario;
import it.unibo.oop.supermario.gamemanager.GameManager;
import it.unibo.oop.supermario.powerup.Flower;
import it.unibo.oop.supermario.powerup.FlowerController;
import it.unibo.oop.supermario.powerup.FlowerView;
import it.unibo.oop.supermario.powerup.Item;
import it.unibo.oop.supermario.powerup.Mushroom;
import it.unibo.oop.supermario.powerup.MushroomController;
import it.unibo.oop.supermario.powerup.MushroomView;
import it.unibo.oop.supermario.screens.Hud;
import it.unibo.oop.supermario.screens.PlayScreen;

/**
 * Class that define CoinBlock.
 */
public class CoinBlock extends TiledObject implements Item {

    private static final int BLANK_COIN = 28;
    private final Hud hud;
    private boolean isPowerUpBlock;
    private boolean isCoinBlock;
    private MushroomController mController;
    private FlowerController fController;
    private CoinController cController;
    private final AssetManager assetManager;
    private static final int COIN_BLOCK_SCORE = 200;
    private static final int X_DISTANCE = 8;
    private static final int Y_MUSHROOM_DISTANCE = 16;
    private static final int Y_FLOWER_DISTANCE = 24;
    private static final int Y_COIN_DISTANCE = 32;

    /**
     * CoinBlock constructor.
     * 
     * @param playScreen Playscreen object
     * @param x          x object's coordinate
     * @param y          y object's coordinate
     * @param obj        object of tiled map
     */
    public CoinBlock(final PlayScreen playScreen, final float x, final float y, final MapObject obj) {
        super(playScreen, x, y, obj);
        this.hud = playScreen.getHud();
        assetManager = GameManager.instance.getAssetManager();
        isPowerUpBlock = false;
        isCoinBlock = false;
        if (obj.getProperties().containsKey("mushroom")) {
            isPowerUpBlock = true;
            mController = new MushroomController(new Mushroom(playScreen, (getPosition().x + X_DISTANCE) / GameManager.PPM,
                    (getPosition().y + Y_MUSHROOM_DISTANCE) / GameManager.PPM), new MushroomView());
            fController = new FlowerController(new Flower(playScreen, (getPosition().x + X_DISTANCE) / GameManager.PPM,
                    (getPosition().y + Y_FLOWER_DISTANCE) / GameManager.PPM), new FlowerView());
            mController.setFController(fController);
            fController.setMController(mController);
        } else {
            isCoinBlock = true;
            cController = new CoinControllerImpl(new Coin(playScreen, (getPosition().x + X_DISTANCE) / GameManager.PPM,
                    (getPosition().y + Y_COIN_DISTANCE) / GameManager.PPM), new CoinView());
        }

        getFixture().setUserData(this);
        setCategoryFilter(GameManager.COIN_BIT);
    }

    @Override
    public final void onCollide(final Mario mario) {
        if (cell().getTile().getId() != BLANK_COIN) {
            if (isPowerUpBlock) {
                if (mario.isGrownUp()) {
                    getPS().addPowerUp(fController);
                    mController.queueDestroy();
                } else {
                    getPS().addPowerUp(mController);
                    fController.queueDestroy();
                }
            }
            if (isCoinBlock) {

                getPS().addCoinItems(cController);
                hud.addScore(COIN_BLOCK_SCORE);
                hud.addCoin();
                assetManager.get("audio/items/coin.wav", Sound.class).play();
            }
            cell().setTile(getTileSet().getTile(BLANK_COIN));
        }
        assetManager.get("audio/items/bump.wav", Sound.class).play();
    }
}
