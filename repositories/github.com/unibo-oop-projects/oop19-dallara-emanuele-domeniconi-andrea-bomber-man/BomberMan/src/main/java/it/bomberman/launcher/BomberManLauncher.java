package it.bomberman.launcher;

import it.bomberman.menu.MenuController;
import it.bomberman.menu.MenuView;

public class BomberManLauncher {

	public static void main(String[] args) {
		MenuView menuView = new MenuView();
		MenuController menuController = new MenuController(menuView);
		menuController.start();
	}
}
