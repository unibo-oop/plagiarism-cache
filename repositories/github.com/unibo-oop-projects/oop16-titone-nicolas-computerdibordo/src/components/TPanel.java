package components;

/**
 * This interface provides methods to implement a renderable panel.
 * @see TComponent
 * @see TSettable
 */
public interface TPanel extends TComponentWithChildren, TSettable{
	
	public void addComponent(TComponent c);
	
	public void setDimension(int width, int height);
};
