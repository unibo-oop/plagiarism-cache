package it.unibo.monopoli.model.mainunits;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;

import it.unibo.monopoli.model.actions.ClassicDicesStrategy;
import it.unibo.monopoli.model.actions.EvadeTaxes;
import it.unibo.monopoli.model.actions.GoToPrison;
import it.unibo.monopoli.model.actions.ItalianDicesStrategy;
import it.unibo.monopoli.model.actions.MoveUpTo;
import it.unibo.monopoli.model.actions.ToBePaid;
import it.unibo.monopoli.model.actions.ToBuyProperties;
import it.unibo.monopoli.model.actions.ToDrawCards;
import it.unibo.monopoli.model.actions.ToMortgage;
import it.unibo.monopoli.model.actions.ToPay;
import it.unibo.monopoli.model.actions.ToRevokeMortgage;
import it.unibo.monopoli.model.actions.ToSellProperties;
import it.unibo.monopoli.model.actions.ToStealMoney;
import it.unibo.monopoli.model.cards.Card;
import it.unibo.monopoli.model.cards.Deck;
import it.unibo.monopoli.model.table.Box;
import it.unibo.monopoli.model.table.Home;
import it.unibo.monopoli.model.table.ItalianNeutralArea;
import it.unibo.monopoli.model.table.Land;
import it.unibo.monopoli.model.table.LandContract;
import it.unibo.monopoli.model.table.LandGroup;
import it.unibo.monopoli.model.table.Ownership;
import it.unibo.monopoli.model.table.Start;

/**
 * This is the class officer to the model's test.
 *
 */
public class TestModel {

    private static final int PAWN_ID_AND_N_PLAYER_1 = 0;
    private static final int PAWN_ID_AND_N_PLAYER_2 = 1;
    private static final int PAWN_ID_AND_N_PLAYER_3 = 2;
    private static final int PAWN_ID_AND_N_PLAYER_4 = 3;
    private static final int PAWN_ID_AND_N_PLAYER_5 = 4;
    private static final int PAWN_ID_AND_N_PLAYER_6 = 5;

    private static final int SEVENTH_PLAYER = 6;

    private static final int PLAYERS_5 = 5;
    private static final int MONEYS_FOR_5_PLAYERS = 200;
    private static final int OWNERSHIPS_FOR_5_PLAYERS = 4;

    private static final int N_MAX_OF_OWNERSHIPS = 28;

    private static final int N_ALL_BOXES = 40;

    private static final int N_OF_DICES = 2;
    private static final int N_OF_ITAL_DICES = 3;
    private static final int N_OF_DECKS = 2;
    private static final int N_OF_CARDS_IN_ONE_DECK = 16;

