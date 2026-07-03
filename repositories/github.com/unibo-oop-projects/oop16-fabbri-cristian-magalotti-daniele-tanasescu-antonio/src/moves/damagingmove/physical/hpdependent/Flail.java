package moves.damagingmove.physical.hpdependent;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class Flail extends HPDependentPhysicalDamagingMove{

    public Flail() {
        super(  "Flail",                                                                                                //name
                "The user flails about aimlessly to attack.\n"+                                                         //description
                "The less HP the user has, the greater the move's power.",                       
                new Normal(),                                                                                           //type
                1,                                                                                                      //accuracy
                critRange1,                                                                                             //crit range 
                15,                                                                                                     //PP
                0,                                                                                                      //Priority
                new double[]{0.6875,0.3542,0.2083,0.1042,0.0417},                                                       //hp ranges (%)
                new int[]{20,40,80,100,150,150});                                                                       //hp-dep. damages
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
