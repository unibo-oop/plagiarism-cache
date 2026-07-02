package controller;

import java.util.Optional;

import view.AudioPlayer;
import view.GameOverView;
import view.Sound;
import view.View;
import view.ViewImpl;

/**
 * 
 * Implementation of MainController.
 *
 */
public final class MainControllerImpl implements MainController {

    private final View view = new ViewImpl();
    private final Leaderboard leaderboard = new Leaderboard();
    private final AudioPlayer audioPlayer = new AudioPlayer();
    private final KeyboardInput input;
    private Optional<GameControllerImpl> activeGameEngine = Optional.empty();

    /**
     * 
     */
    public MainControllerImpl() {
        input = new KeyboardInput(view.getScene(), this);
        menu();
    }

    @Override
    public void menu() {
        activeGameEngine = Optional.empty();
        audioPlayer.playMusic(Sound.MENUTRACK.getPath());
        GameState.getGameState().setState(StateList.MENU);
        new MenuControllerImpl(view, this, audioPlayer, leaderboard);
    }

    @Override
    public void newGame() {
        audioPlayer.playMusic(Sound.GAMETRACK.getPath());
        GameState.getGameState().setState(StateList.INGAME);
        activeGameEngine = Optional.of(new GameControllerImpl(view, input, this, audioPlayer));
    }

    @Override
    public void pause() {
        audioPlayer.pauseMusic();
        GameState.getGameState().setState(StateList.PAUSE);
        activeGameEngine.get().pause();
    }

    @Override
    public void continueGame() {
        audioPlayer.resumeMusic();
        GameState.getGameState().setState(StateList.INGAME);
        activeGameEngine.get().start();

    }

    @Override
    public void gameOver(final int score) {
        activeGameEngine.get().stop();
        audioPlayer.pauseMusic();
        GameState.getGameState().setState(StateList.GAMEOVER);
        new GameOverView(view, this, score, leaderboard);
    }

    @Override
    public void exit() {
        activeGameEngine = Optional.empty();
        System.exit(0);
    }
}
