package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utilities.GUIUtilities;
import cartasoci.User;
/**
 * 
 * @author Alberto Mulazzani
 *
 */
public class CardDetailGUI extends JDialog {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5294910808056208082L;
	private final JPanel main = new JPanel();
	private final String[] names = {"Nome", "Cognome", "Email", "ID", "Punti"};
	/**
	 * 
	 * @param b is the User to analyze
	 */
	public CardDetailGUI(final User b) {
		
		main.setLayout(new BorderLayout());
		
		final JPanel mid = new JPanel(new GridLayout(0, 2));
		
	

		mid.add(GUIUtilities.wrapperPanel(new JLabel(names[0]), FlowLayout.RIGHT));
		mid.add(GUIUtilities.wrapperPanel(new JLabel(b.getName()), FlowLayout.RIGHT));

		
		mid.add(GUIUtilities.wrapperPanel(new JLabel(names[1]), FlowLayout.RIGHT));
		mid.add(GUIUtilities.wrapperPanel(new JLabel(b.getSurname()), FlowLayout.RIGHT));
				
		mid.add(GUIUtilities.wrapperPanel(new JLabel(names[2]), FlowLayout.RIGHT));
		mid.add(GUIUtilities.wrapperPanel(new JLabel(b.getEmail()), FlowLayout.RIGHT));
				
		mid.add(GUIUtilities.wrapperPanel(new JLabel(names[3]), FlowLayout.RIGHT));
		mid.add(GUIUtilities.wrapperPanel(new JLabel("" + b.getID()), FlowLayout.RIGHT));
				
		mid.add(GUIUtilities.wrapperPanel(new JLabel(names[4]), FlowLayout.RIGHT));
		mid.add(GUIUtilities.wrapperPanel(new JLabel("" + b.getPoints()), FlowLayout.RIGHT));
		
		
		main.add(mid, BorderLayout.CENTER);
		
		
	}
	
	/**
	 * 
	 * @return the main panel of the GUI
	 */
	public JPanel getPane() {
		return this.main;
	}
	
	
	
}
