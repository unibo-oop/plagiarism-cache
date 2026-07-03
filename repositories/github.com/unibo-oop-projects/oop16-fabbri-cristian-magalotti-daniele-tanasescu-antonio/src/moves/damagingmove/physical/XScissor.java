package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Bug;

public class XScissor extends PhysicalDamagingMove{

    public XScissor() {
        super(  "X-Scissor",                                                                                            //name
                "The user slashes at the target by crossing its scythes or claws as if they were a pair of scissors.",  //description                     
                80,                                                                                                     //base power
                new Bug(),                                                                                              //type
                1,                                                                                                      //accuracy
                critRange1,                                                                                             //crit range 
                15,                                                                                                     //PP
                0);                                                                                                     //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
