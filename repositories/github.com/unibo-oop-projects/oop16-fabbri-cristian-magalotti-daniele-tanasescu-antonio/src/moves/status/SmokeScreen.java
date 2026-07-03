package moves.status;

import abilities.statisticsalterationondemand.*;
import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class SmokeScreen extends StatusMove{

    public SmokeScreen() {
        super(  "Smoke Screen",                                                                                        	  //name
                "The user releases an obscuring cloud of smoke or ink.\n"
                + "This lowers the target's accuracy.",   	          													  //description
                new Normal(),                                                                                             //type
                1,                                                                                                        //accuracy
                20,                                                                                                       //PP                                                                                                                     
                0);                                                                                                       //priority       
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        target.setAlterationAccuracy(-1, false);                        
    }
}