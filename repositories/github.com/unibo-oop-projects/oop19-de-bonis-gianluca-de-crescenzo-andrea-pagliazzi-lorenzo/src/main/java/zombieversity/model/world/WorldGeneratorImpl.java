package zombieversity.model.world;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javafx.geometry.Point2D;
import javafx.util.Pair;
import zombieversity.view.world.WorldView;
import zombieversity.view.world.WorldViewImpl;
import zombieversity.view.world.graphics.TileFactory;
import zombieversity.view.world.graphics.TileFactoryImpl;
import zombieversity.view.world.graphics.TileType;

/**
 * Class to implements the WorldGenerator, this is usefull to centralize the
 * generation of the World and it's associated WordlView.
 *
 */
public class WorldGeneratorImpl implements WorldGenerator {
    private static final int BACKGROUND_COUNT = 18;
    private final TileFactory tFactory;

    public WorldGeneratorImpl(final String url, final double ts) {
        this.tFactory = new TileFactoryImpl(url, ts);
    }

    /**
     * 
     * @param b  -> represent blocks - Point2D given is relative (0,0) - (1,0) ...
     *           (w,0) (0,1) - (1,1) ... (w,1) .... - .... .... ....
     *           -----------------------------------------------------------(0,h) -
     *           (1,h) ... (w,h) Object are in absolutPos means -
     *           point.multiply(tileSize) (0,0) - (32,0) ... (w*32,0)
     * @param w  -> width of the map ex(100 * tileSize) - 3200
     * @param h  -> height of the map ex(100 * tileSize) - 3200
     * @param ts -> size of each block
     * @return World
     */
    private static World generateWorld(final Map<Point2D, Integer> b, final double w, final double h, final double ts) {
        return new WorldImpl(b, w, h, ts);
    }

    /**
     * @param blocks
     * @param tf
     * @param ts
     * @param bg
     * @param w
     * @param h
     * @return WorldView
     */
    private static WorldView generateWorldView(final Map<Point2D, Integer> blocks, final TileFactory tf,
            final double ts, final Integer bg, final double w, final double h) {
        return new WorldViewImpl(blocks, tf, ts, bg, w, h);
    }

    /**
     * Helper method to generate randomly background index.
     * 
     * @return
     */
    private static int generateBackground() {
        final Random rnd = new Random();
        return rnd.nextInt(BACKGROUND_COUNT);
    }

    /**
     * Helper method to generate an index of a block from the pool.
     * 
     * @return
     */
    private static int generateObject() {
        final Random rnd = new Random();
        return TileType.values()[rnd.nextInt(2)].getId();
    }

    /**
     * Helper method to auto generate an empty object world.
     * 
     * @return HashMap<Point2D, Integer>.
     */
    private static Map<Point2D, Integer> generateEmpty() {
        return new HashMap<Point2D, Integer>();
    }

    /**
     * Help method to set up a basic world.
     * 
     * @param w - widht of the world in blocks
     * @param h - height of the world in blocks
     * @return Map<Point2D, Integer> - the generated map.
     */
    private Map<Point2D, Integer> generate(final double w, final double h, final int bkg) {
        final Point2D center = new Point2D(Math.floor(w / 2), Math.floor(h / 2));
        final Map<Point2D, Integer> blocks = generateEmpty();
        fillBorder(blocks, TileType.WALL.getId(), w, h); // World Limit
        fillCell(blocks, TileType.PLAYER_SPAWN.getId(), center); // Player Spawn
        fillAngles(blocks, TileType.ZOMBIE_SPAWN.getId(), center, w - 2, h - 2); // Zombie spawns
        fillNextTo(blocks, bkg, center); // walkable area next to Player Spawn
        return blocks;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void generateRandom(final Map<Point2D, Integer> blocks, final int rBound, final int w, final int h) {
        final Random r = new Random();
        final int count = r.nextInt(rBound);
        for (int i = 0; i < count; i++) {
            fillCell(blocks, generateObject(), new Point2D(r.nextInt(w), r.nextInt(h)));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Pair<World, WorldView> generate(final double w, final double h, final double ts, final Difficulty d) {
        final int bkg = generateBackground();
        final Map<Point2D, Integer> blocks = generate(w, h, bkg);
        final int bound = (int) ((int) w * h / 100);

        generateRandom(blocks, (int) (bound * 100 / d.getDiff()), (int) w, (int) h);
        return new Pair<>(generateWorld(blocks, w, h, ts), generateWorldView(blocks, this.tFactory, ts, bkg, w, h));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void fillNextTo(final Map<Point2D, Integer> blocks, final int id, final Point2D p) {
        // UP
        blocks.put(p.add(new Point2D(-1, -1)), id);
        blocks.put(p.add(new Point2D(0, -1)), id);
        blocks.put(p.add(new Point2D(1, -1)), id);
        // CENTER
        blocks.put(p.add(new Point2D(-1, 0)), id);
        blocks.put(p.add(new Point2D(1, 0)), id);
        // DOWN
        blocks.put(p.add(new Point2D(-1, 1)), id);
        blocks.put(p.add(new Point2D(0, 1)), id);
        blocks.put(p.add(new Point2D(1, 1)), id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void fillBorder(final Map<Point2D, Integer> blocks, final int id, final double w, final double h) {

        fillAngles(blocks, id, new Point2D(Math.floor(w / 2), Math.floor(h / 2)), w, h);
        fillRow(blocks, id, new Point2D(1, 0), (int) w);
        fillRow(blocks, id, new Point2D(1, h - 1), (int) w);
        fillColumn(blocks, id, new Point2D(0, 1), (int) h);
        fillColumn(blocks, id, new Point2D(w - 1, 1), (int) h);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void fillRow(final Map<Point2D, Integer> blocks, final int id, final Point2D initP, final int count) {
        final Point2D p = new Point2D(1, 0);
        for (int i = 0; i < count; i++) {
            fillCell(blocks, id, initP.add(p.multiply(i)));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void fillColumn(final Map<Point2D, Integer> blocks, final int id, final Point2D initP,
            final int count) {
        final Point2D p = new Point2D(0, 1);
        for (int i = 0; i < count; i++) {
            fillCell(blocks, id, initP.add(p.multiply(i)));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void fillCell(final Map<Point2D, Integer> blocks, final int id, final Point2D p) {
        if (!blocks.containsKey(p)) {
            blocks.put(p, id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void fillAngles(final Map<Point2D, Integer> blocks, final int id, final Point2D center, final double w,
            final double h) {
        final Point2D pLURD = new Point2D(Math.floor(w / 2), Math.floor(h / 2));
        final Point2D pLD = new Point2D(pLURD.getX() * -1, pLURD.getY());
        final Point2D pRU = new Point2D(pLURD.getX(), pLURD.getY() * -1);
        // LEFT_UP
        fillCell(blocks, id, center.add(pLURD.multiply(-1)));
        // RIGHT_UP
        fillCell(blocks, id, center.add(pLD));
        // RIGHT_DOWN
        fillCell(blocks, id, center.add(pLURD));
        // LEFT_DOWN
        fillCell(blocks, id, center.add(pRU));
    }

}
