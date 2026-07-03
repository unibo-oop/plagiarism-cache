package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import controller.SurrogateController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.player.PlayerInfo;
import model.state.StateInfo;
import utils.CircularList;
import utils.enumerations.MapType;
import utils.enumerations.Status;
import view.gamescreen.FooterBattleInfo;
import view.gamescreen.GameScreen;
import view.gamescreen.VictoryScreen;
import view.gamescreen.events.InfoPlayerEvent;
import view.gamescreen.events.StateUpdatedEvent;
import view.mainmenu.MainMenu;
import view.playersetup.MapLabel;

/**
 * 
 * 
 * Main class of program's view. It implements methods written into View interface.
 *
 */
public final class ViewImpl implements View {

    private SurrogateController controller;
    private CircularList<? extends PlayerInfo> playerList;
    private Set<? extends StateInfo> stateList;
    private GameScreen gameScreen;

    private MapLabel gameMap;

    private int tanksToDeploy;

    private final Map<StateInfo, Integer> deployStateList = new HashMap<>();
    private Optional<StateInfo> originState = Optional.empty();
    private Optional<StateInfo> destinationState = Optional.empty();
    private Optional<StateInfo> atkState = Optional.empty();
    private Optional<StateInfo> defState = Optional.empty();

    private static ViewImpl istance;

    /**
     * Singleton creator.
     * 
     * @param c
     *          Controller to be set.
     * 
     * @return
     *          Actual ViewImpl object.
     */
    public static ViewImpl create(final SurrogateController c) {
        synchronized (ViewImpl.class) {
            if (istance == null) {
                istance = new ViewImpl(c);
            }
        }
        return istance;
    }

    /**
     * 
     * Singleton getter.
     * 
     * @return
     *          Actual ViewImpl object.
     */
    public static ViewImpl getIstance() {
        return istance;
    }

    /**
     * 
     * Class constructor.
     * 
     * @param c
     *          Reference to Controller interface.
     * 
     */
    private ViewImpl(final SurrogateController c) {
        setController(c);
    }

    @Override
    public void startView() {
        Application.launch(MainMenu.class);
    }

