package it.unibo.biscia.view.screens;

import it.unibo.biscia.core.Entity;
import it.unibo.biscia.core.Level;
import it.unibo.biscia.core.Player;
import it.unibo.biscia.events.ActionObserver;
import it.unibo.biscia.events.GenericEventSubject;
import it.unibo.biscia.events.GenericEventSubjectImpl;
import it.unibo.biscia.events.StateObserver;
import it.unibo.biscia.utils.fileIO.leaderboard.Leaderboard;
import it.unibo.biscia.utils.fileIO.leaderboard.LeaderboardImpl;
import it.unibo.biscia.view.View;
import it.unibo.biscia.view.actors.GameView;
import it.unibo.biscia.view.actors.GameViewImpl;
import it.unibo.biscia.view.actors.PlayerInfo;
import it.unibo.biscia.view.utils.PlayerOneProcessor;
import it.unibo.biscia.view.utils.PlayerProcessor;
import it.unibo.biscia.view.utils.PlayerTwoProcessor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * Main Implementation of {@link View}. It takes care of collecting user input
 * and implementing a {@link StateObserver}. It delegates the rendering of
 * objects part to {@link GameViewImpl}
 * 
 * @see GameView
 * @see PlayerProcessor
 *
 */
public class Game extends AbstractScreen implements View {

    private final GenericEventSubject<ActionObserver> actionSubject;
    private final Map<Player, PlayerInfo> players;
    private final Table table;
    private boolean isPaused;
    private final GameView gameView;
    private final InputAdapter stateProcessor = new InputAdapter() {
        public boolean keyDown(final int keycode) {
            switch (keycode) {
            case Keys.SPACE:
                actionSubject.notify(a -> a.pauseAndResume());
                break;
            case Keys.ESCAPE:
                if (isPaused) {
                    actionSubject.notify(a -> a.end());
                }
                break;
            default:
                break;
            }
            return false;
        };
    };
    private final Leaderboard leaderboard;

    public Game() {
        this.actionSubject = new GenericEventSubjectImpl<>();
        this.players = new HashMap<>();
        this.table = new Table();
        this.isPaused = false;
        this.gameView = new GameViewImpl();
        this.getInputMultiplexer().addProcessor(stateProcessor);
        this.leaderboard = new LeaderboardImpl();
    }

    @Override
    public final void show() {
        this.table.debugAll();
        this.table.setFillParent(true);
        this.table.top();
        this.getStage().addActor(table);
    }

    @Override
    public final void render(final float delta) {
        this.gameView.getStage().act();
        this.gameView.getStage().draw();
        super.render(delta);
    }

    @Override
    public final void attachActionObserver(final ActionObserver observer) {
        this.actionSubject.attach(observer);
    }

    @Override
    public final void detachActionObserver(final ActionObserver observer) {
        this.actionSubject.detach(observer);
    }

    @Override
    public final void gameOver() {
        players.keySet().stream().forEach(p -> leaderboard.update(p));
        Gdx.app.postRunnable(() -> this.getBiscia().setScreen(new MainMenu()));
    }

    @Override
    public final void newLevel(final Level level) {
        this.gameView.newLevel(level);
    }

    @Override
    public final void update(final List<Entity> entities) {
    }

    @Override
    public final void remove(final List<Entity> entities) {
        this.gameView.getEntityCrew().removeEntities(entities);
    }

    @Override
    public final void add(final List<Entity> entities) {
        this.gameView.getEntityCrew().addEntities(entities);
    }

    @Override
    public final void updatePlayer(final Player player) {
        this.players.get(player).reset(player);
    }

    @Override
    public final void setPlayers(final List<Player> players) {
        players.stream().forEach(p -> this.players.put(p, new PlayerInfo(p, getSkin())));
        this.players.values().stream().forEachOrdered(pI -> this.table.add(pI).expandX().fill());
        this.getInputMultiplexer().addProcessor(new PlayerOneProcessor(players.get(0), actionSubject));
        if (players.size() == 2) {
            this.getInputMultiplexer().addProcessor(new PlayerTwoProcessor(players.get(1), actionSubject));
        }
        this.table.pack();
        this.gameView.setPosition(0, this.table.getHeight());
    }

    @Override
    public void gamePause() {
    }

    @Override
    public void gameResume() {
    }

    @Override
    public final void dispose() {
        this.gameView.dispose();
        super.dispose();
    }

}
