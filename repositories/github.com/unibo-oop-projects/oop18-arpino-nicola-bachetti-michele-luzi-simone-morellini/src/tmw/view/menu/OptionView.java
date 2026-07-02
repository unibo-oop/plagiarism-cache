package tmw.view.menu;

import javafx.fxml.FXMLLoader;
import tmw.view.FxmlFiles;

/**
 * Class of the option scene.
 * <p>
 * Extension of {@link tmw.view.menu.GenericMenuViewImpl}
 */
public class OptionView extends GenericMenuViewImpl {

    private static final String OPTION_VIEW = FxmlFiles.OPTION.getFxmlPath();

    /**
     * OptionView constructor.
     */
    public OptionView() {
        super();
        this.setLoader(new FXMLLoader(getClass().getResource(OPTION_VIEW)));
    }
}

