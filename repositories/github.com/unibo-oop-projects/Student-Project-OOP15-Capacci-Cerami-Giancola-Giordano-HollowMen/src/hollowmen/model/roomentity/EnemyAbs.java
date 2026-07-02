package hollowmen.model.roomentity;

import java.util.Collection;

import org.jbox2d.dynamics.Filter;

import hollowmen.model.Enemy;
import hollowmen.model.Information;
import hollowmen.model.Lootable;
import hollowmen.model.Parameter;
import hollowmen.model.dungeon.FilterType;
import hollowmen.model.utils.Algorithms;
import hollowmen.model.utils.Box2DUtils;
import hollowmen.utilities.Pair;

/**
 * This class extends {@link ActorAbs} and implements {@link Enemy}
 * @author pigio
 *
 */
public abstract class EnemyAbs extends ActorAbs implements Enemy{

	protected MovePattern pattern;
	private int combatLevel;
	private String title;
	private Lootable loot;
	private boolean hitWall;
	
	public EnemyAbs(Information info, Pair<Float, Float> size, Collection<Parameter> param, int power, String title) {
		super(info, size, param);
		this.combatLevel = power;
		this.title = title;
		pattern = movePattern();
		this.loot = genLoot();
	}
	
	/**
	 * This method can be overridden by the final classes for change their move path
	 * @return {@link MovePatter}
	 */
	protected MovePattern movePattern() {
		return new DumbMovePattern(this);
	}

	/**
	 * This method moves this {@code Enemy} based on his {@code MovePattern} without caring at input
	 */
	@Override
	public void move(String s) {
		super.move(pattern.getDirection());
	}

	/**
	 * {@inheritDoc Enemy}
	 */
	@Override
	public Lootable getLoot() {
		return this.loot;
	}
	
	/**
	 * {@inheritDoc Enemy}
	 */
	@Override
	public int getLevel() {
		return this.combatLevel;
	}

	/**
	 * {@inheritDoc Enemy}
	 */
	@Override
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * This utility method give a standard {@link Filter} for each {@code Enemy}
	 * @return
	 */
	public Filter standardEnemyFilter() {
		return Box2DUtils.filterBuilder()
				.addCategory(FilterType.ENEMY.getValue())
				.addMask(FilterType.GROUND.getValue())
				.addMask(FilterType.WALL.getValue())
				.addMask(FilterType.HERO.getValue())
				.addMask(FilterType.HEROATTACK.getValue())
				.build();
	}
	
	/**
	 * This method can be overridden for determinate what Loot this {@code Enemy} have
	 * @return {@link Lootable}
	 */
	protected Lootable genLoot() {
		return Algorithms.genLootEnemy(this);
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + combatLevel;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Enemy)) {
			return false;
		}
		Enemy other = (Enemy) obj;
		if (combatLevel != other.getLevel())
			return false;
		if (title == null)
			if (other.getTitle() != null)
				return false;
			else if (!title.equals(other.getTitle()))
			return false;
		return super.equals(obj);
	}

	/**
	 * This method is used by {@link MovePattern}
	 * @return {@code true} if this {@code Enemy} is hitting a wall, {@code false} otherwise
	 */
	public boolean isHittingWall() {
		return hitWall;
	}

	/**
	 * This method is  used by {@link GameCollisionListener} for tell this {@code Enemy} that he's hitting a wall or not
	 * @param hitWall
	 */
	public void setHitWall(boolean hitWall) {
		this.hitWall = hitWall;
	}
	
}
