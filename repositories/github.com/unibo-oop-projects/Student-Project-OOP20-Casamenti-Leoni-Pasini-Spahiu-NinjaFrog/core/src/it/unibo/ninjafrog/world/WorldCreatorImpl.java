package it.unibo.ninjafrog.world;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;

import it.unibo.ninjafrog.game.utilities.GameConst;
import it.unibo.ninjafrog.screens.PlayScreen;

/**
 * Definition of a {@link it.unibo.ninjafrog.world.WorldCreator WorldCreator}
 * implementation.
 */
public final class WorldCreatorImpl implements WorldCreator {
    private static final int GROUND_LAYER = 2;
    private static final int FRUITBOX_LAYER = 3;
    private static final int BRICK_LAYER = 4;
    private static final int GROUND_OBJECT_LAYER = 5;
    private static final int FINISH_TROPHY_LAYER = 6;
    private final PlayScreen screen;
    private final NonInteractiveBuilder builder;

    /**
     * Public constructor of the WorldCreatorImpl.
     * 
     * @param screen The {@link it.unibo.ninjafrog.screens.PlayScreen PlayScreen}
     *               where the world is going to be created.
     */
    public WorldCreatorImpl(final PlayScreen screen) {
        this.screen = screen;
        this.builder = new NonInteractiveBuilderImpl(screen);
    }

    @Override
    public void createWorld() {
        final TiledMap map = this.screen.getMap();
        for (int i = GROUND_LAYER; i <= FINISH_TROPHY_LAYER; i++) {
            for (final MapObject object : map.getLayers().get(i).getObjects().getByType(RectangleMapObject.class)) {
                switch (i) {
                case GROUND_LAYER:
                    this.builder.selectObject(object).chooseCategoryBit(GameConst.GROUND).build();
                    break;
                case GROUND_OBJECT_LAYER:
                    this.builder.selectObject(object).chooseCategoryBit(GameConst.GROUND_OBJECT).build();
                    break;
                case FINISH_TROPHY_LAYER:
                    this.builder.selectObject(object).chooseCategoryBit(GameConst.FINISH).build();
                    break;
                case FRUITBOX_LAYER:
                    new FruitBox(this.screen, object);
                    break;
                case BRICK_LAYER:
                    new Brick(this.screen, object);
                    break;
                default:
                    // unused;
                    break;
                }
            }
        }
    }

}
