package moves.damagingmove.special;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class HyperVoice extends SpecialDamagingMove{

    public HyperVoice() {
        super(  "Hyper Voice",                                                                                                //name
                "The user lets loose a horribly echoing shout with the power to inflict damage.",                             //description
                90,                                                                                                           //base power
                new Normal(),                                                                                                 //type
                1,                                                                                                            //accuracy
                critRange1,                                                                                                   //crit range 
                10,                                                                                                           //PP
                0);                                                                                                           //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
