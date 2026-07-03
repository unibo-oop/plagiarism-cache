package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Psychic;

public class Meditate extends StatusMove{

    public Meditate() {
        super(  "Meditate",                                                                                        	          //name
                "The user meditates to awaken the power deep within its body and raise its Attack stat.",   	              //description
                new Psychic(),                                                                                                //type
                999,                                                                                                          //accuracy
                40,                                                                                                           //PP                                                                                                                     
                0);                                                                                                           //priority  
        this.setSelfEffect(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
    	user.setAlterationAtk(+1, true);
        
    }

}