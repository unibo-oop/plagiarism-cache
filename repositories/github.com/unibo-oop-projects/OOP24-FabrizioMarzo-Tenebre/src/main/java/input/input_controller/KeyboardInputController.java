package input.input_controller;

/**
 * Implementation of {@link InputController} that interprets keyboard input.
 * 
 * <p>
 * This controller maps specific keyboard key codes to movement and shooting
 * directions,
 * updating its internal state accordingly. It supports arrow keys for shooting
 * directions,
 * WASD keys for movement, space for actions, and a NONE state when no input is
 * detected.
 * </p>
 */
public class KeyboardInputController implements InputController {

	private KeyCodes keyCode = KeyCodes.NONE;

	/**
	 * Sets the current input state to shooting upwards.
	 */
	private void isShootUp() {
		this.keyCode = KeyCodes.ARROW_UP;
	}

	/**
	 * Sets the current input state to shooting downwards.
	 */
	private void isShootDown() {
		this.keyCode = KeyCodes.ARROW_DOWN;
	}

	/**
	 * Sets the current input state to shooting left.
	 */
	private void isShootLeft() {
		this.keyCode = KeyCodes.ARROW_LEFT;
	}

	/**
	 * Sets the current input state to shooting right.
	 */
	private void isShootRight() {
		this.keyCode = KeyCodes.ARROW_RIGHT;
	}

	/**
	 * Sets the current input state to moving upwards.
	 */
	private void isMoveUp() {
		this.keyCode = KeyCodes.KEY_W;
	}

	/**
	 * Sets the current input state to moving downwards.
	 */
	private void isMoveDown() {
		this.keyCode = KeyCodes.KEY_S;
	}

	/**
	 * Sets the current input state to moving left.
	 */
	private void isMoveLeft() {
		this.keyCode = KeyCodes.KEY_A;
	}

	/**
	 * Sets the current input state to shooting right.
	 */
	private void isMoveRight() {
		this.keyCode = KeyCodes.KEY_D;
	}

	/**
	 * Sets the current direction to NONE (no input).
	 */
	private void isMoveNone() {
		this.keyCode = KeyCodes.NONE;
	}

	/**
	 * Sets the current input state to the space key pressed.
	 */
	private void isPresSpace() {
		this.keyCode = KeyCodes.SPACE;
	}

	/**
	 * Returns the current input code representing the latest movement or shooting
	 * direction.
	 * 
	 * @return the integer key code of the current input state
	 */
	@Override
	public int getInputCode() {
		return this.keyCode.getKeyCode();
	}

	/**
	 * Notifies the controller of a new key input and updates the internal state
	 * accordingly.
	 *
	 * Maps the following key codes:
	 * <ul>
	 * <li>37 - Shoot Left</li>
	 * <li>38 - Shoot Up</li>
	 * <li>39 - Shoot Right</li>
	 * <li>40 - Shoot Down</li>
	 * <li>32 - Space</li>
	 * <li>87 (W) - Move Up</li>
	 * <li>65 (A) - Move Left</li>
	 * <li>83 (S) - Move Down</li>
	 * <li>68 (D) - Move Right</li>
	 * <li>Any other key - No Input</li>
	 * </ul>
	 *
	 * @param keyCode the key code received from keyboard input
	 */

	@Override
	public void notifyInput(final int keyCode) {
		switch (keyCode) {
			case 37:
				isShootLeft();
				break;
			case 38:
				isShootUp();
				break;
			case 39:
				isShootRight();
				break;
			case 40:
				isShootDown();
				break;
			case 32:
				isPresSpace();
				break;
			case 87:
				isMoveUp();
				;
				break;
			case 65:
				isMoveLeft();
				break;
			case 83:
				isMoveDown();
				break;
			case 68:
				isMoveRight();
				break;
			default:
				isMoveNone();
				break;
		}

	}

	/**
	 * Notifies the controller that no input is currently active,
	 * resetting the internal state to NONE.
	 */
	@Override
	public void notifyNoInput() {
		isMoveNone();
	}

}
