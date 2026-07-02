package controller;

import java.util.Optional;

import controller.game.MatchController;
import controller.game.MatchControllerImpl;
import controller.users.AccountManager;
import controller.users.AccountOperation;
import javafx.stage.Stage;
import model.Model;
import model.ModelImpl;
import model.enums.PlayerNumber;
import model.gamemode.GameMode;
import model.match.players.MatchInfo;
import model.util.Pair;
import view.View;
import view.ViewImpl;
import view.dialog.DialogType;
import view.scene.SceneName;

/**
 * Concrete implementation of the app's mvc controller.
 */
public final class ControllerImpl implements Controller {

    private final Model model;
    private final View view;

    private final MatchController matchController;
    private final AccountManager manager;

    /**
     * Constructor of this class.
     * @param stage - the app's stage
     */
    public ControllerImpl(final Stage stage) {
        view = new ViewImpl(stage);
        model = new ModelImpl();
        manager = new AccountOperation(model);
        this.matchController = new MatchControllerImpl();
    }

    public AccountManager getAccountManager() {
        return this.manager;
    }

    @Override
    public void changeScene(final SceneName name) {
        view.loadScene(name);
    }

    @Override
    public Optional<String> launchDialog(final DialogType type, final String title, final String header, final String description) {
        return view.launchDialog(type, title, header, description);
    }

    @Override
    public Optional<PlayerNumber> getCurrentPlayer() {
        return model.getCurrentPlayer();
    }

    @Override
    public void setCurrentPlayer(final PlayerNumber playerNumber) {
        model.setCurrentPlayer(playerNumber);
    }

    @Override
    public Boolean isMatchOver(final int playerHits, final int opponentRemainingShips) {
        return model.isMatchOver(playerHits, opponentRemainingShips);
    }

    @Override
    public void setGameMode(final GameMode gameMode) {
        model.setGameMode(gameMode);
    }

    @Override
    public MatchController getMatchController() {
        return this.matchController;
    }

    @Override
    public void nextPlayer() {
        model.nextPlayer();
    }

    @Override
    public Optional<MatchInfo> getMatchInfo() {
        return model.getMatchInfo();
    }

    @Override
    public void setMatchInfo(final MatchInfo info) {
        model.setMatchInfo(info);
    }

    @Override
    public void setAI() {
        this.matchController.setPlayground(this.model.startBasicAI());
    }

    @Override
    public void shotAI() {
        Pair<Integer, Integer> cellHittedByAI = this.model.getNextHitPointBasicAI();
        this.matchController.shot(cellHittedByAI.getX(), cellHittedByAI.getY());
    }

}
