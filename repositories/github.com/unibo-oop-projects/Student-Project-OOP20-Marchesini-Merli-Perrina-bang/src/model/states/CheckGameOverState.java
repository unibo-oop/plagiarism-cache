package model.states;

import model.GameStateMachine;
import model.Role;

public class CheckGameOverState implements State {

	@Override
	public void handle(final GameStateMachine gsMachine) {
		if (this.isOver(gsMachine)) {
			gsMachine.setCurrentState(new EndGameState());
			gsMachine.go();
		}
	}

	/*
	 * Checks if game is over
	 */
	private boolean isOver(final GameStateMachine gsMachine) {
		/*
		 * Check if sheriff is alive. If not, the game is over.
		 */
		if (gsMachine.getTable().getPlayers().stream().filter(p -> p.getRole().equals(Role.SHERIFF)).count() == 0) {
			return true;
		}
		/*
		 * Check if any outlaw or renegade is alive. If not, the game is over.
		 */
		if (gsMachine.getTable().getPlayers().stream()
				.filter(p -> p.getRole().equals(Role.OUTLAW) || p.getRole().equals(Role.RENEGADE)).count() == 0) {
			return true;
		}
		if (gsMachine.getTable().getPlayers().size() <= 1) {
			return true;
		}
		return false;
	}

}
