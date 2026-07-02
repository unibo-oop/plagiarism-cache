package view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 * This class generate a Dialog with the type of error and close the application.
 * 
 *
 */
public class ExceptionDialog {
    
    /**
     * Title of the Dialog.
     */
	private static final String TITLEERRORFRAME = "ERROR !!!";
	/**
     * Frame of the dialog.
     */
	private static final JFrame FRAME = new JFrame();
	
	/**
	 * 
	 * @param message
	 *                the Exception String message
	 *                
	 */
	public ExceptionDialog(final String message) {

		
	           JOptionPane.showMessageDialog(FRAME,
	            	    message,
	            	    TITLEERRORFRAME,
	            	    JOptionPane.ERROR_MESSAGE);
	            		
	           System.exit(0); //close the execution of the program

	           
	           
	}

}
