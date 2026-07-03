package application;

import controller.MainController;
import controller.MainControllerImpl;
import model.TaekwondoPlatform;
import model.TaekwondoPlatformImpl;
import view.MainPanel;
import view.MainPanelImpl;

public class Launcher {
	
	public static void main(String[] args) {
		
		MainPanel p = new MainPanelImpl();
		
		TaekwondoPlatform tkdPlatform = new TaekwondoPlatformImpl();
			
		MainController mainController = new MainControllerImpl(tkdPlatform, p);
	
		p.addObserver(mainController);	
				
			
	}

}
