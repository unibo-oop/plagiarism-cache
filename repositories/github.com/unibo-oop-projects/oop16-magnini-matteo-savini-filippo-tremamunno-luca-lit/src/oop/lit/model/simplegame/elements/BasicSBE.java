package oop.lit.model.simplegame.elements;

import java.util.Set;

import oop.lit.model.simplegame.SimplePlayerHand;

/**
 * A basic board element for a simple game.
 * This type of element provides basic actions to move it to different types of group.
 */
public interface BasicSBE extends SimpleBoardElement {

    /**
     * Moves this element from the board/group/hand it is in, to the provided group.
     * @param group
     *      a group.
     * @param position
     *      the position this element is to be added in.
     *
     * @return
     *      if the element is moved to the group (it was not already in there).
     */
    boolean sendToGroup(GroupSBE group, int position);

    /**
     * @return
     *      (some of) the possible GroupSBE this element can be sent to
     */
    Set<GroupSBE> getPossibleGroups();

    /**
     * Moves this element from the group/hand it is in, to the board.
     *
     * @return
     *      if the element is moved to the board (it was not already in there).
     */
    boolean sendToBoard();

    /**
     * Moves this element from the board/group/hand it is in, to the provided player hand.
     * @param hand
     *      a player hand.
     *
     * @return
     *      if the element is moved to the hand (it was not already in there).
     */
    boolean sendToHand(SimplePlayerHand hand);

    /**
     * @return if this element is on the board.
     */
    boolean isOnBoard();

}