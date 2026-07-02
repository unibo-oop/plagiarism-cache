package talisman.model.board;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import talisman.model.action.TalismanAction;
import talisman.model.action.TalismanActionChoiceAction;
import talisman.model.action.TalismanActionStatistic;
import talisman.model.action.TalismanDrawCardAction;
import talisman.model.action.TalismanEmptyAction;
import talisman.model.action.TalismanFightAction;
import talisman.model.action.TalismanGiveItemAction;
import talisman.model.action.TalismanModifyStatisticAction;
import talisman.model.action.TalismanMoveAction;
import talisman.model.action.TalismanPayAction;
import talisman.model.action.TalismanQuestChoiceAction;
import talisman.model.action.TalismanRequireTalismanAction;
import talisman.model.action.TalismanRollAction;
import talisman.model.action.TalismanRollActionSection;
import talisman.model.action.TalismanSkipTurnAction;
import talisman.model.cards.DeckType;
import talisman.model.quests.DeliverObject;
import talisman.model.quests.KillEnemy;
import talisman.model.quests.QuestObjectType;
import talisman.model.quests.TakePlayerLife;
import talisman.util.CellType;
import talisman.util.DiceType;
import talisman.util.PathUtils;

/**
 * Static class used to abstract the creation of the default game board.
 * 
 * @author Alberto Arduini
 *
 */
public final class TalismanBoardFactory {
    private TalismanBoardFactory() {
    }

    /**
     * Creates a default board model with the starting player pawns, saving it to
     * the file for reuse or loading it if already present.
     * 
     * @param startingPawns the pawns that the players start with
     * @return the create board model
     */
    public static TalismanBoard createDefaultBoardModel(final List<TalismanBoardPawn> startingPawns) {
        // DISABLED SINCE THERE WAS NO TIME TO TEST IT IN BUILD
        // First i try to load from file
        /*
         * final Optional<TalismanBoard> loadedBoard =
         * TalismanBoardSerializer.loadBoard(); if (loadedBoard.isPresent()) { for (int
         * i = 0; i < startingPawns.size(); i++) { loadedBoard.get().addPawn(i,
         * startingPawns.get(i)); } return loadedBoard.get(); }
         */
        // If the loading fails (file not found, classes changed, etc.) then i create it
        // from scratch
        // All section are constructed in order top -> right -> bottom -> left, and
        // indexes follow this order, starting from the top-left corner.
        final List<TalismanBoardSection> sections = List.of(TalismanBoardFactory.createOutsideSection(),
                TalismanBoardFactory.createMiddleSection(), TalismanBoardFactory.createInnerSection(),
                TalismanBoardFactory.createCrownSection());
        final TalismanBoard createdBoard = TalismanBoard.createBoard(sections, startingPawns);
        // DISABLED SINCE THERE WAS NO TIME TO TEST IT IN BUILD
        // After creation, before returning, i save it for future use
        // TalismanBoardSerializer.saveBoard(createdBoard);
        return createdBoard;
    }

