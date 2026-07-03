package moves.status;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import pokemon.Pokemon;
import types.Normal;

public class Splash extends StatusMove{

    public Splash() {
        super(  "Splash",                                                                                              		//name
                "A frenetic dance to uplift the fighting spirit." +
                "(The user just flops and splashes around to no effect at all...)",                				//description
                new Normal(),                                                                                                   //type
                1,                                                                                                              //accuracy
                20,                                                                                                             //PP                                                                                                                     
                0);                                                                                                             //priority  
        this.setSelfEffect(true);
        
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        BattleMenuController.battleLogManager.setNothingHappensMessage();
    }
}