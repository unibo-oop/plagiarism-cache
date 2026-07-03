package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Water;

public class AquaTail extends PhysicalDamagingMove{

    public AquaTail() {
        super(  "Aqua Tail",                                                                                                    //name
                "The user attacks the target while shouting in a startling fashion",                                            //description                                                           
                90,                                                                                                             //base power
                new Water(),                                                                                                    //type
                0.9,                                                                                                            //accuracy
                critRange1,                                                                                                     //crit range 
                10,                                                                                                             //PP
                0);                                                                                                             //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
