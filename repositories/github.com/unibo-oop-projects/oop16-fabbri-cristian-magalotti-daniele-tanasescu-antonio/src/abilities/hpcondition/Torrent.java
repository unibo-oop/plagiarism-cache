package abilities.hpcondition;

import battle_arena.BattleArena;
import moves.Move;
import moves.damagingmove.DamagingMove;
import pokemon.Pokemon;
import types.Water;

public class Torrent extends HPConditionAbility{

    public Torrent() {
        super(  "Torrent",                                                                               //name 
                "Powers up Water-type moves when the Pokemon's HP is low.",                              //description 
                0.33);                                                                                   //hpBound
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        for(Move move : user.getAllMoves()){
            if(move instanceof DamagingMove && move.getMoveType().equals(new Water())){
                ((DamagingMove)move).setBasePower(1.5);
            }
        }
        //no message needed        
    }
    
    public void exitingAbility(Pokemon user, Pokemon target, BattleArena battleArena){
        if(this.hpBound *target.getMaxHp() <= target.getHp()){
            for(Move move : target.getAllMoves()){
                if(move instanceof DamagingMove && move.getMoveType().equals(new Water())){
                    ((DamagingMove)move).setBasePower(1/1.5);
                }
            }
        }
        super.exitingAbility(user, target, battleArena);
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        return null;
    }

}
