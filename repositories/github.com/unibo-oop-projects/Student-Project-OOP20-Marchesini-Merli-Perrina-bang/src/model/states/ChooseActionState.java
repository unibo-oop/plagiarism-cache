package model.states;

import java.util.Map;

import static java.util.Map.entry;

import java.util.HashMap;

import model.GameStateMachine;

public class ChooseActionState implements State {

	private GameStateMachine gsMachine = null;
	private final String action;
	private Map<String, Runnable> actionMap = new HashMap<String, Runnable>(Map.ofEntries(entry("playCard", () -> {
		gsMachine.setMessage("playCard");
	}), entry("endTurn", () -> {
		gsMachine.setCurrentState(new EndTurnState());
		gsMachine.go();
	}), entry("discardCard", () -> {
		gsMachine.setMessage("discardCard");
	})));

	public ChooseActionState(final String action) {
		this.action = action;
	}

	@Override
	public void handle(GameStateMachine gsMachine) {
		this.gsMachine = gsMachine;

		if (actionMap.containsKey(action)) {
			this.actionMap.get(action).run();
		}
	}

}
