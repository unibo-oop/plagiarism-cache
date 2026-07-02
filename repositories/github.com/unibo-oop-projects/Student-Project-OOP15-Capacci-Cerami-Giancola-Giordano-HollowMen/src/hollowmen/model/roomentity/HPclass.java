package hollowmen.model.roomentity;

import hollowmen.model.Actor;
import hollowmen.model.Enemy;
import hollowmen.model.LimitedCounter;
import hollowmen.model.Modifier;
import hollowmen.model.Parameter;
import hollowmen.model.RoomEntity;
import hollowmen.model.dungeon.DungeonSingleton;
import hollowmen.model.dungeon.InfoImpl;
import hollowmen.model.dungeon.ParamImpl;
import hollowmen.model.utils.LowerLimitReachException;
import hollowmen.model.utils.SimpleLimitedCounter;
import hollowmen.model.utils.UpperLimitReachException;
import hollowmen.utilities.ExceptionThrower;

/**
 * This class is a {@link Parameter} but use {@link Modifier} for modify his value without tracking them<br>
 * This class use another {@code Parameter} (HPMAX) for determinate his value, changes at HPMAX will also modify
 * this class value
 * @author pigio
 *
 */
public class HPclass extends ParamImpl{

	private Parameter maxHP;
	private LimitedCounter health;
	private double lastMaxHP;
	private Actor owner;
	
	public HPclass(Parameter maxHP, Actor subj) {
		super(new InfoImpl(Parameter.ParamName.HP.toString()), maxHP.getValue());
		this.maxHP = maxHP;
		this.owner = subj;
		this.lastMaxHP = maxHP.getValue();
		this.health = new SimpleLimitedCounter(maxHP.getValue(), maxHP.getValue());
	}

	/**
	 * This method get the value but checking if HPMAX {@code Parameter} hasn't change
	 */
	@Override
	public double getValue() {
		if(lastMaxHP != maxHP.getValue()) {
			this.health = new SimpleLimitedCounter(this.health.getValue() + (maxHP.getValue() - lastMaxHP),
					this.health.getLimit() + (maxHP.getValue() - lastMaxHP));
			this.lastMaxHP = maxHP.getValue();
		}
		return this.health.getValue();
	}

	/**
	 * This method will add HP, in other words this will "Heal"
	 */
	@Override
	public void addModifier(Modifier mod) throws IllegalArgumentException, NullPointerException {
		ExceptionThrower.checkNullPointer(mod);
		ExceptionThrower.checkIllegalArgument(mod, m -> this.getInfo().getName().equals(m));
		try {
			if(mod.getOperation().equals(Modifier.Operation.ADD.getOp())) {
				this.health.addToValue(mod.getParameter().getValue());
			} else {
				this.health.addToValue(mod.getOperation().apply(this.health.getValue(), mod.getParameter().getValue()));
			}
		} catch (UpperLimitReachException e) {};

	}

	/**
	 * This method will remove HP, in other words this will "Damage"
	 */
	@Override
	public void removeModifier(Modifier mod) throws IllegalArgumentException, NullPointerException {
		ExceptionThrower.checkNullPointer(mod);
		ExceptionThrower.checkIllegalArgument(mod, m -> this.getInfo().getName().equals(m));
		try {
			if(mod.getOperation().equals(Modifier.Operation.ADD.getOp())) {
				this.health.subToValue(mod.getParameter().getValue());
			} else {
				this.health.subToValue(mod.getOperation().apply(this.health.getValue(), mod.getParameter().getValue()));
			}
		} catch (LowerLimitReachException e) {
			if(this.owner instanceof Enemy) {
				DungeonSingleton.getInstance().getHero().pick(((Enemy) this.owner).getLoot());
				this.owner.dispose();
			}
			if(this.owner.getInfo().getName().equals(RoomEntity.RoomEntityName.HERO.toString())) {
				DungeonSingleton.getInstance().gameOver();
			}
		};
		
	}
}
