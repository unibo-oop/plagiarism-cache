package moves.damagingmove.physical.onlyfirstturn;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.volatile_status.Flinch;
import types.Normal;

public class FakeOut extends OnlyFirstTurnPhysicalDamagingMove{

    public FakeOut() {
        super(  "Fake Out",                                                     //name
                "This attack hits first and makes the target flinch.\n"+        //description
                "It only works the first turn the user is in battle.",                     
                40,                                                             //base power
                new Normal(),                                                   //type
                1,                                                              //accuracy
                critRange1,                                                     //crit range 
                10,                                                             //PP
                +3);                                                            //Priority
        this.sideEffect = true;
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        new Flinch().addVolatile(target, target.volatileStatusConditions);
        
    }

}
