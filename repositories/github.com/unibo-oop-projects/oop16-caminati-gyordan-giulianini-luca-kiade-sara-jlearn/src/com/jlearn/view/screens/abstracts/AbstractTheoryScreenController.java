package com.jlearn.view.screens.abstracts;

import org.kordamp.ikonli.material.Material;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.jlearn.controller.ControllerExercise;
import com.jlearn.controller.fileio.FileManagerImpl;
import com.jlearn.view.FXEnvironment;
import com.jlearn.view.factories.GeneralFactory;
import com.jlearn.view.factories.ListViewFactory;
import com.jlearn.view.screens.FXMLScreens;
import com.jlearn.view.screens.MenuScreenController;
import com.jlearn.view.utilities.ViewUtilities;
import com.jlearn.view.utilities.enums.DialogsType.DimDialogs;
import com.jlearn.view.utilities.enums.IconDim;
import com.jlearn.view.utilities.enums.NotificationType;
import com.jlearn.view.utilities.enums.NotificationType.Duration;
import com.jlearn.view.utilities.enums.SoundFX;
import com.jlearn.view.voice_recognition.VoiceRecognition;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.sun.xml.internal.ws.api.pipe.Engine;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * Theory controller for the Theory.
 * <p>
 * This class represent a symple controller for a {@link FXMLScreens}. The fxml is atached to the controller by the
 * {@link FXEnvironment} and chached by the {@link FXMLLoader}.
 * <p>
 * This controller is connected to a {@link ControllerExercise} to implement a MVC design.
 *
 */
public abstract class AbstractTheoryScreenController implements MapComponentInitializedListener {

    // ################################################ FXML VAR ###############################################
    @FXML
    private JFXButton menuButton;
    @FXML
    private BorderPane toolbarBorder;
    @FXML
    private JFXHamburger hamburgerMenu;
    @FXML
    private JFXDrawer drawerLevels;
    @FXML
    private JFXRadioButton radioButtonDrawer;
    @FXML
    private JFXTabPane tabPane;
    @FXML
    private BorderPane mainBorderPanel;
    @FXML
    private BorderPane borderTheory;

    // ################################################ DRAWER VAR ###############################################
    private final JFXListView<Label> drawerListView = new JFXListView<>(); // THE LIST OF LEVELS //NOPMD

    // ################################################ HAMBUGER VAR ################################################
    private HamburgerBackArrowBasicTransition hambTrans;

    // ################################################ POPUP VAR ###############################################
    private final JFXPopup popup = new JFXPopup();
    private final JFXListView<Label> menuListView = new JFXListView<>();

    // ################################################ BROWSER VAR ###############################################
    private WebEngine engine;
    private WebView browser;
    // ################################################ MAP VIEW ###############################################
    @FXML
    private GoogleMapView googleMapView;

    private static final double LAT = 51.509865;
    private static final double LONG = -0.118092;
    private static final double ZOOM = 10;
    // ################################################ LOCAL VAR ###############################################
    private final FXEnvironment environment;

    /**
     * The constructor of the controller.
     *
     * @param environment
     *            the {@link FXEnvironment}
     * 
     */
    public AbstractTheoryScreenController(final FXEnvironment environment) {
        this.environment = environment;

    }

    /**
     * Init method to be called after the constructor. This is a symple method of the {@link FXML}.
     *
     */
    @FXML
    public void initialize() {
        this.initButtons();
        this.initHamburgerTransition();
        this.initPopup();
        this.initTabPane();
        this.initDrawerListView();
        this.initBrowser();
        this.googleMapView.addMapInializedListener(this);
    }

    // ################################################ INIT ###################################################

    @Override
    public void mapInitialized() {
        final MapOptions mapOptions = new MapOptions();
        mapOptions.center(new LatLong(LAT, LONG))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(ZOOM);
        final GoogleMap map;
        map = this.googleMapView.createMap(mapOptions);
        // Add a marker to the map
        final MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(new LatLong(LAT, LONG))
                .visible(Boolean.TRUE)
                .title("My Marker");
        final Marker marker = new Marker(markerOptions);
        map.addMarker(marker);
    }

    private void initButtons() {

        // MENU BUTTON
        this.menuButton.setGraphic(ViewUtilities.iconSetter(Material.EXPAND_MORE, IconDim.MEDIUM));
        this.menuButton.getGraphic().setId("exercise-menu-button-icon");
        this.radioButtonDrawer.setTooltip(new Tooltip("Select the Unit"));
    }

    private void initHamburgerTransition() {
        this.hambTrans = new HamburgerBackArrowBasicTransition(this.hamburgerMenu);
        this.hambTrans.setRate(-1);
    }

    private void initPopup() {
        GeneralFactory.buildPopup(this.menuButton, this.popup, this.menuListView, FXMLScreens.THEORY);
        this.menuButton.setOnMouseClicked(e -> {
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

    private void initDrawerListView() {
        ListViewFactory.buildDrawerListView(this.drawerLevels, this.drawerListView,
                FileManagerImpl.getAllUnitName().size());
        // ACTION HANDLER
        this.drawerListView.setOnMouseClicked(t -> {
            if (this.drawerListView.getSelectionModel().getSelectedIndex() == -1) {
                return;
            }
            final String tmp = this.drawerListView.getSelectionModel().getSelectedItem().getText();
            this.environment.showNotificationPopup("Level", "Selected " + tmp, Duration.SHORTER, NotificationType.INFO,
                    null);
            this.loadPdf(this.drawerListView.getSelectionModel().getSelectedIndex());
            this.radioButtonDrawer.setText(tmp);
        });

    }

    private void initBrowser() {
        Platform.runLater(() -> {
            this.browser = new WebView();
            this.engine = this.browser.getEngine();
            this.borderTheory.setCenter(this.browser);
            this.engine.getLoadWorker().exceptionProperty().addListener(
                    (ChangeListener<Throwable>) (observable, oldValue, newValue) -> Platform.runLater(() -> {
                        if (newValue != null) {
                            AbstractTheoryScreenController.this.environment.showNotificationPopup("Exception",
                                    newValue.getMessage() + '\n' + "Check Internet Connection!", Duration.LONG,
                                    NotificationType.ERROR, null);
                        }
                    }));
        });
    }

    // ################################################ HANDLERS ###################################################

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

    private void initTabPane() {
        // TABS
        this.tabPane.getTabs().get(0).setGraphic(ViewUtilities.iconSetter(Material.BOOK, IconDim.MEDIUM));
        this.tabPane.getTabs().get(1).setGraphic(ViewUtilities.iconSetter(Material.MAP, IconDim.MEDIUM));
        this.tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.playAudio(SoundFX.SWIPE);
        });
    }
    // ########################################### GETTERS AND ABSTRACT ###########################################

    /**
     * Load the Pdf into the browser.
     *
     * @param i
     *            the int level.
     */
    public abstract void loadPdf(int i);

    /**
     * The {@link Engine}.
     *
     * @return the {@link Engine}
     */
    public WebEngine getEngine() {
        return this.engine;
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
