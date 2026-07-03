package abilities.movecondition;

import battle_arena.BattleArena;
import moves.damagingmove.DamagingMove;
import pokemon.Pokemon;

public class SheerForce extends MoveConditionAbility{

    private boolean boostDone;
    
    public SheerForce() {
        super(  "Sheer Force",                                                                                    //name, 
                "Removes additional effects to increase the power of moves when attacking.");                     //description
        this.boostDone = false;
    }
     
    @Override
    public void setNextCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(checkMoveCondition(user, target, battleArena)){
            this.setIsActivable(true);
        }
        else{
            this.setIsActivable(false);
            this.boostDone = false;
        }
    }

    @Override
    public boolean checkMoveCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(user.isAttacking){
            return true;
        }
        return false;
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(user.equals(battleArena.activeEnemy)){
            if(battleArena.enemyMove instanceof DamagingMove){
                if(((DamagingMove)battleArena.enemyMove).sideEffect){
                    if(this.boostDone){                                                        
                        //riporto tutto a posto
                        this.dropPower((DamagingMove) battleArena.enemyMove);
                        ((DamagingMove)battleArena.enemyMove).sideEffect = true;
                    }
                    else{                                                 
                        //no message needed
                        ((DamagingMove)battleArena.enemyMove).sideEffect = false;
                        this.upPower((DamagingMove) battleArena.enemyMove);
                        this.boostDone = true;
                    }
                }
            }
            else{
                if(((DamagingMove)battleArena.playerMove).sideEffect){
                    if(this.boostDone){           
                        this.dropPower((DamagingMove) battleArena.playerMove);
                        ((DamagingMove)battleArena.playerMove).sideEffect = true;
                    }
                    else{                                                               
                        ((DamagingMove)battleArena.playerMove).sideEffect = false;
                        this.upPower((DamagingMove) battleArena.playerMove);
                        this.boostDone = true;
                    }
                }
            }
        }
        else{
            
        }
        
    }
    
    private void upPower(DamagingMove move){
        move.setBasePower(1.3);                                                                //+ 30%
    }
    
    private void dropPower(DamagingMove move){
        move.setBasePower(1/1.3);                                                              //- 30%
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        return null;
    }

}
