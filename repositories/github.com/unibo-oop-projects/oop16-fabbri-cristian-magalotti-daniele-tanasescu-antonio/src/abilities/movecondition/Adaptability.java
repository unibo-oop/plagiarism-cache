package abilities.movecondition;

import battle_arena.BattleArena;
import pokemon.Pokemon;

public class Adaptability extends MoveConditionAbility{

    public Adaptability() {
        super(  "Adaptability",                                                               //name
                "Powers up moves of the same type as the Pokemon.");                          //description
    }

    @Override
    public void setNextCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(checkMoveCondition(user, target, battleArena)){
            this.setIsActivable(true);
        }
        else{
            this.setIsActivable(false);
        }
    }
    
    @Override
    public boolean checkMoveCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(user.isAttacking && user.stab == 1.5){
            return true;
        }
        return false;
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        user.stab = 2;
        //no message needed
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        return null;
    }

}
