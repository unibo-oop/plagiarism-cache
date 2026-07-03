package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class Slash extends PhysicalDamagingMove{

    public Slash() {
        super(  "Slash",                                                                                             	//name
                "The target is attacked with a slash of claws or blades."
                + "Critical hits land more easily.",                                        						    //description
                70,                                                                                                     //base power
                new Normal(),                                                                                           //type
                1,                                                                                                      //accuracy
                critRange2,                                                                                             //crit range 
                20,                                                                                                     //PP
                0);                                                                                                     //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
