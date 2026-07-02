package model.effects;

import model.Table;

public class ModifyLifePoints implements Effect {

	private int howMuch;

	public ModifyLifePoints(int howMuch) {
		this.howMuch = howMuch;
	}

	@Override
	public void useEffect(Table table) {
		table.getCurrentPlayer().modifyLifePoints(this.howMuch);
	}
}
