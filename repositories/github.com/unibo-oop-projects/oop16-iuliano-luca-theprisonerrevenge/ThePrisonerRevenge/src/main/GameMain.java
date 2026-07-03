package main;

import controller.Controller;
import controller.ControllerImpl;
import model.Model;
import model.ModelImpl;
import view.ViewImpl;
import view.View;

/**
 * This class contains the main method. It initializes the {@link model.Model},
 * the {@link view.View}, the {@link controller.Controller} and ties
 * everything together, it also start the thread manage by the Controller.
 * 
 * @author Luca
 */
public class GameMain {
	public static void main(String[] args) {
		Model model = new ModelImpl();
		View view = new ViewImpl(model);
		Controller controller = new ControllerImpl(model, view);
		controller.startControllerThread();
	}
}
