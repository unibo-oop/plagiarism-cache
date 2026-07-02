package model.states;

import java.util.List;
import java.util.stream.Collectors;

import model.GameStateMachine;
import model.Player;
import model.Table;

public class CheckDeadPlayersState implements State {

	@Override
	public void handle(final GameStateMachine gsMachine) {
		Table table = gsMachine.getTable();
		List<Player> dead = table.getPlayers().stream().filter(p -> p.getLifePoints() == 0)
				.collect(Collectors.toList());
		dead.forEach(p -> table.removePlayer(p));
		if (!dead.isEmpty()) {
			gsMachine.setMessage("drawTable");
			;
			gsMachine.setCurrentState(new CheckGameOverState());
			gsMachine.go();
		}
	}

}
