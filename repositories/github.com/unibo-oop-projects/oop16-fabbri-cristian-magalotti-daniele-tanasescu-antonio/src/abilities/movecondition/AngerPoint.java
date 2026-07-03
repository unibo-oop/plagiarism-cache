package abilities.movecondition;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import managers.BattleLogManager;
import pokemon.Pokemon;

public class AngerPoint extends MoveConditionAbility{

    private boolean effectDone;

    public AngerPoint() {
        super(  "Anger Point",                                                                                            //name, 
                "The Pokemon is angered when it takes a critical hit, and that maxes its Attack stat.");                  //description
        this.effectDone = false;
    }

    @Override
    public boolean checkMoveCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(!this.effectDone){
            if(target.isAttacking && target.crit > 1){
                this.effectDone = true;
                return true;
            }
        }
        else{
            this.effectDone = false;
        }
        return false;

    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
        user.setAlterationAtk(+12, true);
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return BattleLogManager.ACTIVATES;
    }

}
