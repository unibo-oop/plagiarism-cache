package abilities.firstturn;

import battle_arena.BattleArena;
import moves.Move;
import moves.status.StatusMove;
import pokemon.Pokemon;

public class Prankster extends FirstTurnAbility{

    public Prankster() {
        super(  "Prankster",                                                                                               //name, 
                "Gives priority to a status move. ");                                                                      //description
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        for(Move move : user.getAllMoves()){
            if(move instanceof StatusMove){
                move.setPriority(move.getPriority()+1);
            }
        }
        //no message needed
    }

    @Override
    public void exitingAbility(Pokemon user, Pokemon target, BattleArena battleArena){
        for(Move move : user.getAllMoves()){
            if(move instanceof StatusMove){
                move.setPriority(move.getPriority()-1);
            }
        }
        super.exitingAbility(user, target, battleArena);
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return null;
    }
}
