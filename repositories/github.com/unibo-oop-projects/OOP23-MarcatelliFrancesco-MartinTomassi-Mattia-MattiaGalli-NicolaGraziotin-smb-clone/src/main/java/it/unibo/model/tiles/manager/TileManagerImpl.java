package it.unibo.model.tiles.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import it.unibo.commons.Constants;
import it.unibo.commons.Point2D;
import it.unibo.model.documentextractor.DocumentExtractor;
import it.unibo.model.documentextractor.factory.DocumentExtractorFactoryImpl;
import it.unibo.model.entity.obstacles.CircularSaw;
import it.unibo.model.entity.obstacles.Platform;
import it.unibo.model.entity.player.MeatBoy;
import it.unibo.model.entity.player.MeatBoyImpl;
import it.unibo.model.entity.target.BandageGirl;
import it.unibo.model.entity.target.BandageGirlImpl;
import it.unibo.model.tiles.Tile;
import it.unibo.model.tiles.loader.factory.manager.TileLoaderManagerFactoryImpl;
import it.unibo.model.tiles.tileset.factory.TileSetFactoryImpl;

/**
 * The TileManagerImpl class implements the TileManager interface and provides
 * functionality to manage tiles, platforms, circular saws, and player entities.
 */
public class TileManagerImpl implements TileManager {

    private final List<List<Optional<Tile>>> stationary;
    private final List<Platform> platforms;
    private final List<CircularSaw> circularSaws;
    private final List<Tile> tiles;
    private final MeatBoy meatBoy;
    private final BandageGirl bandageGirl;
    private final int numRows;
    private final int numColumns;

    /**
     * Constructs a new TileManager object by parsing a specified TMX file.
     *
     * @param tmx The path to the TMX file.
     */
    public TileManagerImpl(final String tmx) {
        this.platforms = new ArrayList<>();
        this.circularSaws = new ArrayList<>();
        this.stationary = new ArrayList<>();
        this.tiles = new TileSetFactoryImpl()
            .createTileSet(tmx).read();
        this.meatBoy = new MeatBoyImpl(0, 0);
        this.bandageGirl = new BandageGirlImpl(0, 0);
        final DocumentExtractor documentExtractor = new DocumentExtractorFactoryImpl()
            .createDocumentExtractor(tmx);
        this.numRows = documentExtractor.getNumRows();
        this.numColumns = documentExtractor.getNumColumns();
        init();
        new TileLoaderManagerFactoryImpl()
            .createTileLoaderManager(this, tmx).load();
    }

    @Override
    public final List<Platform> getPlatforms() {
        return Collections.unmodifiableList(this.platforms);
    }

    @Override
    public final List<CircularSaw> getSaws() {
        return Collections.unmodifiableList(this.circularSaws);
    }

    @Override
    public final int getNumCols() {
        return this.numColumns;
    }

    @Override
    public final int getNumRows() {
        return this.numRows;
    }

    @Override
    public final MeatBoy getMeatBoy() {
        return new MeatBoyImpl(
            this.meatBoy.getX(),
            this.meatBoy.getY());
    }

    @Override
    public final BandageGirl getBandageGirl() {
        return new BandageGirlImpl(
            this.bandageGirl.getX(),
            this.bandageGirl.getY());
    }

    @Override
    public final List<List<Optional<Tile>>> getStationary() {
        return Collections.unmodifiableList(this.stationary);
    }

    @Override
    public final List<Tile> getTiles() {
        return Collections.unmodifiableList(this.tiles);
    }

    @Override
    public final void setSaw(final CircularSaw circularSaw) {
        this.circularSaws.add(circularSaw);
    }

    @Override
    public final void setPlatform(final Platform platform) {
        this.platforms.add(platform);
    }

    @Override
    public final void setMeatBoyCoord(final Point2D<Double, Double> coord) {
        this.meatBoy.setX(coord.getX() * Constants.SCALE_PROPORTION);
        this.meatBoy.setY(coord.getY() * Constants.SCALE_PROPORTION);
    }

    @Override
    public final void setBandageGirlCoord(final Point2D<Double, Double> coord) {
        this.bandageGirl.setX(coord.getX() * Constants.SCALE_PROPORTION);
        this.bandageGirl.setY(coord.getY() * Constants.SCALE_PROPORTION);
    }

    /**
     * Initializes the stationary list.
     * This method is called during the construction of the TileManager object.
     */
    private void init() {
        for (int i = 0; i < this.numRows; i++) {
            stationary.add(new ArrayList<>(Collections.nCopies(this.numColumns, Optional.empty())));
        }
    }

}
