package com.jlearn.view.screens.abstracts;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

import org.apache.log4j.Logger;
import org.kordamp.ikonli.material.Material;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXSlider.IndicatorPosition;
import com.jfoenix.controls.JFXToggleButton;
import com.jlearn.controller.ControllerExercise;
import com.jlearn.view.FXEnvironment;
import com.jlearn.view.factories.GeneralFactory;
import com.jlearn.view.screens.FXMLScreens;
import com.jlearn.view.ui.UIMenu;
import com.jlearn.view.utilities.ViewUtilities;
import com.jlearn.view.utilities.enums.IconDim;
import com.jlearn.view.utilities.enums.NotificationType;
import com.jlearn.view.utilities.enums.SoundFX;
import com.jlearn.view.voice_recognition.EObserver;
import com.jlearn.view.voice_recognition.ESource;
import com.jlearn.view.voice_recognition.VoiceCommands;
import com.jlearn.view.voice_recognition.VoiceRecognition;
import com.sun.glass.ui.Application;
import com.sun.javafx.perf.PerformanceTracker;

import eu.hansolo.enzo.common.SymbolType;
import eu.hansolo.enzo.radialmenu.RadialMenu;
import eu.hansolo.enzo.radialmenu.RadialMenuBuilder;
import eu.hansolo.enzo.radialmenu.RadialMenuItem;
import eu.hansolo.enzo.radialmenu.RadialMenuItemBuilder;
import eu.hansolo.enzo.radialmenu.RadialMenuOptions;
import eu.hansolo.enzo.radialmenu.RadialMenuOptionsBuilder;
import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.util.Duration;

/**
 * Menu controller for the Menu.
 * <p>
 * This class represent a symple controller for a {@link FXMLScreens}. The fxml is atached to the controller by the
 * {@link FXEnvironment} and chached by the {@link FXMLLoader}.
 * <p>
 * This controller is connected to a {@link ControllerExercise} to implement a MVC design.
 *
 */
public abstract class AbstractMenuScreenController implements UIMenu, EObserver<String> {

    private static final Logger LOG = Logger.getLogger(AbstractMenuScreenController.class);
    private static final double MENU_ITEM_SIZE = 50;
    private static final Rectangle2D PRIMARY_SCREEN_BOUNDS = Screen.getPrimary().getVisualBounds();
    private static final int APP_WIDTH = 500;
    private static final int APP_HEIGHT = 600;
    private static final float SCALING_CONSTANT = 1.2f;
    private static final int DELTA = 50;
    private static final int FULL_SCREEN_TRIGGER = 50;

    // ################################################ FXML VAR ###############################################
    @FXML
    private BorderPane nodeBorderMenu;
    @FXML
    private JFXToggleButton buttonCredits;
    @FXML
    private JFXDrawer menuDrawer;
    @FXML
    private VBox vboxCenter;
    @FXML
    private HBox hboxCenter;
    @FXML
    private JFXButton menuButton;
    @FXML
    private Label fpsLabel;

    // ################################################ MENU VAR ###############################################
    private RadialMenu menu;
    private RadialMenuItem menuTheory;
    private RadialMenuItem menuPractice;
    private RadialMenuItem menuSettings;

    // ################################################ POPUP VAR ###############################################
    private final JFXPopup menuPopup = new JFXPopup();
    private final JFXListView<Label> menuListView = new JFXListView<>();
    private JFXPopup sliderPopup;
    private JFXPopup sliderVolumePopup;
    private JFXSlider horRightSlider;
    private JFXSlider volumeSlider;

    private boolean started;
    // ################################################ LOCAL VAR ###############################################
    private final FXEnvironment environment;
    private int easterEgg;

    /**
     * The constructor of the controller.
     *
     * @param environment
     *            the {@link FXEnvironment}
     */
    public AbstractMenuScreenController(final FXEnvironment environment) {
        this.environment = environment;
        VoiceRecognition.getInstance().addEObserver(this);

    }

    /**
     * Init method to be called after the constructor. This is a symple method of the {@link FXML}.
     *
     * @throws IOException
     *             Expception
     */
    @FXML
    protected void initialize() throws IOException { // NOPMD
        this.createMenu();
        this.initSliderPopup();
        this.initButtons();
        this.initPopup();
        this.initVolumeListener();
        this.buildDrawer();
        this.fpsLog();
    }

    // ################################################ INIT ###############################################

