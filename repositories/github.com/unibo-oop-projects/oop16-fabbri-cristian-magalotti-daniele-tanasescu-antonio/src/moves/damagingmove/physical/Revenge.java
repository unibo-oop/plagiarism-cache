package moves.damagingmove.physical;

import battle_arena.BattleArena;
import moves.damagingmove.DamagingMove;
import pokemon.Pokemon;
import types.Fight;

public class Revenge extends PhysicalDamagingMove{

    public Revenge() {
        super(  "Revenge",                                                                                                      //name
                "This attack move's power is doubled if the user has been hurt by the opponent in the same turn.",              //description
                60,                                                                                                             //base power
                new Fight(),                                                                                                    //type
                1,                                                                                                              //accuracy
                critRange1,                                                                                                     //crit range 
                25,                                                                                                             //PP
                -4);                                                                                                            //Priority
    }
    
    @Override
    public void getDamage(Pokemon user, Pokemon target, BattleArena battleArena){
        boolean boost = false;
        if(battleArena.speedTie[0].equals(target)){                                     
            if(target.equals(battleArena.activeEnemy)){                                
                if(battleArena.enemyMove instanceof DamagingMove){                    
                    if(!battleArena.enemyMove.hasFailed){                            
                        boost = this.doublePower();                        
                    }
                }
            }
            else{                                                                       
                if(battleArena.playerMove instanceof DamagingMove){                     
                    if(!battleArena.playerMove.hasFailed){                               
                        boost = this.doublePower();                        
                    }
                }               
            }
        }
        
        super.getDamage(user, target, battleArena);
        if(boost){
            this.setBasePower(0.5);
        }
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }
    
    private boolean doublePower(){
        this.setBasePower(2d);
        return true;
    }

}
