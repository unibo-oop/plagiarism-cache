package moves.damagingmove.physical.multistrike.twotofive;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Ground;

public class BoneRush extends TwoToFiveMultiStrikePhysicalDamagingMove{

    public BoneRush() {
        super(  "Bone Rush",                                                                            //name
                "The user strikes the target with a hard bone two to five times in a row.",             //description
                25,                                                                                     //base power
                new Ground(),                                                                           //type
                0.90,                                                                                   //accuracy
                critRange1,                                                                             //crit range 
                10,                                                                                     //PP
                0);                                                                                     //Priority
        this.setMakingContact(false);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
