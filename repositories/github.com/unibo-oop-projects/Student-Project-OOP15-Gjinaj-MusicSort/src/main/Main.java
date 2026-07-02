package main;

import controller.MainController;
import model.LibraryManager;
import view.MainView;

/**
 * Vengono istanziati MVC
 *
 */
public class Main {
	public static void main(String[] args) {
		
		LibraryManager libraryManager = new LibraryManager();
		MainController controller = new MainController(libraryManager);
		MainView window = new MainView(controller);
		controller.setView(window);
	}

}
