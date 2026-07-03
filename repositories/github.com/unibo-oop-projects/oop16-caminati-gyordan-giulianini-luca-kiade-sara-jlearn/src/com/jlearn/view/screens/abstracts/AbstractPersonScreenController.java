package com.jlearn.view.screens.abstracts;

import java.io.IOException;

import org.kordamp.ikonli.material.Material;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXToggleButton;
import com.jfoenix.effects.JFXDepthManager;
import com.jlearn.view.FXEnvironment;
import com.jlearn.view.utilities.ViewUtilities;
import com.jlearn.view.utilities.enums.IconDim;
import com.jlearn.view.utilities.enums.SoundFX;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;

/**
 * Abstract Controller..
 */

public abstract class AbstractPersonScreenController {
    // ################################################ FXML VAR ###############################################
    @FXML
    private Label labelName, labelSurname, labelNickname, labelAge, labelEmail, labelTelephone;
    @FXML
    private JFXToggleButton showPhotoButton;
    @FXML
    private JFXDrawer personPhotoDrawer;
    @FXML
    private Pane paneImageView;

    // ################################################ LOCAL VAR ###############################################
    private final FXEnvironment environment; // NOPMD

    /**
     * Constructor.
     *
     * @param envirolment
     *            the {@link FXEnvironment}
     */
    public AbstractPersonScreenController(final FXEnvironment envirolment) {
        this.environment = envirolment;
        this.environment.toString();
    }

    /**
     * Init method to be called after the constructor. This is a symple method of the {@link FXML}.
     *
     * @throws IOException
     *             Expception
     */
    @FXML
    protected void initialize() {
        // INIT WEBCAM
        this.initButtons();
        // INIT LABELS
        this.initLabels();
    }

    private void initLabels() {
        this.labelName.setGraphic(ViewUtilities.iconSetter(Material.ACCOUNT_BOX, IconDim.SMALLER));
        this.labelSurname.setGraphic(ViewUtilities.iconSetter(Material.ACCOUNT_BOX, IconDim.SMALLER));
        this.labelNickname.setGraphic(ViewUtilities.iconSetter(Material.PERM_IDENTITY, IconDim.SMALLER));
        this.labelEmail.setGraphic(ViewUtilities.iconSetter(Material.EMAIL, IconDim.SMALLER));
        this.labelAge.setGraphic(ViewUtilities.iconSetter(Material.CAKE, IconDim.SMALLER));
        this.labelTelephone.setGraphic(ViewUtilities.iconSetter(Material.PHONE, IconDim.SMALLER));
    }

    private void initButtons() {
        JFXDepthManager.setDepth(this.personPhotoDrawer, 4);
        this.personPhotoDrawer.setSidePane(this.paneImageView);
        this.showPhotoButton.setTooltip(new Tooltip("Show the photo or make a photo"));
    }

    // ################################################ HANDLERS ###################################################
    @FXML // NOPMD
    void showPhoto(final ActionEvent event) {
        if (this.showPhotoButton.isSelected()) {
            this.personPhotoDrawer.open();
        } else {
            this.personPhotoDrawer.close();
        }
        this.playAudio(SoundFX.DRAWER);
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