package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Water;

public class Withdraw extends StatusMove{

    public Withdraw() {
        super(  "Withdraw",                                                                                        	  //name
                "The user stiffens all the muscles in its body to raise its Defense stat.",   	         		  //description
                new Water(),                                                                                              //type
                999,                                                                                                      //accuracy
                40,                                                                                                       //PP                                                                                                                     
                0);                                                                                                       //priority  
        this.setSelfEffect(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        user.setAlterationDef(+1, true);

    }

}