package it.unibo.oop.crossline.game.screen;

import java.awt.Dimension;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import it.unibo.oop.crossline.game.GameControllerImpl;
import it.unibo.oop.crossline.game.GameModelImpl;
import it.unibo.oop.crossline.game.GameViewImpl;

/**
 * Best use with 16:9 FullHd monitor, do not work properly in full screen.
 */
public class InitialScreen implements Screen {

    private Texture backgroundTexture;
    private SpriteBatch spriteBatch;
    private Sprite backgroundSprite;
    private Texture startGameTexture;
    private Sprite startGameSprite;
    private Dimension dimension;

    @Override
    public final void dispose() {
        backgroundTexture.dispose();
        startGameTexture.dispose();
        spriteBatch.dispose();
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    // TODO Insert button to select game difficult, resume, exit
    @Override
    public final void render(final float arg0) {
        if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
            final Game game = (Game) Gdx.app.getApplicationListener();
            final GameControllerImpl gameController = new GameControllerImpl(new GameViewImpl(), new GameModelImpl());
            game.setScreen(gameController);
        }

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.draw(backgroundSprite, 0, 0, (float) dimension.width, (float) dimension.height);
        spriteBatch.draw(startGameSprite, dimension.width / 25, dimension.height / 5, (float) dimension.width / 1.1f,
                (float) dimension.height / 1.8f);
        spriteBatch.end();

        // TODO use https://github.com/libgdx/libgdx/wiki/Bitmap-fonts
        // font.draw(spriteBatch, "res/startgame.png", , );
    }

    @Override
    public void resize(final int width, final int height) {
    }

    @Override
    public void resume() {
    }

    @Override
    public final void show() {
        backgroundTexture = new Texture("res/background1920x1080.png");
        backgroundSprite = new Sprite(backgroundTexture);
        startGameTexture = new Texture("res/startgame.png");
        startGameSprite = new Sprite(startGameTexture);
        spriteBatch = new SpriteBatch();
        dimension = new Dimension();
        dimension.height = Gdx.graphics.getHeight();
        dimension.width = Gdx.graphics.getWidth();
    }

}
