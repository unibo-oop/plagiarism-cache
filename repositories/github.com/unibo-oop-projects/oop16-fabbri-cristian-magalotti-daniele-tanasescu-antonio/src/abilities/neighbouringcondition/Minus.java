package abilities.neighbouringcondition;

import abilities.Ability;
import battle_arena.BattleArena;
import main.view.BattleMenuController;
import managers.BattleLogManager;
import pokemon.Pokemon;

public class Minus extends NeighbouringConditionAbility{

    public Minus() {
        super(  "Minus",                                                                                                //name
                "Boosts the Sp.Atk stat of the Pokemon if an ally with the Plus or Minus Ability is also in battle.",   //description, 
                new String[]{"Minus", "Plus"});                                                                         //neighbouringCondition
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
        user.setOtherModifierFactorSpA(user.getOtherModifierFactorSpA()*1.5);                                                   //+ 50%
    }
    
    @Override
    public void exitingAbility(Pokemon user, Pokemon target, BattleArena battleArena){
        if(this.effectDone){
            user.setOtherModifierFactorSpA(user.getOtherModifierFactorSpA()/1.5);                                               //- 50%
        }
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return BattleLogManager.ACTIVATES;
    }

}
