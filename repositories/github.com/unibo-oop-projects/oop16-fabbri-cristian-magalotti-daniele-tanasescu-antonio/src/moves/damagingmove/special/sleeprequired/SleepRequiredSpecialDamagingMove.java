package moves.damagingmove.special.sleeprequired;

import battle_arena.BattleArena;
import moves.damagingmove.special.SpecialDamagingMove;
import pokemon.Pokemon;
import types.Type;

public abstract class SleepRequiredSpecialDamagingMove extends SpecialDamagingMove{

	public SleepRequiredSpecialDamagingMove(String name, String description, int moveBasePower, Type moveType,
			double moveAccuracy, double actualCritRange, int minPP, int priority) {
		super(name, description, moveBasePower, moveType, moveAccuracy, actualCritRange, minPP, priority);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void getDamage(Pokemon user, Pokemon target, BattleArena battleArena){
		if(this.checkSleepCondition(user, target, battleArena)){
			super.getDamage(user, target, battleArena);
		}
		else{
			//errore
		}
	}
	
	public abstract boolean checkSleepCondition(Pokemon user, Pokemon target, BattleArena battleArena);

}
