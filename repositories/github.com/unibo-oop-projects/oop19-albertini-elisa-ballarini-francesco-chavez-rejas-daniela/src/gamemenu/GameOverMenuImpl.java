package gamemenu;

import javax.swing.JOptionPane;
import gamegraphics.ViewGame;
import playcontroller.PlayController;

/**
 * Implementation of the GameMenu for the GameOver.
 *
 */
public class GameOverMenuImpl implements GameMenu {
    private final PlayController playController;
    private final ViewGame viewGame;

    /**
     * @param playController : PlayController Object
     * @param viewGame : ViewGame Object
     */
    public GameOverMenuImpl(final PlayController playController, final ViewGame viewGame) {
        this.playController = playController;
        this.viewGame = viewGame;
    }

    @Override
    public final void activate(final int score) {
        final String[] options = { "Quit to Main Menu", "Restart" };
        final int optionSelected = JOptionPane.showOptionDialog(viewGame.getMainPanel(),
                "Game Over\nYour Score is:" + score, "Game Over", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);

        // -1 is in case the user closes the Dialog using the X button
        if (optionSelected == 0 || optionSelected == -1) {
            this.playController.stop();
            this.playController.backToMenu();
        } else if (optionSelected == 1) {
            this.playController.restart();
        }
    }
}
