package view.settingsobserver;

import controller.Controller;
import javafx.scene.control.ToggleButton;

/**
 *
 * Chiara Tarantino.
 * A class that represents an observer for minotaur hedge jumping mode toggle button. 
 *
 */
public class MinotaurusHedgeJumpingObserver implements Observer {

    private final ToggleButton mhjToggleButton;
    private final Controller controller;

    /**
     * Class constructor.
     *
     * @param minotaurHedgeJumping
     *            toggle button that shows if the minotaur can jump over the hedge
     *            or not.
     * @param controller
     *            controller
     */
    public MinotaurusHedgeJumpingObserver(final ToggleButton minotaurHedgeJumping, final Controller controller) {
        this.mhjToggleButton = minotaurHedgeJumping;
        this.controller = controller;
        this.mhjToggleButton.selectedProperty().addListener(e -> this.setText());
        this.setText();
    }

    private void setText() {
        this.mhjToggleButton.setText(this.mhjToggleButton.isSelected() ? "Active" : "Not Active");
    }

    @Override
    public final void updateSettings() {
        this.controller.setMinotaurusEdgeJumpingMode(this.mhjToggleButton.isSelected());
    }

}
