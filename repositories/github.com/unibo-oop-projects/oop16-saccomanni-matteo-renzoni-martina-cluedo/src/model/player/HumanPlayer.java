package model.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

import model.board.Cell;
import model.board.Position;
import model.cards.Card;
import model.cards.Solution;
import utilities.enumerations.CharacterCard;
import utilities.enumerations.RoomCard;
import utilities.enumerations.PlayerType;

/**
 * The implementation of Player interface. This class represents a Human player.
 */
public class HumanPlayer implements Player {

    private static final long serialVersionUID = 4720872327788276724L;

    private final CharacterCard name;
    private Cell occupiedCell;
    private final ImmutableSet<Card> cards;
    private final Map<Card, Boolean> clues;
    private String notes;
    private boolean alreadySuspected;
    private final List<String> history;
    private boolean outOfGame;

    private final HashMultimap<CharacterCard, Card> shownCards;

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
     */
    public HumanPlayer(final CharacterCard name, final Cell initialCell, final Set<Card> cards,
            final Set<Card> commonCards) {
        this.name = name;
        this.occupiedCell = initialCell;
        this.occupiedCell.setOccupied();
        this.cards = ImmutableSet.copyOf(cards);
        this.notes = "";
        this.history = new ArrayList<>();
        this.alreadySuspected = false;
        this.clues = new HashMap<>();
        final List<Card> allCards = Card.getAllCards();
        allCards.forEach(c -> this.clues.put(c, false));
        this.cards.forEach(c -> this.clues.put(c, true));
        commonCards.forEach(c -> this.clues.put(c, true));
        this.outOfGame = false;
        this.shownCards = HashMultimap.create();
    }

    @Override
    public CharacterCard getName() {
        return this.name;
    }

    @Override
    public PlayerType getType() {
        return PlayerType.HUMAN;
    }

    @Override
    public Set<Card> getCards() {
        return ImmutableSet.copyOf(this.cards);
    }

    @Override
    public void registerPlayerCards(final PlayerInfo player) {
        Preconditions.checkNotNull(player);
        Preconditions.checkState(player.isOut(), "Player doesn't left the game");
        player.getCards().forEach(c -> this.clues.put(c, true));
    }

    @Override
    public void setCell(final Cell destinationCell) {
        Preconditions.checkNotNull(destinationCell);
        Preconditions.checkState(!destinationCell.isOccupied());
        this.occupiedCell.setFree();
        this.occupiedCell = destinationCell;
        this.occupiedCell.setOccupied();
    }

    @Override
    public Optional<RoomCard> getRoom() {
        return this.occupiedCell.getRoom();
    }

    @Override
    public Position getPosition() {
        return this.occupiedCell.getPosition();
    }

    @Override
    public boolean canSuspect() {
        return !this.alreadySuspected && getRoom().isPresent();
    }

    @Override
    public void haveSuspected(final boolean alreadySuspected) {
        this.alreadySuspected = alreadySuspected;
    }

    @Override
    public Map<Card, Boolean> getClues() {
        return Collections.unmodifiableMap(this.clues);
    }

    @Override
    public void setClues(final Map<Card, Boolean> clues) {
        clues.forEach((k, v) -> this.clues.put(k, v));

    }

    @Override
    public String getNotes() {
        return this.notes;
    }

    @Override
    public void setNotes(final String notes) {
        this.notes = notes;
    }

    @Override
    public List<String> getHistory() {
        return Collections.unmodifiableList(history);
    }

    @Override
    public void addHistoryEvent(final String description) {
        this.history.add(description);
    }

    @Override
    public boolean isOut() {
        return this.outOfGame;
    }

    @Override
    public void gameOver() {
        this.outOfGame = true;
    }

    @Override
    public Optional<Card> showCard(final PlayerInfo opponent, final Solution reqCards) {
        final Set<Card> availableCards = Sets
                .newHashSet(Iterables.filter(this.cards, c -> reqCards.getCards().contains(c)));
        final Set<Card> oldCards = Sets.intersection(availableCards, this.shownCards.get(opponent.getName()));
        if (!oldCards.isEmpty()) {
            return Optional.of(Iterables.getLast(oldCards));
        }
        if (!availableCards.isEmpty()) {
            this.shownCards.put(opponent.getName(), Iterables.getLast(availableCards));
            return Optional.of(Iterables.getLast(availableCards));
        }
        return Optional.absent();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HumanPlayer other = (HumanPlayer) obj;
        return name == other.name;
    }

    @Override
    public String toString() {
        return "HumanPlayer [name=" + name + ", position=" + occupiedCell + ", cards=" + cards + ", hints=" + clues
                + ", notes=" + notes + ", history=" + history + ", outOfGame=" + outOfGame + ", shownCards="
                + shownCards + "]";
    }
}