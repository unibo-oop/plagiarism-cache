package hollowmen.model.roomentity;

import java.util.HashMap;
import java.util.Map;

/**
 * This class implements {@link ActionAllowed}
 * @author pigio
 *
 */
public class ActionAllowedImpl implements ActionAllowed{

	private Map<String, Runnable> actionMap;
	
	public ActionAllowedImpl() {
		this.actionMap = new HashMap<>();
	}
	
	/**
	 * {@inheritDoc ActionAllowed}
	 */
	@Override
	public Map<String, Runnable> getActionAllowed() {
		return this.actionMap;
	}

}
