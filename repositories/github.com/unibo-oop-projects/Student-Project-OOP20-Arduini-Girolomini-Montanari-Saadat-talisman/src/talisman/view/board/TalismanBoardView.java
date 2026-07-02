package talisman.view.board;

import java.util.EventListener;
import java.util.List;

import talisman.view.cards.TalismanCardView;

public interface TalismanBoardView extends PopulatedBoardView {
    interface CardPickupListener extends EventListener {
        void pickupCard(TalismanCardView card);
    }

    /**
     * Adds a card on top of the specified cell. If the card is already present on
     * the board it does nothing.
     * 
     * @param section       the section of the cell
     * @param cell          the cell
     * @param card          the card view to add
     * @param canBePickedUp if the card is something the player can pick up
     */
    void addOverlayedCard(int section, int cell, TalismanCardView card, boolean canBePickedUp);

    /**
     * Removes a card from the board. If the card is not present it does nothing.
     *
     * @param card the card to remove
     */
    void removeOverlayedCard(TalismanCardView card);

    /**
     * Removes the card on the specified cell. If the cell doesn't have a card it
     * does nothing.
     * 
     * @param section the cell's section
     * @param cell    the cell
     */
    void removeOverlayedCard(int section, int cell);

    /**
     * Sets the listener to call when a card is picked up.
     * 
     * @param listener
     */
    void setCardPickupListener(CardPickupListener listener);

    /**
     * Constructs a new talisman board.
     * 
     * @param sections    the board sections
     * @param mainSection the board main section index
     * @param pawns       the pawns
     * @return the created board
     */
    static TalismanBoardView create(List<BoardSectionView> sections, int mainSection, List<PawnView> pawns) {
        return new TalismanBoardViewImpl(sections, mainSection, pawns);
    }
}
