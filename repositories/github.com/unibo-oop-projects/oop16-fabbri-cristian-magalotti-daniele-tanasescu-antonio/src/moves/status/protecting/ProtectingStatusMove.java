package moves.status.protecting;

import java.util.Random;

import battle_arena.BattleArena;
import main.view.BattleMenuController;
import moves.Move;
import moves.status.StatusMove;
import pokemon.Pokemon;
import types.Type;

public class ProtectingStatusMove extends StatusMove{

    public static final double FIRSTPROTECTACCURACY = 999;
    public static final double THEORICPROTECTACCURACY = 1;
    
    private int consecutiveProtections;
       
    public ProtectingStatusMove(String name, String description, Type moveType, int minPP, int priority) {
        super(name, description, moveType, FIRSTPROTECTACCURACY, minPP, priority);
        this.resetConsecutiveProtections();
        this.setSelfEffect(true);
    }

    @Override
    public void sideEffect(Pokemon user, Pokemon target, BattleArena battleArena) {
        if(!user.equals(battleArena.speedTie[1])){                                      //useless..
            Random random = new Random();
            if(random.nextDouble() < this.getMoveAccuracy()){
                user.isProtecting = true;
                this.incrementConsecutiveProtections();            
            }
            else{
                user.isProtecting = false;
                BattleMenuController.battleLogManager.setMoveFailedMassage();
                this.resetConsecutiveProtections();
            }
            Move targetMove;
            if(target.equals(battleArena.activeEnemy)){                
                targetMove = battleArena.enemyMove;
            }
            else{
                targetMove = battleArena.playerMove;
            }
            if(targetMove instanceof StatusMove){
                if(((StatusMove) targetMove).isSelfEffect()){                              //can't protect them!
                    user.isProtecting = false;                                             /*also if it was true (and Protect's 
                                                                                            accuracy is dropped on purpose!)*/
                }
            }
        }
        else{
            BattleMenuController.battleLogManager.setMoveFailedMassage();
            this.resetConsecutiveProtections();
        }
    }
    
    @Override
    public double getMoveAccuracy(){
        if(this.consecutiveProtections == 0){
            return FIRSTPROTECTACCURACY;
        }
        else return THEORICPROTECTACCURACY/(this.consecutiveProtections*2);
    }

    public int getConsecutiveProtections() {
        return consecutiveProtections;
    }

    public void setConsecutiveProtections(int consecutiveProtections) {
        this.consecutiveProtections = consecutiveProtections;
    }
    
    public void incrementConsecutiveProtections(){
        this.consecutiveProtections++;
    }
    
    public void resetConsecutiveProtections(){
        this.setConsecutiveProtections(0);
    }


}
