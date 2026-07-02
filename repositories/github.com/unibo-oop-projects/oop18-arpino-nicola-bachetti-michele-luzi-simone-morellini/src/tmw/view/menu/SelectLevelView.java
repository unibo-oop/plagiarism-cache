package tmw.view.menu;

import javafx.fxml.FXMLLoader;
import tmw.view.FxmlFiles;

/**
 * Class of the scene where the level is selected.
 * <p>
 * Extension of {@link tmw.view.menu.GenericMenuViewImpl}
 */
public class SelectLevelView extends GenericMenuViewImpl {

    private static final String SELECT_LEVEL_VIEW = FxmlFiles.SELECT_LEVEL.getFxmlPath();

    /**
     * SelectLevelView constructor.
     */
    public SelectLevelView() {
        super();
        this.setLoader(new FXMLLoader(getClass().getResource(SELECT_LEVEL_VIEW)));
    }
}

