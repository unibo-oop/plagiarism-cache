package gamemenu;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import gamegraphics.ViewGame;
import playcontroller.PlayController;

/**
 * Implmentation of GameMenu for the Pause.
 *
 */
public class PauseMenuImpl implements GameMenu {
    private final PlayController playController;
    private final ViewGame viewGame;

    /**
     * @param playController : PlayController Object
     * @param viewGame : ViewGame Object
     */
    public PauseMenuImpl(final PlayController playController, final ViewGame viewGame) {
        this.playController = playController;
        this.viewGame = viewGame;
    }

    @Override
    public final void activate(final int score) {
        this.playController.stop(); // Stops the Timer for the Pause

        final JCheckBox checkAudio = new JCheckBox("Mute Audio");
        if (!this.playController.getSound().isEnabled()) {
            checkAudio.doClick(); // If the Audio is already muted, the checkbox will be checked
        }
        final JCheckBox checkProjection = new JCheckBox("Projection");
        if (this.playController.getProjection().isEnabled()) {
            checkProjection.doClick(); // If the Projection is already turned off, the checkbox will be checked
        }
        final Object[] options = { "Quit to Main Menu", "Resume", "Restart", checkAudio, checkProjection };
        final int optionSelected = JOptionPane.showOptionDialog(viewGame.getMainPanel(),
                "The Game is Paused\nYour Score is:" + score, "Pause", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);

        if (checkAudio.isSelected()) {
            this.playController.getSound().setEnable(false);
        } else {
            this.playController.getSound().setEnable(true);
        }
        if (checkProjection.isSelected()) {
            this.playController.getProjection().setEnable(true);
            this.viewGame.drawProjection(this.playController.getProjection().newProjection(),
                    this.playController.getProjection().getColor());
        } else {
            this.playController.getProjection().setEnable(false);
            viewGame.drawProjection(this.playController.getProjection().newProjection(),
                    this.playController.getProjection().getColor());
        }

        if (optionSelected == 0) {
            this.playController.stop();
            this.playController.backToMenu();
        } else if (optionSelected == 1 || optionSelected == -1) {
            // -1 is if the user closes the dialog using the X button
            this.playController.resume();
        } else if (optionSelected == 2) {
            this.playController.restart();
        }
    }
}