    private static TalismanBoardSection createOutsideSection() {
        return TalismanBoardSection.createSection(List.of(
                // Top row (left -> right)
                TalismanBoardFactory.createCell("Village", "Village", CellType.UP, TalismanCellType.ZONE,
                        new TalismanActionChoiceAction(List.of(new TalismanEmptyAction(),
                                new TalismanPayAction(3, new TalismanGiveItemAction(DeckType.SHOP)),
                                new TalismanPayAction(1,
                                        new TalismanModifyStatisticAction(1, TalismanActionStatistic.HEALTH)),
                                new TalismanRollAction(4, TalismanActionStatistic.FAITH,
                                        new TalismanModifyStatisticAction(1, TalismanActionStatistic.CRAFT),
                                        new TalismanEmptyAction())))),
                TalismanBoardFactory.createCell("FIelds_Top", "Fields", CellType.UP, TalismanCellType.BIOME,
                        new TalismanDrawCardAction(DeckType.ADVENTURE)),
                TalismanBoardFactory.createCell("Graveyard", "Graveyard", CellType.UP, TalismanCellType.ZONE,
                        new TalismanRollAction(4, TalismanActionStatistic.NONE,
                                new TalismanModifyStatisticAction(1, TalismanActionStatistic.HEALTH),
                                new TalismanSkipTurnAction())),
                TalismanBoardFactory.createCell("Woods_Top", "Woods", CellType.UP, TalismanCellType.BIOME,
                        new TalismanDrawCardAction(DeckType.ADVENTURE)),
                TalismanBoardFactory.createCell("Sentinel", "Sentinel", CellType.UP, TalismanCellType.MONSTER,
                        new TalismanRollAction(9, TalismanActionStatistic.STRENGTH, new TalismanMoveAction(1, 3),
                                new TalismanModifyStatisticAction(-1, TalismanActionStatistic.HEALTH))),
                TalismanBoardFactory.createCell("Hills_Top", "Hills", CellType.UP, TalismanCellType.BIOME,
                        new TalismanDrawCardAction(DeckType.ADVENTURE)),
                TalismanBoardFactory.createCell("Chapel", "Chapel", CellType.UP, TalismanCellType.ZONE,
                        new TalismanRollAction(4, TalismanActionStatistic.FAITH,
                                new TalismanModifyStatisticAction(1, TalismanActionStatistic.HEALTH),
                                new TalismanModifyStatisticAction(-1, TalismanActionStatistic.HEALTH))),
                // Right column (top -> bottom)
                TalismanBoardFactory.createCell("FIelds_Right", "Fields", CellType.RIGHT, TalismanCellType.BIOME,
                        new TalismanDrawCardAction(DeckType.ADVENTURE)),
                TalismanBoardFactory.createCell("Crags", "Crags", CellType.RIGHT, TalismanCellType.ZONE,
                        new TalismanRollAction(TalismanActionStatistic.NONE,
                                // This index should be spirit
                                List.of(new TalismanRollActionSection(7, new TalismanFightAction(1)),
                                        new TalismanRollActionSection(2, new TalismanSkipTurnAction()),
                                        new TalismanRollActionSection(4, new TalismanEmptyAction()),
                                        new TalismanRollActionSection(6,
                                                new TalismanModifyStatisticAction(1,
                                                        TalismanActionStatistic.STRENGTH))))),
                TalismanBoardFactory.createCell("Plains_Right", "Plains", CellType.RIGHT, TalismanCellType.BIOME,
                        new TalismanDrawCardAction(DeckType.ADVENTURE)),
                TalismanBoardFactory.createCell("Woods_Right", "Woods", CellType.RIGHT, TalismanCellType.BIOME,
                        new TalismanDrawCardAction(DeckType.ADVENTURE)),
                TalismanBoardFactory.createCell("FIelds_Right", "Fields", CellType.RIGHT, TalismanCellType.BIOME,
                        new TalismanDrawCardAction(DeckType.ADVENTURE)),
                // Bottom row (left <- right)
                TalismanBoardFactory.createCell("City", "City", CellType.DOWN, TalismanCellType.ZONE,
                        new TalismanActionChoiceAction(List.of(new TalismanEmptyAction(),
                                new TalismanRollAction(TalismanActionStatistic.NONE, List.of(
                                        new TalismanRollActionSection(0,
                                                new TalismanModifyStatisticAction(-1,
                                                        TalismanActionStatistic.STRENGTH)),
                                        new TalismanRollActionSection(3,
                                                new TalismanModifyStatisticAction(-1, TalismanActionStatistic.CRAFT)),
                                        new TalismanRollActionSection(5,
                                                new TalismanModifyStatisticAction(1, TalismanActionStatistic.STRENGTH)),
                                        new TalismanRollActionSection(6,
                                                new TalismanModifyStatisticAction(1, TalismanActionStatistic.CRAFT)))),
                                new TalismanPayAction(1,
                                        new TalismanModifyStatisticAction(2, TalismanActionStatistic.HEALTH))))),
                TalismanBoardFactory.createCell("FIelds_Bottom", "Fields", CellType.DOWN, TalismanCellType.BIOME,
                        new TalismanDrawCardAction(DeckType.ADVENTURE)),
                TalismanBoardFactory.createCell("Hills_Bottom", "Hills", CellType.DOWN, TalismanCellType.BIOME,
                        new TalismanDrawCardAction(DeckType.ADVENTURE)),
                TalismanBoardFactory.createCell("FIelds_Bottom", "Plains", CellType.DOWN, TalismanCellType.BIOME,
                        new TalismanDrawCardAction(DeckType.ADVENTURE)),
                TalismanBoardFactory.createCell("Woods_Bottom", "Woods", CellType.DOWN, TalismanCellType.BIOME,
                        new TalismanDrawCardAction(DeckType.ADVENTURE)),
                TalismanBoardFactory.createCell("Plains_Bottom", "Plains", CellType.DOWN, TalismanCellType.BIOME,
                        new TalismanDrawCardAction(DeckType.ADVENTURE)),
                TalismanBoardFactory.createCell("Tavern", "Tavern", CellType.DOWN, TalismanCellType.ZONE,
                        new TalismanRollAction(TalismanActionStatistic.NONE,
                                List.of(new TalismanRollActionSection(1, new TalismanSkipTurnAction()),
                                        // index should be the farmer's one
                                        new TalismanRollActionSection(1, new TalismanFightAction(2)),
                                        new TalismanRollActionSection(3,
                                                new TalismanModifyStatisticAction(-1, TalismanActionStatistic.GOLD)),
                                        new TalismanRollActionSection(4,
                                                new TalismanModifyStatisticAction(1, TalismanActionStatistic.GOLD)),
                                        new TalismanRollActionSection(5, new TalismanEmptyAction()),
                                        new TalismanRollActionSection(6, new TalismanMoveAction(1, 12))))),
                // Left column (bottom -> up)
                TalismanBoardFactory.createCell("FIelds_Left", "Fields", CellType.LEFT, TalismanCellType.BIOME,
                        new TalismanDrawCardAction(DeckType.ADVENTURE)),
                TalismanBoardFactory.createCell("Woods_Left", "Forest", CellType.LEFT, TalismanCellType.BIOME,
                        new TalismanRollAction(TalismanActionStatistic.NONE,
                                // index should be the brigand's one
                                List.of(new TalismanRollActionSection(2, new TalismanFightAction(3)),
                                        new TalismanRollActionSection(2, new TalismanSkipTurnAction()),
                                        new TalismanRollActionSection(4, new TalismanEmptyAction()),
                                        new TalismanRollActionSection(6,
                                                new TalismanModifyStatisticAction(1,
                                                        TalismanActionStatistic.STRENGTH))))),
                TalismanBoardFactory.createCell("Plains_Left", "Plains", CellType.LEFT, TalismanCellType.BIOME,
                        new TalismanDrawCardAction(DeckType.ADVENTURE)),
                TalismanBoardFactory.createCell("Ruins", "Ruins", CellType.LEFT, TalismanCellType.ZONE,
                        new TalismanDrawCardAction(DeckType.ADVENTURE)),
                TalismanBoardFactory.createCell("FIelds_Left", "Fields", CellType.LEFT, TalismanCellType.BIOME,
                        new TalismanDrawCardAction(DeckType.ADVENTURE))));
    }

