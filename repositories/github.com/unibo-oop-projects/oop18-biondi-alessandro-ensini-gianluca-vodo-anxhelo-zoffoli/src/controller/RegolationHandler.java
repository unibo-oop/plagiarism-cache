package controller;

import java.io.IOException;

import controller.exceptions.ImageNotInitializedException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import model.effects.regolations.Brightness;
import model.effects.regolations.Contrast;
import model.effects.regolations.Exsposure;
import model.effects.regolations.Regolation;
import model.effects.regolations.Saturation;
import model.effects.regolations.Temperature;
import view.MainWindowController;
import view.RegolationsController;

/**
 * Handler of the view of regolation.
 */
public final class RegolationHandler {
    private static final RegolationHandler SINGLETON = new RegolationHandler();
    private RegolationsController regolationsController;
    private Pane pane;
    private Regolation regolation;

    private RegolationHandler() {
    };

    /**
     * Gets the HistoryHandler instance.
     * 
     * @return singleton
     */
    public static RegolationHandler getRegolationHandler() {
        return SINGLETON;
    }

    /**
     * Sets type of regolation.
     * 
     * @param nameOfRegolation to apply
     * @return this
     */
    public RegolationHandler setRegolation(final String nameOfRegolation) {
        if (HistoryHandler.getHistoryHandler().exist()) {
            MainWindowController.setImage(HistoryHandler.getHistoryHandler().getCurrentImage());
            switch (nameOfRegolation) {
            case "Brightness":
                this.regolation = new Brightness();
                break;
            case "Contrast":
                this.regolation = new Contrast();
                break;
            case "Saturation":
                this.regolation = new Saturation();
                break;
            case "Temperature":
                this.regolation = new Temperature();
                break;
            case "Exposure":
                this.regolation = new Exsposure();
                break;
            default:
                break;
            }
        }
        return this;
    }

    /**
     * Returns pane for settings.
     * 
     * @return regolation pane
     */
    public Pane getPaneSettings() {
        return this.pane;
    }

    /**
     * Sets a new pane for set PaneSettings.
     * 
     * @return RegolationHandler for more changes
     */
    public RegolationHandler setPaneSettings() {
        try {
            pane = FXMLLoader.load(RegolationHandler.class.getResource("/scene/RegolationSettings.fxml"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * Applies the shown regolation adding it to history.
     */
    public void apply() {
        if (HistoryHandler.getHistoryHandler().exist()) {
            EffectHandler.applyEffect(regolation, HistoryHandler.getHistoryHandler().getCurrentImage());
        }
    }

    /**
     * Deletes the selected effect.
     */
    public void cancel() {
        if (HistoryHandler.getHistoryHandler().exist()) {
            MainWindowController.setImage(HistoryHandler.getHistoryHandler().getCurrentImage());
        }
    }

    /**
    * 
    */
    public void close() {
        regolationsController.closePanes();
    }

    /**
     * Shows preview of regolation.
     * 
     * @param value of regolation.
     * @return RegolationHandler for more changes.
     */
    public RegolationHandler showPreview(final Double value) {
        if (HistoryHandler.getHistoryHandler().exist()) {
            try {
                EffectHandler.showEffect(regolation.setIndex(value.intValue()),
                        HistoryHandler.getHistoryHandler().getCurrentImage());
            } catch (ImageNotInitializedException e) {
                e.printStackTrace();
            }
        }
        return this;
    }

    /**
     * Sets controller.
     * 
     * @param regolationsController to manage
     */
    public void setRegolationsController(final RegolationsController regolationsController) {
        this.regolationsController = regolationsController;
    }
}
