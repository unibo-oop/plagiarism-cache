package it.unibo.arkanoid.view;

import java.util.List;

import it.unibo.arkanoid.audio.SongLoop;
import it.unibo.arkanoid.controller.Controller;
import it.unibo.arkanoid.controller.ControllerImpl;
import it.unibo.arkanoid.controller.GameStats;
import it.unibo.arkanoid.subject.Subject;
import it.unibo.arkanoid.view.controllers.GameScreenController;
import it.unibo.arkanoid.view.controllers.SubViewController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Implementation of {@link View}.
 *
 */
public final class ViewImpl extends Application implements View {

    private final SubViewFactory factory;
    private final Controller controller;
    private SubViewController currentSubview;
    private Stage stage;
    private SongLoop songLoop;

    /**
     * ViewImpl constructor.
     * 
     */
    public ViewImpl() {
        playSongLoop(new SongLoop());
        factory = new SubviewFactoryImpl();
        this.controller = new ControllerImpl();
    }

    private void renderImpl(final List<Subject> subjectList) {
        final Drawer drawer = ((GameScreenController) this.currentSubview).getDrawer();
        drawer.clear();
        drawer.render(subjectList);
    }

    @Override
    public void render(final List<Subject> subjectList) {
        if (this.currentSubview instanceof GameScreenController) {
            Platform.runLater(() -> this.renderImpl(subjectList));
        }
    }

    @Override
    public void setSubView(final SubView subview) {
        switch (subview) {
        case GAME_FINISHED:
            this.currentSubview = factory.createGameFinished();
            break;
        case GAME_OVER:
            this.currentSubview = factory.createGameOver();
            break;
        case HOME:
            this.currentSubview = factory.createHome();
            break;
        case IN_GAME:
            this.currentSubview = factory.createGameScreen();
            break;
        case LEVEL_END:
            this.currentSubview = factory.createLevelWin();
            break;
        case SCORES:
            this.currentSubview = factory.createScoresView();
            break;
        default:
            break;
        }
        this.currentSubview.init(this.controller, this);
        Platform.runLater(() -> {
            if (this.stage.getScene() == null) {
                this.stage.setScene(new Scene(this.currentSubview.getRoot()));
                this.stage.setMaximized(true);
            } else {
                this.stage.getScene().setRoot(this.currentSubview.getRoot());
            }
            this.currentSubview.getRoot().requestFocus();
        });
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        this.stage.show();
        this.controller.setView(this);
    }

    @Override
    public void displayGameStats(final GameStats gameStats) {
        if (this.currentSubview instanceof GameScreenController) {
            Platform.runLater(() -> {
                ((GameScreenController) this.currentSubview).setScore(gameStats.getScore());
                ((GameScreenController) this.currentSubview).setLives(gameStats.getLives());
            });
        }
    }

    @Override
    public SongLoop getSongLoop() {
        return this.songLoop;
    }

    private void playSongLoop(final SongLoop songLoop) {
        this.songLoop = songLoop;
        songLoop.play("/TRACK/Windows_95_slow_4000%.mp3");
    }
}
