package controller;

public class Main {
	
	private final ControllerImpl controller;
	
	private Main() {
		this.controller = new ControllerImpl();
	}
	
	private void start() {// Non puoi 
		this.controller.startController();
	}

	public static void main(final String[] args) {
        final Main application = new Main();
        application.start();
        //startare il menu' del Mon
        //this.controller(parametri passati dal Mon)
    }
}
