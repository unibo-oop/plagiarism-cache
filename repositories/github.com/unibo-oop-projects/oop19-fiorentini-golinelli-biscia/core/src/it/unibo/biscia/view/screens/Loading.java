package it.unibo.biscia.view.screens;

import it.unibo.biscia.Biscia;
import it.unibo.biscia.view.managers.AssetManagerDecorator;
import it.unibo.biscia.view.managers.FontManager;
import it.unibo.biscia.view.managers.SkinManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

/**
 * This screen will load assets for the game.
 *
 */
public final class Loading extends ScreenAdapter {
    private Label loadingText;
    private final Biscia biscia;
    private final AssetManagerDecorator manager;
    private final Stage stage;

    public Loading() {
        this.biscia = (Biscia) Gdx.app.getApplicationListener();
        this.manager = biscia.getAssetManager();
        this.stage = new Stage();
        this.manager.loadFont(FontManager.ARCADE);
        // call finishLoading to BLOCK the application
        // this will load the font and then
        // progressively load everything else.
        this.manager.finishLoading();

    }

    @Override
    public void show() {
        // here you can load everything else

        // loading the main skin with its assets
        this.manager.loadSkin(SkinManager.MAIN);

        this.manager.loadFont(FontManager.LOGO);

        loadingText = new Label("Loading..",
                new Label.LabelStyle(this.manager.get(FontManager.ARCADE.getName()), Color.WHITE));
        loadingText.setFontScale(0.5f);
        loadingText.setFillParent(true);
        loadingText.setAlignment(Align.center);
        this.stage.addActor(loadingText);
    }

    @Override
    public void render(final float delta) {
        // if the manager done loading assets
        if (this.manager.update()) {
            this.biscia.setScreen(new MainMenu());
        } else {
            this.stage.act();
            this.stage.draw();
        }
    }
}
