package view.render;

import java.util.List;
import java.util.Map;

import controller.ModelWrapper;
import util.ImageLoaderProxy.ImageID;
import util.Pair;

/**
 * Class used to draw model contents in a canvas.
 */
public interface CanvasDrawer {

    /**
     * Draw on canvas objects previously setted.
     */
    void draw();

    /**
     * Setter of the variable staticCollections; here are contained all the
     * "unmovable" objects of a room like doors and walls.
     * 
     * @param collection
     *            a collection of ModelWrapper objects
     */
    void setStaticCollection(List<ModelWrapper> collection);

    /**
     * Setter of the variable dynamicCollections; here are contained all the
     * "movable" objects of a room like player, enemies and bullets.
     * 
     * @param collection
     *            a collection of ModelWrapper objects
     */
    void setDynamicCollection(List<ModelWrapper> collection);

    /**
     * Setter of a representation of the model map variable.
     * 
     * @param world
     *            the model map variable
     */
    void setMap(Map<Pair<Integer, Integer>, ImageID> world);

    /**
     * Setter of the player life variable.
     * 
     * @param life
     *            the model player life
     */
    void setPlayerLife(double life);
}
