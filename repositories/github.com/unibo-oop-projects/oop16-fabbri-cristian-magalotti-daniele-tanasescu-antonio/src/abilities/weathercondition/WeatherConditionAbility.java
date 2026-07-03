package abilities.weathercondition;

import abilities.Ability;
import battle_arena.BattleArena;
import battle_arena.weather.Weather;
import pokemon.Pokemon;

public abstract class WeatherConditionAbility extends Ability{

    protected final Weather weatherCondition;

    public boolean effectDone;

    public WeatherConditionAbility(String name, String description, Weather weatherCondition) {
        super(name, description);
        this.abilityStartCondition();
        this.weatherCondition = weatherCondition;
    }

    @Override
    public void setNextCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(battleArena.weather != null){
            if(battleArena.weather.equals(this.weatherCondition)){
                this.setIsActivable(true);
            }
            else{
                this.setIsActivable(false);
                user.changeAbility(user, user, battleArena, this);                  //perde i boost
            }
        }  
        else{
            this.setIsActivable(false);
            if(effectDone){
                user.changeAbility(user, user, battleArena, this);                  //perde i boost
            }
        }

    }

    @Override
    public void abilityStartCondition() {
        this.setIsActivable(false);

    }

    @Override
    public void checkForActivation(Pokemon user, Pokemon target, BattleArena battleArena) {
        this.setNextCondition(user, target, battleArena);
        if(this.getIsActivable() && !this.effectDone){
            this.activateAbility(user, target, battleArena);
            this.effectDone = true;
        }
        this.setNextCondition(user, target, battleArena);

    }

}
