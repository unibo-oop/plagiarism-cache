package it.unibo.monopoli.model.mainunits;

import java.awt.Color;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import it.unibo.monopoli.model.actions.ClassicDicesStrategy;
import it.unibo.monopoli.model.actions.GoToPrison;
import it.unibo.monopoli.model.actions.MoveUpTo;
import it.unibo.monopoli.model.actions.ToBePaid;
import it.unibo.monopoli.model.actions.ToMortgage;
import it.unibo.monopoli.model.actions.ToPay;
import it.unibo.monopoli.model.actions.ToRollDices;
import it.unibo.monopoli.model.actions.ToSellProperties;
import it.unibo.monopoli.model.cards.Card;
import it.unibo.monopoli.model.cards.ChanceCards;
import it.unibo.monopoli.model.cards.ClassicCard;
import it.unibo.monopoli.model.cards.ClassicDeck;
import it.unibo.monopoli.model.cards.ClassicDecks;
import it.unibo.monopoli.model.cards.CommunityChestCards;
import it.unibo.monopoli.model.cards.Deck;
import it.unibo.monopoli.model.table.Box;
import it.unibo.monopoli.model.table.Building;
import it.unibo.monopoli.model.table.ClassicContract;
import it.unibo.monopoli.model.table.ClassicLand;
import it.unibo.monopoli.model.table.ClassicLandContract;
import it.unibo.monopoli.model.table.ClassicLandGroup;
import it.unibo.monopoli.model.table.Company;
import it.unibo.monopoli.model.table.Contract;
import it.unibo.monopoli.model.table.DecksBox;
import it.unibo.monopoli.model.table.Group;
import it.unibo.monopoli.model.table.Home;
import it.unibo.monopoli.model.table.Hotel;
import it.unibo.monopoli.model.table.Land;
import it.unibo.monopoli.model.table.LandGroup;
import it.unibo.monopoli.model.table.NeutralArea;
import it.unibo.monopoli.model.table.Ownership;
import it.unibo.monopoli.model.table.Police;
import it.unibo.monopoli.model.table.PrisonOrTransit;
import it.unibo.monopoli.model.table.Start;
import it.unibo.monopoli.model.table.Station;
import it.unibo.monopoli.model.table.StationIncomeStrategy;
import it.unibo.monopoli.model.table.TaxImpl;

/**
 * This is an implementation of {@link GameStrategy} for initialize the game
 * with the classic version of Monopoly.
 *
 */
public class ClassicStrategy implements GameStrategy {

    private static final int N_MAX_OF_HOUSES = 32;
    private static final int N_MAX_OF_HOTELS = 12;
    private static final int AMOUNT_OF_FEES = 150;

    private static final int MIN_PLAYERS = 2;
    private static final int MAX_PLAYERS = 6;
    private static final int CALCULATE_OWNERSHIP = 9;
    private static final int CALCULATE_MONEY = 50;

    private static final int CARD2 = 2;
    private static final int CARD3 = 3;
    private static final int CARD4 = 4;
    private static final int CARD5 = 5;
    private static final int CARD7 = 7;
    private static final int CARD8 = 8;
    private static final int CARD9 = 9;
    private static final int CARD10 = 10;
    private static final int CARD11 = 11;
    private static final int CARD12 = 12;
    private static final int CARD13 = 13;
    private static final int CARD14 = 14;
    private static final int CARD15 = 15;
    private static final int CARD17 = 17;
    private static final int CARD18 = 18;
    private static final int CARD20 = 20;
    private static final int CARD21 = 21;
    private static final int CARD23 = 23;
    private static final int CARD24 = 24;
    private static final int CARD25 = 25;
    private static final int CARD26 = 26;

    private static final int CHANCE2_STEPS = 37;
    private static final int CHANCE5_PAY = 20;
    private static final int CHANCE8_HOME = 25;
    private static final int CHANCE8_HOTEL = 100;
    private static final int CHANCE9_TAX = 50;
    private static final int CHEST1_2_PAY = 50;
    private static final int CHEST8_HOME = 40;
    private static final int CHEST8_HOTEL = 115;

    private final List<Player> players;
    private final List<Ownership> ownerships;
    private final List<Group> groups;
    private final List<Contract> contracts;
    private final List<Home> homes;
    private final List<Hotel> hotels;
    private List<Box> allBoxes;
    private final List<Deck> decks;
    private final Bank bank;

