package it.unibo.model;

import java.util.List;
import java.util.Optional;

import it.unibo.commons.Point2D;
import it.unibo.controller.GameController;
import it.unibo.model.collision.CollisionHandler;
import it.unibo.model.collision.CollisionHandlerImpl;
import it.unibo.model.entity.obstacles.CircularSaw;
import it.unibo.model.entity.obstacles.Platform;
import it.unibo.model.entity.player.MeatBoy;
import it.unibo.model.entity.target.BandageGirl;
import it.unibo.model.level.Level;
import it.unibo.model.level.factory.LevelFactoryImpl;
import it.unibo.model.tiles.Tile;

/**
 * The GameModelImpl class implements the GameModel interface and represents the model
 * component of the game, providing access to game entities and tiles.
 */
public class GameModelImpl implements GameModel {

    private final Level level;
    private final CollisionHandlerImpl collisionHandler;
    private final GameController gameController;

    /**
     * Constructs a new instance of the GameModel.
     * This constructor initializes the game model with the specified Path to the game map.
     *
     * @param tmx The path to the TMX file representing the game map.
     * @param gameController The game controller.
     */
    public GameModelImpl(final String tmx, final GameController gameController) {
        this.level = new LevelFactoryImpl().createLevel(tmx);
        this.collisionHandler = new CollisionHandlerImpl(this);
        this.gameController = gameController;
    }

    @Override
    public final List<List<Optional<Tile>>> getStationary() {
        return this.level.getStationary();
    }

    @Override
    public final List<CircularSaw> getSaws() {
        return this.level.getSaws();
    }

    @Override
    public final List<Platform> getPlatforms() {
        return this.level.getPlatforms();
    }

    @Override
    public final BandageGirl getBandageGirl() {
        return this.level.getBandageGirl();
    }

    @Override
    public final MeatBoy getMeatBoy() {
        return this.level.getMeatBoy();
    }

    @Override
    public final Point2D<Double, Double> getMeatBoyStartCoord() {
        return this.level.getMeatBoyStartCoord();
    }

    @Override
    public final int getNumRows() {
        return this.level.getNumRows();
    }

    @Override
    public final int getNumCols() {
        return this.level.getNumCols();
    }

    @Override
    public final CollisionHandler getCollisionHandler() {
        return this.collisionHandler;
    }

    /**
     * Signals a victory event.
     * This method is called to indicate that the game has been won.
     */
    @Override
    public void victory() {
        this.gameController.victory();
    }

    /**
     * Initializes the coordinates.
     * This method is responsible for setting up initial coordinates or resetting them to their default values.
     */
    @Override
    public void initializeCoords() {
        this.getMeatBoy().setX(this.getMeatBoyStartCoord().getX());
        this.getMeatBoy().setY(this.getMeatBoyStartCoord().getY());
        this.getCollisionHandler().initializeStates();
    }

    /**
     * This method is called to indicate that the player collides with an obstacles or fall from the map and died.
     */
    @Override
    public void died() {
        initializeCoords();
        this.gameController.isDead();
    }

}
