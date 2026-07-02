package tmw.view.menu;

import javafx.fxml.FXMLLoader;
import tmw.view.FxmlFiles;

/**
 * Class of the GameOver scene.
 * <p>
 * Extension of {@link GenericMenuViewImpl}
 */
public class GameOverView extends GenericMenuViewImpl {

    private static final String GAME_OVER = FxmlFiles.GAME_OVER.getFxmlPath();

    /**
     * GameOver constructor.
     */
    public GameOverView() {
        super();
        this.setLoader(new FXMLLoader(getClass().getResource(GAME_OVER)));

    }

}
