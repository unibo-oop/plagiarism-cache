package team.enemies.enemyais;

import java.io.Serializable;
import java.util.Random;

import battle_arena.BattleArena;
import moves.Move;
import moves.damagingmove.DamagingMove;
import moves.damagingmove.physical.selfko.SelfKOPhysicalDamagingMove;
import moves.damagingmove.physical.selfrecoil.Struggle;
import moves.status.StatusMove;
import moves.status.Switch;
import pokemon.Pokemon;
import team.enemies.Enemy;
import types.Type;
import utilities.Utilities;

/**
 * 
 * @author Cristian
 *
 */

public class MediumAIBattle implements AIBattle, Serializable{

    private static final long serialVersionUID = 1L;

    private static final int STATSBREAKINGPOINT = 450;                  /*oltre questo valore la AI continuerà a cercare di abbassare le 
     *statistiche avversarie o ad aumentare le proprie*/
    private static final double MINDIFFERENCE_SIGNIFICATIVE = 0.25;     //le differenze di punteggio peseranno se almeno del 25%
    private static final double NOTTHISTIMEFACTOR = 0.5;                /* una tipologia di mossa non voluta in quel frangente 
     * avrà metà del punteggio aggiuntivo*/
    private static final int MINPOINTS_TO_REMAINACTIVE = 25;            //punteggio minimo oltre il quale il P. attivo verrà sostituito
    private static final int MAXPOINTFACTORCHANGE = 4;                  //cambio di punteggio massimo possibile (in positivo o negativo)
    private static final int NICEPOINTFACTORCHANGE = 2;                 //un cambio di punteggio comunque non irrilevante (in positivo o negativo)
    private static final int HASMOVECHANGE = 10;                        //punteggio al proprietario di una buona mossa
    private static final int NORMALMOVE = 25;                           //punteggio ad una mossa decente almeno
    private static final int EXCELLENTMOVE = 50;                        //punteggio ad un'ottima mossa


    private int enemyEstimatePower;                                     //stima della potenza dell'avversario (Player) 
    private int[] membersPoints;                                        /*sistema di decision making di qual'è il best-fit membro della squadra 
     *contro quel particolare attivo dell'avversario (Player)*/
    private int[] movesPoints;                                          //sistema decisionale della mossa migliore da fare

    private boolean timeToDamage;                                       /*"clock" che decide quando attaccare o no 
     *(è solo un poco più "intelligente", non troppo)*/      

    //a harder AI to defeat that will be able also to Switch
    public MediumAIBattle(int numOfPokemon) {
        this.timeToDamage = false;                                      //se necessario, non partirà ad attaccare (contro i più potenti)
        this.membersPoints = new int[numOfPokemon];
        this.movesPoints = new int[4];
        this.resetPoints();
    }


    @Override
    public void battleDecision(BattleArena battleArena) {
        this.resetPoints();     
        this.calculateMemberPoints(battleArena);
        Enemy enemy = battleArena.getEnemy();                                           //per risparmiare righe di codice...
        //scopro quale e' la posizione dell'enemy attivo nell'array del team (servirà poi per capire che punteggio ha)
        int actualActiveIndex = battleArena.getEnemy().getItsPosition(battleArena.activeEnemy);
        int bestIndex = Utilities.indexPositionMaxInIntArray(membersPoints);            //l'AI conosce ora chi ha preso piu' punti

        if(actualActiveIndex != bestIndex){                                             //se l'attivo non è la "scelta" migliore
            int actualActivePoints = this.membersPoints[actualActiveIndex];
            int bestPoints = this.membersPoints[bestIndex];

            if(actualActivePoints < MINPOINTS_TO_REMAINACTIVE){                    //se l'attivo attuale è talmente "scarso"...
                //la mossa che sceglie la AI è un cambio dell'attivo col migliore, per quanto "migliore" sia (non si sa)...
                this.decisionChange(battleArena, enemy, actualActiveIndex, bestIndex);
            }
            else{                                                                       //se è almeno "accettabile"..
                //se la differenza tra lui è il migliore è però significativa, almeno >= 25%
                if(((actualActivePoints-bestPoints)*100/bestPoints) >= MINDIFFERENCE_SIGNIFICATIVE){
                    //lo cambio comunque, quella scelta è davvero migliore dell'attuale attivo
                    this.decisionChange(battleArena, enemy, actualActiveIndex, bestIndex);
                }
                else{                                                                   //tiene questo attivo, da decidersi la mossa
                    this.calculateEnemyEstimatePower(battleArena);                      /*calcolo la stima della forza del nemico
                     *che farà decidere se danneggiare o giocare
                     *in modo più "strategico"*/
                    if(this.enemyEstimatePower > STATSBREAKINGPOINT){                   //se è un avversario particolarmente potente
                        if(this.timeToDamage){
                            this.decisionDamage(battleArena);
                        }
                        else{
                            this.decisionStrategy(battleArena);
                        }
                        this.switchAIBehaviour();
                    }
                    else{
                        this.decisionDamage(battleArena);
                    }
                }
            }
        }


    }

