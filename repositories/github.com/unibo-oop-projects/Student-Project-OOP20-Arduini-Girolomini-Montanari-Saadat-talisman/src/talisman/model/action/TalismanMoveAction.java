package talisman.model.action;

import talisman.Controllers;

import talisman.util.Pair;

/**
 * An action for moving the player to another section and cell.
 * 
 * @author Alberto Arduini
 *
 */
public class TalismanMoveAction extends TalismanActionImpl {
    private static final long serialVersionUID = 4969624574046845867L;
    private static final String DESCRIPTION_FORMAT = "Go cell %d in the %s section";
    private static final String[] SECTION_NAMES = { "outside", "middle", "inner", "crown" };

    private final Pair<Integer, Integer> position;

    /**
     * Creates a new move action.
     * 
     * @param section the destination section index
     * @param cell    the destination cell index
     */
    public TalismanMoveAction(final int section, final int cell) {
        this.position = new Pair<>(cell, section);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return String.format(TalismanMoveAction.DESCRIPTION_FORMAT, this.getCell(),
                TalismanMoveAction.SECTION_NAMES[this.getSection()]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void apply() {
        final int playerIndex = Controllers.getCharactersController().getCurrentPlayer().getIndex();
        Controllers.getBoardController().moveCharacterSection(playerIndex, this.getSection(), this.getCell());
        this.actionEnded();
    }

    /**
     * Gets the destination cell.
     * 
     * @return the cell index
     */
    public int getCell() {
        return this.position.getX();
    }

    /**
     * Gets the destination section.
     * 
     * @return the section index
     */
    public int getSection() {
        return this.position.getY();
    }
}
