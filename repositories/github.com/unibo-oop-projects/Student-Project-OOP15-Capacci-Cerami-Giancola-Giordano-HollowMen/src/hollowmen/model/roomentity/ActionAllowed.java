package hollowmen.model.roomentity;

import java.util.Map;

/**
 * A little Strategy Interface that hold the set of Action an Actor can do
 * @author pigio
 *
 */
public interface ActionAllowed {
	
	/**
	 * 
	 * @return Map of String and function to run
	 */
	public Map<String, Runnable> getActionAllowed();
}
