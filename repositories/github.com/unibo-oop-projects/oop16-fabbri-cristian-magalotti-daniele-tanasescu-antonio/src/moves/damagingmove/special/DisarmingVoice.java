package moves.damagingmove.special;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Fairy;

public class DisarmingVoice extends SpecialDamagingMove{

    public DisarmingVoice() {
        super(  "Disarming Voice",                                                                             //name
                "Letting out a charming cry, the user does emotional damage to opposing Pokémon."
                + "This attack never misses.",              												   //description              
                40,                                                                                            //base power
                new Fairy(),                                                                                   //type
                999,                                                                                           //accuracy
                critRange1,                                                                                    //crit range 
                15,                                                                                            //PP
                0);                                                                                            //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
    }
 
}
