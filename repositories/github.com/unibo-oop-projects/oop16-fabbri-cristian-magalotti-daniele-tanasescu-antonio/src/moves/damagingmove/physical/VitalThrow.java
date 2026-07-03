package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Fight;

public class VitalThrow extends PhysicalDamagingMove{

    public VitalThrow() {
        super(  "Vital Throw",                                                                                  //name
                "The user attacks last. In return, this throw move never misses.",                              //description
                70,                                                                                             //base power
                new Fight(),                                                                                    //type
                999,                                                                                            //accuracy
                critRange1,                                                                                     //crit range 
                10,                                                                                             //PP
                -1);                                                                                            //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
