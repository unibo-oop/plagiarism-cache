package main.gameEntities.modifiers;

import main.dynamicBody.character.player.Stats;
import main.worldModel.utilities.Pair;
import main.worldModel.utilities.enums.Entities;

public class HealthUpgrade1 extends ModifiersImpl{

	public HealthUpgrade1(Pair<Integer, Integer> coord) {
		super(coord, Stats.HEALTH, 10, "Health Upgrade", Entities.HEALTHUPGRADE1);
	}
}
