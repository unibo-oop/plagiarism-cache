package components;

/**
 * This interface provides methods to implement a renderable label
 * @see TComponent
 * @see TSettable
 */
public interface TLabel extends TComponent, TSettable{
	
	public void setText(String text);
};
