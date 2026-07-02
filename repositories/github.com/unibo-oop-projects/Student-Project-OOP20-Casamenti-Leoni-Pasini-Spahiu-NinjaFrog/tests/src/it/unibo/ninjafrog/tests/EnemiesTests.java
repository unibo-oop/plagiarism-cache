package it.unibo.ninjafrog.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import it.unibo.ninjafrog.enemies.EnemyControllerImpl;
import it.unibo.ninjafrog.enemies.RinoModelImpl;
import it.unibo.ninjafrog.enemies.RinoViewImpl;
import it.unibo.ninjafrog.enemies.TurtleModelImpl;
import it.unibo.ninjafrog.enemies.TurtleViewImpl;
import it.unibo.ninjafrog.screens.levels.LevelOne;
import it.unibo.ninjafrog.screens.levels.LevelTwo;

@RunWith(GdxTestRunner.class)
public class EnemiesTests {
	private static final String FILE_SEPARATOR = System.getProperty("file.separator");
	private static final String ASSETS_PATH = ".." + FILE_SEPARATOR + "core" + FILE_SEPARATOR + "assets"
			+ FILE_SEPARATOR;
	private static final int RINO_LAYER = 7;
	private static final int TURTLE_LAYER = 8;

	/**
	 * Test for the asset file.
	 */
	@Test
	public void enemyAssetExists() {
		assertTrue("This test will only pass if the png and pack files of the asset exist in the assets folder.",
				Gdx.files.internal(ASSETS_PATH + "ninjaAndEnemies.png").exists());
		assertTrue(Gdx.files.internal(ASSETS_PATH + "ninjaAndEnemies.pack").exists());
	}

	/**
	 * Test for the rino layer. Verifies that the layer exists and contains at least
	 * one object.
	 */
	@Test
	public void rinoLayerExists() {
		final TiledMap map1 = new TmxMapLoader().load(new LevelOne().getMap());
		final TiledMap map2 = new TmxMapLoader().load(new LevelTwo().getMap());
		assertTrue(RINO_LAYER < map1.getLayers().getCount());
		assertTrue(RINO_LAYER < map2.getLayers().getCount());
		assertNotNull(map1.getLayers().get(RINO_LAYER).getObjects().getByType(RectangleMapObject.class));
		assertNotNull(map2.getLayers().get(RINO_LAYER).getObjects().getByType(RectangleMapObject.class));
		assertFalse(map1.getLayers().get(RINO_LAYER).getObjects().getByType(RectangleMapObject.class).isEmpty());
		assertFalse(map2.getLayers().get(RINO_LAYER).getObjects().getByType(RectangleMapObject.class).isEmpty());

	}

	/**
	 * Test for the turtle layer. Verifies that the layer exists and contains at
	 * least one object.
	 */
	@Test
	public void turtleLayerExists() {
		final TiledMap map1 = new TmxMapLoader().load(new LevelOne().getMap());
		final TiledMap map2 = new TmxMapLoader().load(new LevelTwo().getMap());
		assertTrue(TURTLE_LAYER < map1.getLayers().getCount());
		assertTrue(TURTLE_LAYER < map2.getLayers().getCount());
		assertNotNull(map1.getLayers().get(TURTLE_LAYER).getObjects().getByType(RectangleMapObject.class));
		assertNotNull(map2.getLayers().get(TURTLE_LAYER).getObjects().getByType(RectangleMapObject.class));
		assertFalse(map1.getLayers().get(TURTLE_LAYER).getObjects().getByType(RectangleMapObject.class).isEmpty());
		assertFalse(map2.getLayers().get(TURTLE_LAYER).getObjects().getByType(RectangleMapObject.class).isEmpty());
	}

	/**
	 * {@link it.unibo.ninjafrog.enemies.EnemyControllerImpl EnemyControllerImpl}
	 * test.
	 */
	@Test(expected = IllegalStateException.class)
	public void enemyControllerThrowsException() {
		new EnemyControllerImpl(null);
	}

	/**
	 * {@link it.unibo.ninjafrog.enemies.RinoModelImpl RinoModelImpl} test.
	 */
	@Test(expected = NullPointerException.class)
	public void RinoModelImplThrowsException() {
		new RinoModelImpl(null, null);
	}

	/**
	 * {@link it.unibo.ninjafrog.enemies.RinoViewImpl RinoViewImpl} test.
	 */
	@Test(expected = NullPointerException.class)
	public void RinoViewImplThrowException() {
		new RinoViewImpl(null, 0, 0, null);
	}

	/**
	 * {@link it.unibo.ninjafrog.enemies.TurtleModelImpl TurtleModelImpl} test.
	 */
	@Test(expected = NullPointerException.class)
	public void TurtleModelImplThrowException() {
		new TurtleModelImpl(null, null);
	}

	/**
	 * {@link it.unibo.ninjafrog.enemies.TurtleViewImpl TurtleViewImpl} test.
	 */
	@Test(expected = NullPointerException.class)
	public void TurtleViewImplThrowException() {
		new TurtleViewImpl(null, 0, 0, null);
	}

}
