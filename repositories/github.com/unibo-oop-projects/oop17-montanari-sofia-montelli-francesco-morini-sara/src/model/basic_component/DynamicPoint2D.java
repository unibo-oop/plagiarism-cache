package model.basic_component;

//PROVA1234 

/**
 * This interfaces extends the Static point by adding the movement capabilities.
 */
public interface DynamicPoint2D extends StaticPoint2D {

    /**
     * Changes the location of the point.
     * @param xCord the new x of the point
     * @param yCord the new y of the point
     */
    void moveTo(int xCord, int yCord);
    /** 
     * translate the point with the specified values.
     * @param xDelta the value of then x translation.
     * @param yDelta the value of the y translation.
     */
    void traslate(int xDelta, int yDelta);
}
