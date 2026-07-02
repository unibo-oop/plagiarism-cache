package it.dpg.maingame.controller.gamecycle;

import it.dpg.maingame.controller.gamecycle.playercontroller.PlayerController;
import it.dpg.maingame.controller.gamecycle.playercontroller.PlayerFactory;
import it.dpg.maingame.controller.gamecycle.playercontroller.PlayerFactoryImpl;
import it.dpg.maingame.controller.gamecycle.turnmanagement.*;
import it.dpg.maingame.controller.grid.GridGenerator;
import it.dpg.maingame.model.character.Dice;
import it.dpg.maingame.model.character.Difficulty;
import it.dpg.maingame.model.grid.CellType;
import it.dpg.maingame.model.grid.GridType;
import it.dpg.maingame.view.grid.GridView;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class GameCycleImpl implements GameCycle {

    private final GameState gameState;
    private final GridView view;
    private final TurnManager turnManager;
    private final Thread backgroundThread;

    GameCycleImpl(final int nTurns, final Dice defaultDice, final List<Dice> rewardDices, final Set<String> humanPlayers, final Set<Pair<String, Difficulty>> cpuPlayers) {
        this.backgroundThread = new Thread(this::mainCycle);
        this.backgroundThread.setDaemon(true);
        this.gameState = new GameStateImpl();
        GridType level = GridType.GRID_ONE;//randomize when multiple levels are present
        var pair = new GridGenerator(level, this).generate();
        this.view = pair.getRight();
        PlayerFactory playerFactory = new PlayerFactoryImpl(gameState, view, pair.getLeft());
        TurnManagerBuilder turnManagerBuilder = new TurnManagerBuilderImpl(nTurns, gameState);
        turnManagerBuilder
                .setDefaultDice(defaultDice)
                .setRewardDices(rewardDices);
        for (String humanName : humanPlayers) {
            turnManagerBuilder.addPlayer(playerFactory.createHumanPlayer(humanName));
        }
        for (var nameDiff : cpuPlayers) {
            turnManagerBuilder.addPlayer(playerFactory.createCpu(nameDiff.getLeft(), nameDiff.getRight()));
        }
        this.turnManager = turnManagerBuilder.build();
    }

    private void mainCycle() {
        view.setView();
        int turnCounter = 1;
        boolean turnRemaining = true;
        updatePlayersInView();
        while (turnRemaining) {
            waitNextStep("turn " + turnCounter + " has started");
            turnCounter++;
            boolean hasArrived;
            Iterator<PlayerController> iterator = turnManager.getPlayersIterator();
            while (iterator.hasNext()) {
                PlayerController currentPlayer = iterator.next();
                turnStart(currentPlayer);
                hasArrived = movePlayer(currentPlayer);
                if (hasArrived) {
                    waitNextStep(currentPlayer.getCharacter().getName() + " wins!");
                    view.closeView();
                    return;
                }
                if (currentPlayer.getCharacter().getCellType().equals(CellType.GO_BACK)) {
                    view.showText(currentPlayer.getCharacter().getName() + " ended up on a red cell \nand fell one step backwards");
                    sleepMillis(1500);
                    currentPlayer.getCharacter().stepBackward();
                    updatePlayersInView();
                }
            }
            turnRemaining = turnManager.hasNextTurn();
            if (turnManager.hasNextTurn()) {
                waitNextStep("minigames are starting");
                turnManager.nextTurn();
            }
            displayMinigameResults();
        }
        waitNextStep("no more turns remaining, game over");
        view.closeView();
    }

    private void updatePlayersInView() {
        var positions = new HashMap<Integer, Pair<Integer, Integer>>();
        turnManager.getPlayers().forEach(p -> positions.put(p.getCharacter().getId(), p.getCharacter().getPosition()));
        this.view.updatePlayers(positions);
    }

    private void turnStart(PlayerController player) {
        view.showText("it's " + player.getCharacter().getName() + "'s turn");
        view.setCurrentPlayerName(player.getCharacter().getName());
        sleepMillis(1000);
        view.removeText();
        int roll = player.throwDice();
        waitNextStep(player.getCharacter().getName() + " rolled a " + roll);
    }

    private void waitNextStep(String message) {
        gameState.setTurnPause(true);
        view.showText(message + "   continue...");
        synchronized (this.gameState) {
            try {
                while (gameState.isPaused()) {
                    gameState.wait();
                }
            } catch (InterruptedException e) {
                System.out.println("thread interrupted during turn step wait");
            }
        }
        view.removeText();
    }

    /**
     * @return true if the character reached the and of the level, false otherwise
     */
    private boolean movePlayer(PlayerController player) {
        boolean movesRemaining = true;
        while (movesRemaining) {
            sleepMillis(700);
            movesRemaining = singleStep(player);
            updatePlayersInView();
            if (player.getCharacter().getCellType().equals(CellType.END)) {
                return true;
            }
        }
        return false;
    }

    private boolean singleStep(PlayerController player) {
        if (player.getCharacter().getAdjacentPositions().size() == 1) {
            return player.getCharacter().stepForward();
        }
        player.chooseDirection();
        if (gameState.getLastDirectionChoice().isEmpty()) {
            throw new IllegalStateException("Turn state wasn't set properly");
        }
        return player.getCharacter().stepInDirection(gameState.getLastDirectionChoice().get());
    }

    private void displayMinigameResults() {
        StringBuilder results = new StringBuilder();
        for (PlayerController p : turnManager.getPlayers()) {
            results.append(p.getCharacter().getName()).append(" won a ").append(p.getCharacter().getDice()).append("\n");
        }
        waitNextStep(results.toString());
    }

    private void sleepMillis(final int milliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startGameCycle() {
        this.backgroundThread.start();
    }

    @Override
    public void signalDiceThrown() {
        synchronized (gameState) {
            gameState.setDiceThrown(true);
            gameState.notify();
        }
    }

    @Override
    public void signalPathChosen(Pair<Integer, Integer> coordinates) {
        synchronized (gameState) {
            gameState.setChoice(false);
            gameState.setLastDirectionChoice(coordinates);
            gameState.notify();
        }
    }

    @Override
    public void signalNextStep() {
        synchronized (gameState) {
            gameState.setTurnPause(false);
            gameState.notify();
        }
    }
}
