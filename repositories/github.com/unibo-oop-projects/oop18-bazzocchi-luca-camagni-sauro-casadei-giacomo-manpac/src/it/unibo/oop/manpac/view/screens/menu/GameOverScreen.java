package it.unibo.oop.manpac.view.screens.menu;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import it.unibo.oop.manpac.controller.ControllerObserver;
import it.unibo.oop.manpac.utils.SoundManager;
import it.unibo.oop.manpac.utils.UtilsForUI;
import it.unibo.oop.manpac.utils.SoundManager.SoundName;

/**
 * Implementation of ObservableScreen for the Game Over Screen (either win or
 * lose).
 */
public final class GameOverScreen implements ObservableScreen {

    //volume of game_over in percentage
    private static final int VOLUME_GAME_OVER = 30;

    private static final int VOLUME_GAME_OVER_MENU = 100;
    private static final float VELOCITY_GAME_OVER_MENU = 1f;

    private final ControllerObserver controller;
    private final List<TextButton> buttons;
    private final Table mainTable;
    private final Camera camera;
    private final Viewport viewport;
    private final Stage stage;
    private final BitmapFont bitmapFont;
    private Label gameOverLabel;
    private final GUIFactory guiFact;

    private ScreenObserver screenObserver;

    /**
     * Constructor of GameOverScreen class.
     * 
     * @param controller the controller of the application
     * @param winner     a boolean that represents if the player has won the level
     *                   or not
     * @param currentScore the current score
     * @param currentHighScore the current highScore
     */
    public GameOverScreen(final ControllerObserver controller, final boolean winner, final int currentScore,
            final int currentHighScore) {
        this.controller = controller;
        final boolean win = winner;
        this.buttons = new ArrayList<>();
        // Setting the view
        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(UtilsForUI.WIDTH_DEFAULT, UtilsForUI.HEIGHT_DEFAULT, camera);
        this.guiFact = new GUIFactoryImpl();
        this.viewport.apply();

        this.camera.position.set(this.viewport.getWorldWidth() / 2, this.viewport.getWorldHeight() / 2, 0);
        this.camera.update();

        this.stage = new Stage(this.viewport);

        // Create Table
        this.mainTable = new Table();
        // Set table to fill stage
        this.mainTable.setFillParent(true);
        // Set alignment of contents in the table.
        this.mainTable.top();
        // Set distance between buttons
        this.mainTable.defaults().pad(UtilsForUI.DISTANCE_BUTTONS_DEFAULT);
        // Set background
        final Texture backgroundTexture = new Texture("Backgrounds/background_Beige.png");
        this.mainTable.background(new TextureRegionDrawable(new TextureRegion(backgroundTexture)));

        // creating font
        this.bitmapFont = this.guiFact.createBitmapFont("Fonts/crackman.fnt");
        this.bitmapFont.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
        this.bitmapFont.setColor(Color.BLACK);
        this.bitmapFont.getData().setScale(UtilsForUI.DEFAULT_GAMEOVER_SCREEN_SCALE);

        SoundManager.getSoundManager().setVelocity(VELOCITY_GAME_OVER_MENU).setVolume(VOLUME_GAME_OVER_MENU);

        if (win) {
            this.winScreen(currentScore, currentHighScore);
        } else {

            SoundManager.getSoundManager()
                        .setVolume(VOLUME_GAME_OVER)
                        .play(SoundName.GAME_OVER)
                        .setVolume(VOLUME_GAME_OVER_MENU);

            this.loseScreen(currentScore, currentHighScore);
        }

    }

    private void loseScreen(final int currentScore, final int currentHighScore) {
        this.gameOverLabel = this.guiFact.createLabel("GAME OVER", this.bitmapFont, Color.RED);
        this.gameOverLabel.setFontScale(UtilsForUI.GAMEOVER_SCREEN_MAIN_LABEL_SCALE);
        // putting the spacing on the table
        this.mainTable.add(this.gameOverLabel).expandY();
        this.mainTable.row();
        drawScores(this.mainTable, currentScore, currentHighScore);
        // get the buttons's textures
        final TextureAtlas atlas = new TextureAtlas("ButtonImages/Buttons.pack");
        final Skin skin = new Skin(atlas);

        // creating buttons

        final TextButtonStyle exitStyle = this.guiFact.createTextButtonStyle("exitButton_Inactive", "exitButton_Active", skin, this.bitmapFont);
        exitStyle.fontColor = Color.BLACK;
        final TextButton menu = this.guiFact.createTextButton("Return To Menu", exitStyle);
        menu.setTransform(true);
        menu.pad(UtilsForUI.DISTANCE_FROM_MARGINS);
        menu.getLabel().setFontScale(UtilsForUI.SETTINGS_TEXT_SIZE);
        addMenuListener(menu);
        this.buttons.add(menu);

        // Add buttons to table
        this.buttons.forEach(button -> {
            this.mainTable.row();
            this.mainTable.add(button);
        });

        // Add table to stage
        this.stage.addActor(this.mainTable);
    }

