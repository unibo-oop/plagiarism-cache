package abilities.firstturn;

import battle_arena.BattleArena;
import moves.Move;
import moves.damagingmove.DamagingMove;
import pokemon.Pokemon;

public class Technician extends FirstTurnAbility{

    public Technician() {
        super(  "Technician",                                                   //name, 
                "Powers up the Pokémon's weaker moves. ");                      //description
    }

    @Override
    public void exitingAbility(Pokemon user, Pokemon target, BattleArena battleArena){
        for(Move move : user.getAllMoves()){
            if(move instanceof DamagingMove){
                if(((DamagingMove) move).getMoveBasePower() <= 60){
                    ((DamagingMove) move).setBasePower(1/1.5);                  //- 50%
                }
            }
        }   
        super.exitingAbility(user, target, battleArena);
    }  

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        for(Move move : user.getAllMoves()){
            if(move instanceof DamagingMove){
                if(((DamagingMove) move).getMoveBasePower() <= 60){
                    ((DamagingMove) move).setBasePower(1.5);                    //+ 50%
                }
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