    private void fpsLog() {
        final PerformanceTracker tracker = PerformanceTracker
                .getSceneTracker(this.environment.getMainScene());
        this.fpsLabel.setTextFill(Color.LIGHTGREY);
        new Thread(() -> {
            while (true) {
                Platform.runLater(() -> this.fpsLabel.setText(" " + (int) tracker.getAverageFPS()));
                try {
                    Thread.sleep(1000);
                } catch (final InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void initPopup() {
        GeneralFactory.buildPopup(this.menuButton, this.menuPopup, this.menuListView, FXMLScreens.MENU);
        this.menuButton.setOnMouseClicked((e) -> {
            this.menuPopup.show(this.menuButton);
            this.playAudio(SoundFX.OPEN_MENU_POPUP);
        });

        // CHECKSTYLE:OFF DAI E' SONO COSTRETTO
        this.menuListView.setOnMouseClicked(e -> {
            this.menuPopup.hide();
            if (this.menuListView.getSelectionModel().getSelectedIndex() == 0) {
                if (Desktop.isDesktopSupported()) {
                    try {
                        Desktop.getDesktop().browse(new URI("https://bitbucket.org/NiNi94/oop16-jlearn"));
                    } catch (IOException | URISyntaxException e1) {
                        LOG.error("Internet Error");
                    }
                } else {
                    this.environment.showNotificationPopup("Internet Error", "Browser not found",
                            NotificationType.Duration.MEDIUM, NotificationType.WARNING, null);
                }
                this.easterEgg++;
                if (this.easterEgg == 3) {
                    this.easterEggTransition();
                }
            } else if (this.menuListView.getSelectionModel().getSelectedIndex() == 1) {
                this.showResizeScreenPopup(this.nodeBorderMenu);
            } else if (this.menuListView.getSelectionModel().getSelectedIndex() == 2) {
                this.fullScreenOn();
            } else if (this.menuListView.getSelectionModel().getSelectedIndex() == 3) {
                this.fullScreenOff();
            } else if (this.menuListView.getSelectionModel().getSelectedIndex() == 4) {
                this.environment.muteAudio();
            } else if (this.menuListView.getSelectionModel().getSelectedIndex() == 5) {
                this.showVolumePopup(this.nodeBorderMenu);
            } else if (this.menuListView.getSelectionModel().getSelectedIndex() == 6) {
                VoiceRecognition.getInstance().toggleRecognize();
                this.environment.showNotificationPopup("Recognizer On",
                        "Words: menu, theory, exercise, \n voice off, menu, exit application",
                        com.jlearn.view.utilities.enums.NotificationType.Duration.VERY_LONG,
                        NotificationType.INFO, null);
            } else if (this.menuListView.getSelectionModel().getSelectedIndex() == 7) {
                System.exit(0);
                // CHECKSTYLE:ON
            }
        });
    }

    private void initVolumeListener() {
        this.volumeSlider.setValue(100);
        this.volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            this.environment.setAudioVolume(newValue.doubleValue() / 100);
        });
    }

    private void initButtons() {
        this.menuButton.setGraphic(ViewUtilities.iconSetter(Material.MORE_VERT, IconDim.MEDIUM));
        this.buttonCredits.setTooltip(new Tooltip("Player Room: Select or create a new Player"));
        this.menuButton.setTooltip(new Tooltip("Open the menu"));
    }

    private void initSliderPopup() {
        this.horRightSlider = new JFXSlider();
        this.horRightSlider.setIndicatorPosition(IndicatorPosition.RIGHT);
        this.horRightSlider.setMin(APP_WIDTH);
        final HBox box = new HBox(this.horRightSlider);
        box.setId("popup-box");
        this.sliderPopup = new JFXPopup(box);
        this.horRightSlider.setMax(PRIMARY_SCREEN_BOUNDS.getWidth());
        this.horRightSlider.setId("jfx-slider-style");

        this.volumeSlider = new JFXSlider();
        this.volumeSlider.setIndicatorPosition(IndicatorPosition.RIGHT);
        final HBox box1 = new HBox(this.volumeSlider);
        box1.setId("popup-box");
        this.sliderVolumePopup = new JFXPopup(box1);
        this.volumeSlider.setMax(100);
        this.volumeSlider.setId("jfx-slider-style");
    }

    private void createMenu() {
        // CREATE THE OPTIONS FOR THE MENU
        final RadialMenuOptions option = RadialMenuOptionsBuilder.create()
                .buttonHideOnClose(false)
                .buttonSize(MENU_ITEM_SIZE * 2)
                .radius(MENU_ITEM_SIZE * 2)
                .buttonVisible(true)
                .buttonHideOnSelect(false)
                .build();
        // THEORY
        this.menuTheory = RadialMenuItemBuilder
                .create()
                .size(MENU_ITEM_SIZE)
                .symbol(SymbolType.TEXT)
                .build();
        // THEORY
        this.menuPractice = RadialMenuItemBuilder
                .create()
                .size(MENU_ITEM_SIZE)
                .symbol(SymbolType.PEN)
                .build();
        // OPTION AND STATS
        this.menuSettings = RadialMenuItemBuilder
                .create()
                .size(MENU_ITEM_SIZE)
                .symbol(SymbolType.SETTINGS)
                .build();
        // CREATE THE MENU
        this.menu = RadialMenuBuilder.create()
                .items(this.menuTheory, this.menuPractice, this.menuSettings)
                .options(option)
                .build();
        // CENTRARE IL MENU
        this.menu.setId("radial-menu");
        // SETTING THE MENU
        this.vboxCenter.setAlignment(Pos.CENTER);
        this.hboxCenter.setAlignment(Pos.CENTER);
        this.hboxCenter.getChildren().add(this.menu);
        // SETTING THE LISTENERS
        this.menu.setOnMenuOpenStarted(e -> this.playAudio(SoundFX.OPEN_MENU_ROTATE));
        this.menu.setOnMenuCloseStarted(e -> this.playAudio(SoundFX.CLOSE_MENU_ROTATE));
        this.menuTheory.setOnMouseClicked(this::handleMenuEvent);
        this.menuPractice.setOnMouseClicked(this::handleMenuEvent);
        this.menuSettings.setOnMouseClicked(this::handleMenuEvent);
    }

    private void buildDrawer() throws IOException {
        this.menuDrawer.setSidePane((BorderPane) this.environment.getNode(FXMLScreens.DRAWER));
        this.menuDrawer.open();
        this.buttonCredits.fire();
    }

    // INIT THE SCALE ENGINE IT'S A LAMBDA THREAD THAT BIND AND MANAGE STAGE DIMENSIONS
    private void scaleEngine() {
        Platform.runLater(() -> {
            this.horRightSlider.setValue((int) this.environment.getMainStage().getWidth() + DELTA);

            this.horRightSlider.valueProperty().addListener((observable, newValue, oldValue) -> {
                if ((newValue.intValue() < (this.horRightSlider.getMax() - FULL_SCREEN_TRIGGER))) {
                    if ((((int) this.environment.getMainStage()
                            .getHeight()) >= ((int) (PRIMARY_SCREEN_BOUNDS.getHeight() - FULL_SCREEN_TRIGGER)))
                            && (((int) this.environment.getMainStage().getWidth()
                                    * SCALING_CONSTANT) > (int) PRIMARY_SCREEN_BOUNDS.getHeight())) {
                        this.environment.getMainStage().setWidth(newValue.intValue());
                    } else {
                        this.environment.getMainStage().setWidth(newValue.intValue());
                        this.environment.getMainStage().setHeight((int) (newValue.intValue() * SCALING_CONSTANT));
                    }
                } else {
                    this.fullScreenOn();
                }
                if (newValue.intValue() < (APP_WIDTH + DELTA)) {
                    this.fullScreenOff();
                }
            });
        });
    }

    // #################################### PUBLIC METHODS ####################################

    /**
     * Slider screen popup.
     *
     * @param container
     *            the {@link Pane} popup container
     */
    public void showResizeScreenPopup(final Pane container) {
        this.sliderPopup.show(container);
        this.scaleEngine(); // Sarts the scale engine (must be done runtime beacuse access to main Stage)
    }

    /**
     * Slider volume popup.
     *
     * @param container
     *            the {@link Pane} popup container
     */
    public void showVolumePopup(final Pane container) {
        this.sliderVolumePopup.show(container);
    }

    /**
     * Voice Recognition Observer Pattern.
     *
     * @param src
     *            the {@link ESource} source
     * @param arg
     *            the {@link String} argument
     */
    @Override
    public void update(final ESource<? extends String> src, final String arg) {
        if (arg.equals(VoiceCommands.EXERCISE.getVoiceCommand())) {
            this.environment.fadeTransition();
            this.environment.displayScreen(FXMLScreens.EXERCISE);
        } else if (arg.equals(VoiceCommands.THEORY.getVoiceCommand())) {
            this.environment.fadeTransition();
            this.environment.displayScreen(FXMLScreens.THEORY);
        } else if (arg.equals(VoiceCommands.STATISTICS.getVoiceCommand())) {
            this.environment.fadeTransition();
            this.playAudio(SoundFX.STATS_LOAD);
            this.environment.displayScreen(FXMLScreens.STATISTICS);
        } else if (arg.equals(VoiceCommands.HOME.getVoiceCommand())) {
            this.environment.fadeTransition();
            this.environment.displayScreen(FXMLScreens.MENU);
        } else if (arg.equals(VoiceCommands.VOICE_OFF.getVoiceCommand())) {
            VoiceRecognition.getInstance().toggleRecognize();
        } else if (arg.equals(VoiceCommands.MENU.getVoiceCommand())) {
            if (this.menuPopup.isShowing()) {
                this.menuPopup.hide();
            } else {
                this.menuPopup.show(this.nodeBorderMenu);
            }
        } else if (arg.equals(VoiceCommands.EXIT.getVoiceCommand())) {
            Runtime.getRuntime().exit(0);
        }
    }

    // ################################################ HANDLERS ###############################################

    private void easterEggTransition() {
        final RotateTransition rotate = new RotateTransition(Duration.seconds(2), this.menu);
        final ScaleTransition scale = new ScaleTransition(Duration.seconds(2), this.menu);

        scale.setCycleCount(Animation.INDEFINITE);
        rotate.setCycleCount(Animation.INDEFINITE);

        // CHECKSTYLE:OFF Magicnumber
        scale.setAutoReverse(true);
        scale.setToX(-2);
        scale.setToY(-2);

        rotate.setToAngle(360);
        scale.play();
        rotate.play();
        new Thread(() -> {
            while (true) {
                Platform.runLater(() -> {
                    final com.sun.glass.ui.Robot robot = Application.GetApplication().createRobot();
                    robot.mouseMove((int) (new Random().nextDouble() * 1000),
                            (int) (new Random().nextDouble() * 1000));
                });
                try {
                    Thread.sleep(3000);
                } catch (final InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        // CHECKSTYLE:ON Magicnumber
    }

    // LISTEN MENU EVENT
    private void handleMenuEvent(final Event event) {
        final Object obj = event.getSource();

        if (obj.equals(this.menuTheory)) {
            this.environment.displayScreen(FXMLScreens.THEORY);
            this.environment.fadeTransition();
        } else if (obj.equals(this.menuPractice)) {
            this.environment.displayScreen(FXMLScreens.EXERCISE);
            this.environment.fadeTransition();
        } else if (obj.equals(this.menuSettings)) {
            this.environment.displayScreen(FXMLScreens.STATISTICS);
            this.environment.fadeTransition();
            this.playAudio(SoundFX.STATS_LOAD);
        }
    }

    @FXML // DRAWER
    private void handleBackground(final Event e) { // NOPMD
        if (this.menuDrawer.isShown()) {
            this.menuDrawer.close();
            this.buttonCredits.fire();
        }
    }

    @FXML // OPEN DRAWER
    private void handleButtonCredits(final Event event) { // NOPMD

        if (this.buttonCredits.isSelected()) {
            if (this.menu.getState().equals(RadialMenu.State.OPENED)) {
                this.menu.close();
            }
            this.menuDrawer.open();
        } else {
            this.menuDrawer.close();
        }

        if (this.started) {
            this.playAudio(SoundFX.DRAWER);
        } else {
            this.started = true;
        }
    }

    /**
     * Toggle the full screen off.
     */
    public void fullScreenOff() {
        this.environment.fadeTransition();
        Platform.runLater(() -> {
            this.environment.getMainStage().setHeight(APP_HEIGHT);
            this.environment.getMainStage().setWidth(APP_WIDTH);
            this.environment.getMainStage().setX((PRIMARY_SCREEN_BOUNDS.getMaxX() / 2) - (APP_WIDTH / 2));
            this.environment.getMainStage().setY((PRIMARY_SCREEN_BOUNDS.getMaxY() / 2) - (APP_HEIGHT / 2));
        });
        this.playAudio(SoundFX.SELECTION2);
    }

    /**
     * Toggle the full screen on.
     */
    public void fullScreenOn() {
        this.environment.fadeTransition();
        Platform.runLater(() -> {
            this.environment.getMainStage().setHeight(PRIMARY_SCREEN_BOUNDS.getHeight());
            this.environment.getMainStage().setWidth(PRIMARY_SCREEN_BOUNDS.getWidth());
            this.environment.getMainStage().setX(0);
            this.environment.getMainStage().setY(0);
        });
        this.playAudio(SoundFX.SELECTION1);
    }

    /**
     * Play the sound.
     *
     * @param sound
     *            the {@link SoundFX}
     *
     */
    public abstract void playAudio(SoundFX sound);

    // ################################################ GETTERS ###############################################
    /**
     * Get the {@link RadialMenu}.
     *
     * @return the {@link RadialMenu}
     */
    public RadialMenu getMenu() {
        return this.menu;
    }
}
