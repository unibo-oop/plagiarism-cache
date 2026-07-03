package abilities.firstturn;

import abilities.Ability;
import battle_arena.BattleArena;
import main.view.BattleMenuController;
import pokemon.Pokemon;

public abstract class FirstTurnAbility extends Ability {

	public FirstTurnAbility(String name, String description) {
		super(name, description);
		this.abilityStartCondition();
	}

	@Override
	public void abilityStartCondition() {
		this.setIsActivable(true);          
	}
	
	@Override
	public void checkForActivation(Pokemon user, Pokemon target, BattleArena battleArena){
	    if(this.getIsActivable()){
	        this.activateAbility(user, target, battleArena);
	        this.setNextCondition(user, target, battleArena);
	    }
	}
	
	@Override
	public void setNextCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
		if(this.getIsActivable()){
			this.setIsActivable(false);
		}
	}	

}