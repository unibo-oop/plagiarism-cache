package model.states;

import model.GameStateMachine;

public interface State {

	/**
	 * Performs actions.
	 * 
	 * @param gsMachine
	 */
	void handle(final GameStateMachine gsMachine);
}
