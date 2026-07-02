package controller.input;

import enums.KeyInput;

/**
 * Class for managing the input from keyboard. Implements the InputController
 * interface.
 */
public final class KeyboardInputController implements InputController {

    // The enumerations for the movements.
    private final KeyInput keyMoveUp;
    private final KeyInput keyMoveDown;
    private final KeyInput keyMoveLeft;
    private final KeyInput keyMoveRight;
    private final KeyInput keyFire;

    // The booleans for set the movements.
    private boolean moveUp;
    private boolean moveDown;
    private boolean moveLeft;
    private boolean moveRight;
    private boolean fire;

    /**
     * Method constructor of the keyboard controller.
     * 
     * @param keyMoveUp    key for moving up the entity.
     * @param keyMoveDown  key for moving down the entity.
     * @param keyMoveLeft  key for moving left the entity.
     * @param keyMoveRight key for moving right the entity.
     * @param keyFire      key for fire the entity.
     */
    public KeyboardInputController(final KeyInput keyMoveUp, final KeyInput keyMoveDown, final KeyInput keyMoveLeft,
            final KeyInput keyMoveRight, final KeyInput keyFire) {
        this.keyMoveUp = keyMoveUp;
        this.keyMoveDown = keyMoveDown;
        this.keyMoveLeft = keyMoveLeft;
        this.keyMoveRight = keyMoveRight;
        this.keyFire = keyFire;
    }

    @Override
    public boolean isMoveUp() {
        return moveUp;
    }

    @Override
    public boolean isMoveDown() {
        return moveDown;
    }

    @Override
    public boolean isMoveLeft() {
        return moveLeft;
    }

    @Override
    public boolean isMoveRight() {
        return moveRight;
    }

    @Override
    public boolean isFire() {
        return fire;
    }

    /**
     * Method that handle a key pressed event.
     * 
     * @param keyCode the key that is pressed.
     */
    public void notifyKeyPressed(final KeyInput keyCode) {
        if (keyCode == keyMoveUp) {
            moveUp = true;
        } else if (keyCode == keyMoveDown) {
            moveDown = true;
        } else if (keyCode == keyMoveRight) {
            moveRight = true;
        } else if (keyCode == keyMoveLeft) {
            moveLeft = true;
        } else if (keyCode == keyFire) {
            fire = true;
        }
    }

    /**
     * Method that handle a key released event.
     * 
     * @param keyCode the key that is released.
     */
    public void notifyKeyReleased(final KeyInput keyCode) {
        if (keyCode == keyMoveUp) {
            moveUp = false;
        } else if (keyCode == keyMoveDown) {
            moveDown = false;
        } else if (keyCode == keyMoveRight) {
            moveRight = false;
        } else if (keyCode == keyMoveLeft) {
            moveLeft = false;
        } else if (keyCode == keyFire) {
            fire = false;
        }
    }

}
