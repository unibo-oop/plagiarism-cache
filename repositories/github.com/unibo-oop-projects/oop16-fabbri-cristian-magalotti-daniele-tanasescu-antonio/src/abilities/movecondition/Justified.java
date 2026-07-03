package abilities.movecondition;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import managers.BattleLogManager;
import moves.damagingmove.DamagingMove;
import pokemon.Pokemon;
import types.Dark;

public class Justified extends MoveConditionAbility{

    public Justified() {        
        super(  "Justified",                                                                                       //name, 
                "Being hit by a Dark-type move boosts the Attack stat of the Pokemon, for justice.");              //description
    }

    @Override
    public boolean checkMoveCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(target.isAttacking){
            if(target.equals(battleArena.activeEnemy)){
                if(battleArena.enemyMove instanceof DamagingMove && battleArena.enemyMove.getMoveType().equals(new Dark())){
                    return true;
                }
            }
            else{
                if(battleArena.playerMove instanceof DamagingMove && battleArena.playerMove.getMoveType().equals(new Dark())){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
        user.setAlterationAtk(+1, true);
        
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return BattleLogManager.ACTIVATES;
    }

}
