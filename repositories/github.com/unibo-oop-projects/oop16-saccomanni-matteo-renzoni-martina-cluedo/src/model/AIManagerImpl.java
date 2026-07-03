package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;

import model.cards.Card;
import model.cards.Solution;
import model.cards.SolutionImpl;
import model.player.AIPlayer;
import model.player.Player;
import model.player.PlayerInfo;
import utilities.Pair;
import utilities.enumerations.CharacterCard;
import utilities.enumerations.RoomCard;
import utilities.enumerations.CardType;
import utilities.enumerations.PlayerType;
import utilities.enumerations.WeaponCard;

/**
 * Implementation of AIManager interface.
 */
public final class AIManagerImpl implements AIManager {

    private final Model game;
    private final Random rand;

    /**
     * Creates an AIManager instance.
     * 
     * @param game
     *            the current game
     */
    public AIManagerImpl(final Model game) {
        this.game = game;
        this.rand = new Random();
    }

    @Override
    public void collectClue(final PlayerInfo asker, final Solution suspect,
            final Optional<Pair<PlayerInfo, Card>> response) {
        final List<AIPlayer> aiPlayers = new ArrayList<>();
        this.game.getPlayers().stream().filter(p -> p.getType().equals(PlayerType.AI))
                .forEach(p -> aiPlayers.add((AIPlayer) p));
        final Iterator<AIPlayer> aiPlayersIterator = aiPlayers.iterator();
        while (aiPlayersIterator.hasNext()) {
            final AIPlayer currentAIPlayer = aiPlayersIterator.next();
            // no one except eventually asker have this cards
            if (!response.isPresent()) {
                this.game.getPlayers().forEach(p -> {
                    if (!p.equals(asker)) {
                        suspect.getCards().forEach(c -> currentAIPlayer.playerNoCard(c, p.getName()));
                    }
                });
            } else { // there is a response
                final List<PlayerInfo> players = this.game.getPlayers();
                final int numPlayers = players.size();
                final int startPos = (players.indexOf(asker) + 1) % numPlayers;
                final int destinationPos = players.indexOf(response.get().getX());
                // who didn't respond didn't have any of the required cards
                for (int i = startPos; i < destinationPos; i = (i + 1) % numPlayers) {
                    for (final Card card : suspect.getCards()) {
                        currentAIPlayer.playerNoCard(card, players.get(i).getName());
                    }
                }
                if (currentAIPlayer.equals(asker)) {
                    // if i'm the asker i know what card was shown
                    currentAIPlayer.playerHasCard(response.get().getY(), response.get().getX().getName());
                } else {
                    currentAIPlayer.addUnknownClue((Player) response.get().getX(), suspect);
                }
            }
        }
    }

    @Override
    public RoomCard chooseDestination(final int steps) {
        checkState();
        final AIPlayer aiPlayer = (AIPlayer) this.game.getCurrentPlayer();
        aiPlayer.updateClueData();
        if (aiPlayer.hasSolution()) {
            return (RoomCard) aiPlayer.getSolution().get(CardType.ROOM);
        }
        final Set<RoomCard> safeRooms = new HashSet<>();
        final Set<RoomCard> uknownRooms = new HashSet<>();
        final Set<RoomCard> reachableRooms = this.game.getReachableRooms(steps);
        Set<RoomCard> remainingRooms;
        aiPlayer.getSafeCards(CardType.ROOM).forEach(card -> safeRooms.add((RoomCard) card));
        aiPlayer.getUnknownCards(CardType.ROOM).forEach(card -> uknownRooms.add((RoomCard) card));
        if (aiPlayer.getSolution().containsKey(CardType.ROOM)) {
            if (reachableRooms.isEmpty()) {
                return (RoomCard) safeRooms.toArray()[this.rand.nextInt(safeRooms.size())];
            }
            remainingRooms = Sets.intersection(reachableRooms, safeRooms);
        } else {
            if (reachableRooms.isEmpty()) {
                return (RoomCard) uknownRooms.toArray()[this.rand.nextInt(uknownRooms.size())];
            }
            remainingRooms = Sets.intersection(reachableRooms, uknownRooms);
        }
        if (remainingRooms.isEmpty()) {
            return (RoomCard) reachableRooms.toArray()[this.rand.nextInt(reachableRooms.size())];
        } else {
            return (RoomCard) remainingRooms.toArray()[this.rand.nextInt(remainingRooms.size())];
        }
    }

    @Override
    public Solution generateSuspect() {
        checkState();
        final AIPlayer aiPlayer = (AIPlayer) this.game.getCurrentPlayer();
        Preconditions.checkState(aiPlayer.canSuspect());
        aiPlayer.updateClueData();
        CharacterCard character;
        WeaponCard weapon;
        final RoomCard room = aiPlayer.getRoom().get();
        final Set<Card> safeCharacters = aiPlayer.getSafeCards(CardType.CHARACTER);
        final Set<Card> uknownCharacters = aiPlayer.getUnknownCards(CardType.CHARACTER);
        final Set<Card> safeWeapons = aiPlayer.getSafeCards(CardType.WEAPON);
        final Set<Card> uknownWeapons = aiPlayer.getUnknownCards(CardType.WEAPON);
        if (!aiPlayer.getSolution().containsKey(CardType.CHARACTER) && !uknownCharacters.isEmpty()) {
            character = (CharacterCard) uknownCharacters.toArray()[this.rand.nextInt(uknownCharacters.size())];
        } else {
            character = (CharacterCard) safeCharacters.toArray()[this.rand.nextInt(safeCharacters.size())];
        }
        if (!aiPlayer.getSolution().containsKey(CardType.WEAPON) && !uknownWeapons.isEmpty()) {
            weapon = (WeaponCard) uknownWeapons.toArray()[this.rand.nextInt(uknownWeapons.size())];
        } else {
            weapon = (WeaponCard) safeWeapons.toArray()[this.rand.nextInt(safeWeapons.size())];
        }
        return new SolutionImpl(character, weapon, room);
    }

    @Override
    public boolean wantToAccuse() {
        checkState();
        final AIPlayer aiPlayer = (AIPlayer) this.game.getCurrentPlayer();
        Preconditions.checkState(aiPlayer.getRoom().isPresent());
        return aiPlayer.hasSolution() && aiPlayer.getSolution().get(CardType.ROOM).equals(aiPlayer.getRoom().get());
    }

    @Override
    public Solution giveSolution() {
        Preconditions.checkState(wantToAccuse());
        final Map<CardType, Card> solution = ((AIPlayer) this.game.getCurrentPlayer()).getSolution();
        return new SolutionImpl((CharacterCard) solution.get(CardType.CHARACTER),
                (WeaponCard) solution.get(CardType.WEAPON), (RoomCard) solution.get(CardType.ROOM));
    }

    private void checkState() {
        Preconditions.checkState(!this.game.isOver());
        final PlayerInfo currentPlayer = this.game.getCurrentPlayer();
        Preconditions.checkState(currentPlayer.getType().equals(PlayerType.AI),
                "Current player is not controlled by AI");
    }
}