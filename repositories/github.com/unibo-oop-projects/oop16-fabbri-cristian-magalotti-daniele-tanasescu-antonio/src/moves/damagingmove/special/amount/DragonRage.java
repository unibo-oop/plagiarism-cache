package moves.damagingmove.special.amount;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Dragon;


public class DragonRage extends AmountSpecialDamagingMove{

    public DragonRage() {
        super(  "Dragon Rage",                                                                                  		 //name                                                                                                                                                                                                                    
                "This attack hits the target with a shock wave of pure rage. This attack always inflicts 40 HP damage.", //description
                new Dragon(),                                                                                   		 //type
                1,                                                                                           		     //accuracy
                10,                                                                                            	     	 //PP
                0,                                                                                             			 //priority 
                40);                                                                                           			 //amount damage
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
