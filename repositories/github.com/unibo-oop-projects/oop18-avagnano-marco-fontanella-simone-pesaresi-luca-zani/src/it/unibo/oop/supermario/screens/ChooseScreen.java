package it.unibo.oop.supermario.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import it.unibo.oop.supermario.game.SuperMario;
import it.unibo.oop.supermario.gamemanager.GameManager;

/**
 *
 */
public class ChooseScreen extends Game implements Screen {
    private final Stage stage;
    static int currentItem;
    private final SuperMario game;
    private final Label marioLabel;
    private final Label luigiLabel;
    private final TextureRegion backgroundTexture;
    private final Preferences pref;

    /**
     * 
     * 
     * @param game -.
     * @param manager -.
     */
    public ChooseScreen(final SuperMario game, final GameManager manager) {
        this.game = game;
        this.pref = manager.getSetting();
        final Viewport viewport = new FitViewport(GameManager.V_WIDTH, GameManager.V_HEIGHT, new OrthographicCamera());
        viewport.apply();
        stage = new Stage(viewport, game.batch);
        final OrthographicCamera camera = new OrthographicCamera();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        Gdx.input.setInputProcessor(stage);
        final LabelStyle font = new LabelStyle(new BitmapFont(), Color.WHITE);
        backgroundTexture = new TextureRegion(new Texture("background.jpg"));
        currentItem = 1;
        marioLabel =  new Label("Use Mario", font);
        luigiLabel = new Label("Use Luigi", font);
        final Table table = new Table();
        table.center();
        table.setFillParent(true);
        table.add(marioLabel).expandX().padTop(55);
        table.row();
        table.add(luigiLabel).expandX().padTop(20);
        stage.addActor(table);
    }
    @Override
    public void create() {
    }

    @Override
    public void show() {
    }

    @Override
    public final void render(final float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth() / 3f, Gdx.graphics.getHeight() / 3f);
        game.batch.end();
        stage.act();
        stage.draw();

    }

    /**
     * 
     * @param delta -.
     */
    public void update(final float delta) {
        handleInput();
        switch (currentItem) {

        case 1:
            marioLabel.setColor(Color.RED);
            luigiLabel.setColor(Color.WHITE);
            break;

        case 2:
            marioLabel.setColor(Color.WHITE);
            luigiLabel.setColor(Color.RED);
            break;

        default:
            break;
        }
    }

    /**
     * 
     */
    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
            if (currentItem == 1) {
                currentItem = 2;
            } else if (currentItem == 2) {
                currentItem = 1;
            }
        }
        if (Gdx.input.isKeyJustPressed(Keys.UP)) {
            if (currentItem == 1) {
                currentItem = 2;
            } else if (currentItem == 2) {
                currentItem = 1;
            }
        }
        if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
            select();
        }
    }

    private void select() {
        if (currentItem == 1) {
            pref.putString("skin", "Mario");
            game.setScreen(new PlayScreen(game));
        } 
        if (currentItem == 2) {
            pref.putString("skin", "Luigi");
            game.setScreen(new PlayScreen(game));
        }
    }

    /**
     * Resize the window.
     * @param width is the new width of the screen
     * @param height is the new height of the screen
     */
    public void resize(final int width, final int height) {
        Gdx.graphics.setDisplayMode(GameConstant.WIDTH_RESOLUTION,  GameConstant.HEIGHT_RESOLUTION, false);
    }

    @Override
    public void hide() {
    }

    public int getCurrentItem() {
        return currentItem;
    }
}
