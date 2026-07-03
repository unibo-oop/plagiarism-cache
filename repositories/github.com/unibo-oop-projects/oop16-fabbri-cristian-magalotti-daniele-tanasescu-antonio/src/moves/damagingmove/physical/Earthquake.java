package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Ground;

public class Earthquake extends PhysicalDamagingMove{

    public Earthquake() {
        super(  "Earthquake",                                                                                           //name
                "The user sets off a goddamn earthquake that strikes every Pokemon around it.",                         //description
                100,                                                                                                    //base power
                new Ground(),                                                                                           //type
                1,                                                                                                      //accuracy
                critRange1,                                                                                             //crit range 
                10,                                                                                                     //PP
                0);                                                                                                     //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
