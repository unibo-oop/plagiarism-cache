package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Grass;

public class LeafBlade extends PhysicalDamagingMove{

    public LeafBlade() {
        super(  "Leaf Blade",                                                                            //name
                "The user handles a sharp leaf like a sword and attacks by cutting its target.\n"+       //description
                "Critical hits land more easily.",                    
                90,                                                                                      //base power
                new Grass(),                                                                             //type
                1,                                                                                       //accuracy
                critRange2,                                                                              //crit range 
                15,                                                                                      //PP
                0);                                                                                      //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
