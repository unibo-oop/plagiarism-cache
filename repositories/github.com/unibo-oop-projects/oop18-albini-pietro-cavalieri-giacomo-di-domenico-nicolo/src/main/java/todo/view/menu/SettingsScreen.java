package todo.view.menu;

import java.util.Objects;
import java.util.stream.Collectors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.List.ListStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox.SelectBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.utils.Align;

import todo.launcher.LauncherOptions;
import todo.utils.UIConstants;
import todo.view.drawables.screens.ResolutionManager;
import todo.view.drawables.screens.ResolutionManagerImpl;

public class SettingsScreen implements ObservableScreen {
    private final Stage stage;
    private MenuObserver observer;

    public SettingsScreen() {
        this.stage = new Stage(ResolutionManagerImpl.getInstance().getCurrentViewport());

        final Table mainTable = new Table();

        final Skin skin = new Skin();
        skin.add("settings-background", UIConstants.MENU_BACKGROUND_PATCH);
        mainTable.setBackground(skin.getDrawable("settings-background"));
        mainTable.defaults().pad(10);
        mainTable.setFillParent(true);

        final Label title = new Label("Settings",
                UIConstants.labelStyleFromFont(UIConstants.JOYSTIX_FONT_FILE, Color.BLACK, 48));
        title.setAlignment(Align.center);

        final Table aspectRatioTable = new Table();

        // Make the two labels
        final LabelStyle windowLabelStyle = UIConstants.labelStyleFromFont(UIConstants.JOYSTIX_FONT_FILE, Color.BLACK,
                20);
        final Label windowLabel = new Label("Set window mode", windowLabelStyle);

        final LabelStyle displayLabelStyle = UIConstants.labelStyleFromFont(UIConstants.JOYSTIX_FONT_FILE, Color.BLACK,
                20);
        final Label displayLabel = new Label("Choose display mode", displayLabelStyle);

        // Make the select box
        final NinePatchDrawable backgroundDrawable = new NinePatchDrawable(UIConstants.SELECTBOX_PATCH);
        final NinePatchDrawable scrollbarDrawable = new NinePatchDrawable(UIConstants.SCROLLBAR_PATCH);
        final NinePatchDrawable scrollbarKnobDrawable = new NinePatchDrawable(UIConstants.SCROLLBAR_KNOB_PATCH);
        final ScrollPaneStyle scrollStyle = new ScrollPaneStyle(backgroundDrawable, scrollbarDrawable,
                scrollbarKnobDrawable, scrollbarDrawable, scrollbarKnobDrawable);
        final ListStyle listStyle = new ListStyle(
                UIConstants.generateFont(UIConstants.JOYSTIX_FONT_FILE, Color.BLACK, 16), Color.BLUE, Color.BLACK,
                new NinePatchDrawable(UIConstants.SELECTED_PATCH));
        final SelectBoxStyle selectBoxStyle = new SelectBoxStyle(
                UIConstants.generateFont(UIConstants.JOYSTIX_FONT_FILE, Color.BLACK, 16), Color.BLACK,
                backgroundDrawable, scrollStyle, listStyle);

        final SelectBox<ComparableDisplayMode> displayModes = buildDisplayModeSelector(selectBoxStyle);
        displayModes.addListener(new ChangeListener() {
            @Override
            public void changed(final ChangeEvent event, final Actor actor) {
                SettingsScreen.this.observer.changeDisplayMode(displayModes.getSelected().mode);
            }
        });

        // Make the Checkbox
        final NinePatchDrawable checkmarkDrawable = new NinePatchDrawable(UIConstants.CHECKMARK_PATCH);
        final CheckBoxStyle checkBoxStyle = new CheckBoxStyle(backgroundDrawable, checkmarkDrawable,
                UIConstants.generateFont(UIConstants.JOYSTIX_FONT_FILE, Color.BLACK, 16), Color.BLACK);
        final CheckBox windowOnOff = new CheckBox("", checkBoxStyle);
        windowOnOff.addListener(new ChangeListener() {
            @Override
            public void changed(final ChangeEvent event, final Actor actor) {
                SettingsScreen.this.observer.changeWindowOrFullscreen(!windowOnOff.isChecked());
            }

        });

        aspectRatioTable.add(windowLabel).right().padRight(10);
        aspectRatioTable.add(windowOnOff).left();
        aspectRatioTable.row().padTop(50);
        aspectRatioTable.add(displayLabel).padRight(10);
        aspectRatioTable.add(displayModes);

        final Table bottomTable = new Table();
        bottomTable.add(
                new Label("Back", UIConstants.labelStyleFromFont(UIConstants.JOYSTIX_FONT_FILE, Color.BLACK, 10)));
        bottomTable.row();
        bottomTable.add(
                UIConstants.generateImageButton(UIConstants.EXIT_BUTTON_PATCH, () -> this.observer.openMenuScreen()));

        mainTable.add(title).fillX();
        mainTable.row();
        mainTable.add(aspectRatioTable).grow();
        mainTable.row();
        mainTable.add(bottomTable).bottom().left().expandX().height(60);

        this.stage.addActor(mainTable);
        this.stage.setDebugAll(LauncherOptions.getInstance().isDebugModeEnabled());
    }

    private SelectBox<ComparableDisplayMode> buildDisplayModeSelector(final SelectBoxStyle style) {
        // DisplayMode needs to be wrapped in a custom type since it doesn't implement
        // equals.
        final ResolutionManager resolutionManager = ResolutionManagerImpl.getInstance();
        final ComparableDisplayMode[] available = resolutionManager.getSupportedDisplayModes()
                                                                   .stream()
                                                                   .map(mode -> new ComparableDisplayMode(mode))
                                                                   .collect(Collectors.toList())
                                                                   .toArray(new ComparableDisplayMode[0]);
        final ComparableDisplayMode current = new ComparableDisplayMode(resolutionManager.getCurrentDisplayMode());

        final SelectBox<ComparableDisplayMode> select = new SelectBox<>(style);
        select.setAlignment(Align.center);
        select.setItems(available);
        select.setSelected(current);
        return select;
    }

    @Override
    public void render(final float delta) {
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        this.stage.act(delta);
        this.stage.draw();

    }

    @Override
    public void resize(final int width, final int height) {
        this.stage.setViewport(ResolutionManagerImpl.getInstance().getCurrentViewport());
        this.stage.getViewport().update(width, height, true);
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
    public void show() {
    }

    @Override
    public Stage getStage() {
        return this.stage;
    }

    @Override
    public void setObserver(final MenuObserver observer) {
        this.observer = Objects.requireNonNull(observer);
    }

    private static final class ComparableDisplayMode {
        private final DisplayMode mode;

        private ComparableDisplayMode(final DisplayMode mode) {
            this.mode = mode;
        }

        @Override
        public String toString() {
            return this.mode.toString();
        }

        @Override
        public boolean equals(final Object otherObject) {
            if (otherObject instanceof ComparableDisplayMode) {
                final DisplayMode other = ((ComparableDisplayMode) otherObject).mode;
                return this.mode.bitsPerPixel == other.bitsPerPixel && this.mode.height == other.height
                        && this.mode.width == other.width && this.mode.refreshRate == other.refreshRate;
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            return this.mode.hashCode();
        }
    }
}
