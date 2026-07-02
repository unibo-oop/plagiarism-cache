package Application;




import model.SuperMarket;
import model.SuperMarketImpl;
import controller.MainController;
import controller.MainControllerImpl;
import view.MainPanelImpl;

/**
 * @author Marco Martini
 * @author Andrea Gasperini
 */

public class Launcher {

	public static void main(String[] args) {

		MainPanelImpl panel = new MainPanelImpl();

		SuperMarket superMarket = new SuperMarketImpl();

		MainController mainController = new MainControllerImpl(superMarket,
				panel);
		panel.addObserver(mainController);

	}

}
