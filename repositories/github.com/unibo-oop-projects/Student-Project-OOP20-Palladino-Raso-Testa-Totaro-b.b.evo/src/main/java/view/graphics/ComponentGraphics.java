package view.graphics;

import model.entities.GameObject;

public interface ComponentGraphics {

    /**
     * updates the graphic component.
     * @param obj to draw
     * @param graphicsAdapt Graphics adapter
     */
    void update(GameObject obj, AdapterGraphics graphicsAdapt);
}
