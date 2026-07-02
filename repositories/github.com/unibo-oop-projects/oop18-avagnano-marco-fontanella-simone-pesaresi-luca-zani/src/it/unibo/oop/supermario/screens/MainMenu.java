package it.unibo.oop.supermario.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import it.unibo.oop.supermario.game.SuperMario;
import it.unibo.oop.supermario.gamemanager.GameManager;

/** -.
 * 
 *
 */
public class MainMenu extends Game implements Screen  {

    /** 
     * The viewport of the screen.
     * */
    private final Viewport viewport;
    /** 
     * the stage where the actors are added.
     * */
    private final Stage stage;
    /** 
     * The label whith the "play" string.
     * */
    private final Label playLabel;
    /**
     * the label whith the "settings" string.
     * */
    private final Label settingsLabel;
    /** 
     * the label whith the "quit" string.
     * */
    private final Label quitLabel;
    /** 
     * means the current label selected.
     * */
    private int currentLabel;
    /** 
     * the camera of the screen.
     * */
    private final OrthographicCamera camera;
    /**
     * the image on the background.
     * .*/
    private final TextureRegion backgroundTexture;
    /** 
     * game contains its proper spritebatch, so this screen do not need a new one.
     * */
    private final SuperMario game;
    /** 
     * the manager of the game.
     * */
    private final GameManager manager;

    /**
     * 
     * @param game contains its proper spritebatch, so settingScreen do not need a new one.
     */
    public MainMenu(final SuperMario game) {

        manager = GameManager.instance;
        this.game = game;
        viewport = new FitViewport(GameManager.V_WIDTH, GameManager.V_HEIGHT, new OrthographicCamera());
        viewport.apply();
        stage = new Stage(viewport, game.batch);
        camera = new OrthographicCamera();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        Gdx.input.setInputProcessor(stage);
        final Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        backgroundTexture = new TextureRegion(new Texture("background.jpg"));
        currentLabel = 1;
        playLabel = new Label("Play", font);
        settingsLabel = new Label("Settings", font);
        quitLabel = new Label("Exit", font);
        final Table table = new Table();
        table.center();
        table.setFillParent(true);
        table.add(playLabel).expandX().padTop(55);
        table.row();
        table.add(settingsLabel).expandX().padTop(10);
        table.row();
        table.add(quitLabel).expandX().padTop(10);
        stage.addActor(table);
    }

    @Override
    public final void render(final float delta) {
        update(delta);
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

        switch (currentLabel) {

        case 1:
            playLabel.setColor(Color.RED);
            settingsLabel.setColor(Color.WHITE);
            quitLabel.setColor(Color.WHITE);
            break;

        case 2:
            playLabel.setColor(Color.WHITE);
            settingsLabel.setColor(Color.RED);
            quitLabel.setColor(Color.WHITE);
            break;

        case 3: 
            playLabel.setColor(Color.WHITE);
            settingsLabel.setColor(Color.WHITE);
            quitLabel.setColor(Color.RED);
            break;
        default:
            break;

        }

    }

    /**
     * Handles the keyboard input.
     */
    public void handleInput() {
        if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
            if (currentLabel == 1) {
                currentLabel = 2;
            } else if (currentLabel == 2) {
                currentLabel = 3;
            } else if (currentLabel == 3) {
                currentLabel = 1;
            }
        }
        if (Gdx.input.isKeyJustPressed(Keys.UP)) {
            if (currentLabel == 1) {
                currentLabel = 3;
            } else if (currentLabel == 2) {
                currentLabel = 1;
            } else if (currentLabel == 3) {
                currentLabel = 2;
            }
        }
        if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
            select();
        }
    }

    /**
     * this method opens new screens depending by the currentItem value.
     */
    public void select() {
        switch (currentLabel) {
        case 1:
            game.setScreen(new ChooseScreen(game, manager));
            break;
        case 2:
            game.setScreen(new SettingScreen(game));
            break;
        case 3:
            Gdx.app.exit();
            break;
        default:
            break;
        }
    }

    public int getCurrentLabel() {
        return currentLabel;
    }
    
    public void setCurrentLabel(int value) {
        currentLabel= value;
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
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void show() {
    }

    @Override
    public final void dispose() {
        stage.dispose();
        game.batch.dispose();
    }

    @Override
    public void create() {
    }
}
