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
 * Definition of a MainMenu, which is an implementation of Screen. MainMenu is
 * the general menu and the first one that you launch when the game started.
 */
public final class MainMenu implements Screen {
    private static final int SELECTOR_WIDTH = 150;
    private static final int SELECTOR_HEIGHT_FIRST = 115;
    private static final int SELECTOR_HEIGHT_SECOND = 95;
    private static final int SELECTOR_HEIGHT_THIRD = 70;
    private final NinjaFrogGame game;
    private final Stage stage;
    private final Viewport viewport;
    private final Label playLabel;
    private final Label settingsLabel;
    private final Label quitLabel;
    private int currentLabel;
    private final Texture background;
    private final Texture selector;
    private final SoundManager sound;

    /**
     * Public constructor of MainMenu object.
     * 
     * @param game  NinjaFrogGame
     * @param sound SoundManager audio of the game.
     */
    public MainMenu(final NinjaFrogGame game, final SoundManager sound) {
        this.game = game;
        this.sound = sound;
        sound.playMenuSong();
        viewport = new FitViewport(GameConst.WIDTH, GameConst.HEIGHT, new OrthographicCamera());
        viewport.apply();
        stage = new Stage(viewport, game.getBatch());
        Gdx.input.setInputProcessor(stage);
        background = new Texture("Menu1background.png");
        selector = new Texture("Selector.png");
        final Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        currentLabel = 1;
        playLabel = new Label("Play", font);
        settingsLabel = new Label("Setting", font);
        quitLabel = new Label("Exit", font);
        final Table table = new Table();
        table.center();
        table.setFillParent(true);
        table.row();
        table.add(playLabel);
        table.row();
        table.add(settingsLabel);
        table.row();
        table.add(quitLabel);
        stage.addActor(table);
    }

    @Override
    public void render(final float delta) {
        handleInput();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.getBatch().begin();
        game.getBatch().draw(background, 0, 0, GameConst.WIDTH, GameConst.HEIGHT);
        switch (currentLabel) {
        case 1:
            playLabel.setColor(Color.RED);
            settingsLabel.setColor(Color.WHITE);
            quitLabel.setColor(Color.WHITE);
            game.getBatch().draw(selector, SELECTOR_WIDTH, SELECTOR_HEIGHT_FIRST);
            break;
        case 2:
            playLabel.setColor(Color.WHITE);
            settingsLabel.setColor(Color.RED);
            quitLabel.setColor(Color.WHITE);
            game.getBatch().draw(selector, SELECTOR_WIDTH, SELECTOR_HEIGHT_SECOND);
            break;
        case 3:
            playLabel.setColor(Color.WHITE);
            settingsLabel.setColor(Color.WHITE);
            quitLabel.setColor(Color.RED);
            game.getBatch().draw(selector, SELECTOR_WIDTH, SELECTOR_HEIGHT_THIRD);
            break;
        default:
            break;
        }
        game.getBatch().end();
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(final int width, final int height) {
        this.viewport.update(width, height);
    }

    @Override
    public void show() {
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
        game.getBatch().dispose();
        stage.dispose();
    }

    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Keys.DOWN) || Gdx.input.isKeyJustPressed(Keys.S)) {
            switch (currentLabel) {
            case 1:
                currentLabel = 2;
                break;
            case 2:
                currentLabel = 3;
                break;
            case 3:
                currentLabel = 1;
                break;
            default:
                break;
            }
        }
        if (Gdx.input.isKeyJustPressed(Keys.UP) || Gdx.input.isKeyJustPressed(Keys.W)) {
            switch (currentLabel) {
            case 1:
                currentLabel = 3;
                break;
            case 2:
                currentLabel = 1;
                break;
            case 3:
                currentLabel = 2;
                break;
            default:
                break;
            }
        }
        if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
            setMenu();
        }
    }

    private void setMenu() {
        switch (currentLabel) {
        case 1:
            this.game.setScreen(new LevelsMenu(this.game, this.sound));
            break;
        case 2:
            this.game.setScreen(new SettingsMenu(this.game, this.sound));
            break;
        case 3:
            Gdx.app.exit();
            break;
        default:
            break;
        }

    }
}
