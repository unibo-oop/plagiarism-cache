package model.effects;

import model.Table;

public class DrawCardsFromDeck implements Effect {

	private int howMany;

	public DrawCardsFromDeck(int howMany) {
		this.howMany = howMany;
	}

	@Override
	public void useEffect(Table table) {
		table.getDeck().nextCards(this.howMany).forEach(c -> table.getCurrentPlayer().addCard(c));
	}

}
