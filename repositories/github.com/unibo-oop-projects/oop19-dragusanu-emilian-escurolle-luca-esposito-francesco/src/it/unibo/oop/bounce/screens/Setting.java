package it.unibo.oop.bounce.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import it.unibo.oop.bounce.game.Bounce;
import it.unibo.oop.bounce.manager.Manager;

public class Setting implements Screen {

	private final Stage stage;
	private final Bounce game;
	private final Manager manager;
	private final Preferences preferences;
	private final TextureRegion backgroundTexture;
	private final TextureRegion soundOn;
	private final TextureRegion soundOff;
	private final TextureRegion close;
	private final ImageButton soundOnButton;
	private final ImageButton soundOffButton;
	private final ImageButton closeButton;

	private final MoveToAction moveSoundOn;
	private final MoveToAction moveSoundOff;
	private final MoveToAction moveClose;

	private final Viewport viewport;
	private final OrthographicCamera camera;

	public Setting(final Bounce game) {
		manager = Manager.managerInstance;
		this.game = game;
		this.preferences = manager.getSetting();
		viewport = new FitViewport(1200, 624, new OrthographicCamera());
		viewport.apply();
		stage = new Stage(viewport, game.batch);
		camera = new OrthographicCamera();
		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
		camera.update();
		backgroundTexture = new TextureRegion(new Texture("sprites/fontbounce2.jpg"));

		// puliamo lo schermo per evitare eventuali problemi
		stage.clear();
		// Settiamo lo stage che prende input
		Gdx.input.setInputProcessor(stage);

		soundOn = new TextureRegion(new Texture("sprites/SoundOn_Button.png"));

		soundOnButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(soundOn)));
		soundOnButton.setPosition(Manager.POS_X_PIXEL, Manager.POS_Y_PIXEL);
		soundOnButton.addListener(new InputListener() {
			public boolean touchDown(final InputEvent event, final float x, final float y, final int pointer, final int button) {
				return true;
			}
		});

		moveSoundOn = new MoveToAction(); // Add dynamic movement effects to button
		// settiamo dove si deve spostare con l'animazione
		moveSoundOn.setPosition(+Manager.MOVE_X_PIXEL, Manager.MOVE_Y_PIXEL_SOUND_ON);
		moveSoundOn.setDuration(Manager.DURATION);
		soundOnButton.addAction(moveSoundOn);

		stage.addActor(soundOnButton);

		soundOff = new TextureRegion(new Texture("sprites/SoundOff_Button.png"));

		soundOffButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(soundOff)));
		soundOffButton.setPosition(Manager.POS_X_PIXEL, Manager.POS_Y_PIXEL);
		soundOffButton.addListener(new InputListener() {
			public boolean touchDown(final InputEvent event, final float x, final float y, final int pointer, final int button) {

				if (!preferences.getBoolean("disableMusic")) {
					preferences.putBoolean("disableMusic", true);
					preferences.flush();
				}
				return true;
			}
		});

		moveSoundOff = new MoveToAction(); // Add dynamic movement effects to button
		// settiamo dove si deve spostare con l'animazione
		moveSoundOff.setPosition(+Manager.MOVE_X_PIXEL, Manager.MOVE_Y_PIXEL_SOUND_OFF);
		moveSoundOff.setDuration(Manager.DURATION);
		soundOffButton.addAction(moveSoundOff);

		stage.addActor(soundOffButton);

		close = new TextureRegion(new Texture("sprites/Close_Button.png"));
		closeButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(close)));
		closeButton.setPosition(Manager.POS_X_PIXEL, Manager.POS_Y_PIXEL);
		closeButton.addListener(new InputListener() {
			public boolean touchDown(final InputEvent event, final float x, final float y, final int pointer, final int button) {
				return true;
			}

			public void touchUp(final InputEvent event, final float x, final float y, final int pointer, final int button) {
				game.setScreen(new Menu(game));
				stage.clear();
			}

		});

		moveClose = new MoveToAction();	// Add dynamic movement effects to button

		moveClose.setPosition(+Manager.MOVE_X_PIXEL_CLOSE, Manager.MOVE_Y_PIXEL_CLOSE);
		moveClose.setDuration(Manager.DURATION);
		closeButton.addAction(moveClose);

		stage.addActor(closeButton);

	}

	@Override
	public final void dispose() {
		stage.dispose();
		game.batch.dispose();
	}

	@Override
	public final void render(final float delta) {
		update(delta);
		stage.act();
		game.batch.begin();
		game.batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		game.batch.end();
		stage.draw();
	}

	private void update(final float delta) {
	}

	@Override
	public void resize(final int arg0, final int arg1) {
	}

	@Override
	public void resume() {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

}
