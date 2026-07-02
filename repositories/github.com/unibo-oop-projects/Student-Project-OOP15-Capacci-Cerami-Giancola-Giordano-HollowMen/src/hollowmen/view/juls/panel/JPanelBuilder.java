package hollowmen.view.juls.panel;

import java.awt.Component;

import javax.swing.JPanel;

/**
 * The {@code JPanelBuilder} interface defines the methods
 * that the concrete builder will call.
 * 
 * It's based on the Builder Creational Pattern.
 * @author Juls
 */
public interface JPanelBuilder {

	/**
	 * The {@code layout} method allows to arrange components in a GridLayout.
	 * @param rows - number of rows
	 * @param columns - number of columns
	 * @param horizontalGap - horizontal distance between components
	 * @param verticalGap - vertical distance between components
	 */
	public JPanelBuilder layout(int rows, int columns, int horizontalGap, int verticalGap);
	
	/**
	 * The {@code bound} method allows to arrange panel's position and size.
	 * @param x - x coordinate
	 * @param y - y coordinate
	 * @param width - width of the panel
	 * @param height - height of the panel
	 */
	public JPanelBuilder bound(int x, int y, int width, int height);
	
	/**
	 * The {@code addTo} method allows to add components to the panel.
	 * @param component - the component to add
	 */
	public JPanelBuilder addTo(Component component);
	
	/**
	 * The {@code build} method builds all the characteristics together
	 * in order to create the panel.
	 * @return the panel
	 */
	public JPanel build();
	
}