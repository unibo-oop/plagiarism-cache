package abilities.hpcondition;

import battle_arena.BattleArena;
import moves.Move;
import moves.damagingmove.DamagingMove;
import pokemon.Pokemon;
import types.Fire;

public class Blaze extends HPConditionAbility{

    public Blaze() {
        super(  "Blaze",                                                                                //name 
                "Powers up Fire-type moves when the Pokemon's HP is low.",                              //description 
                0.33);                                                                                  //hpBound
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        for(Move move : user.getAllMoves()){
            if(move instanceof DamagingMove && move.getMoveType().equals(new Fire())){
                ((DamagingMove)move).setBasePower(1.5);
            }
        }
        this.effectDone = true;
        //no message needed        
    }
    
    @Override
    public void exitingAbility(Pokemon user, Pokemon target, BattleArena battleArena){
        if(this.hpBound *target.getMaxHp() <= target.getHp()){
            for(Move move : target.getAllMoves()){
                if(move instanceof DamagingMove && move.getMoveType().equals(new Fire())){
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
