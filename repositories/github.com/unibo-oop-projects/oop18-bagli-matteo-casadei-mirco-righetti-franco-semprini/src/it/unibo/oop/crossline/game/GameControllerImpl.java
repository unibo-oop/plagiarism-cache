package it.unibo.oop.crossline.game;

import java.util.Observable;
import java.util.Observer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import it.unibo.oop.crossline.game.screen.InitialScreen;

public class GameControllerImpl implements GameController, Observer {

    private final GameView view;
    private final GameModel model;

    public GameControllerImpl(final GameView view, final GameModel model) {
        this.view = view;
        this.model = model;

        model.addObserver(this);

        view.setTiledMap(model.getTiledMap());
        view.setWorld(model.getWorld());
        view.getCamera().setTarget(model.getPlayer().getBody());
        view.setPlayer(model.getPlayer());
        view.setEnemy(model.getCurrentWave());
    }

    @Override
    public void dispose() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public final void render(final float delta) {
        if (model.shouldExit()) {
            final Game game = (Game) Gdx.app.getApplicationListener();
            game.setScreen(new InitialScreen());
        } else {
            view.render(delta);
            model.update();
        }
    }

    @Override
    public void resize(final int width, final int height) {
    }

    @Override
    public void resume() {
    }

    @Override
    public void show() {
    }

    @Override
    public final GameView getView() {
        return view;
    }

    @Override
    public final GameModel getModel() {
        return model;
    }

    @Override
    public final void update(final Observable observable, final Object object) {
        if (observable instanceof GameModel) {
            final GameModel gameModel = (GameModel) observable;
            view.setEnemy(gameModel.getCurrentWave());
        }
    }
}
