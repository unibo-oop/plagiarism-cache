package model.states;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.GameStateMachine;
import model.Player;
import model.Role;

public class EndGameState implements State {

	@Override
	public void handle(GameStateMachine gsMachine) {
		List<Player> players = new ArrayList<>(gsMachine.getTable().getPlayers());

		if (players.stream().filter(p -> p.getRole().equals(Role.SHERIFF)).collect(Collectors.toList()).isEmpty()) {
			if (players.size() != 1) {
				players.stream().filter(p -> !p.getRole().equals(Role.OUTLAW))
						.forEach(p -> gsMachine.getTable().removePlayer(p));
			}
		}

		gsMachine.setMessage("end");
	}
}
