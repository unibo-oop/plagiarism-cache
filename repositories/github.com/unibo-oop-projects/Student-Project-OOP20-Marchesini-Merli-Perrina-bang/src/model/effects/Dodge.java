package model.effects;

import model.Table;

public class Dodge implements Effect {

	@Override
	public void useEffect(Table table) {
		table.getCurrentPlayer().addProtection();
		table.getCurrentPlayer().addCard(table.getDeck().nextCard());
	}

}
