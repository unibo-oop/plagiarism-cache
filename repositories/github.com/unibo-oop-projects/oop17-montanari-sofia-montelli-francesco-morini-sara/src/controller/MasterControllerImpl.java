package controller;

import java.util.List;

import controller.after_battle.AfterBattleUIControllerImpl;
import controller.global_statistics.GlobalStatisticsUIControllerImpl;
import javafx.stage.Stage;
import model.ModelFactory;
import model.Model;
import model.navy.Navy;
import model.player.Player;
import view.MasterViewImpl;
/**
 * Singleton implementation of {@link MasterController}.
 */
public final class MasterControllerImpl implements MasterController {
    private static final MasterControllerImpl INSTANCE = new MasterControllerImpl();
    private final Model model = ModelFactory.getModelInteract();

    private MasterControllerImpl() { }
    /**
     * @return the instance of this class
     */
    public static MasterController getInstance() {
        return INSTANCE;
    }

    @Override
    public void addNewPlayer(final String name) {
        model.addNewPlayer(name);
    }

    @Override
    public void confirmPlayerData(final Player p, final String password) {
        model.setPlayerData(p, password);
        MasterViewImpl.getInstance().showNavyDisposal();
    }

    @Override
    public void confirmNavy(final Navy navy) {
        model.setNavy(navy);
        if (!model.canPlay()) {
            MasterViewImpl.getInstance().showSelectPlayer();
        } else {
            MasterViewImpl.getInstance().showGameUI();
        }
    }
    @Override
    public void setNavyBuilderSpecification(final List<Integer> specification, final int gridSize) {
        model.setSpecification(specification, gridSize);
        MasterViewImpl.getInstance().showSelectPlayer();
    }

    @Override
    public void showGobalStatistics() {
        MasterViewImpl.getInstance().showGlobalStatistics(new GlobalStatisticsUIControllerImpl());
    }

    @Override
    public void shootUndergo() {
        model.changeTurn();
        if (model.getMatch().isEnded()) {
            endGame();
        } else {
            MasterViewImpl.getInstance().showGameUI();
        }
    }

    @Override
    public void endGame() {
        MasterViewImpl.getInstance().showEndBattle(new AfterBattleUIControllerImpl(model.getStatManager()));
    }

    @Override
    public void credits() {
        MasterViewImpl.getInstance().credits();
    }
    @Override
    public Stage getPrimaryStage() {
        return MasterViewImpl.getInstance().getPrimaryStage();
    }
    @Override
    public void setFilePath(final String playerFile, final String statisticsFile) {
        model.setPaths(playerFile, statisticsFile);
        MasterViewImpl.getInstance().showMainMenu();
    }
}
