package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Singleton used to save and load a game.
 */
public final class LoadingManager {

    private final Path saveDirectory = Paths.get(System.getProperty("user.home"), File.separator, ".risk");

    private static final LoadingManager SINGLETON = new LoadingManager();


    private LoadingManager() {
        if (!Files.exists(this.saveDirectory)) {
            try {
                Files.createDirectory(this.saveDirectory);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 
     * @return The instance of the LoadingManager
     */
    public static LoadingManager getInstance() {
        return SINGLETON;
    }

    /**
     * 
     * @param memento
     *            The {@link model.ModelMemento} to save.
     * @param fileName
     *            The name of the file in which need to be saved.
     */
    public void saveGame(final ModelMemento memento, final String fileName) {
        try {
            final FileOutputStream fileOut = new FileOutputStream(this.saveDirectory.toString() + File.separator + fileName + ".rsk");
            final ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(memento);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * 
     * @param file
     *          The path where the file is stored.
     * @return
     *          The {@link model.ModelMemento} saved in that file.
     */
    public ModelMemento loadGame(final File file) {
        ModelMemento memento = null;
        try {
            final FileInputStream fileIn = new FileInputStream(file);
            final ObjectInputStream in = new ObjectInputStream(fileIn);
            memento = (ModelMemento) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        }
        return memento;
    }

    /**
     * @return 
     *          The path of the saving directory as a string.
     */
    public String getSaveDirectory() {
        return this.saveDirectory.toString();
    }
}
