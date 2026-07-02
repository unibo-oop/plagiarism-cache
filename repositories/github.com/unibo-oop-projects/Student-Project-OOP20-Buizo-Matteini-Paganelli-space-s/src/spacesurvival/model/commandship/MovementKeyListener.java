package spacesurvival.model.commandship;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import spacesurvival.model.common.Pair;
import spacesurvival.utilities.CommandKey;
import spacesurvival.utilities.CommandType;

/**
 * Movement Key Listener for keyboard input.
 */
public class MovementKeyListener implements KeyListener {

    private final List<Pair<CommandKey, CommandType>> commandList;
    /** 
     * Initialize the caller command.
     * 
     */
    public MovementKeyListener() {
        this.commandList = new LinkedList<>();
    }

    /**
     * Return the command list of the ship composed by the input key code and the command type.
     * 
     * @return the command list of the ship
     */
    public List<Pair<CommandKey, CommandType>> getSpaceShipCommandList() {
        return new LinkedList<Pair<CommandKey, CommandType>>(this.commandList);
    }

    /**
     * Clear the current command list.
     */
    public void clearSpaceShipCommandList() {
        this.commandList.clear();
    }

    /** 
     * Read the pressed key and send the right command.
     * 
     * @param e KeyEvent the event of the pressed key
     */
    @Override
    public void keyTyped(final KeyEvent e) {
    }

    /** 
     * Read the pressed key and send the right command.
     * 
     * @param e KeyEvent the event of the pressed key
     */
    @Override
    public void keyPressed(final KeyEvent e) {
        final int pressedKeyCode = e.getKeyCode();
        if (canHandleKey(pressedKeyCode, CommandType.PRESSED)) {
            final CommandKey cmd = translateKeyCode(pressedKeyCode, CommandType.PRESSED).get();
            this.commandList.add(new Pair<CommandKey, CommandType>(cmd, CommandType.PRESSED));
        }
    }

    /** 
     * Read the released key and send the right command.
     * 
     * @param e KeyEvent the event of the released key
     */
    @Override
    public void keyReleased(final KeyEvent e) {
        final int pressedKeyCode = e.getKeyCode();
        if (canHandleKey(pressedKeyCode, CommandType.RELEASED)) {
            final CommandKey cmd = translateKeyCode(pressedKeyCode, CommandType.RELEASED).get();
            this.commandList.add(new Pair<CommandKey, CommandType>(cmd, CommandType.RELEASED));
        }
    }

    /** 
     * Check if the passed key code is accepted from the command caller.
     * 
     * @param currentKeyCode code to check
     * @return retur n
     */
    private boolean canHandleKey(final int currentKeyCode, final CommandType type) {
        return CommandKey.getValue(currentKeyCode, type).isPresent();
    }

    /**
     * Traduce the key code to the respective command type.
     * 
     * @param keyCode the code to be traduced
     * @param type the type of the command to be traduced
     * @return the respective command type is present or an empty optional
     */
    public Optional<CommandKey> translateKeyCode(final int keyCode, final CommandType type) {
        if (canHandleKey(keyCode, type)) {
            return CommandKey.getValue(keyCode, type);
        }
        return Optional.empty();
    }
}
