package abilities.otherconditions;

import battle_arena.BattleArena;
import pokemon.Pokemon;

public class InnerFocus extends OtherConditionsAbility{

    public InnerFocus() {
        super(  "Inner Focus",                                                                                        //name, 
                "The Pokemon's intensely focused, and that protects the Pokemon from flinching.");                    //description
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return null;
    }

}
