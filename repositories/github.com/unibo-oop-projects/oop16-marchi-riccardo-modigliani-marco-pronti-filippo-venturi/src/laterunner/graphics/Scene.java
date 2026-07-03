package laterunner.graphics;

import laterunner.input.Controller;

/**
 * Scene is the main JFrame that contains every panel.
 *
 */
public interface Scene {

    /**
     * Invokes paintComponent.
     */
    void render();

    /**
     * Sets the input controller for this JFrame.
     * 
     * @param c
     *          an instance of Controller class
     */
    void setInputController(Controller c);

    /**
     * Gets an instance of Road.
     * 
     * @return
     *          an instance of Road class
     */
    Road getRoad();

}
