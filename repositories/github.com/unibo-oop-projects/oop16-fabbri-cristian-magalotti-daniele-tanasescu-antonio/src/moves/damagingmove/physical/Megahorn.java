package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Bug;

public class Megahorn extends PhysicalDamagingMove{

    public Megahorn() {
        super(  "MegaHorn",                                                                                     //name
                "Using its tough and impressive horn, the user rams into the target with no letup.",            //description               
                120,                                                                                            //base power
                new Bug(),                                                                                      //type
                0.85,                                                                                           //accuracy
                critRange1,                                                                                     //crit range 
                10,                                                                                             //PP
                0);                                                                                             //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
