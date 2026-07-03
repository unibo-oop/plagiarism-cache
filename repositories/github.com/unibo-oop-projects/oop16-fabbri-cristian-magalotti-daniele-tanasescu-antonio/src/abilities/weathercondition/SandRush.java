package abilities.weathercondition;

import battle_arena.BattleArena;
import battle_arena.weather.Sandstorm;
import pokemon.Pokemon;

public class SandRush extends WeatherConditionAbility{

    public SandRush() {
        super(  "Sand Rush",                                                                                          //name, 
                "Boosts the Pokemon's Speed stat in a sandstorm.",                                                    //description
                new Sandstorm(5));                                                                                    //weatherCondition
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
