package it.unibo.plantsfarm.model.items;

import static it.unibo.plantsfarm.model.player.PlayersTypes.EXPERTFARMER;
import static it.unibo.plantsfarm.model.player.PlayersTypes.FARMER;
import java.util.LinkedHashMap;
import java.util.Map;
import it.unibo.plantsfarm.model.inventario.ModelInventario;
import it.unibo.plantsfarm.model.items.api.ItemsFarm;
import it.unibo.plantsfarm.model.items.api.ItemsFarm.Tooltype;
import it.unibo.plantsfarm.model.player.PlayersTypes;

/**
 * Creates the inventory for the player.
 */
public final class InventoryFactory {

    /**
     * Creates and initializes an inventory according to the given player type.
     * ExpertFarmer offer a the possibility to play the game without limitation (usefull to test  all featurs).
     * Farmer offer the standard game with slower progression.
     *
     * @param typePlayer the type of player for which the inventory is created
     * @return a fully initialized ModelInventario
     * @throws IllegalArgumentException if the player type is not supported
     */
    public ModelInventario createInventory(final PlayersTypes typePlayer) {
        if (typePlayer == EXPERTFARMER) {
            final Map<Tooltype, ItemsFarm> items = new LinkedHashMap<>();
            items.put(Tooltype.AXE, new ItemsExpert(Tooltype.AXE));
            items.put(Tooltype.WATERCAN, new ItemsExpert(Tooltype.WATERCAN));
            items.put(Tooltype.HOE, new ItemsExpert(Tooltype.HOE));
            return new ModelInventario(items);

        } else if (typePlayer == FARMER) {
            final Map<Tooltype, ItemsFarm> items = new LinkedHashMap<>();
            items.put(Tooltype.AXE, new ItemsFarmBase(Tooltype.AXE));
            items.put(Tooltype.WATERCAN, new ItemsFarmBase(Tooltype.WATERCAN));
            items.put(Tooltype.HOE, new ItemsFarmBase(Tooltype.HOE));
            return new ModelInventario(items);
        } else {
            throw new IllegalArgumentException("TypePlayer not found ");
        }
    }
}

