package view.main_menu;

import java.util.Arrays;

import controller.MasterControllerImpl;
import javafx.fxml.FXML;
import view.MasterViewImpl;

/**
 * Javafx implementation of {@link MainMenuUI}.
 */
public final class MainMenuUIImpl implements MainMenuUI {

    @FXML
    @Override
    public void newGameSetup() {
        MasterViewImpl.getInstance().showNavySetup();
    }
    @FXML
    @Override
    public void newGameClassic() {
        MasterControllerImpl.getInstance().setNavyBuilderSpecification(Arrays.asList(4, 3, 2, 1), 10);
    }

    @FXML
    @Override
    public void statistics() {
        MasterControllerImpl.getInstance().showGobalStatistics();
    }

    @FXML
    @Override
    public void credits() {
        MasterControllerImpl.getInstance().credits();
    }
}
