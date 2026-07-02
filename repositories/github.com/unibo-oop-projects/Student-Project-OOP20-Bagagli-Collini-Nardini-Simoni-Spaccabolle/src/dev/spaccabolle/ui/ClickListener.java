package dev.spaccabolle.ui;


/**
 * The listener interface for receiving click events.
 * The class that is interested in processing a click
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's of ClickListener method. When
 * the click event occurs, that object's appropriate
 * method is invoked.
 *
 */
public interface ClickListener {
	
	/**
	 * On click.
	 */
	public void onClick();
	
	

}