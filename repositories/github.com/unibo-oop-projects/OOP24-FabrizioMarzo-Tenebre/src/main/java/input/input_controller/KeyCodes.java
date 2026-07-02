package input.input_controller;

/**
 * Enumeration representing directional inputs mapped to specific key codes.
 * 
 * <p>
 * Each direction corresponds to an arrow key or a "no input" state,
 * identified by its associated key code.
 * </p>
 */
public enum KeyCodes {

    /** Arrow Up key (key code 38) */
    ARROW_UP(38),

    /** Arrow Down key (key code 40) */
    ARROW_DOWN(40),

    /** Arrow Left key (key code 37) */
    ARROW_LEFT(37),

    /** Arrow Right key (key code 39) */
    ARROW_RIGHT(39),

    /** 'W' key for moving up (key code 87) */
    KEY_W(87),

    /** 'A' key for moving left (key code 65) */
    KEY_A(65),

    /** 'S' key for moving down (key code 83) */
    KEY_S(83),

    /** 'D' key for moving right (key code 68) */
    KEY_D(68),

    /** Space key for actions (key code 32) */
    SPACE(32),

    /** No input (used as default state) */
    NONE(-1);

    private final int keyCode;

    /**
     * Constructs a new {@code Direction} with the specified key code.
     *
     * @param keyCode the key code associated with this direction
     */
    KeyCodes(final int keyCode) {
        this.keyCode = keyCode;
    }

    /**
     * Returns the key code associated with this direction.
     *
     * @return the key code
     */
    public int getKeyCode() {
        return keyCode;
    }

}
