package abilities.movecondition;

import battle_arena.BattleArena;
import pokemon.Pokemon;

public class Filter extends MoveConditionAbility{

    public Filter() {
        super(  "Filter ",                                                                                              //name, 
                "Reduces the power of supereffective attacks taken.");                                                  //description
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
        if(target.isAttacking && target.effectiveness > 1){                                                               
            return true;
        }
        return false;
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        target.effectiveness *= 0.75; 
        //no message needed        
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        return null;
    }

}
