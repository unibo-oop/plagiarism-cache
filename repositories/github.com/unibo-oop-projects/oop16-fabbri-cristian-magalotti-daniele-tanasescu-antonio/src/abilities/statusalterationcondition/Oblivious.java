package abilities.statusalterationcondition;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import managers.BattleLogManager;
import pokemon.Pokemon;
import status_condition.volatile_status.Infatuation;
import status_condition.volatile_status.VolatileStatusCondition;

public class Oblivious extends StatusAlterationConditionAbility{

    public Oblivious() {
        super(  "Oblivious",                                                                                        //name, 
                "The Pokémon is oblivious, and that keeps it from being infatuated.");                              //description 
    }

    @Override
    public void checkStatusCondition(Pokemon user) {
        if(new Infatuation().isContained(user.volatileStatusConditions)){
            this.setIsActivable(true);
        }
        else{
            this.setIsActivable(false);
        }
        
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
        for(VolatileStatusCondition vsc : user.volatileStatusConditions){
            if(vsc != null && vsc.equals(new Infatuation())){
                vsc.deleteOneVolatile(user);
                break;
            }
        }
        
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return BattleLogManager.ACTIVATES;
    }

}
