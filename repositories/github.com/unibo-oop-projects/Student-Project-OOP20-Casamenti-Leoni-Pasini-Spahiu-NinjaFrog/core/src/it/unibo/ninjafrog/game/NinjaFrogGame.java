package it.unibo.ninjafrog.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import it.unibo.ninjafrog.game.utilities.SoundManagerImpl;
import it.unibo.ninjafrog.screens.MainMenu;

/**
 * Definition of the NinjaFrogGame class. Main {@link com.badlogic.gdx.Game
 * Game} class of the application.
 */
public final class NinjaFrogGame extends Game {
    private SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new MainMenu(this, new SoundManagerImpl()));
    }

    @Override
    public void render() {
        if (getScreen() != null) {
            getScreen().render(Gdx.graphics.getDeltaTime());
        }
    }

    @Override
    public void dispose() {
        if (getScreen() != null) {
            getScreen().hide();
        }
        this.batch.dispose();
    }

    /**
     * Getter of the batch.
     * 
     * @return The {@link com.badlogic.gdx.graphics.g2d.SpriteBatch batch} of the
     *         {@link NinjaFrogGame}.
     */
    public SpriteBatch getBatch() {
        return this.batch;
    }
}
