package abilities.firstturn;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import managers.BattleLogManager;
import moves.Move;
import pokemon.Pokemon;

public class CompoundEyes extends FirstTurnAbility{

    public CompoundEyes() {
        super(  "Compound Eyes",                                                                                          //name, 
                "The Pokemon's compound eyes boost its accuracy.");                                                       //description
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        for(Move move : user.getAllMoves()){
            move.setMoveAccuracy(1.3);                                                                                 //+30%
        }
        //no message needed      
    }
    
    @Override
    public void exitingAbility(Pokemon user, Pokemon target, BattleArena battleArena){
        for(Move move : target.getAllMoves()){
            move.setMoveAccuracy(0.7);                                                                                //-30%
        }
        super.exitingAbility(user, target, battleArena);
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return null;
    }

}
