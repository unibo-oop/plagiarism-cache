package abilities.otherconditions;

import abilities.Ability;
import battle_arena.BattleArena;
import pokemon.Pokemon;

public abstract class OtherConditionsAbility extends Ability{

    public OtherConditionsAbility(String name, String description) {
        super(name, description);
    }

    @Override
    public void setNextCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
        this.setIsActivable(false);                                                             //vengono chiamate su necessità
        
    }

    @Override
    public void abilityStartCondition() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void checkForActivation(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
