package tmw.view.hud;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import tmw.view.FxmlFiles;

/**
 * Class of the hud scene.
 */
public class HudView {

    private static final String HUD_VIEW = FxmlFiles.HUD.getFxmlPath();
    private final FXMLLoader loader = new FXMLLoader(getClass().getResource(HUD_VIEW));

    /**
     * @return a {@link Parent} containing the hud
     */
    public Parent getHud() {
        try {
            return this.loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @return the FXMLLoader which contains the fxml file of the hud
     */
    public FXMLLoader getLoader() {
        return this.loader;
    }
}

