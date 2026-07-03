package com.jlearn.view.screens.abstracts;

import java.io.IOException;

import org.kordamp.ikonli.material.Material;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.jlearn.controller.ControllerExercise;
import com.jlearn.controller.fileio.FileManagerImpl;
import com.jlearn.view.FXEnvironment;
import com.jlearn.view.factories.GeneralFactory;
import com.jlearn.view.screens.FXMLScreens;
import com.jlearn.view.screens.MenuScreenController;
import com.jlearn.view.ui.UIStatistics;
import com.jlearn.view.utilities.ViewUtilities;
import com.jlearn.view.utilities.enums.DialogsType.DimDialogs;
import com.jlearn.view.utilities.enums.IconDim;
import com.jlearn.view.utilities.enums.NotificationType.Duration;
import com.jlearn.view.utilities.enums.SoundFX;
import com.jlearn.view.voice_recognition.VoiceRecognition;

import javafx.animation.FadeTransition;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Theory controller for the Theory.
 * <p>
 * This class represent a symple controller for a {@link FXMLScreens}. The fxml is atached to the controller by the
 * {@link FXEnvironment} and chached by the {@link FXMLLoader}.
 * <p>
 * This controller is connected to a {@link ControllerExercise} to implement a MVC design.
 *
 */
public abstract class AbstractStatisticsScreenController implements UIStatistics {

    // ################################################ FXML VAR ###############################################
    @FXML
    private BorderPane mainBorderPanel, toolbarBorder;
    @FXML
    private JFXTabPane tabPane;
    @FXML
    private JFXButton menuButton, buttonClearLineChart, buttonPieChart;
    @FXML
    private JFXHamburger hamburgerMenu;
    @FXML
    private BarChart<String, Integer> barChart;
    @FXML
    private LineChart<String, Integer> lineChart;
    @FXML
    private PieChart pieChart;
    @FXML
    private JFXScrollPane scrollo;
    @FXML
    private HBox lineHBox, pieHBox;
    @FXML
    private JFXComboBox<String> comboLineLevels, comboPieLevel, comboPieModality;

    // ################################################ HAMBURGER VAR ###############################################
    private HamburgerBackArrowBasicTransition hambTrans;

    // ################################################ POPUP VAR ###############################################
    private final JFXPopup popup = new JFXPopup();
    private final JFXListView<Label> menuListView = new JFXListView<>();
    // ################################################ LOCAL VAR ###############################################
    private final FXEnvironment environment;

    /**
     * The constructor of the controller.
     *
     * @param environment
     *            the {@link FXEnvironment}
     * 
     */
    public AbstractStatisticsScreenController(final FXEnvironment environment) {
        this.environment = environment;
    }

    /**
     * Init method to be called after the constructor. This is a symple method of the {@link FXML}.
     *
     * @throws IOException
     *             Expception
     */

    @FXML
    protected void initialize() {
        this.setHamburgerTransition();
        this.initButtons();
        this.initChart();
        this.initPopup();
        this.initGraphicTab();
        this.initScrollPane();
        this.initCombos(FileManagerImpl.getAllUnitName().size());

    }

    private void initButtons() {
        this.menuButton.setGraphic(ViewUtilities.iconSetter(Material.EXPAND_MORE, IconDim.MEDIUM));
        this.buttonClearLineChart.setGraphic(ViewUtilities.iconSetter(Material.CLEAR, IconDim.SMALL));
        this.buttonPieChart.setGraphic(ViewUtilities.iconSetter(Material.CLEAR, IconDim.SMALL));
        JFXDepthManager.setDepth(this.buttonClearLineChart, 3);
        JFXDepthManager.setDepth(this.buttonPieChart, 3);
    }

    private void initCombos(final int levelNumber) {
        for (int i = 0; i < levelNumber; i++) {
            final int k = i + 1;
            this.comboPieLevel.getItems().add(k + ") " + FileManagerImpl.getAllUnitName().get(i));
            this.comboLineLevels.getItems().add(k + ") " + FileManagerImpl.getAllUnitName().get(i));
        }
        this.comboPieModality.getItems().add("Easy");
        this.comboPieModality.getItems().add("Medium");
        this.comboPieModality.getItems().add("Hard");
        JFXDepthManager.setDepth(this.comboLineLevels, 4);
        JFXDepthManager.setDepth(this.comboPieLevel, 4);
        JFXDepthManager.setDepth(this.comboPieModality, 4);
    }

