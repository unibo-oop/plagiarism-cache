package it.unibo.plantsfarm.controller.player;

import static it.unibo.plantsfarm.model.items.api.ItemsFarm.Tooltype.AXE;
import static it.unibo.plantsfarm.model.items.api.ItemsFarm.Tooltype.HOE;
import static it.unibo.plantsfarm.model.items.api.ItemsFarm.Tooltype.WATERCAN;

import it.unibo.plantsfarm.controller.gamepanel.api.ControllerGamePanel.UserInput;
import it.unibo.plantsfarm.controller.garden.GardenController;
import it.unibo.plantsfarm.controller.garden.SpawningBuffsController;
import it.unibo.plantsfarm.controller.player.api.ActionHandler;
import it.unibo.plantsfarm.model.garden.Buff;
import it.unibo.plantsfarm.model.inventario.ModelInventario;
import it.unibo.plantsfarm.model.player.api.Player;
import it.unibo.plantsfarm.model.tiles.SoilImpl;
import it.unibo.plantsfarm.model.tiles.api.Soil;
import it.unibo.plantsfarm.model.plant.PlantType;

/**
 * This class translates user input into actions performed by the player,
 * delegate the actual game logic to the {@link Player}.
 */
public final class ImplActionHandler implements ActionHandler {

    private Player player;

    /**
     * Setting the player.
     *
     * @param player set the player created
     */
    public ImplActionHandler(final Player player) {
        setPlayer(player);
    }

    @Override
    public void handleActionHoe(final GardenController controllerGarden, final PlantType selectedPlant) {
        final ModelInventario inventory = player.getInventory();

        final SoilImpl soil = controllerGarden.whichSoilIsPlayerOn(player.getHitBox());
        if (controllerGarden.whichSoilIsPlayerOn(player.getHitBox()) != null
            && soil.isPlanted()
            && soil.getPlant().isMature()
            && inventory.useItem(HOE, soil.getPlant().getRarity())
        ) {
            controllerGarden.harvest();
        } else if (selectedPlant != null
            && controllerGarden.whichSoilIsPlayerOn(player.getHitBox()) != null
            && !soil.isPlanted()
            && inventory.useItem(HOE, selectedPlant.getRarity())
        ) {
            controllerGarden.pianta(selectedPlant);
        }
    }

    @Override
    public void handleWater(final GardenController controllerGarden, final Long now, final PlantType selectedPlant) {
        final ModelInventario inventory = player.getInventory();
        final Soil soil = controllerGarden.whichSoilIsPlayerOn(player.getHitBox());
        if (controllerGarden.whichSoilIsPlayerOn(player.getHitBox()) != null
            && soil.getPlant() != null
            && soil.getPlant().needsWater()
            && inventory.useItem(WATERCAN, soil.getPlant().getRarity())
        ) {
            controllerGarden.innaffia(now);
        }
    }

    @Override
    public void handleAxe(final GardenController controllerGarden) {
        final Soil soil = controllerGarden.whichSoilIsPlayerOn(player.getHitBox());
        final ModelInventario inventory = player.getInventory();
        if (controllerGarden.whichSoilIsPlayerOn(player.getHitBox()) != null
            && soil.isPlanted()
            && inventory.useItem(AXE, soil.getPlant().getRarity())
        ) {
            soil.removePlant();
        }
    }

    @Override
    public void updateDirection(final UserInput input) {
        player.setDirection(input);
    }

    @Override
    public boolean playerActionBuff(final SpawningBuffsController controllerbuff) {
        boolean buffCollected = false;
        final ModelInventario inventory = player.getInventory();
        for (final Buff buff : controllerbuff.getBuffList()) {
            if (player.getHitBox().intersects(buff.getBuffPosition()) || buff.getBuffPosition().contains(player.getHitBox())) {
                controllerbuff.removeBuffFromMap(buff);
                inventory.applyUpgrade();
                buffCollected = true;
            }
        }
        return buffCollected;
    }

    private void setPlayer(final Player player) {
        this.player = player;
    }

}
