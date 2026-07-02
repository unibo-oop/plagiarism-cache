package application.view.controls.areasGrid;

import java.util.List;

import application.model.buildables.area.Area;
import application.view.controls.areaViewer.AreaViewer;

/**
 * Interface that contain all methods needed to manage an Area Adder.
 * @author Marcin Pabich
 */
public interface AreasGrid {
	
    
    /**
     * Draw all areas given in the list.
     * @param areas List of the areas
     */
    void drawArea(List<Area> areas);
    
    /**
     * Delete an area on a specific position.
     * @param areaViewer Element that will be deleted
     */
    void deleteArea(AreaViewer areaViewer);
    
    /**
     * Delete every area present int the grid.
     */
    void clearAllAreas();
}
