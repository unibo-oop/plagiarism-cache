package abilities.movecondition;

import battle_arena.BattleArena;
import moves.damagingmove.DamagingMove;
import pokemon.Pokemon;

public class Analytic extends MoveConditionAbility{
    
    public boolean boostDone;

    public Analytic() {
        super(  "Analytic",                                                                     //name
                "Boosts move power when the Pokemon moves last.");                              //description
        this.boostDone = false;
    }

    
    @Override
    public void setNextCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(checkMoveCondition(user, target, battleArena)){
            this.setIsActivable(true);
        }
        else{
            this.setIsActivable(false);
        }
    }
    
    @Override
    public boolean checkMoveCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(!this.boostDone && user.isAttacking && battleArena.speedTie[1].equals(user)){
            this.boostDone = true;
            return true;
        }
        else if(this.boostDone){
            this.boostDone = false;
            if(user.lastMoveUsed instanceof DamagingMove){
                ((DamagingMove)user.lastMoveUsed).setBasePower(1/1.3);
            }
        }
        return false;
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(user.equals(battleArena.activeEnemy)){
            if(battleArena.enemyMove instanceof DamagingMove){
                ((DamagingMove)battleArena.enemyMove).setBasePower(1.3);
            }
        }
        else{
            if(battleArena.playerMove instanceof DamagingMove){
                ((DamagingMove)battleArena.playerMove).setBasePower(1.3);
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
