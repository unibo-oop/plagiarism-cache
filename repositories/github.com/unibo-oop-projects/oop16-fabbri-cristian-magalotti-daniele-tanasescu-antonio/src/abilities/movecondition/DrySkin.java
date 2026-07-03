package abilities.movecondition;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import moves.Move;
import moves.damagingmove.DamagingMove;
import pokemon.Pokemon;
import types.Fire;
import types.Type;
import types.Water;

public class DrySkin extends MoveConditionAbility{

    private static final String DRYSKINABSORB = "absorbs ";
    private static final String DRYSKINATTACK = " 's attack!";
    private static final String DRYSKINENEMY = "opponent ";

    public DrySkin() {
        super(  "Dry Skin",                                                                                           //name, 
                "Restores HP in rain or when hit by Water-type moves.\n"+                                             //description
                "Reduces HP in sunshine, and increases the damage received from Fire-type moves.");      
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
        if(target.isAttacking){
            if(target.equals(battleArena.activeEnemy)){
                if(battleArena.enemyMove instanceof DamagingMove){
                    if(this.checkMoveTwoType(battleArena.enemyMove, new Water(), new Fire())){
                        return true;
                    }
                }
            }
            else{
                if(battleArena.playerMove instanceof DamagingMove){
                    if(this.checkMoveTwoType(battleArena.playerMove, new Water(), new Fire())){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(target.equals(battleArena.activeEnemy)){
            if(this.checkMoveType(battleArena.enemyMove, new Water())){
                BattleMenuController.battleLogManager.setAbilityActivationMessage(user, this.getAbilityEffect(user, target, battleArena));
                this.waterStop(target, user);
            }
            else{                    
                //no message needed
                this.firePowerUp(target);
            }
        }
        else{
            if(this.checkMoveType(battleArena.playerMove, new Water())){
                this.waterStop(target, user);
            }
            else{                                                                                      
                this.firePowerUp(target);
            }
        }
        
    }
    
    private boolean checkMoveTwoType(Move move, Type type1, Type type2){
        boolean first = this.checkMoveType(move, type1);
        boolean second = this.checkMoveType(move, type2);
        return (first || second);
    }
    
    private boolean checkMoveType(Move move, Type type){
        return move.getMoveType().equals(type);
    }
    
    private void waterStop(Pokemon whoIsAttacking, Pokemon whoHasTheAbility){
        whoIsAttacking.effectiveness = 0;
        if(whoHasTheAbility.getHp() < whoHasTheAbility.getMaxHp()){
            whoHasTheAbility.takeDamage(-whoHasTheAbility.getMaxHp()/4, false);         //heals                                
        }
    }
    
    private void firePowerUp(Pokemon whoIsAttacking){
        whoIsAttacking.effectiveness*= 1.25;                                                           
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        String message = DRYSKINABSORB + (target.equals(battleArena.activeEnemy)? DRYSKINENEMY : "") + 
                         target.toString() + DRYSKINATTACK;
        return message;
    }

}
