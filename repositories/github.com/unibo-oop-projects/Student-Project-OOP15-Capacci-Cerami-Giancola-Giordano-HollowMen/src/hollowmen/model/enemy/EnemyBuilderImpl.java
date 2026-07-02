package hollowmen.model.enemy;

import java.util.Collection;

import java.util.LinkedList;
import java.util.List;

import hollowmen.enumerators.ParamName;
import hollowmen.model.Enemy;
import hollowmen.model.Parameter;
import hollowmen.model.dungeon.ParamImpl;
import hollowmen.utilities.ExceptionThrower;

/**
 * This abstract class implements {@link EnemyBuilder}
 * <br>
 * The abstract method is the name of the {@link Enemy} to build
 * <br>
 * This class has a method for checking if the methods were called
 *  with the right input
 * @author pigio
 *
 */
public abstract class EnemyBuilderImpl implements EnemyBuilder{

	protected String descritpion;
	protected int level;
	protected String title;
	protected Collection<Parameter> parameters;
	
	
	final List<String> titleName = titleName();
	
	
	private List<String> titleName() {
		List<String> list = new LinkedList<>();
		for(Enemy.EnemyTitle e : Enemy.EnemyTitle.values()) {
			list.add(e.toString());
		}
		return list;
	}
	
	
	/**
	 * {@inheritDoc EnemyBuilder}
	 */
	@Override
	public EnemyBuilder description(String description) {
		this.descritpion = description;
		return this;
	}

	
	/**
	 * {@inheritDoc EnemyBuilder}
	 */
	@Override
	public EnemyBuilder level(int level) {
		this.level = level;
		return this;
	}

	/**
	 * {@inheritDoc EnemyBuilder}
	 */
	@Override
	public EnemyBuilder title(String title) {
		this.title = title;
		return this;
	}

	/**
	 * {@inheritDoc EnemyBuilder}
	 */
	@Override
	public EnemyBuilder param(Collection<Parameter> parameters) {
		this.parameters = new LinkedList<>();
		parameters.stream().forEach(p -> this.parameters.add(new ParamImpl(p)));
		return this;
	}

	/**
	 * This method is useful for checking if the {@code Enemy} built
	 * is suitable for the game
	 * @param e {@link Enemy}
	 * @throws IllegalStateException
	 */
	public void standardException(Enemy e) throws IllegalStateException{
		ExceptionThrower.checkIllegalState(e.getLevel(), l -> l <= 0 || l >= 20);
		ExceptionThrower.checkIllegalState(e.getParameters(), p -> !p.containsKey(ParamName.HPMAX.toString()) 
				|| p.get(ParamName.HPMAX.toString()).getValue() <= 0 );
		ExceptionThrower.checkIllegalState(e.getParameters(), p -> !p.containsKey(ParamName.DEFENSE.toString()));
		ExceptionThrower.checkIllegalState(e.getParameters(), p -> !p.containsKey(ParamName.ATTACK.toString()));		
		ExceptionThrower.checkIllegalState(e.getParameters(), p -> !p.containsKey(ParamName.ATTACKRANGE.toString()));
		ExceptionThrower.checkIllegalState(e.getParameters(), p -> !p.containsKey(ParamName.ATTACKSPEED.toString())
				|| p.get(ParamName.ATTACKSPEED.toString()).getValue() <= 0);
		ExceptionThrower.checkIllegalState(e.getParameters(), p -> !p.containsKey(ParamName.MOVSPEED.toString()));
		ExceptionThrower.checkIllegalState(e.getTitle(), c -> !c.contains(this.title));
	}
	/**
	 * {@inheritDoc EnemyBuilder}
	 */
	@Override
	public abstract Enemy build() throws IllegalStateException;

}






