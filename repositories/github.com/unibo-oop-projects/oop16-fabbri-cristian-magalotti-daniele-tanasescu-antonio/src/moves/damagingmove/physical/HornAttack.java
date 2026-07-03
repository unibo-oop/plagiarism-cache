package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class HornAttack extends PhysicalDamagingMove{

    public HornAttack() {
        super(  "Horn Attack",                                                                                          //name
                "The target is jabbed with a sharply pointed horn to inflict damage.",                        
                70,                                                                                                     //base power
                new Normal(),                                                                                           //type
                1,                                                                                                      //accuracy
                critRange1,                                                                                             //crit range 
                15,                                                                                                     //PP
                0);                                                                                                     //Priority
        this.sideEffect = true;
    }

	@Override
	public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
		// TODO Auto-generated method stub
		
	}
}