    private static TalismanBoardSection createMiddleSection() {
        return TalismanBoardSection.createSection(List.of(
                // Top row (left -> right)
                TalismanBoardFactory.createCell("PortalOfPower", "Portal Of Power", CellType.UP, TalismanCellType.ZONE,
                        new TalismanActionChoiceAction(List.of(
                                new TalismanRollAction(5, TalismanActionStatistic.CRAFT, new TalismanMoveAction(2, 0),
                                        new TalismanModifyStatisticAction(-1, TalismanActionStatistic.HEALTH)),
                                new TalismanRollAction(5, TalismanActionStatistic.STRENGTH,
                                        new TalismanMoveAction(2, 0),
                                        new TalismanModifyStatisticAction(-1, TalismanActionStatistic.HEALTH))))),
                TalismanBoardFactory.createCell("BlackNight", "Black Night", CellType.UP, TalismanCellType.MONSTER,
                        new TalismanActionChoiceAction(
                                List.of(new TalismanModifyStatisticAction(-1, TalismanActionStatistic.GOLD),
                                        new TalismanModifyStatisticAction(-1, TalismanActionStatistic.HEALTH)))),
                TalismanBoardFactory.createCell("HiddenValley", "Hidden Valley", CellType.UP, TalismanCellType.ZONE,
                        new TalismanDrawCardAction(DeckType.ADVENTURE)),
                TalismanBoardFactory.createCell("Hills_Top", "Hills", CellType.UP, TalismanCellType.BIOME,
                        new TalismanDrawCardAction(DeckType.ADVENTURE)),
                TalismanBoardFactory.createCell("CursedGlade", "Cursed Glade", CellType.UP, TalismanCellType.ZONE,
                        new TalismanDrawCardAction(DeckType.ADVENTURE)),
                // Right column (top -> bottom)
                TalismanBoardFactory.createCell("Runes_Right", "Runes", CellType.RIGHT, TalismanCellType.BIOME,
                        new TalismanDrawCardAction(DeckType.ADVENTURE)),
                TalismanBoardFactory.createCell("Chasm", "Chasm", CellType.RIGHT, TalismanCellType.ZONE,
                        new TalismanRollAction(3, TalismanActionStatistic.NONE, new TalismanEmptyAction(),
                                new TalismanModifyStatisticAction(-1, TalismanActionStatistic.HEALTH))),
                TalismanBoardFactory.createCell("Runes_Right", "Runes", CellType.RIGHT, TalismanCellType.BIOME,
                        new TalismanDrawCardAction(DeckType.ADVENTURE)),
                // Bottom row (left <- right)
                TalismanBoardFactory.createCell("WarlocksCave", "Warlock's Cave", CellType.DOWN, TalismanCellType.ZONE,
                        new TalismanQuestChoiceAction(List.of(new TakePlayerLife(), new KillEnemy(),
                                new DeliverObject(QuestObjectType.FOLLOWER),
                                new DeliverObject(QuestObjectType.MAGIC_OBJECT),
                                new DeliverObject(QuestObjectType.THREE_COINS),
                                new DeliverObject(QuestObjectType.TWO_COINS)))),
                TalismanBoardFactory.createCell("Desert_Bottom", "Desert", CellType.DOWN, TalismanCellType.BIOME,
                        Set.of(new TalismanModifyStatisticAction(-1, TalismanActionStatistic.HEALTH),
                                new TalismanDrawCardAction(DeckType.ADVENTURE))),
                TalismanBoardFactory.createCell("Oasis", "Oasis", CellType.DOWN, TalismanCellType.ZONE,
                        new TalismanDrawCardAction(DeckType.ADVENTURE)),
                TalismanBoardFactory.createCell("Desert_Bottom", "Desert", CellType.DOWN, TalismanCellType.BIOME,
                        Set.of(new TalismanModifyStatisticAction(-1, TalismanActionStatistic.HEALTH),
                                new TalismanDrawCardAction(DeckType.ADVENTURE))),
                TalismanBoardFactory.createCell("Temple", "Temple", CellType.DOWN, TalismanCellType.ZONE,
                        new TalismanRollAction(DiceType.DOUBLE_SIX, TalismanActionStatistic.NONE, List.of(
                                new TalismanRollActionSection(3,
                                        new TalismanModifyStatisticAction(-1, TalismanActionStatistic.HEALTH)),
                                new TalismanRollActionSection(5, new TalismanSkipTurnAction()),
                                new TalismanRollActionSection(6,
                                        new TalismanModifyStatisticAction(1, TalismanActionStatistic.CRAFT)),
                                new TalismanRollActionSection(8,
                                        new TalismanModifyStatisticAction(1, TalismanActionStatistic.STRENGTH)),
                                new TalismanRollActionSection(10, new TalismanDrawCardAction(DeckType.TALISMAN)),
                                new TalismanRollActionSection(11,
                                        new TalismanModifyStatisticAction(1, TalismanActionStatistic.HEALTH))))),
                // Left column (bottom -> up)
                TalismanBoardFactory.createCell("Woods_Left", "Forest", CellType.LEFT, TalismanCellType.BIOME,
                        new TalismanDrawCardAction(DeckType.ADVENTURE)),
                TalismanBoardFactory.createCell(
                        "Runes_Left", "Runes", CellType.LEFT, TalismanCellType.BIOME, new TalismanDrawCardAction(
                                DeckType.ADVENTURE)),
                TalismanBoardFactory.createCell("Castle", "Castle", CellType.LEFT, TalismanCellType.ZONE,
                        new TalismanActionChoiceAction(List.of(
                                new TalismanPayAction(1,
                                        new TalismanModifyStatisticAction(1, TalismanActionStatistic.HEALTH)),
                                new TalismanEmptyAction())))));
    }

