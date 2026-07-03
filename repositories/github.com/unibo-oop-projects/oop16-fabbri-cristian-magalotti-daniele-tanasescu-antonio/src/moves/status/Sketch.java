package moves.status;

import battle_arena.BattleArena;
import moves.Move;
import pokemon.Pokemon;
import types.Normal;

public class Sketch extends StatusMove{

    public Sketch() {
        super(  "Sketch",                                                                                                        //name
                "It enables the user to permanently learn the move last used by the target. Once used, Sketch disappears.",      //description
                new Normal(),                                                                                                    //type
                999,                                                                                                             //accuracy
                999,                                                                                                               //PP                                                                                                                     
                0);                                                                                                              //priority  
        this.setSelfEffect(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        for(Move move : user.getAllMoves()){
            if (((Object)this).equals(move)){                   //it has to be THAT move, non only a "Sketch" move...
                if(target.lastMoveUsed != null){
                    move = target.lastMoveUsed;
                    //messaggi
                }
                else{
                    //messaggio
                }
            }
        }
        
    }
    
    //like it was 1
    @Override
    public int getActualPP(){
        return 1;
    }
    
    //like it was 1
    @Override
    public int getMaxPP(){
        return 1;
    }

}
