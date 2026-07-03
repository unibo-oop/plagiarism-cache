package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Water;

public class AquaJet extends PhysicalDamagingMove{

    public AquaJet() {
        super(  "Aqua Tail",                                                                                                    //name
                "The user attacks the target while shouting in a startling fashion",                                            //description                                                           
                40,                                                                                                             //base power
                new Water(),                                                                                                    //type
                1,                                                                                                              //accuracy
                critRange1,                                                                                                     //crit range 
                20,                                                                                                             //PP
                +1);                                                                                                            //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
