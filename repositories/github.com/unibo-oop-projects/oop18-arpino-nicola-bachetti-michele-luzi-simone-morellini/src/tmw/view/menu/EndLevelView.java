package tmw.view.menu;

import javafx.fxml.FXMLLoader;
import tmw.view.FxmlFiles;

/**
 * Class of the end level scene.
 * <p>
 * Extension of {@link tmw.view.menu.GenericMenuViewImpl}
 */
public class EndLevelView extends GenericMenuViewImpl {

    private static final String END_LEVEL_VIEW = FxmlFiles.END_LEVEL.getFxmlPath();

    /**
     * EndLevelView constructor.
     */
    public EndLevelView() {
        super();
        this.setLoader(new FXMLLoader(getClass().getResource(END_LEVEL_VIEW)));
    }
}

