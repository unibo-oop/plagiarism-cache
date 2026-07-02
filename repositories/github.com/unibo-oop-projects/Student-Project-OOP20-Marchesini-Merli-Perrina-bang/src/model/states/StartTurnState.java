package model.states;

import model.GameStateMachine;
import model.Player;
import model.Table;

public class StartTurnState implements State {

	@Override
	public void handle(GameStateMachine gsMachine) {
		Table table = gsMachine.getTable();
		Player current = table.getCurrentPlayer();

		if (!current.hasPrison()) {
			table.getDeck().nextCards(2).forEach(c -> current.addCard(c));
		} else {
			current.setPrison(false);
			gsMachine.setCurrentState(new EndTurnState());
			gsMachine.go();
		}

		gsMachine.setMessage("drawTable");
	}

}
