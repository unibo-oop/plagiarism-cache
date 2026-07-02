package boxhead.controller.game;

import java.io.FileNotFoundException;

import boxhead.controller.sound.SoundController;
import boxhead.model.score.Score;
import boxhead.view.EndView;
import boxhead.view.GameView;
import boxhead.view.MenuView;
import boxhead.view.PauseView;
import boxhead.view.SceneSwapper;
import boxhead.view.SceneSwapperImpl;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Game extends Application {

    private GameLevel gameCore;
    private SceneSwapper swapper;
    private Stage stage;
    private SoundController sound;

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
        
        this.stage = primaryStage;
        swapper = new SceneSwapperImpl(primaryStage);
        swapper.addScene(GameState.GameStateEnum.MENU.getName(), new MenuView().getMenuScene());
        GameState.state = GameState.GameStateEnum.MENU;
        this.changeScene();

        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/player/shotgun-south_west.png")));
        primaryStage.setTitle("BOXHEAD");
        primaryStage.setResizable(false);
        primaryStage.show();
        this.sound = new SoundController();
        mainLoop.start();
    }

    private void changeScene() {
        swapper.swapTo(GameState.state.getName());

        if (GameState.init) {
            this.initGame();
            GameState.init = false;
        }
    }

    private void initGame() {
    	swapper.addScene(GameState.GameStateEnum.PAUSE.getName(), new PauseView().getMenuScene());
        swapper.loadFromFile(GameState.GameStateEnum.GAME.getName());
        swapper.swapTo(GameState.GameStateEnum.GAME.getName());
        gameCore = new GameLevelImpl((GameView) (swapper.getFXMLController(GameState.GameStateEnum.GAME.getName()).get()),
                this);
    }

    private void loop() {
        if (GameState.soundOff) {
            this.sound.mute();
        } else {
            this.sound.unmute();
        }
        if (GameState.close) {
            this.stage.close();
        }
        if (GameState.change) {
            this.changeScene();
            GameState.change = false;
        }

        if (GameState.state.equals(GameState.GameStateEnum.GAME)) {
            if (this.gameCore.isPlayerAlive()) {
                this.gameCore.handle();
            } else {
            	final EndView end = new EndView();
            	final Score score = this.gameCore.getScore();
            	score.setGameEnd();
            	end.renderPlayTime(score.getTimePlayed());
            	end.renderKillCount(Integer.toString(score.getKills()));
            	end.renderMaxStreak(Integer.toString(score.getMaxStreak()));
            	swapper.addScene(GameState.GameStateEnum.END.getName(), end.getMenuScene());
                GameState.change = true;
                GameState.state = GameState.GameStateEnum.END;
            }
        }
    }

    /**
     * @return
     *          The current scene shown.
     */
    public final Scene getScene() {
        return this.swapper.getCurrentScene();
    }

    /**
     * 
     * @return
     *          The sound controller.
     */
    public final SoundController getSound() {
        return this.sound;
    }

}