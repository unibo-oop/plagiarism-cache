package abilities.movecondition;

import battle_arena.BattleArena;
import moves.damagingmove.DamagingMove;
import pokemon.Pokemon;

public class ShieldDust extends MoveConditionAbility{
    
    private boolean sideEffectNegated;

    public ShieldDust() {
        super(  "Shield Dust",                                                                 //name
                "This Pokemon's dust blocks the additional effects of attacks taken");        //description
        this.sideEffectNegated = false;
    }

    @Override
    public void setNextCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(checkMoveCondition(user, target, battleArena)){
            this.setIsActivable(true);
        }
        else{
            this.setIsActivable(false);
            this.sideEffectNegated = false;
        }
    }
    
    @Override
    public boolean checkMoveCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
        boolean answer = false;
        if(target.equals(battleArena.activeEnemy)){
            if(battleArena.enemyMove instanceof DamagingMove){
                answer = ((DamagingMove)battleArena.enemyMove).sideEffect;
            }
        }
        else{
            if(battleArena.playerMove instanceof DamagingMove){
                answer = ((DamagingMove)battleArena.playerMove).sideEffect;
            }
        }
        
        return answer;
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(!this.sideEffectNegated){
            //no message needed
            if(target.equals(battleArena.activeEnemy)){
                this.negateEffect((DamagingMove)battleArena.enemyMove);
            }
            else{
                this.negateEffect((DamagingMove)battleArena.playerMove);
            }
            this.sideEffectNegated = true;
        }
        else{                                                                  
            if(target.equals(battleArena.activeEnemy)){
                this.reactivateEffect((DamagingMove)battleArena.enemyMove);
            }
            else{
                this.reactivateEffect((DamagingMove)battleArena.playerMove);
            }
        }
        
    }
    
    private void negateEffect(DamagingMove move){
        move.sideEffect = false;
    }
    
    private void reactivateEffect(DamagingMove move){
        move.sideEffect = true;
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        return null;
    }

}
