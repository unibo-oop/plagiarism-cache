package abilities.movecondition;

import java.util.Random;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import managers.BattleLogManager;
import pokemon.Pokemon;
import status_condition.Paralysis;

public class Static extends MoveConditionAbility{

    public Static() {
        super(  "Static",                                                                                            //name, 
                "The Pokémon is charged with static electricity, so contact with it may cause paralysis.");          //description
    }

    @Override
    public boolean checkMoveCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(target.isAttacking){
            if(target.equals(battleArena.activeEnemy)){
                return battleArena.enemyMove.isMakingContact();
            }
            else{                                                                                                    //player
                return battleArena.playerMove.isMakingContact();
            }
        }
        return false;
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.3){
            BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
            new Paralysis().setPokemonStatusCondition(target, battleArena);
        }
        
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return BattleLogManager.ACTIVATES;
    }

}
