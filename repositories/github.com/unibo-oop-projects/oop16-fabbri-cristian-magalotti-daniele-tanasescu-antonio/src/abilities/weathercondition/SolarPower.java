package abilities.weathercondition;

import battle_arena.BattleArena;
import battle_arena.weather.HarshSunlight;
import pokemon.Pokemon;

public class SolarPower extends WeatherConditionAbility {

    public SolarPower(){	
        super(	"Solar Power",
                "Boosts the Sp. Atk stat in sunny weather,\n"
                 + "but HP decreases every turn.",
                 new HarshSunlight(5));
    }
    // TODO Auto-generated constructor stub

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        // no effect needed
        user.setOtherModifierFactorSpA(user.getOtherModifierFactorSpA()*1.5);                   //+ 50 %
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        return null;
    }
}


