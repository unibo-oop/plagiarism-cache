package tmw.controller.menu;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import tmw.controller.MainController;
import tmw.controller.StageController;
import tmw.controller.level.SelectLevelControllerImpl;
import tmw.view.menu.GenericMenuView;
import tmw.view.menu.MenuView;

/**
 * Class that manage the main menu.
 * <p>
 * implements {@link MenuController}
 *
 */
public class MenuControllerImpl implements MenuController {

    private final StageController stageController;
    private final MainController mainController;
    private final GenericMenuView menuView;
    @FXML
    private Button playButton;
    @FXML
    private Button optionButton;
    @FXML
    private Button exitButton;
    @FXML
    private GridPane gridPane;

    /**
     * Public constructor.
     * 
     * @param stageController stage controller
     * @param controller      main controller
     */
    public MenuControllerImpl(final StageController stageController, final MainController controller) {
        this.stageController = stageController;
        this.mainController = controller;
        menuView = new MenuView();
        menuView.getLoader().setController(this);
        menuView.init();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.stageController.setScene(menuView.getScene());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toLevelMenu() {
        new SelectLevelControllerImpl(stageController, mainController).start();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toOption() {
        final OptionController opC = new OptionControllerImpl(stageController, mainController);
        opC.start();
        opC.resetSettings(mainController.getOptionsSettings());

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void quit() {
        this.mainController.exit();
    }

}
