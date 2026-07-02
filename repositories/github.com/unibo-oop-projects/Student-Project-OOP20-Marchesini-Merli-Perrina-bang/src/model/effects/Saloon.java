package model.effects;

import model.Table;

public class Saloon implements Effect {

	int howMany;

	public Saloon(int howMany) {
		this.howMany = howMany;
	}

	@Override
	public void useEffect(Table table) {
		table.getPlayers().forEach(p -> {
			p.modifyLifePoints(howMany);
		});
	}

}
