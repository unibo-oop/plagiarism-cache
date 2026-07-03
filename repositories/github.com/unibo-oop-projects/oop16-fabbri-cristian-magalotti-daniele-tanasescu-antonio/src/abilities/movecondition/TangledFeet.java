package abilities.movecondition;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import managers.BattleLogManager;
import pokemon.Pokemon;
import status_condition.volatile_status.Confusion;

public class TangledFeet extends MoveConditionAbility{
    
    private boolean effectDone;
    
    public TangledFeet() {
        super(  "Tangled Feet",                                                               //name
                "Raises evasion if the Pokémon is confused.");                                //description
    }
    
    @Override
    public void setNextCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(checkMoveCondition(user, target, battleArena) && !effectDone){
            this.setIsActivable(true);
        }
        else{
            this.setIsActivable(false);
            if(this.effectDone){
                this.effectDone = false; 
                this.changeElusion(user, 1/1.5);                                               //(-50%)
            }
        }
    }

    @Override
    public boolean checkMoveCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(target.isAttacking){
            Confusion confusion = new Confusion();
            if(confusion.isContained(user.volatileStatusConditions)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(!this.effectDone){
            BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
            this.changeElusion(user, 1.5);                                                     //+ 50%
        }
        
    }
    
    private void changeElusion(Pokemon user, double change){
        user.setOtherModifierFactorElusion(user.getOtherModifierFactorElusion()*change);
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return BattleLogManager.ACTIVATES;
    }

}
