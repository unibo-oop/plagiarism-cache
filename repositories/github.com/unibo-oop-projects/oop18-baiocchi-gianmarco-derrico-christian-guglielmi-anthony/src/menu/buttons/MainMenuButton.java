package menu.buttons;

import javax.swing.JFrame;

import game.state.MainMenuState;
import menu.components.GameButtonImpl;

/**
 * This class extends {@link GameButtonImpl}. It allows easy management
 * of the commons behaviors and settings of the MainMenu button in case of one or
 * multiple uses within its own program. For further information see
 * {@link JButton} super class.
 */
public class MainMenuButton extends GameButtonImpl {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final String MAIN_MENU = "MAIN MENU";

    /**
//     * Creates a "main menu" button with his frame reference.
     * 
     * @param frame the where the button is used.
     */
    public MainMenuButton(final JFrame frame) {
        super(MAIN_MENU);
        super.addActionListener(l -> {
            game.state.StateController.getGameContext().setNewState(new MainMenuState());
            frame.dispose();
        });
    }
}
