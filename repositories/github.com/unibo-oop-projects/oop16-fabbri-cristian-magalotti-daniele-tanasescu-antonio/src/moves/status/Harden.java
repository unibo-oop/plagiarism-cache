package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Normal;

public class Harden extends StatusMove{

    public Harden() {
        super(  "Harden",                                                                                        		  //name
                "The user stiffens all the muscles in its body to raise its Defense stat.",   	         			      //description
                new Normal(),                                                                                             //type
                999,                                                                                                        //accuracy
                30,                                                                                                       //PP                                                                                                                     
                0);                                                                                                       //priority       
        this.setSelfEffect(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
    	user.setAlterationDef(+1, true);
    }
}