package model.effects;

import model.Player;
import model.Table;

/**
 * this the class of cards witch allow you to see other players
 */

public class Scope implements Effect {

	@Override
	public void useEffect(Table table) {
		Player p = table.getCurrentPlayer();
		p.modifySight(p.getSight() + 1);
	}
}
