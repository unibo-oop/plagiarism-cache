package moves.status;

import battle_arena.BattleArena;
import battle_arena.weather.Rain;
import main.view.BattleMenuController;
import pokemon.Pokemon;
import types.Water;

public class RainDance extends StatusMove{

    public RainDance() {
        super(  "Rain Dance",                                                                                                    //name
                "The user summons a heavy rain that falls for five turns, powering up Water-type moves.\n"+                      //description
                "It lowers the power of Fire-type moves",
                new Water(),                                                                                                     //type
                999,                                                                                                             //accuracy
                5,                                                                                                               //PP                                                                                                                     
                0);                                                                                                              //priority  
        this.setSelfEffect(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        Rain rain = new Rain(5);
        if(battleArena.weather != null && battleArena.weather.equals(rain)){
            BattleMenuController.battleLogManager.setMoveFailedMassage();
        }
        else{
            rain.setWeather(user, target, battleArena);
        }
    }
}
