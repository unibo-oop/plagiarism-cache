package abilities.movecondition;

import java.util.Random;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import managers.BattleLogManager;
import pokemon.Pokemon;
import status_condition.volatile_status.Infatuation;

public class CuteCharm extends MoveConditionAbility{

    public CuteCharm() {
        super(  "Cute Charm",                                                                                           //name, 
                "Contact with the Pokémon may cause infatuation.");                                                     //description
    }

    @Override
    public boolean checkMoveCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
        boolean answer = false;
        if(target.isAttacking){
        	 if(target.equals(battleArena.activeEnemy)){
                 answer = this.contactCheck(battleArena.enemyMove);
             }
             else{
                 answer = this.contactCheck(battleArena.playerMove);
             }
         }
         if(answer){
             if(!target.isGenderless() && !user.isGenderless()){
                 if(user.getGender() != target.getGender()){
                     return true;
                 }
             }
         }
         return false;
     }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
    	Random random = new Random();
        if(random.nextDouble() < 0.3){
	        Infatuation infatuation = new Infatuation();
	        if(!infatuation.isContained(target.volatileStatusConditions)){
	            BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
	            infatuation.addVolatile(target, target.volatileStatusConditions);
	        }
        }
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return BattleLogManager.ACTIVATES;
    }
    
}
