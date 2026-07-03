package moves.damagingmove.physical;

import types.Normal;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import status_condition.volatile_status.Flinch;

public class HyperFang extends PhysicalDamagingMove {
	
	public HyperFang(){
		super(	"Hyper Fang",
				"The user bites hard on the target with its sharp front fangs.\n"
				+ "This may also make the target flinch.",
				80,
				new Normal(),
				0.9,
				critRange1,
				15,
				0);
		this.sideEffect = true;
	}
	
	@Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Random random = new Random();
        if(random.nextDouble() < 0.3){
            new Flinch().addVolatile(target, target.volatileStatusConditions);
        }
        
    }

}