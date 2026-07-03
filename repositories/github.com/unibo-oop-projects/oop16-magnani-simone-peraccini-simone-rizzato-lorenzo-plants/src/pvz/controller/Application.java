package pvz.controller;


import pvz.view.ViewInterface;
import pvz.view.View;
public class Application {
	
	 
    /**
     * Starts the application.
     * @throws Exception 
     */
    public static void main(final String[] args) throws Exception {
    	
    	final ControllerInterface controller = new Controller(); 
    	final ViewInterface view = new View(controller);
    	((Controller) controller).setView(view);;
    	view.init();
    	
    }

}