    /**
     * This is the test for model's part of the classic game.
     */
    @Test
    public void testClassic() {
        final List<Player> players = new LinkedList<>();
        GameVersion version;

        //Test the number of players
        players.add(new ClassicPlayer("Margherita", new ClassicPawn(PAWN_ID_AND_N_PLAYER_1), true));
        try {
            version = new GameVersionImpl(new ClassicStrategy(players));
            fail();
        } catch (IllegalArgumentException i) {
        }
        players.add(new ClassicPlayer("Laura", new ClassicPawn(PAWN_ID_AND_N_PLAYER_2), true));
        players.add(new ClassicPlayer("Giuseppe", new ClassicPawn(PAWN_ID_AND_N_PLAYER_3), true));
        players.add(new ClassicPlayer("Computer1", new ClassicPawn(PAWN_ID_AND_N_PLAYER_4), false));
        players.add(new ClassicPlayer("Computer2", new ClassicPawn(PAWN_ID_AND_N_PLAYER_5), false));
        players.add(new ClassicPlayer("Computer3", new ClassicPawn(PAWN_ID_AND_N_PLAYER_6), false));
        players.add(new ClassicPlayer("7th player", new ClassicPawn(SEVENTH_PLAYER), true));
        try {
            version = new GameVersionImpl(new ClassicStrategy(players));
            fail();
        } catch (IllegalArgumentException i) {
        }
        players.remove(SEVENTH_PLAYER);
        players.remove(PAWN_ID_AND_N_PLAYER_6);
        version = new GameVersionImpl(new ClassicStrategy(players));
        assertEquals(players.size(), PLAYERS_5);
        final Bank bank = version.getBank();

        //Tests players' money and ownerships
        players.forEach(p -> {
            assertEquals(p.getMoney(), MONEYS_FOR_5_PLAYERS);
            assertEquals(p.getOwnerships().size(), OWNERSHIPS_FOR_5_PLAYERS);
        });

        //Tests if players are humans or computers and their pawn's ID
        assertTrue(players.get(PAWN_ID_AND_N_PLAYER_1).isHuman());
        assertEquals(players.get(PAWN_ID_AND_N_PLAYER_1).getPawn().getID(), PAWN_ID_AND_N_PLAYER_1);
        assertTrue(players.get(PAWN_ID_AND_N_PLAYER_2).isHuman());
        assertEquals(players.get(PAWN_ID_AND_N_PLAYER_2).getPawn().getID(), PAWN_ID_AND_N_PLAYER_2);
        assertTrue(players.get(PAWN_ID_AND_N_PLAYER_3).isHuman());
        assertEquals(players.get(PAWN_ID_AND_N_PLAYER_3).getPawn().getID(), PAWN_ID_AND_N_PLAYER_3);
        assertFalse(players.get(PAWN_ID_AND_N_PLAYER_4).isHuman());
        assertEquals(players.get(PAWN_ID_AND_N_PLAYER_4).getPawn().getID(), PAWN_ID_AND_N_PLAYER_4);
        assertFalse(players.get(PAWN_ID_AND_N_PLAYER_5).isHuman());
        assertEquals(players.get(PAWN_ID_AND_N_PLAYER_5).getPawn().getID(), PAWN_ID_AND_N_PLAYER_5);

        //Tests the players' iterator
        assertEquals(version.getNextPlayer(), players.get(PAWN_ID_AND_N_PLAYER_1));
        assertEquals(version.getNextPlayer(), players.get(PAWN_ID_AND_N_PLAYER_2));
        assertEquals(version.getNextPlayer(), players.get(PAWN_ID_AND_N_PLAYER_3));
        assertEquals(version.getNextPlayer(), players.get(PAWN_ID_AND_N_PLAYER_4));
        assertEquals(version.getNextPlayer(), players.get(PAWN_ID_AND_N_PLAYER_5));
        assertEquals(version.getNextPlayer(), players.get(PAWN_ID_AND_N_PLAYER_1));
        assertEquals(version.getNextPlayer(), players.get(PAWN_ID_AND_N_PLAYER_2));
        assertEquals(version.getNextPlayer(), players.get(PAWN_ID_AND_N_PLAYER_3));
        assertEquals(version.getNextPlayer(), players.get(PAWN_ID_AND_N_PLAYER_4));
        assertEquals(version.getNextPlayer(), players.get(PAWN_ID_AND_N_PLAYER_5));
        assertEquals(version.getNextPlayer(), players.get(PAWN_ID_AND_N_PLAYER_1));
        assertEquals(version.getNextPlayer(), players.get(PAWN_ID_AND_N_PLAYER_2));
        assertEquals(version.getNextPlayer(), players.get(PAWN_ID_AND_N_PLAYER_3));
        assertEquals(version.getNextPlayer(), players.get(PAWN_ID_AND_N_PLAYER_4));
        assertEquals(version.getNextPlayer(), players.get(PAWN_ID_AND_N_PLAYER_5));
        assertEquals(version.getNextPlayer(), players.get(PAWN_ID_AND_N_PLAYER_1));

        assertEquals(version.endOfTurnAndNextPlayer(), players.get(PAWN_ID_AND_N_PLAYER_2));
        assertFalse(players.get(PAWN_ID_AND_N_PLAYER_2).dicesAlreadyRolled());
        assertEquals(version.getNextPlayer(), players.get(PAWN_ID_AND_N_PLAYER_3));
        assertFalse(players.get(PAWN_ID_AND_N_PLAYER_3).dicesAlreadyRolled());
        assertEquals(version.getNextPlayer(), players.get(PAWN_ID_AND_N_PLAYER_4));
        assertFalse(players.get(PAWN_ID_AND_N_PLAYER_4).dicesAlreadyRolled());
        assertEquals(version.getNextPlayer(), players.get(PAWN_ID_AND_N_PLAYER_5));
        assertFalse(players.get(PAWN_ID_AND_N_PLAYER_5).dicesAlreadyRolled());
        assertEquals(version.getNextPlayer(), players.get(PAWN_ID_AND_N_PLAYER_1));
        assertFalse(players.get(PAWN_ID_AND_N_PLAYER_1).dicesAlreadyRolled());

        //Tests twice dices and GoToPrison
        assertEquals(version.toRollDices().size(), N_OF_DICES);
        final List<Integer> dices = new LinkedList<>();
        dices.add(3);
        dices.add(3);
        final Player testPlayer = version.endOfTurnAndNextPlayer();
        assertFalse(testPlayer.dicesAlreadyRolled());
        new GoToPrison(version.getAllBoxes().get(BoxesPositions.PRISON_POSITION.getPos())).play(testPlayer);
        assertTrue(testPlayer.isInPrison());
        testPlayer.setLastDicesNumber(dices);
        final int pos = testPlayer.getPawn().getActualPos();
        assertEquals(pos, BoxesPositions.PRISON_POSITION.getPos());
        assertEquals(pos, 10);
        new ClassicDicesStrategy().nowPlay(testPlayer);
        assertFalse(testPlayer.isInPrison());
        assertEquals(testPlayer.getPawn().getActualPos(), pos + (testPlayer.lastDicesNumber().get(0) + testPlayer.lastDicesNumber().get(1)));
        assertEquals(testPlayer.getPawn().getActualPos(), 16);
        assertEquals(pos, testPlayer.getPawn().getPreviousPos());
        version.endOfTurnAndNextPlayer();
        assertFalse(testPlayer.dicesAlreadyRolled());

        testPlayer.setDicesRoll(true);
        new ClassicDicesStrategy().nowPlay(testPlayer);
        assertFalse(testPlayer.dicesAlreadyRolled());
        assertEquals(testPlayer.getPawn().getPreviousPos() + testPlayer.lastDicesNumber().get(0) + testPlayer.lastDicesNumber().get(1), testPlayer.getPawn().getActualPos());

        //Tests bank's ownerships size, allBoxes size and decks' sizes
        assertEquals(bank.getLeftOwnership().size(), N_MAX_OF_OWNERSHIPS - (PLAYERS_5 * OWNERSHIPS_FOR_5_PLAYERS));
        assertEquals(version.getAllBoxes().size(), N_ALL_BOXES);

        final List<Deck> decks = version.getDecks();
        assertEquals(decks.size(), N_OF_DECKS);
        assertEquals(decks.get(0).getCards().size(), decks.get(1).getCards().size());
        assertEquals(decks.get(0).getCards().size(), N_OF_CARDS_IN_ONE_DECK);

        //Tests MoveUpTo.takeSteps and MoveUpTo.theNearestStation
        final int amount = testPlayer.getMoney();
        MoveUpTo.takeSteps(2).play(testPlayer);
        assertEquals(testPlayer.getPawn().getActualPos(), testPlayer.getPawn().getPreviousPos() + 2);
        assertEquals(amount, testPlayer.getMoney());
        MoveUpTo.theNearestStation().play(testPlayer);
        assertEquals(testPlayer.getPawn().getActualPos(), BoxesPositions.OWNERSHIP18_POSITION.getPos());
        assertEquals(amount, testPlayer.getMoney());
        MoveUpTo.theNearestStation().play(testPlayer);
        assertEquals(testPlayer.getPawn().getActualPos(), BoxesPositions.OWNERSHIP26_POSITION.getPos());
        assertEquals(amount, testPlayer.getMoney());

        //PassFromStart
        final int newAmount = amount + Start.getMuchToPick();
        MoveUpTo.theNearestStation().play(testPlayer);
        assertEquals(testPlayer.getPawn().getActualPos(), BoxesPositions.OWNERSHIP3_POSITION.getPos());
        assertEquals(newAmount, testPlayer.getMoney());
        MoveUpTo.theNearestStation().play(testPlayer);
        assertEquals(testPlayer.getPawn().getActualPos(), BoxesPositions.OWNERSHIP11_POSITION.getPos());

        //Tests ToPay and ToBePaid
        final int money = 40;
        new ToPay(money).play(testPlayer);
        assertEquals(testPlayer.getMoney(), newAmount - money);
        new ToBePaid(money).play(testPlayer);
        assertEquals(testPlayer.getMoney(), newAmount);

        //Tests ToBuyProperties and ToSellProperties (buildings and ownerships) 
        //and ToMortgage and ToRevokeMortgage
        testPlayer.getOwnerships().forEach(o -> {
            if (o instanceof Land) {
                try {
                    ToSellProperties.sellABuilding(((Land) o), new Home(), bank).play(testPlayer);
                    fail();
                } catch (IllegalArgumentException i) {
                }
            }
        });
        testPlayer.getOwnerships().forEach(o -> {
            if (o instanceof Land) {
                final Land land = ((Land) o);
                try {
                    ToBuyProperties.buyABuilding(land, bank).play(testPlayer);
                    ToBuyProperties.buyABuilding(land, bank).play(testPlayer);
                } catch (IllegalArgumentException i) {
                    fail();
                }
                try {
                    ToSellProperties.sellABuilding(land, ((LandGroup) land.getGroup()).getBuildings().get(0), bank).play(testPlayer);
                    ToSellProperties.sellABuilding(land, ((LandGroup) land.getGroup()).getBuildings().get(0), bank).play(testPlayer);
                } catch (IllegalArgumentException i) {
                    fail();
                }
                final int amount2 = testPlayer.getMoney();
                try {
                    new ToMortgage(land).play(testPlayer);
                } catch (IllegalArgumentException i) {
                    fail();
                }
                assertEquals(amount2, testPlayer.getMoney() - ((LandContract) land.getContract()).getMortgageValue());
                assertTrue(land.isMortgaged());
                try {
                    ToSellProperties.sellAOwnership(land.getContract().getCost(), land, bank).play(testPlayer);
                    fail();
                } catch (IllegalArgumentException i) {
                }
                new ToRevokeMortgage(land).play(testPlayer);
                assertEquals(amount2, testPlayer.getMoney());
                assertFalse(land.isMortgaged());
            }
        });
        final Ownership ow = bank.getOwnership();
        final int newMoney = testPlayer.getMoney();
        ToBuyProperties.buyAOwnership(ow.getContract().getCost(), ow).play(testPlayer);
        assertFalse(bank.getLeftOwnership().contains(ow));
        assertEquals(testPlayer.getOwnerships().size(), OWNERSHIPS_FOR_5_PLAYERS + 1);
        assertEquals(testPlayer.getMoney(), newMoney - ow.getContract().getCost());
        ToSellProperties.sellAOwnership(ow.getContract().getCost(), ow, bank).play(testPlayer);
        assertTrue(bank.getLeftOwnership().contains(ow));
        assertEquals(testPlayer.getOwnerships().size(), OWNERSHIPS_FOR_5_PLAYERS);
        assertEquals(testPlayer.getMoney(), newMoney);
        assertFalse(testPlayer.getOwnerships().contains(ow));

        //Tests ToDrawCard
        new ToDrawCards(decks.get(0)).play(testPlayer);
        final Card oneToPick = testPlayer.lastCardDrew();
        try {
            oneToPick.getPlayer().get();
            fail();
        } catch (NoSuchElementException n) {
        }
        testPlayer.addCard(oneToPick);
        assertEquals(oneToPick.getPlayer().get(), testPlayer);
        assertEquals(1, testPlayer.getCards().size());
        assertEquals(oneToPick, testPlayer.getCards().get(0));
        testPlayer.removeCard(oneToPick);
        try {
            oneToPick.getPlayer().get();
            fail();
        } catch (NoSuchElementException n) {
        }
        assertEquals(0, testPlayer.getCards().size());
  }


