package moves.damagingmove.special.counterattacking;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import moves.damagingmove.DamagingMove;
import moves.damagingmove.special.SpecialDamagingMove;
import pokemon.Pokemon;
import types.Psychic;
import types.Type;

public class MirrorCoat extends SpecialDamagingMove{

    public MirrorCoat() {
        super(  "Mirror Coat",                                                                                          //name
                "A retaliation move that counters any special attack, inflicting double the damage taken.",             //description
                999,                                                                                                    //base power
                new Psychic(),                                                                                          //type
                1,                                                                                                      //accuracy
                critRange1,                                                                                             //crit range 
                20,                                                                                                     //PP
                -5);                                                                                                    //Priority
    }
    
    
    @Override
    public void getDamage(Pokemon user, Pokemon target, BattleArena battleArena) {
        BattleMenuController.battleLogManager.setUsedMoveMessage(user, target, battleArena, this);
        for(Type type : target.getType()){
            if(type != null){
                if(type.isImmuneTo(this.getMoveType())){
                    BattleMenuController.battleLogManager.setEffectivenessMessage(0);
                    return;
                }
            }
        }
        if(battleArena.speedTie[0].equals(target)){                                                     
            if(target.equals(battleArena.activePlayer)){                                                
                if(battleArena.playerMove instanceof SpecialDamagingMove){                              
                    if(!battleArena.playerMove.equals(this)){                                          
                        double damage = 2 *((DamagingMove)battleArena.playerMove).getLastDamageDone();  
                        target.takeDamage(damage, this.hasRecoil());
                    }
                    else{
                        BattleMenuController.battleLogManager.setMoveFailedMassage();
                    }
                }       
                else{                                                                              
                    BattleMenuController.battleLogManager.setMoveFailedMassage();
                }
            }
            else{                                                                                                                                                                    
                if(battleArena.enemyMove instanceof SpecialDamagingMove){                               
                    if(!battleArena.enemyMove.equals(this)){                                            
                        double damage = 2 *((DamagingMove)battleArena.enemyMove).getLastDamageDone();  
                        target.takeDamage(damage, this.hasRecoil());
                    }
                    else{
                        BattleMenuController.battleLogManager.setMoveFailedMassage();
                    } 
                }    
                else{                                                                              
                    BattleMenuController.battleLogManager.setMoveFailedMassage();
                }
            }
        }
        else{                                                                                  
            BattleMenuController.battleLogManager.setMoveFailedMassage();
        }
    }
        

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        // TODO Auto-generated method stub
        
    }

}
