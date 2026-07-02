package it.unibo.plantsfarm.controller.garden;

import java.awt.Rectangle;
import java.util.List;

import it.unibo.plantsfarm.model.plant.PlantImpl;
import it.unibo.plantsfarm.model.plant.PlantType;
import it.unibo.plantsfarm.model.garden.GardenModel;
import it.unibo.plantsfarm.model.menu.impl.GameStateImpl;
import it.unibo.plantsfarm.model.player.api.Player;
import it.unibo.plantsfarm.model.tiles.SoilImpl;
import it.unibo.plantsfarm.model.tiles.api.Soil;

/**
 * Controller for garden logic.
 */
public class GardenController {
    private static final int ORNAMENTAL_SOIL = 2;

    private GameStateImpl gameState;
    private Player player;
    private GardenModel gardenModel;

    /**
     * Creates a new GardenController.
     *
     * @param gameState The game state.
     * @param player    The player.
     */
    public GardenController(final GameStateImpl gameState, final Player player) {
        setGameState(gameState);
        setPlayer(player);
        setGardenModel();
    }

    /**
     * Water the plant.
     *
     * @param now The current time in milliseconds.
     */
    public final void innaffia(final long now) {
        final SoilImpl soil = whichSoilIsPlayerOn(player.getHitBox());
        if (soil.isPlanted()) {
            soil.getPlant().water(now);
        }
    }

    /**
     * Update the garden model.
     *
     * @param now The current time in milliseconds.
     * 
     * @return true if at least one plant grew, false otherwise.
     */
    public final boolean updateSoil(final Long now) {
        return this.gardenModel.updateGame(now); 
    }

    /**
     * Plant or harvest.
     *
     * @param type The plant type selected.
     */
    public final void pianta(final PlantType type) {
        if (type != null) {
            final PlantImpl pianta = new PlantImpl(type);
            final SoilImpl soil = whichSoilIsPlayerOn(player.getHitBox());
            if (!soil.isPlanted()
                && soil.getTileId() == ORNAMENTAL_SOIL
                && !type.isEdible()
                || soil.getTileId() != ORNAMENTAL_SOIL
                && type.isEdible()
            ) {
                soil.setPlanted(pianta);
            }
        }
    }

    /**
     * Harvest the plant if it's mature.
     */
    public final void harvest() {
        final Soil soil = whichSoilIsPlayerOn(player.getHitBox());
        if (soil.isPlanted()
            && soil.getPlant().isMature()) {
            gameState.addHarvest(soil.getPlant().getType(), soil.getPlant().harvest());
        }
    }

    /**
     * Give the list of all soil tiles in the garden.
     *
     * @return The list of soil objects.
     */
    public final List<SoilImpl> getSoilList() {
        return gardenModel.getSoils();
    }

    /**
     * Checks if the player is on a soil tile.
     *
     * @param hitbox The hitbox of the player.
     *
     * @return True if the player is on soil, false otherwise.
     */
    public SoilImpl whichSoilIsPlayerOn(final Rectangle hitbox) {
        for (final SoilImpl soilRect : gardenModel.getSoils()) {
            if (hitbox.intersects(soilRect.getCoordinate()) || soilRect.getCoordinate().contains(hitbox)) {
                return soilRect;
            }
        }
        return null;
    }

    private void setGameState(final GameStateImpl givenGameState) {
        this.gameState = givenGameState;
    }

    private void setPlayer(final Player givenPlayer) {
        this.player = givenPlayer;
    }

    private void setGardenModel() {
        this.gardenModel = new GardenModel();
    }
}
