package abilities.endofturnconditionability;

import abilities.Ability;
import battle_arena.BattleArena;
import pokemon.Pokemon;

public abstract class EndOfTurnConditionAbility extends Ability{
    
    private boolean boostDone;

    public EndOfTurnConditionAbility(String name, String description) {
        super(name, description);
        this.boostDone = false;
    }
    
    @Override
    public void checkForActivation(Pokemon user, Pokemon target, BattleArena battleArena){
        this.setNextCondition(user, target, battleArena);
        if(this.getIsActivable() && !this.boostDone){
            this.activateAbility(user, target, battleArena);
            this.boostDone = true;
        }
        else{
            this.boostDone = false;
        }
    }
    
    @Override
    public void setNextCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(battleArena.endOfTurn){
            this.setIsActivable(true);
        }
        else{
            this.setIsActivable(false);
        }
        
    }

    @Override
    public void abilityStartCondition() {
        this.setIsActivable(false);
        
    }

}
