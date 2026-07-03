package oop.lit.model.simplegame.elements.actions;

import java.util.Arrays;
import java.util.Collection;

import oop.lit.model.Action;
import oop.lit.model.simplegame.elements.GroupSBE;

/**
 * An action factory for a Group simple board element.
 */
public interface GroupSBEActionFactory extends SBEActionFactory {

    /**
     * Get an action used to show the contained group in the group viewer as a non-selectable group.
     * @param element
     *      an element.
     * @return
     *      the action.
     */
    Action getShowGroupAction(GroupSBE element);

    /**
     * Get an action used to show the contained group in the group viewer as a selectable group;
     * users will be able to select the contained elements and perform actions on the selected elements.
     * @param element
     *      an element.
     * @return
     *      the action.
     */
    Action getUseGroupAction(GroupSBE element);

    /**
     * Get a shuffle action for the provided element.
     * @param element
     *      an element.
     * @return
     *      the action.
     */
    default Action getShuffleAction(GroupSBE element) {
        return getShuffleAction(Arrays.asList(element));
    }

    /**
     * Get a shuffle action for the provided elements (shuffles them all at once.
     * @param elements
     *      a collection of elements.
     * @return
     *      the action.
     */
    Action getShuffleAction(Collection<GroupSBE> elements);

    /**
     * Get a draw action for the provided element. Sends the first element of the group to the board it belongs to.
     * @param element
     *      an element.
     * @return
     *      the action.
     */
    Action getDrawFromTopAction(GroupSBE element);

    /**
     * Get a draw action for the provided element. Sends the last element of the group to the board it belongs to.
     * @param element
     *      an element.
     * @return
     *      the action.
     */
    Action getDrawFromBottomAction(GroupSBE element);

    /**
     * Get a draw action for the provided element. Sends a random element of the group to the board it belongs to.
     * @param element
     *      an element.
     * @return
     *      the action.
     */
    Action getDrawRandomAction(GroupSBE element);

    /**
     * Get a draw action for the provided element. Sends the element of the group in the specified position to the board it belongs to.
     * @param element
     *      an element.
     * @return
     *      the action.
     */
    Action getDrawSpecificAction(GroupSBE element);

}