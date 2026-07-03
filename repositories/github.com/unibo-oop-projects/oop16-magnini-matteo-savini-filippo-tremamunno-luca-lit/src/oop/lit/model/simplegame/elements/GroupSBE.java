package oop.lit.model.simplegame.elements;

import oop.lit.model.GameElementModel;
import oop.lit.model.PlayerModel;
import oop.lit.model.SelectableElementGroupModel;

/**
 * A BoardElement holding BasicSBE.
 * This element provides Actions to get, see and use the contained elements.
 */
public interface GroupSBE extends SimpleBoardElement {

    /**
     * Randomly permutes contained elements.
     */
    void shuffle();

    /**
     * @return the number of contained elements.
     */
    int getContainedElementsSize();

    /**
     * Removes the element in the provided position from this group and sends it to his board.
     *
     * @param position
     *      a value representing the position of an element.
     * @throws IllegalArgumentException
     *      if the position provided does not match any element.
     */
    void draw(int position);

    /**
     * Adds the provided element to the contained elements.
     * @param element
     *      an element.
     * @param position
     *      the position this element is to be added in.
     * @return
     *      if the element was added (it was not contained in this).
     * @throws IllegalArgumentException
     *      if the provided position is not valid.
     */
    boolean addElement(BasicSBE element, int position);

    /**
     * Removes the provided element from the contained elements.
     * @param element
     *      an element.
     * @return
     *      if the element was removed (it was contained in this).
     */
    boolean removeElement(GameElementModel element);

    /**
     * @param player
     *      a player.
     * @param isPlayerTurn
     *      if it is the provided player turn.
     * @return
     *      if the provided player can add elements now.
     */
    boolean canPlayerAdd(PlayerModel player, boolean isPlayerTurn);

    /**
     * @return
     *      the contained group as a SelectableElementGroupModel.
     */
    SelectableElementGroupModel getSelectableElementGroupModel();
}