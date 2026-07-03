package abilities.firstturn;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import managers.BattleLogManager;
import moves.Move;
import moves.damagingmove.DamagingMove;
import pokemon.Pokemon;
import types.Flying;
import types.Normal;

public class Aerilate extends FirstTurnAbility{

    public Aerilate() {
        super(  "Aerilate",                                                                                               //name, 
                "Normal-type moves become Flying-type moves. The power of those moves is boosted a little.");             //description
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        for(Move move : user.getAllMoves()){
            if(move.getMoveType().equals(new Normal())){
                move.setNewType(new Flying());
                move.typeHasChanged = true;
                if(move instanceof DamagingMove){
                    ((DamagingMove) move).setBasePower(1.2);                                                              //+20%
                }
            }
        }
        //no message needed
    }

    @Override
    public void exitingAbility(Pokemon user, Pokemon target, BattleArena battleArena){
        for(Move move : target.getAllMoves()){
            if(move.typeHasChanged){
                move.setNewType(new Normal());
                move.typeHasChanged = false;
                if(move instanceof DamagingMove){
                    ((DamagingMove) move).setBasePower(0.8);                                                              //-20%
                }
            }

        }
        super.exitingAbility(user, target, battleArena);
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        return null;
    }

}