package components;

import java.awt.LayoutManager;

/**
 * This interface provides methods to render a graphical component who can add other component insede of itself.
 *
 */
public interface TComponentWithChildren extends TComponent {
	
	/**
	 * This method is used to add a {@link @TComponent} to this component.
	 * @param c the component
	 */
	public void addComponent(TComponent c);
	
	/**
	 * This method is used to add a {@link @TComponent} to this component in a defined position
	 * @see TPosition
	 * @param c the component
	 * @param position a String representation of the position
	 */
	public void addComponent(TComponent c, String position);
	
	/**
	 * This method clear the component by its children components.
	 */
	public void clear();
	
	/**
	 * This method set a layout manager for this component
	 * @param m the layout manager to be set
	 */
	public void setLayout(LayoutManager m);
}

