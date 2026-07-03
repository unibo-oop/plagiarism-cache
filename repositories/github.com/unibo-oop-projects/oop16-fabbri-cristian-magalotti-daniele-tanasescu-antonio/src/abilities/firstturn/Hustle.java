package abilities.firstturn;

import battle_arena.BattleArena;
import moves.Move;
import moves.damagingmove.physical.PhysicalDamagingMove;
import pokemon.Pokemon;

public class Hustle extends FirstTurnAbility{

    public Hustle() {
        super(  "Hustle",                                                                                                        //name, 
                "Boosts the Attack stat, but lowers accuracy.");                                                                //description
    }
    
    
    @Override
    public void exitingAbility(Pokemon user, Pokemon target, BattleArena battleArena){
        user.setOtherModifierFactorAtk(user.getOtherModifierFactorAtk()/1.5);                                                   //-50%     
        for(Move move : user.getAllMoves()){
            if(move instanceof PhysicalDamagingMove){
                move.setMoveAccuracy(1/0.8);                                                                                //-20%
            }
        }
        super.exitingAbility(user, target, battleArena);
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        user.setOtherModifierFactorAtk(user.getOtherModifierFactorAtk()*1.5);                                                    //+50%
        for(Move move : user.getAllMoves()){
            if(move instanceof PhysicalDamagingMove){
                move.setMoveAccuracy(0.8);                                                                                   //-20%
            }
        }
        
        //no message needed
    }


    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        return null;
    }

}
