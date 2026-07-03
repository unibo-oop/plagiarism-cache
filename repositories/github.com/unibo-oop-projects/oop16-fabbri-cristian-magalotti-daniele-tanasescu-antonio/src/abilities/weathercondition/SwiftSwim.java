package abilities.weathercondition;

import battle_arena.BattleArena;
import battle_arena.weather.Rain;
import pokemon.Pokemon;

public class SwiftSwim extends WeatherConditionAbility{

    public SwiftSwim() {
        super(  "Swift Swim",                                                         	 //name 
                "Boosts the Pokemon's Speed stat in the rain.",                    		 //description
                new Rain(5));                                                 		     //weatherCondition
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
