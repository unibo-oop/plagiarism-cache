package abilities.otherconditions;

import battle_arena.BattleArena;
import pokemon.Pokemon;

public class RockHead extends OtherConditionsAbility{

    public RockHead() {
        super(  "Rock Head",                                                                                          //name, 
                "Protects the Pokémon from recoil damage. ");                                                         //description
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub

    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        return null;
    }

}
