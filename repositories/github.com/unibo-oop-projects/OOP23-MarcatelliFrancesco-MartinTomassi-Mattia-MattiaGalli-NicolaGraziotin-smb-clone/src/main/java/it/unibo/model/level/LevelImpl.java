package it.unibo.model.level;

import java.util.List;
import java.util.Optional;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.tiles.Tile;
import it.unibo.model.tiles.manager.TileManager;
import it.unibo.model.tiles.manager.factory.TileManagerFactoryImpl;
import it.unibo.commons.Point2D;
import it.unibo.model.entity.obstacles.CircularSaw;
import it.unibo.model.entity.obstacles.Platform;
import it.unibo.model.entity.player.MeatBoy;
import it.unibo.model.entity.target.BandageGirl;
import it.unibo.model.entity.target.BandageGirlImpl;

/**
 * Implementation of the Level interface that reads a tmx file and creates a level from it.
 */
public class LevelImpl implements Level {

    private final TileManager tileManager;
    private final MeatBoy meatBoy;
    private final BandageGirl bandageGirl;
    private final Point2D<Double, Double> meatBoyStartCoord;

    /**
     * Constructs a new instance of LevelImpl with the specified tmx file.
     *
     * @param tmx The URL to the tmx file representing the level.
     */
    public LevelImpl(final String tmx) {
        this.tileManager = new TileManagerFactoryImpl()
            .createTileManager(tmx);
        this.meatBoy = this.tileManager.getMeatBoy();
        this.bandageGirl = this.tileManager.getBandageGirl();
        this.meatBoyStartCoord = new Point2D<>(
            this.meatBoy.getX(),
            this.meatBoy.getY());
    }

    @SuppressFBWarnings(value = "EI", justification =
                "Justification for Suppressing SpotBugs Warning:\r\n"
                + //
                "The SpotBugs warning \"EI\" indicates that the method returns a reference to a mutable object (meatBoy),\r\n"
                + //
                "potentially allowing the caller to modify its internal state. In this context, the\r\n"
                + //
                "method returns the reference to the `MeatBoy` to allow the CollisionHandler to modify its state.\r\n"
                + //
                "This decision is intentional and necessary for the correct functioning of the system.\r\n"
                + //
                "Despite exposing a reference to a mutable object, such exposure is accurate and necessary for\r\n"
                + //
                "the proper functioning of the system, ensuring that the CollisionHandler can directly access\r\n"
                + //
                "the `MeatBoy` to update its state in response to collision events.")
    @Override
    public final MeatBoy getMeatBoy() {
        return this.meatBoy;
    }

    @Override
    public final Point2D<Double, Double> getMeatBoyStartCoord() {
        return this.meatBoyStartCoord.copy();
    }

    @Override
    public final BandageGirl getBandageGirl() {
        return new BandageGirlImpl(
            this.bandageGirl.getX(),
            this.bandageGirl.getY());
    }

    @Override
    public final List<List<Optional<Tile>>> getStationary() {
        return this.tileManager.getStationary();
    }

    @Override
    public final List<Platform> getPlatforms() {
        return this.tileManager.getPlatforms();
    }

    @Override
    public final List<CircularSaw> getSaws() {
        return this.tileManager.getSaws();
    }

    @Override
    public final int getNumCols() {
        return this.tileManager.getNumCols();
    }

    @Override
    public final int getNumRows() {
        return this.tileManager.getNumRows();
    }

}
