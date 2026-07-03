package abilities.neighbouringcondition;

import java.util.Arrays;

import abilities.Ability;
import battle_arena.BattleArena;
import pokemon.Pokemon;

public abstract class NeighbouringConditionAbility extends Ability{   

    private String[] neighbouringConditions;
    protected boolean effectDone;

    public NeighbouringConditionAbility(String name, String description, String[] abilities) {
        super(name, description);
        this.abilityStartCondition();
        this.neighbouringConditions =  Arrays.copyOf(abilities,abilities.length);
    }

    @Override
    public void setNextCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
        for(String ability : this.neighbouringConditions){
            if (target.getAbility().getName().equals(ability)){
                this.setIsActivable(true);
            }
            else{
                this.setIsActivable(false);
            }
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
        if(this.getIsActivable() && !effectDone){
            this.activateAbility(user, target, battleArena);
            this.effectDone = true;
        }
        else{
            this.effectDone = false;
        }
    }

}
