package model.effects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import model.Player;
import model.Table;
import model.card.Card;

public class CatBalou implements Effect {

	@Override
	public void useEffect(Table table) {
		List<Player> others = new ArrayList<>(table.getPlayers());
		others.remove(table.getCurrentPlayer());

		var plObs = table.getChoosePlayerObservable();
		plObs.addObserver(() -> {
			Player opponent = plObs.get();
			Random r = new Random();
			if (opponent.getCards().size() > 0) {
				Card card = opponent.getCards().get(r.nextInt(opponent.getCards().size()));
				opponent.removeCard(card);
			}
		});

		table.choosePlayer(others.stream().collect(Collectors.toSet()));
	}
}
