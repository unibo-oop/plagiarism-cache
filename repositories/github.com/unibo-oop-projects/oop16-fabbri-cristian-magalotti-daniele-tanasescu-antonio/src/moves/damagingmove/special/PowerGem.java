package moves.damagingmove.special;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Rock;

public class PowerGem extends SpecialDamagingMove{

    public PowerGem() {
        super(  "Power Gem",                                                                                   //name
                "The user attacks with a ray of light that sparkles as if it were made of gemstones.",         //description
                80,                                                                      					   //base power
                new Rock(),                                                                                    //type
                1,                                                                                             //accuracy
                critRange1,                                                                                    //crit range 
                20,                                                                                            //PP
                0);                                                                                            //Priority
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        
        
    }

}