    // CHECKSTYLE:OFF Magicnumber
    private void initScrollPane() {
        final Label title = new Label("Statistics");
        StackPane.setMargin(title, new Insets(0, 0, 0, 80));
        StackPane.setAlignment(title, Pos.CENTER_LEFT);
        this.scrollo.getBottomBar().getChildren().add(title);
        title.setStyle("-fx-text-fill:WHITE; -fx-font-size: 40;");
        this.scrollo.getMainHeader().setId("personHeader");
        this.tabPane.getTabs().get(0).setContent(this.scrollo);
        // COOL BACK BOX
        final VBox backBox = new VBox();
        final Pane backPane1 = new Pane();
        final Pane backPane2 = new Pane();
        backBox.getChildren().addAll(backPane1, backPane2);
        VBox.setVgrow(backPane1, Priority.ALWAYS);
        VBox.setVgrow(backPane2, Priority.ALWAYS);
        backPane1.setId("backPane1");
        backPane2.setId("backPane2");
        backPane1.setMinHeight(100d);

        // STACK CENTER PERSONA
        final AnchorPane persona = (AnchorPane) this.environment.getNode(FXMLScreens.PERSON);
        persona.setMaxWidth(300);
        final StackPane stack = new StackPane(persona);
        StackPane.setAlignment(persona, Pos.CENTER);
        JFXDepthManager.setDepth(persona, 6);
        stack.setId("personStack");
        // ANCOR FINAL
        final AnchorPane anchor = new AnchorPane(backBox, stack);
        AnchorPane.setLeftAnchor(stack, 100.0d);
        AnchorPane.setRightAnchor(stack, 100.0d);
        AnchorPane.setBottomAnchor(stack, 100.0d);
        AnchorPane.setTopAnchor(stack, 100.0d);
        AnchorPane.setLeftAnchor(backBox, 0d);
        AnchorPane.setRightAnchor(backBox, 0d);
        AnchorPane.setBottomAnchor(backBox, 0d);
        AnchorPane.setTopAnchor(backBox, 0d);
        final StackPane container = new StackPane(anchor);
        container.setPadding(new Insets(-1));
        // SET THE CONTENT
        this.scrollo.setContent(container);
        // CHECKSTYLE:ON Magicnumber
    }

    private void initGraphicTab() {
        this.tabPane.getTabs().get(0).setGraphic(ViewUtilities.iconSetter(Material.PERSON, IconDim.MEDIUM));
        this.tabPane.getTabs().get(1).setGraphic(ViewUtilities.iconSetter(Material.INSERT_CHART, IconDim.MEDIUM));
        this.tabPane.getTabs().get(2).setGraphic(ViewUtilities.iconSetter(Material.MULTILINE_CHART, IconDim.MEDIUM));
        this.tabPane.getTabs().get(3).setGraphic(ViewUtilities.iconSetter(Material.PIE_CHART, IconDim.MEDIUM));

    }

    private void initPopup() {
        GeneralFactory.buildPopup(this.menuButton, this.popup, this.menuListView, FXMLScreens.EXERCISE);
        this.menuButton.setOnMouseClicked((e) -> {
            this.popup.show(this.menuButton);
            this.playAudio(SoundFX.OPEN_MENU_POPUP);
        });
        // ACTION HANDLER
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

    private void initChart() {
        VBox.setVgrow(this.lineChart, Priority.ALWAYS);
        VBox.setVgrow(this.barChart, Priority.ALWAYS);
        VBox.setVgrow(this.pieChart, Priority.ALWAYS);
        JFXDepthManager.setDepth(this.lineHBox, 1);
        JFXDepthManager.setDepth(this.pieHBox, 1);

    }

    // ################################################ HANDLERS ###################################################
    private void setHamburgerTransition() {
        this.hambTrans = new HamburgerBackArrowBasicTransition(this.hamburgerMenu);
        this.hambTrans.setRate(-1);
    }

    @FXML
    private void handleBackMenu(final Event event) { // NOPMD
        final Object obj = event.getSource();
        if (obj.equals(this.hamburgerMenu) && event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
            this.environment.displayScreen(FXMLScreens.MENU);
            this.environment.translateTransition();
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
    private void tabPaneClicked(final MouseEvent event) { // NOPMD
        final FadeTransition fade = new FadeTransition(javafx.util.Duration.seconds(Duration.SHORTEST.getValue()),
                this.mainBorderPanel);
        fade.setFromValue(0.5);
        fade.setToValue(1);
        fade.setAutoReverse(true);
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

    /**
     * Play the sound.
     *
     * @param sound
     *            the {@link SoundFX}
     *
     */
    public abstract void playAudio(SoundFX sound);
}
