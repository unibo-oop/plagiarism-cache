package it.unibo.monopoli.controller;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import it.unibo.monopoli.model.mainunits.ClassicPawn;
import it.unibo.monopoli.model.table.Box;
import it.unibo.monopoli.model.table.Ownership;

/**
 * Is Used for do jUnit test on controller .
 *
 */
public class TestController {
    private static final int PAWN_ID_AND_N_PLAYER_1 = 0;
    private static final int PAWN_ID_AND_N_PLAYER_2 = 1;
    private static final int PAWN_ID_AND_N_PLAYER_3 = 2;
    private static final int PAWN_ID_AND_N_PLAYER_4 = 3;

    /**
     * This is the test for model's part of the classic game.
     */
    @Test
    public void testAction() {
        Controller contro = new ControllerImpl();

        contro.addPlayer("Margherita", new ClassicPawn(PAWN_ID_AND_N_PLAYER_1), true);
        contro.addPlayer("Laura", new ClassicPawn(PAWN_ID_AND_N_PLAYER_2), true);
        contro.addPlayer("Giuseppe", new ClassicPawn(PAWN_ID_AND_N_PLAYER_3), false);
        contro.addPlayer("Computer1", new ClassicPawn(PAWN_ID_AND_N_PLAYER_4), false);
        contro.initializedVersion(EVersion.CLASSIC);
        List<Box> boxes = contro.getAllBoxes();
        contro.endTurn();
        // Test the number of players
        Ownership o = ((Ownership) boxes.get(1));
        o.setOwner(contro.getBank());
        contro.toRollDices();

        // test new owner is player and if let buy an ownership
        contro.getNextBoxsActions(boxes.get(1), contro.getActualPlayer());
        try {
            contro.setActualPosition(1);
            contro.buyOwnership();

            assertEquals(((Ownership) contro.getActualBox()).getOwner(), contro.getActualPlayer());
        } catch (IllegalArgumentException i) {
            System.out.println("You can\'t buy this ownership");
        }
        // test if let mortgage an ownership
        contro.getNextBoxsActions(boxes.get(1), contro.getActualPlayer());
        try {
            contro.setActualPosition(1);

            ((Ownership) contro.getActualBox()).setMortgage(false);

            contro.mortgageOwnership();

            assertEquals(((Ownership) contro.getActualBox()).getOwner(), contro.getActualPlayer());
        } catch (IllegalArgumentException i) {
            System.out.println("You can\'t mortgage this ownership'");
        }
        // test if let revoke mortgage of ownership

        contro.getNextBoxsActions(boxes.get(1), contro.getActualPlayer());
        try {
            contro.setActualPosition(1);

            contro.revokeMortgageOwnership();

            assertEquals(((Ownership) contro.getActualBox()).getOwner(), contro.getActualPlayer());
        } catch (IllegalArgumentException i) {
            System.out.println("Non puoi togliere l\'ipoteca da questa proprieta\'");
        }
        // test new owner is bank and if let sell an ownership
        contro.getNextBoxsActions(boxes.get(1), contro.getActualPlayer());
        try {
            contro.setActualPosition(1);
            contro.sellOwnership();
            assertEquals(((Ownership) contro.getActualBox()).getOwner(), contro.getBank());
        } catch (IllegalArgumentException i) {
            System.out.println("You can't revoke mortgage on other player\'s ownerships");
        }

    }
    /**
     * Test if player want do action on other player's ownerships.
     */
    @Test
    public void testActionNegative() {
        Controller contro = new ControllerImpl();

        contro.addPlayer("Margherita", new ClassicPawn(PAWN_ID_AND_N_PLAYER_1), true);
        contro.addPlayer("Laura", new ClassicPawn(PAWN_ID_AND_N_PLAYER_2), true);
        contro.addPlayer("Giuseppe", new ClassicPawn(PAWN_ID_AND_N_PLAYER_3), false);
        contro.addPlayer("Computer1", new ClassicPawn(PAWN_ID_AND_N_PLAYER_4), false);
        contro.initializedVersion(EVersion.CLASSIC);
        List<Box> boxes = contro.getAllBoxes();
        contro.endTurn();
        // Test the number of players
        Ownership o = ((Ownership) boxes.get(1));
        o.setOwner(contro.getPlayers().get((contro.getPlayers().indexOf(contro.getActualPlayer()) + 1)));
        contro.toRollDices();

        // control if let buy the ownerships of other player

        contro.getNextBoxsActions(boxes.get(1), contro.getActualPlayer());
        try {
            contro.setActualPosition(1);
            contro.buyOwnership();
            assertEquals(((Ownership) contro.getActualBox()).getOwner(), contro.getActualPlayer());
        } catch (IllegalArgumentException i) {
            System.out.println("You can\'t buy this ownership'");
        }

        // control if sell mortgage the ownerships of other player
        contro.getNextBoxsActions(boxes.get(1), contro.getActualPlayer());
        try {
            contro.setActualPosition(1);
            contro.sellOwnership();
            assertEquals(((Ownership) contro.getActualBox()).getOwner(), contro.getBank());
        } catch (IllegalArgumentException i) {
            System.out.println("You can\'t sell this ownership");
        }

        // control if let mortgage the ownerships of other player
        contro.getNextBoxsActions(boxes.get(1), contro.getActualPlayer());
        try {
            contro.setActualPosition(1);
            ((Ownership) contro.getActualBox()).setMortgage(false);
            contro.mortgageOwnership();
            contro.sellOwnership();
            assertEquals(((Ownership) contro.getActualBox()).getOwner(), contro.getBank());
        } catch (IllegalArgumentException i) {
            System.out.println("You cant mortgage this ownership'");
        }
        // Control if let revoke mortgage on other player\'s ownerships
        contro.getNextBoxsActions(boxes.get(1), contro.getActualPlayer());
        try {
            contro.setActualPosition(1);

            contro.revokeMortgageOwnership();

            assertEquals(((Ownership) contro.getActualBox()).getOwner(), contro.getActualPlayer());
        } catch (IllegalArgumentException i) {
            System.out.println("You can't revoke mortgage on other player\'s ownerships");
        }

    }
    // //Tests if players are humans or computers and their pawn's ID
    // assertTrue(players.get(PAWN_ID_AND_N_PLAYER_1).isHuman());
    // assertEquals(players.get(PAWN_ID_AND_N_PLAYER_1).getPawn().getID(),
    // PAWN_ID_AND_N_PLAYER_1);
    // assertTrue(players.get(PAWN_ID_AND_N_PLAYER_2).isHuman());
    // assertEquals(players.get(PAWN_ID_AND_N_PLAYER_2).getPawn().getID(),
    // PAWN_ID_AND_N_PLAYER_2);
    // assertTrue(players.get(PAWN_ID_AND_N_PLAYER_3).isHuman());
    // assertEquals(players.get(PAWN_ID_AND_N_PLAYER_3).getPawn().getID(),
    // PAWN_ID_AND_N_PLAYER_3);
    // assertFalse(players.get(PAWN_ID_AND_N_PLAYER_4).isHuman());
    // assertEquals(players.get(PAWN_ID_AND_N_PLAYER_4).getPawn().getID(),
    // PAWN_ID_AND_N_PLAYER_4);
    // assertFalse(players.get(PAWN_ID_AND_N_PLAYER_5).isHuman());
    // assertEquals(players.get(PAWN_ID_AND_N_PLAYER_5).getPawn().getID(),
    // PAWN_ID_AND_N_PLAYER_5);
    //
    // //Tests the players' iterator
    // assertEquals(version.getNextPlayer(),
    // players.get(PAWN_ID_AND_N_PLAYER_1));
    // assertEquals(version.getNextPlayer(),
    // players.get(PAWN_ID_AND_N_PLAYER_2));
    // assertEquals(version.getNextPlayer(),
    // players.get(PAWN_ID_AND_N_PLAYER_3));
    // assertEquals(version.getNextPlayer(),
    // players.get(PAWN_ID_AND_N_PLAYER_4));
    // assertEquals(version.getNextPlayer(),
    // players.get(PAWN_ID_AND_N_PLAYER_5));
    // assertEquals(version.getNextPlayer(),
    // players.get(PAWN_ID_AND_N_PLAYER_1));
    // assertEquals(version.getNextPlayer(),
    // players.get(PAWN_ID_AND_N_PLAYER_2));
    // assertEquals(version.getNextPlayer(),
    // players.get(PAWN_ID_AND_N_PLAYER_3));
    // assertEquals(version.getNextPlayer(),
    // players.get(PAWN_ID_AND_N_PLAYER_4));
    // assertEquals(version.getNextPlayer(),
    // players.get(PAWN_ID_AND_N_PLAYER_5));
    // assertEquals(version.getNextPlayer(),
    // players.get(PAWN_ID_AND_N_PLAYER_1));
    // assertEquals(version.getNextPlayer(),
    // players.get(PAWN_ID_AND_N_PLAYER_2));
    // assertEquals(version.getNextPlayer(),
    // players.get(PAWN_ID_AND_N_PLAYER_3));
    // assertEquals(version.getNextPlayer(),
    // players.get(PAWN_ID_AND_N_PLAYER_4));
    // assertEquals(version.getNextPlayer(),
    // players.get(PAWN_ID_AND_N_PLAYER_5));
    // assertEquals(version.getNextPlayer(),
    // players.get(PAWN_ID_AND_N_PLAYER_1));
    //
    // assertEquals(version.endOfTurnAndNextPlayer(),
    // players.get(PAWN_ID_AND_N_PLAYER_2));
    // assertFalse(players.get(PAWN_ID_AND_N_PLAYER_2).dicesAlreadyRolled());
    // assertEquals(version.getNextPlayer(),
    // players.get(PAWN_ID_AND_N_PLAYER_3));
    // assertFalse(players.get(PAWN_ID_AND_N_PLAYER_3).dicesAlreadyRolled());
    // assertEquals(version.getNextPlayer(),
    // players.get(PAWN_ID_AND_N_PLAYER_4));
    // assertFalse(players.get(PAWN_ID_AND_N_PLAYER_4).dicesAlreadyRolled());
    // assertEquals(version.getNextPlayer(),
    // players.get(PAWN_ID_AND_N_PLAYER_5));
    // assertFalse(players.get(PAWN_ID_AND_N_PLAYER_5).dicesAlreadyRolled());
    // assertEquals(version.getNextPlayer(),
    // players.get(PAWN_ID_AND_N_PLAYER_1));
    // assertFalse(players.get(PAWN_ID_AND_N_PLAYER_1).dicesAlreadyRolled());
    //
    // //Tests twice dices and GoToPrison
    // assertEquals(version.toRollDices().size(), N_OF_DICES);
    // final List<Integer> dices = new LinkedList<>();
    // dices.add(3);
    // dices.add(3);
    // final Player testPlayer = version.endOfTurnAndNextPlayer();
    // assertFalse(testPlayer.dicesAlreadyRolled());
    // new
    // GoToPrison(version.getAllBoxes().get(BoxesPositions.PRISON_POSITION.getPos())).play(testPlayer);
    // assertTrue(testPlayer.isInPrison());
    // testPlayer.setLastDicesNumber(dices);
    // final int pos = testPlayer.getPawn().getActualPos();
    // assertEquals(pos, BoxesPositions.PRISON_POSITION.getPos());
    // assertEquals(pos, 10);
    // new ClassicDicesStrategy().nowPlay(testPlayer);
    // assertFalse(testPlayer.isInPrison());
    // assertEquals(testPlayer.getPawn().getActualPos(), pos +
    // (testPlayer.lastDicesNumber().get(0) +
    // testPlayer.lastDicesNumber().get(1)));
    // assertEquals(testPlayer.getPawn().getActualPos(), 16);
    // assertEquals(pos, testPlayer.getPawn().getPreviousPos());
    // version.endOfTurnAndNextPlayer();
    // assertFalse(testPlayer.dicesAlreadyRolled());
    //
    // testPlayer.setDicesRoll(true);
    // new ClassicDicesStrategy().nowPlay(testPlayer);
    // assertFalse(testPlayer.dicesAlreadyRolled());
    // assertEquals(testPlayer.getPawn().getPreviousPos() +
    // testPlayer.lastDicesNumber().get(0) +
    // testPlayer.lastDicesNumber().get(1),
    // testPlayer.getPawn().getActualPos());
    //
    // //Tests bank's ownerships size, allBoxes size and decks' sizes
    // assertEquals(bank.getLeftOwnership().size(), N_MAX_OF_OWNERSHIPS -
    // (PLAYERS_5 * OWNERSHIPS_FOR_5_PLAYERS));
    // assertEquals(version.getAllBoxes().size(), N_ALL_BOXES);
    //
    // final List<Deck> decks = version.getDecks();
    // assertEquals(decks.size(), N_OF_DECKS);
    // assertEquals(decks.get(0).getCards().size(),
    // decks.get(1).getCards().size());
    // assertEquals(decks.get(0).getCards().size(), N_OF_CARDS_IN_ONE_DECK);
    //
    // //Tests MoveUpTo.takeSteps and MoveUpTo.theNearestStation
    // final int amount = testPlayer.getMoney();
    // MoveUpTo.takeSteps(2).play(testPlayer);
    // assertEquals(testPlayer.getPawn().getActualPos(),
    // testPlayer.getPawn().getPreviousPos() + 2);
    // assertEquals(amount, testPlayer.getMoney());
    // MoveUpTo.theNearestStation().play(testPlayer);
    // assertEquals(testPlayer.getPawn().getActualPos(),
    // BoxesPositions.OWNERSHIP18_POSITION.getPos());
    // assertEquals(amount, testPlayer.getMoney());
    // MoveUpTo.theNearestStation().play(testPlayer);
    // assertEquals(testPlayer.getPawn().getActualPos(),
    // BoxesPositions.OWNERSHIP26_POSITION.getPos());
    // assertEquals(amount, testPlayer.getMoney());
    //
    // //PassFromStart
    // final int newAmount = amount + Start.getMuchToPick();
    // MoveUpTo.theNearestStation().play(testPlayer);
    // assertEquals(testPlayer.getPawn().getActualPos(),
    // BoxesPositions.OWNERSHIP3_POSITION.getPos());
    // assertEquals(newAmount, testPlayer.getMoney());
    // MoveUpTo.theNearestStation().play(testPlayer);
    // assertEquals(testPlayer.getPawn().getActualPos(),
    // BoxesPositions.OWNERSHIP11_POSITION.getPos());
    //
    // //Tests ToPay and ToBePaid
    // final int money = 40;
    // new ToPay(money, testPlayer).play(testPlayer);
    // assertEquals(testPlayer.getMoney(), newAmount - money);
    // new ToBePaid(money).play(testPlayer);
    // assertEquals(testPlayer.getMoney(), newAmount);
    //
    // //Tests ToBuyProperties and ToSellProperties (buildings and ownerships)
    // //and ToMortgage and ToRevokeMortgage
    // testPlayer.getOwnerships().forEach(o -> {
    // if (o instanceof Land) {
    // try {
    // ToSellProperties.sellABuilding(((Land) o), new Home(),
    // bank).play(testPlayer);
    // fail();
    // } catch (IllegalArgumentException i) {
    // }
    // }
    // });
    // testPlayer.getOwnerships().forEach(o -> {
    // if (o instanceof Land) {
    // final Land land = ((Land) o);
    // try {
    // ToBuyProperties.buyABuilding(land, bank).play(testPlayer);
    // ToBuyProperties.buyABuilding(land, bank).play(testPlayer);
    // } catch (IllegalArgumentException i) {
    // fail();
    // }
    // try {
    // new ToMortgage(land).play(testPlayer);
    // fail();
    // } catch (IllegalArgumentException i) {
    // }
    // try {
    // ToSellProperties.sellABuilding(land, ((LandGroup)
    // land.getGroup()).getBuildings().get(0), bank).play(testPlayer);
    // ToSellProperties.sellABuilding(land, ((LandGroup)
    // land.getGroup()).getBuildings().get(0), bank).play(testPlayer);
    // } catch (IllegalArgumentException i) {
    // fail();
    // }
    // final int amount2 = testPlayer.getMoney();
    // try {
    // new ToMortgage(land).play(testPlayer);
    // } catch (IllegalArgumentException i) {
    // fail();
    // }
    // assertEquals(amount2, testPlayer.getMoney() - ((LandContract)
    // land.getContract()).getMortgageValue());
    // assertTrue(land.isMortgaged());
    // try {
    // ToBuyProperties.buyABuilding(land, bank).play(testPlayer);
    // fail();
    // } catch (IllegalArgumentException i) {
    // }
    // try {
    // ToSellProperties.sellAOwnership(land.getContract().getCost(), land,
    // bank).play(testPlayer);
    // fail();
    // } catch (IllegalArgumentException i) {
    // }
    // new ToRevokeMortgage(land).play(testPlayer);
    // assertEquals(amount2, testPlayer.getMoney());
    // assertFalse(land.isMortgaged());
    // }
    // });
    // final Ownership ow = bank.getOwnership();
    // final int newMoney = testPlayer.getMoney();
    // ToBuyProperties.buyAOwnership(ow.getContract().getCost(),
    // ow).play(testPlayer);
    // assertFalse(bank.getLeftOwnership().contains(ow));
    // assertEquals(testPlayer.getOwnerships().size(), OWNERSHIPS_FOR_5_PLAYERS
    // + 1);
    // assertEquals(testPlayer.getMoney(), newMoney -
    // ow.getContract().getCost());
    // ToSellProperties.sellAOwnership(ow.getContract().getCost(), ow,
    // bank).play(testPlayer);
    // assertTrue(bank.getLeftOwnership().contains(ow));
    // assertEquals(testPlayer.getOwnerships().size(),
    // OWNERSHIPS_FOR_5_PLAYERS);
    // assertEquals(testPlayer.getMoney(), newMoney);
    // assertFalse(testPlayer.getOwnerships().contains(ow));
    //
    // //Tests ToDrawCard
    // final Card oneToPick = decks.get(0).getCards().get(0);
    // new ToDrawCards(decks.get(0)).play(testPlayer);
    // assertEquals(oneToPick, testPlayer.lastCardDrew());
    // for (int i = 0; i < N_OF_CARDS_IN_ONE_DECK - 1; i++) {
    // new ToDrawCards(decks.get(0)).play(testPlayer);
    // }
    // final Card lastToPick =
    // decks.get(0).getCards().get(N_OF_CARDS_IN_ONE_DECK - 1);
    // assertEquals(lastToPick, testPlayer.lastCardDrew());
    // new ToDrawCards(decks.get(0)).play(testPlayer);
    // assertEquals(oneToPick, testPlayer.lastCardDrew());
    //
    // //Tests ClassicCard
    // try {
    // oneToPick.getPlayer().get();
    // fail();
    // } catch (NoSuchElementException n) {
    // }
    // testPlayer.addCard(oneToPick);
    // assertEquals(oneToPick.getPlayer().get(), testPlayer);
    // assertEquals(1, testPlayer.getCards().size());
    // assertEquals(oneToPick, testPlayer.getCards().get(0));
    // for (int i = 0; i < N_OF_CARDS_IN_ONE_DECK - 1; i++) {
    // new ToDrawCards(decks.get(0)).play(testPlayer);
    // }
    // assertEquals(decks.get(0).getCards().get(N_OF_CARDS_IN_ONE_DECK - 1),
    // testPlayer.lastCardDrew());
    // new ToDrawCards(decks.get(0)).play(testPlayer);
    // assertFalse(oneToPick.equals(testPlayer.lastCardDrew()));
    // assertEquals(decks.get(0).getCards().get(1), testPlayer.lastCardDrew());
    // testPlayer.removeCard(oneToPick);
    // try {
    // oneToPick.getPlayer().get();
    // fail();
    // } catch (NoSuchElementException n) {
    // }
    // assertEquals(0, testPlayer.getCards().size());
    // }
    //
    //
    // /**
    // * This is the test for model's part of the italian game.
    // */
    // @Test
    // public void testItalian() {
    // final List<Player> players = new LinkedList<>();
    // GameVersion version;
    //
    // //Test the number of players
    // players.add(new ClassicPlayer("Margherita", new
    // ClassicPawn(PAWN_ID_AND_N_PLAYER_1), true));
    // try {
    // version = new GameVersionImpl(new ClassicStrategy(players));
    // fail();
    // } catch (IllegalArgumentException i) {
    // }
    // players.add(new ClassicPlayer("Laura", new
    // ClassicPawn(PAWN_ID_AND_N_PLAYER_2), true));
    // players.add(new ClassicPlayer("Giuseppe", new
    // ClassicPawn(PAWN_ID_AND_N_PLAYER_3), true));
    // players.add(new ClassicPlayer("Computer1", new
    // ClassicPawn(PAWN_ID_AND_N_PLAYER_4), false));
    // players.add(new ClassicPlayer("Computer2", new
    // ClassicPawn(PAWN_ID_AND_N_PLAYER_5), false));
    // players.add(new ClassicPlayer("Computer3", new
    // ClassicPawn(PAWN_ID_AND_N_PLAYER_6), false));
    // players.add(new ClassicPlayer("7th player", new
    // ClassicPawn(SEVENTH_PLAYER), true));
    // try {
    // version = new GameVersionImpl(new ItalianStrategy(players));
    // fail();
    // } catch (IllegalArgumentException i) {
    // }
    // players.remove(SEVENTH_PLAYER);
    // players.remove(PAWN_ID_AND_N_PLAYER_6);
    // version = new GameVersionImpl(new ItalianStrategy(players));
    // assertEquals(players.size(), PLAYERS_5);
    //// final Bank bank = version.getBank();
    //
    // //Tests players' money and ownerships
    // players.forEach(p -> {
    // assertEquals(p.getMoney(), MONEYS_FOR_5_PLAYERS);
    // assertEquals(p.getOwnerships().size(), OWNERSHIPS_FOR_5_PLAYERS);
    // });
    //
    // //Tests if players are humans or computers and their pawn's ID
    // assertTrue(players.get(PAWN_ID_AND_N_PLAYER_1).isHuman());
    // assertEquals(players.get(PAWN_ID_AND_N_PLAYER_1).getPawn().getID(),
    // PAWN_ID_AND_N_PLAYER_1);
    // assertTrue(players.get(PAWN_ID_AND_N_PLAYER_2).isHuman());
    // assertEquals(players.get(PAWN_ID_AND_N_PLAYER_2).getPawn().getID(),
    // PAWN_ID_AND_N_PLAYER_2);
    // assertTrue(players.get(PAWN_ID_AND_N_PLAYER_3).isHuman());
    // assertEquals(players.get(PAWN_ID_AND_N_PLAYER_3).getPawn().getID(),
    // PAWN_ID_AND_N_PLAYER_3);
    // assertFalse(players.get(PAWN_ID_AND_N_PLAYER_4).isHuman());
    // assertEquals(players.get(PAWN_ID_AND_N_PLAYER_4).getPawn().getID(),
    // PAWN_ID_AND_N_PLAYER_4);
    // assertFalse(players.get(PAWN_ID_AND_N_PLAYER_5).isHuman());
    // assertEquals(players.get(PAWN_ID_AND_N_PLAYER_5).getPawn().getID(),
    // PAWN_ID_AND_N_PLAYER_5);
    //
    // //Tests the players' iterator
    // assertEquals(version.getNextPlayer(),
    // players.get(PAWN_ID_AND_N_PLAYER_1));
    // assertEquals(version.getNextPlayer(),
    // players.get(PAWN_ID_AND_N_PLAYER_2));
    // assertEquals(version.getNextPlayer(),
    // players.get(PAWN_ID_AND_N_PLAYER_3));
    // assertEquals(version.getNextPlayer(),
    // players.get(PAWN_ID_AND_N_PLAYER_4));
    // assertEquals(version.getNextPlayer(),
    // players.get(PAWN_ID_AND_N_PLAYER_5));
    // assertEquals(version.getNextPlayer(),
    // players.get(PAWN_ID_AND_N_PLAYER_1));
    // assertEquals(version.getNextPlayer(),
    // players.get(PAWN_ID_AND_N_PLAYER_2));
    // assertEquals(version.getNextPlayer(),
    // players.get(PAWN_ID_AND_N_PLAYER_3));
    // assertEquals(version.getNextPlayer(),
    // players.get(PAWN_ID_AND_N_PLAYER_4));
    // assertEquals(version.getNextPlayer(),
    // players.get(PAWN_ID_AND_N_PLAYER_5));
    // assertEquals(version.getNextPlayer(),
    // players.get(PAWN_ID_AND_N_PLAYER_1));
    // assertEquals(version.getNextPlayer(),
    // players.get(PAWN_ID_AND_N_PLAYER_2));
    // assertEquals(version.getNextPlayer(),
    // players.get(PAWN_ID_AND_N_PLAYER_3));
    // assertEquals(version.getNextPlayer(),
    // players.get(PAWN_ID_AND_N_PLAYER_4));
    // assertEquals(version.getNextPlayer(),
    // players.get(PAWN_ID_AND_N_PLAYER_5));
    // assertEquals(version.getNextPlayer(),
    // players.get(PAWN_ID_AND_N_PLAYER_1));
    //
    // assertEquals(version.endOfTurnAndNextPlayer(),
    // players.get(PAWN_ID_AND_N_PLAYER_2));
    // assertFalse(players.get(PAWN_ID_AND_N_PLAYER_2).dicesAlreadyRolled());
    // assertEquals(version.getNextPlayer(),
    // players.get(PAWN_ID_AND_N_PLAYER_3));
    // assertFalse(players.get(PAWN_ID_AND_N_PLAYER_3).dicesAlreadyRolled());
    // assertEquals(version.getNextPlayer(),
    // players.get(PAWN_ID_AND_N_PLAYER_4));
    // assertFalse(players.get(PAWN_ID_AND_N_PLAYER_4).dicesAlreadyRolled());
    // assertEquals(version.getNextPlayer(),
    // players.get(PAWN_ID_AND_N_PLAYER_5));
    // assertFalse(players.get(PAWN_ID_AND_N_PLAYER_5).dicesAlreadyRolled());
    // assertEquals(version.getNextPlayer(),
    // players.get(PAWN_ID_AND_N_PLAYER_1));
    // assertFalse(players.get(PAWN_ID_AND_N_PLAYER_1).dicesAlreadyRolled());
    //
    // //Tests dices and GoToPrison
    // final Player testPlayer = version.getNextPlayer();
    // assertEquals(version.toRollDices().size(), N_OF_ITAL_DICES);
    // final List<Integer> dices = new LinkedList<>();
    // dices.add(3);
    // dices.add(3);
    // dices.add(3);
    // testPlayer.setLastDicesNumber(dices);
    // final Box prison =
    // version.getAllBoxes().get(BoxesPositions.PRISON_POSITION.getPos());
    // new ItalianDicesStrategy(prison).nowPlay(testPlayer);
    // assertTrue(testPlayer.isInPrison());
    // final int pos = testPlayer.getPawn().getActualPos();
    // assertEquals(pos, BoxesPositions.PRISON_POSITION.getPos());
    // assertEquals(pos, 10);
    // dices.clear();
    // dices.add(2);
    // dices.add(2);
    // dices.add(4);
    // testPlayer.setLastDicesNumber(dices);
    // new ItalianDicesStrategy(prison).nowPlay(testPlayer);
    // assertFalse(testPlayer.isInPrison());
    // assertEquals(testPlayer.getPawn().getActualPos(), pos +
    // (testPlayer.lastDicesNumber().get(0) +
    // testPlayer.lastDicesNumber().get(1) +
    // testPlayer.lastDicesNumber().get(2)));
    // assertEquals(pos, testPlayer.getPawn().getPreviousPos());
    // version.endOfTurnAndNextPlayer();
    // assertFalse(testPlayer.dicesAlreadyRolled());
    //
    // testPlayer.setDicesRoll(true);
    // new ItalianDicesStrategy(prison).nowPlay(testPlayer);
    // assertFalse(testPlayer.dicesAlreadyRolled());
    // assertEquals(testPlayer.getPawn().getPreviousPos() +
    // testPlayer.lastDicesNumber().get(0) + testPlayer.lastDicesNumber().get(1)
    // + testPlayer.lastDicesNumber().get(2),
    // testPlayer.getPawn().getActualPos());
    //
    // //Tests EvadeTaxes and ToStealMoney
    // final int money = 40;
    // final int amount = testPlayer.getMoney();
    // final int tax = ((int) Math.floor(money * 0.78));
    // final ItalianNeutralArea neutra = ((ItalianNeutralArea)
    // version.getAllBoxes().get(BoxesPositions.NEUTRAL_AREA_POSITION.getPos()));
    // new EvadeTaxes(money, neutra).play(testPlayer);
    // assertEquals(testPlayer.getMoney(), amount - tax, 1);
    // final int neutralMoney = neutra.getDirtyMoney();
    // assertEquals(neutralMoney, tax, 1);
    // new ToStealMoney(neutra).play(testPlayer);
    // assertEquals(testPlayer.getMoney(), amount, 1);
    // assertEquals(neutra.getDirtyMoney(), 0);
    //
    // }

}
