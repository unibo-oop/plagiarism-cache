package model.effects;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.Player;
import model.Role;
import model.Table;
import model.TurnObservable;

public class Jail implements Effect {

	@Override
	public void useEffect(Table table) {
		Player p1 = table.getCurrentPlayer();
		TurnObservable<Player> ob = table.getChoosePlayerObservable();
		List<Player> others = new ArrayList<>(table.getPlayers());
		others.remove(p1);
		others.remove(table.getPlayers().stream().filter(p -> p.getRole().equals(Role.SHERIFF)).findFirst().get());

		if (!others.isEmpty()) {
			ob.addObserver(() -> {
				Player p2 = ob.get();
				p2.setPrison(true);
			});

			table.choosePlayer(others.stream().collect(Collectors.toSet()));
		}
	}

}
