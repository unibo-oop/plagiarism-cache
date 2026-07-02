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
import controller.BookController;
import controller.IBookController;
import exceptions.MissingBookException;
/**
 * 
 * @author Alberto Mulazzani
 *
 */
public class ModifyBookGUI {
	
	
	
	private final JPanel main = new JPanel();
	private final JButton conf = new JButton("Conferma");
	private final JTextField[] fields = new JTextField[2];
	private final String[] names = {"Titolo", "Autore"};
	
	/**
	 * 
	 * 
	 */
	public ModifyBookGUI() {
		
		
		final IBookController controller = BookController.getIstance();
		
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
					JOptionPane.showOptionDialog(main, new DetailsToModifyGUI(controller.searchBook(GUIUtilities.getArray(fields))).getPane() , "Modifica libro", JOptionPane.DEFAULT_OPTION, 
					         JOptionPane.INFORMATION_MESSAGE, GUIUtilities.getCommonIcon(), GUIUtilities.getOptions(), null);
					final JOptionPane optionPane = (JOptionPane)
						    SwingUtilities.getAncestorOfClass(JOptionPane.class, conf);
						optionPane.setValue(JOptionPane.CLOSED_OPTION);
				} catch (MissingBookException e) {
					
					JOptionPane.showMessageDialog(main, "Il libro non è presente", "Libro mancante", JOptionPane.ERROR_MESSAGE);							
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
