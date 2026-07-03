package moves.damagingmove.physical.hpdependent;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Fight;

public class Reversal extends HPDependentPhysicalDamagingMove{

    public Reversal() {
        super(  "Reversal",                                                                                             //name
                "An all-out attack that becomes more powerful the less HP the user has.",                               //description                       
                new Fight(),                                                                                            //type
                1,                                                                                                      //accuracy
                critRange1,                                                                                             //crit range 
                15,                                                                                                     //PP
                0,                                                                                                      //Priority
                new double[]{0.6875,0.3542,0.2083,0.1042,0.0417},                                                       //hp ranges (%)
                new int[]{20,40,80,100,150,200});                                                                       //hp-dep. damages
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
