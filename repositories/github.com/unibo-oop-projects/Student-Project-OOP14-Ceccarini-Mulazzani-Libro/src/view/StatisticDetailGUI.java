package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import utilities.GUIUtilities;
import utilities.Pair;

/**
 * 
 * @author Alberto Mulazzani
 *
 */
public class StatisticDetailGUI extends JDialog {

	private static final long serialVersionUID = -8563544567918601056L;
	private final JPanel main = new JPanel();
	private final String[] names = {"Autore", "Libri prodotti"};
	
	/**
	 * 
	 * @param set is the List to print
	 */
	public StatisticDetailGUI(final List<Pair < String, Integer>> set) {
		
		main.setLayout(new BorderLayout());
		
		final JPanel top = new JPanel(new GridBagLayout());
		GridBagConstraints c = GUIUtilities.getConstr();
		
		
		for (int i = 0; i < names.length; i++) {
			top.add(new JLabel(names[i]));
		}
			c.gridy++;
		
		JLabel[] list = new JLabel[set.size() * 2];
		
		for (int i = 0; i < list.length; i++) {
			list[i] = new JLabel();
		}
		
		
		System.out.println(set.size());
		int i = 0;
		
		for (final Pair<String, Integer> p : set) {
			list[i].setText(p.getFirst());
			list[i + 1].setText("" + p.getSecond());
			top.add(list[i], c);
			top.add(list[i + 1], c);

			c.gridy++;
			i += 2;
		}
		
		
		final JPanel mid = new JPanel(new FlowLayout());
		mid.add(top);
		
		final JScrollPane extPane = new JScrollPane(top);
		extPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	    final int x = (dim.width) / 5;
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
