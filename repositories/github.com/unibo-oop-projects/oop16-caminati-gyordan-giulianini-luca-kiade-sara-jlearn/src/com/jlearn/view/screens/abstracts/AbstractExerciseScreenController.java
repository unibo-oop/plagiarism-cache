package com.jlearn.view.screens.abstracts;

import java.util.List;

import org.kordamp.ikonli.material.Material;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.jlearn.controller.ControllerExercise;
import com.jlearn.controller.fileio.FileManagerImpl;
import com.jlearn.view.FXEnvironment;
import com.jlearn.view.factories.GeneralFactory;
import com.jlearn.view.factories.ListViewFactory;
import com.jlearn.view.factories.ScrollPaneFactory;
import com.jlearn.view.screens.FXMLScreens;
import com.jlearn.view.screens.MenuScreenController;
import com.jlearn.view.ui.UIExercise;
import com.jlearn.view.utilities.ViewUtilities;
import com.jlearn.view.utilities.enums.DialogsType.DimDialogs;
import com.jlearn.view.utilities.enums.ExerciseType;
import com.jlearn.view.utilities.enums.IconDim;
import com.jlearn.view.utilities.enums.NotificationType.Duration;
import com.jlearn.view.utilities.enums.SoundFX;
import com.jlearn.view.voice_recognition.VoiceRecognition;

import javafx.animation.FadeTransition;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

/**
 * Exercise controller for the Exercise.
 * <p>
 * This class represent a symple controller for a {@link FXMLScreens}. The fxml is atached to the controller by the
 * {@link FXEnvironment} and chached by the {@link FXMLLoader}.
 * <p>
 * This controller is connected to a {@link ControllerExercise} to implement a MVC design.
 *
 */
public abstract class AbstractExerciseScreenController implements UIExercise {
    // ################################################ FXML VAR ###############################################

    @FXML
    private BorderPane toolbarBorder, mainBorderPanel;
    @FXML
    private JFXButton menuButton, doneButton;
    @FXML
    private JFXTabPane tabPane;
    @FXML
    private JFXHamburger hamburgerMenu;
    @FXML
    private JFXRadioButton radioButtonDrawer;
    @FXML
    private JFXListView<BorderPane> trueFalseListView, compListView, audioListView, multiListView;
    @FXML
    private JFXScrollPane scrollTrueFalse, scrollMultiple, scrollComplete, scrollAudio;

    // ################################################ DRAWER VAR ###############################################
    @FXML
    private JFXDrawer drawerLevels; // DRAWER FOR LEVELS
    private final JFXListView<Label> drawerListView = new JFXListView<>();

    // ################################################ HAMBURGER VAR ###############################################
    private HamburgerBackArrowBasicTransition hambTrans;

    // ################################################ POPUP VAR ###############################################
    private final JFXPopup popup = new JFXPopup();
    private final JFXListView<Label> menuListView = new JFXListView<>();

    // ################################################ LOCAL VAR ###############################################
    private final FXEnvironment environment;
    private int listViewSize;

    /**
     * The constructor of the controller.
     *
     * @param environment
     *            the {@link FXEnvironment}
     */
    public AbstractExerciseScreenController(final FXEnvironment environment) {
        this.environment = environment;
    }

    /**
     * Init method to be called after the constructor. This is a symple method of the {@link FXML}.
     * 
     */
    @FXML
    protected void initialize() { // NOPMD
        this.initHamburgerTransition();
        this.initListDrawerView();
        this.initScreensListeners();
        this.initButtons();
        this.initPopup();
        this.initGraphicTab();
        this.initListViews();
        this.initTabPaneListener();
    }

    // ############################################# INIT ###################################################
    private void initListViews() {
        // INIT SCROLLING
        ScrollPaneFactory.wrapListViewOnScrollPane(this.scrollTrueFalse, this.trueFalseListView, "True False",
                "trueFalseHeader");
        ScrollPaneFactory.wrapListViewOnScrollPane(this.scrollMultiple, this.multiListView, "Multi Answer",
                "multiHeader");
        ScrollPaneFactory.wrapListViewOnScrollPane(this.scrollComplete, this.compListView, "Complete",
                "completeHeader");
        ScrollPaneFactory.wrapListViewOnScrollPane(this.scrollAudio, this.audioListView, "Listen", "audioHeader");

    }

