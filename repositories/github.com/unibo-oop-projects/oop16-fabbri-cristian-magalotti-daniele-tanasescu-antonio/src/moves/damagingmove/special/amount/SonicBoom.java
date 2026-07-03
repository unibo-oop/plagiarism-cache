package moves.damagingmove.special.amount;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;


public class SonicBoom extends AmountSpecialDamagingMove{

    public SonicBoom() {
        super(  "Sonic Boom",                                                                                   //name                                                                                                                                                                                                                    
                "The target is hit with a destructive shock wave that always inflicts 20 HP damage",            //description
                new Normal(),                                                                                   //type
                0.9,                                                                                            //accuracy
                20,                                                                                             //PP
                0,                                                                                              //priority 
                20);                                                                                            //amount damage
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
