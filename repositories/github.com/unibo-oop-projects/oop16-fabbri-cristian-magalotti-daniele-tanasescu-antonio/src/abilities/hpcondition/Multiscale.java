package abilities.hpcondition;

import battle_arena.BattleArena;
import pokemon.Pokemon;

public class Multiscale extends HPConditionAbility{

    public Multiscale() {
        super(  "Multiscale",                                                                                //name 
                "Reduces the amoint of damage the Pokemon takes when its HP is full.",                       //description 
                1);                                                                                          //hpBound
    }
    
    @Override
    public void setNextCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
          if (user.getHp() == this.hpBound*user.getMaxHp() && target.isAttacking){
                this.setIsActivable(true);
          }   
          else{
              this.effectDone = false;         
          }
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        target.effectiveness *= 0.5;                                                                          //half the damage
        //no message needed
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        return null;
    }

}
