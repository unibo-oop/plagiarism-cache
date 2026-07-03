package abilities.weathercondition;

import battle_arena.BattleArena;
import battle_arena.weather.Hail;
import pokemon.Pokemon;

public class SnowCloak extends WeatherConditionAbility{

    private boolean boostDone;
    
    public SnowCloak() {
        super(  "Snow Cloak",                                                                                         //name, 
                "Boosts evasion in a hailstorm. ",                                                                    //description
                new Hail(5));                                                                                         //weatherCondition
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(!this.boostDone){
            user.setOtherModifierFactorElusion(user.getOtherModifierFactorAccuracy()*1.25);                            //+ 25%
            this.boostDone = true;
        } 
    }
    
    @Override
    public void exitingAbility(Pokemon user, Pokemon target, BattleArena battleArena){
        user.setOtherModifierFactorElusion(user.getOtherModifierFactorAccuracy()/1.25);                                //- 25%
        this.boostDone = false;
        super.exitingAbility(user, target, battleArena);
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        return null;
    }

}
