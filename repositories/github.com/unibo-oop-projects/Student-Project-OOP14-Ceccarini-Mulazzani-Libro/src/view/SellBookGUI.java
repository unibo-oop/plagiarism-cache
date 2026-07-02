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

import model.Libro;
import utilities.GUIUtilities;
import controller.BookController;
import controller.FidelityController;
import controller.IBookController;
import controller.IFidelityController;
import exceptions.MissingBookException;
import exceptions.MissingUserException;
import exceptions.NotEnoughBookException;
/**
 * 
 * @author Alberto Mulazzani
 *
 */
public class SellBookGUI extends JDialog {
	
	private static final long serialVersionUID = 6224252338040945108L;
	private final String[] names = {"Titolo", "Autore", "Copie", "Carta"};
	private final JTextField[] fields = new JTextField[names.length];
	private final JPanel main = new JPanel();
	private final JButton conf = new JButton("Conferma");
	private	Libro lib;
	/**
	 * 
	 * 
	 */
	public SellBookGUI() {
		
		final IBookController controller = BookController.getIstance();
		final IFidelityController fidcontroller = FidelityController.getIstance();
		
		main.setLayout(new BorderLayout());
		
		final JPanel mid = new JPanel(new GridLayout(0, 2));
		
		for (int i = 0; i < fields.length; i++) {
			fields[i] = new JTextField(GUIUtilities.getAddLenght());
		}
		
		for (int i = 0; i < fields.length; i++) {
			mid.add(GUIUtilities.wrapperPanel(new JLabel(names[i]), FlowLayout.RIGHT));
			mid.add(GUIUtilities.wrapperPanel(fields[i], FlowLayout.CENTER));

		}
		
		main.add(mid, BorderLayout.CENTER);
		
		final JPanel bot = new JPanel(new FlowLayout());
		
		bot.add(conf);
		bot.add(GUIUtilities.getReset(fields));
		main.add(bot, BorderLayout.SOUTH);
	
		
		conf.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent e) {
				
				try {
					lib = controller.searchBook(GUIUtilities.getArray(fields));

					controller.sellBook(lib, fields[2].getText());
//				Questo controllo evita un bug che abbiamo riscontrato dopo la consegna
//				lo correggiamo per volontà di completezza, ma alla consegna NON era presente l'if
//					if (fields[3].getText().length() != 0) {
						fidcontroller.addPoints(GUIUtilities.getArray(fields), lib.getPrice());		
//					}
					JOptionPane.showMessageDialog(main, "Il libro è stato venduto con successo!!", "Successo!!", JOptionPane.INFORMATION_MESSAGE);
					final JOptionPane optionPane = (JOptionPane)
						    SwingUtilities.getAncestorOfClass(JOptionPane.class, conf);
						optionPane.setValue(JOptionPane.CLOSED_OPTION);
				} catch (MissingBookException e1) {
					JOptionPane.showMessageDialog(main, "Il libro non esiste in magazzino", "Il libro non esiste", JOptionPane.ERROR_MESSAGE);
				} catch (NotEnoughBookException e1) {
					JOptionPane.showMessageDialog(main, "Non sono presenti abbastanza copie in magazzino \n "
							+ "ne sono state richieste " + fields[2].getText() + ", ma ne sono presenti solo " + lib.getNCopy(), "Copie mancanti", JOptionPane.ERROR_MESSAGE);
				} catch (MissingUserException e1) {
					JOptionPane.showMessageDialog(main, "La carta non è presente in archivio", "La carta non esiste", JOptionPane.ERROR_MESSAGE);				
					
				} catch (IllegalArgumentException e1) {
					JOptionPane.showMessageDialog(main, "Errore Grave!! Contattare l'assistenza!", "Attenzione!!", JOptionPane.ERROR_MESSAGE);
				}
				
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
