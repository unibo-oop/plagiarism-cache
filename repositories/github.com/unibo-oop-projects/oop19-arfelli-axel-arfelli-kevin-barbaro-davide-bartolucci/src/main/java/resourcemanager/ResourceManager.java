package resourcemanager;

import java.net.URL;
import java.util.Optional;
import filetypes.Leaderboard;
import filetypes.Settings;
import javafx.scene.image.Image;

/**
 * This interface provides various methods for accessing resources
 * located in the resources folder or in the user's folder.
 */
public interface ResourceManager {

    /**
     * Looks for fileName in the "images" folder.
     * @param fileName the wanted file's name including the extension (e.g.: .png)
     * @return the specified image file.
     */
    Image getImage(String fileName);

    /**
     * Returns the game settings as a Settings object.
     * @return the game settings.
     */
    Settings getSettingsAsObject();

    /**
     * Updates ResourceManager's own instance of Settings and writes it to file.
     * @param settings the settings to be saved.
     */
    void saveSettings(Settings settings);

    /**
     * Sets the settings istance's fields to default values.
     */
    void setDefaultSettings();

    /**
     * Returns the game leaderboard as a filetypes.Leaderboard object.
     * @return the game leaderboard.
     */
    Leaderboard getLeaderboardAsObject();

    /**
     * Updates ResourceManager's own instance of Leaderboard and writes it to file.
     * @param leaderboard the leaderboard to be saved.
     */
    void saveLeaderboard(Leaderboard leaderboard);

    /**
     * Looks for "fileName" in the "FXML" resource folder and returns its URL, if found. If not, it returns an empty optional.
     * @param fileName the wanted FXML file's name, including the extension.
     * @return an Optional containing the URL of the wanted FXML file.
     */
    Optional<URL> getFXMLFileURL(String fileName);
}
