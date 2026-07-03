package abilities.movecondition;

import battle_arena.BattleArena;
import pokemon.Pokemon;

public class Sniper extends MoveConditionAbility{

    private boolean effectDone;

    public Sniper() {
        super(  "Sniper",                                                                                            //name, 
                "Powers up moves if they become critical hits when attacking.");          					         //description
        this.effectDone = false;
    }

    @Override
    public void setNextCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(checkMoveCondition(user, target, battleArena)){
            this.setIsActivable(true);
        }
        else{
            this.setIsActivable(false);
            this.effectDone = false;
        }
    }

    @Override
    public boolean checkMoveCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(user.isAttacking && user.crit > 1){
            return true;
        }
        return false;

    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        //no message needed
        if(!this.effectDone){
            user.crit = 2.25;
            this.effectDone = true;
        }
        else{
            user.crit = 1;
            this.effectDone = false;
        }

    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        return null;
    }
}