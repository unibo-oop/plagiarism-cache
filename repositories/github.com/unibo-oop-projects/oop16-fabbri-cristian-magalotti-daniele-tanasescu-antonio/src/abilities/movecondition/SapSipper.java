package abilities.movecondition;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import pokemon.Pokemon;
import types.Grass;

public class SapSipper extends MoveConditionAbility{
    
    private static final String SIPPERABSORBS =  "absorbs ";
    private static final String SIPPERATTACK = "'s attack!";
    
    private boolean grassAbsorbed;

    public SapSipper() {
        super(  "Sap Sipper",                                                                                               //name, 
                "Boosts the Attack stat if hit by a Grass-type move, instead of taking damage.");                           //description
        this.grassAbsorbed = false;
    }

    @Override
    public boolean checkMoveCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(target.isAttacking && !this.grassAbsorbed){
            if(target.equals(battleArena.activeEnemy)){
                if(battleArena.enemyMove.getMoveType().equals(new Grass())){
                    return true;
                }
            }
            else{
                if(battleArena.playerMove.getMoveType().equals(new Grass())){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        target.effectiveness = 0;
        BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
        this.grassAbsorbed = true;
        user.setAlterationAtk(+1, true);
        
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return SIPPERABSORBS + target.toString() + SIPPERATTACK;
    }

}
