package javagotchi.controller.minigame;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.Pair;

import javagotchi.controller.home.HomeController;
import javagotchi.controller.minigame.file.SavedData;
import javagotchi.controller.minigame.file.SavedDataImpl;
import javagotchi.controller.minigame.main.MiniGame;
import javagotchi.model.minigame.MiniModel;
import javagotchi.model.minigame.MiniModelImpl;
import javagotchi.model.minigame.ScoreImpl;
import javagotchi.model.minigame.TimeImpl;
import javagotchi.utility.Utility;
import javagotchi.view.minigame.FactoryView;
import javagotchi.view.minigame.MiniView;
import javagotchi.view.minigame.MiniViewImpl;
import javagotchi.view.minigame.component.GameButton;

/**
 * 
 * @author marica
 *
 */
public final class ControllerMiniGameImpl implements ControllerMiniGame {

    private final MiniView view = new MiniViewImpl();
    private final MiniModel model = new MiniModelImpl();
    private SavedData savedData;
    private HomeController hc;

    private static class LazyController {
        private static final ControllerMiniGame CONTROLLER = new ControllerMiniGameImpl();
    }

    private ControllerMiniGameImpl() {
    }

    /**
     * Gets Controller of MiniGame instance.
     * 
     * @return Controller instance
     */
    public static ControllerMiniGame getInstance() {
        return LazyController.CONTROLLER;
    }

    @Override
    public void newGame() {
        model.initModel();
        view.setViewMiniGame(FactoryView.createViewMiniGame());
        view.getViewMiniGame().display();
        this.getSavedData().deleteGameSaved();
    }

    @Override
    public void startGame() {
        model.setGameState(MiniModel.GameState.START);
        model.getTime().start();
        check();
    }

    @Override
    public void playGame() {
        this.move();
        model.getScore().setCurrentScore();
        view.updateScoreGui(model.getScore().getStringScore());
        view.getViewMiniGame().repaintGameView();
    }

    private void move() {
        model.getGameGrid().move();
        final Iterator<Pair<Integer, Integer>> it = model.getGameGrid().getCoords().iterator();
        view.getViewMiniGame().getButtons().forEach(b -> b.setCoord(it.next()));
    }

    @Override
    public void pauseGame() {
        view.getViewMiniGame().reActive(false);
        if (model.isGameState(MiniModel.GameState.START)) {
            model.getTime().stopTimer();
        }
        model.setGameState(MiniModel.GameState.PAUSE);
        FactoryView.createViewPause().display();
        check();
    }

    @Override
    public void resumeGame() {
        view.getViewMiniGame().reActive(true);
        if (model.getTime().isTimeGame()) {
            model.setTime(model.getTime().restart());
            model.getTime().start();
            model.setGameState(MiniModel.GameState.START);
        } else if (model.getTime().isStartTime()) {
            model.setGameState(MiniModel.GameState.NULL);
        } else {
            FactoryView.createDialogGameOver();
            model.setGameState(MiniModel.GameState.OVER);
        }
        check();
    }

    @Override
    public void endGame() {
        model.setGameState(MiniModel.GameState.OVER);
        check();
        model.getTime().stopTimer();
        FactoryView.createDialogGameOver();
    }

    @Override
    public void resetGame() {
        view.getViewMiniGame().disposeWindow();
        this.newGame();
    }

    @Override
    public void saveGame() {
        this.getSavedData().writeGame(model.getScore().getCurrentScore(), view.getViewMiniGame().getButtons(),
                model.getTime().getSeconds());
        view.getViewMiniGame().disposeWindow();
        view.getMainMenu().setEnableContinueGame(true);
        view.getMainMenu().display();
    }

    @Override
    public void continueGame() {
        this.setSavedGame(this.savedData.readGameSaved());
        model.setGameState(MiniModel.GameState.START);
        view.getViewMiniGame().reActive(true);
        model.getTime().start();
        view.getViewMiniGame().display();
        check();
    }

    @SuppressWarnings("unchecked")
    private void setSavedGame(final List<Object> savedDataGame) { 
        if (view.getViewMiniGame() == null) {
            model.setScore(new ScoreImpl((Integer) savedDataGame.get(0)));
            view.setViewMiniGame(FactoryView.createViewMiniGame(((List<GameButton>) savedDataGame.get(1))));
        }
        model.setTime(new TimeImpl((Integer) savedDataGame.get(2)));
    }

    @Override
    public void backToMenu() {
        check();
        if (model.isGameState(MiniModel.GameState.OVER)) {
            this.getSavedData().deleteGameSaved();
            view.getMainMenu().setEnableContinueGame(false);
        }
        view.getViewMiniGame().disposeWindow();
        view.getMainMenu().display();
    }

    @Override
    public void exit() {
        MiniGame.getFactoryController().getMusic().stopAudio();
        this.hc.getTimers().start();
    }

    @Override
    public MiniModel getModel() {
        return model;
    }

    @Override
    public MiniView getView() {
        return view;
    }

    @Override
    public SavedData getSavedData() {
        if (savedData == null) {
            savedData = new SavedDataImpl(model.getGotchi().getInformation().getName());
        }
        return savedData;
    }

    @Override
    public void setHomeController(final HomeController hc) {
        this.hc = hc;
    }

    @Override
    public HomeController getHomeController() {
        return this.hc;
    }

    private void check() {
        Utility.log("Controller: " + model.toString());
    }
}
