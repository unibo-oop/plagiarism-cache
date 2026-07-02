package tmw.view.menu;

import javafx.fxml.FXMLLoader;
import tmw.view.FxmlFiles;

/**
 * Class of the menu scene.
 * <p>
 * Extension of {@link GenericMenuViewImpl}
 */
public class MenuView extends GenericMenuViewImpl {

    private static final String MENU_VIEW = FxmlFiles.MENU.getFxmlPath();

    /**
     * MenuView constructor.
     */
    public MenuView() {
        super();
        this.setLoader(new FXMLLoader(getClass().getResource(MENU_VIEW)));
    }
}
