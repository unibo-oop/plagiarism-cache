package it.unibo.plantsfarm.model.garden;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import it.unibo.plantsfarm.model.tiles.SoilImpl;

/**
 * Handles the saving and loading of soil data for the game.
 */
public final class SoilSaving {

    private static final Logger LOGGER = Logger.getLogger(SoilSaving.class.getName());
    private final String filePath = System.getProperty("user.home") + File.separator + ".plantsfarm" + File.separator + "plants";

    /**
     * Saves the current state of the soil list to a file.
     *
     * @param pod The list of Soil objects to be saved.
     */
    public void saveGame(final List<SoilImpl> pod) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.filePath))) {
            oos.writeObject(pod);
        } catch (final IOException e) {
            LOGGER.log(Level.SEVERE, "Errore durante il salvataggio dei soils.", e);
        }
    }

    /**
     * Loads the state of the soil list from a file.
     *
     * @return A list of Soil objects loaded from the file, or an empty list if loading fails.
     */
    public List<SoilImpl> loadGame() {
        final File file = new File(this.filePath);

        if (!file.exists()) {
            LOGGER.log(Level.INFO, "Nessun salvataggio trovato.");
            return List.of();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            final Object obj = ois.readObject();

            if (obj instanceof List<?>) {
                final List<SoilImpl> checkedList = new LinkedList<>();
                for (final Object item : (List<?>) obj) {
                    if (item instanceof SoilImpl) {
                        checkedList.add((SoilImpl) item);
                    }
                }
                return checkedList;
            }
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Errore durante il caricamento dei soils.", e);
        }
        return List.of();
    }

    /**
     * Resets the saved game data by deleting the save file.
     */
    public void reset() {
        final File file = new File(this.filePath);
        if (file.exists()) {
            if (file.delete()) {
                LOGGER.log(Level.INFO, "Salvataggio eliminato con successo.");
            } else {
                LOGGER.log(Level.SEVERE, "Impossibile eliminare il salvataggio.");
            }
        }
    }
}
