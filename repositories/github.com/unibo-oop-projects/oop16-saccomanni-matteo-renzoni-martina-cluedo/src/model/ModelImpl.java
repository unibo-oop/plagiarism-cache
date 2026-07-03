package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

import model.board.Board;
import model.board.Cell;
import model.board.Position;
import model.board.TrapDoorCell;
import model.cards.Card;
import model.cards.Solution;
import model.player.Player;
import model.player.PlayerInfo;
import utilities.Pair;
import utilities.enumerations.CellType;
import utilities.enumerations.CharacterCard;
import utilities.enumerations.RoomCard;

/**
 * Implementation of Model interface.
 */
public class ModelImpl implements Model {

    private final Board gameBoard;
    private final List<Player> players;
    private final Solution solution;
    private final Set<Card> commonCards;
    private int curPlayer;
    private final AIManager aiManager;
    private boolean gameOver;
    private final Random rand;

    private static final int MIN_DICE_RESULT = 2;
    private static final int MAX_DICE_RESULT = 12;

    /**
     * Creates the ModelImpl object.
     * 
     * @param gameBoard
     *            the board of the game
     * @param solution
     *            the solution of the game
     * @param players
     *            the players of the game
     * @param curPlayer
     *            the current player index
     * @param commonCards
     *            cards known to all players
     */
    ModelImpl(final Board gameBoard, final Solution solution, final List<Player> players, final int curPlayer,
            final Set<Card> commonCards) {
        this.gameBoard = gameBoard;
        this.players = players;
        this.solution = solution;
        this.commonCards = commonCards;
        this.curPlayer = curPlayer;
        this.gameOver = false;
        this.rand = new Random();
        this.aiManager = new AIManagerImpl(this);
    }

    @Override
    public PlayerInfo getCurrentPlayer() {
        checkState();
        return currentPlayer();
    }

    @Override
    public List<PlayerInfo> getPlayers() {
        return ImmutableList.copyOf(activePlayers());
    }

    @Override
    public Set<Card> getCommonCards() {
        return ImmutableSet.copyOf(this.commonCards);
    }

    @Override
    public void nextTurn() {
        checkState();
        this.curPlayer = (this.curPlayer + 1) % players.size();
        if (getCurrentPlayer().isOut()) {
            nextTurn();
        }
        currentPlayer().haveSuspected(false);
    }

    @Override
    public int rollDices() {
        checkState();
        return MIN_DICE_RESULT + rand.nextInt(MAX_DICE_RESULT - MIN_DICE_RESULT + 1);
    }

    @Override
    public Set<RoomCard> getReachableRooms(final int steps) {
        int remainingSteps = steps;
        Position currentPosition = currentPlayer().getPosition();
        final Set<RoomCard> reachableRooms = new HashSet<>();
        if (currentPlayer().getRoom().isPresent()) {
            final RoomCard startRoom = currentPlayer().getRoom().get();
            reachableRooms.add(startRoom);
            final Optional<TrapDoorCell> trap = this.gameBoard.getTrapDoor(startRoom);
            if (trap.isPresent()) {
                reachableRooms.add(trap.get().getDestinationRoom());
            }
            currentPosition = this.gameBoard.getDoor(startRoom).getDestination().getPosition();
            remainingSteps--;
        }
        for (final RoomCard r : RoomCard.getRoomCards()) {
            final Cell d = this.gameBoard.getDoor(r);
            if (this.gameBoard.getDistance(currentPosition, d.getPosition()) <= remainingSteps) {
                reachableRooms.add(r);
            }
        }
        return ImmutableSet.copyOf(reachableRooms);
    }

