package it.unibo.roguekong;

import it.unibo.roguekong.controller.GameController;
import it.unibo.roguekong.controller.LevelController;
import it.unibo.roguekong.controller.ScoreManager;
import it.unibo.roguekong.controller.SoundManager;
import it.unibo.roguekong.model.entity.impl.EnemyImpl;
import it.unibo.roguekong.model.entity.impl.PlayerImpl;
import it.unibo.roguekong.model.game.impl.*;
import it.unibo.roguekong.model.value.impl.PositionImpl;
import it.unibo.roguekong.view.impl.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {
    private static final SoundManager BACKGROUND_MUSIC = new SoundManager("/assets/sound/musicBackground.wav", -20.0f);

    @Override
    public void start(Stage stage) {
        stage.setTitle("RogueKong");
        stage.setResizable(false);

        GameStateImpl gameState = new GameStateImpl();

        MenuView menuView = new MenuView();
        ScoreView scoreView = new ScoreView();
        PauseView pauseView = new PauseView();
        GameView gameView = new GameView();
        VictoryView victoryView = new VictoryView();
        GameOverView gameOverView = new GameOverView();
        ScoreManager scoreManager = new ScoreManager();
        RulesView rulesView = new RulesView();
        PlayerImpl player = new PlayerImpl();

        scoreView.setScores(scoreManager.loadTopScores(3));

        var enemies1 = List.of(new EnemyImpl(new PositionImpl(200, 200)));
        LevelModel level1 = new LevelBuilderImpl()
                .setSpawnPosition(new PositionImpl(0, 480))
                .setEndPoint(new PositionImpl(928, 64))
                .setEnemiesList(enemies1)
                .setPlayer(player)
                .setTileManager(new TileManager("maps/map1.txt", "maps/background1.txt"))
                .build();

        var enemies2 = List.of(new EnemyImpl(new PositionImpl(200, 400)), new EnemyImpl(new PositionImpl(100, 150)));
        LevelModel level2 = new LevelBuilderImpl()
                .setSpawnPosition(new PositionImpl(0, 480))
                .setEndPoint(new PositionImpl(928, 64))
                .setEnemiesList(enemies2)
                .setPlayer(player)
                .setTileManager(new TileManager("maps/map2.txt", "maps/background1.txt"))
                .build();

        var enemies3 = List.of(new EnemyImpl(new PositionImpl(200, 400)),  new EnemyImpl(new PositionImpl(100, 150)));
        LevelModel level3 = new LevelBuilderImpl()
                .setSpawnPosition(new PositionImpl(0, 480))
                .setEndPoint(new PositionImpl(928, 64))
                .setEnemiesList(enemies3)
                .setPlayer(player)
                .setTileManager(new TileManager("maps/map3.txt", "maps/background2.txt"))
                .build();

        var enemies4 = List.of(new EnemyImpl(new PositionImpl(200, 250)),  new EnemyImpl(new PositionImpl(100, 430)));
        LevelModel level4 = new LevelBuilderImpl()
                .setSpawnPosition(new PositionImpl(0, 480))
                .setEndPoint(new PositionImpl(928, 64))
                .setEnemiesList(enemies4)
                .setPlayer(player)
                .setTileManager(new TileManager("maps/map4.txt", "maps/background3.txt"))
                .build();

        var enemies5 = List.of(new EnemyImpl(new PositionImpl(200, 200)),  new EnemyImpl(new PositionImpl(300, 50)));
        LevelModel level5 = new LevelBuilderImpl()
                .setSpawnPosition(new PositionImpl(0, 480))
                .setEndPoint(new PositionImpl(928, 64))
                .setEnemiesList(enemies5)
                .setPlayer(player)
                .setTileManager(new TileManager("maps/map5.txt", "maps/background4.txt"))
                .build();

        /**
         * Creation of the LevelController, which contains LevelModel implementation for each levels
         */
        List<LevelModel> levels = List.of(level1, level2, level3, level4, level5);
        LevelController levelController = new LevelController(levels);
        levelController.getCurrentLevel().getPlayer().setTileManager(levelController.getCurrentLevel().getTileManager());

        /**
         * Set up the first level
         */
        levelController.setUpLevel();

        GameController controller = new GameController(gameView, gameState, levelController.getCurrentLevel().getPlayer(), levelController, scoreManager);
        gameView.loadMap(levelController.getCurrentLevel().getTileManager());
        gameView.renderPlayer(levelController.getCurrentLevel().getPlayer());

        /* ------------ MENU RUNNABLES -------------*/
        menuView.setOnStart(() -> {
            controller.start();
            stage.setScene(gameView.getScene());
            gameView.clearKeyPressed();
            gameView.getRoot().requestFocus();
            player.resetPlayerStatus();
            BACKGROUND_MUSIC.loop();
        });

        menuView.setOnScore(() -> {
            scoreView.setScores(scoreManager.loadTopScores(3));
            stage.setScene(scoreView.getScene());
        });

        menuView.setOnRules(() -> {
            stage.setScene(rulesView.getScene());
        });

        menuView.setOnExit(() -> {
            Platform.exit();
        });
        /* ---------------------------------------*/

        /* ------------ SCORE RUNNABLES -------------*/
        scoreView.setOnReturn(() -> {
            stage.setScene(menuView.getScene());
        });

        scoreView.setOnClearScores(() -> {
            scoreManager.clearScores();
            scoreView.setScores(scoreManager.loadTopScores(3));
        });
        /* ---------------------------------------*/

        /* ------------ RULES RUNNABLES -------------*/
        rulesView.setOnReturn(() -> {
            stage.setScene(menuView.getScene());
        });
        /* ---------------------------------------*/

        /* ------------ CONTROLLER RUNNABLES -------------*/
        controller.setOnPause(() -> {
            stage.setScene(pauseView.getScene());
            BACKGROUND_MUSIC.stop();
        });

        controller.setOnVictory(() -> {
            stage.setScene(victoryView.getScene());
            controller.stop();
            gameView.clearKeyPressed();
            levelController.reset(gameView);
            BACKGROUND_MUSIC.stop();
        } );

        controller.setOnDeath(() -> {
            stage.setScene(gameOverView.getScene());
            controller.stop();
            gameView.clearKeyPressed();
            player.resetPlayerStatus();
            levelController.reset(gameView);
            BACKGROUND_MUSIC.stop();
        });
        /* ---------------------------------------*/

        /* ------------ GAME RUNNABLES -------------*/
        gameView.setOnKill(() -> {
            stage.setScene(gameOverView.getScene());
            controller.stop();
            gameView.clearKeyPressed();
            levelController.reset(gameView);
            BACKGROUND_MUSIC.stop();
        });

        gameView.setOnHit(() -> {
            if(player.getLives().getLives() == 1){
                stage.setScene(gameOverView.getScene());
                controller.stop();
                gameView.clearKeyPressed();
                levelController.reset(gameView);
                BACKGROUND_MUSIC.stop();
            } else {
                player.hit();
            }
        });
        /* ---------------------------------------*/

        /* ------------ PAUSE RUNNABLES -------------*/
        pauseView.setOnResume(() -> {
            controller.resume();
            stage.setScene(gameView.getScene());
            gameView.clearKeyPressed();
            gameView.getRoot().requestFocus();
            BACKGROUND_MUSIC.restart();
        });

        pauseView.setOnMenu(() -> {
            stage.setScene(menuView.getScene());
            levelController.reset(gameView);
            player.resetPlayerStatus();
            BACKGROUND_MUSIC.stop();
            scoreManager.saveScore(new ScoreRecord("Player", controller.getScoreManager()));
        });
        /* ---------------------------------------*/

        /* ------------ GAME OVER RUNNABLES -------------*/
        gameOverView.setOnMenu(() -> {
            stage.setScene(menuView.getScene());
            levelController.reset(gameView);
            player.resetPlayerStatus();
            BACKGROUND_MUSIC.stop();
            scoreManager.saveScore(new ScoreRecord("Player", controller.getScoreManager()));
        });
        /* -----------------------------------------------*/

        victoryView.setOnMenu(() -> {
            stage.setScene(menuView.getScene());
            levelController.reset(gameView);
            player.resetPlayerStatus();
            BACKGROUND_MUSIC.stop();
            scoreManager.saveScore(new ScoreRecord("Player", controller.getScoreManager()));
        });

        stage.setScene(menuView.getScene());
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}