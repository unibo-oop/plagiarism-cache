package moves.status;

import battle_arena.BattleArena;
import battle_arena.weather.HarshSunlight;
import main.view.BattleMenuController;
import pokemon.Pokemon;
import types.Fire;

public class SunnyDay extends StatusMove{

    public SunnyDay() {
        super(  "Sunny Day",                                                                                                     //name
                "The user intensifies the sun for five turns, powering up Fire-type moves.\n"+                                   //description
                "It lowers the power of Water-type moves.",                                             
                new Fire(),                                                                                                     //type
                999,                                                                                                            //accuracy
                5,                                                                                                              //PP                                                                                                                     
                0);                                                                                                             //priority  
        this.setSelfEffect(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        HarshSunlight harshSunlight = new HarshSunlight(5);
        if(battleArena.weather != null && battleArena.weather.equals(harshSunlight)){
            BattleMenuController.battleLogManager.setMoveFailedMassage();
        }
        else{
            harshSunlight.setWeather(user, target, battleArena);
        }

    }

}
