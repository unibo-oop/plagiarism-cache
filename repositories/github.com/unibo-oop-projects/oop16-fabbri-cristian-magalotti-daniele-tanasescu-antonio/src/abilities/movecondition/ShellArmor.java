package abilities.movecondition;

import battle_arena.BattleArena;
import pokemon.Pokemon;

public class ShellArmor extends MoveConditionAbility{

    public ShellArmor() {
        super(  "Shell Armor ",                                                                                         //name, 
                "A hard shell protects the Pokémon from critical hits. ");                                              //description
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
        if(target.isAttacking && target.crit > 1){                                                               
            return true;
        }
        return false;
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        target.crit = 1;
        
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        return null;
    }

}
