package moves.status;

import java.util.Random;

import battle_arena.BattleArena;
import pokemon.Pokemon;
import types.Ghost;

public class Spite extends StatusMove{

    public Spite() {
        super(  "Spite",                                                                                                         //name
                "The user unleashes its grudge on the move last used by the target by cutting 4 PP from it.",                    //description
                new Ghost(),                                                                                                     //type
                999,                                                                                                             //accuracy
                10,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(target.lastMoveUsed != null){
            Random random = new Random();
            int spiteNumber = random.nextInt(5) + 1;            //1-5
            target.lastMoveUsed.decrementActualPP(spiteNumber);
            //messaggio
        }
        
    }

}
