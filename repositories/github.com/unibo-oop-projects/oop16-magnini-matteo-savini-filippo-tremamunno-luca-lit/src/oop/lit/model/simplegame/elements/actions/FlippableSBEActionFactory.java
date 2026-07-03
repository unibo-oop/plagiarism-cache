package oop.lit.model.simplegame.elements.actions;

import java.util.Arrays;
import java.util.Collection;

import oop.lit.model.Action;
import oop.lit.model.simplegame.elements.FlippableSBE;

/**
 * A BasicSBEActionFactory, providing action factory methods for a FlippableSBE also.
 */
public interface FlippableSBEActionFactory extends BasicSBEActionFactory {

    /**
     * Get a flip action for the provided element.
     * @param element
     *      an element.
     * @return
     *      the action.
     */
    default Action getFlipAction(FlippableSBE element) {
        return this.getFlipAction(Arrays.asList(element));
    }

    /**
     * Get a flip action for the provided elements.
     * @param elements
     *      a collection of elements.
     * @return
     *      the action.
     */
    Action getFlipAction(Collection<FlippableSBE> elements);

}