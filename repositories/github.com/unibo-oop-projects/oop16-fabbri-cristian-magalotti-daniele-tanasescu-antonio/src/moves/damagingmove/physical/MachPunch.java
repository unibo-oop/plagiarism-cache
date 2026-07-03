package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Fight;

public class MachPunch extends PhysicalDamagingMove{

    public MachPunch() {
        super(  "Mach Punch",                                                                                   //name
                "The user throws a punch at blinding speed. This move always goes first.",                      //description
                50,                                                                                             //base power
                new Fight(),                                                                                    //type
                1,                                                                                              //accuracy
                critRange1,                                                                                     //crit range 
                25,                                                                                             //PP
                +1);                                                                                            //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
