package talisman.test;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import talisman.Controllers;
import talisman.controller.cards.TalismanDeckController;
import talisman.controller.cards.TalismanDeckControllerImpl;
import talisman.model.action.TalismanAction;
import talisman.model.action.TalismanActionStatistic;
import talisman.model.action.TalismanGiveItemAction;
import talisman.model.action.TalismanModifyStatisticAction;
import talisman.model.action.TalismanMoveAction;
import talisman.model.action.TalismanPayAction;
import talisman.model.action.TalismanRequireTalismanAction;
import talisman.model.board.BoardPawn;
import talisman.model.cards.Card;
import talisman.model.cards.CardImpl;
import talisman.model.cards.DeckType;
import talisman.model.cards.TalismanDeckFactory;
import talisman.model.character.CharacterModelImpl;

import talisman.test.util.BoardTestUtils;

public class BoardActionTests {
    private static final int SECTION_COUNT = 3;
    private static final int CELL_COUNT = 3;
    private static final int STARTING_GOLD = 1;

    @Test
    public void testMoveAction() {
        final BoardPawn pawn = BoardTestUtils
                .setupControllers(BoardActionTests.SECTION_COUNT, BoardActionTests.CELL_COUNT, 1).getCharacterPawn(0);
        Controllers.getCharactersController().getCurrentPlayer().getCurrentCharacter()
                .setGold(BoardActionTests.STARTING_GOLD);
        TalismanAction action = new TalismanMoveAction(1, 2);
        // 0,0 -> 1,2
        action.apply();
        Assertions.assertEquals(1, pawn.getPositionSection());
        Assertions.assertEquals(2, pawn.getPositionCell());
        // 1,3 -> 1,1
        action = new TalismanMoveAction(1, 3);
        action.apply();
        Assertions.assertEquals(1, pawn.getPositionSection());
        Assertions.assertEquals(0, pawn.getPositionCell());
    }

    @Test
    public void testPayAction() {
        final BoardPawn pawn = BoardTestUtils
                .setupControllers(BoardActionTests.SECTION_COUNT, BoardActionTests.CELL_COUNT, 1).getCharacterPawn(0);
        Controllers.getCharactersController().getCurrentPlayer().getCurrentCharacter()
                .setGold(BoardActionTests.STARTING_GOLD);
        final TalismanAction action = new TalismanPayAction(1, new TalismanMoveAction(1, 2));
        // gold: 0, should move pawn to 1,3
        action.apply();
        Assertions.assertEquals(BoardActionTests.STARTING_GOLD - 1,
                ((CharacterModelImpl) Controllers.getCharactersController().getCurrentPlayer().getCurrentCharacter())
                        .getGold());
        Assertions.assertEquals(1, pawn.getPositionSection());
        Assertions.assertEquals(2, pawn.getPositionCell());
    }

    @Test
    public void testGiveItemAction() {
        BoardTestUtils.setupControllers(BoardActionTests.SECTION_COUNT, BoardActionTests.CELL_COUNT, 1);
        Controllers.getCharactersController().getCurrentPlayer().getCurrentCharacter()
                .setGold(BoardActionTests.STARTING_GOLD);
        Controllers.setDeckController(DeckType.ADVENTURE, new TalismanDeckControllerImpl(DeckType.ADVENTURE));
        final TalismanAction action = new TalismanGiveItemAction(DeckType.ADVENTURE);
        final int startingSize = Controllers.getCharactersController().getCurrentPlayer().getCurrentCharacter()
                .getInventory().listCards().size();
        action.apply();
        Assertions.assertEquals(startingSize + 1, Controllers.getCharactersController().getCurrentPlayer()
                .getCurrentCharacter().getInventory().listCards().size());
    }

    @Test
    public void testRequireTalismanAction() {
        final BoardPawn pawn = BoardTestUtils
                .setupControllers(BoardActionTests.SECTION_COUNT, BoardActionTests.CELL_COUNT, 1).getCharacterPawn(0);
        Controllers.getCharactersController().getCurrentPlayer().getCurrentCharacter()
                .setGold(BoardActionTests.STARTING_GOLD);
        Controllers.setDeckController(DeckType.TALISMAN, new TalismanDeckControllerImpl(DeckType.TALISMAN));
        final TalismanAction action = new TalismanRequireTalismanAction(new TalismanMoveAction(1, 2),
                new TalismanMoveAction(0, 2));
        // No talisman, should do the second action
        action.apply();
        Assertions.assertEquals(pawn.getPositionSection(), 0);
        Assertions.assertEquals(pawn.getPositionCell(), 2);
        // With talisman, should do first action
        Controllers.getCharactersController().getCurrentPlayer().getCurrentCharacter().getInventory()
                .addCard((CardImpl) Controllers.getDeckController(DeckType.TALISMAN).draw());
        action.apply();
        Assertions.assertEquals(pawn.getPositionSection(), 1);
        Assertions.assertEquals(pawn.getPositionCell(), 2);
    }

    @Test
    public void testModifyStatisticAction() {
        BoardTestUtils.setupControllers(BoardActionTests.SECTION_COUNT, BoardActionTests.CELL_COUNT, 1);
        Controllers.getCharactersController().getCurrentPlayer().getCurrentCharacter()
                .setGold(BoardActionTests.STARTING_GOLD);
        TalismanAction action = new TalismanModifyStatisticAction(1, TalismanActionStatistic.CRAFT);
        int startingCraft = Controllers.getCharactersController().getCurrentPlayer().getCurrentCharacter().getCraft();
        action.apply();
        Assertions.assertEquals(startingCraft + 1,
                Controllers.getCharactersController().getCurrentPlayer().getCurrentCharacter().getCraft());
        action = new TalismanModifyStatisticAction(-2, TalismanActionStatistic.CRAFT);
        startingCraft = Controllers.getCharactersController().getCurrentPlayer().getCurrentCharacter().getCraft();
        action.apply();
        Assertions.assertEquals(startingCraft - 2,
                Controllers.getCharactersController().getCurrentPlayer().getCurrentCharacter().getCraft());
    }
}
