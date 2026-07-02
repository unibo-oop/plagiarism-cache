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
 * Class containing the components for the main menu creation.
 */

public final class MainMenuScreen implements ObservableScreen, ScreensMessenger {

    private static final int SOUND_VOLUME_MENU = 100;
    private static final float SOUND_VELOCITY_MENU = 1f;

    private final Viewport viewport;
    private final Stage stage;

    private ScreenObserver screenObserver;

    private final SoundManager soundManager;
    private Optional<ControllerObserver> controller;

    /**
     * Constructor of MainMenuScreen.
     */
    public MainMenuScreen() {
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
        final Texture backgroundTexture = guiFactory.createTexture("Backgrounds/background_MainMenu.png");
        mainTable.background(new TextureRegionDrawable(new TextureRegion(backgroundTexture)));

        // creating font
        final BitmapFont bitmapFont = guiFactory.createBitmapFont("Fonts/crackman.fnt");

        // putting empty spacing on the table
        mainTable.add(guiFactory.createLabel("", bitmapFont, Color.WHITE)).size(UtilsForUI.DEFAULT_DISTANCE_FROM_TOP);
        mainTable.row();

        // the buttons's textures
        final Skin skin = guiFactory.createSkin("ButtonImages/Buttons.pack");

        // creating buttons
        final TextButtonStyle playStyle = guiFactory.createTextButtonStyle("playButton_Inactive", "playButton_Active",
                skin, bitmapFont);
        buttons.add(this.addButtonListener(guiFactory.createTextButton("Play", playStyle)));

        final TextButtonStyle settingsStyle = guiFactory.createTextButtonStyle("settingsButton_Inactive",
                "settingsButton_Active", skin, bitmapFont);
        buttons.add(this.addButtonListener(guiFactory.createTextButton("Settings", settingsStyle)));

        final TextButtonStyle exitStyle = guiFactory.createTextButtonStyle("exitButton_Inactive", "exitButton_Active",
                skin, bitmapFont);
        buttons.add(this.addButtonListener(guiFactory.createTextButton("Exit", exitStyle)));

        // Add buttons to table
        buttons.forEach(button -> {
            mainTable.row();
            mainTable.add(button);
        });

        // Add table to stage
        this.stage.addActor(mainTable);

        this.controller = Optional.empty();
        this.soundManager = SoundManager.getSoundManager();
    }

    @Override
    public void show() {
        this.soundManager.setVolume(SOUND_VOLUME_MENU).setVelocity(SOUND_VELOCITY_MENU);
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
    public void setObserver(final ScreenObserver screenObserver) {
        this.screenObserver = screenObserver;
    }

    @Override
    public void startControllerObserving(final ControllerObserver controller) {
        if (Objects.isNull(controller)) {
            throw new IllegalArgumentException("The controller passed as input in startControllerObserving is null!");
        }
        this.controller = Optional.ofNullable(controller);
    }

    @Override
    public void stopControllerObserving() {
        this.controller = Optional.empty();
    }

    @Override
    public Stage getStage() {
        return this.stage;
    }

    private TextButton addButtonListener(final TextButton button) {
        final String text = button.getText().toString();
        switch (text) {
        case "Play":
            button.getLabel().setFontScale(UtilsForUI.TEXT_SIZE_DEFAULT);
            this.addPlayListener(button);
            break;
        case "Settings":
            button.getLabel().setFontScale(UtilsForUI.TEXT_SIZE_SETTINGS);
            this.addSettingsListener(button);
            break;
        case "Exit":
            button.getLabel().setFontScale(UtilsForUI.TEXT_SIZE_DEFAULT);
            this.addExitListener(button);
            break;
        default:
            final String msg = "Button not supported";
            throw new IllegalArgumentException(msg);
        }
        return button;
    }

    private void addPlayListener(final TextButton button) {
        button.addListener(new ClickListener() {
            @Override
            public void clicked(final InputEvent event, final float x, final float y) {
                soundManager.stopAllSounds()
                            .playWhithSleep(SoundName.BUTTON_TWO);
                controller.orElseThrow(
                        () -> new IllegalStateException(ControllerObserver.ERROR_INITIALIZE + " on " + this.getClass())
                        ).requestResetGame();
                screenObserver.setGameScreen();
            }
        });
    }

    private void addSettingsListener(final TextButton button) {
        button.addListener(new ClickListener() {
            @Override
            public void clicked(final InputEvent event, final float x, final float y) {
                soundManager.stopAllSounds()
                            .play(SoundName.BUTTON_TWO);
                screenObserver.setSettingsScreen();
            }
        });
    }

    private void addExitListener(final TextButton button) {
        button.addListener(new ClickListener() {
            @Override
            public void clicked(final InputEvent event, final float x, final float y) {
                soundManager.stopAllSounds()
                            .playWhithSleep(SoundName.BUTTON_TWO);
                screenObserver.closeGame();
            }
        });
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
}
