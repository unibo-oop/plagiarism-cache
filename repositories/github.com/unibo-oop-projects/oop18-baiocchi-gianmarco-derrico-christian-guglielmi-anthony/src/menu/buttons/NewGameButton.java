package menu.buttons;

import javax.swing.JFrame;

import game.state.GameOverState;
import game.state.StateController;
import menu.components.GameButtonImpl;

/**
 * This class extends {@link GameButtonImpl}. It allows easy management of the
 * commons behaviors and settings of the NewGame button in case of one or
 * multiple uses within its own program. For further information see
 * {@link JButton} super class.
 */
public class NewGameButton extends GameButtonImpl {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static final String START = "NEW GAME";

    /**
     * Creates a "new game" button with his frame reference.
     * 
     * @param frame 
     */
    public NewGameButton(final JFrame frame) {
        super(START);
        super.addActionListener(l -> {
            //metodo da correggere con lo stato al new game, implementato così per rimuovere segnalazioni pmd
            StateController.getGameContext().setNewState(new GameOverState());
            frame.dispose();
        });
    }
}
