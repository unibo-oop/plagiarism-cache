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
import exceptions.MissingUserException;
/**
 * 
 * @author Alberto Mulazzani
 *
 */
public class ModifyCardGUI {
	
	
	
	private final JPanel main = new JPanel();
	private final JButton conf = new JButton("Conferma");
	private final JTextField[] fields = new JTextField[1];
	private final String[] names = {"Identificativo"};
	/**
	 * 
	 * 
	 */
	public ModifyCardGUI() {
		
		
		final IFidelityController fidcontroller = FidelityController.getIstance();
		
		main.setLayout(new BorderLayout());
		
		final JPanel mid = new JPanel(new GridLayout(0, 2));
		
		
		for (int i = 0; i < fields.length; i++) {
			fields[i] = new JTextField(GUIUtilities.getModifyLenght());
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
			public void actionPerformed(final ActionEvent arg0) {
				try {
					JOptionPane.showOptionDialog(main, new CardDetailsToModifyGUI(fidcontroller.searchID(fields[0].getText())).getPane() , "Modifica libro", JOptionPane.DEFAULT_OPTION, 
					         JOptionPane.INFORMATION_MESSAGE, GUIUtilities.getCommonIcon(), GUIUtilities.getOptions(), null);
					final JOptionPane optionPane = (JOptionPane)
						    SwingUtilities.getAncestorOfClass(JOptionPane.class, conf);
						optionPane.setValue(JOptionPane.CLOSED_OPTION);
				} catch (MissingUserException e) {
					
					JOptionPane.showMessageDialog(main, "L'ID non è presente", "ID mancante", JOptionPane.ERROR_MESSAGE);							
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
