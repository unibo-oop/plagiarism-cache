package view.settingsobserver;

import controller.Controller;
import javafx.scene.control.ComboBox;

/**
 *
 * Chiara Tarantino.
 * A class that represents an observer for combobox that contains the number of minotaur steps. 
 *
 */
public class MinotaurusStepsObserver implements Observer {

    private final ComboBox<Integer> minStepsComboBox;
    private final Controller controller;

    /**
     * Class constructor.
     *
     * @param minotaurSteps
     *            combobox that contains the number of minotaur steps.
     * @param controller
     *            controller.
     */
    public MinotaurusStepsObserver(final ComboBox<Integer> minotaurSteps, final Controller controller) {
        this.minStepsComboBox = minotaurSteps;
        this.controller = controller;
    }

    @Override
    public final void updateSettings() {
        this.controller.setMinotaurusSteps(this.minStepsComboBox.getValue());
    }

}
