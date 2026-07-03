package abilities.weathercondition;

import battle_arena.BattleArena;
import battle_arena.weather.HarshSunlight;
import pokemon.Pokemon;

public class Chlorophyll extends WeatherConditionAbility{

    public Chlorophyll() {
        super(  "Chlorophyll",                                                          //name 
                "Boosts the Pokemon's Speed stat in sunshine.",                         //description
                new HarshSunlight(5));                                                  //weatherCondition
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        //no message needed
        user.setOtherModifierFactorSpe(user.getOtherModifierFactorSpe()*2);  
    }
    
    @Override
    public void exitingAbility(Pokemon user, Pokemon target, BattleArena battleArena){
        target.setOtherModifierFactorSpe(user.getOtherModifierFactorSpe()/2);
        super.exitingAbility(user, target, battleArena);
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        return null;
    }

}
