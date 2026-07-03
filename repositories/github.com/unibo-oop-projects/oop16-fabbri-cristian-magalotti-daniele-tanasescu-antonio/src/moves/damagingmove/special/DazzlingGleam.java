package moves.damagingmove.special;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Fairy;

public class DazzlingGleam extends SpecialDamagingMove{

    public DazzlingGleam() {
        super(  "Dazzling Gleam",                                                                                //name
                "The user damages opposing Pokémon by emitting a powerful flash. ",                              //description
                80,                                                                                              //base power
                new Fairy(),                                                                                     //type
                1,                                                                                               //accuracy
                critRange1,                                                                                      //crit range 
                15,                                                                                              //PP
                0);                                                                                              //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
