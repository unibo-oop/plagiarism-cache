package vg.view;

import vg.view.utils.KeyAction;

/**
 * Interface for fxml controllers (single scene).
 */
public interface SceneController {
    /**
     * Based on a {@link KeyAction}, the controller
     * will createMysteryBox an effect.
     * @param k {@link KeyAction}
     */
    void keyTapped(KeyAction k);

    /**
     *
     * @param k {@link KeyAction}
     */
    void keyPressed(KeyAction k);

    /**
     *
     * @param k {@link KeyAction}
     */
    void keyReleased(KeyAction k);

}
