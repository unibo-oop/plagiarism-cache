package talisman.view.menu;

import java.awt.LayoutManager;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import talisman.util.PathUtils;

/**
 * Panel used to show all the players ready to start a game.
 * 
 * @author Alberto Arduini
 *
 */
public class PlayersInfoPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final int OFFSET = 5;

    private final List<PlayerInfoPanel> playerPanels;

    /**
     * Creates a new panel.
     */
    public PlayersInfoPanel() {
        this.playerPanels = new ArrayList<>();

        final LayoutManager layout = new BoxLayout(this, BoxLayout.X_AXIS);
        this.setLayout(layout);
    }

    /**
     * Adds a player to the panel.
     * 
     * @param character the player character
     * @param name      the player name
     */
    public void addPlayer(final int character, final String name) {
        final PlayerInfoPanel panel = this.createPlayerPanel(character, name);
        this.playerPanels.add(panel);
        this.add(panel);
    }

    private PlayerInfoPanel createPlayerPanel(final int character, final String name) {
        final String imagePath = PathUtils.getPathToCharacterIcon(character);
        final PlayerInfoPanel panel = new PlayerInfoPanel(imagePath, name);
        if (this.playerPanels.size() > 0) {
            panel.setBorder(BorderFactory.createEmptyBorder(0, PlayersInfoPanel.OFFSET, 0, 0));
        }
        return panel;
    }
}
