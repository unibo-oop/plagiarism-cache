package view.settingsobserver;

import controller.Controller;
import javafx.scene.control.ToggleButton;

/**
 *
 * Chiara Tarantino.
 * A class that represents an observer for hedge jumping mode toggle button.
 *
 */
public class HedgeJumpingObserver implements Observer {

    private final ToggleButton hjToggleButton;
    private final Controller controller;

    /**
     * Class constructor.
     *
     * @param hedgeJumping
     *            toggle button that shows if hedgeJumping mode is active or not.
     * @param controller
     *            controller
     */
    public HedgeJumpingObserver(final ToggleButton hedgeJumping, final Controller controller) {
        this.hjToggleButton = hedgeJumping;
        this.controller = controller;
        this.hjToggleButton.selectedProperty().addListener(e -> this.setText());
        this.setText();
    }

    private void setText() {
        this.hjToggleButton.setText(this.hjToggleButton.isSelected() ? "Active" : "Not Active");
    }

    @Override
    public final void updateSettings() {
        this.controller.setEdgeJumpingMode(this.hjToggleButton.isSelected());
    }

}
