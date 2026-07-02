package it.unibo.monopoli.model.mainunits;

import java.awt.Color;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import it.unibo.monopoli.model.actions.EvadeTaxes;
import it.unibo.monopoli.model.actions.GoToPrison;
import it.unibo.monopoli.model.actions.ItalianDicesStrategy;
import it.unibo.monopoli.model.actions.MoveUpTo;
import it.unibo.monopoli.model.actions.ToBePaid;
import it.unibo.monopoli.model.actions.ToMortgage;
import it.unibo.monopoli.model.actions.ToPay;
import it.unibo.monopoli.model.actions.ToRollDices;
import it.unibo.monopoli.model.actions.ToSellProperties;
import it.unibo.monopoli.model.cards.Deck;
import it.unibo.monopoli.model.cards.ImprevistiCards;
import it.unibo.monopoli.model.cards.ProbabilitaCards;
import it.unibo.monopoli.model.cards.Card;
import it.unibo.monopoli.model.cards.ClassicCard;
import it.unibo.monopoli.model.cards.ClassicDeck;
import it.unibo.monopoli.model.cards.ClassicDecks;
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
import it.unibo.monopoli.model.table.ItalianNeutralArea;
import it.unibo.monopoli.model.table.Land;
import it.unibo.monopoli.model.table.LandGroup;
import it.unibo.monopoli.model.table.Ownership;
import it.unibo.monopoli.model.table.Police;
import it.unibo.monopoli.model.table.PrisonOrTransit;
import it.unibo.monopoli.model.table.Start;
import it.unibo.monopoli.model.table.Station;
import it.unibo.monopoli.model.table.StationIncomeStrategy;
import it.unibo.monopoli.model.table.TaxImpl;

/**
 * This is an implementation of {@link GameStrategy} for initialize the game
 * with the italian version of Monopoly.
 *
 */
