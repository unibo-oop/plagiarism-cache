package it.unibo.oop.bounce.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
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

public class Menu extends Game implements Screen {

	private Stage stage;
	private final Viewport viewport;
	private final OrthographicCamera camera;

	private final TextureRegion play;
	private final TextureRegion setting;
	private final TextureRegion exit;
	private final TextureRegion backgroundTexture;

	private final ImageButton playButton;
	private final ImageButton settingButton;
	private final ImageButton exitButton;

	private final MoveToAction movePlay;
	private final MoveToAction moveSetting;
	private final MoveToAction moveExit;

	private final Bounce game;

	public Menu(final Bounce game) {
		this.game = game;
		// guardare playscreen per renderizzare schermo intero
		viewport = new FitViewport(Manager.X_RESOLUTION, Manager.Y_RESOLUTION, new OrthographicCamera());
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

		// carichiamo il font dei bottoni
		play = new TextureRegion(new Texture("sprites/Play_Button.png"));
		// gli diciamo che è un bottone
		playButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(play)));
		// settiamo la pos del bottone
		playButton.setPosition(Manager.POS_X_PIXEL, Manager.POS_Y_PIXEL);
		// addiamo un Listener e un input listener per l'evento da effetuare se il
		// bottone è premuto
		playButton.addListener(new InputListener() {
			public boolean touchDown(final InputEvent event, final float x, final float y, final int pointer,
					final int button) {
				stage.clear();
				return true;

			}

			public void touchUp(final InputEvent event, final float x, final float y, final int pointer,
					final int button) {
				game.setScreen(new PlayScreen(game));
				stage.clear();
			}
		});

		movePlay = new MoveToAction(); // Aggiunge movimento al bottone
		// settiamo dove si deve spostare con l'animazione
		movePlay.setPosition(+Manager.MOVE_X_PIXEL, Manager.MOVE_Y_PIXEL_PLAY);
		movePlay.setDuration(Manager.DURATION);
		playButton.addAction(movePlay);

		stage.addActor(playButton);

		setting = new TextureRegion(new Texture("sprites/Setting_Button.png"));
		settingButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(setting)));
		settingButton.setPosition(Manager.POS_X_PIXEL, Manager.POS_Y_PIXEL);
		settingButton.addListener(new InputListener() {
			public boolean touchDown(final InputEvent event, final float x, final float y, final int pointer,
					final int button) {
				return true;
			}

			public void touchUp(final InputEvent event, final float x, final float y, final int pointer,
					final int button) {
				game.setScreen(new Setting(game));
				stage.clear();
			}
		});

		moveSetting = new MoveToAction(); // Aggiunge movimento al bottone

		moveSetting.setPosition(+Manager.MOVE_X_PIXEL, Manager.MOVE_Y_PIXEL_SETTING);
		moveSetting.setDuration(Manager.DURATION);
		settingButton.addAction(moveSetting);

		stage.addActor(settingButton);

		exit = new TextureRegion(new Texture("sprites/Exit_Button.png"));
		exitButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(exit)));
		exitButton.setPosition(Manager.POS_X_PIXEL, Manager.POS_Y_PIXEL);
		exitButton.addListener(new InputListener() {
			public boolean touchDown(final InputEvent event, final float x, final float y, final int pointer,
					final int button) {
				return true;
			}

			public void touchUp(final InputEvent event, final float x, final float y, final int pointer,
					final int button) {
				Gdx.app.exit();
				stage.clear();
			}
		});

		moveExit = new MoveToAction(); // Aggiunge movimento al bottone

		moveExit.setPosition(+Manager.CENTER_FOR_BUTTON, Manager.MOVE_Y_PIXEL_EXIT);
		moveExit.setDuration(Manager.DURATION);
		exitButton.addAction(moveExit);

		stage.addActor(exitButton);
	}

	@Override
	public void hide() {
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

	public final void dispose() {
		stage.dispose();
		game.batch.dispose();
	}

	public final void resize(final int width, final int height) {
		Gdx.graphics.setDisplayMode(Manager.X_RESOLUTION, Manager.Y_RESOLUTION, false);
	}

	private void update(final float delta) {
	}

	@Override
	public void show() {
	}

	@Override
	public void create() {

	}

}
