package it.unibo.pacman.view.mapeditor;

import javax.swing.JLabel;

/**
 * Model the tile of the grid.
 */
public class GridTile extends JLabel {

    private static final long serialVersionUID = 5049973399457959943L;

    private Tile tile;

    /**
     * Create a grid tile.
     * 
     * @param tile to be extend
     */
    public GridTile(final Tile tile) {
        this.tile = tile;
        this.update();
    }

    /**
     * Update the JLabel.
     */
    private void update() {
        super.setIcon(tile.getIcon());
    }
    /**
     * Set a new tile that it should show.
     * 
     * @param tile to be add
     */
    public void setTile(final Tile tile) {
        this.tile = tile;
        this.update();
    }
    /**
     * 
     * @return the tile of the JLabel
     */
    public Tile getTile() {
        return this.tile;
    }

}
