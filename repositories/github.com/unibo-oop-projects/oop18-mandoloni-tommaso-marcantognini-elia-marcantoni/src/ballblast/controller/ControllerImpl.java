package ballblast.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;
import ballblast.controller.files.LeaderboardManager;
import ballblast.controller.files.SimpleLeaderboardManager;
import ballblast.controller.files.SimpleUserManager;
import ballblast.controller.files.UserManager;
import ballblast.controller.sound.Sound;
import ballblast.model.Model;
import ballblast.model.data.GameData;
import ballblast.model.data.Leaderboard;
import ballblast.model.data.UserData;
import ballblast.model.gameobjects.GameObject;
import ballblast.model.inputs.InputManager.PlayerTag;
import ballblast.model.inputs.InputType;
import ballblast.view.View;

/**
 * The implementation of the {@link Controller} in the MVC architecture.
 */
public class ControllerImpl implements Controller, GameLoopObserver {

    private final Model model;
    private final View view;
    private final UserManager userManager;
    private final Leaderboard leaderboard;
    private final LeaderboardManager lbManager;
    private GameLoop gameloop;
    private Optional<UserData> currentUser;
    private boolean musicStatus;
    private boolean soundStatus;

    /**
     * Create a new instance of {@link Controller}.
     * 
     * @param model The model of the MVC architecture.
     * @param view  The view of the MVC architecture.
     */
    public ControllerImpl(final Model model, final View view) {
        DirectoryManager.setupApplication();
        this.model = model;
        this.view = view;
        this.userManager = new SimpleUserManager();
        this.currentUser = Optional.empty();
        this.lbManager = new SimpleLeaderboardManager();
        this.leaderboard = this.lbManager.loadSurvivalLeaderboard().get();
        this.musicStatus = true;
        this.soundStatus = true;
        Sound.loadSounds();
    }

    @Override
    public final void startSurvivalMode() {
        this.createGameLoop();
        this.model.startSurvival();
        this.gameloop.start();
        this.startMusic();
    }

    @Override
    public final void pauseGame() {
        this.gameloop.pause();
    }

    @Override
    public final void resume() {
        this.gameloop.resumeLoop();
    }

    @Override
    public final void notifyGameOver() {
        this.gameloop.stopLoop();
    }

    @Override
    public final void receiveInput(final PlayerTag tag, final InputType input) {
        this.gameloop.addInput(tag, input);
    }

    @Override
    public final List<GameObject> getGameObjects() {
        return this.model.getGameObjects();
    }

    @Override
    public final GameData getGameData() {
        return this.model.getGameData();
    }

    @Override
    public final boolean checkLoginUser(final String username, final String password)
            throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        this.currentUser = this.userManager.login(username, password);
        return this.currentUser.isPresent();
    }

    @Override
    public final boolean checkRegisterUser(final String username, final String password)
            throws ParserConfigurationException, IOException, TransformerException, SAXException {
        this.currentUser = this.userManager.register(username, password);
        return this.currentUser.isPresent();
    }

    @Override
    public final UserData getCurrentUser() {
        return this.currentUser.get();
    }

    @Override
    public final Leaderboard getLeaderboard() {
        return this.lbManager.loadSurvivalLeaderboard().get();
    }

    @Override
    public final void updateLeaderboard() {
        this.leaderboard.addRecord(currentUser.get().getName(), this.model.getGameData().getScore());
        this.lbManager.saveSurvivalLeaderboard(leaderboard);
        this.currentUser.get().addGameData(this.model.getGameData());
        this.updateStats();
    }

    @Override
    public final void updateStats() {
        this.userManager.updateUserData(this.currentUser.get());
    }

    @Override
    public final void setMusic(final boolean isMusicOn) {
        this.musicStatus = isMusicOn;
    }

    @Override
    public final void setSoundEffects(final boolean isSoundOn) {
        this.soundStatus = isSoundOn;
    }

    @Override
    public final boolean isMusicOn() {
        return this.musicStatus;
    }

    @Override
    public final boolean isSoundOn() {
        return this.soundStatus;
    }

    private void createGameLoop() {
        this.gameloop = this.soundStatus
                ? new SoundGameLoop(this.model, this.view, this.currentUser.get().getFramesPerSecond())
                : new SimpleGameLoop(this.model, this.view, this.currentUser.get().getFramesPerSecond());
        this.gameloop.addObserver(this);
    }

    private void startMusic() {
        if (this.musicStatus) {
            Sound.THEME.loopSound();
        }
    }

}
