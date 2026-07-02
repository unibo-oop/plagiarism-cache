package zombieversity.controller;

import java.util.Set;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.util.Pair;
import zombieversity.model.world.World;
import zombieversity.model.world.WorldGenerator;
import zombieversity.model.world.WorldGenerator.Difficulty;
import zombieversity.model.world.WorldGeneratorImpl;
import zombieversity.view.world.WorldView;

/**
 * Controller for World and WorldView, initial supposed to have also minimap but
 * couldn't be properly implemented.
 *
 */
public class MapController {

    private static String url = "/ambient.png";
    private static final int TILE_SIZE = 128;

//    private static final int REGIONS = 2;
//    private List<Region> regions;
//    private final double  offsetW;
//    private final double offsetH;

    private World w;
    private WorldView wv;

    private final WorldGenerator wGenerator;

    /**
     * 
     * @param w
     * @param h
     * @param tS
     * @param renderScale
     */
    public MapController(final double w, final double h, final double tS, final double renderScale) {
        this.wGenerator = new WorldGeneratorImpl(url, TILE_SIZE);
        this.generateMap(w, h, tS);
        this.w.setScale(renderScale);
        this.wv.setScale(renderScale);
//        this.offsetW = (w * tS) / REGIONS;
//        this.offsetH = (h * tS) / REGIONS;
//        this.regions = new LinkedList<>();

//        for(int i = 0; i < w * tS; i+= offsetW) {
//            for(int j = 0; j < h * tS; j += offsetH) {
//                regions.add(new RegionImpl(
//                        new Point2D(i, j),
//                        new Point2D(i + offsetW - 1, j + offsetH - 1)));
//            }
//        }
//        this.loadRegions();
    }

//    private void loadRegions() {
//        w.getObstacles().forEach( o -> {
//             regions.forEach(r -> {
//                 if(r.isInside(o.getPosition())) {
//                     r.addObstacle(o);
//                 }
//             });
//        });
//    }
    /**
     * 
     * @param w  Map's width.
     * @param h  Map's height.
     * @param ts Tiles size.
     */
    public final void generateMap(final double w, final double h, final double ts) {
        final Pair<World, WorldView> result = this.wGenerator.generate(w, h, ts, Difficulty.HARD);
        this.w = result.getKey();
        this.wv = result.getValue();
    }

    /**
     * @return World Map.
     */
    public final World getMap() {
        return this.w;
    }

    /**
     * Not implemented for performance issues.
     * 
     * @param gc
     */
    public void setMiniMap(final GraphicsContext gc) {
    }

    /**
     * @return Set<Pair<Point2D, Image>> Map elements positions.
     */
    public final Set<Pair<Point2D, Image>> render() {
        return this.wv.renderMap();
    }

    /**
     * 
     * @return WorldView
     *          The {@link WorldView}
     */
    public final WorldView getWorldView() {
        return this.wv;
    }

    /**
     * 
     * @param scale
 *          Factor to resize map.
     */
    public final void resize(final double scale) {
        this.wv.setScale(scale);
    }

//    public void addEntity(final Entity e) {
//        regions.forEach(r -> {
//            if(r.isInside(e.getPosition()))
//                r.addEntity(e);
//        });
//    }
//
//    public <T> void addEntities(final Set<T> entities) {
//        entities.forEach(e -> 
//            regions.forEach(r -> {
//                if(r.isInside(((Entity) e).getPosition()))
//                    r.addEntity((Entity) e);
//            })
//         );
//    }

//    public void update() {
//        Set<Entity> toReassign = new HashSet<>();
//        regions.forEach(r -> {
//            new Thread( new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(Thread.currentThread().getName());
//                    toReassign.addAll(r.outOfRegion());
//                }
//           }).start();
//        });
//        addEntities(toReassign);
//    }
}
