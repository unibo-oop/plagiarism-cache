package todo.view.menu;

import java.util.List;
import java.util.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;

import todo.launcher.LauncherOptions;
import todo.utils.UIConstants;
import todo.view.drawables.screens.ResolutionManagerImpl;

public class MenuScreen implements ObservableScreen {
    private final Stage stage;
    private MenuObserver observer;

    public MenuScreen(final List<String> titles) {
        this.stage = new Stage(ResolutionManagerImpl.getInstance().getCurrentViewport());

        final Table mainTable = new Table();
        final Skin skin = new Skin();
        skin.add("menu-background", UIConstants.MENU_BACKGROUND_PATCH);
        mainTable.setBackground(skin.getDrawable("menu-background"));
        mainTable.defaults().pad(10F);
        mainTable.setFillParent(true);

        final Label label = new Label("//TODO",
                UIConstants.labelStyleFromFont(UIConstants.JOYSTIX_FONT_FILE, Color.BLACK, 48));
        label.setAlignment(Align.center);

        final Table levelsTable = new Table();
        final ScrollPane scrollPane = new ScrollPane(levelsTable);

        for (int i = 0; i < titles.size(); i++) {
            final String level = titles.get(i);
            final TextButton btn = UIConstants.fromLevelToButton(titles.size() - i);
            btn.addListener(new ChangeListener() {
                @Override
                public void changed(final ChangeEvent event, final Actor actor) {
                    MenuScreen.this.observer.openLevelScreen(level);
                }
            });
            levelsTable.add(btn).padRight(10);
            levelsTable.add(
                    new Label(level, UIConstants.labelStyleFromFont(UIConstants.JOYSTIX_FONT_FILE, Color.BLACK, 20)));
            levelsTable.row();
            if ((titles.size() - i) != 1) {
                levelsTable.add(new Image(UIConstants.ARROW_PATCH)).left().padLeft(5);
                levelsTable.row();
            }
        }

        scrollPane.layout();
        scrollPane.scrollTo(0, 0, 0, 0);

        final Table bottomTable = new Table();

        final Label settingsLabel = new Label("Settings",
                UIConstants.labelStyleFromFont(UIConstants.JOYSTIX_FONT_FILE, Color.BLACK, 10));
        final Label exitLabel = new Label("Exit",
                UIConstants.labelStyleFromFont(UIConstants.JOYSTIX_FONT_FILE, Color.BLACK, 10));
        final ImageButton settingsButton = UIConstants.generateImageButton(UIConstants.SETTINGS_BUTTON_PATCH,
                () -> this.observer.openSettingsScreen());
        final ImageButton exitButton = UIConstants.generateImageButton(UIConstants.EXIT_BUTTON_PATCH,
                () -> this.observer.close());
        bottomTable.add(settingsLabel).padRight(10);
        bottomTable.add(exitLabel);
        bottomTable.row();
        bottomTable.add(settingsButton).padRight(10);
        bottomTable.add(exitButton);

        mainTable.add(label).fillX();
        mainTable.row();
        mainTable.add(scrollPane).grow();
        mainTable.row();
        mainTable.add(bottomTable).bottom().left().expandX().height(60);

        this.stage.addActor(mainTable);
        this.stage.setDebugAll(LauncherOptions.getInstance().isDebugModeEnabled());
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

}
