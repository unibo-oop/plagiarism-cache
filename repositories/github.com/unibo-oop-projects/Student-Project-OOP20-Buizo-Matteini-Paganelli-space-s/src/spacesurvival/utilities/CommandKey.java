package spacesurvival.utilities;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Class for the command type, all available keys and their key codes.
 *
 */
public enum CommandKey {
    /**
     * Type of command to pass to the CallerCommand to rotate the spaceship left.
     */
    KEY_LEFT(37, CommandType.PRESSED),
    /**
     * Type of command to pass to the CallerCommand to acelerate the spaceship.
     */
    KEY_UP_PRESSED(38, CommandType.PRESSED),
    /**
     * Type of command to pass to the CallerCommand to decelerate the spaceship.
     */
    KEY_UP_RELEASED(38, CommandType.RELEASED),
    /**
     * Type of command to pass to the CallerCommand to rotate the spaceship right.
     */
    KEY_RIGHT(39, CommandType.PRESSED),
    /**
     * Type of command to pass to the CallerAudio to let the spaceship shot.
     */
    KEY_SPACE_BAR(32, CommandType.PRESSED),
    /**
     * Type of command to pass to the CallerCommand to let the spaceship shot.
     */
    KEY_K(75, CommandType.PRESSED),
    /**
     * Type of command to pass to the CallerCommand to rotate the spaceship left.
     */
    KEY_A(65, CommandType.PRESSED),
    /**
     * Type of command to pass to the CallerCommand to acelerate the spaceship.
     */
    KEY_W_PRESSED(87, CommandType.PRESSED),
    /**
     * Type of command to pass to the CallerCommand to decelerate the spaceship.
     */
    KEY_W_RELEASED(87, CommandType.RELEASED),
    /**
     * Type of command to pass to the CallerCommand to rotate the spaceship right.
     */
    KEY_D(68, CommandType.PRESSED);

    /**
     * Code of the relative character.
     */
    private final Integer keyCode;
    private final CommandType type;

    /**
     * Constructor for create enum associating a key code.
     */
    CommandKey(final Integer keyCode, final CommandType type) {
        this.keyCode = keyCode;
        this.type = type;
    }

    /**
     * Return an optional of CommandType from the passed value if present.
     * 
     * @param value int rapresenting the code of the key
     * @param type  CommandType rapresenting the type of the command
     * @return the specific CommanType or an empty optional 
     */
    public static Optional<CommandKey> getValue(final int value, final CommandType type) {
        return Stream.of(CommandKey.values()).filter(cmd -> cmd.getKeyCode() == value && cmd.getType().equals(type)).findFirst();
    }

    /**
     * Return the key code.
     * 
     * @return the specific key code
     */
    public int getKeyCode() {
        return this.keyCode;
    }

    /**
     * Return the type.
     * 
     * @return the specific key code
     */
    public CommandType getType() {
        return this.type;
    }

}
