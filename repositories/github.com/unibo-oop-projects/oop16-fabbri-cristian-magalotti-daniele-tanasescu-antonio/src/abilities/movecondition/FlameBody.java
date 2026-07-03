package abilities.movecondition;

import java.util.Random;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import managers.BattleLogManager;
import pokemon.Pokemon;
import status_condition.Burn;

public class FlameBody extends MoveConditionAbility{                                                                                   
    
    public FlameBody() {
        super(  "Flame Body",                                                    //name, 
                "Contact with the Pokemon may burn the attacker.");              //description
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
        Random random = new Random();
        if(random.nextDouble() < 0.3){
            BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
            new Burn().setPokemonStatusCondition(target, battleArena);
        }
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return BattleLogManager.ACTIVATES;
    }
    

}
