package ballblast.settings;

import javafx.scene.input.KeyCode;

/**
 * 
 *
 */
public enum KeyCodeSet {
    /**
     * 
     */
    SET_ONE(KeyCode.RIGHT, KeyCode.LEFT, KeyCode.SPACE),
    /**
     * 
     */
    SET_TWO(KeyCode.D, KeyCode.A, KeyCode.CONTROL);

    private final KeyCode moveRight;
    private final KeyCode moveLeft;
    private final KeyCode shoot;

    /**
     * 
     * @param moveRight the {@link KeyCode} for right moving.
     * @param moveLeft  the {@link KeyCode} for left moving.
     * @param shoot     the {@link KeyCode} for shooting.
     */
    KeyCodeSet(final KeyCode moveRight, final KeyCode moveLeft, final KeyCode shoot) {
        this.moveRight = moveRight;
        this.moveLeft = moveLeft;
        this.shoot = shoot;
    }

    /**
     * 
     * @return {@link KeyCode} used to move right.
     */
    public KeyCode getMoveRight() {
        return this.moveRight;
    }

    /**
     * 
     * @return {@link KeyCode} used to move left.
     */
    public KeyCode getMoveLeft() {
        return this.moveLeft;
    }

    /**
     * 
     * @return {@link KeyCode} used to shoot.
     */
    public KeyCode getShoot() {
        return this.shoot;
    }
}