    /**
     * Constructs a new instance of {@link ClassicStrategy} that needs all the
     * {@link Player}s to be initialized.
     * 
     * @param players
     *            - a {@link List} of all the current {@link Player}s
     */
    public ClassicStrategy(final List<Player> players) {
        this.homes = new LinkedList<>();
        this.hotels = new LinkedList<>();
        this.inizializesBuildings();
        this.bank = new ClassicBank(this.homes, this.hotels);
        this.ownerships = new LinkedList<>();
        this.inizializesOwnerships();
        this.bank.setOwnerships(this.ownerships);
        this.groups = new LinkedList<>();
        this.inizializesGroups();
        this.contracts = new LinkedList<>();
        this.inizializesContracts();
        this.players = players;
        this.inizializesPlayers(players);
        this.decks = new LinkedList<>();
        this.inizializesDecks();
        this.allBoxes = new LinkedList<>();
        this.inizializesBoxes();
    }

    private void inizializesOwnerships() {
        this.ownerships.add(new ClassicLand("OLD KENT ROAD", BoxesPositions.OWNERSHIP1_POSITION.getPos(), this.bank,
                Color.DARK_GRAY));
        this.ownerships.add(new ClassicLand("WHITECHAPEL ROAD", BoxesPositions.OWNERSHIP2_POSITION.getPos(), this.bank,
                Color.DARK_GRAY));
        this.ownerships.add(new Station("KINGS CROSS STATION", BoxesPositions.OWNERSHIP3_POSITION.getPos(), this.bank));
        this.ownerships.add(new ClassicLand("THE ANGEL, ISLINGTON", BoxesPositions.OWNERSHIP4_POSITION.getPos(),
                this.bank, Color.CYAN));
        this.ownerships.add(
                new ClassicLand("EUSTON ROAD", BoxesPositions.OWNERSHIP5_POSITION.getPos(), this.bank, Color.CYAN));
        this.ownerships.add(new ClassicLand("PENTONVILLE ROAD", BoxesPositions.OWNERSHIP6_POSITION.getPos(), this.bank,
                Color.CYAN));
        this.ownerships.add(
                new ClassicLand("PALL MALL", BoxesPositions.OWNERSHIP7_POSITION.getPos(), this.bank, Color.MAGENTA));
        this.ownerships.add(new Company("ELETTRIC COMPANY", BoxesPositions.OWNERSHIP8_POSITION.getPos(), this.bank));
        this.ownerships.add(
                new ClassicLand("WHITEHALL", BoxesPositions.OWNERSHIP9_POSITION.getPos(), this.bank, Color.MAGENTA));
        this.ownerships.add(new ClassicLand("NORTHUMRL'D AVENUE", BoxesPositions.OWNERSHIP10_POSITION.getPos(),
                this.bank, Color.MAGENTA));
        this.ownerships.add(new Station("MARYLEBONE STATION", BoxesPositions.OWNERSHIP11_POSITION.getPos(), this.bank));
        this.ownerships.add(
                new ClassicLand("BOW STREET", BoxesPositions.OWNERSHIP12_POSITION.getPos(), this.bank, Color.ORANGE));
        this.ownerships.add(new ClassicLand("MARLBOROUGH STREET", BoxesPositions.OWNERSHIP13_POSITION.getPos(),
                this.bank, Color.ORANGE));
        this.ownerships.add(
                new ClassicLand("VINE STREET", BoxesPositions.OWNERSHIP14_POSITION.getPos(), this.bank, Color.ORANGE));
        this.ownerships
                .add(new ClassicLand("STRAND", BoxesPositions.OWNERSHIP15_POSITION.getPos(), this.bank, Color.RED));
        this.ownerships.add(
                new ClassicLand("FLEET STREET", BoxesPositions.OWNERSHIP16_POSITION.getPos(), this.bank, Color.RED));
        this.ownerships.add(new ClassicLand("TRAFALGAR SQUARE", BoxesPositions.OWNERSHIP17_POSITION.getPos(), this.bank,
                Color.RED));
        this.ownerships
                .add(new Station("FENCHURCH ST. STATION", BoxesPositions.OWNERSHIP18_POSITION.getPos(), this.bank));
        this.ownerships.add(new ClassicLand("LEICESTER SQUARE", BoxesPositions.OWNERSHIP19_POSITION.getPos(), this.bank,
                Color.YELLOW));
        this.ownerships.add(new ClassicLand("COVENTRY STREET", BoxesPositions.OWNERSHIP20_POSITION.getPos(), this.bank,
                Color.YELLOW));
        this.ownerships.add(new Company("WATER COMPANY", BoxesPositions.OWNERSHIP21_POSITION.getPos(), this.bank));
        this.ownerships.add(
                new ClassicLand("PICCADILLY", BoxesPositions.OWNERSHIP22_POSITION.getPos(), this.bank, Color.YELLOW));
        this.ownerships.add(
                new ClassicLand("REGENT STREET", BoxesPositions.OWNERSHIP23_POSITION.getPos(), this.bank, Color.GREEN));
        this.ownerships.add(
                new ClassicLand("OXFORD STREET", BoxesPositions.OWNERSHIP24_POSITION.getPos(), this.bank, Color.GREEN));
        this.ownerships.add(
                new ClassicLand("BOND STREET", BoxesPositions.OWNERSHIP25_POSITION.getPos(), this.bank, Color.GREEN));
        this.ownerships
                .add(new Station("LIVERPOOL ST. STATION", BoxesPositions.OWNERSHIP26_POSITION.getPos(), this.bank));
        this.ownerships
                .add(new ClassicLand("PARK LANE", BoxesPositions.OWNERSHIP27_POSITION.getPos(), this.bank, Color.BLUE));
        this.ownerships
                .add(new ClassicLand("MAYFAIR", BoxesPositions.OWNERSHIP28_POSITION.getPos(), this.bank, Color.BLUE));
    }

