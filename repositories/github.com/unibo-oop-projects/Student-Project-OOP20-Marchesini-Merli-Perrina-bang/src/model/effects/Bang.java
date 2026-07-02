package model.effects;

import model.Logics;
import model.Player;
import model.Table;
import model.TurnObservable;

public class Bang implements Effect {

	@Override
	public void useEffect(Table table) {
		Logics logics = new Logics(table);
		var targets = logics.getTargets();

		if ((table.getCurrentPlayer().getActiveCardsByName("volcanic").size() != 0
				|| !table.getPlayerUsedCards().contains("bang")) && targets.size() != 0) {
			TurnObservable<Player> ob = table.getChoosePlayerObservable();

			ob.addObserver(() -> {
				Player p = ob.get();

				if (p.hasProtection()) {
					p.removeProtection();
				} else {
					p.modifyLifePoints(-1);
				}
			});

			table.choosePlayer(targets);
			table.playerUsedCard("bang");
		}
	}
}
