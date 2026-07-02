package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import utilities.GUIUtilities;
import controller.FidelityController;
import controller.IFidelityController;
import exceptions.MissingDataException;
import exceptions.UserAlreadyExisting;
import exceptions.WrongDataException;

/**
 * 
 * @author Alberto Mulazzani
 *
 */
public class AddPersonGUI {
	
	private final JTextField[] fields = new JTextField[3];
	private final String[] names = {"Nome", "Cognome", "Email"};
	private final JButton add = new JButton("Conferma");
	private final JPanel main = new JPanel();
	/**
	 * 
	 * 
	 */
	public AddPersonGUI() {
		
		IFidelityController fidcontroller = FidelityController.getIstance();
		
		main.setLayout(new BorderLayout());		
		main.setLayout(new BorderLayout());
		
		final JPanel bot = new JPanel(new FlowLayout());
		bot.add(GUIUtilities.getReset(fields));
		bot.add(add);
		main.add(bot, BorderLayout.SOUTH);
				
		
		final JPanel mid = new JPanel(new GridLayout(0, 2));
		
		for (int i = 0; i < fields.length; i++) {
			fields[i] = new JTextField(GUIUtilities.getAddLenght());
		}
		
		for (int i = 0; i < fields.length; i++) {
			mid.add(GUIUtilities.wrapperPanel(new JLabel(names[i]), FlowLayout.RIGHT));
			mid.add(GUIUtilities.wrapperPanel(fields[i], FlowLayout.CENTER));
		}
		
		main.add(mid, BorderLayout.CENTER);

		
		// Handler dei pulsanti
		

		
		//PULSANTE CONFERMA
		
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent e) {
				try {
	
					fidcontroller.addPerson(GUIUtilities.getArray(fields));
					JOptionPane.showMessageDialog(main, "La carta è stata creata con successo, il suo ID è " + fidcontroller.getCurrent(), "Successo!", JOptionPane.INFORMATION_MESSAGE);
					
					final JOptionPane optionPane = (JOptionPane)
						    SwingUtilities.getAncestorOfClass(JOptionPane.class, add);
						optionPane.setValue(JOptionPane.CLOSED_OPTION);
			
				} catch (UserAlreadyExisting e1) {
					JOptionPane.showMessageDialog(main, "L'utente è già presente", "Utente già presente", JOptionPane.ERROR_MESSAGE);
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
	 * @return the main panel of the GUI
	 */
	public JPanel getPane() {
		return main;
	}

}
