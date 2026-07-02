package it.unibo.oop.supermario.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import it.unibo.oop.supermario.game.SuperMario;
import it.unibo.oop.supermario.gamemanager.GameManager;

/**
 * 
 * this class is the screen called everytime mario wins.
 *
 */
public final class WinScreen implements Screen {
    /**
     * the stage contains the actors.
     * */
    private final Stage stage;
    /**
     * game contains its proper spritebatch, so this screen do not need a new one.
     * */
    private final SuperMario game;
    /** 
     * the time passed by the start of this screen.
     * */
    private float stateTime;

    private final GameManager manager;

    private FitViewport viewport;

    /**
     * @param game contains its proper spritebatch, so this screen do not need a new one.
     */
    public WinScreen(final SuperMario game) {
        manager = GameManager.instance;
        this.game = game;
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport, new SpriteBatch());

        final Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(Gdx.files.internal("skin/fontMario.fnt")), Color.WHITE);

        final Table table = new Table();
        table.center();
        table.setFillParent(true);

        final Label gameOverLabel = new Label("CONGRATULATIONS, YOU WIN", font);
        final Label labelExit;
        if (GameManager.instance.getCurrentLevel() == 1) {
            labelExit = new Label("Press Enter to pass next level", font);
        } else {
            labelExit = new Label("Press Enter to play again", font);
        }

        table.add(gameOverLabel).expandX();
        table.row();
        table.add(labelExit).expandX().padTop(10);
        stage.addActor(table);
    }

    /**
     * -.
     */
    public void create() {
    }

    @Override
    public void show() {
    }

    @Override
    public void render(final float delta) {
        stateTime += delta;
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            if (this.manager.getCurrentLevel() == 1) {
                this.manager.setLevel(2);
                game.setScreen(new PlayScreen(game));
            } else {
                this.manager.setLevel(1);
                game.setScreen(new MainMenu(game));
            }
            GameManager.instance.getAssetManager().get("world_clear.wav", Sound.class).stop();
            dispose();
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void resize(final int width, final int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
