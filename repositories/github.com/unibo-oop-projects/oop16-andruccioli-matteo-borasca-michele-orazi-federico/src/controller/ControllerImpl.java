package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Semaphore;

import org.apache.commons.lang3.tuple.Triple;

import model.GameLoader;
import model.Model;
import model.bonus.BonusCard;
import model.state.StateInfo;
import utils.HistoryLog;
import utils.enumerations.Color;
import utils.enumerations.ControlType;
import utils.enumerations.MapType;
import utils.enumerations.Status;
import view.View;

/**
 * Implementation of {@link Controller} interface by using singleton pattern.
 */
public final class ControllerImpl implements Controller {

    private Optional<Model> model;
    private Optional<View> view;
    private final Semaphore sem;
    private static final ControllerImpl SINGLETON = new ControllerImpl();

    private ControllerImpl() {
        this.sem = new Semaphore(0);
        this.model = Optional.empty();
        this.view = Optional.empty();
    }

    /**
     * Getter of the object as required in singleton pattern.
     * @return the singleton object.
     */
    public static ControllerImpl getController() {
        return SINGLETON;
    }

    @Override
    public void endTurn() {
        this.model.get().shiftPlayer();
        StatusManager.getStatusManager().setGameStatus(Status.DEPLOYMENT);
        this.model.get().autoSave();
        this.view.get().updateInfoPlayer();
        this.initBattle();
    }

    @Override
    public Status getGameStatus() {
        return StatusManager.getStatusManager().getGameStatus();
    }

    @Override
    public void initialization(final List<Triple<String, Color, ControlType>> playersInfo, final MapType mapType) {
        Objects.requireNonNull(playersInfo, "NullPointerException, playersInfo required non-null.");
        Objects.requireNonNull(mapType, "NullPointerException, mapType required non-null.");
        HistoryLog.getLog().registerView(this.view.get());
        try {
            this.registerModel(GameLoader.getGameLoader().initGame(playersInfo, mapType));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.view.get().setGameList(this.model.get().getPlayers(), this.model.get().getStates(), Optional.empty());
        this.view.get().drawMap();
    }

    @Override
    public void initializeMap() {
        if (!this.model.isPresent()) {
            throw new IllegalStateException();
        }
        this.view.get().updateStates();
        this.view.get().updateInfoPlayer();
        if (StatusManager.getStatusManager().getGameStatus().equals(Status.INITIALIZATION)) {
            this.startInitialDeploymentPhase();
        } else {
            this.startBeginingTurnDeployment();
        }
    }

    @Override
    public void initBattle() {
        this.startBeginingTurnDeployment();
    }

    @Override
    public void registerView(final View view) {
        this.view = Optional.of(view);
    }

    @Override
    public void tanksToMove(final int tanksNumber) {
        try {
            this.model.get().getAttackManager().moveTanks(tanksNumber);
            this.view.get().updateStates();
        } catch (IllegalArgumentException e) {
            this.view.get().printError(e.getMessage());
        }
    }

    @Override
    public void tanksToMove(final StateInfo source, final StateInfo destination, final int tanksNumber) {
        Objects.requireNonNull(source, "NullPointerException, source required non-null.");
        Objects.requireNonNull(destination, "NullPointerException, destination required non-null.");
        StatusManager.getStatusManager().setGameStatus(Status.MOVEMENT);
        this.checkStates(source, destination, tanksNumber);
    }

    @Override
    public void saveOnFile(final String fileName) {
        this.model.get().saveOnFile(fileName);
    }

    @Override
    public void loadGameFromFile(final File fileName) {
        Objects.requireNonNull(fileName, "NullPointerException, fileName required non-null.");
        HistoryLog.getLog().registerView(this.view.get());
        this.registerModel(GameLoader.getGameLoader().loadGame(fileName));
        StatusManager.getStatusManager().setGameStatus(Status.DEPLOYMENT);
        this.view.get().setGameList(this.model.get().getPlayers(), this.model.get().getStates(), Optional.of(this.model.get().getMap()));
        this.view.get().drawMap();
    }

    @Override
    public void checkDeployment(final Map<StateInfo, Integer> choice) {
        Objects.requireNonNull(choice, "NullPointerException, choice required non-null.");
        if (choice.isEmpty()) {
            throw new IllegalArgumentException("Choiche list void.");
        }
        new TanksAnalyst(this.model.get(), this.view.get(), choice).start();
    }

    @Override
    public void checkStates(final StateInfo source, final StateInfo destination, final int tanksNumber) {
        Objects.requireNonNull(source, "NullPointerException, source required non-null.");
        Objects.requireNonNull(destination, "NullPointerException, destination required non-null.");
        new StatesAnalyst(this.model.get(), this.view.get(), source, destination, tanksNumber).start();
    }

    @Override
    public void resetGame() {
        this.model.get().resetGame();
        StatusManager.getStatusManager().setGameStatus(Status.INITIALIZATION);
        this.sem.release(this.sem.getQueueLength());
    }

    @Override
    public List<BonusCard> getBestCombo() {
        return this.model.get().getBonusCardManager().getBestCombo(this.model.get().getActualPlayer().forwarder());
    }

    @Override
    public void tradeCombo(final List<BonusCard> choice) {
        Objects.requireNonNull(choice, "NullPointerException, choice required non-null.");
        try {
        this.model.get().getBonusCardManager().tradeCombo(this.model.get().getActualPlayer().forwarder(), choice);
        } catch (IllegalArgumentException e) {
            this.view.get().printError(e.getMessage());
        }
    }

    @Override
    public synchronized Semaphore getSemaphore() {
        return this.sem;
    }

    @Override
    public synchronized void startBattlePhase() {
        new BattleManager(this.model.get(), this.view.get()).start();
    }

    private void startInitialDeploymentPhase() {
        new StatesArmer(this.model.get(), this.view.get()).start();
    }

    private void startBeginingTurnDeployment() {
        new TanksDealer(this.model.get(), this.view.get()).start();
    }

    private void registerModel(final Model model) {
        this.model = Optional.of(model);
    }

}
