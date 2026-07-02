package it.unibo.oop.bounce.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import it.unibo.oop.bounce.game.Bounce;


public class GameOver implements Screen {
	
	private final Stage stage;
	
	private Bounce game;
	
	private final Viewport viewport; 
    private final OrthographicCamera cam; 
    /**
     * serve per settare il punto del table exit.
     */
    public static final int EXPAND_X = 10;

    public GameOver(final Bounce game) {
    	 this.game = game;
         cam = new OrthographicCamera();
         viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), cam);
         stage = new Stage(viewport, new SpriteBatch());

         final Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

         final Table table = new Table();
         table.center();
         table.setFillParent(true);

         final Label gameOver = new Label("GAME OVER", font);
         final Label exit = new Label("Press ENTER to play again...", font);


         table.add(gameOver).expandX();
         table.row();
         table.add(exit).expandX().padTop(EXPAND_X);
         stage.addActor(table);
    }

	@Override
	public final void dispose() {
		stage.dispose();
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public final void render(final float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            game.setScreen(new PlayScreen(game));
            this.dispose();
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);	//(red/green/blue/alpha)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
	}

	@Override
	public final void resize(final int width, final int height) {
		viewport.update(width, height);
        cam.update();
	}

	@Override
	public void resume() {
	}

	@Override
	public void show() {
	}
	
	public final void newScreen(final Bounce game, final float x, final float y) {
		this.game = game;
		final BodyDef setEnt = new BodyDef();
		setEnt.position.set(x, y);
		game.setScreen(new PlayScreen(game));
	}

}
