package tmw.controller.menu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Dimension2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import tmw.controller.MainController;
import tmw.controller.StageController;
import tmw.view.menu.GenericMenuView;
import tmw.view.menu.OptionView;

/**
 * Class that permit to chose game options.
 * <p>
 * implements {@link OptionController}
 *
 */
public class OptionControllerImpl implements OptionController {

    private static final String FIRST_RES = 640 + "x" + 480;
    private static final String SECOND_RES = 800 + "x" + 600;
    private static final String THIRD_RES = 1024 + "x" + 700;
    private static final String FOURTH_RES = 1280 + "x" + 720;
    private static final String FIFTH_RES = 1920 + "x" + 1080;
    private static final String SIXTH_RES = 3840 + "x" + 2160;
    private static final String VISUAL_RES = String.valueOf((int) Screen.getPrimary().getVisualBounds().getWidth() + "x"
            + (int) Screen.getPrimary().getVisualBounds().getHeight());
    private static final ObservableList<String> LIST = FXCollections.observableArrayList(FIRST_RES, SECOND_RES,
            THIRD_RES, FOURTH_RES, FIFTH_RES, SIXTH_RES, VISUAL_RES);

    private final StageController stageController;
    private final MainController mainController;
    private final GenericMenuView optionView;
    private OptionsSettings currentOptions;
    private double currentWidth = Screen.getPrimary().getVisualBounds().getWidth();
    private double currentHeight = Screen.getPrimary().getVisualBounds().getHeight();
    @FXML
    private BorderPane borderPane;
    @FXML
    private Button saveButton;
    @FXML
    private Button backButton;
    @FXML
    private CheckBox muteCheckBox;
    @FXML
    private Slider volumeSlider;
    @FXML
    private ChoiceBox<String> resolution;

    /**
     * Public constructor.
     * 
     * @param stageController {@link StageController}
     * @param controller      {@link MainController}
     */
    public OptionControllerImpl(final StageController stageController, final MainController controller) {
        this.stageController = stageController;
        this.mainController = controller;
        optionView = new OptionView();
        optionView.getLoader().setController(this);
        optionView.init();
        currentOptions = mainController.getOptionsSettings();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.stageController.setScene(optionView.getScene());
        resetSettings(currentOptions);
        this.resolution.setItems(LIST);
        this.resolution.setValue(VISUAL_RES);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void muteVolume() {
        if (muteCheckBox.isSelected()) {
            volumeSlider.setDisable(true);
            mainController.getAudioController().muteVolume(true);
            mainController.getOptionsSettings().setMute(true);
            mainController.getOptionsSettings().setDisable(true);
        } else {
            volumeSlider.setDisable(false);
            mainController.getAudioController().muteVolume(false);
            mainController.getOptionsSettings().setMute(false);
            mainController.getOptionsSettings().setDisable(false);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void goBack() {
        new MenuControllerImpl(this.stageController, this.mainController).start();
        mainController.setOptionsSettings(currentOptions);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save() {
        muteVolume();
        setVolume();
        setResolution();
        this.currentOptions = new OptionsSettings(volumeSlider.getValue(), muteCheckBox.isSelected(),
                volumeSlider.isDisable(), currentWidth, currentHeight);
        this.mainController.getView().setGameResolution(currentWidth, currentHeight);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVolume() {
        mainController.getAudioController().setVolume(volumeSlider.getValue());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetSettings(final OptionsSettings settings) {
        volumeSlider.setValue(settings.getVolume());
        muteCheckBox.setSelected(settings.isMute());
        volumeSlider.setDisable(settings.isDisable());

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setResolution() {
        final Dimension2D resolution;
        final Rectangle2D screenRes = Screen.getPrimary().getVisualBounds();
        if (!this.resolution.getValue().isEmpty()) {
            final String[] splittedValues = this.resolution.getValue().split("x");
            resolution = new Dimension2D(Double.parseDouble(splittedValues[0]), Double.parseDouble(splittedValues[1]));
        } else {
            resolution = new Dimension2D(screenRes.getWidth(), screenRes.getHeight());
        }
        if (resolution.getWidth() > screenRes.getWidth() || resolution.getHeight() > screenRes.getHeight()) {
            this.currentWidth = screenRes.getWidth();
            this.currentHeight = screenRes.getHeight();
        } else {
            this.currentWidth = resolution.getWidth();
            this.currentHeight = resolution.getHeight();

        }
    }

}