    @Override
    public Position movePlayer(final RoomCard destinationRoom, final int steps) {
        Cell destination;
        if (getReachableRooms(steps).contains(destinationRoom)) {
            destination = this.gameBoard.getRoomCells(destinationRoom).stream()
                    .filter(cell -> cell.getType().equals(CellType.SIMPLE) && !cell.isOccupied()).iterator().next();
        } else {
            final Cell destinationDoor = this.gameBoard.getDoor(destinationRoom);
            final Set<Cell> rechableCells;
            if (currentPlayer().getRoom().isPresent()) {
                rechableCells = Sets.filter(this.gameBoard.getHallwayCells(),
                        cell -> !cell.isOccupied() && this.gameBoard.getDistance(cell.getPosition(), this.gameBoard
                                .getDoor(currentPlayer().getRoom().get()).getDestination().getPosition()) < steps);
            } else {
                rechableCells = Sets.filter(this.gameBoard.getHallwayCells(), cell -> !cell.isOccupied()
                        && this.gameBoard.getDistance(cell.getPosition(), currentPlayer().getPosition()) <= steps);
            }
            destination = rechableCells.iterator().next();
            int bestDistance = this.gameBoard.getDistance(destination.getPosition(), destinationDoor.getPosition());
            for (final Cell c : rechableCells) {
                final int distance = this.gameBoard.getDistance(c.getPosition(), destinationDoor.getPosition());
                if (distance < bestDistance) {
                    bestDistance = distance;
                    destination = c;
                }
            }
        }
        currentPlayer().setCell(destination);
        return destination.getPosition();
    }

    @Override
    public Optional<Pair<PlayerInfo, Card>> disproveSuspect(final Solution suspect) {
        checkState();
        Preconditions.checkNotNull(suspect);
        Preconditions.checkState(getCurrentPlayer().canSuspect());
        Preconditions.checkState(suspect.getRoom().equals(currentPlayer().getRoom().get()));
        currentPlayer().haveSuspected(true);
        moveOpponent(suspect.getCharacter(), suspect.getRoom());
        final List<Player> otherPlayers = activePlayers();
        otherPlayers.remove(0);
        for (final Player p : otherPlayers) {
            final Optional<Card> result = p.showCard(currentPlayer(), suspect);
            if (result.isPresent()) {
                return Optional.of(new Pair<>(p, result.get()));
            }
        }
        return Optional.absent();
    }

    @Override
    public boolean checkSolution(final Solution possibleSolution) {
        checkState();
        Preconditions.checkNotNull(possibleSolution);
        Preconditions.checkState(possibleSolution.getRoom().equals(currentPlayer().getRoom().get()));
        moveOpponent(possibleSolution.getCharacter(), possibleSolution.getRoom());
        return possibleSolution.equals(this.solution);
    }

    @Override
    public void removePlayer(final PlayerInfo player) {
        Preconditions.checkNotNull(player);
        ((Player) player).gameOver();
        this.commonCards.addAll(player.getCards());
        activePlayers().forEach(p -> p.registerPlayerCards(player));
    }

    @Override
    public void saveGame() {
        CareMementoTaker.getInstance().setMemento(
                new ModelMemento(this.gameBoard, this.solution, this.players, this.curPlayer, this.commonCards));
    }

    @Override
    public void endGame() {
        this.gameOver = true;
        GameInit.getInstance().reset();
    }

    @Override
    public boolean isOver() {
        return this.gameOver;
    }

    @Override
    public AIManager getAIManager() {
        checkState();
        return this.aiManager;
    }

    private void checkState() {
        Preconditions.checkState(!this.gameOver, "Game is over");
    }

    private Player currentPlayer() {
        return this.players.get(this.curPlayer);
    }

    private List<Player> activePlayers() {
        final List<Player> activePlayers = new ArrayList<>();
        final int startPos = this.players.indexOf(currentPlayer());
        int i = startPos;
        do {
            if (!this.players.get(i).isOut()) {
                activePlayers.add(this.players.get(i));
            }
            i = (i + 1) % this.players.size();
        } while (i != startPos);
        return activePlayers;
    }

    private void moveOpponent(final CharacterCard opponent, final RoomCard room) {
        for (final Player p : activePlayers()) {
            if (p.getName().equals(opponent) && !currentPlayer().getName().equals(opponent)) {
                p.setCell(this.gameBoard.getRoomCells(room).stream()
                        .filter(cell -> !cell.isOccupied() && cell.getType().equals(CellType.SIMPLE)).findFirst()
                        .get());
            }
        }
    }
}