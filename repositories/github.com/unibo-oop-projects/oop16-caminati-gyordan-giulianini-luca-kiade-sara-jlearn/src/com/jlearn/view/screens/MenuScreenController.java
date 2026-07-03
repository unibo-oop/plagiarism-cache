package com.jlearn.view.screens;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import com.jlearn.controller.ControllerExercise;
import com.jlearn.controller.ControllerLogic;
import com.jlearn.controller.ControllerLogicImpl;
import com.jlearn.model.users.User;
import com.jlearn.model.users.UserImpl;
import com.jlearn.model.utilities.Pair;
import com.jlearn.view.FXEnvironment;
import com.jlearn.view.factories.ListViewFactory;
import com.jlearn.view.screens.abstracts.AbstractMenuDrawerScreenController;
import com.jlearn.view.screens.abstracts.AbstractMenuScreenController;
import com.jlearn.view.utilities.enums.NotificationType;
import com.jlearn.view.utilities.enums.NotificationType.Duration;
import com.jlearn.view.utilities.enums.SoundFX;

import eu.hansolo.enzo.notification.NotificationEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Menu controller for the Menu.
 * <p>
 * This class represent a symple controller for a {@link FXMLScreens}. The fxml is atached to the controller by the
 * {@link FXEnvironment} and chached by the {@link FXMLLoader}.
 * <p>
 * This controller is connected to a {@link ControllerExercise} to implement a MVC design.
 *
 */
public final class MenuScreenController extends AbstractMenuScreenController {

    private static final long SNACKBAR_DURATION = 3000;
    // ################################################ FXML VAR ###############################################

    // ################################################ LOCAL VAR ###############################################
    private final FXEnvironment environment;
    private final ControllerLogic controller; // NOPMD
    private static MenuScreenController singleton;
    private final DrawerScreenController drawerController;

    /**
     * The constructor of the controller.
     *
     * @param envirolment
     *            the {@link FXEnvironment}
     * @param controller
     *            the {@link ControllerLogic}
     * @throws IOException
     *             the exception cause by the loading of the {@link FXMLLoader}
     */
    private MenuScreenController() {
        super(FXEnvironment.getInstance());
        this.environment = FXEnvironment.getInstance();
        this.controller = ControllerLogicImpl.getInstance();
        this.drawerController = new DrawerScreenController(this.environment, this.controller);
        this.environment.loadScreen(FXMLScreens.MENU, this);
        this.controller.getClass(); // TOGLIAMO IL WARNING

    }

    @Override
    @FXML
    protected void initialize() throws IOException { // NOPMD
        super.initialize();
        this.getMenu().setDisable(true);
    }

    /**
     * Get the {@link MenuScreenController} instance.
     *
     * @return the {@link MenuScreenController}
     */
    public static MenuScreenController getInstance() {
        synchronized (MenuScreenController.class) {
            if (singleton == null) {
                singleton = new MenuScreenController();
            }
        }
        return singleton;
    }

    /**
     * Call the {@link FXEnvironment} show method for updatig the grafic and the {@link Stage}.
     */
    public void show() {
        this.environment.displayScreen(FXMLScreens.MENU);
        this.environment.showNotificationPopup("Hey, Welcome!", "Wear Headphones for \n a better experience",
                Duration.LONG,
                NotificationType.INFO, null);
    }

    // ################################################ FROM CONTROLLER ###############################################
    @Override
    public void setProfileImageView(final Image image) {
        this.drawerController.setProfileImageView(image);
    }

    @Override
    public void addPlayerListView(final String nickname, final Image image) {
        this.drawerController.createUserInListView(nickname, image);
    }

    @Override
    public void setProfileLoginNickname(final String loginNickname) {
        this.drawerController.setProfileLoginNickname(loginNickname);
    }

    @Override
    public void clearListOfPlayers() {
        this.drawerController.clearListOfPlayers();
    }

    @Override
    public void showNotificationPopup(final String title, final String message, final Duration secondsDuration,
            final NotificationType notiType, final EventHandler<NotificationEvent> ev) {
        this.environment.showNotificationPopup(title, message, secondsDuration, notiType, ev);

    }

    @Override
    public void playAudio(final SoundFX sound) {
        this.environment.playAudioFXClip(sound);
    }

    // ################################################ DRAWER ###############################################
    /**
     * Drawer controller for the Drawer.
     * <p>
     * This class represent a symple controller for a {@link FXMLScreens}. The fxml is atached to the controller by the
     * {@link FXEnvironment} and chached by the {@link FXMLLoader}.
     * <p>
     * This controller is connected to a {@link ControllerExercise} to implement a MVC design.
     *
     */
    final class DrawerScreenController extends AbstractMenuDrawerScreenController {

        private static final double IMAGE_WIDTH = 150;
        private static final double IMAGE_HEIGHT = 130;
        // ################################################ FXML VAR ###############################################
        // ################################################ TAB 1 ###############################################
        @FXML
        private Label nameLoginPlayer;
        @FXML
        private Pane paneImageView;

