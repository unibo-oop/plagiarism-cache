package abilities.movecondition;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import managers.BattleLogManager;
import pokemon.Pokemon;

public class RoughSkin extends MoveConditionAbility{

    public RoughSkin() {
        super(  "Rough Skin",                                                                                          //name, 
                "This Pokémon inflicts damage with its rough skin to the attacker on contact. ");                      //description
    }

    @Override
    public boolean checkMoveCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(target.isAttacking){
            if(target.equals(battleArena.activeEnemy)){
                return this.contactCheck(battleArena.enemyMove);
            }
            else{
                return this.contactCheck(battleArena.playerMove);
            }
        }
        return false;
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
        target.takeDamage(target.getMaxHp()/16, false);
        
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return BattleLogManager.ACTIVATES;
    }

}
