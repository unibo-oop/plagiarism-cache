package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Steel;

public class BulletPunch extends PhysicalDamagingMove{

    public BulletPunch() {   
        super(  "Bullet Punch",                                                                                         //name
                "The user strikes the target with tough punches as fast as bullets."
                + "This move always goes first.", 																		//description
                40,                                                                                                     //base power
                new Steel(),                                                                                           //type
                1,                                                                                                      //accuracy
                critRange1,                                                                                             //crit range 
                30,                                                                                                     //PP
                1);                                                                                                     //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
