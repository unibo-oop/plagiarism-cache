package model.effects;

import model.Table;

public class Weapon implements Effect {

	private int range;
	private String card;

	public Weapon(int range, String card) {
		this.range = range;
		this.card = card;
	}

	@Override
	public void useEffect(Table table) {
		table.getCurrentPlayer().addWeapon(this.card);
		table.getCurrentPlayer().modifySight(this.range);
	}
}