    private void inizializesGroups() {
        this.groups.add(new ClassicLandGroup("Dark Gray", this.ownerships.get(BoxesPositions.INDEX_0.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_1.getPos())));
        this.ownerships.get(BoxesPositions.INDEX_0.getPos()).setGroup(this.groups.get(BoxesPositions.GROUP_0.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_1.getPos()).setGroup(this.groups.get(BoxesPositions.GROUP_0.getPos()));
        this.groups.add(new ClassicLandGroup("Cyan", this.ownerships.get(BoxesPositions.INDEX_3.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_4.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_5.getPos())));
        this.ownerships.get(BoxesPositions.INDEX_3.getPos()).setGroup(this.groups.get(BoxesPositions.GROUP_1.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_4.getPos()).setGroup(this.groups.get(BoxesPositions.GROUP_1.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_5.getPos()).setGroup(this.groups.get(BoxesPositions.GROUP_1.getPos()));
        this.groups.add(new ClassicLandGroup("Magenta", this.ownerships.get(BoxesPositions.INDEX_6.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_8.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_9.getPos())));
        this.ownerships.get(BoxesPositions.INDEX_6.getPos()).setGroup(this.groups.get(BoxesPositions.GROUP_2.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_8.getPos()).setGroup(this.groups.get(BoxesPositions.GROUP_2.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_9.getPos()).setGroup(this.groups.get(BoxesPositions.GROUP_2.getPos()));
        this.groups.add(new ClassicLandGroup("Orange", this.ownerships.get(BoxesPositions.INDEX_11.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_12.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_13.getPos())));
        this.ownerships.get(BoxesPositions.INDEX_11.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_3.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_12.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_3.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_13.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_3.getPos()));
        this.groups.add(new ClassicLandGroup("Red", this.ownerships.get(BoxesPositions.INDEX_14.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_15.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_16.getPos())));
        this.ownerships.get(BoxesPositions.INDEX_14.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_4.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_15.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_4.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_16.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_4.getPos()));
        this.groups.add(new ClassicLandGroup("Yellow", this.ownerships.get(BoxesPositions.INDEX_18.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_19.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_21.getPos())));
        this.ownerships.get(BoxesPositions.INDEX_18.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_5.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_19.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_5.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_21.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_5.getPos()));
        this.groups.add(new ClassicLandGroup("Green", this.ownerships.get(BoxesPositions.INDEX_22.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_23.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_24.getPos())));
        this.ownerships.get(BoxesPositions.INDEX_22.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_6.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_23.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_6.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_24.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_6.getPos()));
        this.groups.add(new ClassicLandGroup("Blue", this.ownerships.get(BoxesPositions.INDEX_26.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_27.getPos())));
        this.ownerships.get(BoxesPositions.INDEX_26.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_7.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_27.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_7.getPos()));
        this.groups.add(new ClassicLandGroup("Stations", this.ownerships.get(BoxesPositions.INDEX_2.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_10.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_17.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_25.getPos())));
        this.ownerships.get(BoxesPositions.INDEX_2.getPos()).setGroup(this.groups.get(BoxesPositions.GROUP_8.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_10.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_8.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_17.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_8.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_25.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_8.getPos()));
        this.groups.add(new ClassicLandGroup("Companies", this.ownerships.get(BoxesPositions.INDEX_7.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_20.getPos())));
        this.ownerships.get(BoxesPositions.INDEX_7.getPos()).setGroup(this.groups.get(BoxesPositions.GROUP_9.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_20.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_9.getPos()));
    }

