package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Fight;

public class SkyUppercut extends PhysicalDamagingMove{

    public SkyUppercut() {
        super(  "Sky Uppercut",                                                                                 //name
                "The user attacks the target with an uppercut thrown skyward with force.",                      //description
                85,                                                                                             //base power
                new Fight(),                                                                                    //type
                0.9,                                                                                            //accuracy
                critRange1,                                                                                     //crit range 
                15,                                                                                             //PP
                0);                                                                                             //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
