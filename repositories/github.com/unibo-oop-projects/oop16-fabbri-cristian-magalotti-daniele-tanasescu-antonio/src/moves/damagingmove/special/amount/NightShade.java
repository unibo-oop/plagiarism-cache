package moves.damagingmove.special.amount;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Ghost;

public class NightShade extends AmountSpecialDamagingMove{

    public NightShade(Pokemon user) {
        super(  "Night Shade",                                                                                                  //name                                                                                                                                                                                                                    
                "The user makes the target see a frightening mirage. It inflicts damage equal to the user's level.",            //description
                new Ghost(),                                                                                                    //type
                1,                                                                                                              //accuracy
                15,                                                                                                             //PP
                0,                                                                                                              //priority 
                user.getLevel());                                                                                               //amount damage
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
