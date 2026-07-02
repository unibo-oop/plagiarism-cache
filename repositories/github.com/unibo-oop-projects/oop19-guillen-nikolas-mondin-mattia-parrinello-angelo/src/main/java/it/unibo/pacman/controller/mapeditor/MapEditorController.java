package it.unibo.pacman.controller.mapeditor;

import it.unibo.pacman.model.utilities.EntityType;
import it.unibo.pacman.view.mapeditor.Tile;

/**
 * Model a controller of the map editor.
 */
public interface MapEditorController {

    /**
     * Set the select tile.
     * 
     * @param selectedTile is the selected tile
     */
    void setSelectedTile(Tile selectedTile);

    /**
     * 
     * @return the selected tile
     */
    Tile getSelectedTile();

    /**
     * Update the given position with the select tile.
     * 
     * @param x is the coordinate of the columns
     * @param y is the coordinate of the rows
     */
    void updatePosition(int x, int y);

    /**
     * 
     * @param x is the coordinate of the columns
     * @param y is the coordinate of the rows
     * @return the Type at the given position
     */
    EntityType getType(int x, int y);

    /**
     * Show the editor's view.
     */
    void showEditor();

    /**
     * Save the map.
     * 
     * @param mapName is the name of the new map
     */
    void saveMap(String mapName);

    /**
     * 
     * @return the height of the editor
     */
    int getHeight();

    /**
     * 
     * @return the width of the editor
     */
    int getWidth();

}
