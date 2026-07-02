package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Libro;
import utilities.GUIUtilities;
/**
 * 
 * @author Alberto Mulazzani
 *
 */
public class BookDetailGUI extends JDialog {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5294910808056208082L;
	private final JPanel main = new JPanel();
	private final String[] names = {"Titolo", "Autore", "Anno di pubblicazione", "Editore", "ISBN", "Prezzo", "Quantità", "Copie Vendute" };
	
	/**
	 * 
	 * @param b is the Book to use
	 */
	public BookDetailGUI(final Libro b) {
		
		main.setLayout(new BorderLayout());
		
		final JPanel mid = new JPanel(new GridLayout(0, 2));
		
		final String[] fields = b.getFields();
		

		for (int i = 0; i < fields.length; i++) {
			mid.add(GUIUtilities.wrapperPanel(new JLabel(names[i]), FlowLayout.RIGHT));
			mid.add(GUIUtilities.wrapperPanel(new JLabel(fields[i]), FlowLayout.LEFT));
		}
		
		
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
