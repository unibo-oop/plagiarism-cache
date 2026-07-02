package unibo.exiled.view;

import unibo.exiled.model.menu.Command;
import unibo.exiled.model.menu.MenuItem;
import unibo.exiled.utilities.ConstantsAndResourceLoader;
import unibo.exiled.view.items.GameButton;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.io.Serial;
import java.util.List;
import java.util.Optional;

/**
 * The view of the in-game menu.
 */
public final class MenuView extends JPanel {
    @Serial
    private static final long serialVersionUID = 4L;
    private static final int GRID_INSET = 3;

    /**
     * The constructor of the in-game view.
     *
     * @param menuItems The items of the menu.
     * @param gameView  The view of the game.
     */
    public MenuView(final List<MenuItem> menuItems, final Optional<GameView> gameView) {
        super();

        // CREATING STANDARD UI
        final JPanel buttonListPanel = new JPanel(new GridBagLayout());
        final GridBagConstraints cnst = new GridBagConstraints();
        cnst.gridy = 0;
        cnst.insets = new Insets(GRID_INSET, GRID_INSET, GRID_INSET, GRID_INSET);
        cnst.fill = GridBagConstraints.HORIZONTAL;
        final JLabel logoLabel = new JLabel(new ImageIcon(ConstantsAndResourceLoader
                .getResourceURLFromPath(ConstantsAndResourceLoader.IMAGES_PATH
                        + "/interface/logo.png")));
        buttonListPanel.add(logoLabel, cnst);
        cnst.gridy++;

        for (final MenuItem menuItem : menuItems) {
            final GameButton gameButton = getGameButton(gameView, menuItem);

            buttonListPanel.add(gameButton, cnst);
            cnst.gridy++;
        }

        this.setLayout(new BorderLayout());
        this.add(buttonListPanel, BorderLayout.CENTER);
    }

    private GameButton getGameButton(final Optional<GameView> gameView, final MenuItem menuItem) {
        final GameButton gameButton = new GameButton(menuItem.getItemText());
        gameButton.setActionCommand(menuItem.getItemCommand().getCommandString());
        gameButton.addActionListener(e -> {
            if (e.getActionCommand().equals(Command.NEW_GAME.getCommandString())) {
                final PlayerClassView playerClassView = new PlayerClassView();
                playerClassView.display();
                for (final Frame f : Frame.getFrames()) {
                    if (!"Player class".equals(f.getName())) {
                        f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
                    }
                }
            } else if (e.getActionCommand().equals(Command.CLOSE_MENU.getCommandString())) {
                gameView.ifPresent(GameView::hideMenu);
            } else if (e.getActionCommand().equals(Command.QUIT.getCommandString())) {
                final int dialogResult = JOptionPane.showConfirmDialog(this,
                        "Would you like to quit the game?", "Warning",
                        JOptionPane.YES_NO_OPTION);

                if (dialogResult == JOptionPane.YES_OPTION) {
                    for (final Frame f : Frame.getFrames()) {
                        f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
                    }
                }
            } else {
                throw new IllegalArgumentException("Command is not valid");
            }
        });
        return gameButton;
    }

}
