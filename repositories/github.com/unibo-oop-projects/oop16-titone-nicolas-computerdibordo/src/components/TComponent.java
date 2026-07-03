package components;

/**
 * This interface provides methods to implement a renderable graphical component.
 * @see TComponent
 * @see TSettable
 */
public interface TComponent extends TSettable{
	
	/**
	 * This method repaint the component
	 */
	public void repaint();
};