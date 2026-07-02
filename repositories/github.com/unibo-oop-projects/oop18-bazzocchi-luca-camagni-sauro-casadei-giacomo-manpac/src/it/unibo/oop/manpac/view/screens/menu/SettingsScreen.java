package it.unibo.oop.manpac.view.screens.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
 * Class containing the components for the settings screen creation.
 */

public final class SettingsScreen implements ObservableScreen, ScreensMessenger.TheSettings {

    private Optional<ControllerObserver.OnlySettings> controller;

    private final Viewport viewport;
    private final Stage stage;

    private final Label highScoreLabel;

    private int highScore;

    private ScreenObserver screenObserver;

    /**
     * Constructor of SettingsScreen.
     */
    public SettingsScreen() {
        final List<TextButton> buttons = new ArrayList<>();

        // Setting the view
        final Camera camera = new OrthographicCamera();
        this.viewport = new FitViewport(UtilsForUI.WIDTH_DEFAULT, UtilsForUI.HEIGHT_DEFAULT, camera);
        this.viewport.apply();

        this.stage = new Stage(this.viewport);

        // Create Table
        final Table mainTable = new Table();
        // Set table to fill stage
        mainTable.setFillParent(true);
        // Set alignment of contents in the table.
        mainTable.top();
        // Set distance between buttons
        mainTable.defaults().pad(UtilsForUI.DISTANCE_BUTTONS_DEFAULT);

        final GUIFactory guiFactory = new GUIFactoryImpl();

        // Set background
        final Texture backgroundTexture = guiFactory.createTexture("Backgrounds/background_Blue.png");
        mainTable.background(new TextureRegionDrawable(new TextureRegion(backgroundTexture)));

        // creating font
        final BitmapFont bitmapFont = guiFactory.createBitmapFont("Fonts/crackman.fnt");
        bitmapFont.getData().setScale(UtilsForUI.DEFAULT_SCALE);

        // creating high score label
        this.highScore = 0;
        this.highScoreLabel = guiFactory.createLabel("High Score:\t" + this.highScore, bitmapFont, Color.WHITE);

        // putting the highScoreLabel on the table
        mainTable.add(this.highScoreLabel);
        mainTable.row();

        // get the buttons's textures
        final Skin skin = guiFactory.createSkin("ButtonImages/Buttons.pack");

        // creating buttons

        final TextButtonStyle resetStyle = guiFactory.createTextButtonStyle("settingsButton_Inactive",
                "settingsButton_Active", skin, bitmapFont);
        final TextButton resetButton = guiFactory.createTextButton("Reset High Score", resetStyle);
        resetButton.getLabel().setFontScale(UtilsForUI.SETTINGS_TEXT_SIZE);
        buttons.add(this.addButtonListener(resetButton));

        final TextButtonStyle returnStyle = guiFactory.createTextButtonStyle("exitButton_Inactive", "exitButton_Active",
                skin, bitmapFont);
        final TextButton returnButton = guiFactory.createTextButton("Return To Menu", returnStyle);
        returnButton.getLabel().setFontScale(UtilsForUI.SETTINGS_TEXT_SIZE);
        buttons.add(this.addButtonListener(returnButton));

        // Add buttons to table
        buttons.forEach(button -> {
            mainTable.row();
            mainTable.add(button);
        });

        // Add table to stage
        this.stage.addActor(mainTable);

        this.controller = Optional.empty();
    }

    private void setHighScoreLabel() {
        this.highScoreLabel.setText("High Score: " + this.highScore);
    }

    @Override
    public void show() {
        controllerOrThrows().requestHighScore();
    }

    @Override
    public void render(final float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1); // black color
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.stage.act();
        this.stage.draw();
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
    }

    @Override
    public void setObserver(final ScreenObserver screenObserver) {
        this.screenObserver = screenObserver;
    }

    @Override
    public Stage getStage() {
        return this.stage;
    }

    @Override
    public void startControllerObserving(final ControllerObserver controller) {
        if (Objects.isNull(controller)) {
            throw new IllegalArgumentException("The controller passed as input in startControllerObserving is null!");
        }
        this.controller = Optional.of((ControllerObserver.OnlySettings) controller);
    }

    @Override
    public void stopControllerObserving() {
        this.controller = Optional.empty();
    }

    @Override
    public void sendHighScore(final int highScore) {
        this.highScore = highScore;
        this.setHighScoreLabel();
    }

    private TextButton addButtonListener(final TextButton button) {
        final String text = button.getText().toString();
        switch (text) {
        case "Reset High Score":
            this.addHighScoreListener(button);
            break;
        case "Return To Menu":
            this.addMenuListener(button);
            break;
        default:
            final String msg = "Button not supported";
            throw new IllegalArgumentException(msg);
        }
        return button;
    }

    private void addHighScoreListener(final TextButton button) {
        button.addListener(new ClickListener() {
            @Override
            public void clicked(final InputEvent event, final float x, final float y) {
                SoundManager.getSoundManager()
                            .stopAllSounds()
                            .play(SoundName.BUTTON_ONE);
                if (highScore != 0) {
                    highScore = 0;
                    controllerOrThrows().requestResetBothScore();
                    controllerOrThrows().requestSaveHighScore();
                    setHighScoreLabel();
                }
            }
        });
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

    /**
     * It is used to check if the controller has been set; in the positive case it
     * returns it, in the negative case the IllegalStateException (with message) is
     * thrown for the absence of it.
     */
    private ControllerObserver.OnlySettings controllerOrThrows() throws IllegalStateException {
        return this.controller.orElseThrow(
                () -> new IllegalStateException(ControllerObserver.ERROR_INITIALIZE + " on " + this.getClass()));
    }
}
