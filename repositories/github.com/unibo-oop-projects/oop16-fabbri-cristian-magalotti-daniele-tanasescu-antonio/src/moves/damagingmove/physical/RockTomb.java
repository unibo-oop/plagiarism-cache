package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Rock;

public class RockTomb extends PhysicalDamagingMove{

    public RockTomb() {
        super(  "Rock Tomb",                                                                                                         //name
                "Boulders are hurled at the target. This also lowers the target's Speed stat by preventing its movement.",           //description
                60,                                                                                                                  //base power
                new Rock(),                                                                                                          //type
                0.95,                                                                                                                //accuracy
                critRange1,                                                                                                          //crit range 
                15,                                                                                                                  //PP
                0);                                                                                                                  //Priority
        this.sideEffect = true;
        this.setMakingContact(false);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        target.setAlterationSpe(-1, false);
        
    }

}
