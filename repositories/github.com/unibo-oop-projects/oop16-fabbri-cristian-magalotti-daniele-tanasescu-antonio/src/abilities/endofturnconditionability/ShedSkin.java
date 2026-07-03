package abilities.endofturnconditionability;

import java.util.Random;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import managers.BattleLogManager;
import pokemon.Pokemon;

public class ShedSkin extends EndOfTurnConditionAbility {

	public ShedSkin() {
		super(	"Shed Skin", 
			"The Pok�mon may heal its own status conditions by shedding its skin.");
	}

	@Override
	public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
		Random random = new Random();
		if(user.statusCondition != null){
			if(random.nextDouble() < 0.3){
				user.statusCondition.exitingStatusAlteration(user);
				BattleMenuController.battleLogManager.setAbilityActivationMessage
				                                      (user, this.getAbilityEffect(user, target, battleArena));
			}
		}
		
	}

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return BattleLogManager.ACTIVATES;
    }

}
