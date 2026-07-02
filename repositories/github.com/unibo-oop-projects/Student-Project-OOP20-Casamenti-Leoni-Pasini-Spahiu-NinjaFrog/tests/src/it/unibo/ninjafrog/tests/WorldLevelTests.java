package it.unibo.ninjafrog.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import it.unibo.ninjafrog.game.utilities.GameConst;
import it.unibo.ninjafrog.screens.levels.Level;
import it.unibo.ninjafrog.screens.levels.LevelOne;
import it.unibo.ninjafrog.screens.levels.LevelTwo;
import it.unibo.ninjafrog.world.Brick;
import it.unibo.ninjafrog.world.FruitBox;
import it.unibo.ninjafrog.world.NonInteractiveBuilder;
import it.unibo.ninjafrog.world.NonInteractiveBuilderImpl;
import it.unibo.ninjafrog.world.WorldCollisionListener;

/**
 * Automated tests for the World and the level maps.
 */
@RunWith(GdxTestRunner.class)
public class WorldLevelTests {
    private static final String FILE_SEPARATOR = System.getProperty("file.separator");
    private static final String ASSETS_PATH = ".." + FILE_SEPARATOR + "core" + FILE_SEPARATOR + "assets"
            + FILE_SEPARATOR;
    private static final int GRAPHICS_LAYER = 1;
    private static final int GROUND_LAYER = 2;
    private static final int FINISH_TROPHY_LAYER = 6;
    private static final int NOT_A_LAYER = 9;

    /**
     * Test for the level one map file.
     */
    @Test
    public void levelOneExists() {
        final Level level = new LevelOne();
        assertTrue("This test will only pass if the tmx file of the Map exists in the assets folder.",
                Gdx.files.internal(ASSETS_PATH + level.getMap()).exists());
    }

    /**
     * Test for the level two map file.
     */
    @Test
    public void levelTwoExists() {
        final Level level = new LevelTwo();
        assertTrue("This test will only pass if the tmx file of the Map exists in the assets folder.",
                Gdx.files.internal(ASSETS_PATH + level.getMap()).exists());
    }

    /**
     * Test for the background files.
     */
    @Test
    public void backgroundsExist() {
        assertTrue(
                "This test will only pass if the jpg/png and tsx files of the backgrounds exist in the assets folder.",
                Gdx.files.internal(ASSETS_PATH + "Level1background.jpg").exists());
        assertTrue(Gdx.files.internal(ASSETS_PATH + "Level2background.png").exists());
        assertTrue(Gdx.files.internal(ASSETS_PATH + "Level1background.tsx").exists());
        assertTrue(Gdx.files.internal(ASSETS_PATH + "Level2background.tsx").exists());
    }

    /**
     * Test for the asset file.
     */
    @Test
    public void assetExists() {
        assertTrue("This test will only pass if the png and tsx files of the asset exist in the assets folder.",
                Gdx.files.internal(ASSETS_PATH + "NinjaFrogAsset.png").exists());
        assertTrue(Gdx.files.internal(ASSETS_PATH + "NinjaFrogAsset.tsx").exists());
    }

    /**
     * Test for the maps graphic layer.
     */
    @Test
    public void graphicLayerExist() {
        final TiledMap map1 = new TmxMapLoader().load(new LevelOne().getMap());
        final TiledMap map2 = new TmxMapLoader().load(new LevelTwo().getMap());
        assertNotNull(map1.getLayers().get(GRAPHICS_LAYER));
        assertNotNull(map2.getLayers().get(GRAPHICS_LAYER));
    }

    /**
     * Test for the maps objects layers. Verifies that every layer exists and
     * contains at least one object.
     */
    @Test
    public void layersExistLevels() {
        final TiledMap map1 = new TmxMapLoader().load(new LevelOne().getMap());
        final TiledMap map2 = new TmxMapLoader().load(new LevelTwo().getMap());
        for (int layer = GROUND_LAYER; layer <= FINISH_TROPHY_LAYER; layer++) {
            assertTrue(layer < map1.getLayers().getCount());
            assertTrue(layer < map2.getLayers().getCount());
            assertNotNull(map1.getLayers().get(layer).getObjects().getByType(RectangleMapObject.class));
            assertNotNull(map2.getLayers().get(layer).getObjects().getByType(RectangleMapObject.class));
            assertFalse(map1.getLayers().get(layer).getObjects().getByType(RectangleMapObject.class).isEmpty());
            assertFalse(map2.getLayers().get(layer).getObjects().getByType(RectangleMapObject.class).isEmpty());
        }
    }

    /**
     * Test for the level one layers.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void indexLayersExceptionLevOne() {
        final TiledMap map = new TmxMapLoader().load(new LevelOne().getMap());
        map.getLayers().get(NOT_A_LAYER);
    }

    /**
     * Test for the level two layers.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void indexLayersExceptionLevTwo() {
        final TiledMap map = new TmxMapLoader().load(new LevelTwo().getMap());
        map.getLayers().get(NOT_A_LAYER);
    }

    /**
     * {@link it.unibo.ninjafrog.world.NonInteractiveBuilder NonInteractiveBuilder}
     * test.
     */
    @Test(expected = IllegalStateException.class)
    public void builderThrowsException() {
        final NonInteractiveBuilder builder = new NonInteractiveBuilderImpl(null);
        builder.chooseCategoryBit(GameConst.GROUND).selectObject(new MapObject()).build();
    }

    /**
     * {@link it.unibo.ninjafrog.world.FruitBox FruitBox} test.
     */
    @Test(expected = IllegalStateException.class)
    public void fruitBoxThrowsException() {
        new FruitBox(null, new MapObject());
    }

    /**
     * {@link it.unibo.ninjafrog.world.Brick Brick} test.
     */
    @Test(expected = IllegalStateException.class)
    public void BrickThrowsException() {
        new Brick(null, new MapObject());
    }

    /**
     * {@link it.unibo.ninjafrog.world.WorldCollisionListener
     * WorldCollisionListener} test.
     */
    @Test
    public void listenerCreationTest() {
        final WorldCollisionListener listener = new WorldCollisionListener(null, null);
        assertNotNull(listener);
    }
}
