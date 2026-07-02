package talisman.test.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import talisman.Controllers;

import talisman.controller.board.TalismanBoardController;
import talisman.controller.character.CharacterControllerImpl;
import talisman.controller.character.CharactersController;

import talisman.model.board.TalismanBoard;
import talisman.model.board.TalismanBoardCell;
import talisman.model.board.TalismanBoardPawn;
import talisman.model.board.TalismanBoardSection;
import talisman.model.board.TalismanCellType;
import talisman.model.character.CharacterModelImpl;
import talisman.model.character.defaultCharacters.TalismanCharacterFactory;
import talisman.util.CellType;

import talisman.view.board.TalismanBoardViewBuilder;

public final class BoardTestUtils {
    private BoardTestUtils() {
    }

    /**
     * Creates a test board.
     * 
     * @param sectionsCount the number of sections
     * @param cellsCount    the number of cells of every section
     * @param pawnsCount    the number of pawns
     * @return the created board
     */
    public static TalismanBoard createBoard(final int sectionsCount, final int cellsCount, final int pawnsCount) {
        final List<TalismanBoardSection> sections = new ArrayList<>();
        for (int i = 0; i < sectionsCount; i++) {
            final List<TalismanBoardCell> cells = new ArrayList<>();
            for (int j = 0; j < cellsCount; j++) {
                cells.add(
                        TalismanBoardCell.createCell("", "Cell " + j, CellType.DOWN, TalismanCellType.BIOME, Set.of()));
            }
            sections.add(TalismanBoardSection.createSection(cells));
        }
        final List<TalismanBoardPawn> pawns = new ArrayList<>();
        for (int i = 0; i < pawnsCount; i++) {
            pawns.add(TalismanBoardPawn.createPawn("", i));
        }
        return TalismanBoard.createBoard(sections, pawns);
    }

    /**
     * Creates a test controller with a test board.
     * 
     * @param sectionsCount the number of sections
     * @param cellsCount    the number of cells of every section
     * @param pawnsCount    the number of pawns
     * @return the created controller
     */
    public static TalismanBoardController createController(final int sectionsCount, final int cellsCount,
            final int pawnsCount) {
        final TalismanBoard board = BoardTestUtils.createBoard(sectionsCount, cellsCount, pawnsCount);
        // I don't care about testing the view, so I create an basic, empty one
        final TalismanBoardViewBuilder viewBuilder = new TalismanBoardViewBuilder();
        return TalismanBoardController.create(board, viewBuilder.buildFromModel(board));
    }

    /**
     * Sets up all the controllers for complex testing.
     * 
     * @param sectionsCount the number of sections
     * @param cellsCount    the number of cells of every section
     * @param pawnsCount    the number of pawns
     * @return the created controller for the board
     */
    public static TalismanBoardController setupControllers(final int sectionsCount, final int cellsCount,
            final int pawnsCount) {
        Controllers.reset();
        final CharactersController characterController = new CharacterControllerImpl();
        Controllers.setCharactersController(characterController);
        for (int i = 0; i < pawnsCount; i++) {
            Controllers.getCharactersController().addPlayer(TalismanCharacterFactory.createAssassinCharacter());
        }
        Controllers.setBoardController(BoardTestUtils.createController(sectionsCount, cellsCount, pawnsCount));
        return Controllers.getBoardController();
    }
}
