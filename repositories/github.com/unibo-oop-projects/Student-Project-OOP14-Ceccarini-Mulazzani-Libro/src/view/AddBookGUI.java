package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import utilities.GUIUtilities;
import controller.BookController;
import controller.IBookController;
import exceptions.MissingDataException;
import exceptions.WrongDataException;

/**
 * 
 * @author Alberto Mulazzani
 *
 */
public class AddBookGUI extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final JButton add = new JButton("Conferma");
	private final JPanel main = new JPanel();
	private final String[] names = {"Titolo", "Autore", "Anno di pubblicazione", "Editore", "ISBN", "Prezzo", "Quantità" };
	private final JTextField[] fields = new JTextField[names.length];
	/**
	 * 
	 * 
	 * */
	public AddBookGUI() {
		
		
		IBookController controller = BookController.getIstance();
		
		main.setLayout(new BorderLayout());
		
		final JPanel bot = new JPanel(new FlowLayout());
		bot.add(GUIUtilities.getReset(fields));
		bot.add(add);
		main.add(bot, BorderLayout.SOUTH);
				
		
		final JPanel mid = new JPanel(new GridLayout(0 , 2));
		
		for (int i = 0; i < fields.length; i++) {
			fields[i] = new JTextField(GUIUtilities.getAddLenght());
		}
		

		for (int i = 0; i < fields.length; i++) {
			mid.add(GUIUtilities.wrapperPanel(new JLabel(names[i]), FlowLayout.RIGHT));
			mid.add(GUIUtilities.wrapperPanel(fields[i], FlowLayout.CENTER));
		}

		
		main.add(mid, BorderLayout.CENTER);
		
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent e) {
				dispose();
				try {
					controller.setFields(GUIUtilities.getArray(fields));

					JOptionPane.showMessageDialog(main, "Il libro è stato inserito correttamente", "Successo!", JOptionPane.INFORMATION_MESSAGE);
					controller.addBook();
					final JOptionPane optionPane = (JOptionPane)
						    SwingUtilities.getAncestorOfClass(JOptionPane.class, add);
						optionPane.setValue(JOptionPane.CLOSED_OPTION);
									
				} catch (MissingDataException e1) {
					JOptionPane.showMessageDialog(main, "Inserire tutti i dati richiesti", "Dati mancanti", JOptionPane.ERROR_MESSAGE);
				} catch (WrongDataException | NumberFormatException e1) {
					JOptionPane.showMessageDialog(main, "I dati inseriti non sono corretti, ricontrollare", "Dati Errati", JOptionPane.ERROR_MESSAGE);					
				}		
			
			}
		});
		
		
	}
	
	/**
	 * 
	 * @return the panel main.
	 * 
	 */
	public JPanel getPane() {
		return this.main;
	}
	

}
