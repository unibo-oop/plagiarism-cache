package moves.damagingmove.physical;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Grass;

public class SeedBomb extends PhysicalDamagingMove{

    public SeedBomb() {
        super(  "Seed Bomb",                                                   							//name
                "The user slams a barrage of hard-shelled seeds down on the target from above.",       	//description         
                80,                                                             						//base power
                new Grass(),                                                    						//type
                1,                                                              						//accuracy
                critRange1,                                                    							//crit range 
                15,                                                             						//PP
                0);                                                             						//Priority
    }

	@Override
	public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
		// TODO Auto-generated method stub
		
	}
}