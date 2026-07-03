package zengine.interfaces;

import zengine.constants.ZengineKeyboardConstant;
import zengine.constants.ZengineMouseConstant;

/**
 * This interface represents the Zengine component that receives input from
 * keyboard and mouse. It contains methods that checks if some buttons are
 * pressed, relased, being held etc. It does also provide methods to find the
 * mouse position.
 */
public interface GameFunctionsInput {

    /**
     * returns true if key keyCode has been pressed in the last quantum of time
     * only, false otherwise. In order to return true again, the key needs to be
     * released and pressed again.
     * 
     * @param keyCode
     *            key to be checked
     * @return true in the first moment the key starts being pressed
     */
    boolean keyboardCheckPressed(ZengineKeyboardConstant keyCode);

    /**
     * returns true if key keyCode is being kept pressed in the current quantum
     * of time.
     * 
     * @param keyCode
     *            key to be checked
     * @return true as long as the key is currently pressed
     */
    boolean keyboardCheck(ZengineKeyboardConstant keyCode);

    /**
     * returns true if key keyCode has been released in the last quantum of time
     * only, false otherwise. In order to return true again, the key needs to be
     * pressed and released again.
     * 
     * @param keyCode
     *            key to be checked
     * @return true in the moment the key stops being pressed
     */
    boolean keyboardCheckReleased(ZengineKeyboardConstant keyCode);

    /**
     * returns true if the given mouse button has been pressed in the last
     * quantum of time only, false otherwise. In order to return true again, the
     * button needs to be released and pressed again.
     * 
     * @param mouseButtonCode
     *            button to check
     * @return true in the first moment the button starts being pressed
     */
    boolean mouseCheckPressed(ZengineMouseConstant mouseButtonCode);

    /**
     * returns true if the given mouse button is being kept pressed in the
     * current quantum of time.
     * 
     * @param mouseButtonCode
     *            button to check
     * @return true as long as the button is currently being pressed
     */
    boolean mouseCheck(ZengineMouseConstant mouseButtonCode);

    /**
     * returns true if the given mouse button has been released in the last
     * quantum of time only, false otherwise. In order to return true again, the
     * button needs to be pressed and released again.
     * 
     * @param mouseButtonCode
     *            button to check
     * @return true in the moment the button stops being pressed
     */
    boolean mouseCheckReleased(ZengineMouseConstant mouseButtonCode);

    /**
     * returns the x position of the mouse cursor within the game world
     * (considering view position and scaling factor).
     * 
     * @return x position of the mouse relative to the game world
     */
    double mouseX();

    /**
     * returns the y position of the mouse cursor within the game world
     * (considering view position and scaling factor).
     * 
     * @return y position of the mouse relative to the game world
     */
    double mouseY();

    /**
     * returns the x position of the mouse cursor within the user's whole
     * screen.
     * 
     * @return absolute x position of the mouse within the whole screen
     */
    int displayMouseX();

    /**
     * returns the y position of the mouse cursor within the user's whole
     * screen.
     * 
     * @return absolute y position of the mouse within the whole screen
     */
    int displayMouseY();

    /**
     * returns the x position of the mouse cursor within the game window.
     * 
     * @return x position of the mouse relative to the window
     */
    int windowMouseX();

    /**
     * returns the y position of the mouse cursor within the game window.
     * 
     * @return y position of the mouse relative to the window
     */
    int windowMouseY();
}
