package model.player;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ArrayTable;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Sets;

import model.board.Cell;
import model.cards.Card;
import model.cards.Solution;
import utilities.enumerations.CharacterCard;
import utilities.enumerations.HoldingStatus;
import utilities.enumerations.CardType;
import utilities.enumerations.PlayerType;

/**
 * This class contains data structures and methods needed for a player driven by
 * an AI. It's an extension of HumanPlayer.
 */
public class AIPlayer extends HumanPlayer {

    private static final long serialVersionUID = -901089649425194890L;

    private final HashMultimap<CharacterCard, Set<Card>> unknownClues;
    private final ArrayTable<Card, CharacterCard, HoldingStatus> decks;
    private final Map<CardType, Card> solution;
    private final Set<Card> commonCards;

    /**
     * Creates an instance of PlayerImpl.
     * 
     * @param name
     *            the name of the player (the character)
     * @param initialCell
     *            the cell occupied by the player on the board
     * @param cards
     *            player cards deck
     * @param commonCards
     *            cards known to all players
     * @param players
     *            the list of all current game players
     */
    public AIPlayer(final CharacterCard name, final Cell initialCell, final Set<Card> cards,
            final Set<Card> commonCards, final Set<CharacterCard> players) {
        super(name, initialCell, cards, commonCards);
        this.solution = new HashMap<>();
        this.unknownClues = HashMultimap.create();
        this.decks = ArrayTable.create(Card.getAllCards(), players);
        this.commonCards = commonCards;
        this.decks.rowKeyList().forEach(card -> {
            this.decks.columnKeyList().forEach(player -> this.decks.put(card, player, HoldingStatus.MAYBE));
            if (getCards().contains(card) || this.commonCards.contains(card)) {
                playerHasCard(card, name);
            } else {
                playerNoCard(card, name);
            }
        });
    }

    @Override
    public PlayerType getType() {
        return PlayerType.AI;
    }

    @Override
    public void registerPlayerCards(final PlayerInfo player) {
        super.registerPlayerCards(player);
        player.getCards().forEach(c -> playerHasCard(c, player.getName()));
        this.decks.column(player.getName()).forEach((card, value) -> {
            if (value.equals(HoldingStatus.MAYBE)) {
                playerNoCard(card, player.getName());
            }
        });
        this.unknownClues.removeAll(player.getName());
    }

    /**
     * This method is called when the player discovers a card of another player.
     * 
     * @param card
     *            the card discovered
     * @param player
     *            the player that owns the card
     */
    public final void playerHasCard(final Card card, final CharacterCard player) {
        for (final Map.Entry<CharacterCard, HoldingStatus> entry : this.decks.row(card).entrySet()) {
            if (entry.getKey().equals(player) && entry.getValue().equals(HoldingStatus.HASNOT)) {
                throw new IllegalStateException("The player was previously marked to not have this card!");
            } else if (entry.getKey().equals(player)) {
                this.decks.put(card, player, HoldingStatus.HAS);
                final Set<Set<Card>> setsToKeep = this.unknownClues.get(entry.getKey()).stream()
                        .filter(set -> !set.contains(card)).collect(Collectors.toSet());
                this.unknownClues.get(entry.getKey()).clear();
                this.unknownClues.get(entry.getKey()).addAll(setsToKeep);
            } else {
                playerNoCard(card, entry.getKey());
            }
        }
    }

    /**
     * This method is called when the player finds that another player doesn't
     * have a certain card.
     * 
     * @param card
     *            the card
     * @param player
     *            the player that doesn't own the card
     */
    public final void playerNoCard(final Card card, final CharacterCard player) {
        if (this.decks.get(card, player).equals(HoldingStatus.MAYBE)) {
            this.decks.put(card, player, HoldingStatus.HASNOT);
            for (final Set<Card> set : this.unknownClues.get(player)) {
                set.remove(card);
                if (set.isEmpty()) {
                    throw new IllegalStateException("It's impossibile that this set becomes empty!");
                }
            }
        } else if (!this.commonCards.contains(card) && this.decks.get(card, player).equals(HoldingStatus.HAS)) {
            throw new IllegalStateException("The player was previously marked to have this card!");
        }
    }

    /**
     * Adds an unknown clue: the specified player has one of the cards contained
     * in suspect, but the player doesn't know which one at the moment.
     * 
     * @param player
     *            the player that owns one of the cards into suspect
     * @param suspect
     *            the suspect (a triple of cards)
     */
    public void addUnknownClue(final Player player, final Solution suspect) {
        final Set<Card> cards = new HashSet<>();
        for (final Card c : suspect.getCards()) {
            if (this.decks.get(c, player.getName()).equals(HoldingStatus.MAYBE)) {
                cards.add(c);
            } else if (this.decks.get(c, player.getName()).equals(HoldingStatus.HAS)) {
                return;
            }
        }
        if (!cards.isEmpty()) {
            this.unknownClues.put(player.getName(), cards);
        }
    }