public class ItalianStrategy implements GameStrategy {

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
     * Constructs a new instance of {@link ItalianStrategy} that needs all the
     * {@link Player}s to be initialized.
     * 
     * @param players
     *            - a {@link List} of all the current {@link Player}s
     */
    public ItalianStrategy(final List<Player> players) {
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
        this.ownerships.add(new ClassicLand("VICOLO CORTO", BoxesPositions.OWNERSHIP1_POSITION.getPos(), this.bank,
                Color.DARK_GRAY));
        this.ownerships.add(new ClassicLand("VICOLO STRETTO", BoxesPositions.OWNERSHIP2_POSITION.getPos(), this.bank,
                Color.DARK_GRAY));
        this.ownerships.add(new Station("STAZIONE SUD", BoxesPositions.OWNERSHIP3_POSITION.getPos(), this.bank));
        this.ownerships.add(new ClassicLand("BASTIONI GRAN SASSO", BoxesPositions.OWNERSHIP4_POSITION.getPos(),
                this.bank, Color.CYAN));
        this.ownerships.add(
                new ClassicLand("VIALE MONTEROSA", BoxesPositions.OWNERSHIP5_POSITION.getPos(), this.bank, Color.CYAN));
        this.ownerships.add(
                new ClassicLand("VIALE VESUVIO", BoxesPositions.OWNERSHIP6_POSITION.getPos(), this.bank, Color.CYAN));
        this.ownerships.add(new ClassicLand("VIA ACCADEMIA", BoxesPositions.OWNERSHIP7_POSITION.getPos(), this.bank,
                Color.MAGENTA));
        this.ownerships.add(new Company("SOCIETA' ELETTRICA", BoxesPositions.OWNERSHIP8_POSITION.getPos(), this.bank));
        this.ownerships.add(
                new ClassicLand("CORSO ATENEO", BoxesPositions.OWNERSHIP9_POSITION.getPos(), this.bank, Color.MAGENTA));
        this.ownerships.add(new ClassicLand("PIAZZA UNIVERSITÀ", BoxesPositions.OWNERSHIP10_POSITION.getPos(),
                this.bank, Color.MAGENTA));
        this.ownerships.add(new Station("STAZIONE OVEST", BoxesPositions.OWNERSHIP11_POSITION.getPos(), this.bank));
        this.ownerships.add(
                new ClassicLand("VIA VERDI", BoxesPositions.OWNERSHIP12_POSITION.getPos(), this.bank, Color.ORANGE));
        this.ownerships.add(new ClassicLand("CORSO RAFFAELLO", BoxesPositions.OWNERSHIP13_POSITION.getPos(), this.bank,
                Color.ORANGE));
        this.ownerships.add(
                new ClassicLand("PIAZZA DANTE", BoxesPositions.OWNERSHIP14_POSITION.getPos(), this.bank, Color.ORANGE));
        this.ownerships.add(
                new ClassicLand("VIA MARCO POLO", BoxesPositions.OWNERSHIP15_POSITION.getPos(), this.bank, Color.RED));
        this.ownerships.add(
                new ClassicLand("CORSO MAGELLANO", BoxesPositions.OWNERSHIP16_POSITION.getPos(), this.bank, Color.RED));
        this.ownerships.add(
                new ClassicLand("LARGO COLOMBO", BoxesPositions.OWNERSHIP17_POSITION.getPos(), this.bank, Color.RED));
        this.ownerships.add(new Station("STAZIONE NORD", BoxesPositions.OWNERSHIP18_POSITION.getPos(), this.bank));
        this.ownerships.add(new ClassicLand("VIALE COSTANTINO", BoxesPositions.OWNERSHIP19_POSITION.getPos(), this.bank,
                Color.YELLOW));
        this.ownerships.add(new ClassicLand("VIALE TRAIANO", BoxesPositions.OWNERSHIP20_POSITION.getPos(), this.bank,
                Color.YELLOW));
        this.ownerships
                .add(new Company("SOCIETA' ACQUA POTABILE", BoxesPositions.OWNERSHIP21_POSITION.getPos(), this.bank));
        this.ownerships.add(new ClassicLand("PIAZZA GIULIO CESARE", BoxesPositions.OWNERSHIP22_POSITION.getPos(),
                this.bank, Color.YELLOW));
        this.ownerships
                .add(new ClassicLand("VIA ROMA", BoxesPositions.OWNERSHIP23_POSITION.getPos(), this.bank, Color.GREEN));
        this.ownerships.add(
                new ClassicLand("CORSO IMPERO", BoxesPositions.OWNERSHIP24_POSITION.getPos(), this.bank, Color.GREEN));
        this.ownerships.add(
                new ClassicLand("LARGO AUGUSTO", BoxesPositions.OWNERSHIP25_POSITION.getPos(), this.bank, Color.GREEN));
        this.ownerships.add(new Station("STAZIONE EST", BoxesPositions.OWNERSHIP26_POSITION.getPos(), this.bank));
        this.ownerships.add(new ClassicLand("VIALE DEI GIARDINI", BoxesPositions.OWNERSHIP27_POSITION.getPos(),
                this.bank, Color.BLUE));
        this.ownerships.add(new ClassicLand("PARCO DELLA VITTORIA", BoxesPositions.OWNERSHIP28_POSITION.getPos(),
                this.bank, Color.BLUE));
    }

