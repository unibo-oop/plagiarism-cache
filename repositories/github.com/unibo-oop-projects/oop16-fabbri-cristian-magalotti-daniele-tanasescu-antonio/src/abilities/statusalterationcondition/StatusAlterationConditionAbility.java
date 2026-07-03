package abilities.statusalterationcondition;

import abilities.Ability;
import battle_arena.BattleArena;
import pokemon.Pokemon;

public abstract class StatusAlterationConditionAbility extends Ability{

    private boolean effectDone;
    
    public StatusAlterationConditionAbility(String name, String description) {
        super(name, description);
        this.abilityStartCondition();
    }

    @Override
    public void setNextCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
    	if(!effectDone){
            this.checkStatusCondition(user);
        }
        else{
            this.setIsActivable(false);
        }
    }

    @Override
    public void abilityStartCondition() {
        this.setIsActivable(false);
        this.effectDone = false;
    }

    @Override
    public void checkForActivation(Pokemon user, Pokemon target, BattleArena battleArena) {
        this.setNextCondition(user, target, battleArena);        
        if(this.getIsActivable()){
            this.activateAbility(user, target, battleArena);
            this.effectDone = false;
        }   
    }
    
    @Override                                   
    public void exitingAbility(Pokemon user, Pokemon target, BattleArena battleArena){
        this.effectDone = false;
        super.exitingAbility(user, target, battleArena);
    }
    
    public abstract void checkStatusCondition(Pokemon user);                                    

}