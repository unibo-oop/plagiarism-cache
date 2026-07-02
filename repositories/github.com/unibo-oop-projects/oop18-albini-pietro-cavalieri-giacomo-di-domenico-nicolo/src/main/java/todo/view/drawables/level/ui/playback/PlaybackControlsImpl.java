package todo.view.drawables.level.ui.playback;

import java.util.Objects;
import java.util.function.Consumer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

import todo.controller.RoomController;
import todo.launcher.LauncherOptions;
import todo.utils.UIConstants;
import todo.view.menu.MenuObserver;
import todo.view.rooms.AnimationsSpeed;
import todo.view.screens.RoomScreen;

public class PlaybackControlsImpl implements PlaybackControls {
    private static final float SMALL_BUTTON_PAD = 8f;
    private static final float SMALL_BUTTON_SIZE = 64f;

    private final Skin skin;
    private final RoomController roomController;
    private final AnimationsSpeed animationsSpeed;
    private final Table table;
    private final ImageButtonStyle playStyle;
    private final ImageButtonStyle stopStyle;
    private final ImageButton playStopButton;
    private final Button undoButton;
    private final Button redoButton;
    private final Button stepButton;
    private final Button copyButton;
    private final Button pasteButton;
    private final Button fastButton;
    private final Button slowButton;
    private final Button clearButton;

    public PlaybackControlsImpl(final RoomController roomController, final RoomScreen screen,
            final MenuObserver menuObserver, final AnimationsSpeed animationsSpeed) {
        this.roomController = Objects.requireNonNull(roomController);
        this.animationsSpeed = Objects.requireNonNull(animationsSpeed);
        this.table = new Table();
        this.table.setBounds(0, 0, screen.getStage().getWidth() * 0.67f, screen.getStage().getHeight());
        // Create the skin
        this.skin = new Skin();
        this.skin.add("play", new Texture(Gdx.files.internal("assets/buttons/play.png")));
        this.skin.add("stop", new Texture(Gdx.files.internal("assets/buttons/stop.png")));
        this.skin.add("step", new Texture(Gdx.files.internal("assets/buttons/step.png")));
        this.skin.add("undo", new Texture(Gdx.files.internal("assets/buttons/undo.png")));
        this.skin.add("redo", new Texture(Gdx.files.internal("assets/buttons/redo.png")));
        this.skin.add("copy", new Texture(Gdx.files.internal("assets/buttons/copy.png")));
        this.skin.add("paste", new Texture(Gdx.files.internal("assets/buttons/paste.png")));
        this.skin.add("exit", new Texture(Gdx.files.internal("assets/buttons/exit.png")));
        this.skin.add("info", new Texture(Gdx.files.internal("assets/buttons/info.png")));
        this.skin.add("fast", new Texture(Gdx.files.internal("assets/buttons/fast.png")));
        this.skin.add("slow", new Texture(Gdx.files.internal("assets/buttons/slow.png")));
        this.skin.add("clear", new Texture(Gdx.files.internal("assets/buttons/trash.png")));
        // Make the play/stop button styles
        final Drawable playDrawable = this.skin.getDrawable("play");
        final Drawable stopDrawable = this.skin.getDrawable("stop");
        this.playStyle = makeHoldableButtonStyle(playDrawable, UIConstants.GREEN_BUTTON_PATCH);
        this.stopStyle = makeHoldableButtonStyle(stopDrawable, UIConstants.RED_BUTTON_PATCH);
        // Make the step button
        this.stepButton = makeButton("step", UIConstants.BROWN_BUTTON_PATCH, e -> roomController.onStep());
        // Make the play/stop button
        this.playStopButton = makeButton(this.playStyle, e -> {
            if (roomController.isPlaying()) {
                roomController.onStop();
            } else {
                roomController.onPlay();
            }
        });
        // Make the undo/redo buttons
        this.undoButton = makeButton("undo", UIConstants.BROWN_BUTTON_PATCH, SMALL_BUTTON_PAD,
                e -> roomController.onUndo());
        this.redoButton = makeButton("redo", UIConstants.BROWN_BUTTON_PATCH, SMALL_BUTTON_PAD,
                e -> roomController.onRedo());
        // Make the copy/paste buttons
        this.copyButton = makeButton("copy", UIConstants.BROWN_BUTTON_PATCH, SMALL_BUTTON_PAD,
                e -> roomController.onCopy());
        this.pasteButton = makeButton("paste", UIConstants.BROWN_BUTTON_PATCH, SMALL_BUTTON_PAD,
                e -> roomController.onPaste());
        // Make the clear button
        this.clearButton = makeButton("clear", UIConstants.BROWN_BUTTON_PATCH, SMALL_BUTTON_PAD,
                e -> roomController.onClear());
        // Make the slow button
        this.slowButton = makeButton("slow", UIConstants.BROWN_BUTTON_PATCH, SMALL_BUTTON_PAD,
                e -> animationsSpeed.decrease());
        // Make the fast button
        this.fastButton = makeButton("fast", UIConstants.BROWN_BUTTON_PATCH, SMALL_BUTTON_PAD,
                e -> animationsSpeed.increase());
        // Make the info button
        final Button infoButton = makeButton("info", UIConstants.BLUE_BUTTON_PATCH, SMALL_BUTTON_PAD,
                e -> screen.showLevelDescription());
        // Make the exit button
        final Button exitButton = makeButton("exit", UIConstants.BLUE_BUTTON_PATCH, SMALL_BUTTON_PAD,
                e -> menuObserver.openMenuScreen());
        addToTable(exitButton, SMALL_BUTTON_SIZE).expand();
        addToTable(infoButton, SMALL_BUTTON_SIZE);
        addToTable(this.slowButton, SMALL_BUTTON_SIZE);
        addToTable(this.fastButton, SMALL_BUTTON_SIZE);
        addToTable(this.clearButton, SMALL_BUTTON_SIZE);
        addToTable(this.copyButton, SMALL_BUTTON_SIZE);
        addToTable(this.pasteButton, SMALL_BUTTON_SIZE);
        addToTable(this.undoButton, SMALL_BUTTON_SIZE);
        addToTable(this.redoButton, SMALL_BUTTON_SIZE);
        addToTable(this.playStopButton);
        addToTable(this.stepButton);
        if (LauncherOptions.getInstance().isDebugModeEnabled()) {
            this.table.setDebug(true, true);
        }
        screen.getStage().addActor(this.table);
    }

