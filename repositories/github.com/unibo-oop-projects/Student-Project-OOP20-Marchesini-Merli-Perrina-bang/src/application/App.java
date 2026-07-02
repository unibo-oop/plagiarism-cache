package application;

import controller.Controller;
import view.SwingViewFactory;

public class App {

	public static void main(String[] args) {
		Controller c = new Controller(new SwingViewFactory());
		c.start();
	}
}
