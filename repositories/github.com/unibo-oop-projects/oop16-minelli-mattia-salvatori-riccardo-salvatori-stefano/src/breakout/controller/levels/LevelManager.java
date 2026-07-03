package breakout.controller.levels;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import breakout.model.levels.DecoratedLevel;
import breakout.model.levels.LevelImpl;
import breakout.view.utils.Utils;

/**
 * This class implements some methods to save,load and delete levels.
 */
public final class LevelManager {

    private static final String LEVELS_DIR = System.getProperty("user.home") + File.separator + ".breakout"
            + File.separator + "Levels";
    private final File dir = new File(LEVELS_DIR);
    private final List<DecoratedLevel> availableLevels = new ArrayList<>();

    /**
     * Create Level Manager and the folder to contain all the level.
     */
    public LevelManager() {
        if (!this.dir.exists()) {
            this.dir.mkdir();
        }
        // Copia nella directory i livelli di default
        try {
            Files.copy(Utils.getResStream("/Level1"), Paths.get(dir.getAbsolutePath() + File.separator + "Level1"),
                    StandardCopyOption.REPLACE_EXISTING);
            Files.copy(Utils.getResStream("/Level2"), Paths.get(dir.getAbsolutePath() + File.separator + "Level2"),
                    StandardCopyOption.REPLACE_EXISTING);
            Files.copy(Utils.getResStream("/Level3"), Paths.get(dir.getAbsolutePath() + File.separator + "Level3"),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println("error while copying default levels");
        }
    }

    /**
     * Save the new Level in the folder.
     * 
     * @param level
     *            the level to be saved.
     * @throws IOException
     *             I/O error
     * 
     */

    public void saveLevel(final DecoratedLevel level) throws IOException {
        final File file = new File(LEVELS_DIR + File.separator + level.getName());
        final ObjectOutputStream levelWriter = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(file)));
        levelWriter.writeObject(level);
        levelWriter.flush();
        levelWriter.close();
        this.availableLevels.add(level);
    }

    /**
     * Load a saved Level from the folder.
     * 
     * @param fileName
     *            the name of the level to load.
     * @return a LevelEditorImpl with the same name to the fileName
     * @throws IOException
     * 
     * @throws ClassNotFoundException
     *             Type casting exception
     * @throws IOException
     *             I/0 exception
     * 
     */
    public LevelImpl loadLevel(final String fileName) throws IOException, ClassNotFoundException {
        final File file = new File(LEVELS_DIR + File.separator + fileName);
        final ObjectInputStream levelReader = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
        final LevelImpl level = (LevelImpl) levelReader.readObject();
        levelReader.close();
        return level;
    }

    /**
     * Delete a Level from the folder.
     * 
     * @param fileName
     *            the name of the level to delete
     */
    public void deleteLevel(final String fileName) {
        final File file = new File(LEVELS_DIR + File.separator + fileName);
        this.availableLevels.stream().filter(level -> level.getName().equals(fileName)).findFirst()
                            .ifPresent(level -> this.availableLevels.remove(level));
        file.delete();
    }

    /**
     * Loads all levels from "../.breakout/Levels" folder.
     * 
     * @return the list of levels
     */
    public List<DecoratedLevel> loadAllLevels() {
        for (final String file : dir.list()) {
            try {
                this.availableLevels.add(this.loadLevel(file));
            } catch (ClassNotFoundException | IOException e) {
                System.out.println("error while reading file from levels folder (user/.breakout/)");
            }
        }
        return availableLevels;
    }

}
