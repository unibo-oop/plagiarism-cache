package model.collidable.terrain.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.junit.Before;
import org.junit.Test;

import model.collidable.terrain.Terrain;
import model.collidable.terrain.TerrainImpl;
import model.explosion.Explosion;
import model.explosion.ExplosionImpl;

/**
 * Terrain Test.
 * 
 * @author Nicola Tamburini
 *
 */
// CHECKSTYLE: MagicNumber OFF
public class TerrainTest {

    private Terrain t;
    private static final int HEIGHT = 200;
    private static final int WIDTH = 500;
    private static final long SEED = 12332543;
    private static final int WAVELENGTH = 50;
    private static final int SAMPLING = 5;

    /**
     * Initialization Terrain object.
     * 
     */
    @Before
    public void before() {
        final Vector2D g = new Vector2D(9.81, 0);

        t = new TerrainImpl.Builder().setGravitationalAcceleration(g).setHeight(HEIGHT).setWidth(WIDTH)
                .setWaveLenght(WAVELENGTH).setSeed(SEED).build();
    }

    private Explosion newExplosion(final double x, final double y) {
        final Vector2D exPosition = new Vector2D(x, y);
        return new ExplosionImpl(exPosition, 20, 10);
    }

    private List<Vector2D> getSurface() {
        final List<Vector2D> surface = new ArrayList<>(t.getOutlinesPoints().get(t.getOutlinesPoints().size() - 1));
        surface.remove(surface.size() - 1);
        surface.remove(surface.size() - 1);
        return surface;
    }

    private boolean checkGeneratedPoints(final List<Vector2D> l) {
        return IntStream.range(0, l.size() - 1)
                .allMatch(i -> 0 == Double.compare((l.get(i).getX() + SAMPLING), l.get(i + 1).getX()));
    }

    /**
     * test the correct Terrain surface.
     */
    @Test
    public void preTest() {
        final List<Vector2D> surface = getSurface();
        assertTrue(checkGeneratedPoints(surface));
    }

    /**
     * test the explosion of terrain.
     */
    @Test
    public void explosion() {
        final List<Explosion> exs = new ArrayList<>();
        final Explosion ex1 = newExplosion(102.0, 95.0);
        exs.add(ex1);
        final List<Vector2D> preExplosionsurface = getSurface();

        t.explode(exs);

        final List<Vector2D> postEx1 = getSurface();
        assertFalse(preExplosionsurface.equals(postEx1));
        assertFalse(postEx1.stream().allMatch(v -> Math.pow(v.getX() - ex1.getPosition().getX(), 2)
                + Math.pow(v.getY() - ex1.getPosition().getY(), 2) <= Math.pow(ex1.getBlastRadius(), 2)));

        t.explode(exs);
        assertEquals(postEx1, getSurface());

        final Explosion ex2 = newExplosion(50.0, 35.0);
        exs.add(ex2);
        t.explode(exs);
        final List<Vector2D> postEx2 = getSurface();
        assertFalse(postEx1.equals(postEx2));

        assertFalse(postEx2.stream().allMatch(v -> Math.pow(v.getX() - ex2.getPosition().getX(), 2)
                + Math.pow(v.getY() - ex2.getPosition().getY(), 2) <= Math.pow(ex2.getBlastRadius(), 2)));

    }

    private boolean checkSortedPoints(final List<Vector2D> l) {
        return IntStream.range(0, l.size() - 1).allMatch(i -> l.get(i).getX() <= l.get(i + 1).getX());
    }

    /**
     * Test if the terrain is consistent after settlement.
     */
    @Test
    public void terrainSettlement() {

        final double timestep = 1.0 / 60.0;
        final List<Explosion> exs = new ArrayList<>();
        exs.add(newExplosion(102.0, 95.0));
        exs.add(newExplosion(50.0, 35.0));
        exs.add(newExplosion(76.0, 67.0));

        t.explode(exs);
        while (!t.isStationary()) {
            t.update(timestep);
        }
        assertTrue(checkSortedPoints(getSurface()));
    }

}
