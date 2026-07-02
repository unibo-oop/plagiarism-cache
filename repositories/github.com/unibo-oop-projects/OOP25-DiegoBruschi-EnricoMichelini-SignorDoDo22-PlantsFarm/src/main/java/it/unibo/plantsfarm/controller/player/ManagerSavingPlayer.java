package it.unibo.plantsfarm.controller.player;

import static it.unibo.plantsfarm.model.player.PlayersTypes.FARMER;

import java.nio.file.Path;

import it.unibo.plantsfarm.model.inventario.ModelInventario;
import it.unibo.plantsfarm.model.player.SavePlayer;
import it.unibo.plantsfarm.model.player.api.Player;
import it.unibo.plantsfarm.view.utility.FileMemory;
import it.unibo.plantsfarm.view.utility.Memory;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manages the saving and loading of player inventory data for the game.
 * This class acts as a controller that interacts with the SavePlayer class to persist player inventory information.
 */
public class ManagerSavingPlayer {

    private static final Logger LOGGER = Logger.getLogger(ManagerSavingPlayer.class.getName());
    private final Path basePath = Path.of(System.getProperty("user.home"), ".plantsfarm");
    private final Memory memory = new FileMemory(basePath);

    /**
     * Loads the player's inventory data if the player is of type FARMER.
     *
     * @param inventarioPlayer the player's inventory model to be updated with the loaded items
     * @param abstractPlayer the player whose inventory is to be loaded
     */
    public void loadManager(final ModelInventario inventarioPlayer, final Player abstractPlayer) {
        final Player player = abstractPlayer;
        final ModelInventario inventario = inventarioPlayer;
        final SavePlayer savePlayer = new SavePlayer();
        if (player.getPlayerType() == FARMER) {
            savePlayer.load(memory, inventario);
        }
    }

    /**
     * Saves the player's inventory data if the player is of type FARMER.
     *
     * @param inventarioPlayer the player's inventory model containing the items to be saved
     * @param abstractPlayer the player whose inventory is to be saved
     */
    public void saveManager(final ModelInventario inventarioPlayer, final Player abstractPlayer) {
        final Player player = abstractPlayer;
        final ModelInventario inventario = inventarioPlayer;
        final SavePlayer savePlayer = new SavePlayer();
        if (player.getPlayerType() == FARMER) {
            savePlayer.save(memory, inventario);
        }
    }

    /**
     * Resets the saved player inventory data by deleting the save file.
     * This method can be used to clear the player's progress and start fresh.
     */
    public void resetSaving() {
        final Path saveFilePath = basePath.resolve("player.txt");
        try {
            java.nio.file.Files.deleteIfExists(saveFilePath);
        } catch (final java.io.IOException e) {
            LOGGER.log(Level.SEVERE, "Errore durante il reset del salvataggio del giocatore.", e);
        }
    }
}
