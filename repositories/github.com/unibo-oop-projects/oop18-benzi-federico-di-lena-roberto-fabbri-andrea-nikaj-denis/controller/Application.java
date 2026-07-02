package controller;

import view.SceneManager;
import view.View;
import view.ViewImpl;

/**
 * The main application starter
 */
public class Application {

	public static void main(final String[] args) {
		final SceneManager sceneManager = new SceneManager();
		final View v = new ViewImpl(sceneManager);
		v.startView();
	}
}
