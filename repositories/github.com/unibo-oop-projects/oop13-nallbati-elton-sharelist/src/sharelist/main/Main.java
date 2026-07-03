package sharelist.main;

import sharelist.controller.Controller;
import sharelist.model.Model;
import sharelist.view.login.LoginView;

/**
 * Classe Main dell'applicazione Sharelist.
 * 
 * @author Elton Nallbati
 * @version 1.0
 */

public class Main{
	/**
	 * Main dell'applicazione
	 * 
	 * @param args
	 * 				eventuali parametri d'ingresso
	 */
	public static void main(String[] args){
		Controller controller = new Controller();
		Model model = new Model();
		LoginView loginView = new LoginView();
		controller.setModel(model);
		controller.setView(loginView);
	}
}