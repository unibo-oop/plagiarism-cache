package vg.controller.menu;

import javafx.application.Platform;
import vg.controller.Controller;
import vg.controller.GameControllerImpl;
import vg.controller.gameBoard.GameBoardController;
import vg.controller.leaderboard.ScoreManager;
import vg.controller.leaderboard.ScoreManagerImpl;
import vg.controller.prompt.PromptObserver;
import vg.controller.prompt.PromptOption;
import vg.model.StageImpl;
import vg.sound.manager.SoundManager;
import vg.utils.V2D;
import vg.view.AdaptableView;
import vg.view.ViewFactory;
import vg.view.ViewManager;
import vg.view.leaderBoard.LeaderBoardView;
import vg.view.menu.MenuView;
import vg.view.menu.prompt.PromptView;
import vg.view.settings.SettingView;
import vg.view.utils.KeyAction;

public class MenuController extends Controller<MenuView> implements PromptObserver {
    /**
     * Menu selection cursor.
     */
    private int idxSelection = 0;
    private final SoundManager soundManager;

    public MenuController(final MenuView view, final ViewManager viewManager, final SoundManager soundManager) {
        super(view, viewManager);
        this.getView().getViewController().highlightSelectedButton(idxSelection);
        this.soundManager = soundManager;
    }

    /**
     * Depending on selected button start corresponding view.
     */
    private void launchScreen() {
        if (idxSelection == MenuOption.PLAY.ordinal()) {
            playGame();
        } else if (idxSelection == MenuOption.LEADERBOARDS.ordinal()) {
           this.leaderBoards();
        } else if (idxSelection == MenuOption.SETTINGS.ordinal()) {
            SettingView settingsView = ViewFactory.settingView(this.getViewManager(), this.soundManager);
            this.getViewManager().addView(settingsView);
        } else if (idxSelection == MenuOption.QUIT.ordinal()) {
            PromptView promptView = ViewFactory.promptView(this.getViewManager(), this);
            this.getViewManager().addView(promptView);
        }
    }

    public void leaderBoards() {
        ScoreManager scoreManager = ScoreManagerImpl.newScoreManager();
        LeaderBoardView leaderBoardView = ViewFactory.leaderBoardView(getViewManager());
        this.getViewManager().addView(leaderBoardView);
        leaderBoardView.getViewController().showList(scoreManager.getTopScore(20));
    }

    /**
     * Load game model then show gameboard and start game session.
     * {@see GameControllerImpl}
     * {@see Game}
     */
    private void playGame() {
        try {
            vg.model.Stage<V2D> stageModel = new StageImpl<>();
            // 1) CREATE view
            AdaptableView<GameBoardController> gameView = ViewFactory.newGameBoardView();
            // 2) CREATE createMysteryBox logic controller
            GameControllerImpl gameController = new GameControllerImpl(gameView, stageModel, this.getViewManager(), this.soundManager);
            // 3) set logic controller in view
            gameView.setSceneController(gameController);
            this.getViewManager().addView(gameView);
            //5) run game-loop
            gameController.launchGameSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void keyTapped(final KeyAction k) {
        if (k == KeyAction.DOWN && idxSelection < MenuOption.values().length-1) {
            idxSelection++;
        } else if (k == KeyAction.UP && idxSelection > 0) {
             idxSelection--;
        } else if (k == KeyAction.ENTER) {
            launchScreen();
        }
        this.getView().getViewController().highlightSelectedButton(idxSelection);
    }

    @Override
    public void keyPressed(final KeyAction k) {
        keyTapped(k);
    }

    @Override
    public void keyReleased(final KeyAction k) {
    }

    @Override
    public void notifyPromptAnswer(final PromptOption answer) {
        if (answer == PromptOption.CONFIRM) {
            Platform.exit();
        } else if (answer == PromptOption.DENY) {
            this.getViewManager().popView();
        }
    }
}
