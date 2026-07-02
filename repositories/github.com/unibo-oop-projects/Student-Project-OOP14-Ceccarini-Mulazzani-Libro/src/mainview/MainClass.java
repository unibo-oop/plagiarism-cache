package mainview;

import javax.swing.SwingUtilities;

import view.Main;
/**
 * @author Chiara Ceccarini
 * @author Alberto Mulazzani
 * 
 *
 */
public final class MainClass {
	
	private MainClass() {
		
	}

	/**
	 * 
	 * @param args of main
	 */
    public static void main(final String... args) { 
    	

    	
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new Main();
				Main.createAndShowGUI();
            }
        });
	}

}
