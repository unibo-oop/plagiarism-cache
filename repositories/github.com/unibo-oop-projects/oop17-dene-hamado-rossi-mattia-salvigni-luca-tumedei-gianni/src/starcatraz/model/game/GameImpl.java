package starcatraz.model.game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import starcatraz.model.cards.Card;
import starcatraz.model.cards.CardColor;
import starcatraz.model.cards.CardImpl;
import starcatraz.model.cards.CardType;

/**
 * Game implementation.
 */
public class GameImpl implements Game {

    private static final int RED_ROCKET_PART_A_COUNT = 9;
    private static final int BLUE_ROCKET_PART_A_COUNT = 8;
    private static final int YELLOW_ROCKET_PART_A_COUNT = 7;
    private static final int GREY_ROCKET_PART_A_COUNT = 6;
    private static final int ROCKET_PART_B_COUNT = 4;
    private static final int CHIP_COUNT = 3;
    private static final int ROBOT_COUNT = 10;
    private static final int ROCKET_COUNT = 2;
    private static final int ROCKET_TOTAL = ROCKET_COUNT * (CardColor.values().length - 1);
    private static final int DEFAULT_CARDS_IN_HAND = 5;
    private static final int CARDS_TO_DISCARD = 5;
    private static final int CARDS_REVEALED_BY_CHIP = 5;

    private final ObservableList<Card> deck;
    private final ObservableList<Card> discardPile;
    private final ObservableList<Card> playerHand;
    private final ObservableList<Card> playedCards;
    private final ObservableList<Card> chipCards;
    private final ObservableList<Card> limboCards;
    private final ObservableList<Card> rockets;
    private final ObservableList<Card> unmodifiableDeck;
    private final ObservableList<Card> unmodifiableDiscardPile;
    private final ObservableList<Card> unmodifiablePlayerHand;
    private final ObservableList<Card> unmodifiablePlayedCards;
    private final ObservableList<Card> unmodifiableChipCards;
    private final ObservableList<Card> unmodifiableLimboCards;
    private final ObservableList<Card> unmodifiableRockets;
    private GameStatus status;
    private boolean limboModeOn;
    private int streak;
    private int chipSize;

    /**
     * Constructor for GameImpl.
     */
    public GameImpl() {
        this.status = GameStatus.IN_PROGRESS;
        this.deck = FXCollections.observableList(new LinkedList<>());
        this.discardPile = FXCollections.observableList(new LinkedList<>());
        this.playerHand = FXCollections.observableList(new LinkedList<>());
        this.playedCards = FXCollections.observableList(new LinkedList<>());
        this.chipCards = FXCollections.observableList(new LinkedList<>());
        this.limboCards = FXCollections.observableList(new LinkedList<>());
        this.rockets = FXCollections.observableList(new ArrayList<>(ROCKET_TOTAL));
        this.unmodifiableDeck = FXCollections.unmodifiableObservableList(this.deck);
        this.unmodifiableDiscardPile = FXCollections.unmodifiableObservableList(this.discardPile);
        this.unmodifiablePlayerHand = FXCollections.unmodifiableObservableList(this.playerHand);
        this.unmodifiablePlayedCards = FXCollections.unmodifiableObservableList(this.playedCards);
        this.unmodifiableChipCards = FXCollections.unmodifiableObservableList(this.chipCards);
        this.unmodifiableLimboCards = FXCollections.unmodifiableObservableList(this.limboCards);
        this.unmodifiableRockets = FXCollections.unmodifiableObservableList(this.rockets);
        this.limboModeOn = false;
        this.streak = 0;
    }

    @Override
    public ObservableList<Card> getDeck() {
        return this.unmodifiableDeck;
    }

    @Override
    public ObservableList<Card> getDiscardPile() {
        return this.unmodifiableDiscardPile;
    }

    @Override
    public ObservableList<Card> getPlayerHand() {
        return this.unmodifiablePlayerHand;
    }

    @Override
    public ObservableList<Card> getPlayedCards() {
        return this.unmodifiablePlayedCards;
    }

    @Override
    public ObservableList<Card> getChipCards() {
        return this.unmodifiableChipCards;
    }

    @Override
    public ObservableList<Card> getLimboCards() {
        return this.unmodifiableLimboCards;
    }

    @Override
    public ObservableList<Card> getRockets() {
        return this.unmodifiableRockets;
    }

    @Override
    public GameStatus getStatus() {
        return this.status;
    }

