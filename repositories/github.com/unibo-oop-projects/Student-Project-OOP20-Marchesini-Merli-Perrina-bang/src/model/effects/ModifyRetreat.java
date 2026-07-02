package model.effects;

import model.Table;

public class ModifyRetreat implements Effect {

	int howMuch;

	public ModifyRetreat(int howMuch) {
		this.howMuch = howMuch;
	}

	@Override
	public void useEffect(Table table) {
		table.getCurrentPlayer().modifyRetreat(howMuch);
	}

}
