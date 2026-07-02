package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import utilities.GUIUtilities;
import cartasoci.User;
/**
 * 
 * @author Alberto Mulazzani
 *
 */
public class ListCardGUI extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8563544567918601056L;
	
	private final JPanel main = new JPanel();
	private final String[] names = { "Nome ", "Cognome", "Email", "ID"};
	
	/**
	 * 
	 * @param set is the Map to print
	 */
	public ListCardGUI(final Map<Integer, User> set) {
	
		main.setLayout(new BorderLayout());
		
		final JPanel top = new JPanel(new GridBagLayout());
		GridBagConstraints c = GUIUtilities.getConstr();
		
	
		for (int i = 0; i < names.length; i++) {
			top.add(new JLabel(names[i]), c);
		}
			c.gridy++;
			
			
			
		JLabel[] list = new JLabel[set.values().size() * 4];
		
		for (int i = 0; i < list.length; i++) {
			list[i] = new JLabel();
		}			
		
		int i = 0;
		for (final Entry<Integer, User> e : set.entrySet()) {
			
			list[i].setText(e.getValue().getName());
			list[i + 1].setText(e.getValue().getSurname());
			list[i + 2].setText(e.getValue().getEmail());
			list[i + 3].setText("" + e.getKey());		
			top.add(list[i], c);
			top.add(list[i + 1], c);
			top.add(list[i + 2], c);
			top.add(list[i + 3], c);
			c.gridy++;
			i += 4;
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
