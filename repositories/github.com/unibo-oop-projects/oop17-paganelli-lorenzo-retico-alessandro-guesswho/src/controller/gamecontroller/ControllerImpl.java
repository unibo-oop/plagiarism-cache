package controller.gamecontroller;

import static utilities.Messages.*;
import java.util.*;
import java.util.logging.*;
import java.util.stream.IntStream;

import controller.command.Action;
import controller.gameoptions.*;
import controller.playercontroller.*;
import controller.playercontroller.PlayerController.Status;
import controller.resources.*;
import utilities.Utilities;
import view.*;
import view.viewposition.PositionManager;

/**
 * Controller implementation.
 */
public class ControllerImpl implements Controller {

    private enum GameStatus {
        STARTING, ON_GOING, ENDED;
    }

    private static final int NUMBER_OF_PLAYERS = 2;
    private static final Logger LOG = Logger.getAnonymousLogger();

    private final List<PlayerController> players = new ArrayList<>();
    private PlayerController current;
    private int playersInGame;
    private GameStatus status = GameStatus.STARTING;

    /**
     * Constructor.
     * @throws LoadingException 
     *              in case of error during loading of resources.
     * @param modality 
     *              the game's modality.
     * @param difficulty 
     *              the game's difficulty.
     * @param pack
     *              the game's pack.
     */
    public ControllerImpl(final Modality modality, final Difficulty difficulty, final Pack pack) throws LoadingException {
        Utilities.requireNonNull(modality, difficulty, pack);
        LOG.addHandler(new FileHandler());
        if (modality == Modality.SIMULATION) {
            LOG.addHandler(new ViewHandler(new LogView()));
        }
        Resources.load(pack.toString());
        PositionManager.createPositions(modality);
        IntStream.range(0, NUMBER_OF_PLAYERS).forEach(n -> players.add(n, n < modality.getNumberOfHumans() 
                ? new HumanController(difficulty, this) : new CpuController(difficulty, this)));
        current = Utilities.getRandom(players);
        this.checkReady();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void selected(final PlayerController player, final String characterName) {
        this.checkStatus(GameStatus.STARTING);
        Utilities.requireNonNull(player, characterName);
        playersInGame++;
        LOG.log(Level.INFO, HAS_SELECTED + characterName, 
                (player instanceof HumanController ? "[Human]" : "[Cpu]") + "Player " + playersInGame);
        this.checkReady();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void askOpponent(final Action action) {
        this.checkStatus(GameStatus.ON_GOING);
        Utilities.requireNonNull(action);
        try {
            this.logg(current, action.getQuestion());
            final boolean answer = getOpponent().askPlayer(action);
            this.logg(getOpponent(), answer ? YES : NO);
            current.registerAnswer(action, answer);
            this.endTurn(current.getStatus());
        } catch (InterruptedException e) {
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public void endGame(final PlayerController player) {
        Utilities.requireNonNull(player);
        if (status != GameStatus.ENDED) {
            status = GameStatus.ENDED;
            playersInGame = NUMBER_OF_PLAYERS - 1;
            this.logg(player.equals(current) ? current : getOpponent(), EXIT);
            (player.equals(current) ? getOpponent() : current).endGame(Status.OPPONENT_QUIT, Optional.empty());
            Arrays.stream(LOG.getHandlers()).forEach(Handler::close);
        } else {
            playersInGame--;
            if (playersInGame == 0) {
                new StartingView();
            }
        }
    }

    private void endTurn(final Status status) {
        if (status != Status.PLAYING) {
            this.status = GameStatus.ENDED;
            final Status opponentStatus = status == Status.WON ? Status.LOST : Status.WON;
            this.logg(current, status.name().toLowerCase(Locale.ITALIAN));
            this.logg(getOpponent(), opponentStatus.name().toLowerCase(Locale.ITALIAN));
            current.endGame(status, Optional.of(getOpponent().getSelected()));
            getOpponent().endGame(opponentStatus, Optional.of(current.getSelected()));
        } else {
            current = getOpponent();
            current.play();
        }
    }

    private void logg(final PlayerController player, final String msg) {
        LOG.log(Level.INFO, msg, this.getName(player));
    }

    private String getName(final PlayerController player) {
        return (player instanceof HumanController ? "[Human]" : "[Cpu]") + "Player " + (players.indexOf(player));
    }

    private void checkReady() {
        if (playersInGame == NUMBER_OF_PLAYERS && players.size() == NUMBER_OF_PLAYERS) {
            status = GameStatus.ON_GOING;
            current.play();
        }
    } 

    private PlayerController getOpponent() {
        return players.get((players.indexOf(current) + 1) % NUMBER_OF_PLAYERS);
    }

    private void checkStatus(final GameStatus required) {
        if (!status.equals(required)) {
            throw new IllegalStateException("Game status is :" + status);
        }
    }

}
