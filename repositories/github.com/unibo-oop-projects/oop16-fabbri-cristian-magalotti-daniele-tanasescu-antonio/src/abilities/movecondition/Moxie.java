package abilities.movecondition;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import managers.BattleLogManager;
import pokemon.Pokemon;

public class Moxie extends MoveConditionAbility{

    private boolean effectDone;

    public Moxie() {
        super(  "Moxie",                                                                                                //name, 
                "The Pokemon shows moxie, and that boosts the Attack stat after knocking out any Pokemon");             //description
        this.effectDone = false;
    }

    @Override
    public boolean checkMoveCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(user.isAttacking && !effectDone){
            return true;
        }
        else{
            this.effectDone = false;
            return false;
        }
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(target.isFainted()){
            BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
            user.setAlterationAtk(+1, true);
            this.effectDone = true;
        }

    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return BattleLogManager.ACTIVATES;
    }

}
