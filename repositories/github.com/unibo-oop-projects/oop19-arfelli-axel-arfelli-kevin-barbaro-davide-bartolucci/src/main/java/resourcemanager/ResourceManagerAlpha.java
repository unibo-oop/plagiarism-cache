package resourcemanager;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import filetypes.Settings;
import filetypes.SettingsV1;
import gameengine.GameLogger;
import gameengine.GameLoggerAdaptor;
import gameengine.GameLogger.OutputLevel;
import filetypes.Leaderboard;
import filetypes.LeaderboardImpl;

/**
 * An implementation of the ResourceManager interface.
 * This class is a Singleton.
 * Because of the fact that this class has its own instances of Settings and Leaderboard,
 * there cannot be more than one instance of ResourceManagerAlpha at a time, otherwise
 * there would be some inconsistencies when saving those objects as files.
 */
public final class ResourceManagerAlpha implements ResourceManager {

    private static final String SEPARATOR = File.separator;
    private static final String LEADERBOARD_FILE_NAME = "leaderboard";
    private static final String SETTING_FILE_NAME = "settings";
    private static final String IMAGES_PATH = SEPARATOR + "images" + SEPARATOR;
    private static final String FXML_PATH = SEPARATOR + "FXML" + SEPARATOR;
    private static final Integer PICTURE_SIZE = 512;
    private final String userPath;
    private Settings settings;
    private Leaderboard leaderboard;
    private static ResourceManager instance;     //Serve da riferimento all'istanza per SINGLETON
    private final GameLogger logger = new GameLoggerAdaptor();

    //Costruttore, privato per via dell'architettura SINGLETON.
    private ResourceManagerAlpha() {
        userPath = System.getProperty("user.home").concat(SEPARATOR).concat("stardust-invaders").concat(SEPARATOR);
        //logger.logLine("User preferences folder: " + userPath, OutputLevel.LOG);
        final boolean success = new File(userPath).mkdirs();
        if (!success) {
            logger.logLine("Folder not created: it may already exist.", OutputLevel.DEBUG);
        }
        this.checkSettings();
        this.checkLeaderboard();
    }

    //Metodo per ottenere l'istanza di questa classe.
    public static synchronized ResourceManager getIstance() {
        if (instance == null) {
            instance = new ResourceManagerAlpha();
        }
        return instance;
    }

    /**
     * This method tries to read an object that has been serialized, which path is defined by filePath.
     * If it succeeds, the wanted file is returned as Object, otherwise null is returned.
     * @param filePath the path where to look for the file.
     * @return Object obj, the wanted object. null in case something fails.
     */
    private Object readObjectFromFile(final String filePath) {
        Object obj;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(Paths.get(filePath)))) {
            try {
                obj = objectInputStream.readObject();
            } catch (ClassNotFoundException ex1) {
                logger.logLine("Class of the serialized object could not be found.", OutputLevel.ERROR);
                obj = null;
            }
            //logger.logLine("The Object has been read from the file", OutputLevel.LOG);
        } catch (IOException ex2) {
            logger.logLine("There has been an error while opening the given object file. (" + filePath + "). Such file may not exist.", OutputLevel.ERROR);
            obj = null;
        }
        return obj;
    }

    @Override
    public Image getImage(final String fileName) {
        //logger.logLine("getImage: " + IMAGES_PATH + fileName, OutputLevel.DEBUG);
        //InputStream targetStream = new FileInputStream(this.getFile(fileName, IMAGES_PATH));
        Image tmpImage = null;
        final URL res = Thread.currentThread().getContextClassLoader().getResource(IMAGES_PATH + fileName);
        if (res == null) {
            logger.logLine("Failed to load " + fileName + ". There might be no file with such name.", OutputLevel.ERROR);
            return this.drawImage();
        } else {
            try {
                tmpImage = new Image(res.toURI().toString());
            } catch (URISyntaxException e) {
                logger.logLine("Failed to parse URI to String in getImage", OutputLevel.ERROR);
                return this.drawImage();
            }
        }
        return tmpImage;

    }

    @Override
    public Settings getSettingsAsObject() {
        return this.settings;
    }

    @Override
    public void saveSettings(final Settings settings) {
        this.settings = settings;
        this.writeObjectToFile(this.settings, SETTING_FILE_NAME);
    }

    @Override
    public void setDefaultSettings() {
        this.settings.setToDefaultSettings();
        //logger.logLine("Settings object was initialized with default settings", OutputLevel.LOG);
    }

    @Override
    public Leaderboard getLeaderboardAsObject() {
        return this.leaderboard;
    }

    @Override
    public void saveLeaderboard(final Leaderboard leaderboard) {
        this.leaderboard = leaderboard;
        this.writeObjectToFile(this.leaderboard, LEADERBOARD_FILE_NAME);

    }

    @Override
    public Optional<URL> getFXMLFileURL(final String fileName) {
        final URL res = Thread.currentThread().getContextClassLoader().getResource(FXML_PATH + fileName);
        if (res == null) {
            logger.logLine("There has been an error while loading " + fileName + ". Such file may not exist", OutputLevel.ERROR);
        }
        return Optional.ofNullable(res);
    }

    /**
     * Writes the given object to file, whose name is fileName.
     * @param object the object to write on disk.
     * @param fileName the wanted name for the file.
     */
    private void writeObjectToFile(final Object object, final String fileName) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get(userPath + SEPARATOR + fileName)))) {
            objectOutputStream.writeObject(object);
        } catch (IOException ex) {
            logger.logLine("Something went wrong while saving " + fileName + " as a file.", OutputLevel.ERROR);
            ex.printStackTrace();
        }
    }

    /**
     * Checks if there is a "settings" file in the "stardust-invaders" folder, 
     * located in user.home.
     * If there is, the settings file is read by calling readObjectFromFile.
     * If there isn't, it creates a new SettingsV1 object 
     * and calls setDefaultSettings.
     */
    private void checkSettings() {
        final Object tmpSettings = readObjectFromFile(userPath + SETTING_FILE_NAME);
        //logger.logLine("Is Object o istance of settings? " + SettingsV1.class.isInstance(tmpSettings), OutputLevel.LOG);
        if (tmpSettings != null && SettingsV1.class.isInstance(tmpSettings)) {
            this.settings = (SettingsV1) tmpSettings;
        } else {
            this.settings = new SettingsV1();
            this.setDefaultSettings();
        }
    }

    private void checkLeaderboard() {
        final Leaderboard tmpLeaderboard = (LeaderboardImpl) readObjectFromFile(userPath + LEADERBOARD_FILE_NAME);
        this.leaderboard = tmpLeaderboard == null ? new LeaderboardImpl() : tmpLeaderboard;
    }

    /**
     * This method generates a monochromatic WritableImage (magenta)
     * to use as a replacement if something goes wrong with 
     * the loading of certains images from file.
     * @return a monochromatic WritableImage
     */
    private WritableImage drawImage() {
        final WritableImage monochromatic = new WritableImage(PICTURE_SIZE, PICTURE_SIZE);
        final BufferedImage bufferedImg = new BufferedImage(PICTURE_SIZE, PICTURE_SIZE, BufferedImage.TYPE_INT_RGB); 
        for (int y = 0; y < PICTURE_SIZE; y++) {
            for (int x = 0; x < PICTURE_SIZE; x++) {
                bufferedImg.setRGB(x, y, Color.magenta.getRGB()); 
            } 
        }
        javafx.embed.swing.SwingFXUtils.toFXImage(bufferedImg, monochromatic);
        return monochromatic;
    }

}

