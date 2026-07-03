package controller;

import java.util.List;

import utility.GraphicType;
import view.PongElement;

/**
 * This class is used as a factory/destroyer of PongElements, that keeps track of the current elem to be displeyed in a list.
 * @author Missi
 *
 */
public interface GraphicManager {

    /**
     * Instantiate a new PongElement and add it to the internal list.
     * @param s **the url of the image resource as string**
     * @param type **the type of elem to be rappresented**
     * @return the graphic rappresentation of the elem
     */
    PongElement createPongElement(String s, GraphicType type);

    /**
     * Remove a PongElement from the internal list.
     * @param list **the list of PongElement to remove**
     */
    void removePongElement(List<PongElement> list);

    /**
     * @return the list of current PongElements to be displayed
     */
    List<PongElement> getList();

}