    private void initGraphicTab() {
        this.tabPane.getTabs().get(0).setGraphic(ViewUtilities.iconSetter(Material.CHECK_BOX, IconDim.MEDIUM));
        this.tabPane.getTabs().get(1).setGraphic(ViewUtilities.iconSetter(Material.LIST, IconDim.MEDIUM));
        this.tabPane.getTabs().get(2).setGraphic(ViewUtilities.iconSetter(Material.TEXT_FIELDS, IconDim.MEDIUM));
        this.tabPane.getTabs().get(3).setGraphic(ViewUtilities.iconSetter(Material.AUDIOTRACK, IconDim.MEDIUM));
    }

    private void initPopup() {
        GeneralFactory.buildPopup(this.menuButton, this.popup, this.menuListView, FXMLScreens.EXERCISE);
        this.menuButton.setOnMouseClicked((e) -> {
            this.popup.show(this.menuButton);
            this.playAudio(SoundFX.OPEN_MENU_POPUP);
        });
        // CHECKSTYLE:OFF
        this.menuListView.setOnMouseClicked(e -> {
            this.popup.hide();
            if (this.menuListView.getSelectionModel().getSelectedIndex() == 0) {
                this.environment.showDialog("HELP",
                        "1) Right click opens toolbar\n2) Click on Levels\n3) Choose the level", DimDialogs.MEDIUM,
                        null);
            } else if (this.menuListView.getSelectionModel().getSelectedIndex() == 1) {
                MenuScreenController.getInstance().showResizeScreenPopup(this.mainBorderPanel);
            } else if (this.menuListView.getSelectionModel().getSelectedIndex() == 2) {
                MenuScreenController.getInstance().fullScreenOn();
            } else if (this.menuListView.getSelectionModel().getSelectedIndex() == 3) {
                MenuScreenController.getInstance().fullScreenOff();
            } else if (this.menuListView.getSelectionModel().getSelectedIndex() == 4) {
                this.environment.muteAudio();
            } else if (this.menuListView.getSelectionModel().getSelectedIndex() == 5) {
                MenuScreenController.getInstance().showVolumePopup(this.mainBorderPanel);
            } else if (this.menuListView.getSelectionModel().getSelectedIndex() == 6) {
                VoiceRecognition.getInstance().toggleRecognize();
            } else if (this.menuListView.getSelectionModel().getSelectedIndex() == 7) {
                System.exit(0);
                // CHECKSTYLE:ON
            }
        });
    }

    private void initListDrawerView() {
        ListViewFactory.buildDrawerListView(this.drawerLevels, this.drawerListView,
                FileManagerImpl.getAllUnitName().size());
        JFXDepthManager.setDepth(this.drawerListView, 10);
    }

    private void initButtons() {
        this.doneButton.setGraphic(ViewUtilities.iconSetter(Material.SEND, IconDim.MEDIUM));
        this.menuButton.setGraphic(ViewUtilities.iconSetter(Material.EXPAND_MORE, IconDim.MEDIUM));
        this.menuButton.getGraphic().setId("exercise-menu-button-icon");
        JFXDepthManager.setDepth(this.doneButton, 10);
        this.radioButtonDrawer.setTooltip(new Tooltip("Select the Unit"));
    }

    private void initScreensListeners() {
        this.trueFalseListView.setOnMouseClicked(t -> this.tabPaneClicked(t));
        this.multiListView.setOnMouseClicked(t -> this.tabPaneClicked(t));
        this.compListView.setOnMouseClicked(t -> this.tabPaneClicked(t));
        this.audioListView.setOnMouseClicked(t -> this.tabPaneClicked(t));
    }

    private void initHamburgerTransition() {
        this.hambTrans = new HamburgerBackArrowBasicTransition(this.hamburgerMenu);
        this.hambTrans.setRate(-1);
    }

    private void refreshListView() {
        this.scrollTrueFalse.setContent(this.trueFalseListView);
        this.scrollMultiple.setContent(this.multiListView);
        this.scrollComplete.setContent(this.compListView);
        this.scrollAudio.setContent(this.audioListView);
    }

