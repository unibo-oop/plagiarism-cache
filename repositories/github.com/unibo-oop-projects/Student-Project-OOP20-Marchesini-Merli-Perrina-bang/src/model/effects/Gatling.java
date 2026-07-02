package model.effects;

import java.util.ArrayList;
import java.util.List;

import model.Player;
import model.Table;

public class Gatling implements Effect {

	@Override
	public void useEffect(Table table) {
		List<Player> others = new ArrayList<>(table.getPlayers());
		others.remove(table.getCurrentPlayer());

		others.forEach(p -> {
			if (p.hasProtection()) {
				p.removeProtection();
			} else {
				p.modifyLifePoints(-1);
			}
		});
	}

}
