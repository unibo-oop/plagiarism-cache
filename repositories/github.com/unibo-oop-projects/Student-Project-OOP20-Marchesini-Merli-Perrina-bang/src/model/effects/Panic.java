package model.effects;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import model.Player;
import model.Table;
import model.TurnObservable;
import model.card.Card;

public class Panic implements Effect {

	@Override
	public void useEffect(Table table) {
		TurnObservable<Player> opponentOb = table.getChoosePlayerObservable();
		Player current = table.getCurrentPlayer();

		opponentOb.addObserver(() -> {
			Player opponent = opponentOb.get();
			var cardList = opponent.getCards();
			if (cardList.size() > 0) {
				Card c = cardList.get(new Random().nextInt(cardList.size()));
				opponent.removeCard(c);
				current.addCard(c);
			}
		});

		Set<Player> s = new HashSet<>();
		s.add(table.getPlayers().getNextOf(current));
		s.add(table.getPlayers().getPrevOf(current));
		table.choosePlayer(s);
	}

}
