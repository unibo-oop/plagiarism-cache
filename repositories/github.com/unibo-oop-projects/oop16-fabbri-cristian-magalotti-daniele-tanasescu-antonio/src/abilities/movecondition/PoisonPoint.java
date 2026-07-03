package abilities.movecondition;

import java.util.Random;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import managers.BattleLogManager;
import pokemon.Pokemon;
import status_condition.Poison;

public class PoisonPoint extends MoveConditionAbility{

    public PoisonPoint() {
        super(  "Poison Point",                                                                                        //name, 
                "Contact with the Pokémon may poison the attacker.");                                                  //description
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
            new Poison().setPokemonStatusCondition(target, battleArena);
        }
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return BattleLogManager.ACTIVATES;
    }

}