    /**
     * Returns true if the player knows the game solution.
     * 
     * @return true if the player knows the game solution
     */
    public boolean hasSolution() {
        updateClueData();
        return this.solution.keySet().containsAll(Arrays.asList(CardType.values()));
    }

    /**
     * Returns the found solution of the game.
     * 
     * @return the found solution of the game.
     */
    public Map<CardType, Card> getSolution() {
        return Collections.unmodifiableMap(this.solution);
    }

    /**
     * Returns a set of cards of a certain type that no other player owns.
     * 
     * @param type
     *            the type of card
     * @return a set of cards of a certain type that no other player owns
     */
    public Set<Card> getSafeCards(final CardType type) {
        final Set<Card> cards = Sets.union(getCards(), this.commonCards).stream()
                .filter(c -> c.getCardType().equals(type)).collect(Collectors.toSet());
        if (this.solution.containsKey(type)) {
            cards.add(this.solution.get(type));
        }
        return cards;
    }

    /**
     * Returns a set of cards of a certain type whose holder is unknown yet.
     * 
     * @param type
     *            the type of card
     * @return a set of cards of a certain type whose holder is unknown yet
     */
    public Set<Card> getUnknownCards(final CardType type) {
        final Set<Card> unknownCards = getClues().entrySet().stream()
                .filter(entry -> entry.getKey().getCardType().equals(type) && !entry.getValue())
                .map(entry -> entry.getKey()).collect(Collectors.toSet());
        unknownCards.removeAll(this.solution.values());
        return unknownCards;
    }

    /**
     * Processes the clues previously collected to make better decisions later.
     */
    public void updateClueData() {
        Boolean modified = false;
        for (final CharacterCard player : this.decks.columnKeyList()) {
            final Collection<HoldingStatus> playerValues = this.decks.column(player).values();
            final long hasCount = Collections.frequency(playerValues, HoldingStatus.HAS);
            final long maybeCount = Collections.frequency(playerValues, HoldingStatus.MAYBE);
            // Knowing the number of cards of each player, after having excluded
            // many possible cards, it's possible to identify other player cards
            if (maybeCount != 0 && hasCount + maybeCount == getCards().size()) {
                this.decks.rowKeyList().forEach(card -> {
                    if (this.decks.get(card, player).equals(HoldingStatus.MAYBE)) {
                        playerHasCard(card, player);
                    }
                });
                modified = true;
            }
        }
        updateHints();
        // Then checks if there's only one card for each type of cards that no
        // one still owns. If yes, that card is part of the solution
        final ListMultimap<CardType, Card> uknownCards = ArrayListMultimap.create();
        this.getClues().entrySet().stream().filter(entry -> entry.getValue().equals(false)).forEach(entry -> {
            uknownCards.put(entry.getKey().getCardType(), entry.getKey());
        });
        for (final CardType typeOfCard : uknownCards.keySet()) {
            final Card solutionCard = uknownCards.get(typeOfCard).get(0);
            if (uknownCards.get(typeOfCard).size() == 1
                    && this.decks.row(solutionCard).containsValue(HoldingStatus.MAYBE)) {
                this.decks.columnKeyList().forEach(player -> this.playerNoCard(solutionCard, player));
                modified = true;
            }
        }
        if (modified || updateUknownClues()) {
            updateClueData();
        }
    }

    private boolean updateUknownClues() {
        final Map<CharacterCard, Card> tmp = new HashMap<>();
        for (final CharacterCard character : this.unknownClues.keySet()) {
            for (final Set<Card> set : this.unknownClues.get(character)) {
                if (set.size() == 1) {
                    tmp.put(character, set.stream().findFirst().get());
                }
            }
        }
        if (!tmp.isEmpty()) {
            tmp.forEach((key, card) -> this.playerHasCard(card, key));
            return true;
        } else {
            return false;
        }
    }

    private void updateHints() {
        // It updates the hints map by marking true the cards that aren't
        // in the solution
        final Map<Card, Boolean> hints = new HashMap<>();
        this.decks.rowKeyList().forEach(card -> {
            if (this.decks.row(card).containsValue(HoldingStatus.HAS)) {
                hints.put(card, true);
            } else {
                hints.put(card, false);
                if (!this.decks.row(card).containsValue(HoldingStatus.MAYBE)) {
                    this.solution.put(card.getCardType(), card);
                }
            }
        });
        this.setClues(hints);
    }

    @Override
    public String toString() {
        return "AIPlayer [" + super.toString() + ", unknownClues=" + unknownClues + ", decks=" + decks + ", solution="
                + solution + "]";
    }
}