    private void inizializesContracts() {
        this.contracts.add(new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_0.getPos()))
                .landCost(Ownerships.OWNERSHIP1.getCost()).landIncome(Ownerships.OWNERSHIP1.getIncome().get())
                .buildingCost(Ownerships.OWNERSHIP1.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_0.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_0.getPos()));
        this.contracts.add(new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_1.getPos()))
                .landCost(Ownerships.OWNERSHIP2.getCost()).landIncome(Ownerships.OWNERSHIP2.getIncome().get())
                .buildingCost(Ownerships.OWNERSHIP2.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_1.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_1.getPos()));
        this.contracts.add(new ClassicContract(this.ownerships.get(BoxesPositions.INDEX_2.getPos()),
                Ownerships.OWNERSHIP3.getCost()));
        this.ownerships.get(BoxesPositions.INDEX_2.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_2.getPos()));
        this.contracts.add(new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_3.getPos()))
                .landCost(Ownerships.OWNERSHIP4.getCost()).landIncome(Ownerships.OWNERSHIP4.getIncome().get())
                .buildingCost(Ownerships.OWNERSHIP4.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_3.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_3.getPos()));
        this.contracts.add(new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_4.getPos()))
                .landCost(Ownerships.OWNERSHIP5.getCost()).landIncome(Ownerships.OWNERSHIP5.getIncome().get())
                .buildingCost(Ownerships.OWNERSHIP5.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_4.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_4.getPos()));
        this.contracts.add(new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_5.getPos()))
                .landCost(Ownerships.OWNERSHIP6.getCost()).landIncome(Ownerships.OWNERSHIP6.getIncome().get())
                .buildingCost(Ownerships.OWNERSHIP6.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_5.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_5.getPos()));
        this.contracts.add(new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_6.getPos()))
                .landCost(Ownerships.OWNERSHIP7.getCost()).landIncome(Ownerships.OWNERSHIP7.getIncome().get())
                .buildingCost(Ownerships.OWNERSHIP7.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_6.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_6.getPos()));
        this.contracts.add(new ClassicContract(this.ownerships.get(BoxesPositions.INDEX_7.getPos()),
                Ownerships.OWNERSHIP8.getCost()));
        this.ownerships.get(BoxesPositions.INDEX_7.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_7.getPos()));
        this.contracts.add(new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_8.getPos()))
                .landCost(Ownerships.OWNERSHIP9.getCost()).landIncome(Ownerships.OWNERSHIP9.getIncome().get())
                .buildingCost(Ownerships.OWNERSHIP9.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_8.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_8.getPos()));
        this.contracts.add(new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_9.getPos()))
                .landCost(Ownerships.OWNERSHIP10.getCost()).landIncome(Ownerships.OWNERSHIP10.getIncome().get())
                .buildingCost(Ownerships.OWNERSHIP10.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_9.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_9.getPos()));
        this.contracts.add(new ClassicContract(this.ownerships.get(BoxesPositions.INDEX_10.getPos()),
                Ownerships.OWNERSHIP11.getCost()));
        this.ownerships.get(BoxesPositions.INDEX_10.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_10.getPos()));
        this.contracts.add(new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_11.getPos()))
                .landCost(Ownerships.OWNERSHIP12.getCost()).landIncome(Ownerships.OWNERSHIP12.getIncome().get())
                .buildingCost(Ownerships.OWNERSHIP12.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_11.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_11.getPos()));
        this.contracts.add(new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_12.getPos()))
                .landCost(Ownerships.OWNERSHIP13.getCost()).landIncome(Ownerships.OWNERSHIP13.getIncome().get())
                .buildingCost(Ownerships.OWNERSHIP13.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_12.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_12.getPos()));
        this.contracts.add(new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_13.getPos()))
                .landCost(Ownerships.OWNERSHIP14.getCost()).landIncome(Ownerships.OWNERSHIP14.getIncome().get())
                .buildingCost(Ownerships.OWNERSHIP14.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_13.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_13.getPos()));
        this.contracts.add(new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_14.getPos()))
                .landCost(Ownerships.OWNERSHIP15.getCost()).landIncome(Ownerships.OWNERSHIP15.getIncome().get())
                .buildingCost(Ownerships.OWNERSHIP15.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_14.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_14.getPos()));
        this.contracts.add(new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_15.getPos()))
                .landCost(Ownerships.OWNERSHIP16.getCost()).landIncome(Ownerships.OWNERSHIP16.getIncome().get())
                .buildingCost(Ownerships.OWNERSHIP16.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_15.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_15.getPos()));
        this.contracts.add(new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_16.getPos()))
                .landCost(Ownerships.OWNERSHIP17.getCost()).landIncome(Ownerships.OWNERSHIP17.getIncome().get())
                .buildingCost(Ownerships.OWNERSHIP17.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_16.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_16.getPos()));
        this.contracts.add(new ClassicContract(this.ownerships.get(BoxesPositions.INDEX_17.getPos()),
                Ownerships.OWNERSHIP18.getCost()));
        this.ownerships.get(BoxesPositions.INDEX_17.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_17.getPos()));
        this.contracts.add(new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_18.getPos()))
                .landCost(Ownerships.OWNERSHIP19.getCost()).landIncome(Ownerships.OWNERSHIP19.getIncome().get())
                .buildingCost(Ownerships.OWNERSHIP19.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_18.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_18.getPos()));
        this.contracts.add(new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_19.getPos()))
                .landCost(Ownerships.OWNERSHIP20.getCost()).landIncome(Ownerships.OWNERSHIP20.getIncome().get())
                .buildingCost(Ownerships.OWNERSHIP20.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_19.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_19.getPos()));
        this.contracts.add(new ClassicContract(this.ownerships.get(BoxesPositions.INDEX_20.getPos()),
                Ownerships.OWNERSHIP21.getCost()));
        this.ownerships.get(BoxesPositions.INDEX_20.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_20.getPos()));
        this.contracts.add(new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_21.getPos()))
                .landCost(Ownerships.OWNERSHIP22.getCost()).landIncome(Ownerships.OWNERSHIP22.getIncome().get())
                .buildingCost(Ownerships.OWNERSHIP22.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_21.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_21.getPos()));
        this.contracts.add(new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_22.getPos()))
                .landCost(Ownerships.OWNERSHIP23.getCost()).landIncome(Ownerships.OWNERSHIP23.getIncome().get())
                .buildingCost(Ownerships.OWNERSHIP23.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_22.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_22.getPos()));
        this.contracts.add(new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_23.getPos()))
                .landCost(Ownerships.OWNERSHIP24.getCost()).landIncome(Ownerships.OWNERSHIP24.getIncome().get())
                .buildingCost(Ownerships.OWNERSHIP24.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_23.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_23.getPos()));
        this.contracts.add(new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_24.getPos()))
                .landCost(Ownerships.OWNERSHIP25.getCost()).landIncome(Ownerships.OWNERSHIP25.getIncome().get())
                .buildingCost(Ownerships.OWNERSHIP25.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_24.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_24.getPos()));
        this.contracts.add(new ClassicContract(this.ownerships.get(BoxesPositions.INDEX_25.getPos()),
                Ownerships.OWNERSHIP26.getCost()));
        this.ownerships.get(BoxesPositions.INDEX_25.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_25.getPos()));
        this.contracts.add(new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_26.getPos()))
                .landCost(Ownerships.OWNERSHIP27.getCost()).landIncome(Ownerships.OWNERSHIP27.getIncome().get())
                .buildingCost(Ownerships.OWNERSHIP27.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_26.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_26.getPos()));
        this.contracts.add(new ClassicLandContract.Builder().land(this.ownerships.get(BoxesPositions.INDEX_27.getPos()))
                .landCost(Ownerships.OWNERSHIP28.getCost()).landIncome(Ownerships.OWNERSHIP28.getIncome().get())
                .buildingCost(Ownerships.OWNERSHIP28.getBuildingCost().get()).build());
        this.ownerships.get(BoxesPositions.INDEX_27.getPos())
                .setContract(this.contracts.get(BoxesPositions.INDEX_27.getPos()));
    }

    private void inizializesBuildings() {
        for (int i = 0; i < N_MAX_OF_HOUSES; i++) {
            this.homes.add(new Home());
        }
        for (int i = 0; i < N_MAX_OF_HOTELS; i++) {
            this.hotels.add(new Hotel());
        }
    }

    private void inizializesPlayers(final List<Player> players) {
        if (players.size() < MIN_PLAYERS || players.size() > MAX_PLAYERS) {
            throw new IllegalArgumentException("Minimum players: 2. Maximum players: 6");
        }
        players.stream().forEach(p -> {
            final int i = (CALCULATE_OWNERSHIP - players.size());
            this.addOwnerships(i, p);
            p.setMoney(i * CALCULATE_MONEY);
        });
    }

    private void addOwnerships(final int nOfOwnership, final Player player) {
        for (int i = 0; i < nOfOwnership; i++) {
            final Ownership ow = this.bank.getOwnership();
            player.addOwnership(ow);
            ow.setOwner(player);
        }
    }

    private void inizializesDecks() {
        final List<Card> chance = new LinkedList<>();
        chance.add(new ClassicCard(ChanceCards.CARD1.getDescription(), ChanceCards.CARD1.getID(),
                ChanceCards.CARD1.getActions()));
        chance.add(new ClassicCard(ChanceCards.CARD2.getDescription(), ChanceCards.CARD2.getID(),
                ChanceCards.CARD2.getActions()));
        chance.add(new ClassicCard(ChanceCards.CARD3.getDescription(), ChanceCards.CARD3.getID(),
                ChanceCards.CARD3.getActions()));
        chance.add(new ClassicCard(ChanceCards.CARD4.getDescription(), ChanceCards.CARD4.getID(),
                ChanceCards.CARD4.getActions()));
        chance.add(new ClassicCard(ChanceCards.CARD5.getDescription(), ChanceCards.CARD5.getID(),
                ChanceCards.CARD5.getActions()));
        chance.add(new ClassicCard(ChanceCards.CARD6.getDescription(), ChanceCards.CARD6.getID(),
                ChanceCards.CARD6.getActions()));
        chance.add(new ClassicCard(ChanceCards.CARD7.getDescription(), ChanceCards.CARD7.getID(),
                ChanceCards.CARD7.getActions()));
        chance.add(new ClassicCard(ChanceCards.CARD8.getDescription(), ChanceCards.CARD8.getID(),
                ChanceCards.CARD8.getActions()));
        chance.add(new ClassicCard(ChanceCards.CARD9.getDescription(), ChanceCards.CARD9.getID(),
                ChanceCards.CARD9.getActions()));
        chance.add(new ClassicCard(ChanceCards.CARD10.getDescription(), ChanceCards.CARD10.getID(),
                ChanceCards.CARD10.getActions()));
        chance.add(new ClassicCard(ChanceCards.CARD11.getDescription(), ChanceCards.CARD11.getID(),
                ChanceCards.CARD11.getActions()));
        chance.add(new ClassicCard(ChanceCards.CARD12.getDescription(), ChanceCards.CARD12.getID(),
                ChanceCards.CARD12.getActions()));
        chance.add(new ClassicCard(ChanceCards.CARD13.getDescription(), ChanceCards.CARD13.getID(),
                ChanceCards.CARD13.getActions()));
        chance.add(new ClassicCard(ChanceCards.CARD14.getDescription(), ChanceCards.CARD14.getID(),
                ChanceCards.CARD14.getActions()));
        chance.add(new ClassicCard(ChanceCards.CARD15.getDescription(), ChanceCards.CARD15.getID(),
                ChanceCards.CARD15.getActions()));
        chance.add(new ClassicCard(ChanceCards.CARD16.getDescription(), ChanceCards.CARD16.getID(),
                ChanceCards.CARD16.getActions()));
        this.decks.add(new ClassicDeck(ClassicDecks.CHANCE.getName(), chance));
        final List<Card> chest = new LinkedList<>();
        chest.add(new ClassicCard(CommunityChestCards.CARD1.getDescription(), CommunityChestCards.CARD1.getID(),
                CommunityChestCards.CARD1.getActions()));
        chest.add(new ClassicCard(CommunityChestCards.CARD2.getDescription(), CommunityChestCards.CARD2.getID(),
                CommunityChestCards.CARD2.getActions()));
        chest.add(new ClassicCard(CommunityChestCards.CARD3.getDescription(), CommunityChestCards.CARD3.getID(),
                CommunityChestCards.CARD3.getActions()));
        chest.add(new ClassicCard(CommunityChestCards.CARD4.getDescription(), CommunityChestCards.CARD4.getID(),
                CommunityChestCards.CARD4.getActions()));
        chest.add(new ClassicCard(CommunityChestCards.CARD5.getDescription(), CommunityChestCards.CARD5.getID(),
                CommunityChestCards.CARD5.getActions()));
        chest.add(new ClassicCard(CommunityChestCards.CARD6.getDescription(), CommunityChestCards.CARD6.getID(),
                CommunityChestCards.CARD6.getActions()));
        chest.add(new ClassicCard(CommunityChestCards.CARD7.getDescription(), CommunityChestCards.CARD7.getID(),
                CommunityChestCards.CARD7.getActions()));
        chest.add(new ClassicCard(CommunityChestCards.CARD8.getDescription(), CommunityChestCards.CARD8.getID(),
                CommunityChestCards.CARD8.getActions()));
        chest.add(new ClassicCard(CommunityChestCards.CARD9.getDescription(), CommunityChestCards.CARD9.getID(),
                CommunityChestCards.CARD9.getActions()));
        chest.add(new ClassicCard(CommunityChestCards.CARD10.getDescription(), CommunityChestCards.CARD10.getID(),
                CommunityChestCards.CARD10.getActions()));
        chest.add(new ClassicCard(CommunityChestCards.CARD11.getDescription(), CommunityChestCards.CARD11.getID(),
                CommunityChestCards.CARD11.getActions()));
        chest.add(new ClassicCard(CommunityChestCards.CARD12.getDescription(), CommunityChestCards.CARD12.getID(),
                CommunityChestCards.CARD12.getActions()));
        chest.add(new ClassicCard(CommunityChestCards.CARD13.getDescription(), CommunityChestCards.CARD13.getID(),
                CommunityChestCards.CARD13.getActions()));
        chest.add(new ClassicCard(CommunityChestCards.CARD14.getDescription(), CommunityChestCards.CARD14.getID(),
                CommunityChestCards.CARD14.getActions()));
        chest.add(new ClassicCard(CommunityChestCards.CARD15.getDescription(), CommunityChestCards.CARD15.getID(),
                CommunityChestCards.CARD15.getActions()));
        chest.add(new ClassicCard(CommunityChestCards.CARD16.getDescription(), CommunityChestCards.CARD16.getID(),
                CommunityChestCards.CARD16.getActions()));
        this.decks.add(new ClassicDeck(ClassicDecks.COMMUNITY_CHEST.getName(), chest));
    }

    private void inizializesBoxes() {
        final List<Box> temp = new LinkedList<>();
        temp.add(new Start("GO", BoxesPositions.START_POSITION.getPos()));
        final PrisonOrTransit prison = new PrisonOrTransit("IN JAIL OR JUST VISITING",
                BoxesPositions.PRISON_POSITION.getPos());
        temp.add(prison);
        temp.add(new NeutralArea("FREE PARKING", BoxesPositions.NEUTRAL_AREA_POSITION.getPos()));
        temp.add(new Police("GO TO JAIL", BoxesPositions.POLICE_POSITION.getPos()));
        temp.add(new DecksBox("CHANCE", BoxesPositions.FIRST_CHANCE_POSITION.getPos()));
        temp.add(new DecksBox("CHANCE", BoxesPositions.SECOND_CHANCE_POSITION.getPos()));
        temp.add(new DecksBox("CHANCE", BoxesPositions.THIRD_CHANCE_POSITION.getPos()));
        temp.add(new DecksBox("COMMUNITY CHEST", BoxesPositions.FIRST_COMMUNITY_CHEST_POSITION.getPos()));
        temp.add(new DecksBox("COMMUNITY CHEST", BoxesPositions.SECOND_COMMUNITY_CHEST_POSITION.getPos()));
        temp.add(new DecksBox("COMMUNITY CHEST", BoxesPositions.THIRD_COMMUNITY_CHEST_POSITION.getPos()));
        temp.add(new TaxImpl("INCOME TAX", BoxesPositions.INCOME_TAX_POSITION.getPos(), AMOUNT_OF_FEES));
        temp.add(new TaxImpl("SUPER TAX", BoxesPositions.SUPER_TAX_POSITION.getPos(), AMOUNT_OF_FEES));
        temp.addAll(this.ownerships);
        this.allBoxes = temp.stream().sorted(new Comparator<Box>() {

            @Override
            public int compare(final Box o1, final Box o2) {
                return o1.getID() - o2.getID();
            }
        }).collect(Collectors.toList());
    }

    @Override
    public List<Player> getPlayers() {
        return this.players;
    }

    @Override
    public Bank getBank() {
        return this.bank;
    }

    @Override
    public List<Box> getBoxes() {
        return Collections.unmodifiableList(this.allBoxes);
    }

    @Override
    public List<Deck> getDecks() {
        return Collections.unmodifiableList(this.decks);
    }

    @Override
    public List<Integer> toRollDices(final Player player) {
        new ToRollDices(new ClassicDicesStrategy(), this.getBoxes().get(BoxesPositions.PRISON_POSITION.getPos()))
                .play(player);
        return player.lastDicesNumber();
    }

    @Override
    public boolean getNextCardsActions(final Box box, final Card card, final Player player) {
        switch (card.getID()) {
        case CARD2:
            MoveUpTo.moveUpToBox(this.allBoxes.get(CHANCE2_STEPS)).play(player);
            break;
        case CARD3:
            if (!((Ownership) box).getOwner().equals(player) && !((Ownership) box).getOwner().equals(this.bank)) {
                final int amount = 2
                        * ((Ownership) box).getContract().getIncome(new StationIncomeStrategy(((Ownership) box)));
                new ToBePaid(amount).play(player); // IL GIOCATORE DEVE ESSERE
                                                   // COMUNQUE PAGATO
                if (!this.tryToPay(player, amount)) {
                    return true;
                }
            }
            break;
        case CARD4:
            player.addCard(card);
            break;
        case CARD5:
            if (!this.tryToPay(player, CHANCE5_PAY)) {
                return true;
            }
            break;
        case CARD7:
            new GoToPrison(this.allBoxes.get(BoxesPositions.PRISON_POSITION.getPos())).play(player);
            break;
        case CARD8:
            final List<List<Building>> building = player.getOwnerships().stream().filter(o -> o instanceof Land)
                    .filter(o -> !((LandGroup) o.getGroup()).getBuildings().isEmpty())
                    .map(o -> ((LandGroup) o.getGroup()).getBuildings()).collect(Collectors.toList());
            for (final List<Building> l : building) {
                for (final Building b : l) {
                    if (!this.tryToPay(player, b instanceof Home ? CHANCE8_HOME : CHANCE8_HOTEL)) {
                        return true;
                    }
                }
            }
            break;
        case CARD9:
            for (final Player p : this.players) {
                if (!p.equals(player)) {
                    new ToBePaid(CHANCE9_TAX).play(player);
                    if (!this.tryToPay(p, CHANCE9_TAX)) {
                        return true;
                    }
                }
            }
            break;
        case CARD10:
            MoveUpTo.moveUpToBox(this.allBoxes.get(BoxesPositions.START_POSITION.getPos())).play(player);
            break;
        case CARD11:
            MoveUpTo.moveUpToBox(this.allBoxes.get(BoxesPositions.OWNERSHIP18_POSITION.getPos())).play(player);
            break;
        case CARD12:
            if (!((Ownership) box).getOwner().equals(player) && !((Ownership) box).getOwner().equals(this.bank)) {
                final int amount = 2
                        * ((Ownership) box).getContract().getIncome(new StationIncomeStrategy(((Ownership) box)));
                new ToBePaid(amount).play(player);
                if (!this.tryToPay(player, amount)) {
                    return true;
                }
            }
            break;
        case CARD13:
            MoveUpTo.moveUpToBox(this.allBoxes.get(BoxesPositions.OWNERSHIP17_POSITION.getPos())).play(player);
            break;
        case CARD14:
            MoveUpTo.moveUpToBox(this.allBoxes.get(BoxesPositions.OWNERSHIP9_POSITION.getPos())).play(player);
            break;
        case CARD15:
            if (!((Ownership) box).getOwner().equals(player) && !((Ownership) box).getOwner().equals(this.bank)) {
                final int amount = (player.lastDicesNumber().stream().reduce((d, d1) -> d + d1).get() * 10);
                new ToBePaid(amount).play(player);
                if (!this.tryToPay(player, amount)) {
                    return true;
                }
            }
            break;
        case CARD17:
            if (!this.tryToPay(player, CHEST1_2_PAY)) {
                return true;
            }
            break;
        case CARD18:
            if (!this.tryToPay(player, CHEST1_2_PAY)) {
                return true;
            }
            break;
        case CARD20:
            player.addCard(card);
            break;
        case CARD21:
            if (!this.tryToPay(player, 100)) {
                return true;
            }
            break;
        case CARD23:
            new GoToPrison(this.allBoxes.get(BoxesPositions.PRISON_POSITION.getPos())).play(player);
            break;
        case CARD24:
            final List<List<Building>> buildings = player.getOwnerships().stream().filter(o -> o instanceof Land)
                    .filter(o -> !((LandGroup) o.getGroup()).getBuildings().isEmpty())
                    .map(o -> ((LandGroup) o.getGroup()).getBuildings()).collect(Collectors.toList());
            for (final List<Building> l : buildings) {
                for (final Building b : l) {
                    if (!this.tryToPay(player, b instanceof Home ? CHEST8_HOME : CHEST8_HOTEL)) {
                        return true;
                    }
                }
            }
            break;
        case CARD25:
            for (final Player p : this.players) {
                if (!p.equals(player)) {
                    new ToBePaid(10).play(player);
                    if (!this.tryToPay(p, 10)) {
                        return true;
                    }
                }
            }
            break;
        case CARD26:
            MoveUpTo.moveUpToBox(this.allBoxes.get(BoxesPositions.START_POSITION.getPos())).play(player);
            break;
        default:
            return false;
        }
        return false;
    }

    @Override
    public boolean haveEnoughMoney(final Player player, final int moneyToPay) {
        if (!player.getOwnerships().isEmpty()) {
            player.getOwnerships().stream().filter(o -> o.getGroup() instanceof LandGroup)
                    .filter(o -> ((LandGroup) o.getGroup()).getBuildings().size() > 0).forEach(o -> {
                        ((LandGroup) o.getGroup()).getBuildings().forEach(b -> {
                            if (player.getMoney() <= moneyToPay) {
                                ToSellProperties.sellABuilding(((Land) o), b, this.bank).play(player);
                            }
                        });
                    });
            player.getOwnerships().stream().forEach(o -> {
                if (player.getMoney() <= moneyToPay && !o.isMortgaged()) {
                    new ToMortgage(o).play(player);
                }
            });
        }
        return player.getMoney() > moneyToPay;
    }

    private boolean tryToPay(final Player player, final int amount) {
        try {
            new ToPay(amount).play(player);
            return true;
        } catch (IllegalArgumentException i) {
            return this.haveEnoughMoney(player, amount) ? this.tryToPay(player, amount) : false;
        }
    }

}