    private static TalismanBoardSection createInnerSection() {
        return TalismanBoardSection.createSection(List.of(
                // Top row (left -> right)
                TalismanBoardFactory.createCell("PlainOfPeril", "Plains of Peril", CellType.UP, TalismanCellType.ZONE,
                        new TalismanSkipTurnAction()),
                TalismanBoardFactory.createCell("Mines", "Mines", CellType.UP, TalismanCellType.ZONE,
                        new TalismanRollAction(5, TalismanActionStatistic.CRAFT, new TalismanEmptyAction(),
                                new TalismanMoveAction(0, 19))),
                TalismanBoardFactory.createCell("VampiresTower", "Vampire's Tower", CellType.UP, TalismanCellType.ZONE,
                        new TalismanRollAction(4, TalismanActionStatistic.NONE,
                                new TalismanModifyStatisticAction(-1, TalismanActionStatistic.HEALTH),
                                new TalismanModifyStatisticAction(-3, TalismanActionStatistic.HEALTH))),
                // Right column
                TalismanBoardFactory.createCell("Pits", "Pits", CellType.RIGHT, TalismanCellType.MONSTER,
                        new TalismanFightAction(0)),
                // Bottom row (left <- right)
                TalismanBoardFactory.createCell("ValleyOfFire", "Valley of Fire", CellType.DOWN, TalismanCellType.ZONE,
                        new TalismanRequireTalismanAction(new TalismanMoveAction(3, 0), new TalismanEmptyAction())),
                TalismanBoardFactory.createCell("WerewolfDen", "Werewolf Den", CellType.DOWN, TalismanCellType.MONSTER,
                        new TalismanRollAction(6, TalismanActionStatistic.NONE, new TalismanEmptyAction(),
                                new TalismanModifyStatisticAction(-1, TalismanActionStatistic.HEALTH))),
                TalismanBoardFactory.createCell("DiceWithDeath", "Dice with Death", CellType.DOWN,
                        TalismanCellType.MONSTER,
                        new TalismanRollAction(5, TalismanActionStatistic.NONE, new TalismanEmptyAction(),
                                new TalismanModifyStatisticAction(-1, TalismanActionStatistic.HEALTH))),
                // Left column
                TalismanBoardFactory.createCell("Crypt", "Crypt", CellType.LEFT, TalismanCellType.ZONE,
                        new TalismanActionChoiceAction(List.of(new TalismanEmptyAction(),
                                new TalismanPayAction(2, new TalismanMoveAction(1, 8)),
                                new TalismanPayAction(3, new TalismanMoveAction(0, 12)))))));
    }

