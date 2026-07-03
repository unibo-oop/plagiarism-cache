package moves.damagingmove.physical.multistrike.twotofive;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Grass;

public class BulletSeed extends TwoToFiveMultiStrikePhysicalDamagingMove{

    public BulletSeed() {
        super(  "Bullet Seed",                                                                          //name
                "The user forcefully shoots seeds at the target two to five times in a row.",           //description
                25,                                                                                     //base power
                new Grass(),                                                                            //type
                1,                                                                                      //accuracy
                critRange1,                                                                             //crit range 
                30,                                                                                     //PP
                0);                                                                                     //Priority
        this.setMakingContact(false);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