    private void initTabPaneListener() {
        this.tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.playAudio(SoundFX.SWIPE);
            if (this.tabPane.getTabs().get(3).equals(newValue) && !oldValue.equals(newValue)) {
                this.playPauseAudioExercise();
                this.environment.getAudioAgent(FXMLScreens.EXERCISE).pausePlayer();
            } else if (this.tabPane.getTabs().get(3).equals(oldValue) && !newValue.equals(oldValue)) {
                this.pauseAudioExercise();
                this.environment.getAudioAgent(FXMLScreens.EXERCISE).startPlayer();
            }
        });
    }

    // ################################################ PUBLIC ###############################################
    /**
     * Add {@link BorderPane} cell to {@link JFXListView}.
     *
     * @param elemNumber
     *            the elem number
     */
    protected void addElemToListView(final int elemNumber) {
        if (this.listViewSize < elemNumber) {
            // BUILDING THE LISTVIEWS
            ListViewFactory.addElemToListView(this.trueFalseListView, elemNumber - this.listViewSize,
                    ExerciseType.TRUE_FALSE);
            ListViewFactory.addElemToListView(this.multiListView, elemNumber - this.listViewSize, ExerciseType.MULTI);
            ListViewFactory.addElemToListView(this.compListView, elemNumber - this.listViewSize, ExerciseType.COMPLETE);
            ListViewFactory.addElemToListView(this.audioListView, elemNumber - this.listViewSize, ExerciseType.AUDIO);
            // SETTING LISTVIEW BEHAVIOUR
            ListViewFactory.lsitViewBehaviour(this.trueFalseListView, ExerciseType.TRUE_FALSE);
            ListViewFactory.lsitViewBehaviour(this.audioListView, ExerciseType.AUDIO);
            ListViewFactory.lsitViewBehaviour(this.multiListView, ExerciseType.MULTI);
            ListViewFactory.lsitViewBehaviour(this.compListView, ExerciseType.COMPLETE);

            this.listViewSize = elemNumber;
            this.refreshListView();
        } else if (this.listViewSize > elemNumber) {
            final int base = this.trueFalseListView.getItems().size();
            this.trueFalseListView.getItems().remove(base - elemNumber, base);
            this.audioListView.getItems().remove(base - elemNumber, base);
            this.multiListView.getItems().remove(base - elemNumber, base);
            this.compListView.getItems().remove(base - elemNumber, base);
            this.listViewSize = elemNumber;
            this.refreshListView();
        }
    }

    /**
     * Set color of {@link JFXTextField}.
     *
     * @param errorList
     *            the {@link List} of error
     */
    protected void colorError(final List<Integer> errorList) {
        errorList.stream()
                .forEach(t -> {
                    final JFXTextField text = (JFXTextField) this.compListView.getItems().get(t.intValue()).getRight();
                    text.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
                });
    }

    // ############################################## HANDLERS ###################################################
    @FXML
    private void handleBackMenu(final Event event) { // NOPMD
        final Object obj = event.getSource();
        if (obj.equals(this.hamburgerMenu) && event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
            this.environment.displayScreen(FXMLScreens.MENU);
            this.environment.translateTransition();
            this.notifyBack();
        } else if ((obj.equals(this.hamburgerMenu) && event.getEventType().equals(MouseEvent.MOUSE_ENTERED))
                || event.getEventType().equals(MouseEvent.MOUSE_EXITED)) {
            if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED)) {
                this.playAudio(SoundFX.BACK_BOTTON);
            }
            this.hambTrans.setRate(this.hambTrans.getRate() * -1);
            this.hambTrans.play();
        }
    }

    @FXML
    private void showDrawerModules() { // NOPMD
        if (this.radioButtonDrawer.isSelected()) {
            this.drawerLevels.open();
        } else {
            this.drawerLevels.close();
        }
        this.playAudio(SoundFX.DRAWER);
    }

    @FXML
    private void tabPaneClicked(final MouseEvent event) { // NOPMD
        final FadeTransition fade = new FadeTransition(javafx.util.Duration.seconds(Duration.SHORTEST.getValue()),
                this.mainBorderPanel);
        fade.setFromValue(0.5);
        fade.setToValue(1);
        fade.setAutoReverse(true);
        if (this.drawerLevels.isShown()) {
            this.drawerLevels.close();
            this.radioButtonDrawer.fire();
        }
        if (event.getButton().equals(MouseButton.SECONDARY)) {
            this.mainBorderPanel.setTop(this.toolbarBorder);
            fade.play();
            this.playAudio(SoundFX.SELECTION1);
        } else if (event.getButton().equals(MouseButton.PRIMARY) && (this.mainBorderPanel.getTop() != null)) {
            this.mainBorderPanel.setTop(null);
            fade.play();
            this.playAudio(SoundFX.SELECTION2);
        }
    }

    // ################################################ GETTERS ###################################################

    /**
     * The drawer {@link JFXListView}.
     *
     * @return the {@link JFXListView}
     */
    public JFXListView<Label> getDrawerListView() {
        return this.drawerListView;
    }

    /**
     * Play the sound.
     *
     * @param sound
     *            the {@link SoundFX}
     *
     */
    protected abstract void playAudio(SoundFX sound);

    /**
     * Stops the audio exercise.
     */
    protected abstract void playPauseAudioExercise();

    /**
     * Play the audio exercise.
     */
    protected abstract void pauseAudioExercise();

    /**
     * Notify coming back.
     */
    protected abstract void notifyBack();

}
