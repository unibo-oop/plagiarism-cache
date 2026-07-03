package moves.damagingmove.physical.multistrike.twotofive;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Rock;

public class RockBlast extends TwoToFiveMultiStrikePhysicalDamagingMove{

    public RockBlast() {
        super(  "Rock Blast",                                                                           //name
                "The user strikes the target with a hard bone two to five times in a row.",             //description
                25,                                                                                     //base power
                new Rock(),                                                                             //type
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
