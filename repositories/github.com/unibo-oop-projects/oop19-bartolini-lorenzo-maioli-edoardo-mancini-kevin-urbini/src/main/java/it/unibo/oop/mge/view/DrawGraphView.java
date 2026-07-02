package it.unibo.oop.mge.view;

import java.util.List;

import it.unibo.oop.mge.c3d.geometry.Segment2D;
import it.unibo.oop.mge.control.DrawGraphViewObserver;

/**
 * The GUI interface.
 */
public interface DrawGraphView {

    /**
     * Start the GUI.
     */
    void start();

    /**
     * Sets the observer.
     *
     * @param observer the new observer
     */
    void setObserver(DrawGraphViewObserver observer);

    /**
     * Plot a graph.
     *
     * @param segments the segments to be plotted
     */
    void plotGraph(List<Segment2D> segments);

    /**
     * Plot the graph's properties.
     * 
     * @param properties the graph's properties to be plotted
     */
    void plotProperties(List<String> properties);

    /**
     * Shows message of expression incorrect.
     */
    void expressionIncorrect();

    /**
     * Shows message of IO error.
     */
    void ioError();
}
