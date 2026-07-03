package components;

/**
 * This interface provides methods to implement a renderable frame. 
 * @see TComponent
 * @see TimeSettableWithChildren
 */
public interface TFrame extends TComponentWithChildren{
	
	/**
	 * This method set the visibility of this component
	 * @param b a Boolean representing the visibility (true = visible).
	 */
	public void SetVisibility(Boolean b);
	
	/**
	 * This method set the dimension of this component(width and height).
	 * @param width the width wanted
	 * @param height the height wanted
	 */
	public void setDimension(int width, int height);
};