    /**
     * This is the test for model's part of the italian game.
     */
    @Test
    public void testItalian() {
        final List<Player> players = new LinkedList<>();
        GameVersion version;

        //Test the number of players
        players.add(new ClassicPlayer("Margherita", new ClassicPawn(PAWN_ID_AND_N_PLAYER_1), true));
        try {
            version = new GameVersionImpl(new ClassicStrategy(players));
            fail();
        } catch (IllegalArgumentException i) {
        }
        players.add(new ClassicPlayer("Laura", new ClassicPawn(PAWN_ID_AND_N_PLAYER_2), true));
        players.add(new ClassicPlayer("Giuseppe", new ClassicPawn(PAWN_ID_AND_N_PLAYER_3), true));
        players.add(new ClassicPlayer("Computer1", new ClassicPawn(PAWN_ID_AND_N_PLAYER_4), false));
        players.add(new ClassicPlayer("Computer2", new ClassicPawn(PAWN_ID_AND_N_PLAYER_5), false));
        players.add(new ClassicPlayer("Computer3", new ClassicPawn(PAWN_ID_AND_N_PLAYER_6), false));
        players.add(new ClassicPlayer("7th player", new ClassicPawn(SEVENTH_PLAYER), true));
        try {
            version = new GameVersionImpl(new ItalianStrategy(players));
            fail();
        } catch (IllegalArgumentException i) {
        }
        players.remove(SEVENTH_PLAYER);
        players.remove(PAWN_ID_AND_N_PLAYER_6);
        version = new GameVersionImpl(new ItalianStrategy(players));
        assertEquals(players.size(), PLAYERS_5);

        //Tests players' money and ownerships
        players.forEach(p -> {
            assertEquals(p.getMoney(), MONEYS_FOR_5_PLAYERS);
            assertEquals(p.getOwnerships().size(), OWNERSHIPS_FOR_5_PLAYERS);
        });

        //Tests if players are humans or computers and their pawn's ID
        assertTrue(players.get(PAWN_ID_AND_N_PLAYER_1).isHuman());
        assertEquals(players.get(PAWN_ID_AND_N_PLAYER_1).getPawn().getID(), PAWN_ID_AND_N_PLAYER_1);
        assertTrue(players.get(PAWN_ID_AND_N_PLAYER_2).isHuman());
        assertEquals(players.get(PAWN_ID_AND_N_PLAYER_2).getPawn().getID(), PAWN_ID_AND_N_PLAYER_2);
        assertTrue(players.get(PAWN_ID_AND_N_PLAYER_3).isHuman());
        assertEquals(players.get(PAWN_ID_AND_N_PLAYER_3).getPawn().getID(), PAWN_ID_AND_N_PLAYER_3);
        assertFalse(players.get(PAWN_ID_AND_N_PLAYER_4).isHuman());
        assertEquals(players.get(PAWN_ID_AND_N_PLAYER_4).getPawn().getID(), PAWN_ID_AND_N_PLAYER_4);
        assertFalse(players.get(PAWN_ID_AND_N_PLAYER_5).isHuman());
        assertEquals(players.get(PAWN_ID_AND_N_PLAYER_5).getPawn().getID(), PAWN_ID_AND_N_PLAYER_5);

        //Tests the players' iterator
        assertEquals(version.getNextPlayer(), players.get(PAWN_ID_AND_N_PLAYER_1));
        assertEquals(version.getNextPlayer(), players.get(PAWN_ID_AND_N_PLAYER_2));
        assertEquals(version.getNextPlayer(), players.get(PAWN_ID_AND_N_PLAYER_3));
        assertEquals(version.getNextPlayer(), players.get(PAWN_ID_AND_N_PLAYER_4));
        assertEquals(version.getNextPlayer(), players.get(PAWN_ID_AND_N_PLAYER_5));
        assertEquals(version.getNextPlayer(), players.get(PAWN_ID_AND_N_PLAYER_1));
        assertEquals(version.getNextPlayer(), players.get(PAWN_ID_AND_N_PLAYER_2));
        assertEquals(version.getNextPlayer(), players.get(PAWN_ID_AND_N_PLAYER_3));
        assertEquals(version.getNextPlayer(), players.get(PAWN_ID_AND_N_PLAYER_4));
        assertEquals(version.getNextPlayer(), players.get(PAWN_ID_AND_N_PLAYER_5));
        assertEquals(version.getNextPlayer(), players.get(PAWN_ID_AND_N_PLAYER_1));
        assertEquals(version.getNextPlayer(), players.get(PAWN_ID_AND_N_PLAYER_2));
        assertEquals(version.getNextPlayer(), players.get(PAWN_ID_AND_N_PLAYER_3));
        assertEquals(version.getNextPlayer(), players.get(PAWN_ID_AND_N_PLAYER_4));
        assertEquals(version.getNextPlayer(), players.get(PAWN_ID_AND_N_PLAYER_5));
        assertEquals(version.getNextPlayer(), players.get(PAWN_ID_AND_N_PLAYER_1));

        assertEquals(version.endOfTurnAndNextPlayer(), players.get(PAWN_ID_AND_N_PLAYER_2));
        assertFalse(players.get(PAWN_ID_AND_N_PLAYER_2).dicesAlreadyRolled());
        assertEquals(version.getNextPlayer(), players.get(PAWN_ID_AND_N_PLAYER_3));
        assertFalse(players.get(PAWN_ID_AND_N_PLAYER_3).dicesAlreadyRolled());
        assertEquals(version.getNextPlayer(), players.get(PAWN_ID_AND_N_PLAYER_4));
        assertFalse(players.get(PAWN_ID_AND_N_PLAYER_4).dicesAlreadyRolled());
        assertEquals(version.getNextPlayer(), players.get(PAWN_ID_AND_N_PLAYER_5));
        assertFalse(players.get(PAWN_ID_AND_N_PLAYER_5).dicesAlreadyRolled());
        assertEquals(version.getNextPlayer(), players.get(PAWN_ID_AND_N_PLAYER_1));
        assertFalse(players.get(PAWN_ID_AND_N_PLAYER_1).dicesAlreadyRolled());

        //Tests dices and GoToPrison
        final Player testPlayer = version.getNextPlayer();
        assertEquals(version.toRollDices().size(), N_OF_ITAL_DICES);
        final List<Integer> dices = new LinkedList<>();
        dices.add(3);
        dices.add(3);
        dices.add(3);
        testPlayer.setLastDicesNumber(dices);
        final Box prison = version.getAllBoxes().get(BoxesPositions.PRISON_POSITION.getPos());
        new ItalianDicesStrategy(prison).nowPlay(testPlayer);
        assertTrue(testPlayer.isInPrison());
        final int pos = testPlayer.getPawn().getActualPos();
        assertEquals(pos, BoxesPositions.PRISON_POSITION.getPos());
        assertEquals(pos, 10);
        dices.clear();
        dices.add(2);
        dices.add(2);
        dices.add(4);
        testPlayer.setLastDicesNumber(dices);
        new ItalianDicesStrategy(prison).nowPlay(testPlayer);
        assertFalse(testPlayer.isInPrison());
        assertEquals(testPlayer.getPawn().getActualPos(), pos + (testPlayer.lastDicesNumber().get(0) + testPlayer.lastDicesNumber().get(1) + testPlayer.lastDicesNumber().get(2)));
        assertEquals(pos, testPlayer.getPawn().getPreviousPos());
        version.endOfTurnAndNextPlayer();
        assertFalse(testPlayer.dicesAlreadyRolled());

        testPlayer.setDicesRoll(true);
        new ItalianDicesStrategy(prison).nowPlay(testPlayer);
        assertFalse(testPlayer.dicesAlreadyRolled());
        assertEquals(testPlayer.getPawn().getPreviousPos() + testPlayer.lastDicesNumber().get(0) + testPlayer.lastDicesNumber().get(1) + testPlayer.lastDicesNumber().get(2), testPlayer.getPawn().getActualPos());

        //Tests EvadeTaxes and ToStealMoney
        final int money = 40;
        final int amount = testPlayer.getMoney();
        final int tax = ((int) Math.floor(money * 0.78));
        final ItalianNeutralArea neutra = ((ItalianNeutralArea) version.getAllBoxes().get(BoxesPositions.NEUTRAL_AREA_POSITION.getPos()));
        new EvadeTaxes(money, neutra).play(testPlayer);
        assertEquals(testPlayer.getMoney(), amount - tax, 1);
        final int neutralMoney = neutra.getDirtyMoney();
        assertEquals(neutralMoney, tax, 1);
        new ToStealMoney(neutra).play(testPlayer);
        assertEquals(testPlayer.getMoney(), amount, 1);
        assertEquals(neutra.getDirtyMoney(), 0);

  }

}
