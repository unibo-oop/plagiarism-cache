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
import it.unibo.oop.bounce.manager.Manager;

public class WinScreen implements Screen {
	
	private final Stage stage;
	
	private  Bounce games;
	private final Manager manager;
	private float stateTime;
	private final Viewport viewport; 
    private final OrthographicCamera cam; 

    public WinScreen(final Bounce game) {
    	 manager = Manager.managerInstance;
    	 this.games = game;
         cam = new OrthographicCamera();
         viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), cam);
         stage = new Stage(viewport, new SpriteBatch());
 
         final Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
 
         final Table table = new Table();
         table.center();
         table.setFillParent(true);

         final Label gameOver = new Label("*************** YOU WIN *************** \n\n", font);
         final Label exit = new Label("\n\nPRESS ENTER TO CONTINUE...", font);


         table.add(gameOver).expandX();
         table.row();
         table.add(exit).expandX().padTop(10);
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

		stateTime = getStateTime() + delta;
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
        	   if (this.manager.getLevel() == 1) {
                   this.manager.setLevel();
                   games.setScreen(new PlayScreen(games));
               } else {
                   games.setScreen(new Menu(games));
               }
            this.dispose();
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);
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
		this.games = game;
		final BodyDef setEnt = new BodyDef();
		setEnt.position.set(x, y);
		game.setScreen(new PlayScreen(game));
	}

	public float getStateTime() {
		return stateTime;
	}

}
