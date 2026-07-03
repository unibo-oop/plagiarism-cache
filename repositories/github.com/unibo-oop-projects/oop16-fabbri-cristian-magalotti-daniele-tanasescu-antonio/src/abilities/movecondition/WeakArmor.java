package abilities.movecondition;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import managers.BattleLogManager;
import moves.damagingmove.physical.PhysicalDamagingMove;
import pokemon.Pokemon;

public class WeakArmor extends MoveConditionAbility{
    
    public WeakArmor() {
        super(  "Weak Armor",                                                                                            //name
                "Physical attacks to the Pokémon lower its Defense stat but sharply raise its Speed stat. ");            //description
    }

    @Override
    public boolean checkMoveCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
        boolean answer;
        if(target.equals(battleArena.activeEnemy)){
            answer = (target.isAttacking && battleArena.enemyMove instanceof PhysicalDamagingMove);
        }
        else{
            answer = (target.isAttacking && battleArena.playerMove instanceof PhysicalDamagingMove);
        }
        return answer;
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
        user.setAlterationDef(-1, true);
        user.setAlterationSpe(+1, true);
        
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return BattleLogManager.ACTIVATES;
    }

}
