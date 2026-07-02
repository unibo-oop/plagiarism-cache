package view.base;

import java.awt.Dimension;
import java.awt.Toolkit;

import controller.base.BaseController;
import controller.base.BaseControllerImpl;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.game.GameView;
import view.game.GameViewImpl;
import view.leaderboard.LeaderboardView;
import view.leaderboard.LeaderboardViewImpl;
import view.menu.ChoiceMenuView;
import view.menu.ChoiceMenuViewImpl;
import view.menu.MainMenuView;
import view.menu.MainMenuViewImpl;

/**
 * This is the implementation of {@link BaseView} interface.
 */
public class BaseViewImpl implements BaseView {

    private final Stage stage;
    private final BaseController controller;

    /**
     * BaseView's constructor.
     * @param stage
     *      The main {@link Stage}.
     */
    public BaseViewImpl(final Stage stage) {
        this.stage = stage;
        this.stage.setTitle("Peggle");
        this.stage.setResizable(false);
        final Group root = new Group();
        final Scene scene = new Scene(root);
        this.stage.setScene(scene);
        this.controller = new BaseControllerImpl(this);
        this.controller.startProgram();
        this.stage.show();
    }

    @Override
    public final void startMainMenu() {
        final MainMenuView mainMenu = new MainMenuViewImpl(this.controller, this.stage);
        this.setMenuDimension();
        mainMenu.showMenu();
    }

    @Override
    public final void showChoiceMenu() {
        final ChoiceMenuView choiceMenu = new ChoiceMenuViewImpl(this.controller, this.stage);
        choiceMenu.show();
    }

    @Override
    public final void displayGame(final String mapChosen, final String name, final String characterChosen) {
        final GameView gameView = new GameViewImpl(this.controller, this.stage, name, mapChosen, characterChosen);
        gameView.initiateGame();
    }

    @Override
    public final void showLeaderboard() {
        final LeaderboardView leaderboard = new LeaderboardViewImpl(this.controller, this.stage);
        leaderboard.show();
    }

    private void setMenuDimension() {
        final Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        this.stage.setHeight(size.getHeight() / 2);
        this.stage.setWidth(size.getWidth() / 2);
    }
}
