package utilities;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * 
 * @author Alberto Mulazzani
 *
 */
public final class GUIUtilities {
	/**
	 * 
	 */
	private static final String[] STRING = {"Annulla"};
	/**
	 * 
	 */
	private static ImageIcon icon = createImageIcon("/iconBook.png");
	/**
	 * 
	 */
	private static Image image = icon.getImage();
	private static ImageIcon icon2 = createImageIcon("/background.png");
	/**
	 * 
	 */
	private static Image back = icon2.getImage();


	private static final int MODIFYLENGHT = 15;
	private static final int ADDLENGHT = 20;
	
	private GUIUtilities() {
		
	}
	/**
	 * 
	 * @return the String Array for the JOptionPanes
	 */
	public static String[] getOptions() {
		return STRING;
	}
	
	/**
	 * 
	 * @return the Icon of the Software
	 */
	public static ImageIcon getCommonIcon() {
		return icon;
	}
	/**
	 * 
	 * @return the Background
	 */
	public static Image getBackground() {
		return back;
	}
	/**
	 * 
	 * @return the Logo of the software
	 */
	public static Image getCommonImage() {
		return image;
	}
	
	
	
	/**
	 * 
	 * @return the costants C used for the GridBagLayout
	 */
	public static GridBagConstraints getConstr() {
		final GridBagConstraints c = new GridBagConstraints();
		c.gridy = 0;
		c.insets = new Insets(3, 3 , 7 , 3);
		c.fill = GridBagConstraints.HORIZONTAL;
		
		return c;
	}
	/**
	 * 
	 * @return the Close button used in the other classes
	 */
	public static JButton getClosing() {
		final JButton chiudi = new JButton("Chiudi");
		chiudi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent e) {
	     	    final String[] objButtons = {"Sì", "No"};
        	    final int promptResult = JOptionPane.showOptionDialog(null, 
        	        "Sei sicuro di voler uscire? I dati non salvati saranno persi", "Sei proprio sicuro?", 
        	        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, 
        	        objButtons, objButtons[1]);
        	    if (promptResult == 0) {
        	      System.exit(0);          
        	    }
			}
		});
		return chiudi;	
		
	}
	
	/**
	 * 
	 * @param fields are the JTextField given by the GUI
	 * @return a Reset Button
	 */
	public static JButton getReset(final JTextField... fields) {
		final JButton reset = new JButton("Reset");
		
		reset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent e) {
				for (int i = 0; i < fields.length; i++) {
					fields[i].setText("");
				}
			}
		});
		
		return reset;
	}
	/**
	 * 
	 * @return the Lenght for all the Add JTextField
	 */
	public static int getAddLenght() {
		return ADDLENGHT;
	}
	/**
	 * 
	 * @return the lenght for all the Modify JTextField
	 */
	public static int getModifyLenght() {
		return MODIFYLENGHT;
	}
	
	private static ImageIcon createImageIcon(final String path) {
		final java.net.URL imgURL = GUIUtilities.class.getResource(path);

		if (imgURL == null) {
			System.err.println("Couldn't find image file: " + path);
			return null;

		} else {
			return new ImageIcon(imgURL);
		}
	}
	/**
	 * 
	 * @param component is the component to add in the panel
	 * @param orientation is the position of the component
	 * @return a wrapped panel with the component and the given orientation
	 */
	public static JPanel wrapperPanel(final JComponent component, final int orientation) {
		final JPanel panel = new JPanel(new FlowLayout(orientation));
		panel.add(component);
		return panel;
	}
	/**
	 * 
	 * @param fields are the JTextField of the GUI
	 * @return an array with the Text of the fields;
	 */
	public static String[] getArray(final JTextField... fields) {
		String[] string = new String[fields.length];
		
		for (int i = 0; i < fields.length; i++) {
			string[i] = fields[i].getText();
		}
		
		
		return string;
		
	}
	
	
}
