package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import utilities.GUIUtilities;
import controller.BookController;
import controller.EarningsController;
import controller.IBookController;
import controller.IEarningsController;
/**
 * 
 * @author Alberto Mulazzani
 *
 */
public class EconomyGUI {

	private final String[] names = {"Totale libri venduti", "Totali in negozio", "Totale guadagnato", "Totale speso", "Totale fatturato"};
	private final JPanel main = new JPanel();
	/**
	 * 
	 * 
	 * 
	 */
	public EconomyGUI() {
		
		final IBookController controller = BookController.getIstance();
		final IEarningsController earcontroller = EarningsController.getIstance();
		
		
		main.setLayout(new BorderLayout());
		
		final JPanel mid = new JPanel(new GridLayout(0, 2));

		
		mid.add(GUIUtilities.wrapperPanel(new JLabel(names[0]), FlowLayout.RIGHT));
		mid.add(GUIUtilities.wrapperPanel(new JLabel("" + earcontroller.bookSold(controller.bookList())), FlowLayout.CENTER));
				
		mid.add(GUIUtilities.wrapperPanel(new JLabel(names[1]), FlowLayout.RIGHT));
		mid.add(GUIUtilities.wrapperPanel(new JLabel("" + earcontroller.bookInStore(controller.bookList())), FlowLayout.CENTER));
				
					
		mid.add(GUIUtilities.wrapperPanel(new JLabel(names[2]), FlowLayout.RIGHT));
		mid.add(GUIUtilities.wrapperPanel(new JLabel("" + earcontroller.totSell(controller.bookList()) + "€"), FlowLayout.CENTER));
						
					
		mid.add(GUIUtilities.wrapperPanel(new JLabel(names[3]), FlowLayout.RIGHT));
		mid.add(GUIUtilities.wrapperPanel(new JLabel("" + earcontroller.totSpent(controller.bookList()) + "€"), FlowLayout.CENTER));
					
			
		mid.add(GUIUtilities.wrapperPanel(new JLabel(names[4]), FlowLayout.RIGHT));
		mid.add(GUIUtilities.wrapperPanel(new JLabel("" + earcontroller.totEarnings(controller.bookList()) + "€"), FlowLayout.CENTER));
		
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
