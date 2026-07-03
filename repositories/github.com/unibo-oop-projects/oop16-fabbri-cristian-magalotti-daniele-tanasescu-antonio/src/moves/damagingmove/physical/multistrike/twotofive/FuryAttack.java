package moves.damagingmove.physical.multistrike.twotofive;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class FuryAttack extends TwoToFiveMultiStrikePhysicalDamagingMove{

    public FuryAttack() {
        super(  "Fury Attack",                                                                            //name
                "The target is jabbed repeatedly with a horn or beak two to five times in a row.",        //description
                15,                                                                                       //base power
                new Normal(),                                                                             //type
                0.85,                                                                                     //accuracy
                critRange1,                                                                               //crit range 
                20,                                                                                       //PP
                0);                                                                                       //Priority       
        this.setMakingContact(false);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
