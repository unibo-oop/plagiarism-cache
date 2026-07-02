package hollowmen.model.roomentity.hero.skilltree;

import hollowmen.model.Information;
import hollowmen.model.Modifier;
import hollowmen.model.dungeon.DungeonSingleton;
import hollowmen.model.utils.Actors;

/**
 * This class is a simple {@link SkillNode} that adds his {@link Modifier}, each time a point is spent on it, to the {@link Hero}
 * 
 * @author pigio
 *
 */
public class SkillNodeModifier extends SkillNodeImpl{

	
	private Modifier mod;
	
	public SkillNodeModifier(Information info, String tag, int level, double limit, Modifier mod) {
		super(info, tag, level, limit);
	}

	/**
	 * This method adds this {@code SkillNodeModifier}'s {@code Modifier} to the {@code Hero}
	 * {@inheritDoc SimpleLimitedCounter}
	 */
	@Override
	public void addToValue(double value) throws IllegalArgumentException, IllegalStateException {
		super.addToValue(value);
		Actors.addModifier(DungeonSingleton.getInstance().getHero(), this.mod);
	}
	
	/**
	 * This method removes this {@code SkillNodeModifier}'s {@code Modifier} to the {@code Hero}
	 * {@inheritDoc SimpleLimitedCounter}
	 */
	@Override
	public void subToValue(double value) throws IllegalArgumentException, IllegalStateException {
		super.addToValue(value);
		Actors.removeModifier(DungeonSingleton.getInstance().getHero(), this.mod);
	}
	
}
