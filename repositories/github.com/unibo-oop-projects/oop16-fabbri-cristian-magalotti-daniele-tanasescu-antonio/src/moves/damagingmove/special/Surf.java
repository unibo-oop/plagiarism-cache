package moves.damagingmove.special;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Water;

public class Surf extends SpecialDamagingMove{

    public Surf() {
        super(  "Surf",                                                                                                //name
                "The user attacks everything around it by swamping its surroundings with a giant wave.",               //description
                90,                                                                                                    //base power
                new Water(),                                                                                           //type
                1,                                                                                                     //accuracy
                critRange1,                                                                                            //crit range 
                15,                                                                                                    //PP
                0);                                                                                                    //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