    private void calculateEnemyEstimatePower(BattleArena battleArena){
        this.enemyEstimatePower = 0;
        this.enemyEstimatePower += battleArena.activePlayer.getMaxHp();
        this.enemyEstimatePower += battleArena.activePlayer.getAtk();
        this.enemyEstimatePower += battleArena.activePlayer.getDef();
        this.enemyEstimatePower += battleArena.activePlayer.getSpe();
        this.enemyEstimatePower += battleArena.activePlayer.getSpA();
        this.enemyEstimatePower += battleArena.activePlayer.getSpD();
    }

    private void resetPoints(){                                                  //assegna dei valori arbitrari di partenza ai vari punteggi
        for(int i = 0; i < this.membersPoints.length; i++){
            this.membersPoints[i] = 100;                                                
        }
        for(int i = 0; i < this.movesPoints.length; i++){
            this.movesPoints[i] = 100;
        }
    }

    private void calculateMemberPoints(BattleArena battleArena){
        Enemy enemy = battleArena.getEnemy();
        for(int i = 0; i < this.membersPoints.length; i++){                     //this.membersPoints.length == enemy.team_num_of_members   
            for(Type playerType: battleArena.activePlayer.getType()){           //controllo le affinità di tipi con l'attivo di Player...
                if(playerType != null){
                    for(Type enemyType : enemy.getPokemon()[i].getType()){          //...con ogni tipo di quell'enemy member     
                        if(enemyType != null){
                            //enemy towards player types check
                            if(enemyType.isImmuneTo(playerType)){                       //quindi se questo enemy è immune al player attivo...
                                this.membersPoints[i] *= MAXPOINTFACTORCHANGE;                //...avrà un buonissimo punteggio!       
                            }
                            else if(enemyType.isResistantTo(playerType)){               //se questo tipo di enemy è resistente a questo di player...
                                this.membersPoints[i] *= NICEPOINTFACTORCHANGE;               //...comunque avrà un buon punteggio!
                            }
                            else if(enemyType.isWeakTo(playerType)){                    //se invece questosuo tipo ne soffre...
                                this.membersPoints[i] /= NICEPOINTFACTORCHANGE;               //...riceve un punteggio peggiore                        
                            }

                            //player towords enemy types check
                            if(playerType.isImmuneTo(enemyType)){                       //se player è immune a questo enemy...
                                this.membersPoints[i] /= MAXPOINTFACTORCHANGE;                //...riceverà un punteggio molto basso!   
                            }
                            else if(playerType.isResistantTo(enemyType)){               //se questo tipo di player è resistente a questo di enemy...
                                this.membersPoints[i] /= NICEPOINTFACTORCHANGE;               //...riceverà un punteggio più basso                        
                            }
                            else if(playerType.isWeakTo(enemyType)){                    //se invece ne soffre un pò...
                                this.membersPoints[i] *= NICEPOINTFACTORCHANGE;               //...riceverà un buon punteggio!         
                            }

                        }
                    }
                    for(Move move : enemy.getPokemon()[i].getAllMoves()){           //...e ora controllo se le mosse che ha sono inutili, buone o ottime
                        if(move instanceof DamagingMove){                           // (controllo solo quelle che fanno danno)
                            if(playerType.isImmuneTo(move.getMoveType())){          //se il nemico è immune a quella mossa...
                                this.membersPoints[i] -= HASMOVECHANGE*2;                   //...il possessore perderà molti punti!
                            }
                            else if(playerType.isResistantTo(move.getMoveType())){ //se invece gli resiste...
                                this.membersPoints[i] -= HASMOVECHANGE;                     //...il possessore perde dei punti
                            }
                            else if(playerType.isWeakTo(move.getMoveType())){       //se invece ne è debole...
                                this.membersPoints[i] += HASMOVECHANGE;                     //...il possessore guadagna dei punti!
                            }
                        }
                    }
                }                
            }

        }
    }

