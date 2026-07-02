package it.unibo.biscia.view.screens;

import it.unibo.biscia.Biscia;
import it.unibo.biscia.view.actors.UI.ActionOverLabel;
import it.unibo.biscia.view.managers.AssetManagerDecorator;
import it.unibo.biscia.view.managers.SkinManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

import de.golfgl.gdx.controllers.ControllerMenuStage;

/**
 * A screen to implement. Every Screen in the application will extend from this
 * class.
 *
 * @see com.badlogic.gdx.Screen Screen
 */
public abstract class AbstractScreen extends ScreenAdapter {

    // Elements that every Screen that extends this class should have.
    private final Biscia biscia;
    private final AssetManagerDecorator manager;
    private final Skin skin;
    private final ActionOverLabel backLabel;
    // a stage made for menu with keyboard handle: if you type the Key ENTER the
    // stage will fire a click event on the button highlithed.
    private final ControllerMenuStage stage;
    private final InputMultiplexer multiplexer;

    // This is an InputAdapter that handle only mouse events but it really does
    // nothing with them. it is used to filter mouse events on the game window.
    private final InputAdapter inputAdapter = new InputAdapter() {
        @Override
        public boolean touchDown(final int screenX, final int screenY, final int pointer, final int button) {
            return true;
        }

        @Override
        public boolean touchUp(final int screenX, final int screenY, final int pointer, final int button) {
            return true;
        }

        @Override
        public boolean touchDragged(final int screenX, final int screenY, final int pointer) {
            return true;
        }

        @Override
        public boolean mouseMoved(final int screenX, final int screenY) {
            return true;
        }

        @Override
        public boolean scrolled(final int amount) {
            return true;
        }
    };

    protected AbstractScreen() {
        this.biscia = (Biscia) Gdx.app.getApplicationListener();
        this.manager = (AssetManagerDecorator) this.biscia.getAssetManager();
        this.stage = new ControllerMenuStage(new ScalingViewport(Scaling.stretch, Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight(), new OrthographicCamera()));
        // disabling mouse events
        multiplexer = new InputMultiplexer();
        // inputAdapter will handle only mouse events and it will forward the others to
        // the next input processor
        multiplexer.addProcessor(inputAdapter);
        // stage also implements input processor
        multiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(multiplexer);
        this.skin = this.manager.get(SkinManager.MAIN.getPath(), Skin.class);
        this.backLabel = new ActionOverLabel("<- Back", skin, null);
    }

    public final Biscia getBiscia() {
        return biscia;
    }

    public final AssetManagerDecorator getManager() {
        return manager;
    }

    public final ControllerMenuStage getStage() {
        return stage;
    }

    public final Skin getSkin() {
        return skin;
    }

    public final ActionOverLabel getBackLabel() {
        return backLabel;
    }

    public final InputMultiplexer getInputMultiplexer() {
        return this.multiplexer;
    }

    // Here a screen must define the actors for better order
    @Override
    public abstract void show();

    @Override
    public void render(final float delta) {
        // refresh the stage
        this.stage.act();
        this.stage.draw();
    };

    @Override
    public final void hide() {
        // dispose is not automatically called.
        this.dispose();
    }

    @Override
    public void dispose() {
        // clear the stage and release all his resources.
        this.stage.clear();
        this.stage.dispose();
    };
}
