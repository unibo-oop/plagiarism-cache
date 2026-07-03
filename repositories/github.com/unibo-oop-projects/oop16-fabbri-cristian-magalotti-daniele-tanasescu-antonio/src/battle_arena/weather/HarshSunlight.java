package battle_arena.weather;

import abilities.movecondition.DrySkin;
import abilities.weathercondition.SolarPower;
import moves.Move;
import moves.damagingmove.special.Hurricane;
import moves.damagingmove.special.Thunder;
import pokemon.Pokemon;
import types.Fire;
import types.Water;

/**
 * 
 * @author daniele
 *
 */

public class HarshSunlight extends Weather {
    
        private static final String SUNSTART = "The sunlight turned harsh!";
        private static final String SUNCONTINUE = "The sunlight is strong.";
        private static final String SUNEND = "The sunlight faded.";

	public HarshSunlight(int turns) {
		super(turns,                                                        //turns
		      true,                                                         //does modify move accuracy?
		      true,                                                         //does modify move power
		      false,                                                        //does modify pokemon stat
		      new Move[]{new Thunder(),                                     //moves that change accuracy under this
		                 new Hurricane()}, 
		      null,                                                         //types immune to dot
		      new Fire(),                                                   //power up type
		      new Water(),                                                  //power drop type
		      null,                                                         //type of Pokemon to alter stat
		      SUNSTART,                                                     //weatherStartMessage
		      SUNCONTINUE,                                                  //weatherDotMessage (no normal dot)
		      SUNEND);                                                      //weatherEndMessage                                                                                
	}

    @Override
    public void getDot(Pokemon pokemon) {
        if(pokemon.getAbility().equals(new DrySkin()) || pokemon.getAbility().equals(new SolarPower())){
            pokemon.takeDamage(pokemon.getMaxHp()/8, false);
        }        
    }

    @Override
    public void changeMoveAccuracy(Move move) {
        move.setMoveAccuracy(0.5);        
    }

    @Override
    public void alterPokemonStat(Pokemon pokemon) {
    
    }

}
