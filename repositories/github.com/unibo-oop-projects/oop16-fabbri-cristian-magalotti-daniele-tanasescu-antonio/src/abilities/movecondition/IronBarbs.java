package abilities.movecondition;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import managers.BattleLogManager;
import pokemon.Pokemon;

public class IronBarbs extends MoveConditionAbility{

    public IronBarbs() {
        super(  "Iron Barbs",                                                                   //name, 
                "Inflicts damage to the attacker on contact with iron barbs.");                 //description
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
        BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
        target.takeDamage(target.getMaxHp()/8, false);                                                 //damage of 1/8 of its max HP
        
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return BattleLogManager.ACTIVATES;
    }

}
