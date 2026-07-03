package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.base.Preconditions;

import model.board.Board;
import model.board.BoardFactory;
import model.board.Cell;
import model.cards.Card;
import model.cards.Solution;
import model.cards.SolutionImpl;
import model.player.Player;
import model.player.AIPlayer;
import model.player.HumanPlayer;
import utilities.enumerations.BoardType;
import utilities.enumerations.CharacterCard;
import utilities.enumerations.RoomCard;
import utilities.enumerations.PlayerType;
import utilities.enumerations.WeaponCard;

/**
 * Singleton that initializes a new game or load a previous one.
 */
public final class GameInit {

    private static final GameInit SINGLETON = new GameInit();

    private boolean alreadyInitialized;
    private Random rand;
    private static final int MIN_PLAYERS = 3;
    private static final int MAX_PLAYERS = 6;

    private GameInit() {
        reset();
    }

    /**
     * Returns the singleton.
     * 
     * @return the singleton
     */
    public static GameInit getInstance() {
        return SINGLETON;
    }

    /**
     * Setups everything in order to start a new game.
     * 
     * @param players
     *            a map of characters (each player is represented by a
     *            character) and the type of player ( human or AI)
     * @return a model already initialized
     * @throws IOException
     *             when can't load the board initialization file
     */
    public Model initGame(final Map<CharacterCard, PlayerType> players) throws IOException {
        Preconditions.checkNotNull(players);
        if (this.alreadyInitialized) {
            throw new IllegalStateException("Game already initialized");
        }
        if (players.size() < MIN_PLAYERS || players.size() > MAX_PLAYERS) {
            throw new IllegalArgumentException("Player number must be between 3 and 6");
        }
        this.alreadyInitialized = true;

        // Board initialization
        final Board gameBoard = new BoardFactory(BoardType.DEFAULT_BOARD).createBoard();

        // Solution initialization
        final List<CharacterCard> characters = CharacterCard.getCharacterCards();
        final List<WeaponCard> weapons = WeaponCard.getWeaponCards();
        final List<RoomCard> rooms = RoomCard.getRoomCards();
        final Solution gameSolution = new SolutionImpl(characters.get(rand.nextInt(characters.size())),
                weapons.get(rand.nextInt(weapons.size())), rooms.get(rand.nextInt(rooms.size())));

        // Player initialization
        final List<Player> gamePlayers = new ArrayList<>();
        final List<Card> allCards = Card.getAllCards();
        allCards.removeAll(gameSolution.getCards());
        Collections.shuffle(allCards);
        final int cardsPerPlayer = allCards.size() / players.size();
        final int commonCardsNum = allCards.size() % players.size();
        final Set<Card> commonCards = new HashSet<>();
        for (int i = 0; i < commonCardsNum; i++) {
            commonCards.add(allCards.remove(0));
        }
        players.forEach((character, typeOfPlayer) -> {
            final Set<Card> playerCards = new HashSet<>(allCards.subList(0, cardsPerPlayer));
            allCards.removeAll(playerCards);
            if (typeOfPlayer.equals(PlayerType.HUMAN)) {
                gamePlayers.add(new HumanPlayer(character, startPosition(gameBoard), playerCards, commonCards));
            } else {
                gamePlayers.add(
                        new AIPlayer(character, startPosition(gameBoard), playerCards, commonCards, players.keySet()));
            }
        });

        final Model model = new ModelImpl(gameBoard, gameSolution, gamePlayers, rand.nextInt(gamePlayers.size()),
                commonCards);
        return model;
    }

    /**
     * Setups everything in order to load a previously saved game.
     * 
     * @param memento
     *            A partial snapshot of a Model's subclass previously saved
     *            internal state
     * @return a model already initialized
     */
    public Model loadGame(final ModelMemento memento) {
        Preconditions.checkNotNull(memento);
        final Model model = new ModelImpl(memento.getGameBoard(), memento.getSolution(), memento.getPlayers(),
                memento.getCurrentPlayer(), memento.getCommonCards());
        return model;
    }

    /**
     * Restore the initial state. A new game can be created.
     */
    public void reset() {
        this.alreadyInitialized = false;
        CareMementoTaker.getInstance().setMemento(null);
        this.rand = new Random();
    }

    private Cell startPosition(final Board gameBoard) {
        final List<Cell> cells = gameBoard.getHallwayCells().stream().filter(cell -> !cell.isOccupied())
                .collect(Collectors.toList());
        return cells.get(this.rand.nextInt(cells.size()));
    }
}