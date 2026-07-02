package it.unibo.plantsfarm.model.player;

import static it.unibo.plantsfarm.model.player.PlayersTypes.EXPERTFARMER;
import static it.unibo.plantsfarm.model.player.PlayersTypes.FARMER;

import it.unibo.plantsfarm.model.items.InventoryFactory;
import it.unibo.plantsfarm.model.player.api.Player;
import it.unibo.plantsfarm.model.player.api.FactoryPlayer;

/**
 * Implementation of the {@link FactoryPlayer} interface used to create players.
 */
public final class ImplFactoryPlayer implements FactoryPlayer {

    private final InventoryFactory factoryInventory = new InventoryFactory();

    /**
     * Creates a player of the specified type.*
     * The {@code EXPERTFARMER} type can be used to test the application
     * with all items already upgraded.
     * The {@code FARMER} type can create a normal Player with base item not
     * upgraded.
     *
     * @param request the type of player to create
     * @return the created player
     * @throws IllegalArgumentException if the player type is not supported
     */
    @Override
    public Player createPlayer(final PlayersTypes request) {
        if (request == FARMER) {
            return new FarmerPlayer(factoryInventory.createInventory(request), request);
        } else if (request == EXPERTFARMER) {
            return new ExpertFarmer(factoryInventory.createInventory(request), request);
        } else {
            throw new IllegalArgumentException("Player Type: " + request + " NOT FOUND");
        }
    }
}
