package abilities.movecondition;

import battle_arena.BattleArena;
import pokemon.Pokemon;

public class BattleArmor extends MoveConditionAbility{

    public BattleArmor() {
        super(  "Battle Armor",                                                                                           //name, 
                "Hard armor protects the Pokemon from critical hits.");                                                   //description
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
        if(target.isAttacking){
            return true;
        }
        return false;
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        target.crit = 1;
        //no message needed
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        return null;
    }

}