    private void winScreen(final int currentScore, final int currentHighScore) {
        this.gameOverLabel = this.guiFact.createLabel("YOU WON ", this.bitmapFont, Color.valueOf(UtilsForUI.YOU_WON_COLOR));
        this.gameOverLabel.setFontScale(UtilsForUI.GAMEOVER_SCREEN_MAIN_LABEL_SCALE);
        // putting the spacing on the table
        this.mainTable.add(this.gameOverLabel).expandY();
        this.mainTable.row();
        drawScores(this.mainTable, currentScore, currentHighScore);
        // get the buttons's textures
        final TextureAtlas atlas = new TextureAtlas("ButtonImages/Buttons.pack");
        final Skin skin = new Skin(atlas);

        // creating buttons

        final TextButtonStyle nextStyle = this.guiFact.createTextButtonStyle("playButton_Inactive", "playButton_Active", skin, this.bitmapFont);
        nextStyle.fontColor = Color.BLACK;
        final TextButton next = this.guiFact.createTextButton("Next Level", nextStyle);
        next.setTransform(true);
        next.pad(UtilsForUI.DISTANCE_FROM_MARGINS);
        next.getLabel().setFontScale(UtilsForUI.SETTINGS_TEXT_SIZE);
        addNextLevelListener(next);
        this.buttons.add(next);

        final TextButtonStyle exitStyle = this.guiFact.createTextButtonStyle("exitButton_Inactive", "exitButton_Active", skin, this.bitmapFont);
        exitStyle.fontColor = Color.BLACK;
        final TextButton menu = this.guiFact.createTextButton("Return To Menu", exitStyle);
        menu.setTransform(true);
        menu.pad(UtilsForUI.DISTANCE_FROM_MARGINS);
        menu.getLabel().setFontScale(UtilsForUI.SETTINGS_TEXT_SIZE);
        addMenuListener(menu);
        this.buttons.add(menu);

        // Add buttons to table
        this.buttons.forEach(button -> {
            this.mainTable.row();
            this.mainTable.add(button);
        });

        // Add table to stage
        this.stage.addActor(mainTable);
    }

    private void drawScores(final Table table, final int currentScore,
            final int currentHighScore) {
        if (currentHighScore == currentScore) {
            this.controller.requestSaveHighScore();
            final Label label = this.guiFact.createLabel("NEW HIGHSCORE: " + currentScore, this.bitmapFont, Color.BLACK);
            table.add(label);
            table.row();
        } else {
            Label label = this.guiFact.createLabel("HIGHSCORE: " + currentHighScore, this.bitmapFont, Color.BLACK);
            table.add(label);
            table.row();
            label = this.guiFact.createLabel("CURRENT SCORE: " + currentScore, this.bitmapFont, Color.BLACK);
            table.add(label);
            table.row();
        }
    }

    private void addMenuListener(final TextButton button) {
        button.addListener(new ClickListener() {
            @Override
            public void clicked(final InputEvent event, final float x, final float y) {
                SoundManager.getSoundManager()
                            .stopAllSounds()
                            .play(SoundName.BUTTON_TWO);
                screenObserver.setMainMenuScreen();
            }
        });
    }

    private void addNextLevelListener(final TextButton button) {
        button.addListener(new ClickListener() {
            @Override
            public void clicked(final InputEvent event, final float x, final float y) {
                SoundManager.getSoundManager()
                            .stopAllSounds()
                            .playWhithSleep(SoundName.BUTTON_TWO);
                controller.requestContinueGame();
                screenObserver.setGameScreen();
            }
        });
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final float deltaTime) {
        Gdx.gl.glClearColor(0, 0, 0, 1); // black color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.stage.act();
        this.stage.draw();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resize(final int width, final int height) {
        this.viewport.update(width, height);
        this.camera.position.set(this.camera.viewportWidth / 2, this.camera.viewportHeight / 2, 0);
        this.camera.update();
    }

    @Override
    public void resume() {
    }

    @Override
    public void show() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Stage getStage() {
        return this.stage;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setObserver(final ScreenObserver screenObserver) {
        this.screenObserver = screenObserver;
    }
}
