package it.unibo.monopoli.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import it.unibo.monopoli.model.actions.Action;
import it.unibo.monopoli.model.actions.EvadeTaxes;
import it.unibo.monopoli.model.actions.GoToPrison;
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
import it.unibo.monopoli.model.mainunits.Bank;
import it.unibo.monopoli.model.mainunits.BoxesPositions;
import it.unibo.monopoli.model.mainunits.ClassicPawn;
import it.unibo.monopoli.model.mainunits.ClassicPlayer;
import it.unibo.monopoli.model.mainunits.ClassicStrategy;
import it.unibo.monopoli.model.mainunits.GameStrategy;
import it.unibo.monopoli.model.mainunits.GameVersion;
import it.unibo.monopoli.model.mainunits.GameVersionImpl;
import it.unibo.monopoli.model.mainunits.ItalianStrategy;
import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.Box;
import it.unibo.monopoli.model.table.ClassicLandContract;
import it.unibo.monopoli.model.table.CompanysIncomeStrategy;
import it.unibo.monopoli.model.table.DecksBox;
import it.unibo.monopoli.model.table.Home;
import it.unibo.monopoli.model.table.Hotel;
import it.unibo.monopoli.model.table.ItalianNeutralArea;
import it.unibo.monopoli.model.table.Land;
import it.unibo.monopoli.model.table.LandContract;
import it.unibo.monopoli.model.table.LandGroup;
import it.unibo.monopoli.model.table.LandIncomeStrategy;
import it.unibo.monopoli.model.table.Ownership;
import it.unibo.monopoli.model.table.Police;
import it.unibo.monopoli.model.table.Station;
import it.unibo.monopoli.model.table.StationIncomeStrategy;
import it.unibo.monopoli.model.table.TaxImpl;
import it.unibo.monopoli.view.InPlay;

/**
 * This is a class with all method for communicate between view and model .
 */
public class ControllerImpl implements Controller {

    private static final int PRISON_POSITION = 10;
    private static final int FIRST_CHANCE_POSITION = 7;
    private static final int SECOND_CHANCE_POSITION = 22;
    private static final int THIRD_CHANCE_POSITION = 36;
    private static final int AVERAGE_COST = 36;
    private final List<Player> players;
    private Player actualPlayer;
    private GameVersion version;
    private int actualPosition;
    private List<Integer> lastDices;
    private Bank bank;
    private List<Box> boxes;
    private List<Deck> decks;
    private Optional<InPlay> view;
    private boolean alreadyBuilt;
    private List<Actions> actions;
    private GameStrategy strategy;
    private int playerSize;

    /**
     * Constructor a new instance for list of {@link Player} and set the and set
     * empty the view.
     */
    public ControllerImpl() {
        this.players = new LinkedList<>();
        this.view = Optional.empty();
    }

    @Override
    public Player getActualPlayer() {
        return this.actualPlayer;
    }

    @Override
    public int getActualPosition() {
        return this.actualPosition;
    }

    @Override
    public void addPlayer(final String name, final ClassicPawn pawn, final boolean isHuman) {
        this.players.add(new ClassicPlayer(name, pawn, isHuman));
    }

    @Override
    public void initializedVersion(final EVersion versionEnum) {
        switch (versionEnum) {
        case CLASSIC:
            this.strategy = new ClassicStrategy(this.players);
            this.version = new GameVersionImpl(this.strategy);
            break;
        case ITALIAN_VERSION:
            this.strategy = new ItalianStrategy(this.players);
            this.version = new GameVersionImpl(this.strategy);
            break;
        default:
            break;
        }
        this.playerSize = this.players.size();
        this.bank = this.version.getBank();
        this.boxes = this.version.getAllBoxes();
        this.decks = this.version.getDecks();
        this.actualPlayer = this.version.getNextPlayer();

    }

    @Override
    public void play(final EVersion version) {
        if (!this.actualPlayer.isHuman()) {
            this.computerPlayer();
        }
        if (!this.view.isPresent()) {
            this.initializedVersion(version);
        }

    }

    @Override
    public void addView(final InPlay view) {
        this.view = Optional.of(view);
    }

    @Override
    public List<Box> getAllBoxes() {
        return this.boxes;
    }

    @Override
    public Bank getBank() {
        return this.bank;
    }

    @Override
    public List<Player> getPlayers() {
        return this.players;
    }

