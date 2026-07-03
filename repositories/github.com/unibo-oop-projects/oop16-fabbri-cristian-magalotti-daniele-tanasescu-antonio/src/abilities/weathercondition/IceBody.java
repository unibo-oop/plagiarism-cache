package abilities.weathercondition;

import battle_arena.BattleArena;
import battle_arena.weather.Hail;
import main.view.BattleMenuController;
import managers.BattleLogManager;
import pokemon.Pokemon;

public class IceBody extends WeatherConditionAbility{

    public IceBody() {
        super(  "Ice Body",                                                                //name 
                "The Pokemon gradually regains HP in a hailstorm.",                        //description
                new Hail(5));                                                          //weatherCondition
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(user.getHp() < user.getMaxHp()){
            BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
            user.takeDamage(-user.getMaxHp()/16, false);                                           //heals 1/16 of the HP
        }
        
    }
    
    //it only actives at the end of the turn!
    @Override
    public void setNextCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(battleArena.weather != null){
            if(battleArena.weather.equals(this.weatherCondition) && battleArena.endOfTurn){
                this.setIsActivable(true);
            }
            else{
                this.setIsActivable(false);
            }
        }
        else{
            this.setIsActivable(false);
        }
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return BattleLogManager.ACTIVATES;
    }

}