    private static TalismanBoardSection createCrownSection() {
        return TalismanBoardSection.createSection(List
                .of(TalismanBoardFactory.createCell("Crown", "Crown", CellType.UP, TalismanCellType.ZONE, Set.of())));
    }

    /**
     * Creates a new cell. Automatically gets the full path to the image.
     * 
     * @param imageName   the name of the image
     * @param text        the text of the cell
     * @param orientation the orientation in the board
     * @param type        the type if ambient
     * @param actions     the cell's actions
     * @return the created cell
     */
    public static TalismanBoardCell createCell(final String imageName, final String text, final CellType orientation,
            final TalismanCellType type, final Collection<TalismanAction> actions) {
        return TalismanBoardCell.createCell(PathUtils.getPathToCell(type, imageName), text, orientation, type,
                actions);
    }

    /**
     * Creates a new cell. Automatically gets the full path to the image.
     * 
     * @param imageName   the name of the image
     * @param text        the text of the cell
     * @param orientation the orientation in the board
     * @param type        the type if ambient
     * @param action      the cell's action
     * 
     * @return the created cell
     */
    public static TalismanBoardCell createCell(final String imageName, final String text, final CellType orientation,
            final TalismanCellType type, final TalismanAction action) {
        return TalismanBoardCell.createCell(PathUtils.getPathToCell(type, imageName), text, orientation, type,
                action);
    }
}
