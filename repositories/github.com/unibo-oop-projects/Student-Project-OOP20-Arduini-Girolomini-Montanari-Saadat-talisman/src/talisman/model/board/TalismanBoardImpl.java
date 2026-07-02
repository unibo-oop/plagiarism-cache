package talisman.model.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements a board for talisman.
 * 
 * @author Alberto Arduini
 */
public class TalismanBoardImpl implements TalismanBoard {
    private static final long serialVersionUID = 2554535334083673195L;

    private final List<TalismanBoardSection> sections;
    private transient List<TalismanBoardPawn> characterPawns;

    /**
     * Creates a new talisman board.
     * 
     * @param sections       the sections that the board contains
     * @param characterPawns the starting player pawns
     */
    public TalismanBoardImpl(final List<TalismanBoardSection> sections, final List<TalismanBoardPawn> characterPawns) {
        super();
        this.sections = List.copyOf(sections);
        this.characterPawns = new ArrayList<>(characterPawns);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSectionCount() {
        return this.sections.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TalismanBoardSection getSection(final int index) {
        return this.sections.get(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void movePawnTo(final int playerIndex, final int cell) {
        final int oldSection = this.getPawn(playerIndex).getPositionSection();
        this.getPawn(playerIndex).setPosition(oldSection, cell);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changePawnSection(final int playerIndex, final int section, final int cell) {
        this.getPawn(playerIndex).setPosition(section, cell);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPawnCount() {
        return this.characterPawns.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TalismanBoardPawn getPawn(final int playerIndex) {
        return this.characterPawns.get(playerIndex);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPawn(final int playerIndex, final TalismanBoardPawn pawn) {
        if (this.characterPawns.size() == playerIndex) {
            this.characterPawns.add(pawn);
        } else {
            this.characterPawns.set(playerIndex, pawn);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removePawn(final int playerIndex) {
        this.characterPawns.set(playerIndex, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPawnSectionIndex(final int playerIndex) {
        return this.getPawn(playerIndex).getPositionSection();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPawnCellIndex(final int playerIndex) {
        return this.getPawn(playerIndex).getPositionCell();
    }

    private void readObject(final java.io.ObjectInputStream in)
        throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.characterPawns = new ArrayList<>();
    }
}
