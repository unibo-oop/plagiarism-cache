package abilities.otherconditions;

import battle_arena.BattleArena;
import pokemon.Pokemon;

public class Scrappy extends OtherConditionsAbility{

    //it will be called by moves themselves
    public Scrappy() {
        super(  "Scrappy",                                                                                            //name, 
                "The Pokémon can hit Ghost-type Pokémon with Normal- and Fighting-type moves.");                      //description
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
