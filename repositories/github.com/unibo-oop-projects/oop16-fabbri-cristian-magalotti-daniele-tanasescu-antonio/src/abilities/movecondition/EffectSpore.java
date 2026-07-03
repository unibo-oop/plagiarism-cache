package abilities.movecondition;

import java.util.Random;

import abilities.otherconditions.Overcoat;
import battle_arena.BattleArena;
import main.view.BattleMenuController;
import managers.BattleLogManager;
import pokemon.Pokemon;
import status_condition.*;
import types.Grass;

public class EffectSpore extends MoveConditionAbility{
	
    public EffectSpore() {
        super(  "Effect Spore",                                                                                           //name, 
                "Contact with the Pokemon may inflict poison, sleep, or paralysis on its attacker.");                     //description
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
        return answer;
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(!target.getType()[0].equals(new Grass()) && target.getType()[1]!= null && !target.getType()[1].equals(new Grass())){                      
            if(target.getAbility().equals(new Overcoat())){
                //activate Overcoat
            }
            else{
                Random random = new Random();
                double probability = random.nextDouble();
                if(probability < 0.3){      
                    BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
                    if(probability < 0.09){                                                            
                        new Poison().setPokemonStatusCondition(target, battleArena);
                    }
                    else if(probability < 0.19){                                                   
                        new Paralysis().setPokemonStatusCondition(target, battleArena);                    
                    }
                    else{                                                                            
                        new Sleep().setPokemonStatusCondition(target, battleArena);
                    }
                }
            }
           
        }
        
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return BattleLogManager.ACTIVATES;
    }

}
