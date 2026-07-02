package it.unibo.model.tiles.loader.manager;

import java.util.List;
import java.util.Optional;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.commons.Point2D;
import it.unibo.model.documentextractor.DocumentExtractor;
import it.unibo.model.documentextractor.factory.DocumentExtractorFactoryImpl;
import it.unibo.model.entity.obstacles.CircularSaw;
import it.unibo.model.entity.obstacles.Platform;
import it.unibo.model.tiles.Tile;
import it.unibo.model.tiles.loader.factory.gameobjects.TileLoaderGameObjectsFactoryImpl;
import it.unibo.model.tiles.loader.factory.stationary.TileLoaderStationaryFactoryImpl;
import it.unibo.model.tiles.loader.gameobjects.TileLoaderGameObjects;
import it.unibo.model.tiles.loader.stationary.TileLoaderStationary;
import it.unibo.model.tiles.manager.TileManager;
/**
 * The implementation of the TileLoaderManager interface is responsible for managing the loading 
 * of StationaryTiles and GameObjects from a TMX file.
*/
public class TileLoaderManagerImpl implements TileLoaderManager {

    private final TileManager tileManager;
    private final TileLoaderGameObjects tileLoaderGameObjects;
    private final TileLoaderStationary tileLoaderStationary;

    /**
     * Constructs a TileLoaderManagerImpl with the specified TileManager and TMX file.
     *
     * @param tileManager The tile manager responsible for managing tiles in the game.
     * @param tmx         The path to the TMX file containing tile information.
     */
    @SuppressFBWarnings(value = "EI2", justification =
                "Justification for Suppressing SpotBugs Warning:\r\n"
                + //
                "The SpotBugs warning \"EI2\" indicates that a mutable object (tileManager)\r\n"
                + //
                "is being stored without defensively copying its contents. In this context, the\r\n"
                + //
                "tileManager object is used solely for internal use within the\r\n"
                + //
                "TileLoaderManagerImpl class and for initializing its internal state. It's not\r\n"
                + //
                "exposed to external code, and no modifications are made to it after initialization.\r\n"
                + //
                "Therefore, there's no risk of unintended modification by external code or concurrent\r\n"
                + //
                "access issues. Suppressing this warning is justified as it accurately reflects the\r\n"
                + //
                "intentional design and usage of the tileManager object within this class.")
    public TileLoaderManagerImpl(final TileManager tileManager, final String tmx) {
        this.tileManager = tileManager;
        final DocumentExtractor documentExtractor = new DocumentExtractorFactoryImpl()
            .createDocumentExtractor(tmx);
        this.tileLoaderGameObjects = new TileLoaderGameObjectsFactoryImpl()
            .createTileLoaderGameObjects(this, documentExtractor);
        this.tileLoaderStationary = new TileLoaderStationaryFactoryImpl()
            .createTileLoaderStationary(this, documentExtractor);
    }

    /**
     * Loads stationary tiles and objects from the TMX file.
     */
    @Override
    public void load() {
        this.tileLoaderStationary.load();
        this.tileLoaderGameObjects.load();
    }

    @Override
    public final String trim(final String s) {
        return s.contains(".") ? s.replaceAll("\\s+", "") : s;
    }

    @Override
    public final List<List<Optional<Tile>>> getStationary() {
        return this.tileManager.getStationary();
    }

    @Override
    public final List<Tile> getTiles() {
        return this.tileManager.getTiles();
    }

    @Override
    public final void setPlatform(final Platform platform) {
        this.tileManager.setPlatform(platform);
    }

    @Override
    public final void setSaw(final CircularSaw circularSaw) {
        this.tileManager.setSaw(circularSaw);
    }

    @Override
    public final void setMeatBoyCoord(final Point2D<Double, Double> coord) {
        this.tileManager.setMeatBoyCoord(coord);
    }

    @Override
    public final void setBandageGirlCoord(final Point2D<Double, Double> coord) {
        this.tileManager.setBandageGirlCoord(coord);
    }

}