    @Override
    public int toRollDices() {

        this.lastDices = this.version.toRollDices();

        this.actualPosition = this.actualPlayer.getPawn().getActualPos();

        for (Box b : this.boxes) {
            if (b.getID() == this.actualPosition) {
                if (this.actualPlayer.isHuman()) {
                    this.view.ifPresent(v -> v.setButton(this.getNextBoxsActions(b, this.actualPlayer)));
                }
            }

        }
        if (this.actualPlayer.getPawn().getActualPos() != this.actualPosition) {
            this.actualPosition = this.actualPlayer.getPawn().getActualPos();
        }
        return this.actualPosition;
    }

    @Override
    public Box getActualBox() {
        return this.boxes.get(this.actualPosition);
    }

    @Override
    public void setActualPosition(final int position) {
        this.actualPosition = position;
    }

    /**
     * This is a notify for the view. Notify if {@link Player} loose.
     * 
     * @param player
     *            of looser.
     */
    public void notifyGameOver(final Player player) {
        this.view.ifPresent(v -> v.gameOver(player, this.players.indexOf(player)));
    }

    /**
     * This is a notify for the view. Notify computer is starting a turn.
     * 
     * @param player
     *            of looser.
     */
    public void notifyComputer(final Player player) {
        this.view.ifPresent(v -> v.computerTurn(player));
    }

    /**
     * This is a notify for the view. Notify if a card is draw with description.
     * 
     * @param card
     *            -the {@link Card} draw.
     */
    public void notifyDrawCard(final Card card) {
        this.view.ifPresent(v -> v.drawCard(card.getDescription()));
    }

    /**
     * This is a notify for the view. Notify the turn of computer .
     * 
     * @param player
     *            of looser.
     */
    public void notifyEndTurnComputer(final Player player) {
        this.view.ifPresent(v -> v.notifyEndTurnComputer(player));
    }

    /**
     * This is a notify for the view. Notify the actual position of computer.
     * 
     * @param position
     *            - the position of actual player.
     */
    public void notifyPositionComputer(final int position) {

        this.view.ifPresent(v -> v.beginComputer(position));
    }

    /**
     * This is a notify for the view.Notify the actual player winner.
     * 
     * @param player
     *            winner
     */
    public void notifyFinish(final Player player) {

        this.view.ifPresent(v -> v.finish(player));
    }

    @Override
    public void endTurn() {
        if (this.playerSize > this.players.size()) {
            System.out.println("l'ha rimosso");
            this.playerSize = this.players.size();
        } else {
            this.actualPlayer = this.version.endOfTurnAndNextPlayer();
        }

        if (!this.actualPlayer.isHuman()) {
            this.computerPlayer();
        } else {
            this.view.ifPresent(
                    v -> v.setButton(this.getNextBoxsActions(this.boxes.get(this.actualPosition), this.actualPlayer)));
        }
    }

    @Override
    public void buyOwnership() {

        if ((!actions.isEmpty() && actions.contains(Actions.BUY)) || !this.actualPlayer.isHuman()) {
            ToBuyProperties.buyAOwnership(((Ownership) this.boxes.get(actualPosition)).getContract().getCost(),
                    ((Ownership) this.boxes.get(actualPosition))).play(this.actualPlayer);
            if (this.actualPlayer.isHuman()) {
                this.view.ifPresent(v -> v.setButton(
                        this.getNextBoxsActions(((Ownership) this.boxes.get(actualPosition)), this.actualPlayer)));

            }

        } else {
            throw new IllegalArgumentException();
        }

    }