    private Optional<Card> getLastPlayedCard() {
        if (this.playedCards.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(this.playedCards.get(this.playedCards.size() - 1));
    }

    @Override
    public void reset() {
        this.status = GameStatus.IN_PROGRESS;
        this.deck.clear();
        this.discardPile.clear();
        this.playerHand.clear();
        this.playedCards.clear();
        this.chipCards.clear();
        this.limboCards.clear();
        this.rockets.clear();
        this.limboModeOn = false;
        this.streak = 0;
        fillDeck();
        shuffleDeck();
    }

    /**
     * Throws InvalidStateException if the game status is not expectedStatus.
     * @param expectedStatus
     */
    private void checkStatus(final GameStatus expectedStatus) {
        if (this.status != expectedStatus) {
            throw new IllegalStateException("Game status: " + this.status.toString() +
                                            "\nExpected status: " + expectedStatus.toString());
        }
    }

    @Override
    public void playCard(final Card card) {
        if (this.status == GameStatus.WON || this.status == GameStatus.LOST) {
            return;
        }
        checkStatus(GameStatus.IN_PROGRESS);
        if (!this.playerHand.contains(card)) {
            throw new InvalidCardException(card.getType(), card.getColor());
        }
        if (getLastPlayedCard().isPresent() && card.getType() == getLastPlayedCard().get().getType()) {
            return;
        }
        final Card c = this.playerHand.remove(this.playerHand.indexOf(card));
        // Check for rocket storage
        if (this.streak == 0 || getLastPlayedCard().get().getColor() != card.getColor()) {
            this.streak = 1;
        } else if (this.streak == 1) {
            this.streak++;
        } else if (this.streak == 2) {
            this.streak = 0;
            // Store rocket from deck if present
            if (cardCount(this.deck, CardType.ROCKET, card.getColor()) > 0) {
                storeRocket(pullRocketFromDeck(card.getColor()));
            }
        }
        this.playedCards.add(c);
        drawCard();
    }

    @Override
    public void discardCard(final Card card) {
        if (this.status == GameStatus.WON || this.status == GameStatus.LOST) {
            return;
        }
        if (!this.playerHand.contains(card) || card.getType() == CardType.CHIP) {
            throw new InvalidCardException(card.getType(), card.getColor());
        }
        discardCard(this.playerHand, card.getType(), card.getColor());
        drawCard();
    }

    @Override
    public void useChip(final CardColor chipColor) {
        if (this.status == GameStatus.WON || this.status == GameStatus.LOST) {
            return;
        }
        checkStatus(GameStatus.IN_PROGRESS);
        if (cardCount(this.playerHand, CardType.CHIP, chipColor) == 0) {
            throw new IllegalStateException("A Chip should be discarded but there are no Chips in the player's hand");
        }
        discardCard(this.playerHand, CardType.CHIP, chipColor);
        while (this.chipCards.size() < CARDS_REVEALED_BY_CHIP && !this.deck.isEmpty()) {
            this.chipCards.add(this.deck.remove(0));
        }
        this.chipSize = this.chipCards.size();
        this.status = GameStatus.USING_CHIP;
    }

    @Override
    public void selectChipCard(final Card card) {
        if (this.status == GameStatus.WON || this.status == GameStatus.LOST) {
            return;
        }
        checkStatus(GameStatus.USING_CHIP);
        if (cardCount(this.chipCards, card.getType(), card.getColor()) == 0) {
            throw new InvalidCardException(card.getType(), card.getColor());
        }
        if (cardCount(this.chipCards, CardType.ROCKET) == this.chipCards.size()) {
            this.deck.add(0, this.chipCards.remove(this.chipCards.indexOf(card)));
            // Discard the last card
            if (this.chipCards.size() == 0) {
                this.chipSize = 0;
                this.status = GameStatus.IN_PROGRESS;
                drawCard();
            }
        } else if (this.chipSize == this.chipCards.size() && card.getType() != CardType.ROCKET) {
            this.discardPile.add(this.chipCards.remove(this.chipCards.indexOf(card)));
        } else {
            this.deck.add(0, this.chipCards.remove(this.chipCards.indexOf(card)));
            // Discard the last card
            if (this.chipCards.size() == 0) {
                this.chipSize = 0;
                this.status = GameStatus.IN_PROGRESS;
                drawCard();
            }
        }
    }

    @Override
    public void handleRobotAttack(final RobotAttackChoice choice, final Optional<CardColor> color) {
        if (this.status == GameStatus.WON || this.status == GameStatus.LOST) {
            return;
        }
        checkStatus(GameStatus.UNDER_ATTACK);
        switch (choice) {
        case DISCARD_HAND:
            // Move all cards from the player''s hand to the discard pile
            while (!this.playerHand.isEmpty()) {
                this.discardPile.add(this.playerHand.remove(0));
            }
            // Draw DEFAULT_CARDS_IN_HAND cards from deck
            drawHand();
            if (this.status != GameStatus.LOST) {
                this.status = GameStatus.IN_PROGRESS;
            }
            break;
        case DISCARD_FROM_DECK:
            // Discard CARDS_TO_DISCARD from deck
            // If a Rocket or Robot appears, it is shuffled back inside the deck
            for (int count = 0; count < CARDS_TO_DISCARD; count++) {
                if (this.deck.isEmpty()) {
                    this.status = GameStatus.LOST;
                    return;
                }
                final Card card = this.deck.remove(0);
                switch (card.getType()) {
                case ROCKET_PART_A:
                case ROCKET_PART_B:
                case CHIP:
                    this.discardPile.add(card);
                    break;
                case ROCKET:
                case ROBOT:
                    this.limboCards.add(card);
                    break;
                default:
                    throw new UnhandledCardTypeException(card.getType());
                }
            }
            while (!this.limboCards.isEmpty()) {
                this.deck.add(this.limboCards.remove(0));
            }
            shuffleDeck();
            this.status = GameStatus.IN_PROGRESS;
            while (this.playerHand.size() < DEFAULT_CARDS_IN_HAND) {
                drawCard();
                if (this.status != GameStatus.IN_PROGRESS) {
                    return;
                }
            }
            break;
        case USE_CHIP:
            if (!color.isPresent()) {
                throw new IllegalArgumentException(choice.toString() +
                    " has been chosen but no chip color has been provided");
            }
            if (cardCount(this.playerHand, CardType.CHIP, color.get()) == 0) {
                throw new InvalidChoiceException(choice);
            }
            discardCard(this.playerHand, CardType.CHIP, color.get());
            // Draw a card in limbo mode to have 4 cards, then draw another card
            this.limboModeOn = true;
            while (this.playerHand.size() < DEFAULT_CARDS_IN_HAND) {
                if (this.status == GameStatus.LOST) {
                    return;
                }
                drawCard();
            }
            this.limboModeOn = false;
            if (!this.limboCards.isEmpty()) {
                while (!this.limboCards.isEmpty()) {
                    this.deck.add(this.limboCards.remove(0));
                }
                shuffleDeck();
            }
            this.status = GameStatus.IN_PROGRESS;
            break;
        case USE_ROCKET:
            if (!color.isPresent()) {
                throw new IllegalArgumentException(choice.toString() +
                    " has been chosen but no rocket color has been provided");
            }
            if (cardCount(this.rockets, CardType.ROCKET, color.get()) == 0) {
                throw new InvalidChoiceException(choice);
            }
            addRocketToDeck(color.get());
            shuffleDeck();
            while (this.playerHand.size() < DEFAULT_CARDS_IN_HAND) {
                if (this.status == GameStatus.LOST) {
                    return;
                }
                drawCard();
            }
            this.sortRockets();
            this.status = GameStatus.IN_PROGRESS;
            break;
        default:
            throw new UnhandledChoiceException(choice);
        }
    }

    @Override
    public void handleRocket(final boolean useChip) {
        if (this.status == GameStatus.WON || this.status == GameStatus.LOST) {
            return;
        }
        checkStatus(GameStatus.ACTIVATING_ROCKET);
        if (this.limboCards.size() > 1) {
            throw new IllegalStateException("Trying to handle the activation of a rocket when there is more than one active card");
        }
        if (cardCount(this.limboCards, CardType.ROCKET) != 1) {
            throw new IllegalStateException("Trying to handle the activation of a rocket when there are no rockets to activate - " + this.limboCards.size());
        }
        if (useChip) {
            discardCard(this.playerHand, CardType.CHIP, this.limboCards.get(0).getColor());
            this.storeRocket(this.limboCards.remove(0));
        } else {
            this.deck.add(this.limboCards.remove(0));
            shuffleDeck();
        }
        if (this.status == GameStatus.ACTIVATING_ROCKET) {
            this.status = GameStatus.IN_PROGRESS;
        }
        while (this.playerHand.size() < DEFAULT_CARDS_IN_HAND) {
            drawCard();
            if (this.status != GameStatus.IN_PROGRESS) {
                return;
            }
        }
        this.limboCards.forEach(System.out::println);
    }

    private int cardCount(final List<Card> cards, final CardType type, final CardColor color) {
        return (int) cards.stream()
                          .filter(c -> (c.getType() == type && c.getColor() == color))
                          .count();
    }

    private int cardCount(final List<Card> cards, final CardType type) {
        return (int) cards.stream()
                          .filter(c -> c.getType() == type)
                          .count();
    }

    private int cardCount(final List<Card> cards, final CardColor color) {
        return (int) cards.stream()
                          .filter(c -> c.getColor() == color)
                          .count();
    }

    private void shuffleDeck() {
        if (this.status == GameStatus.WON || this.status == GameStatus.LOST) {
            return;
        }
        final Random rnd = new Random();
        final List<Card> temp = new LinkedList<>();
        int r;
        while (!this.deck.isEmpty()) {
            r = rnd.nextInt(this.deck.size());
            temp.add(this.deck.remove(r));
        }
        while (!temp.isEmpty()) {
            r = rnd.nextInt(temp.size());
            this.deck.add(temp.remove(r));
        }
    }

    @Override
    public void drawHand() {
        if (this.status == GameStatus.WON || this.status == GameStatus.LOST) {
            return;
        }
        // During this phase, the player cannot be attacked by robots and cannot store rockets
        this.limboModeOn = true;
        while (this.playerHand.size() < DEFAULT_CARDS_IN_HAND) {
            if (this.status == GameStatus.LOST) {
                return;
            }
            drawCard();
        }
        while (!this.limboCards.isEmpty()) {
            this.deck.add(this.limboCards.remove(0));
        }
        shuffleDeck();
        this.limboModeOn = false;
    }

    /**
     * Draws a card from the deck and acts corresponding to its type.
     */
    private void drawCard() {
        if (this.status == GameStatus.WON || this.status == GameStatus.LOST) {
            return;
        }
        if (this.deck.isEmpty()) {
            this.status = GameStatus.LOST;
            return;
        }
        final Card card = this.deck.remove(0);
        switch (card.getType()) {
            case ROBOT:
                if (this.limboModeOn) {
                    this.limboCards.add(card);
                } else {
                    this.discardPile.add(card);
                    this.status = GameStatus.UNDER_ATTACK;
                }
                break;
            case ROCKET:
                if (this.limboModeOn) {
                    this.limboCards.add(card);
                } else {
                    this.status = GameStatus.ACTIVATING_ROCKET;
                    if (cardCount(this.deck, CardType.ROCKET) == this.deck.size()) {
                        this.status = GameStatus.LOST;
                    } else {
                        this.limboCards.add(card);
                    }
                }
                break;
            case ROCKET_PART_A:
            case ROCKET_PART_B:
            case CHIP:
                this.playerHand.add(card);
                break;
            default:
                throw new UnhandledCardTypeException(card.getType());
        }
    }

    /**
     * Removes a card of the given type and color from the player's hand and adds it to the discard pile.
     * @param type
     * @param color
     */
    private void discardCard(final List<Card> cards, final CardType type, final CardColor color) {
        if (this.status == GameStatus.WON || this.status == GameStatus.LOST) {
            return;
        }
        if (cardCount(cards, type, color) == 0) {
            throw new InvalidCardException(type, color);
        }
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getType() == type
               && cards.get(i).getColor() == color) {
                this.discardPile.add(0, cards.remove(i));
                i = cards.size();
            }
        }
    }

    /**
     * Adds a rocket to the deposit.
     * @param card: the rocket card to be addedd
     */
    private void storeRocket(final Card rocket) {
        if (this.status == GameStatus.WON || this.status == GameStatus.LOST) {
            return;
        }
        if (rocket.getType() != CardType.ROCKET
           || cardCount(this.rockets, rocket.getColor()) == ROCKET_COUNT) {
            throw new InvalidCardException(rocket.getType(), rocket.getColor());
        }
        this.rockets.add(rocket);
        sortRockets();
        if (this.rockets.size() == ROCKET_TOTAL) {
            this.status = GameStatus.WON;
        }
    }

    /**
     * Removes and returns a rocket of the given color from the deck.
     * @param color: the color of the rocket to be returned
     * @return a rocket of the given color
     */
    private Card pullRocketFromDeck(final CardColor color) {
        if (this.status == GameStatus.WON || this.status == GameStatus.LOST) {
            return null;
        }
        for (int i = 0; i < this.deck.size(); i++) {
            if (this.deck.get(i).getType() == CardType.ROCKET
               && this.deck.get(i).getColor() == color) {
                final Card c = this.deck.remove(i);
                shuffleDeck();
                return c;
            }
        }
        throw new IllegalArgumentException("The deck does not contain rockets of the given color.");
    }

    private void addRocketToDeck(final CardColor color) {
        if (this.status == GameStatus.WON || this.status == GameStatus.LOST) {
            return;
        }
        for (int i = 0; i < this.rockets.size(); i++) {
            if (this.rockets.get(i).getColor() == color) {
                this.deck.add(this.rockets.remove(i));
                return;
            }
        }
    }

    /**
     * Sort method for rocket store that functions with action listeners.
     */
    private void sortRockets() {
        final List<Card> temp = new LinkedList<>();
        while (!this.rockets.isEmpty()) {
            temp.add(this.rockets.remove(0));
        }
        temp.sort((a, b) -> Integer.compare(a.getValue(), b.getValue()));
        while (!temp.isEmpty()) {
            this.rockets.add(temp.remove(0));
        }
    }

    @Override
    public int getDeckRobotCount() {
        return (int) this.deck.stream().filter(c -> c.getType() == CardType.ROBOT).count();
    }

    @Override
    public int getDeckSize() {
        return this.deck.size();
    }

    /**
     * Fills the deck with all the appropriate cards.
     * Assumes that deck is empty
     */
    private void fillDeck() {
        // Add red rocket part A
        for (int i = 0; i < RED_ROCKET_PART_A_COUNT; i++) {
            this.deck.add(new CardImpl(CardType.ROCKET_PART_A, CardColor.RED));
        }
        // Add red rocket part B
        for (int i = 0; i < ROCKET_PART_B_COUNT; i++) {
            this.deck.add(new CardImpl(CardType.ROCKET_PART_B, CardColor.RED));
        }
        // Add blue rocket part A
        for (int i = 0; i < BLUE_ROCKET_PART_A_COUNT; i++) {
            this.deck.add(new CardImpl(CardType.ROCKET_PART_A, CardColor.BLUE));
        }
        // Add blue rocket part B
        for (int i = 0; i < ROCKET_PART_B_COUNT; i++) {
            this.deck.add(new CardImpl(CardType.ROCKET_PART_B, CardColor.BLUE));
        }
        // Add yellow rocket part A
        for (int i = 0; i < YELLOW_ROCKET_PART_A_COUNT; i++) {
            this.deck.add(new CardImpl(CardType.ROCKET_PART_A, CardColor.YELLOW));
        }
        // Add yellow rocket part B
        for (int i = 0; i < ROCKET_PART_B_COUNT; i++) {
            this.deck.add(new CardImpl(CardType.ROCKET_PART_B, CardColor.YELLOW));
        }
        // Add grey rocket part A
        for (int i = 0; i < GREY_ROCKET_PART_A_COUNT; i++) {
            this.deck.add(new CardImpl(CardType.ROCKET_PART_A, CardColor.GREY));
        }
        // Add grey rocket part B
        for (int i = 0; i < ROCKET_PART_B_COUNT; i++) {
            this.deck.add(new CardImpl(CardType.ROCKET_PART_B, CardColor.GREY));
        }
        // Add red chip
        for (int i = 0; i < CHIP_COUNT; i++) {
            this.deck.add(new CardImpl(CardType.CHIP, CardColor.RED));
        }
        // Add blue chip
        for (int i = 0; i < CHIP_COUNT; i++) {
            this.deck.add(new CardImpl(CardType.CHIP, CardColor.BLUE));
        }
        // Add yellow chip
        for (int i = 0; i < CHIP_COUNT; i++) {
            this.deck.add(new CardImpl(CardType.CHIP, CardColor.YELLOW));
        }
        // Add grey chip
        for (int i = 0; i < CHIP_COUNT; i++) {
            this.deck.add(new CardImpl(CardType.CHIP, CardColor.GREY));
        }
        // Add red rocket
        for (int i = 0; i < ROCKET_COUNT; i++) {
            this.deck.add(new CardImpl(CardType.ROCKET, CardColor.RED));
        }
        // Add blue rocket
        for (int i = 0; i < ROCKET_COUNT; i++) {
            this.deck.add(new CardImpl(CardType.ROCKET, CardColor.BLUE));
        }
        // Add yellow rocket
        for (int i = 0; i < ROCKET_COUNT; i++) {
            this.deck.add(new CardImpl(CardType.ROCKET, CardColor.YELLOW));
        }
        // Add grey rocket
        for (int i = 0; i < ROCKET_COUNT; i++) {
            this.deck.add(new CardImpl(CardType.ROCKET, CardColor.GREY));
        }
        // Add robot
        for (int i = 0; i < ROBOT_COUNT; i++) {
            this.deck.add(new CardImpl(CardType.ROBOT, CardColor.NO_COLOR));
        }
    }
}
