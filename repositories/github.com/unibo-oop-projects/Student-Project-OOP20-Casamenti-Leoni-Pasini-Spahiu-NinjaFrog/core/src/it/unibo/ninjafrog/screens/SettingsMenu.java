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
 * Definition of a SettingsMenu, which is an implementation of Screen.
 * SettingsMenu is a menu where you can set the game music.
 */
public final class SettingsMenu implements Screen {
    private static final int SELECTOR_WIDTH = 140;
    private static final int SELECTOR_HEIGHT_FIRST = 105;
    private static final int SELECTOR_HEIGHT_SECOND = 85;
    private final Stage stage;
    private final NinjaFrogGame game;
    private final Label musicLabel;
    private final Label exit;
    private final Viewport viewport;
    private int currentLabel;
    private final Texture selector;
    private final Texture background;
    private final SoundManager sound;

    /**
     * Public constructor of a SettingsMenu object.
     * 
     * @param game  NinjaFrogGame
     * @param sound SoundManager audio of the game.
     */
    public SettingsMenu(final NinjaFrogGame game, final SoundManager sound) {
        this.game = game;
        this.sound = sound;
        viewport = new FitViewport(GameConst.WIDTH, GameConst.HEIGHT, new OrthographicCamera());
        viewport.apply();
        stage = new Stage(viewport, game.getBatch());
        Gdx.input.setInputProcessor(stage);
        final Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        selector = new Texture("Selector.png");
        background = new Texture("Menu2background.png");
        musicLabel = new Label("Music: ", font);
        exit = new Label("Back", font);
        currentLabel = 1;
        final Table table = new Table();
        table.center();
        table.setFillParent(true);
        table.add(musicLabel);
        table.row();
        table.add(exit);
        stage.addActor(table);
    }

    @Override
    public void render(final float delta) {
        handleInput();
        updateMusicLabel();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.getBatch().begin();
        game.getBatch().draw(background, 0, 0, GameConst.WIDTH, GameConst.HEIGHT);
        switch (currentLabel) {
        case 1:
            musicLabel.setColor(Color.RED);
            exit.setColor(Color.WHITE);
            game.getBatch().draw(selector, SELECTOR_WIDTH, SELECTOR_HEIGHT_FIRST);
            break;
        case 2:
            musicLabel.setColor(Color.WHITE);
            exit.setColor(Color.RED);
            game.getBatch().draw(selector, SELECTOR_WIDTH, SELECTOR_HEIGHT_SECOND);
            break;
        default:
            break;
        }
        game.getBatch().end();
        stage.act();
        stage.draw();
    }

    @Override
    public void show() {
    }

    @Override
    public void resize(final int width, final int height) {
        this.viewport.update(width, height);
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
        if (Gdx.input.isKeyJustPressed(Keys.DOWN) || Gdx.input.isKeyJustPressed(Keys.UP)
                || Gdx.input.isKeyJustPressed(Keys.S) || Gdx.input.isKeyJustPressed(Keys.W)) {
            if (currentLabel == 1) {
                currentLabel = 2;
            } else {
                currentLabel = 1;
            }
        }
        if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
            setStatus();
        }
    }

    private void updateMusicLabel() {
        if (sound.isState()) {
            musicLabel.setText("Music: ON");
        } else {
            musicLabel.setText("Music: OFF");
        }
    }

    private void setStatus() {
        switch (currentLabel) {
        case 1:
            this.sound.changeState();
            updateMusicLabel();
            break;
        case 2:
            this.game.setScreen(new MainMenu(this.game, this.sound));
            break;
        default:
            break;
        }
    }

}