    private void calculateMovesPoints(BattleArena battleArena, boolean damage){
        Random random = new Random();                                           //la scelta delle status move sarà ancora random...
        Pokemon enemyActive = battleArena.activeEnemy;                          //salva linee di codice...
        int index = 0;
        if(damage){
            for(Move move : enemyActive.getAllMoves()){
                double factor = 1;                                              //sarà il segno dei punteggi sommati
                int pointsAdded = 0;
                if(move instanceof DamagingMove){
                    for(Type playerType : battleArena.activePlayer.getType()){  //controllo l'affinità dei tipi
                        if(playerType != null){
                            if(playerType.isImmuneTo(move.getMoveType())){          //se è immune
                                factor *= 0;
                            }
                            else if(playerType.isResistantTo(move.getMoveType())){   //se è resistente
                                factor *= 0.75;
                                pointsAdded += NORMALMOVE;
                            }
                            else if(playerType.isWeakTo(move.getMoveType())){       //se ne è debole
                                factor *= 2;
                                pointsAdded += EXCELLENTMOVE;
                            }
                            else{                                                   //se ne è indifferente
                                pointsAdded += NORMALMOVE;
                            }                                
                        }
                    }
                    pointsAdded += ((DamagingMove) move).getMoveBasePower();    //ne sommo la potenza di base!
                }
                else{                                                           //se è una mossa di stato
                    factor = NOTTHISTIMEFACTOR;
                    pointsAdded = NORMALMOVE;
                }

                if(move instanceof SelfKOPhysicalDamagingMove){                 //altrimenti potrebbero Esplodere tutti i Pokemon...
                    pointsAdded -= NORMALMOVE;
                }
                this.movesPoints[index] += pointsAdded*factor;
                index++;
            }
        }
        else{                                                                   //cerco una status move
            for(Move move : enemyActive.getAllMoves()){
                double factor = 1;                                              //sarà il segno dei punteggi sommati
                int pointsAdded = 0;
                if(move instanceof StatusMove){
                    for(Type playerType : battleArena.activePlayer.getType()){  //controllo l'affinità dei tipi
                        if(playerType.isImmuneTo(move.getMoveType())){          //se è immune
                            factor *= 0;
                        }
                        else{                                                   //se ne è indifferente
                            pointsAdded += EXCELLENTMOVE + random.nextInt(EXCELLENTMOVE);
                        }      
                    }
                }
                else{                                                           //se è una mossa che fa danno
                    factor = NOTTHISTIMEFACTOR;
                    //cast sicuro, c'è quindi anche la (piccola) possibilità che venga scelta lo stesso una mossa di attacco (quando molto potente)!
                    pointsAdded = NORMALMOVE + ((DamagingMove)move).getMoveBasePower();
                }

                this.movesPoints[index] += pointsAdded*factor;
                index++;
            }

        }

    }

    private void decisionDamage(BattleArena battleArena){
        if(battleArena.activeEnemy.hasStillAMoveWithSomePP()){
            this.calculateMovesPoints(battleArena, true);                             //it will prefer damaging moves
            int bestIndex = Utilities.indexPositionMaxInIntArray(movesPoints);        //index of the move with the gratest points
            battleArena.enemyMove = battleArena.activeEnemy.getMove(bestIndex);
            battleArena.hasEnemyChosen = true;
        }
        else{
            battleArena.enemyMove = new Struggle();                              //Struggle Event
        }
    }

    private void decisionStrategy(BattleArena battleArena){
        if(battleArena.activeEnemy.hasStillAMoveWithSomePP()){
            this.calculateMovesPoints(battleArena, true);                            //it will prefer status moves
            int bestIndex = Utilities.indexPositionMaxInIntArray(movesPoints);        //index of the move with the gratest points
            battleArena.enemyMove = battleArena.activeEnemy.getMove(bestIndex);
            battleArena.hasEnemyChosen = true;
        }
        else{
            battleArena.enemyMove = new Struggle();                              //Struggle Event
        }
    }

    private void decisionChange(BattleArena battleArena, Enemy enemy, int actualActiveIndex, int bestIndex){
        battleArena.enemyMove = new Switch(enemy.getPokemon()[actualActiveIndex], enemy.getPokemon()[bestIndex]);  
        battleArena.hasEnemyChosen = true;
    }

    private void switchAIBehaviour(){
        Random random = new Random();
        this.timeToDamage = random.nextBoolean();                               //it will be casual!
    }

}
