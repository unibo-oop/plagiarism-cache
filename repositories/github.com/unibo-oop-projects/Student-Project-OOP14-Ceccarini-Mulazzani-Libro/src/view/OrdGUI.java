package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import utilities.ControllerUtilities.TipoController;
import utilities.GUIUtilities;
import controller.BookController;
import controller.IBookController;
import exceptions.MissingBookException;
/**
 * 
 * @author Alberto Mulazzani
 *
 */
public class OrdGUI {
	
	
	
	private final String[] names = {"Aggiungi un ordine", "Rimuovi un ordine", "Modifica un ordine", "Ricerca un ordine", "Lista degli Ordini", "Evadi gli ordini"};
	private final JButton[] buttons = new JButton[names.length];
	private final BackgroundPanel main = new BackgroundPanel(GUIUtilities.getBackground());
	/**
	 * 
	 * 
	 */
	public OrdGUI() {
		
		final IBookController controller = BookController.getIstance();
		
		main.setLayout(new BorderLayout());
	    
		final JPanel top = new JPanel();
		top.setLayout(new GridBagLayout());
		top.setBorder(new TitledBorder("Gestione Ordini"));
		GridBagConstraints c = GUIUtilities.getConstr();

		
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton(names[i]);
			top.add(buttons[i], c);
			c.gridy++;
		}
		
		final JPanel topExt = new JPanel(new FlowLayout());
		topExt.add(top);

		
		main.add(topExt, BorderLayout.NORTH);
		
		final JPanel bot = new JPanel(new FlowLayout());
		bot.add(GUIUtilities.getClosing());
		main.add(bot, BorderLayout.SOUTH);
		
		buttons[0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent e) {
				
				controller.setType(TipoController.ORDINI);	
				JOptionPane.showOptionDialog(main, new AddBookGUI().getPane() , "Aggiungi un ordine", JOptionPane.DEFAULT_OPTION, 
	                     JOptionPane.INFORMATION_MESSAGE, GUIUtilities.getCommonIcon(), GUIUtilities.getOptions(), null);
			
			}
		});
		
		buttons[1].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent e) {

				controller.setType(TipoController.ORDINI);	
				JOptionPane.showOptionDialog(main, new RemoveOrderGUI().getPane() , "Rimuovi un ordine", JOptionPane.DEFAULT_OPTION, 
	                     JOptionPane.INFORMATION_MESSAGE, GUIUtilities.getCommonIcon(), GUIUtilities.getOptions(), null);
			
			}
		});
		
		buttons[2].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent e) {
				
				controller.setType(TipoController.ORDINI);	
				JOptionPane.showOptionDialog(main, new ModifyBookGUI().getPane() , "Modifica un ordine", JOptionPane.DEFAULT_OPTION, 
	                     JOptionPane.INFORMATION_MESSAGE, GUIUtilities.getCommonIcon(), GUIUtilities.getOptions(), null);
			
			}
		});
		
		
		
		buttons[3].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent e) {
				
				controller.setType(TipoController.ORDINI);	
				JOptionPane.showOptionDialog(main, new SearchBookGUI().getPane() , "Cerca un ordine", JOptionPane.DEFAULT_OPTION, 
	                     JOptionPane.INFORMATION_MESSAGE, GUIUtilities.getCommonIcon(), GUIUtilities.getOptions(), null);
			
			}
		});
		
		buttons[4].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent e) {
				
				controller.setType(TipoController.ORDINI);	
				JOptionPane.showOptionDialog(main, new ListTableGUI(controller.bookList(), false).getPane() , "Lista degli ordini", JOptionPane.DEFAULT_OPTION, 
	                     JOptionPane.INFORMATION_MESSAGE, GUIUtilities.getCommonIcon(), GUIUtilities.getOptions(), null);
			
			}
		});
		
		buttons[5].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent e) {
				
				controller.setType(TipoController.ORDINI);	
				try {
					controller.evasioneOrdini();
				} catch (MissingBookException e1) {
					JOptionPane.showMessageDialog(main, "Non ci sono ordini!", "Dati mancanti", JOptionPane.ERROR_MESSAGE);
				}
				
				JOptionPane.showMessageDialog(main, "Ordini evasi correttamente!", "Successo!", JOptionPane.INFORMATION_MESSAGE);
	
			}
		});
		
		
	}
	
	/**
	 * 
	 * @return the main panel of the GUI
	 */
	public JPanel getPane() {
		return this.main;
	}
	
	
	
	
	
}
