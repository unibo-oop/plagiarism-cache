/**
 * 
 */
package it.unibo.pacman.view.mapeditor;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import it.unibo.pacman.controller.mapeditor.MapEditorController;

/**
 * Implements the view of the grid.
 */
public class GridView extends JPanel {

    private static final long serialVersionUID = -527325970664334556L;

    private final List<List<GridTile>> gridMap = new ArrayList<>();
    private final MapEditorController controller;

    /**
     * Create the view of the grid.
     * 
     * @param controller is the controller of the map editor
     */
    public GridView(final MapEditorController controller) {
        super(new GridLayout(controller.getHeight(), controller.getWidth()));
        this.controller = controller;
        this.loadMap();
    }

    /**
     * Load the grid map.
     */
    private void loadMap() {
        for (int y = 0; y < controller.getHeight(); y++) {
            gridMap.add(new ArrayList<GridTile>());
            for (int x = 0; x < controller.getWidth(); x++) {
                final GridTile gt = new GridTile(new Tile(controller.getType(x, y)));
                gt.setSize(Tile.getSize(), Tile.getSize());
                gridMap.get(y).add(gt);
                this.add(gridMap.get(y).get(x));
            }
        }
    }

    /**
     *  {@inheritDoc}
     */
    public final void updateGrid(final int x, final int y, final Tile tile) {
        this.gridMap.get(y).get(x).setTile(tile);
    }

}
