package it.unibo.oop.crossline.game.screen;

import java.awt.Dimension;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import it.unibo.oop.crossline.game.GameControllerImpl;
import it.unibo.oop.crossline.game.GameModelImpl;
import it.unibo.oop.crossline.game.GameViewImpl;

public class FinalScreen implements Screen {

    private SpriteBatch spriteBatch;
    private Texture textureImage;
    private Sprite backGroundSprite;
    private BitmapFont font;
    private Dimension dimension;

    @Override
    public final void dispose() {
        spriteBatch.dispose();
        textureImage.dispose();
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
        font.draw(spriteBatch, "Game Finished", (float) dimension.width, (float) dimension.height);
        spriteBatch.draw(backGroundSprite, 0, 0, (float) dimension.width, (float) dimension.height);
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
        textureImage = new Texture("res/background1920x1080.png");
        backGroundSprite = new Sprite(textureImage);
        spriteBatch = new SpriteBatch();
        dimension = new Dimension(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        font = new BitmapFont();
    }

}
