package net.pokemonbt.controller.resources;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;

import net.pokemonbt.controller.battle.BattleEnvironment;
import net.pokemonbt.controller.resources.GameSession.GameSpeed;
import net.pokemonbt.model.pokemon.TeamType;

/**
 * Class used for saving and loading games.
 */
public final class LoadoutManager {
    private static final String SAVES_DIR = "saves";
    private static final String SAVE = "Save_File.txt";
    private static final String SETTINGS = "Settings.txt";

    private static final File SAVE_FILE = convertToPath(SAVES_DIR, SAVE).toFile();
    private static final File SETTINGS_FILE = convertToPath(SAVES_DIR, SETTINGS).toFile();

    private LoadoutManager() { }

    /**
     * @param s A series of Strings to build the {@link Path} from.
     * @return The {@link Path} 
     */
    private static Path convertToPath(final String... s) {
        final String output = System.getProperty("user.dir");

        return FileSystems.getDefault().getPath(output, s);
    }

    /**
     * Creates the given File and it's parent Directory (if Missing).
     * 
     * @param file The {@link File} to be created. If it already 
     *      exists nothing will happen.
     * @throws IllegalStateException If for any reason the {@link File} could
     *      not be created.
     */
    private static void createFile(final File file) {
        try {
            final var parentDir = SAVE_FILE.getParentFile();

            if (
                Objects.nonNull(parentDir)
                && !parentDir.mkdir()
                && Files.notExists(parentDir.toPath())
            ) {
                throw new IllegalStateException();
            }
            if (
                !file.createNewFile()
                && Files.notExists(file.toPath())
            ) {
                throw new IllegalStateException();
            }
        } catch (final IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Writes the save file freezing the state of the game, it can then
     *      be loaded in the future with the appropriate method.
     * 
     * @param be The {@link BattleEnvironment} to write in the save file.
     * @return 'true' on a successful save. 'false' otherwise.
     */
    public static boolean saveGame(final BattleEnvironment be) {
        createFile(SAVE_FILE);
        try (
            OutputStream file = new FileOutputStream(SAVE_FILE);
            OutputStream bstream = new BufferedOutputStream(file);
            ObjectOutputStream ostream = new ObjectOutputStream(bstream);
        ) {

            ostream.writeObject(Objects.requireNonNull(be));

        } catch (final IOException e) {
            return false;
        }
        return true;
    }

    /**
     * Loads an old game from the save files.
     * 
     * @return 'true' when the file exists and is successfully read, 'false' otherwise.
     */
    public static Optional<BattleEnvironment> loadGame() {
        try (
            InputStream file = new FileInputStream(SAVE_FILE);
            InputStream bstream = new BufferedInputStream(file);
            ObjectInputStream ostream = new ObjectInputStream(bstream);
        ) {
            final var loadedBE = (BattleEnvironment) ostream.readObject();

            return Optional.of(
                new BattleEnvironment(
                    loadedBE.getAllPokemons(TeamType.PLAYER).values(), 
                    loadedBE.getAllPokemons(TeamType.ENEMY).values(),
                    loadedBE.getCurrentOwn(TeamType.PLAYER).getID(),
                    loadedBE.getCurrentOwn(TeamType.ENEMY).getID()
                )
            );

        } catch (final IOException | ClassNotFoundException e) {
            return Optional.empty();
        }
    }

    /**
     * Checks the folder of the save file.
     * 
     * @return 'true' if the file is missing. 'false' otherwise.
     */
    public static boolean saveFileMissing() {
        return Files.notExists(SAVE_FILE.toPath());
    }

    /**
     * Tries to delete a save file.
     * 
     * @return 'true' on a successful deletion. 'false' otherwise.
     */
    public static boolean deleteSaveFile() {
        return SAVE_FILE.delete();
    }

    /**
     * Tries to save the modified settings.
     * 
     * @return 'true' when the settings are successfully saved. 'false' otherwise.
     */
    public static boolean saveSettings() {
        createFile(SETTINGS_FILE);
        try (
            OutputStream file = new FileOutputStream(SETTINGS_FILE);
            OutputStream bstream = new BufferedOutputStream(file);
            ObjectOutputStream ostream = new ObjectOutputStream(bstream);
        ) {

            ostream.writeInt(GameSession.getEnemyTeamSize());
            ostream.writeObject(GameSession.getSpeed());
            ostream.writeUTF(GameSession.getCurrentEnemyDifficulty());
            ostream.writeUTF(GameSession.getPlayerName());
            ostream.writeUTF(GameSession.getEnemyName());

        } catch (final IOException e) {
            return false;
        }
        return true;
    }

    /**
     * Reads the settings file and sets the values to the last saved ones. 
     *      If the file is empty the default values are kept and ends.
     * 
     * @return 'true' when the file exists and is successfully read, 'false' otherwise.
     */
    public static boolean loadSettings() {
        try (
            InputStream file = new FileInputStream(SETTINGS_FILE);
            InputStream bstream = new BufferedInputStream(file);
            ObjectInputStream ostream = new ObjectInputStream(bstream);
        ) {

            GameSession.setEnemyTeamSize(ostream.readInt());
            GameSession.setSpeed((GameSpeed) ostream.readObject());
            GameSession.setEnemyDifficulty(ostream.readUTF());
            GameSession.setPlayerName(ostream.readUTF());
            GameSession.setEnemyName(ostream.readUTF());

        } catch (final IOException | ClassNotFoundException e) {
            return false;
        }
        return true;
    }
}
