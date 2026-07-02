package it.unibo.plantsfarm.model.tiles;

import java.awt.Rectangle;
import java.io.Serializable;

import it.unibo.plantsfarm.model.plant.PlantImpl;
import it.unibo.plantsfarm.model.tiles.api.Soil;

/**
 * Represents a soil tile in the game, which can be planted with a plant.
 */
public final class SoilImpl implements Serializable, Soil {

    private static final long serialVersionUID = 1L;

    private final Rectangle coordinatePod;
    private final int tileId;
    private boolean isPlanted;
    private PlantImpl plant;

    /**
     * Creates a new Soil tile with the specified coordinates and tile ID.
     * 
     * @param coordinatePod The coordinates of the soil tile as a Rectangle.
     * @param tileId The ID of the tile, used to identify the type of soil.
     */
    public SoilImpl(final Rectangle coordinatePod, final int tileId) {
        this.coordinatePod = new Rectangle(coordinatePod);
        this.tileId = tileId;
    }

    /**
     * Gets the coordinates of the soil tile.
     * 
     * @return The coordinates of the soil tile as a Rectangle.
     */
    @Override
    public Rectangle getCoordinate() {
        return new Rectangle(this.coordinatePod);
    }

    /**
     * Plants a plant in the soil tile.
     * 
     * @param selectedPlant The plant to be planted in the soil tile.
     */
    @Override
    public void setPlanted(final PlantImpl selectedPlant) {
        this.isPlanted = true;
        this.plant = new PlantImpl(selectedPlant.getType());
    }

    /**
     * Checks if the soil tile is planted with a plant.
     * 
     * @return true if the soil tile is planted, false otherwise.
     */
    @Override
    public boolean isPlanted() {
        return this.isPlanted;
    }

    /**
     * Gets the plant planted in the soil tile.
     * 
     * @return The plant planted in the soil tile, or null if none.
     */
    @Override
    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings(
        value = "EI_EXPOSE_REP", 
        justification = "The controller needs the original reference to update the plant's "
                      + "growth and water status in real-time."
    )
    public PlantImpl getPlant() {
        return this.plant;
    }

    /**
     * Gets the tile ID of the soil tile.
     * 
     * @return The tile ID of the soil tile.
     */
    @Override
    public int getTileId() {
        return this.tileId;
    }

    /**
     * Removes the plant from the soil tile, making it unplanted.
     */
    @Override
    public void removePlant() {
        this.plant = null;
        this.isPlanted = false;
    }
}
