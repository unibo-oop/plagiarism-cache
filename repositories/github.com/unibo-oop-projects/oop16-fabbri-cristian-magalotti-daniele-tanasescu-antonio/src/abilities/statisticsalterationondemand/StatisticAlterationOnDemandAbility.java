package abilities.statisticsalterationondemand;

import abilities.Ability;
import battle_arena.BattleArena;
import pokemon.Pokemon;

public abstract class StatisticAlterationOnDemandAbility extends Ability{

    public StatisticAlterationOnDemandAbility(String name, String description) {
        super(name, description);
        this.abilityStartCondition();
    }

    @Override
    public void setNextCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
        this.setIsActivable(false);
        
    }

    @Override
    public void abilityStartCondition() {
        this.setIsActivable(false); 
    }

    //not needed
    @Override
    public void checkForActivation(Pokemon user, Pokemon target, BattleArena battleArena) {       
    }

}
