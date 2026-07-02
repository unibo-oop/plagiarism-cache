package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Libro;
import utilities.GUIUtilities;
/**
 * 
 * @author Alberto Mulazzani
 *
 */
public class ListTableGUI extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8563544567918601056L;
	private final JLabel[] list;
	private final JPanel main = new JPanel();
	private String[] names = { "Titolo ", "Autore ", "Copie Vendute "};
	
	/**
	 * 
	 * @param set is the List to print
	 * @param tipo is the Type of the BookController
	 */
	public ListTableGUI(final List<Libro> set, final boolean tipo) {
		
		main.setLayout(new BorderLayout());
		
		final JPanel top = new JPanel(new GridBagLayout());
		GridBagConstraints c = GUIUtilities.getConstr();
		
		if (!tipo) {
			names[2] = " Copie";
		}
	
		for (int i = 0; i < names.length; i++) {
			top.add(new JLabel(names[i]), c);
		}
			c.gridy++;
		
		list = new JLabel[set.size() * 3];
		JButton[] buttons = new JButton[set.size()];
		
		for (int i = 0; i < list.length; i++) {
			list[i] = new JLabel();
		}
		
		int i = 0;
		int j = 0;
				
		
		for (final Libro b : set) {
			buttons[j] = new JButton("Dettagli");
			list[i].setText(b.getTitle());
			list[i + 1].setText(b.getAuthor());
			if (tipo) {
				list[i + 2].setText("" + b.getNSales());
			} else {
				list[i + 2].setText("" + b.getNCopy());
			}
			buttons[j].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(final ActionEvent e) {
						JOptionPane.showOptionDialog(main, new BookDetailGUI(b).getPane() , "Dettagli Libro", JOptionPane.DEFAULT_OPTION, 
						         JOptionPane.INFORMATION_MESSAGE, GUIUtilities.getCommonIcon(), GUIUtilities.getOptions(), null);

				}
			});
			top.add(list[i], c);
			top.add(list[i + 1], c);
			top.add(list[i + 2], c);
			top.add(buttons[j], c);
			c.gridy++;
			i += 3;
			j++;
		}
		
		
		final JPanel mid = new JPanel(new FlowLayout());
		mid.add(top);
		
		final JScrollPane extPane = new JScrollPane(top);
		extPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	    final int x = (dim.width) / 4;
	    final int y = (dim.height) / 3;
		extPane.setPreferredSize(new Dimension(x, y));
		
		main.add(extPane);
		
		
	}
	
	
	/**
	 * 
	 * @return the main panel of the GUI
	 */
	
	public JPanel getPane() {
		return this.main;
	}
	
	
}
