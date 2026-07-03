package abilities.neighbouringcondition;

import abilities.Ability;
import battle_arena.BattleArena;
import managers.BattleLogManager;
import pokemon.Pokemon;

public class Plus extends NeighbouringConditionAbility{

    public Plus() {
        super(  "Plus",                                                                                                 //name
                "Boosts the Sp.Atk stat of the Pokemon if an ally with the Plus or Minus Ability is also in battle.",   //description, 
                new String[]{"Minus", "Plus"});                                                                         //neighbouringCondition
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
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
