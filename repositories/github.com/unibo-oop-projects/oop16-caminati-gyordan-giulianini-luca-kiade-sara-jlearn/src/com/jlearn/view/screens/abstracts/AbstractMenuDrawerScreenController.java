package com.jlearn.view.screens.abstracts;

import java.io.IOException;

import org.kordamp.ikonli.material.Material;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.effects.JFXDepthManager;
import com.jlearn.controller.ControllerExercise;
import com.jlearn.view.FXEnvironment;
import com.jlearn.view.factories.ScrollPaneFactory;
import com.jlearn.view.screens.FXMLScreens;
import com.jlearn.view.utilities.ViewUtilities;
import com.jlearn.view.utilities.enums.IconDim;
import com.jlearn.view.utilities.enums.SoundFX;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * Drawer controller for the Drawer.
 * <p>
 * This class represent a symple controller for a {@link FXMLScreens}. The fxml is atached to the controller by the
 * {@link FXEnvironment} and chached by the {@link FXMLLoader}.
 * <p>
 * This controller is connected to a {@link ControllerExercise} to implement a MVC design.
 *
 */
public abstract class AbstractMenuDrawerScreenController {

    // ################################################ FXML VAR ###############################################
    @FXML
    private JFXTabPane tabPane;
    // ################################################ TAB 1 ###############################################
    @FXML
    private JFXButton webCamButtonLoginPlayer;
    @FXML
    private Pane paneImageView;
    @FXML
    private JFXComboBox<String> comboModality;
    @FXML
    private JFXDrawer personPhotoDrawer;
    @FXML
    private HBox hBoxMode;
    // ################################################ TAB 2 ###############################################
    @FXML
    private JFXScrollPane scrollPane;
    @FXML
    private JFXListView<BorderPane> playersListView;
    @FXML
    private AnchorPane registerPlayerMainPane;
    // ################################################ TAB 3 ###############################################
    @FXML
    private Label labelName, labelSurname, labelNickname, labelAge, labelEmail, labelTelephone;
    @FXML
    private JFXButton buttonRegisterPlayer;
    // ################################################ LOCAL VAR ###############################################
    private final FXEnvironment environment; // NOPMD

    /**
     * Constructor that calls the {@link FXEnvironment} to store the {@link Node}. This {@link Node} will be charged
     * into the {@link AbstractMenuScreenController}.
     *
     * @param environment
     *            the envirolment passed
     */
    public AbstractMenuDrawerScreenController(final FXEnvironment environment) {
        this.environment = environment;
        this.environment.loadScreen(FXMLScreens.DRAWER, this);
    }

    /**
     * Init method to be called after the constructor. This is a symple method of the {@link FXML}.
     *
     * @throws IOException
     *             Expception
     */
    @FXML
    protected void initialize() { // NOPMD
        this.initLabels();
        this.setButtons();
        this.initListView();
        this.initTab();
        this.bindImageViews();
        this.setDrawerPhoto();
        this.initComboModality();
    }

    // ################################################ INIT ###############################################
    private void initComboModality() {
        this.comboModality.getItems().add("Easy");
        this.comboModality.getItems().add("Medium");
        this.comboModality.getItems().add("Hard");
        JFXDepthManager.setDepth(this.hBoxMode, 1);
    }

    private void bindImageViews() {
        JFXDepthManager.setDepth(this.buttonRegisterPlayer, 10);
        JFXDepthManager.setDepth(this.paneImageView, 10);
    }

    private void setDrawerPhoto() {
        this.personPhotoDrawer.setSidePane(this.paneImageView);

    }

    private void setButtons() {
        JFXDepthManager.setDepth(this.buttonRegisterPlayer, 10);
        JFXDepthManager.setDepth(this.webCamButtonLoginPlayer, 10);
        this.buttonRegisterPlayer.setRipplerFill(Color.BLUE);
        this.webCamButtonLoginPlayer.setRipplerFill(Color.BLUE);

        this.buttonRegisterPlayer.setGraphic(ViewUtilities.iconSetter(Material.CREATE, IconDim.MEDIUM));
        this.webCamButtonLoginPlayer.setGraphic(ViewUtilities.iconSetter(Material.IMAGE, IconDim.SMALL));

    }

    private void initTab() {
        this.tabPane.getTabs().get(0).setGraphic(ViewUtilities.iconSetter(Material.ACCOUNT_CIRCLE, IconDim.MEDIUM));
        this.tabPane.getTabs().get(1).setGraphic(ViewUtilities.iconSetter(Material.VIEW_LIST, IconDim.MEDIUM));
        this.tabPane.getTabs().get(2).setGraphic(ViewUtilities.iconSetter(Material.PERSON_ADD, IconDim.MEDIUM));
        this.tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            this.playAudio(SoundFX.SWIPE);
        });
    }

    private void initLabels() {
        this.labelName.setGraphic(ViewUtilities.iconSetter(Material.ACCOUNT_BOX, IconDim.SMALLER));
        this.labelSurname.setGraphic(ViewUtilities.iconSetter(Material.ACCOUNT_BOX, IconDim.SMALLER));
        this.labelNickname.setGraphic(ViewUtilities.iconSetter(Material.PERM_IDENTITY, IconDim.SMALLER));
        this.labelEmail.setGraphic(ViewUtilities.iconSetter(Material.EMAIL, IconDim.SMALLER));
        this.labelAge.setGraphic(ViewUtilities.iconSetter(Material.CAKE, IconDim.SMALLER));
        this.labelTelephone.setGraphic(ViewUtilities.iconSetter(Material.PHONE, IconDim.SMALLER));

    }

    private void initListView() {
        ScrollPaneFactory.wrapListViewOnScrollPane(this.scrollPane, this.playersListView, "Players", "headerPlayers");
    }

    // ################################################ PUBLIC ###############################################
    /**
     * Flush the Labels.
     */
    protected void flushRegister() {
        this.registerPlayerMainPane.getChildren()
                .stream()
                .filter(t -> t.getClass().equals(JFXTextField.class))
                .map(p -> (JFXTextField) p)
                .forEach(j -> j.setText(""));
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