    @Override
    public void sellOwnership() {

        if (actions.contains(Actions.SELL) || !this.actualPlayer.isHuman()) {
            ToSellProperties.sellAOwnership(((Ownership) this.boxes.get(actualPosition)).getContract().getCost(),
                    (Ownership) this.boxes.get(actualPosition), this.bank).play(this.actualPlayer);

            if (this.actualPlayer.isHuman()) {
                this.view.ifPresent(v -> v.setButton(
                        this.getNextBoxsActions(((Ownership) this.boxes.get(actualPosition)), this.actualPlayer)));
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void build() {

        if (actions.contains(Actions.BUILD) || !this.actualPlayer.isHuman()) {
            ToBuyProperties.buyABuilding((Land) this.boxes.get(actualPosition), this.bank).play(this.actualPlayer);
            this.alreadyBuilt = true;
            if (this.actualPlayer.isHuman()) {
                this.view.ifPresent(v -> v
                        .setButton(this.getNextBoxsActions((Land) this.boxes.get(actualPosition), this.actualPlayer)));
            }
            this.alreadyBuilt = false;
        } else {
            throw new IllegalArgumentException();
        }

    }

    @Override
    public void sellBuilding() {

        if (actions.contains(Actions.SELL_BUILDING) || !this.actualPlayer.isHuman()) {
            Land land = (Land) this.boxes.get(actualPosition);
            ToSellProperties.sellABuilding(land, ((LandGroup) land.getGroup()).getBuildings().get(0), this.bank)
                    .play(this.actualPlayer);
            if (this.actualPlayer.isHuman()) {
                this.view.ifPresent(v -> v
                        .setButton(this.getNextBoxsActions((Land) this.boxes.get(actualPosition), this.actualPlayer)));
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void mortgageOwnership() {

        if (actions.contains(Actions.MORTGAGE) || !this.actualPlayer.isHuman()) {
            new ToMortgage((Ownership) this.boxes.get(actualPosition)).play(this.actualPlayer);
            if (this.actualPlayer.isHuman()) {
                this.view.ifPresent(v -> v.setButton(
                        this.getNextBoxsActions((Ownership) this.boxes.get(actualPosition), this.actualPlayer)));
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void revokeMortgageOwnership() {

        if (actions.contains(Actions.REVOKE_MORTGAGE) || !this.actualPlayer.isHuman()) {
            new ToRevokeMortgage((Ownership) this.boxes.get(actualPosition)).play(this.actualPlayer);
            if (this.actualPlayer.isHuman()) {
                if (this.view != null) {
                    this.view.ifPresent(v -> v.setButton(
                            this.getNextBoxsActions((Ownership) this.boxes.get(actualPosition), this.actualPlayer)));
                }
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Map<Player, Integer> endGame() {
        Map<Player, Integer> map = new HashMap<>();
        final List<Player> pl = this.players.stream().sorted((p, p1) -> this.patrimony(p1) - this.patrimony(p))
                .collect(Collectors.toList());
        for (Player p : pl) {
            map.put(p, this.patrimony(p));
        }
        if (!this.view.isPresent()) {
            map.entrySet().forEach(p -> {
                System.out.println(p.getKey().getName() + " have " + p.getValue());
            });
        }
        return map;
    }

    private int patrimony(final Player player) {
        Optional<Integer> res = Optional.empty();
        if (!player.getOwnerships().isEmpty()) {
            res = player.getOwnerships().stream().map(o -> o.getContract().getMortgageValue())
                    .reduce((i, i1) -> i + i1);
        }
        return player.getMoney() + (res.isPresent() ? res.get() : 0);
    }

    private void gameOverPerson(final Player player) {
        this.notifyGameOver(player);
        this.actualPlayer = this.version.removePlayer(player);
        if (this.players.size() == 1) {
            this.notifyFinish(this.players.get(0));
            this.endGame();
        }
        this.endTurn();
    }

    /**
     * This method is used for say if was twice or not.
     * 
     * @return -return true if last throw of dice have two equal value.
     */
    public boolean isTwiceDices() {
        return lastDices.get(0) == lastDices.get(1);
    }

    private boolean computerPlayer() {

        this.notifyComputer(actualPlayer);

        notifyPositionComputer(this.actualPlayer.getPawn().getActualPos());
        this.actualPosition = this.toRollDices();
        this.actions = this.getNextBoxsActions(this.boxes.get(this.actualPosition), this.actualPlayer);
        if (this.boxes.get(this.actualPosition) instanceof Ownership) {
            if (((Ownership) this.boxes.get(this.actualPosition)).isMortgaged()) {
                if (((Ownership) this.boxes.get(this.actualPosition)).getOwner().equals(this.actualPlayer)) {

                    if ((((Ownership) this.boxes.get(this.actualPosition)).getContract().getCost())
                            / 2 < (this.actualPlayer.getMoney() + AVERAGE_COST)) {
                        this.revokeMortgageOwnership();

                    }
                }
            }
        }

        if (this.boxes.get(this.actualPosition) instanceof Land) {
            final Land land = ((Land) this.boxes.get(this.actualPosition));
            if (land.getOwner().equals(this.bank)) {
                if (this.actualPlayer.getMoney() > ((Land) this.boxes.get(this.actualPosition)).getContract().getCost()
                        + AVERAGE_COST) {
                    this.buyOwnership();
                }
            } else if (land.getOwner().equals(this.actualPlayer)) {
                if (this.actualPlayer.getOwnerships().containsAll(land.getGroup().getMembers())
                        && ((LandGroup) land.getGroup()).canBuiling() && this.bank.getLeftBuilding().size() > 0
                        && this.actualPlayer.getMoney() >= (((LandContract) land.getContract()).getCostForEachBuilding()
                                + (AVERAGE_COST * 8))
                        && !this.alreadyBuilt) {
                    this.bank.getLeftBuilding().forEach(b -> {
                        if ((((LandGroup) land.getGroup()).getBuildings().size() < 4 && b instanceof Home)
                                || (b instanceof Hotel)) {
                            this.build();

                        }
                    });
                }

            } else {
                final int amount = ((ClassicLandContract) land.getContract()).getIncome(new LandIncomeStrategy(land));
                if (amount <= this.actualPlayer.getMoney()) {

                    new ToPay(amount).play(this.actualPlayer);
                    new ToBePaid(amount).play((Player) land.getOwner());

                } else {
                    if (this.version.haveEnoughMoney(this.actualPlayer, amount)) {
                        new ToPay(amount).play(this.actualPlayer);
                        new ToBePaid(amount).play((Player) land.getOwner());

                    } else {

                        new ToBePaid(amount).play((Player) land.getOwner());
                        if (!this.actualPlayer.getOwnerships().isEmpty()) {
                            for (Ownership o : this.getActualPlayer().getOwnerships()) {
                                o.setOwner(this.bank);

                            }
                        }
                        this.gameOverPerson(this.actualPlayer);
                        return true;
                    }
                }
            }
        } else if (this.boxes.get(this.actualPosition) instanceof Ownership) {
            final Ownership ownership = ((Ownership) this.boxes.get(this.actualPosition));
            if (ownership.getOwner().equals(this.bank)) {
                if (this.actualPlayer.getMoney() > ((Ownership) this.boxes.get(this.actualPosition)).getContract()
                        .getCost()) {
                    this.buyOwnership();
                }
            } else if (!ownership.getOwner().equals(this.actualPlayer)) {

                final int amount = ownership.getContract()
                        .getIncome(ownership instanceof Station ? new StationIncomeStrategy(ownership)
                                : new CompanysIncomeStrategy(ownership, this.actualPlayer));

                if (amount <= this.actualPlayer.getMoney()) {
                    new ToPay(amount).play(this.actualPlayer);
                    new ToBePaid(amount).play((Player) ((Ownership) this.boxes.get(this.actualPosition)).getOwner());
                } else {
                    if (version.haveEnoughMoney(this.actualPlayer, amount)) {
                        new ToPay(amount).play(this.actualPlayer);
                        new ToBePaid(amount)
                                .play((Player) ((Ownership) this.boxes.get(this.actualPosition)).getOwner());

                    } else {
                        new ToBePaid(amount)
                                .play((Player) ((Ownership) this.boxes.get(this.actualPosition)).getOwner());
                        if (!this.actualPlayer.getOwnerships().isEmpty()) {
                            for (Ownership o : this.getActualPlayer().getOwnerships()) {
                                o.setOwner(this.bank);

                            }
                        }
                        this.gameOverPerson(this.actualPlayer);
                        return true;
                    }

                }
            }
        } else {

            if (this.boxes.get(this.actualPosition) instanceof Police) {
                new GoToPrison(this.boxes.get(PRISON_POSITION)).play(this.actualPlayer);
            }
            if (this.boxes.get(this.actualPosition) instanceof DecksBox) {

                this.drawCard((this.decks.get(this.boxes.get(this.actualPosition).getID() == FIRST_CHANCE_POSITION
                        || this.boxes.get(this.actualPosition).getID() == SECOND_CHANCE_POSITION
                        || this.boxes.get(this.actualPosition).getID() == THIRD_CHANCE_POSITION ? 0 : 1)));

            }
            if (this.boxes.get(this.actualPosition) instanceof TaxImpl) {
                if (this.strategy instanceof ClassicStrategy) {
                    if (this.version.haveEnoughMoney(this.actualPlayer,
                            ((TaxImpl) this.boxes.get(this.actualPosition)).getCost())) {
                        new ToPay(((TaxImpl) this.boxes.get(this.actualPosition)).getCost()).play(this.actualPlayer);
                    } else {
                        if (!this.actualPlayer.getOwnerships().isEmpty()) {
                            for (Ownership o : this.getActualPlayer().getOwnerships()) {
                                o.setOwner(this.bank);

                            }
                        }
                        this.gameOverPerson(this.actualPlayer);
                        return true;
                    }

                } else {

                    if (this.version.haveEnoughMoney(this.actualPlayer,
                            ((TaxImpl) this.boxes.get(this.actualPosition)).getCost())) {
                        new EvadeTaxes(((TaxImpl) this.boxes.get(this.actualPosition)).getCost(),
                                ((ItalianNeutralArea) this.boxes.get(BoxesPositions.NEUTRAL_AREA_POSITION.getPos())))
                                        .play(this.actualPlayer);
                    } else {
                        if (!this.actualPlayer.getOwnerships().isEmpty()) {
                            for (Ownership o : this.getActualPlayer().getOwnerships()) {
                                o.setOwner(this.bank);

                            }
                        }
                        this.gameOverPerson(this.actualPlayer);
                        return true;
                    }

                }
            }
            if (this.strategy instanceof ItalianStrategy
                    && this.boxes.get(this.actualPosition) instanceof ItalianNeutralArea) {
                new ToStealMoney((ItalianNeutralArea) this.boxes.get(this.actualPosition));
            }

        }
        if (this.isTwiceDices()) {
            this.notifyEndTurnComputer(this.actualPlayer);
            this.computerPlayer();
        } else {
            this.notifyEndTurnComputer(this.actualPlayer);
            this.endTurn();
        }
        return false;
    }

    /**
     * This method draw {@link Card} from a {@link Deck}.
     * 
     * @param deck
     *            -{@link Deck} with all {@link Card}.
     */
    public void drawCard(final Deck deck) {
        new ToDrawCards(deck).play(this.actualPlayer);
        final Card card = this.actualPlayer.lastCardDrew();
        if (card.getActions().isPresent()) {
            List<Action> list = card.getActions().get();
            for (Action a : list) {
                a.play(this.getActualPlayer());
                if (this.actualPlayer.getPawn().getActualPos() != this.actualPosition) {
                    this.actualPosition = this.actualPlayer.getPawn().getActualPos();
                }
            }
        }
        this.notifyDrawCard(card);
        if (this.version.getNextCardsAction(this.getActualBox(), card, this.actualPlayer)) {
            this.gameOverPerson(this.actualPlayer);
        }

    }

    @Override
    public List<Actions> getNextBoxsActions(final Box box, final Player player) {
        actions = new LinkedList<>();
        actions.clear();

        actions.add(Actions.END_OF_THE_GAME);
        if (!player.dicesAlreadyRolled() && player.isTheFirtsLaunch()) {
            actions.add(Actions.ROLL_DICES);
            return actions;
        } else if (!player.dicesAlreadyRolled() && !player.isTheFirtsLaunch()) {
            actions.add(Actions.ROLL_DICES);
        }
        if (box instanceof Land) {
            final Land land = (Land) box;
            if (land.getOwner().equals(this.bank)) {
                this.toBuyOrToEndOfTurn(land, player, actions);
            } else if (land.getOwner().equals(player)) {
                if (land.isMortgaged()) {
                    actions.add(Actions.REVOKE_MORTGAGE);
                } else if (player.getOwnerships().containsAll(land.getGroup().getMembers())
                        && ((LandGroup) land.getGroup()).canBuiling() && this.bank.getLeftBuilding().size() > 0
                        && player.getMoney() >= ((LandContract) land.getContract()).getCostForEachBuilding()
                        && !this.alreadyBuilt) {
                    this.bank.getLeftBuilding().forEach(b -> {
                        if ((((LandGroup) land.getGroup()).getBuildings().size() < 4 && b instanceof Home)
                                || (b instanceof Hotel)) {
                            actions.add(Actions.BUILD);
                        }
                    });
                }
                if (!((LandGroup) land.getGroup()).getBuildings().isEmpty()) {
                    actions.add(Actions.SELL_BUILDING);

                }

            } else {
                if (this.actualPlayer.isHuman()) {
                    final int amount = ((ClassicLandContract) land.getContract())
                            .getIncome(new LandIncomeStrategy(land));
                    if (amount <= player.getMoney()) {
                        new ToPay(amount).play(player);
                        new ToBePaid(amount).play((Player) land.getOwner());
                        player.setDebts(true);
                    } else {
                        if (version.haveEnoughMoney(this.actualPlayer, amount)) {
                            new ToPay(amount).play(this.actualPlayer);
                            new ToBePaid(amount).play((Player) land.getOwner());

                        } else {
                            new ToBePaid(amount).play((Player) land.getOwner());
                            if (!this.actualPlayer.getOwnerships().isEmpty()) {
                                for (Ownership o : this.getActualPlayer().getOwnerships()) {
                                    o.setOwner(this.bank);

                                }
                            }
                            this.gameOverPerson(this.actualPlayer);
                        }
                    }
                }
            }
        } else if (box instanceof Ownership) {
            final Ownership ownership = (Ownership) box;
            if (ownership.getOwner().equals(this.bank)) {
                this.toBuyOrToEndOfTurn(ownership, player, actions);
            } else if (!ownership.getOwner().equals(player)) {
                if (this.actualPlayer.isHuman()) {
                    final int amount = ownership.getContract().getIncome(ownership instanceof Station
                            ? new StationIncomeStrategy(ownership) : new CompanysIncomeStrategy(ownership, player));
                    if (amount <= player.getMoney()) {
                        new ToPay(amount).play(player);
                        new ToBePaid(amount).play((Player) ownership.getOwner());
                        player.setDebts(true);
                    } else {
                        if (version.haveEnoughMoney(this.actualPlayer, amount)) {
                            new ToPay(amount).play(this.actualPlayer);
                            new ToBePaid(amount).play((Player) ownership.getOwner());

                        } else {
                            new ToBePaid(amount).play((Player) ownership.getOwner());
                            if (!this.actualPlayer.getOwnerships().isEmpty()) {
                                for (Ownership o : this.getActualPlayer().getOwnerships()) {
                                    o.setOwner(this.bank);

                                }
                            }
                            this.gameOverPerson(this.actualPlayer);
                        }
                    }
                }
            } else {
                if (ownership.isMortgaged()) {
                    this.actions.add(Actions.REVOKE_MORTGAGE);
                }
            }
        } else {
            if (this.actualPlayer.isHuman()) {
                if (box instanceof Police) {
                    new GoToPrison(this.boxes.get(PRISON_POSITION)).play(player);
                }
                if (box instanceof DecksBox) {

                    this.drawCard((this.decks.get(box.getID() == FIRST_CHANCE_POSITION
                            || box.getID() == SECOND_CHANCE_POSITION || box.getID() == THIRD_CHANCE_POSITION ? 0 : 1)));

                }
                if (this.strategy instanceof ItalianStrategy && box instanceof ItalianNeutralArea) {
                    new ToStealMoney((ItalianNeutralArea) box);
                }

                if (box instanceof TaxImpl) {
                    if (this.strategy instanceof ClassicStrategy) {
                        if (this.version.haveEnoughMoney(this.actualPlayer, ((TaxImpl) box).getCost())) {
                            new ToPay(((TaxImpl) box).getCost()).play(player);
                        } else {
                            if (!this.actualPlayer.getOwnerships().isEmpty()) {
                                for (Ownership o : this.getActualPlayer().getOwnerships()) {
                                    o.setOwner(this.bank);

                                }
                            }
                            this.gameOverPerson(this.actualPlayer);
                        }

                    } else {

                        if (this.version.haveEnoughMoney(this.actualPlayer, ((TaxImpl) box).getCost())) {
                            new EvadeTaxes(((TaxImpl) box).getCost(), ((ItalianNeutralArea) this.boxes
                                    .get(BoxesPositions.NEUTRAL_AREA_POSITION.getPos()))).play(player);
                        } else {
                            if (!this.actualPlayer.getOwnerships().isEmpty()) {
                                for (Ownership o : this.getActualPlayer().getOwnerships()) {
                                    o.setOwner(this.bank);

                                }
                            }
                            this.gameOverPerson(this.actualPlayer);
                        }

                    }
                }
            }
        }
        if (player.dicesAlreadyRolled() && !this.isTwiceDices()) {
            actions.add(Actions.END_OF_TURN);
        }
        if ((box instanceof Ownership) && !((Ownership) box).isMortgaged()
                && (((Ownership) box).getOwner().equals(player))) {
            actions.add(Actions.MORTGAGE);
            actions.add(Actions.SELL);
        }
        if (this.actions.contains(Actions.SELL_BUILDING) && this.actions.contains(Actions.MORTGAGE)) {
            this.actions.remove(this.actions.indexOf(Actions.MORTGAGE));
            this.actions.remove(this.actions.indexOf(Actions.SELL));
        }
        return actions;
    }

    private void toBuyOrToEndOfTurn(final Ownership ownership, final Player player, final List<Actions> actions) {
        if (player.getMoney() >= ownership.getContract().getCost()) {
            actions.add(Actions.BUY);
        }
    }
}
