package it.unibo.pacman.view.mapeditor;

import java.awt.Dimension;

import javax.swing.JButton;

import it.unibo.pacman.controller.mapeditor.MapEditorController;

/**
 * Model a button to select the preferred tile.
 *
 */
public class SelectionTile extends JButton {

    private static final long serialVersionUID = 7741560986021405539L;

    private final Tile tile;
    private final MapEditorController controller;

    /**
     * Create a JButton for a tile.
     * 
     * @param controller is the editor controller
     * @param tile is the tile to be selected
     */
    public SelectionTile(final MapEditorController controller, final Tile tile) {
        this.controller = controller;
        this.tile = tile;
        this.createButton();
    }

    /**
     * Creates the button.
     */
    private void createButton() {
        this.setPreferredSize(new Dimension(Tile.getSize(), Tile.getSize()));
        this.setIcon(tile.getIcon());
        this.addActionListener(e -> {
            controller.setSelectedTile(tile);
        });
    }

}
