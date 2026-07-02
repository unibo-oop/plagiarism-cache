package it.unibo.oop.supermario.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import it.unibo.oop.supermario.game.SuperMario;
import it.unibo.oop.supermario.gamemanager.GameManager;

/**
 * 
 * The class that contains the settings of the game.
 *
 */

public class SettingScreen implements Screen {
    /** 
     * the stage where the actors are added.
     * */
    private final Stage stage;
    /** 
     * means the current label selected.
     * */
    private int currentItem;
    /** 
     * game contains its proper spritebatch, so this screen do not need a new one.
     * */
    private final SuperMario game;
    /**
     * this checkbox disable the music audio .
     */
    private final CheckBox audioCheckbox;
    /**
     * this label contains the string "done".
     */
    private final Label doneLabel;
    /**
     * this label contains the string "disable audio".
     */
    private final Label audioLabel;
    /**
     * contains the background image.
     */
    private final TextureRegion backgroundTexture;
    /**
     * used for the audio to stay disabled even after closing the program.
     */
    private final Preferences pref;
    /**
     * String used to disable audio.
     */
    private static final String DISABLE_AUDIO = "disableAudio";

    /**
     * 
     * @param game contains its proper spritebatch, so settingScreen do not need a new one
     */
    public SettingScreen(final SuperMario game) {
        final GameManager manager = GameManager.instance;
        this.pref = manager.getSetting();
        this.game = game;
        final Skin mySkin = new Skin(Gdx.files.internal(GameConstant.SKIN));
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
        audioCheckbox = new CheckBox(" ", mySkin);
        if (pref.getBoolean(DISABLE_AUDIO)) {
            audioCheckbox.setChecked(true);
        }
        audioLabel =  new Label("Disable music", font);
        doneLabel = new Label("Done", font);
        final Table table = new Table();
        table.center();
        table.setFillParent(true);
        table.add(audioLabel).expandX().padTop(50).padLeft(140);
        table.add(audioCheckbox).expandX().padRight(130).padTop(65);
        table.row();
        table.row();
        table.add(doneLabel).expandX().padTop(20).padLeft(175);
        stage.addActor(table);
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
     * 
     * @param delta is the delta time of the game.
     */
    public void update(final float delta) {

        handleInput();

        switch (currentItem) {

        case 1:
            audioLabel.setColor(Color.RED);
            doneLabel.setColor(Color.WHITE);
            break;

        case 2:
            audioLabel.setColor(Color.WHITE);
            doneLabel.setColor(Color.RED);
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

    /**
     * this method opens new screens depending by the currentItem value.
     */
    private void select() {
        if (currentItem == 1 && !pref.getBoolean(DISABLE_AUDIO)) {
            audioCheckbox.setChecked(true);
            pref.putBoolean(DISABLE_AUDIO, true);
            pref.flush();
        } else if (currentItem == 1) {
            audioCheckbox.setChecked(false);
            pref.putBoolean(DISABLE_AUDIO, false);
            pref.flush();
        }
        if (currentItem == 2) {
            game.setScreen(new MainMenu(game));
        }
    }

    public int getCurrentItem() {
        return currentItem;
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
    public final void dispose() {
        stage.dispose();
        game.batch.dispose();

    }

}
