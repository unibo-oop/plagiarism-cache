package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jbox2d.common.Vec2;

import com.google.common.eventbus.Subscribe;

import common.CommonStrings;
import common.EventBusConnection;
import common.Log;
import common.MsgStrings;
import common.events.CoinEvent;
import common.events.EscEvent;
import common.events.SceneEvent;
import enumerators.Level;
import enumerators.PlayerCharacter;
import enumerators.SceneType;
import javafx.scene.Scene;
import model.GameModel;
import model.user.CurrentUser;
import model.user.UserDataInterface;
import view.GameView;
import view.PlayerInputManager;

/**
 * The game main controller.
 */
public final class GameController extends EventBusConnection implements GameControllerInterface {

    private static final GameController INSTANCE = new GameController();

    private static final Vec2 PLAYER_POSITION = new Vec2(CommonStrings.WINDOW_WIDTH / 2,
            (float) (CommonStrings.WINDOW_HEIGHT * 0.1));
    private static final long FPS = 60;

    /**
     * Height the player has to reach to win.
     */
    public static final double VICTORY_HEIGHT = 3000f;

    private List<Generator> generators;
    private PlayerInputManager input;
    private HUD hud;
    private GameModel model;
    private GameView view;

    private int step;
    private Level level;
    private boolean victory;
    private boolean firstTime;

    private GameController() {
        super();
        firstTime = true;
    }

    /**
     * Returns the singleton instance of the GameController.
     * 
     * @return instance
     */
    public static GameController getInstance() {
        return INSTANCE;
    }

    @Override
    public void initNewGame(final PlayerCharacter playerCharacter, final Optional<Level> optionalLevel) {
        if (optionalLevel.isPresent()) {
            this.level = optionalLevel.get();
            if (firstTime) {
                Log.add("FIRST TIME LEVEL");
                view = new GameView(FPS, this);
                model = new GameModel(FPS);
                hud = new HUDImpl();
                input = new PlayerInputManager();
                generators = new ArrayList<>();
                generators.add(new PlatformGenerator());
                generators.add(new EnemyGenerator());
                generators.add(new CoinGenerator());
                firstTime = false;
            }
            view.init(level);
            model.init(level);
            model.createPlayer(playerCharacter, PLAYER_POSITION);
            final UserDataInterface data = CurrentUser.getInstance().getUser().getUserData();
            hud.init(data.getMaxHeight(), 0);
            input.addListeners(view.getScene());
            generators.forEach(g -> g.init(level));
            victory = false;
            view.timerStart();
        } else {
            throw new IllegalArgumentException("Null level exception");
        }
    }

    @Override
    public void tick() {
        step++;
        model.updateWorld();
        view.getCamera().update();
        model.updateEntities();
        generators.forEach(g -> g.update());

        final double currentPhyY = model.getPlayer().getPhysicPosition().y;
        checkVictory(currentPhyY);

        hud.setMaxHeight((int) Math.round(model.getMaxHeight()));

        model.checkEntitiesToDelete();
        if (model.isPlayerDead()) {
            gameOver();
            getBus().post(new SceneEvent(SceneType.END_GAME));
        }
    }

    @Override
    public void resumeGame() {
        view.timerStart();
        input.addListeners(view.getScene());
    }

    @Override
    public void stopGame() {
    }

    /**
     * Pauses the game.
     */
    public void pauseGame() {
        view.timerStop();
        input.removeListeners(view.getScene());
    }

    /**
     * Handles game over. Resets world, stops the timer and removes the input
     * listeners. It also saves the score.
     */
    public void gameOver() {
        view.timerStop();
        model.resetWorld();
        input.removeListeners(view.getScene());
        CurrentUser.getInstance().getUser().getUserData().addCoin(hud.getCoins());
        CurrentUser.getInstance().getUser().getUserData().setMaxHeight(hud.getMaxHeight());
        Log.add("Max = " + hud.getMaxHeight());
        FileUserManager.update(CurrentUser.getInstance().getUser());
        Log.add("Game over");
    }

    private void checkVictory(final double currentY) {
        if (currentY >= VICTORY_HEIGHT && !victory) {
            int unlockedLevel = CurrentUser.getInstance().getUser().getUserData().getLevel().ordinal();
            try {
                CurrentUser.getInstance().getUser().getUserData().setLevel(Level.values()[unlockedLevel + 1]);
            } catch (ArrayIndexOutOfBoundsException ex) {
                Log.add("All levels are unlocked");
            }
            victory = true;
        }
    }

    @Override
    public int getStep() {
        return step;
    }

    @Override
    public GameView getGameView() {
        return this.view;
    }

    @Override
    public GameModel getGameModel() {
        return model;
    }

    @Override
    public Level getLevel() {
        return level;
    }

    @Override
    public void setLevel(final Level level) {
        this.level = level;
    }

    @Override
    public Scene getScene() {
        return view.getScene();
    }

    @Override
    public void sendMsg(final String msg) {
        switch (msg) {
        case MsgStrings.END_GAME:
            gameOver();
            getBus().post(new SceneEvent(SceneType.END_GAME));
            break;
        case MsgStrings.PAUSE:
            break;
        default:
            break;
        }
    }

    @Override
    @Subscribe
    public void handleKeyEvent(final EscEvent event) {
        Log.add("PAUSE");
        this.pauseGame();
        // this.sendMsg(MsgStrings.PAUSE);
        getBus().post(new SceneEvent(SceneType.GAME_MENU));
    }

    /**
     * Handle the coin event and adds the coin value to the score.
     * @param event - the CoinEvent
     */
    @Subscribe
    public void handleCoinEvent(final CoinEvent event) {
        this.hud.addCoins(event.getCoinValue());
    }
}
