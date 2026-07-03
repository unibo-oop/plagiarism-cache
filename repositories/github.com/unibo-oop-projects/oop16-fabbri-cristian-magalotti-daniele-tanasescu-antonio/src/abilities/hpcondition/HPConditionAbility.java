package abilities.hpcondition;

import abilities.Ability;
import battle_arena.BattleArena;
import pokemon.Pokemon;

public abstract class HPConditionAbility extends Ability {

    public final double hpBound;    

    protected boolean effectDone;

    public HPConditionAbility(String name, String description, double hpBound) {
        super(name, description);
        this.abilityStartCondition();
        this.hpBound = hpBound;
        this.effectDone = false;
    }

    @Override
    public void setNextCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
        if (this.hpBound*user.getMaxHp() <= user.getHp()){
            this.setIsActivable(true);
        }   
        else{
            this.setIsActivable(false);
            this.effectDone = false;         
        }
    }

    @Override
    public void abilityStartCondition() {
        this.setIsActivable(false);                   
    }

    @Override
    public void checkForActivation(Pokemon user, Pokemon target, BattleArena battleArena){
        this.setNextCondition(user, target, battleArena);        
        if(this.getIsActivable()){
            if(!this.effectDone){
                this.activateAbility(user, target, battleArena);
                this.effectDone = true;
            }
        }
        else{
            user.changeAbility(user, user, battleArena, this);
        }
    }
}