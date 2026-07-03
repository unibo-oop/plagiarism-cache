package moves.status;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Fairy;

public class Charm extends StatusMove{

    public Charm() {
        super(  "Charm",                                                                                                         //name
                "The user gazes at the target rather charmingly, making it less wary.\n"+                                        //description
                "This harshly lowers its Attack stat.",                                              
                new Fairy(),                                                                                                     //type
                1,                                                                                                               //accuracy
                20,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        target.setAlterationAtk(-2, false);
        
    }

}
