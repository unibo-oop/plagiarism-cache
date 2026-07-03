package oop.lit.model.simplegame.elements;

/**
 * A simple board element with two faces.
 * This element provides also a method to change which of his face is up.
 */
public interface FlippableSBE extends BasicSBE {
    /**
     * Flips this element.
     */
    void flip();
}