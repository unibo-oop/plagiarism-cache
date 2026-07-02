package vg.view;

import vg.controller.GameOverController;
import vg.controller.gameBoard.GameBoardController;
import vg.controller.leaderboard.LeaderBoardController;
import vg.controller.menu.MenuController;
import vg.controller.prompt.PromptController;
import vg.controller.prompt.PromptObserver;
import vg.controller.settings.SettingsController;
import vg.sound.manager.SoundManager;
import vg.view.gameOver.GameOverView;
import vg.view.leaderBoard.LeaderBoardView;
import vg.view.menu.MenuView;
import vg.view.menu.prompt.PromptView;
import vg.view.settings.SettingView;
import vg.view.transition.TransitionView;
import vg.view.transition.TransitionViewController;
import vg.view.utils.CountdownView;

public class ViewFactory {

     private static <T> AdaptableView<T> makeAdaptableView(final String resName) {
        return new AdaptableView<T>(resName);
    }

    public static GameOverView gameOverView(final int score, final int round, final ViewManager viewManager) {
        GameOverView gameOverView = new GameOverView();
        GameOverController gameOverController = new GameOverController(gameOverView, viewManager);
        gameOverView.setSceneController(gameOverController);
        gameOverController.set(score, round);
        return gameOverView;
     }

    /**
     * Load gameboard view and its view controller from file.
     * @return GameBoard {@link AdaptableView}.
     */
    public static AdaptableView<GameBoardController> newGameBoardView() {
        return new AdaptableView<>("/layout/GameBoard1.fxml");
    }

    /**
     * Return pause view.
     * @return Pause view
     */
    public static View<ViewController> pauseView() {
        return makeAdaptableView("/layout/PauseView.fxml");
    }

    /**
     * Return CountdownView specific for when player won level and goes to next one.
     * This view show a victory message, current score and next level number.
     * Is also showed a countdown timer that inform player how long wait for next level.
     * @param score score reached by player
     * @param round next round number
     * @return CountdownView controlled by TransitionViewController
     */
    public static CountdownView<TransitionViewController> transitionView(final int score, final int round) {
        CountdownView<TransitionViewController> transitionView =  new TransitionView();
        transitionView.getViewController().setLevel(round);
        transitionView.getViewController().setScore(score);
        return transitionView;
    }

    /**
     * Create new LeaderBoard view to show list of player's score.
     * @param viewManager current viewManager
     * @return LeaderBoardView
     */
    public static LeaderBoardView leaderBoardView(final ViewManager viewManager) {
        LeaderBoardView leaderBoardView = new LeaderBoardView();
        LeaderBoardController leaderBoardController = new LeaderBoardController(leaderBoardView, viewManager);
        leaderBoardView.setSceneController(leaderBoardController);
        return leaderBoardView;
    }

    /**
     * Create prompt view that ask user something and can be answered by two buttons.
     * @param viewManager current viewManager
     * @param observer Controller or class that need to be informed of prompt answer
     * @return PromptView that ask user something
     */
      public static PromptView promptView(final ViewManager viewManager, final PromptObserver observer) {
        PromptView promptView = PromptView.newConfirmDialogView();
        PromptController promptController =
                new PromptController(promptView, viewManager, observer);
        promptView.setSceneController(promptController);
        return  promptView;
    }

    /**
     * Create main menu view that show title and many button to
     * @param viewManager current viewManager
     * @param soundManager current soundManager
     * @return MenuView
     */
    public static MenuView menuView(final ViewManager viewManager, final SoundManager soundManager) {
        MenuView menuView = new MenuView();
        MenuController menuController = new MenuController(menuView, viewManager, soundManager);
        menuView.setSceneController(menuController);
        return menuView;
    }

    /**
     * Create settings view where change settings.
     * @param viewManager current viewManager
     * @param soundManager current soundManager
     * @return SettingView
     */
    public static SettingView settingView(final ViewManager viewManager, final SoundManager soundManager) {
        SettingView settingView = new SettingView();
        SettingsController settingsController = new SettingsController(settingView, viewManager, soundManager);
        settingView.setSceneController(settingsController);
        return settingView;
    }
}