    private void inizializesGroups() {
        this.groups.add(new ClassicLandGroup("Grigio scuro", this.ownerships.get(BoxesPositions.INDEX_0.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_1.getPos())));
        this.ownerships.get(BoxesPositions.INDEX_0.getPos()).setGroup(this.groups.get(BoxesPositions.GROUP_0.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_1.getPos()).setGroup(this.groups.get(BoxesPositions.GROUP_0.getPos()));
        this.groups.add(new ClassicLandGroup("Ciano", this.ownerships.get(BoxesPositions.INDEX_3.getPos()),
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
        this.groups.add(new ClassicLandGroup("Arancione", this.ownerships.get(BoxesPositions.INDEX_11.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_12.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_13.getPos())));
        this.ownerships.get(BoxesPositions.INDEX_11.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_3.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_12.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_3.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_13.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_3.getPos()));
        this.groups.add(new ClassicLandGroup("Rosso", this.ownerships.get(BoxesPositions.INDEX_14.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_15.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_16.getPos())));
        this.ownerships.get(BoxesPositions.INDEX_14.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_4.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_15.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_4.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_16.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_4.getPos()));
        this.groups.add(new ClassicLandGroup("Giallo", this.ownerships.get(BoxesPositions.INDEX_18.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_19.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_21.getPos())));
        this.ownerships.get(BoxesPositions.INDEX_18.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_5.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_19.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_5.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_21.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_5.getPos()));
        this.groups.add(new ClassicLandGroup("Verde", this.ownerships.get(BoxesPositions.INDEX_22.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_23.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_24.getPos())));
        this.ownerships.get(BoxesPositions.INDEX_22.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_6.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_23.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_6.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_24.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_6.getPos()));
        this.groups.add(new ClassicLandGroup("Blu", this.ownerships.get(BoxesPositions.INDEX_26.getPos()),
                this.ownerships.get(BoxesPositions.INDEX_27.getPos())));
        this.ownerships.get(BoxesPositions.INDEX_26.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_7.getPos()));
        this.ownerships.get(BoxesPositions.INDEX_27.getPos())
                .setGroup(this.groups.get(BoxesPositions.GROUP_7.getPos()));
        this.groups.add(new ClassicLandGroup("Stazioni", this.ownerships.get(BoxesPositions.INDEX_2.getPos()),
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
        this.groups.add(new ClassicLandGroup("Società", this.ownerships.get(BoxesPositions.INDEX_7.getPos()),
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
            throw new IllegalArgumentException("Minimo giocatori: 2. Massimo giocatori: 6");
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
        chance.add(new ClassicCard(ImprevistiCards.CARD1.getDescription(), ImprevistiCards.CARD1.getID(),
                ImprevistiCards.CARD1.getActions()));
        chance.add(new ClassicCard(ImprevistiCards.CARD2.getDescription(), ImprevistiCards.CARD2.getID(),
                ImprevistiCards.CARD2.getActions()));
        chance.add(new ClassicCard(ImprevistiCards.CARD3.getDescription(), ImprevistiCards.CARD3.getID(),
                ImprevistiCards.CARD3.getActions()));
        chance.add(new ClassicCard(ImprevistiCards.CARD4.getDescription(), ImprevistiCards.CARD4.getID(),
                ImprevistiCards.CARD4.getActions()));
        chance.add(new ClassicCard(ImprevistiCards.CARD5.getDescription(), ImprevistiCards.CARD5.getID(),
                ImprevistiCards.CARD5.getActions()));
        chance.add(new ClassicCard(ImprevistiCards.CARD6.getDescription(), ImprevistiCards.CARD6.getID(),
                ImprevistiCards.CARD6.getActions()));
        chance.add(new ClassicCard(ImprevistiCards.CARD7.getDescription(), ImprevistiCards.CARD7.getID(),
                ImprevistiCards.CARD7.getActions()));
        chance.add(new ClassicCard(ImprevistiCards.CARD8.getDescription(), ImprevistiCards.CARD8.getID(),
                ImprevistiCards.CARD8.getActions()));
        chance.add(new ClassicCard(ImprevistiCards.CARD9.getDescription(), ImprevistiCards.CARD9.getID(),
                ImprevistiCards.CARD9.getActions()));
        chance.add(new ClassicCard(ImprevistiCards.CARD10.getDescription(), ImprevistiCards.CARD10.getID(),
                ImprevistiCards.CARD10.getActions()));
        chance.add(new ClassicCard(ImprevistiCards.CARD11.getDescription(), ImprevistiCards.CARD11.getID(),
                ImprevistiCards.CARD11.getActions()));
        chance.add(new ClassicCard(ImprevistiCards.CARD12.getDescription(), ImprevistiCards.CARD12.getID(),
                ImprevistiCards.CARD12.getActions()));
        chance.add(new ClassicCard(ImprevistiCards.CARD13.getDescription(), ImprevistiCards.CARD13.getID(),
                ImprevistiCards.CARD13.getActions()));
        chance.add(new ClassicCard(ImprevistiCards.CARD14.getDescription(), ImprevistiCards.CARD14.getID(),
                ImprevistiCards.CARD14.getActions()));
        chance.add(new ClassicCard(ImprevistiCards.CARD15.getDescription(), ImprevistiCards.CARD15.getID(),
                ImprevistiCards.CARD15.getActions()));
        chance.add(new ClassicCard(ImprevistiCards.CARD16.getDescription(), ImprevistiCards.CARD16.getID(),
                ImprevistiCards.CARD16.getActions()));
        this.decks.add(new ClassicDeck(ClassicDecks.CHANCE.getName(), chance));
        final List<Card> chest = new LinkedList<>();
        chest.add(new ClassicCard(ProbabilitaCards.CARD1.getDescription(), ProbabilitaCards.CARD1.getID(),
                ProbabilitaCards.CARD1.getActions()));
        chest.add(new ClassicCard(ProbabilitaCards.CARD2.getDescription(), ProbabilitaCards.CARD2.getID(),
                ProbabilitaCards.CARD2.getActions()));
        chest.add(new ClassicCard(ProbabilitaCards.CARD3.getDescription(), ProbabilitaCards.CARD3.getID(),
                ProbabilitaCards.CARD3.getActions()));
        chest.add(new ClassicCard(ProbabilitaCards.CARD4.getDescription(), ProbabilitaCards.CARD4.getID(),
                ProbabilitaCards.CARD4.getActions()));
        chest.add(new ClassicCard(ProbabilitaCards.CARD5.getDescription(), ProbabilitaCards.CARD5.getID(),
                ProbabilitaCards.CARD5.getActions()));
        chest.add(new ClassicCard(ProbabilitaCards.CARD6.getDescription(), ProbabilitaCards.CARD6.getID(),
                ProbabilitaCards.CARD6.getActions()));
        chest.add(new ClassicCard(ProbabilitaCards.CARD7.getDescription(), ProbabilitaCards.CARD7.getID(),
                ProbabilitaCards.CARD7.getActions()));
        chest.add(new ClassicCard(ProbabilitaCards.CARD8.getDescription(), ProbabilitaCards.CARD8.getID(),
                ProbabilitaCards.CARD8.getActions()));
        chest.add(new ClassicCard(ProbabilitaCards.CARD9.getDescription(), ProbabilitaCards.CARD9.getID(),
                ProbabilitaCards.CARD9.getActions()));
        chest.add(new ClassicCard(ProbabilitaCards.CARD10.getDescription(), ProbabilitaCards.CARD10.getID(),
                ProbabilitaCards.CARD10.getActions()));
        chest.add(new ClassicCard(ProbabilitaCards.CARD11.getDescription(), ProbabilitaCards.CARD11.getID(),
                ProbabilitaCards.CARD11.getActions()));
        chest.add(new ClassicCard(ProbabilitaCards.CARD12.getDescription(), ProbabilitaCards.CARD12.getID(),
                ProbabilitaCards.CARD12.getActions()));
        chest.add(new ClassicCard(ProbabilitaCards.CARD13.getDescription(), ProbabilitaCards.CARD13.getID(),
                ProbabilitaCards.CARD13.getActions()));
        chest.add(new ClassicCard(ProbabilitaCards.CARD14.getDescription(), ProbabilitaCards.CARD14.getID(),
                ProbabilitaCards.CARD14.getActions()));
        chest.add(new ClassicCard(ProbabilitaCards.CARD15.getDescription(), ProbabilitaCards.CARD15.getID(),
                ProbabilitaCards.CARD15.getActions()));
        chest.add(new ClassicCard(ProbabilitaCards.CARD16.getDescription(), ProbabilitaCards.CARD16.getID(),
                ProbabilitaCards.CARD16.getActions()));
        this.decks.add(new ClassicDeck(ClassicDecks.COMMUNITY_CHEST.getName(), chest));
    }

    private void inizializesBoxes() {
        final List<Box> temp = new LinkedList<>();
        temp.add(new Start("VIA", BoxesPositions.START_POSITION.getPos()));
        final PrisonOrTransit prison = new PrisonOrTransit("IN TRANSITO O IN PRIGIONE",
                BoxesPositions.PRISON_POSITION.getPos());
        temp.add(prison);
        temp.add(new ItalianNeutralArea("PARCHEGGIO GRATUITO", BoxesPositions.NEUTRAL_AREA_POSITION.getPos()));
        temp.add(new Police("IN PRIGIONE!", BoxesPositions.POLICE_POSITION.getPos()));
        temp.add(new DecksBox("IMPREVISTI", BoxesPositions.FIRST_CHANCE_POSITION.getPos()));
        temp.add(new DecksBox("IMPREVISTI", BoxesPositions.SECOND_CHANCE_POSITION.getPos()));
        temp.add(new DecksBox("IMPREVISTI", BoxesPositions.THIRD_CHANCE_POSITION.getPos()));
        temp.add(new DecksBox("PROBABILITÀ", BoxesPositions.FIRST_COMMUNITY_CHEST_POSITION.getPos()));
        temp.add(new DecksBox("PROBABILITÀ", BoxesPositions.SECOND_COMMUNITY_CHEST_POSITION.getPos()));
        temp.add(new DecksBox("PROBABILITÀ", BoxesPositions.THIRD_COMMUNITY_CHEST_POSITION.getPos()));
        temp.add(new TaxImpl("TASSA PATRIMONIALE", BoxesPositions.INCOME_TAX_POSITION.getPos(), AMOUNT_OF_FEES));
        temp.add(new TaxImpl("TASSA DI LUSSO", BoxesPositions.SUPER_TAX_POSITION.getPos(), AMOUNT_OF_FEES));
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
        return Collections.unmodifiableList(this.players);
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
        final Box prison = this.allBoxes.get(BoxesPositions.PRISON_POSITION.getPos());
        new ToRollDices(new ItalianDicesStrategy(prison), prison).play(player);
        return player.lastDicesNumber();
    }

    @Override
    public boolean getNextCardsActions(final Box box, final Card card, final Player player) {
        final ItalianNeutralArea neutral = ((ItalianNeutralArea) this.allBoxes
                .get(BoxesPositions.NEUTRAL_AREA_POSITION.getPos()));
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
            if (!this.tryToEvadeTaxes(player, CHANCE9_TAX)) {
                return true;
            }
            new EvadeTaxes(CHANCE5_PAY, neutral).play(player);
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
                    if (!this.tryToEvadeTaxes(player, b instanceof Home ? CHANCE8_HOME : CHANCE8_HOTEL)) {
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
            if (!this.tryToEvadeTaxes(player, CHEST1_2_PAY)) {
                return true;
            }
            break;
        case CARD18:
            if (!this.tryToEvadeTaxes(player, CHEST1_2_PAY)) {
                return true;
            }
            break;
        case CARD20:
            player.addCard(card);
            break;
        case CARD21:
            if (!this.tryToEvadeTaxes(player, 100)) {
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
                    if (!this.tryToEvadeTaxes(player, b instanceof Home ? CHEST8_HOME : CHEST8_HOTEL)) {
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
            break;
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
            player.getOwnerships().forEach(o -> {
                if (player.getMoney() <= moneyToPay && !o.isMortgaged()) {
                    new ToMortgage(o).play(player);
                }
            });
        }
        return player.getMoney() > moneyToPay;
    }

    private boolean tryToPay(final Player player, final int amoun) {
        try {
            new ToPay(1).play(player);
            return true;
        } catch (IllegalArgumentException i) {
            return this.haveEnoughMoney(player, amoun) ? this.tryToPay(player, amoun) : false;
        }
    }

    private boolean tryToEvadeTaxes(final Player player, final int amount) {
        try {
            final ItalianNeutralArea neutral = ((ItalianNeutralArea) this.allBoxes
                    .get(BoxesPositions.NEUTRAL_AREA_POSITION.getPos()));
            new EvadeTaxes(amount, neutral).play(player);
            return true;
        } catch (IllegalArgumentException i) {
            return this.haveEnoughMoney(player, amount) ? this.tryToPay(player, amount) : false;
        }
    }
}
