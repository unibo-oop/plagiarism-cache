package it.unibo.oop.supermario.world;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;

import it.unibo.oop.supermario.screens.PlayScreen;

/**
 * Class that defines the game world.
 */
public class SolidWorldCreatorImpl implements SolidWorldCreator {

    private final PlayScreen playScreen;
    private final TiledMap map;
    private static final int START_GROUP_BLOCK = 1;
    private static final int N_GROUP_BLOCK = 6;
    private static final int COIN_CASE = 4;
    private static final int BRICK_CASE = 5;

    /**
     * Solid world constructor.
     * 
     * @param playScreen Playscreen's object
     */
    public SolidWorldCreatorImpl(final PlayScreen playScreen) {
        this.playScreen = playScreen;
        this.map = playScreen.getMap();
    }

    /**
     * Load each object of tiled map in its related class objects.
     */
    public void initSolidWorld() {
        for (int i = START_GROUP_BLOCK; i < N_GROUP_BLOCK; i++) {
            for (final MapObject obj : map.getLayers().get(i).getObjects().getByType(RectangleMapObject.class)) {
                // put in each map its relative object
                final Rectangle rect = ((RectangleMapObject) obj).getRectangle();
                switch (i) {
                case COIN_CASE:
                    new CoinBlock(playScreen, rect.getX(), rect.getY(), obj);
                    break;
                case BRICK_CASE:
                    new Brick(playScreen, rect.getX(), rect.getY(), obj);
                    break;
                default:
                    if (obj.getProperties().containsKey("flag")) {
                        new FlagTube(playScreen, rect.getX(), rect.getY(), obj);
                    } else {
                        new TiledObject(playScreen, rect.getX(), rect.getY(), obj);
                    }
                }
            }
        }
    }
}
