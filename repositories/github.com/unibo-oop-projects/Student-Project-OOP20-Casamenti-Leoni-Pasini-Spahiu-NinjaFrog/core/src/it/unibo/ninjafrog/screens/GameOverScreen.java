package it.unibo.ninjafrog.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import it.unibo.ninjafrog.game.NinjaFrogGame;
import it.unibo.ninjafrog.game.utilities.GameConst;
import it.unibo.ninjafrog.game.utilities.SoundManager;

/**
 * Definition of a GameOverScreen, which is an implementation of Screen.
 * GameOverScreen is launched when the player died during the game.
 */
public final class GameOverScreen implements Screen {
    private final Stage stage;
    private final NinjaFrogGame game;
    private final Viewport viewport;
    private final Texture background;
    private final SoundManager sound;

    /**
     * Public constructor of a GameOverScreenobject.
     * 
     * @param game  NinjaFruitGame
     * @param score Integer score of the game.
     * @param sound SoundManager audio of the game.
     */
    public GameOverScreen(final NinjaFrogGame game, final Integer score, final SoundManager sound) {
        this.game = game;
        this.sound = sound;
        viewport = new FitViewport(GameConst.WIDTH, GameConst.HEIGHT, new OrthographicCamera());
        viewport.apply();
        stage = new Stage(viewport, game.getBatch());
        final Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        background = new Texture("GameOver_Screen.png");
        final Label scoreLabel = new Label("SCORE:" + score, font);
        final Label exitLabel = new Label("Press Enter to continue", font);
        final Table table = new Table();
        table.center();
        table.setFillParent(true);
        table.add(scoreLabel);
        table.row();
        table.add(exitLabel);
        stage.addActor(table);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(final float delta) {
        handleInput();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.getBatch().begin();
        game.getBatch().draw(background, 0, 0, GameConst.WIDTH, GameConst.HEIGHT);
        game.getBatch().end();
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
        game.getBatch().dispose();
    }

    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
            game.setScreen(new MainMenu(this.game, this.sound));
        }
    }

}
