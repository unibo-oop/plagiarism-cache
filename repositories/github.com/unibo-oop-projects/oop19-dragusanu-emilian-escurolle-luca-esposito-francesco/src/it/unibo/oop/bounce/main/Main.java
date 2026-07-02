package it.unibo.oop.bounce.main;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import it.unibo.oop.bounce.game.Bounce;

public final class Main {
	
	private static final int WIDTH = 1200; 
	private static final int HEIGHT = 624; 

	private Main() {
	}

	public static void main(final String[] args) {
		final LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = WIDTH;
		config.height = HEIGHT;
		new LwjglApplication(new Bounce(), config);
	}
}
