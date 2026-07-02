package menu.buttons;

import javax.swing.JFrame;

import game.state.GameOverState;
import game.state.StateController;
import menu.components.GameButtonImpl;

/**
 * This class extends {@link GameButtonImpl}. It allows easy management
 * of the commons behaviors and settings of the Info button in case of one or
 * multiple uses within its own program. For further information see
 * {@link JButton} super class.
 */
public class InfoButton extends GameButtonImpl {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final String INFO = "INFO";

    /**
     * Creates a "info" button with his frame reference.
     * 
     * @param frame the where the button is used.
     */
    public InfoButton(final JFrame frame) {
        super(INFO);
        super.addActionListener(l -> {
            //metodo da correggere con lo stato al menù info, implementato così per rimuovere segnalazioni pmd
            StateController.getGameContext().setNewState(new GameOverState());
            frame.dispose();
        });
    }
}