    @Override
    public void setGameList(final CircularList<? extends PlayerInfo> playerList, final Set<? extends StateInfo> stateList, final Optional<MapType> map) {
        this.playerList = playerList;
        this.stateList = stateList;
        map.ifPresent(o -> {
            try {
                this.gameMap = MapLabel.getMapFromMapType(o);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void drawMap() {
        Platform.runLater(new Runnable() {
            public void run() {
                new GameScreen().start(new Stage());
            }
        });
    }
 
    @Override
    public void updateStates() {
        final Event updateStateEvt = new StateUpdatedEvent();
        Platform.runLater(() -> gameScreen.getWorldMap().getStatelist().forEach(s -> Event.fireEvent(s, updateStateEvt)));
    }

    @Override
    public void updateInfoPlayer() {
        final Event updatePlayerInfoEvt = new InfoPlayerEvent();
        Platform.runLater(() -> Event.fireEvent(gameScreen.getFooterPlayerInfo(), updatePlayerInfoEvt));
        Platform.runLater(() -> gameScreen.getToolbar().getSideBar().updateCards());
        Platform.runLater(() -> gameScreen.getToolbar().getSideBar().updateAim());

    }

    @Override
    public void deployTanks() {
        disableAllStates(true);
        gameScreen.getWorldMap().enablesActivePlayerStates();
        setTanksToDeploy(ViewImpl.getIstance().getPlayerList().getHead().getTanksToDeploy());
    }

    @Override
    public void revertAction() {
        if (getController().getGameStatus().equals(Status.DEPLOYMENT) || getController().getGameStatus().equals(Status.INITIALIZATION)) {
            deployTanks(); 
        }
    }

    @Override
    public void updateStates(final List<StateInfo> assignment) {
        final Event updateStateEvt = new StateUpdatedEvent();
        Platform.runLater(() -> 
            assignment.forEach(s -> Event.fireEvent(gameScreen.getWorldMap().getStatelist().stream().filter(vs -> s.getName().equals(vs.getName())).findFirst().get(), updateStateEvt)));
        }

    @Override
    public void updateDice(final List<Pair<Integer, Integer>> diceValue) {
        final List<Integer> atkDice = new ArrayList<>();
        final List<Integer> defDice = new ArrayList<>();

        diceValue.iterator().forEachRemaining(d -> {
            atkDice.add(d.getKey());
            defDice.add(d.getValue());
        });

        FooterBattleInfo.updateAtkDice(atkDice);
        FooterBattleInfo.updateDefDice(defDice);
    }

    @Override
    public void printError(final String error) {
        Platform.runLater(() -> gameScreen.getWorldMap().printMessageonScreen(error));
    }

    @Override
    public void showVictory() {
        final VictoryScreen vs = new VictoryScreen();
        Platform.runLater(() -> {
            ((Stage) gameScreen.getWorldMap().getScene().getWindow()).getScene().setRoot(vs);

            this.restoreViewToInitialValues();
        });

        getController().resetGame();
    }

    @Override
    public void deployPhaseFinished() {
        this.controller.initBattle();
    }

    @Override
    public void aIAttackPhaseFinished() {
        Platform.runLater(() -> {
            gameScreen.getWorldMap().getGameStateBtn().setText("End Turn");
            gameScreen.getWorldMap().getGameStateBtn().setDisable(false);
        });
    }

    /**
     * 
     * Setter for the controller.
     * 
     * @param c
     *          Controller of the game.
     * 
     */
    private void setController(final SurrogateController c) {
        this.controller = c;
    }

    /**
     * 
     * Getter for the controller.
     * 
     * @return
     *          Return the controller of the game.
     */
    public SurrogateController getController() {
        return this.controller;
    }

    /**
     * 
     * Getter for playerList.
     * 
     * @return
     *          Return the player list of the game.
     * 
     */
    public CircularList<? extends PlayerInfo> getPlayerList() {
        return this.playerList;
    }

    /**
     * 
     * Getter for stateList.
     * 
     * @return
     *          Return the state list of the game.
     * 
     */
    public Set<? extends StateInfo> getStateList() {
        return this.stateList;
    }

    @Override
    public void updateLog(final String msg) {
        Platform.runLater(() -> gameScreen.getWorldMap().printMessageonScreen(msg));
        Platform.runLater(() -> gameScreen.getToolbar().getSideBar().addLogInfo(msg));
        System.out.println(msg);
    }

    /**
     * 
     * Set active GameScreen inside viewImpl.
     * 
     *  @param gs
     *              Active GameScreen.
     * 
     */
    public void registerGameScreen(final GameScreen gs) {
        this.gameScreen = gs;
    }

    /**
     * 
     * Return the list of states in which there will be added one tank.
     * 
     * @return
     *          The list of state.
     * 
     */
    public Map<StateInfo, Integer> getDeployStateList() {
        return this.deployStateList;
    }

    /**
     * 
     * Show a message dialog. It asks the player if he wants to move or not tanks after his attack phase.
     * 
     */
    public void showTanksMovementDiag() {
        gameScreen.getWorldMap().showMoveTanksDiag();
    }

    /**
     * 
     * Attacking state getter.
     * 
     * @return
     *          Attacker's state.
     * 
     */
    public Optional<StateInfo> getAtkState() {
        return atkState;
    }

    /**
     * 
     * Attacking state setter.
     * 
     * @param atkState
     *                  Attacker's state.
     * 
     */
    public void setAtkState(final StateInfo atkState) {
        this.atkState = Optional.of(atkState);
        gameScreen.getWorldMap().getChooserSlider().setMax();
        if (!gameScreen.getFooterBattleInfo().getMove().isVisible()) {
            gameScreen.getWorldMap().getMoverSlider().setVisible(false);
            gameScreen.getWorldMap().getChooserSlider().setVisible(true);
        }
    }

    /**
     * 
     * Defending state getter.
     * 
     * @return
     *          Defender's state.
     * 
     */
    public Optional<StateInfo> getDefState() {
        return defState;
    }

    /**
     * 
     * Defending state setter.
     * 
     * @param defState
     *                  Defender's state.
     * 
     */
    public void setDefState(final StateInfo defState) {
        this.defState = Optional.of(defState);
    }

    /**
     * 
     * Tankstodeploy getter.
     * 
     * @return
     *          Number of tanks to be deployed.
     * 
     */
    public int getTanksToDeploy() {
        return tanksToDeploy;
    }

    /**
     * 
     * Set tanks to deploy.
     * 
     * @param tanksToDeploy
     *                      Number of tanks to be deployed.
     *
     */
    public void setTanksToDeploy(final int tanksToDeploy) {
        this.tanksToDeploy = tanksToDeploy;
        Platform.runLater(() -> gameScreen.getFooterPlayerInfo().setRemainingTanks(tanksToDeploy));
    }

    /**
     * 
     * Toggle all view states.
     * 
     * @param bool
     *              The condition by which all states disables or enables. If true it disables.
     * 
     */
    public void disableAllStates(final boolean bool) {
       gameScreen.getWorldMap().toggleStates(bool);
    }

    /**
     * 
     * Getter for map choosen.
     * 
     * @return
     *          Return the state list of the game.
     * 
     */
    public MapLabel getGameMap() {
        return gameMap;
    }

    /**
     * 
     * Setter to set the map chosen by user.
     * 
     * @param gameMap
     *                  Game map selected by user.
     * 
     */
    public void setGameMap(final MapLabel gameMap) {
        this.gameMap = gameMap;
    }
 
    /**
     * 
     * Restore all selection and label texts to its primary value.
     * 
     */
    public void resetView() {
        gameScreen.getWorldMap().resetSelectedCountry();
        gameScreen.getWorldMap().getChooserSlider().setVisible(false);
        gameScreen.getFooterBattleInfo().resetSelections();
        gameScreen.getFooterPlayerInfo().resetStateInfo();
        this.atkState = Optional.empty();
        this.defState = Optional.empty();
        this.originState = Optional.empty();
        this.destinationState = Optional.empty();
        if (getController().getGameStatus().equals(Status.MOVEMENT)) {
            gameScreen.getFooterBattleInfo().setMoveButton(false);
        }
    }

    /**
     * 
     * Prepare the GUI for Movement phase.
     * 
     * @param check
     *              If true prepares the GUI for movement phase, if false not.
     * 
     */
    public void moveTanks(final boolean check) {
        disableAllStates(check);
        gameScreen.getWorldMap().enablesActivePlayerStates();
        gameScreen.getFooterBattleInfo().setMoveButton(check);
    }

    @Override
    public void disableAllComponents(final boolean check) {
        this.disableAllStates(check);
        gameScreen.getWorldMap().getGameStateBtn().setDisable(check);
    }

    @Override
    public void showMovementDialog() {
        if (atkState.get().getTanks() + defState.get().getTanks() - 1 != defState.get().getTanks()) {
            Platform.runLater(() -> gameScreen.getWorldMap().showMoveTanksAfterAttackDiag());
        }
    }

    /**
     * Getter for originState.
     * 
     * @return
     *          The state from which move tanks.
     * 
     */
    public Optional<StateInfo> getOriginState() {
        return originState;
    }

    /**
     * 
     * Set the state from which move tanks.
     * 
     * @param fromState
     *                  The actual State
     * 
     */
    public void setOriginState(final StateInfo fromState) {
        this.originState = Optional.of(fromState);
        gameScreen.getWorldMap().getMoverSlider().setMax();
        if (gameScreen.getFooterBattleInfo().getMove().isVisible()) {
            gameScreen.getWorldMap().getMoverSlider().setVisible(true);
            gameScreen.getWorldMap().getChooserSlider().setVisible(false);
        }
    }

    /**
     * 
     * Getter for destinationState.
     * 
     * @return
     *          The state to which move tanks.
     * 
     */
    public Optional<StateInfo> getDestState() {
        return destinationState;
    }

    /**
     * 
     * Set the state to which move tanks.
     * 
     * @param toState
     *              The actual state.
     * 
     */
    public void setDestState(final StateInfo toState) {
        this.destinationState = Optional.of(toState);
    }

    /**
     * 
     * Restore all view components to initial values.
     * 
     */
    public void restoreViewToInitialValues() {
        gameScreen.getToolbar().getChildren().clear();
        gameScreen.getFooterBattleInfo().getChildren().clear();
        gameScreen.getFooterPlayerInfo().getChildren().clear();
        gameScreen.getWorldMap().getChildren().clear();
    }

    /**
     * 
     * Change text of GameStateButton depending on which Status is passed as input.
     * 
     * @param status
     *                  Status passed as input.
     * 
     */
    public void setGameStateButtonText(final Status status) {
        switch(status) {
        case DEPLOYMENT:
            gameScreen.getWorldMap().getGameStateBtn().setText("End Deploy Phase");
            break;
        case ATTACK:
            gameScreen.getWorldMap().getGameStateBtn().setText("End Attack Phase");
            break;
        case MOVEMENT:
            gameScreen.getWorldMap().getGameStateBtn().setText("End Turn");
            break;
         default:
             gameScreen.getWorldMap().getGameStateBtn().setText("End Deploy Phase");
        }
    }

    /**
     * 
     * Check if move button is visible or not.
     * 
     * @return
     *          Boolean value that inidicate if move button is visible or not.
     * 
     */
    public boolean isMoveButtonVisible() {
        return this.gameScreen.getFooterBattleInfo().getMove().isVisible();
    }
}
