package talisman.view.menu;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import talisman.view.ImagePanel;

/**
 * A panel used to show information on a player that is waiting for a game to
 * start.
 * 
 * @author Alberto Arduini
 *
 */
class PlayerInfoPanel extends ImagePanel {
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new info panel.
     * 
     * @param imagePath  the character image path
     * @param playerName the player name
     */
    PlayerInfoPanel(final String imagePath, final String playerName) {
        super(imagePath);
        this.setOpaque(false);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        final JLabel nameLabel = new JLabel(playerName);
        nameLabel.setBackground(Color.BLACK);
        this.add(nameLabel);

        final Dimension size = new Dimension(100, 100);
        this.setMinimumSize(size);
        this.setMaximumSize(size);
    }
}
