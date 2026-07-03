package oop.lit.model.simplegame.elements.actions;

import java.util.Arrays;
import java.util.Collection;

import oop.lit.model.Action;
import oop.lit.model.simplegame.elements.BasicSBE;

/**
 * An action factory for a Basic simple board element.
 */
public interface BasicSBEActionFactory extends SBEActionFactory {

    //SEND TO GROUP ACTIONS
    /**
     * Get a send to group action. Sends the provided element to the top of the group.
     * @param element
     *      an element.
     * @return
     *      the action.
     */
    default Action getSendToGroupTopAction(BasicSBE element) {
        return getSendToGroupTopAction(Arrays.asList(element));
    }

    /**
     * Get a send to group action. Sends the provided elements to the top of the group.
     * @param elements
     *      an collection of elements.
     * @return
     *      the action.
     */
    Action getSendToGroupTopAction(Collection<BasicSBE> elements);

    /**
     * Get a send to group action. Sends the provided element to the bottom of the group.
     * @param element
     *      an element.
     * @return
     *      the action.
     */
    default Action getSendToGroupBottomAction(BasicSBE element) {
        return getSendToGroupBottomAction(Arrays.asList(element));
    }

    /**
     * Get a send to group action. Sends the provided elements to the bottom of the group.
     * @param elements
     *      an collection of elements.
     * @return
     *      the action.
     */
    Action getSendToGroupBottomAction(Collection<BasicSBE> elements);

    /**
     * Get a send to group action. Sends the provided element to a random position in the group.
     * @param element
     *      an element.
     * @return
     *      the action.
     */
    default Action getSendToGroupRandomAction(BasicSBE element) {
        return getSendToGroupRandomAction(Arrays.asList(element));
    }

    /**
     * Get a send to group action. Sends the provided elements to a random position in the group.
     * @param elements
     *      a collection of elements.
     * @return
     *      the action.
     */
    Action getSendToGroupRandomAction(Collection<BasicSBE> elements);

    /**
     * Get a send to group action. Sends the provided element to the (user) specified position in the group.
     * @param element
     *      an element.
     * @return
     *      the action.
     */
    default Action getSendToGroupSpecificAction(BasicSBE element) {
        return getSendToGroupSpecificAction(Arrays.asList(element));
    }

    /**
     * Get a send to group action. Sends the provided elements to the (user) specified position in the group.
     * @param elements
     *      an collection of elements.
     * @return
     *      the action.
     */
    Action getSendToGroupSpecificAction(Collection<BasicSBE> elements);

    //GET SEND TO HAND ACTION
    /**
     * Get a send to group action. Sends the provided element to the playing
     * player hand or a user defined hand if the player is a gm.
     * 
     * @param element
     *            an element.
     * @return the action.
     */
    default Action getSendToHandAction(BasicSBE element) {
        return getSendToHandAction(Arrays.asList(element));
    }

    /**
     * Get a send to group action. Sends the provided elements to the playing
     * player hand or a user defined hand if the player is a gm.
     * @param elements
     *      a collection of elements.
     * @return
     *      the action.
     */
    Action getSendToHandAction(Collection<BasicSBE> elements);

    //GET SEND TO BOARD ACTION
    /**
     * Get a send to board action. Sends the provided element to the board it belongs to.
     * @param element
     *      an element.
     * @return
     *      the action.
     */
    default Action getSendToBoardAction(BasicSBE element) {
        return getSendToBoardAction(Arrays.asList(element));
    }

    /**
     * Get a send to board action. Sends the provided elements to the board it belongs to.
     * @param elements
     *      a collection of elements.
     * @return
     *      the action.
     */
    Action getSendToBoardAction(Collection<BasicSBE> elements);

}