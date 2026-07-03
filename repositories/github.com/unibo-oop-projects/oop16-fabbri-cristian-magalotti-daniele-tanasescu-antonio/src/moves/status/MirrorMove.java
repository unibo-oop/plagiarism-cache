package moves.status;

import battle_arena.BattleArena;
import moves.Move;
import moves.damagingmove.DamagingMove;
import moves.damagingmove.physical.multistrike.MultiStrikePhysicalDamagingMove;
import pokemon.Pokemon;
import types.Flying;

public class MirrorMove extends StatusMove{

    public MirrorMove() {
        super(  "Mirror Move",                                                                                                   //name
                "The user counters the target by mimicking the target's last move.",                                             //description
                new Flying(),                                                                                                    //type
                999,                                                                                                             //accuracy
                20,                                                                                                              //PP                                                                                                                     
                0);                                                                                                              //priority  
        this.setSelfEffect(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(target.lastMoveUsed != null){
            Move move = target.lastMoveUsed;
            //this will be moved to the controller
            if(move instanceof StatusMove){
                ((StatusMove) move).getEffect(user, target, battleArena);
                //message
            }
            else if(move instanceof DamagingMove){
                    if(move instanceof MultiStrikePhysicalDamagingMove){
                        ((MultiStrikePhysicalDamagingMove) move).multiStrike(user, target, battleArena);
                        //message
                    }
                    else{
                        ((DamagingMove) move).getDamage(user, target, battleArena);
                        //message
                    }
            }
        }
        
    }

}
