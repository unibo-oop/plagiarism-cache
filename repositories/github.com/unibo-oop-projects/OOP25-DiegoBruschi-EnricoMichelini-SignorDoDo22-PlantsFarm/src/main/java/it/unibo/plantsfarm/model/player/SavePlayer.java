package it.unibo.plantsfarm.model.player;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unibo.plantsfarm.model.items.api.ItemsFarm;
import it.unibo.plantsfarm.model.items.api.ItemsFarm.Tooltype;
import it.unibo.plantsfarm.view.utility.Memory;
import it.unibo.plantsfarm.model.inventario.ModelInventario;

/**
 * Handles the saving and loading of player inventory data for the game.
 */
public final class SavePlayer {

    private static final Logger LOGGER = Logger.getLogger(SavePlayer.class.getName());
    private static final String PAIR_SEPARATOR = ";";
    private static final String VALUE_SEPARATOR = "=";
    private final String fileName = System.getProperty("user.home")
            + File.separator + ".plantsfarm"
            + File.separator + "player.txt";

    /**
     * Saves the player's inventory data using the provided Memory implementation.
     *
     * @param memory the Memory implementation to use for saving data
     * @param inventario the player's inventory model containing the items to be saved
     */
    public void save(final Memory memory, final ModelInventario inventario) {
        final StringBuilder sb = new StringBuilder();

        for (final Tooltype tool : Tooltype.values()) {
            final ItemsFarm item = inventario.getItem(tool);
            if (item == null) {
                continue;
            }
            sb.append(tool.name())
              .append(VALUE_SEPARATOR)
              .append(item.getLevel())
              .append(PAIR_SEPARATOR);
        }

        try {
            memory.save(fileName, sb.toString());
        } catch (final IOException e) {
            LOGGER.log(Level.SEVERE, "Could not save player inventory", e);
        }
    }

    /**
     * Loads the player's inventory data using the provided Memory implementation and updates the given inventory model.
     *
     * @param memory the Memory implementation to use for loading data
     * @param inventario the player's inventory model to be updated with the loaded items
     */
    public void load(final Memory memory, final ModelInventario inventario) {
        try {
            final String data = memory.load(fileName);
            if (data == null || data.isBlank()) {
                return;
            }

            final String[] pairs = data.split(PAIR_SEPARATOR);
            for (final String pair : pairs) {
                if (!pair.contains(VALUE_SEPARATOR)) {
                    continue;
                }

                final String[] parts = pair.split(VALUE_SEPARATOR);
                if (parts.length < 2) {
                    continue;
                }

                try {
                    final Tooltype type = Tooltype.valueOf(parts[0]);
                    final int level = Integer.parseInt(parts[1]);
                    inventario.loadItem(type, level);
                } catch (final IllegalArgumentException e) {
                    LOGGER.log(Level.WARNING, "Invalid inventory data: " + pair, e);
                }
            }
        } catch (final IOException e) {
            LOGGER.log(Level.WARNING, "Couldn't load player inventory", e);
        }
    }
}
