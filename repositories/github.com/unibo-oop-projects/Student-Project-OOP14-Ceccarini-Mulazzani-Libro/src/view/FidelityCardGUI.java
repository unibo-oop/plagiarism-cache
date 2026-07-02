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

import utilities.GUIUtilities;
import controller.FidelityController;
import controller.IFidelityController;
/**
 * 
 * @author Alberto Mulazzani
 *
 */
public class FidelityCardGUI {
	
	private final String[] names = {"Aggiungi una carta", "Rimuovi una carta", "Modifica una carta", "Ricerca una carta", "Lista delle carte"};
	private final JButton[] buttons = new JButton[names.length];
	
	private final BackgroundPanel main = new BackgroundPanel(GUIUtilities.getBackground());
	/**
	 * 
	 * 
	 */
	public FidelityCardGUI() {
		
		IFidelityController fidcontroller = FidelityController.getIstance();
	
		main.setLayout(new BorderLayout());
		
		final JPanel top = new JPanel();
		top.setLayout(new GridBagLayout());
		GridBagConstraints c = GUIUtilities.getConstr();
		
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton(names[i]);
			top.add(buttons[i], c);
			c.gridy++;
			
		}
		
		top.setBorder(new TitledBorder("Gestione Carta Soci"));
		
		final JPanel comandi = new JPanel(new FlowLayout());
		comandi.add(top);
		final JPanel bot = new JPanel(new FlowLayout());
		bot.add(GUIUtilities.getClosing());
		
		main.add(comandi, BorderLayout.CENTER);
		main.add(bot, BorderLayout.SOUTH);
		
		//	AGGIUNGI UNA CARTA
		
		buttons[0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent e) {
				
				
				JOptionPane.showOptionDialog(main, new AddPersonGUI().getPane() , "Aggiungi una carta", JOptionPane.DEFAULT_OPTION, 
	                     JOptionPane.INFORMATION_MESSAGE, GUIUtilities.getCommonIcon(), GUIUtilities.getOptions(), null);
			
			}
		});
		
		//RIMUOVI UNA CARTA
		
		buttons[1].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent e) {
				
				
				JOptionPane.showOptionDialog(main, new RemoveCardGUI().getPane() , "Rimuovi una carta", JOptionPane.DEFAULT_OPTION, 
	                     JOptionPane.INFORMATION_MESSAGE, GUIUtilities.getCommonIcon(), GUIUtilities.getOptions(), null);
			
			}
		});
		
		//MODIFICA UNA CARTA
		
		buttons[2].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent e) {
				
				
				JOptionPane.showOptionDialog(main, new ModifyCardGUI().getPane() , "Modifica una carta", JOptionPane.DEFAULT_OPTION, 
	                     JOptionPane.INFORMATION_MESSAGE, GUIUtilities.getCommonIcon(), GUIUtilities.getOptions(), null);
			
			}
		});
		
		//RICERCA UNA CARTA
		
		buttons[3].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent e) {
				
				
				JOptionPane.showOptionDialog(main, new SearchCardGUI().getPane() , "Ricerca una carta", JOptionPane.DEFAULT_OPTION, 
	                     JOptionPane.INFORMATION_MESSAGE, GUIUtilities.getCommonIcon(), GUIUtilities.getOptions(), null);
			
			}
		});
		
		//LISTA DELLE CARTE
		
		buttons[4].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(final ActionEvent e) {
				
				
				JOptionPane.showOptionDialog(main, new ListCardGUI(fidcontroller.getMap()).getPane() , "Lista delle carte", JOptionPane.DEFAULT_OPTION, 
	                     JOptionPane.INFORMATION_MESSAGE, GUIUtilities.getCommonIcon(), GUIUtilities.getOptions(), null);
			
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
