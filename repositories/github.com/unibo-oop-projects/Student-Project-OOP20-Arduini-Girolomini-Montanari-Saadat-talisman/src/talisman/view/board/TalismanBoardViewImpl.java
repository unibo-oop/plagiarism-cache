package talisman.view.board;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.SwingUtilities;

import talisman.Controllers;

import talisman.model.board.TalismanBoardPawn;

import talisman.util.Pair;

import talisman.view.cards.TalismanCardView;

public class TalismanBoardViewImpl extends PopulatedBoardViewImpl implements TalismanBoardView {
    private static final long serialVersionUID = 1L;
    private final Map<Pair<Integer, Integer>, TalismanBoardCardView> cards;
    // I need to save these, in order to properly remove them from the cells when
    // the card gets removed from the board
    private final Map<TalismanBoardCardView, MouseListener> cellCardMouseListeners;

    private boolean hideCardOnLeave = true;
    private TalismanBoardCardView shownCard;
    private CardPickupListener listener;

    /**
     * Creates a new talisman board view.
     * 
     * @param sections    the board sections
     * @param mainSection the main (outed) board section index in the list
     * @param pawns       the list of pawns
     */
    public TalismanBoardViewImpl(final List<BoardSectionView> sections, final int mainSection,
            final List<PawnView> pawns) {
        super(sections, mainSection, pawns);
        this.cards = new HashMap<>();
        for (int i = 0; i < this.getSectionCount(); i++) {
            final BoardSectionView section = this.getSection(i);
            for (int j = 0; j < section.getCellCount(); j++) {
                final BoardCellViewImpl cell = (BoardCellViewImpl) section.getCell(j);
                cell.addMouseListener(new MouseAdapter() {

                    @Override
                    public void mouseEntered(final MouseEvent e) {
                        cell.setTextVisiblity(true);
                    }

                    @Override
                    public void mouseExited(final MouseEvent e) {
                        cell.setTextVisiblity(false);
                    }
                });
            }
        }
        this.cellCardMouseListeners = new HashMap<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addOverlayedCard(final int section, final int cell, final TalismanCardView card,
            final boolean canBePickedUp) {
        if (this.cards.values().stream().anyMatch(c -> c.getCard() == card)) {
            return;
        }
        if (this.cards.containsKey(new Pair<>(section, cell))) {
            return;
        }

        final TalismanBoardCardView boardCard = new TalismanBoardCardView(card, canBePickedUp);
        this.cards.put(new Pair<>(section, cell), boardCard);
        final BoardCellView cellInstance = this.getSection(section).getCell(cell);
        this.add(boardCard, 0);

        final MouseListener mouseListener = new MouseAdapter() {
            @Override
            public void mouseEntered(final MouseEvent e) {
                if (TalismanBoardViewImpl.this.shownCard == null
                        && TalismanBoardViewImpl.this.canShowCard(section, cell)) {
                    TalismanBoardViewImpl.this.shownCard = boardCard;
                    boardCard.setVisible(true);
                }
            }

            @Override
            public void mouseExited(final MouseEvent e) {
                if (TalismanBoardViewImpl.this.hideCardOnLeave) {
                    TalismanBoardViewImpl.this.shownCard = null;
                    boardCard.setVisible(false);
                }
            }

            @Override
            public void mouseClicked(final MouseEvent e) {
                if (TalismanBoardViewImpl.this.shownCard != null) {
                    TalismanBoardViewImpl.this.hideCardOnLeave = false;
                }
            }
        };

        this.cellCardMouseListeners.put(boardCard, mouseListener);

        ((BoardCellViewImpl) cellInstance).addMouseListener(mouseListener);

        boardCard.setInteractListener((c) -> {
            this.hideCardOnLeave = true;
            this.shownCard = null;
            this.pickupCard(c.getCard());
        });
        boardCard.setHiddenListener((c) -> {
            this.hideCardOnLeave = true;
            this.shownCard = null;
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeOverlayedCard(final TalismanCardView card) {
        for (final Map.Entry<Pair<Integer, Integer>, TalismanBoardCardView> entry : this.cards.entrySet()) {
            if (entry.getValue().getCard() == card) {
                this.removeOverlayedCard(entry.getKey().getX(), entry.getKey().getY());
                break;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeOverlayedCard(final int section, final int cell) {
        SwingUtilities.invokeLater(() -> {
            final Pair<Integer, Integer> position = new Pair<>(section, cell);
            if (!this.cards.containsKey(position)) {
                return;
            }
            final TalismanBoardCardView boardCard = this.cards.get(position);
            if (this.shownCard == boardCard) {
                this.shownCard = null;
                boardCard.setVisible(false);
            }
            this.hideCardOnLeave = true;
            ((BoardCellViewImpl) this.getSection(section).getCell(cell))
                    .removeMouseListener(this.cellCardMouseListeners.get(boardCard));
            this.cellCardMouseListeners.remove(boardCard);
            this.cards.remove(position);
            this.remove(boardCard);
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCardPickupListener(final CardPickupListener listener) {
        this.listener = listener;
    }

    private boolean canShowCard(final int section, final int cell) {
        final int playerIndex = Controllers.getCharactersController().getCurrentPlayer().getIndex();
        final TalismanBoardPawn pawn = Controllers.getBoardController().getCharacterPawn(playerIndex);
        return pawn.getPositionSection() == section && pawn.getPositionCell() == cell;
    }

    private void pickupCard(final TalismanCardView card) {
        if (this.listener != null) {
            this.listener.pickupCard(card);
        }
    }
}
