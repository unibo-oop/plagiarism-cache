package moves.damagingmove.physical.selfko;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class Explosion extends SelfKOPhysicalDamagingMove{

    public Explosion() {
        super(  "Explosion",                                                                                            //name
                "The user attacks everything around it by causing a tremendous explosion.\n"+                           //description
                "The user faints upon using this move.",                                            
                250,                                                                                                    //base power
                new Normal(),                                                                                           //type
                1,                                                                                                      //accuracy
                critRange1,                                                                                             //crit range 
                5,                                                                                                      //PP
                0);                                                                                                     //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
