package talisman.controller.board;

import talisman.model.board.PopulatedBoard;
import talisman.view.board.PopulatedBoardView;
import talisman.model.board.BoardSection;

import java.util.Set;

import talisman.model.board.BoardCell;
import talisman.model.board.BoardPawn;

/**
 * A MVC controller for a populated board model.
 * 
 * @author Alberto Arduini
 *
 * @param <B> The board type
 * @param <S> The section type
 * @param <C> The cell type
 * @param <P> The pawn type
 */
public interface PopulatedBoardController<B extends PopulatedBoard<S, C, P>, S extends BoardSection<C>, C extends BoardCell, P extends BoardPawn>
        extends BoardController<B> {

    /**
     * {@inheritDoc}
     */
    @Override
    PopulatedBoardView getView();

    /**
     * Moves the character of the specified player in the specified cell.
     * 
     * @param player the player's index
     * @param cell   the destination cell
     */
    void moveCharacterCell(int player, int cell);

    /**
     * Moves a character in the first cell of another section.
     * 
     * @param player  the player's index
     * @param section the destination section
     */
    default void moveCharacterSection(int player, int section) {
        this.moveCharacterSection(player, section, 0);
    }

    /**
     * Moves a character in the specified cell and section.
     * 
     * @param player  the player's index
     * @param section the destination section
     * @param cell    the destination cell
     */
    void moveCharacterSection(int player, int section, int cell);

    /**
     * Gets the section on which the player is.
     * 
     * @param player the player's index
     * @return the section
     */
    S getCharacterSection(int player);

    /**
     * Gets the cell on which the player is.
     * 
     * @param player the player's index
     * @return the cell
     */
    C getCharacterCell(int player);

    /**
     * Gets the pawn of the character of the specified player.
     * 
     * @param player the player's index
     * @return the pawn
     */
    P getCharacterPawn(int player);

    /**
     * Gets the characters that are on the specified cell.
     * 
     * @param section the cell's section
     * @param cell    the cell
     * @return a set of characters
     */
    Set<P> getCharactersInCell(int section, int cell);
}