        private JFXSnackbar snackBar;
        @FXML
        private JFXDrawer personPhotoDrawer;
        @FXML
        private JFXComboBox<String> comboModality;

        // ################################################ TAB 2 ###############################################
        @FXML
        private JFXScrollPane scrollPane;
        @FXML
        private JFXListView<BorderPane> playersListView;

        // ################################################ TAB 3 ###############################################
        @FXML
        private JFXTextField nameTextField, nicknameTextField, surnameTextField, ageTextField, emailTextField,
                telephoneTextField;

        // ############################################# LOCAL VAR ###################################################
        private final FXEnvironment environment;
        private final ControllerLogic controller;

        /**
         * Constructor that calls the {@link FXEnvironment} to store the {@link Node}. This {@link Node} will be charged
         * into the {@link MenuScreenController}.
         *
         * @param environment
         *            the envirolment passed
         * @param controller
         *            the {@link ControllerLogic}.
         */
        DrawerScreenController(final FXEnvironment environment, final ControllerLogic controller) {
            super(environment);
            this.environment = environment;
            this.controller = controller;
            this.environment.loadScreen(FXMLScreens.DRAWER, this);
            this.controller.toString();
        }

        @Override
        @FXML
        protected void initialize() { // NOPMD
            super.initialize();
            this.iniListViewSelection();
            this.initPlayerDeleteListViewListeners();
            this.initSnackBar();
        }

        private void initSnackBar() {
            this.snackBar = new JFXSnackbar(this.paneImageView);
            this.paneImageView.setOnMouseClicked(e -> this.webCamPhoto());
        }

        // ########################################## TO CONTROLLER ###############################################
        @FXML
        private void selectedModality() {
            this.controller.setMode(this.comboModality.getSelectionModel().getSelectedIndex());
        }

        private void iniListViewSelection() {
            this.playersListView.setOnMouseClicked(t -> {
                if (this.playersListView.getSelectionModel().getSelectedItem() != null) {
                    final Label nickname = (Label) this.playersListView.getSelectionModel()
                            .getSelectedItem()
                            .getCenter();
                    this.controller.choosenUser(nickname.getText());
                    // ENABLE THE RADIAL MENU
                    MenuScreenController.super.getMenu().setDisable(false);
                }
            });

        }

        private void initPlayerDeleteListViewListeners() {
            this.playersListView.getItems()
                    .stream()
                    .map(t -> new Pair<>((Label) t.getCenter(), (JFXButton) t.getRight()))
                    .forEach(j -> {
                        j.getY().setOnMouseClicked(k -> {
                            this.controller.deletePlayer(j.getX().getText());
                            MenuScreenController.super.getMenu().setDisable(true);
                        });
                    });
        }

        private void webCamPhoto() {
            this.controller.webCamPhoto();
            this.playAudio(SoundFX.CAMERA);
        }

        @FXML
        private void openDrawerPhoto() {
            this.playAudio(SoundFX.DRAWER);
            if (this.personPhotoDrawer.isShown()) {
                this.personPhotoDrawer.close();
            } else {
                this.personPhotoDrawer.open();
                this.snackBar.show("Take Photo", SNACKBAR_DURATION);
            }
        }

        @FXML // NOPMD
        void signUser(final ActionEvent event) {
            try {
                final User user = new UserImpl(this.nameTextField.getText(), this.surnameTextField.getText(),
                        this.nicknameTextField.getText(), Integer.parseInt(this.ageTextField.getText()),
                        this.emailTextField.getText(), this.telephoneTextField.getText());
                this.controller.signUser(user);
            } catch (final Exception e) {
                this.environment.showNotificationPopup("Error", "Check the form", Duration.MEDIUM,
                        NotificationType.WARNING, null);
            }
            super.flushRegister();
        }

        // ########################################### FROM VCONTROLLER #############################################
        /**
         * Set the profile {@link Image}.
         *
         * @param image
         *            the {@link Image}
         */
        protected void setProfileImageView(final Image image) {
            this.paneImageView.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
                    new BackgroundSize(IMAGE_WIDTH, IMAGE_HEIGHT, false, false,
                            false, false))));
        }

        /**
         * Create the user {@link Border} in {@link JFXListView}.
         *
         * @param nickname
         *            the {@link String} nickname
         * @param image
         *            the {@link Image}
         */
        protected void createUserInListView(final String nickname, final Image image) {
            ListViewFactory.addPlayersToListView(this.playersListView, image, nickname);
            this.initPlayerDeleteListViewListeners();
            this.scrollPane.setContent(this.playersListView);
        }

        /**
         * Set Profile nickname.
         *
         * @param loginNickname
         *            the {@link String} nickname
         */
        protected void setProfileLoginNickname(final String loginNickname) {
            this.nameLoginPlayer.setText(loginNickname);
        }

        public void clearListOfPlayers() {
            this.playersListView.getItems().clear();
        }

        @Override
        public void playAudio(final SoundFX sound) {
            this.environment.playAudioFXClip(sound);
        }

    }

}
