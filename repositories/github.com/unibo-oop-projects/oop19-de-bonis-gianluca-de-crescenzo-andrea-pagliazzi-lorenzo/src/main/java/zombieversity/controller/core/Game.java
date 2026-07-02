package zombieversity.controller.core;

import java.io.FileNotFoundException;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import zombieversity.controller.core.GameState.GameStateEnum;
import zombieversity.controller.sound.SoundControllerImpl;
import zombieversity.model.score.Leaderboard;
import zombieversity.model.score.LeaderboardImpl;
import zombieversity.view.GameView;
import zombieversity.view.LeaderboardView;
import zombieversity.view.MenuView;
import zombieversity.view.NickInsertionView;
import zombieversity.view.SceneSwapperImpl;

/**
 * Game handler.
 */
public class Game extends Application {

    private GameWorld gameCore;
    private SceneSwapperImpl swap;
    private Stage stage;
    private SoundControllerImpl sound;
    private Leaderboard leaderboard;

    /**
     * Game entry point.
     * @param args
     *          Not used.
     */
    public static void main(final String... args) {
        Application.launch();
    }

    /**
     * Set the start of the game.
     */
    @Override
    public final void start(final Stage primaryStage) throws FileNotFoundException {
        final AnimationTimer mainLoop = new AnimationTimer() {
            @Override
            public void handle(final long now) {
                loop();
            }
        };

        this.leaderboard = new LeaderboardImpl();
        this.sound = new SoundControllerImpl();
        this.stage = primaryStage;
        swap = new SceneSwapperImpl(primaryStage);
        swap.addScene(GameState.GameStateEnum.MENU.getName(), new MenuView().getMenuScene());
        swap.loadFromFile(GameState.GameStateEnum.NICKNAME.getName());
        swap.loadFromFile(GameState.GameStateEnum.LEADERBOARD.getName());
        GameState.state = GameState.GameStateEnum.MENU;
        this.changeScene();

        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/icon.png")));
        primaryStage.setTitle("ZOMBIEVERSITY");
        primaryStage.setResizable(false);
        primaryStage.show();
        this.sound.startSound("background");
        mainLoop.start();
    }

    private void changeScene() {
        swap.swapTo(GameState.state.getName());

        final Initializable init = swap.getFXMLController(GameState.state.getName()).orElse(null);
        if (init instanceof LeaderboardView) {
            swap.removeScene(GameState.GameStateEnum.GAME.getName());
            final LeaderboardView leaderboardView = (LeaderboardView) swap.getFXMLController(GameState.state.getName())
                    .get();
            final String s = ((NickInsertionView) swap.getFXMLController(GameState.GameStateEnum.NICKNAME.getName())
                    .get()).getNickname();
            this.leaderboard.handleScore(this.gameCore.getScore());
            this.leaderboard.getLastScore().setGameEnd();
            this.leaderboard.getLastScore().setNickname(s);
            this.leaderboard.getLastScore()
                    .setKills(this.gameCore.getZombieController().getZombieModel().getKilledZombies());
            this.leaderboard.updateLeaderboard();
            leaderboardView.setUpData(this.leaderboard.getLeaderboard());
        }

        if (GameState.init) {
            this.initGame();
            GameState.init = false;
        }
    }

    private void initGame() {
        swap.loadFromFile(GameState.GameStateEnum.GAME.getName());
        swap.swapTo(GameState.GameStateEnum.GAME.getName());
        gameCore = new GameWorldImpl((GameView) (swap.getFXMLController(GameState.GameStateEnum.GAME.getName()).get()),
                this);
    }

    private void loop() {
        if (GameState.soundOff) {
            this.sound.stopGeneral();
        } else {
            this.sound.startGeneral();
        }
        if (GameState.close) {
            this.stage.close();
        }
        if (GameState.change) {
            this.changeScene();
            GameState.change = false;
        }

        if (GameState.state.equals(GameState.GameStateEnum.GAME)) {
            if (this.gameCore.playerIsAlive()) {
                this.gameCore.handle();
            } else {
                GameState.change = true;
                GameState.state = GameStateEnum.NICKNAME;
            }

        }
    }

    /**
     * @return
     *          Game's leaderboard.
     */
    public final Leaderboard getLeaderboard() {
        return this.leaderboard;
    }

    /**
     * @return
     *          The current scene shown.
     */
    public final Scene getScene() {
        return this.swap.getCurrent();
    }

    /**
     * 
     * @return
     *          The sound controller.
     */
    public final SoundControllerImpl getSound() {
        return this.sound;
    }

}
