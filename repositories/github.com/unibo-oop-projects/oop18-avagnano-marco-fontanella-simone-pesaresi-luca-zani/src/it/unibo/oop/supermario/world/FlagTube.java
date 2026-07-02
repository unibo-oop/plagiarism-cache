package it.unibo.oop.supermario.world;

import com.badlogic.gdx.maps.MapObject;

import it.unibo.oop.supermario.character.Mario;
import it.unibo.oop.supermario.character.MarioController;
import it.unibo.oop.supermario.gamemanager.GameManager;
import it.unibo.oop.supermario.powerup.Item;
import it.unibo.oop.supermario.screens.Hud;
import it.unibo.oop.supermario.screens.PlayScreen;

/**
 * Class defines the staff of flag.
 */
public class FlagTube extends TiledObject implements Item {
    private final Flag fModel;
    private final MarioController marioController;
    private final Hud hud;
    private static final int FLAG_POINTS = 5000;

    /**
     * FlagTube constructor.
     * 
     * @param playScreen Playscreen object
     * @param x          x object's coordinate
     * @param y          y object's coordinate
     * @param obj        object of tiled map
     */
    public FlagTube(final PlayScreen playScreen, final float x, final float y, final MapObject obj) {
        super(playScreen, x, y, obj);
        setCategoryFilter(GameManager.FLAG_BIT);
        getFixture().setUserData(this);
        fModel = new FlagImpl(playScreen, x, y);
        playScreen.setFlag(fModel);
        hud = playScreen.getHud();
        marioController = playScreen.getMarioController();
    }

    @Override
    public final void onCollide(final Mario mario) {
        marioController.setBlockMario();
        hud.addScore(FLAG_POINTS);
        fModel.setFlagFalling(true);
    }
}
