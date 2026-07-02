package controllers;

import controllers.utilities.TubeMap;

/**
 * This interfaces initializes and controls the TubeRectangles of the view.
 */
public interface TubeController {

    /**
     *Call {@link TubeMap#addToMap(Pair)} and {@link TubeMap#printPairTube(Pair)} methods that add the new Pair of tubes' rectangles to the treeMap and print them in the view.
     */
    void addTube();

    /**
     * Call {@link TubeMap#scrollTubePair()} method that scroll all the tubes' rectangles in the screen.
     */
    void scrollTubes();

    /**
     *
     * @return the class that manage all the tube's rectangle in the screen
     */
    TubeMap getTubeMap();
}