    @Override
    public void refresh() {
        // Disable the undo button if there's nothing to undo
        final boolean canUndo = this.roomController.canUndo();
        this.undoButton.setTouchable(canUndo ? Touchable.enabled : Touchable.disabled);
        this.undoButton.setColor(canUndo ? Color.WHITE : Color.RED);
        // Disable the redo button if there's nothing to redo
        final boolean canRedo = this.roomController.canRedo();
        this.redoButton.setTouchable(canRedo ? Touchable.enabled : Touchable.disabled);
        this.redoButton.setColor(canRedo ? Color.WHITE : Color.RED);
        // Switch between play and stop buttons
        final boolean isPlaying = this.roomController.isPlaying();
        this.playStopButton.setStyle(isPlaying ? this.stopStyle : this.playStyle);
        this.playStopButton.setChecked(isPlaying);
        this.undoButton.setVisible(!isPlaying);
        this.redoButton.setVisible(!isPlaying);
        this.copyButton.setVisible(!isPlaying);
        this.pasteButton.setVisible(!isPlaying);
        this.clearButton.setVisible(!isPlaying);
        // Disable the step button if necessary
        final boolean canStep = this.roomController.canStep();
        this.stepButton.setTouchable(canStep ? Touchable.enabled : Touchable.disabled);
        this.stepButton.setColor(canStep ? Color.WHITE : Color.RED);
        // Check if the speed controls can be used
        final boolean canIncreaseSpeed = this.animationsSpeed.canIncrease();
        this.fastButton.setTouchable(canIncreaseSpeed ? Touchable.enabled : Touchable.disabled);
        this.fastButton.setColor(canIncreaseSpeed ? Color.WHITE : Color.RED);
        final boolean canDecreaseSpeed = this.animationsSpeed.canDecrease();
        this.slowButton.setTouchable(canDecreaseSpeed ? Touchable.enabled : Touchable.disabled);
        this.slowButton.setColor(canDecreaseSpeed ? Color.WHITE : Color.RED);
    }

    @Override
    public void dispose() {
        this.skin.dispose();
    }

    private ImageButtonStyle makeHoldableButtonStyle(final Drawable image, final NinePatch ninePatch) {
        final NinePatchDrawable buttonDrawable = new NinePatchDrawable(ninePatch);
        final NinePatchDrawable heldButtonDrawable = buttonDrawable.tint(Color.LIGHT_GRAY);
        return new ImageButtonStyle(buttonDrawable, heldButtonDrawable, heldButtonDrawable, image, image, image);
    }

    private ImageButtonStyle makeButtonStyle(final Drawable image, final NinePatch ninePatch) {
        final NinePatchDrawable buttonDrawable = new NinePatchDrawable(ninePatch);
        return new ImageButtonStyle(buttonDrawable, buttonDrawable.tint(Color.LIGHT_GRAY), buttonDrawable, image, image,
                image);
    }

    private ImageButton makeButton(final ImageButtonStyle style, final Consumer<InputEvent> onClick) {
        final ImageButton button = new ImageButton(style);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(final InputEvent event, final float x, final float y) {
                onClick.accept(event);
            }
        });
        return button;
    }

    private ImageButton makeButton(final String skinName, final NinePatch ninePatch,
            final Consumer<InputEvent> onClick) {
        return makeButton(makeButtonStyle(this.skin.getDrawable(skinName), ninePatch), onClick);
    }

    private ImageButton makeButton(final String skinName, final NinePatch ninePatch, final float pad,
            final Consumer<InputEvent> onClick) {
        final ImageButton button = makeButton(skinName, ninePatch, onClick);
        button.pad(pad);
        return button;
    }

    private Cell<Actor> addToTable(final Actor actor) {
        return this.table.add(actor).top().right();
    }

    private Cell<Actor> addToTable(final Actor actor, final float size) {
        return addToTable(actor).width(size).height(size);
    }
}
