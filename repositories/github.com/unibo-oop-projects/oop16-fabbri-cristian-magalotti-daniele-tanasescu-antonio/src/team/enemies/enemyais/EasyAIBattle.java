package team.enemies.enemyais;

import java.io.Serializable;
import java.util.Random;

import battle_arena.BattleArena;
import moves.Move;
import moves.damagingmove.DamagingMove;
import moves.damagingmove.physical.selfrecoil.Struggle;
import moves.status.StatusMove;
import utilities.Utilities;

/**
 * 
 * @author Cristian
 *
 */

public class EasyAIBattle implements AIBattle, Serializable{
    

    private static final long serialVersionUID = 1L;
    
    private boolean timeToDamage;               //"clock" che decide quando attaccare o no
    private boolean hasStatus;                  //conferma che abbia ALMENO una mossa di stato
    private boolean hasDamaging;                //conferma che abbia ALMENO una mossa di attacco
    

    //the easiest AI possible
    public EasyAIBattle() {
        this.timeToDamage = false;              
    }

    @Override
    public void battleDecision(BattleArena battleArena) {
        if(battleArena.activeEnemy.hasStillAMoveWithSomePP()){          //se ha almeno una mossa utilizzabile...
            if(this.timeToDamage){
                this.damageDecision(battleArena);
            }
            else{
                this.statusDecision(battleArena);
            }
        }
        else{
            battleArena.enemyMove = new Struggle();                     //Struggle Event
        }

    }
    
    //questa AI è ingenua, userà una mossa random tra quelle disponibili di Status
    private void statusDecision(BattleArena battleArena){
        this.hasStatus = false;                                           //poi guardo se è cambiato!
        int[] points = new int[]{0,0,0,0};                                //valore iniziale del punteggio di decisione
        int index = 0;
        Random random = new Random();
        for(Move move : battleArena.activeEnemy.getAllMoves()){
            if(move instanceof DamagingMove){                            //se è una mossa che arreca danno
                points[index] += 0;                                     //non avrà punti!
            }
            else{
                if(move.hasSomePP()){                                   //la mossa non deve solo esserci ma deve anche essere utilizzabile (move.PP > 0)                      
                    this.hasStatus = true;
                    points[index] += random.nextInt(100) + 1;           //avrà almeno un punto (> ogni Damaging), ma la scelta sarà casuale fra le mosse Status!
                }
                //se non è utilizzabile la tratto al pari di una mossa di attacco!
                else{
                    points[index] += 0;
                }
            }
            index ++;
        }
        index = Utilities.indexPositionMaxInIntArray(points);            //controlla quale è la mossa con più "punteggio" -> decisione su quella
        //la decisione della mossa di stato verrà fatta se ha almeno una mossa di stato con PP > 0
        if(this.hasStatus){
            battleArena.enemyMove = battleArena.activeEnemy.getMove(index);
            battleArena.hasEnemyChosen = true;
            this.switchAIBehaviour();
        }
        else{                                                           //se non ha mosse di stato disponibili userà comunque una mossa di danno!              
            this.damageDecision(battleArena);                    
        }
    }
    
    //questa AI è ingenua, userà una mossa random tra quelle disponibili di Damage
    private void damageDecision(BattleArena battleArena){
        this.hasDamaging = false;                                          //poi guardo se è cambiato!
        int[] points = new int[]{0,0,0,0};                                //valore iniziale del punteggio di decisione
        int index = 0;
        Random random = new Random();
        for(Move move : battleArena.activeEnemy.getAllMoves()){
            if(move instanceof StatusMove){                            //se è una mossa che di stato
                points[index] += 0;                                     //non avrà punti!
            }
            else{
                if(move.hasSomePP()){                                   //la mossa non deve solo esserci ma deve anche essere utilizzabile (move.PP > 0)                      
                    this.hasDamaging = true;
                    points[index] += random.nextInt(100) + 1;           //avrà almeno un punto (> ogni Status), ma la scelta sarà casuale fra le mosse Damaging!
                }
                //se non è utilizzabile la tratto al pari di una mossa di stato!
                else{
                    points[index] += 0;
                }
            }
            index ++;
        }
        index = Utilities.indexPositionMaxInIntArray(points);            //controlla quale è la mossa con più "punteggio" -> decisione su quella
        //la decisione della mossa di attacco verrà fatta se ha almeno una mossa di attacco con PP > 0
        if(this.hasDamaging){
            battleArena.enemyMove = battleArena.activeEnemy.getMove(index);
            battleArena.hasEnemyChosen = true;
            this.switchAIBehaviour();
        }
        else{                                                           //se non ha mosse di attacco disponibili userà comunque una mossa di stato!              
            this.statusDecision(battleArena);                    
        }
        
    }
    
    private void switchAIBehaviour(){
        this.timeToDamage = !this.timeToDamage;
    }
    
 

}
