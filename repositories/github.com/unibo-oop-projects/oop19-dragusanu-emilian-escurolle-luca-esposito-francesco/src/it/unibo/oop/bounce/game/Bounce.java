package it.unibo.oop.bounce.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import it.unibo.oop.bounce.manager.Manager;
//import it.unibo.oop.bounce.manager.Manager;
//import it.unibo.oop.bounce.screens.GameOver;
import it.unibo.oop.bounce.screens.Menu;
import it.unibo.oop.bounce.screens.WinScreen;

public class Bounce extends Game {
	
	/**
	 * Spritebatch ci permette di disegnare rettangoli 2D che si riferiscono ad una Texture.
	 */
	public SpriteBatch batch;
	private Manager manager;
	/**
	 * Servono nelle classi screen per richiamare la grandezza della finestra.
	 */
	public static final int V_WIDTH = 1200;
	/**
	 * -.
	 */
	public static final int V_HEIGHT = 624;

	@Override
	public final void create() {
		batch = new SpriteBatch();
		if (Manager.managerInstance != null) {
			manager = Manager.managerInstance;
		} else {
			manager = new Manager(this);
		}

		setScreen(new Menu(this));

	}

	public final void render() {
		super.render();

	}

	public final void dispose() {
		super.dispose();
		manager.dispose();
	}

}
