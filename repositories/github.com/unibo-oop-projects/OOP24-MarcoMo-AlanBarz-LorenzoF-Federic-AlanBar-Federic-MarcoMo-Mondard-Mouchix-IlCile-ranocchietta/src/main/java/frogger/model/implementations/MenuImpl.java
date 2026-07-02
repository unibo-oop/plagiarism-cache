package frogger.model.implementations;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import frogger.common.Constants;
import frogger.common.GameState;
import frogger.model.interfaces.Button;
import frogger.model.interfaces.Menu;

/**
 * Implementation of the {@link Menu} interface that manages a list of menu buttons.
 * <p>
 * This class is responsible for creating, updating, and handling mouse interactions
 * with menu buttons, each associated with a specific {@link GameState}.
 * </p>
 *
 * <ul>
 *   <li>Buttons are positioned vertically centered in the frame, with configurable spacing.</li>
 *   <li>Handles mouse events to update button states and trigger game state changes.</li>
 * </ul>
 */
public class MenuImpl implements Menu {

    private final List<Button> buttons = new LinkedList<>();

    /**
     * Constructs a MenuImpl with a list of game states.
     * <p>
     * Each button is created based on the provided game states, positioned
     * vertically centered in the frame.
     * </p>
     *
     * @param states the game states to associate with the menu buttons
     */
    public MenuImpl(final GameState... states) {
        loadButtons(states);
    }

    /**
     * Loads and positions menu buttons corresponding to the given game states.
     * <p>
     * The buttons are horizontally centered on the screen and vertically spaced
     * based on the total number of {@link GameState} entries. Each button is associated
     * with an image index matching the ordinal value of its respective state.
     * </p>
     */
    private void loadButtons(final GameState... states) {
        final int xPos = Constants.FRAME_WIDTH / 2 - Constants.BUTTON_WIDTH / 2;
        final int yPos = Constants.FRAME_HEIGHT / 2;
        int i = 0;
        for (final GameState state : states) {
            final int offset = (i - states.length / 2) * (Constants.BUTTON_HEIGHT + Constants.BUTTONS_DISTANCE);
            final int imgIndex = state.ordinal();
            buttons.add(new MenuButtons(xPos, yPos + offset, imgIndex, states[i]));
            i++;
        }
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public List<Button> getButtonList() {
        return new ArrayList<>(buttons);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        for (final Button button : buttons) {
            button.update();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mousePressed(final MouseEvent e) {
       for (final Button button : buttons) {
            if (isIn(e, button)) {
                button.setMousePressed(true);
                break;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseReleased(final MouseEvent e) {
       for (final Button button : buttons) {
            if (isIn(e, button) && button.isMousePressed()) {
                button.applyGameState();
                break;
            }
        }
        resetButtons();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseMoved(final MouseEvent e) {
        for (final Button button : buttons) {
            button.setMouseOver(false);
        }
        for (final Button button : buttons) {
            if (isIn(e, button)) {
                button.setMouseOver(true);
                break;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isIn(final MouseEvent e, final Button button) {
        return button.getBounds().contains(e.getX(), e.getY());
    }

    /**
     * Resets the mouse pressed state of all buttons in the menu.
     * <p>
     * This method is called after a mouse release event to ensure that
     * buttons are ready for the next interaction.
     * </p>
     */
    private void resetButtons() {
       for (final Button button : buttons) {
            button.resetBools();
        } 
    }
}
