package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utilities.GUIUtilities;
import controller.FidelityController;
import controller.IFidelityController;
import exceptions.MissingUserException;
/**
 * 
 * @author Alberto Mulazzani
 *
 */
public class SearchCardGUI extends JDialog {
	

	private static final long serialVersionUID = 6224252338040945108L;

	private final String[] names = {"Nome", "Cognome", "ID"};
	private final JTextField[] fields = new JTextField[names.length];
	private final JPanel main = new JPanel();
	/**
	 * 
	 * 
	 */
	public SearchCardGUI() {
		
		final IFidelityController fidcontroller = FidelityController.getIstance();
		main.setLayout(new BorderLayout());
		
		final JPanel mid = new JPanel(new GridLayout(0, 3));
		final JButton[] oks = new JButton[names.length];
		
		for (int i = 0; i < fields.length; i++) {
			fields[i] = new JTextField(GUIUtilities.getModifyLenght());
			oks[i] = new JButton("Ok");
		}
		
		int j = 0;
		
		for (int i = 0; i < fields.length; i++) {
			mid.add(GUIUtilities.wrapperPanel(new JLabel(names[i]), FlowLayout.RIGHT));
			mid.add(GUIUtilities.wrapperPanel(fields[i], FlowLayout.CENTER));
			if (j > 0) {
				mid.add(GUIUtilities.wrapperPanel(oks[j], FlowLayout.LEFT));			
			} else {
				mid.add(GUIUtilities.wrapperPanel(new JLabel(), FlowLayout.LEFT));			
			}
			j++;
		}
		
		main.add(mid, BorderLayout.CENTER);
		
		final JPanel bot = new JPanel(new FlowLayout());
		
		bot.add(GUIUtilities.getReset(fields));
		main.add(bot, BorderLayout.SOUTH);
		
		oks[1].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent e) {
				
				try {
					JOptionPane.showOptionDialog(main, new CardDetailGUI(fidcontroller.searchName(fields[0].getText(), fields[1].getText())).getPane() , "Ricerca titolo", JOptionPane.DEFAULT_OPTION, 
					         JOptionPane.INFORMATION_MESSAGE, GUIUtilities.getCommonIcon(), GUIUtilities.getOptions(), null);
				} catch (HeadlessException | IllegalArgumentException | MissingUserException e1) {
					JOptionPane.showMessageDialog(main, "La persona non è presente", "Persona mancante", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		
		oks[2].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent e) {
				
				try {
					JOptionPane.showOptionDialog(main, new CardDetailGUI(fidcontroller.searchID(fields[2].getText())).getPane() , "Ricerca autore", JOptionPane.DEFAULT_OPTION, 
					         JOptionPane.INFORMATION_MESSAGE, GUIUtilities.getCommonIcon(), GUIUtilities.getOptions(), null);
				} catch (HeadlessException | MissingUserException e1) {
					JOptionPane.showMessageDialog(main, "L'ID non è presente", "ID mancante", JOptionPane.ERROR_MESSAGE);
				} catch (IllegalArgumentException e1) {
					JOptionPane.showMessageDialog(main, "I Dati inseriti sono errati", "Dati errati", JOptionPane.ERROR_MESSAGE);
					
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
