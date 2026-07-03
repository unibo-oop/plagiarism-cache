package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class DoubleTeam extends StatusMove{

    public DoubleTeam() {
        super(  "Double Team",                                                                                        	  //name
                "By moving rapidly, the user makes illusory copies of itself to raise its evasiveness.",   	              //description
                new Normal(),                                                                                             //type
                999,                                                                                                        //accuracy
                15,                                                                                                       //PP                                                                                                                     
                0);                                                                                                       //priority  
        this.setSelfEffect(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
    	user.setAlterationElusion(+1, true);
        
    }

}