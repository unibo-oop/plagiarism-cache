package abilities.weathercondition;

import battle_arena.BattleArena;
import battle_arena.weather.Sandstorm;
import pokemon.Pokemon;

public class SandVeil extends WeatherConditionAbility{

    public SandVeil() {
        super(  "Sand Veil",                                                                                          //name, 
                "Boosts the Pokemon's evasion in a sandstorm.",                                                       //description
                new Sandstorm(5));                                                                                    //weatherCondition
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        //no message needed
        target.setOtherModifierFactorAccuracy(target.getOtherModifierFactorAccuracy()*0.8);                          /*the accuracy of any move used against a Pokemon 
                                                                                                                     with this Ability is modified by a factor of 4/5*/       
    }
    
    @Override
    public void exitingAbility(Pokemon user, Pokemon target, BattleArena battleArena){
        target.setOtherModifierFactorAccuracy(target.getOtherModifierFactorAccuracy()*1.25);                          //1/0.8 = 1.25
        super.exitingAbility(user, target, battleArena);
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        return null;
    }

}
