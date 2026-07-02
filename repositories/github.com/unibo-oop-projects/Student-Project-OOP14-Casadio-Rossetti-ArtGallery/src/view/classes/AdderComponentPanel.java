package view.classes;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * This class adds the graphical components to a panel with GridBagLayout.
 * @author Elisa Casadio
 *
 */

public final class AdderComponentPanel {
	
	private static AdderComponentPanel adder;
	
	/**
	 * Empty constructor.
	 */
	private AdderComponentPanel() {
	}
	
	/**
	 * Returns the object associated with the current Java application.
	 * Most of the methods of class AdderComponentPanel are instance
	 * methods and must be invoked with respect to the current object.
	 * 
	 * @return the object associated with the current Java application.
	 */
	public static AdderComponentPanel getAdder() {
		if (adder == null) {
			adder = new AdderComponentPanel();
		}
		return adder;
	}
	
	/**
	 * Adds the component to the panel with a specific layout. Each integer
	 * value passed is a parameter to place the component within the panel.
	 * 
	 * @param p
	 * 			the panel where must be added the JComponent.
	 * @param c
	 * 			the component that must be added.
	 * @param x
	 * 			the number of the column.
	 * @param y
	 * 			the number of the row.
	 * @param gwidth
	 * 			the number of the columns occupied by the component.
	 * @param gheight
	 * 			the number of the rows occupied by the component.
	 * @param align
	 * 			the position of the component in the cell.
	 * @param insTop
	 * 			the top edge of the component.
	 * @param insBottom
	 * 			the bottom edge of the component.
	 * @param layout
	 * 			the layout that must be applied the association between the
	 * 			component and the GridBagConstraints.
	 */
	public void addComponent(final JPanel p, final JComponent c, final int x,
			final int y, final int gwidth, final int gheight, final int align,
			final int insTop, final int insBottom, final GridBagLayout layout) {
		
		final GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.weightx = 1;
		gbc.gridwidth = gwidth;
		gbc.gridheight = gheight;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = align;
		gbc.insets.top = insTop;
		gbc.insets.bottom = insBottom;
		layout.setConstraints(c, gbc);
		p.add(c);
	}

}
