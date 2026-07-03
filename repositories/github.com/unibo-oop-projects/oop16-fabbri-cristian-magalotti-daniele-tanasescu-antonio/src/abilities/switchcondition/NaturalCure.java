package abilities.switchcondition;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import managers.BattleLogManager;
import pokemon.Pokemon;

public class NaturalCure extends SwitchConditionAbility{

    public NaturalCure() {
        super(  "Natural Cure",                                                                                         //name, 
                "All status conditions heal when the Pokemon switches out.");                                           //description
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(user.statusCondition != null){
            BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
            user.statusCondition.exitingStatusAlteration(user);
        }
        else{
            
        }
        
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return BattleLogManager.ACTIVATES;
    }

}
