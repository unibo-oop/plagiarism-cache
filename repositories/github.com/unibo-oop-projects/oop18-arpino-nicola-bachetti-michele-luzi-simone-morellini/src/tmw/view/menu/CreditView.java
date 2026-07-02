package tmw.view.menu;

import javafx.fxml.FXMLLoader;
import tmw.view.FxmlFiles;

/**
 * Class of the credit scene.
 * <p>
 * Extension of {@link tmw.view.menu.GenericMenuViewImpl}
 */
public class CreditView extends GenericMenuViewImpl {

    private static final String CREDIT_VIEW = FxmlFiles.CREDIT.getFxmlPath();

    /**
     * CreditView constructor.
     */
    public CreditView() {
        super();
        this.setLoader(new FXMLLoader(getClass().getResource(CREDIT_VIEW)));
    }
}

