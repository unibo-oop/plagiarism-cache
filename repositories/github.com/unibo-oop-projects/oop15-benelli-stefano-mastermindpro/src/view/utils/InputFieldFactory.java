package view.utils;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.players.PlayerType;
import view.images.ImageSetFactory.SetGroup;

/**
 * 
 * @author Stefano Benelli
 * This is an helper class used to create common Input Controls
 */
public class InputFieldFactory {
	
	/**
	 * Get a new Instance of JComboBox already populated with values in the interval
	 * @param minValue Minimum value
	 * @param maxValue Maximum value
	 * @return ComboBox
	 */
	public static JComboBox<Integer> getIntegerComboBox(final int minValue, final int maxValue) {
		
	    JComboBox<Integer> cb = new JComboBox<Integer>();
	    for (int i = minValue; i <= maxValue; i++) {
	    	cb.addItem(i);
	    }
	    return cb;
	}
	
	/**
	 * Get a new Instance of a JComboBox for the PlayerType
	 * @return ComboBox
	 */
	public static JComboBox<PlayerType> getPlayerTypeComboBox() {
		JComboBox<PlayerType> cb = new JComboBox<PlayerType>(PlayerType.values());
		return cb;
	}
	
	/**
	 * Get a new Instance of a JComboBox for the SetGroup
	 * @return ComboBox
	 */
	public static JComboBox<SetGroup> getImageSetsComboBox() {
		JComboBox<SetGroup> cb = new JComboBox<SetGroup>(SetGroup.values());
		return cb;
	}

	/**
	 * Get a new Instance of a Panel containing Label and Component passed
	 * @param label Label to display
	 * @param text Component to display
	 * @return Panel Panel composed by Label and Component
	 */
	public static JPanel getLabelTextPanel(final JLabel label, final JComponent text) {
		JPanel row = new JPanel(new FlowLayout());
		row.add(label, FlowLayout.LEFT);
		text.setPreferredSize(new Dimension(70,22));
		row.add(text, FlowLayout.CENTER);
		return row;
	}
}
