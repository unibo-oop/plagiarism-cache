package abilities.movecondition;

import battle_arena.BattleArena;
import moves.damagingmove.DamagingMove;
import pokemon.Pokemon;

public class Rivalry extends MoveConditionAbility{

    private boolean rivalryDone;
    
    public Rivalry() {
        super(  "Rivalry",                                                                                        //name, 
                "Becomes competitive and deals more damage to Pokemon of the same gender,\n"+                     //description
                "but deals less to Pokemon of the opposite gender.");
        this.rivalryDone = false;
    }

    @Override
    public boolean checkMoveCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(user.isAttacking){
            return true;
        }
        return false;
    }
    
    @Override
    public void setNextCondition(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(checkMoveCondition(user, target, battleArena)){
            this.setIsActivable(true);
        }
        else{
            this.setIsActivable(false);
            this.rivalryDone = false;
        }
    }

    @Override
    public void activateAbility(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(!user.isGenderless() && !target.isGenderless()){
            DamagingMove move = null;
            if(user.equals(battleArena.activeEnemy)){
                if(battleArena.enemyMove instanceof DamagingMove){
                    move = (DamagingMove)battleArena.enemyMove;
                }
            }
            else{
                if(battleArena.playerMove instanceof DamagingMove){
                    move = (DamagingMove)battleArena.playerMove;
                }
            }
            if(move != null){                                                                  
                if(user.getGender() != target.getGender()){
                    if(rivalryDone){                                                                
                        this.dropPower(move);                                                      
                    }
                    else{                                                                          
                        this.upPower(move);
                        this.rivalryDone = true;
                    }
                }
                else{
                    if(rivalryDone){                                                               
                        this.upPower(move);                                                         
                    }
                    else{                                                                          
                        this.dropPower(move);
                        this.rivalryDone = true;
                    }
                }
                
                //no message needed
            }
        }
        
    }
    
    private void upPower(DamagingMove move){
        move.setBasePower(1.25);                                                                //+ 25%
    }
    
    private void dropPower(DamagingMove move){
        move.setBasePower(1/1.25);                                                              //- 25%
    }

    @Override
    public String getAbilityEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        return null;
    }


}
