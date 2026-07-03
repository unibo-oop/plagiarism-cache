package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.SwingUtilities;

import controller.setting.MovesActive;
import controller.setting.MovesDisabled;
import controller.setting.PossibleMoves;
import controller.setting.ThreatActive;
import controller.setting.ThreatDisabled;
import controller.setting.ThreatPawns;
import controller.setting.Time;
import controller.setting.TimeActive;
import controller.setting.TimeDisabled;
import utilities.Castling;
import utilities.Pair;
import utilities.Players;
import view.BoardLayout;
import view.Chess;
import view.Items.PawnsRemoved;
import model.chessboard.Chessboard;
import model.logic.GameLogic;
import model.memento.Memento;
import model.memento.MementoImpl;
import model.utilities.pawns.*;

/**
 * 
 * @author Alex Ravaglia
 *Implementation of the controller of the game, it implements the interface Controller
 */
public final class ControllerImp implements Controller{

    private static ControllerImp controller;
    private static final GameLogic GL = GameLogic.getLog();
    private static final Chessboard CH = Chessboard.getLog();
    private static final Chess VIEW = Chess.getLog();
    private static final Memento MEM = MementoImpl.getLog();
    private PossibleMoves absMove = new MovesDisabled();
    private ThreatPawns absThreat = new ThreatDisabled();
    private Time absTime = new TimeDisabled();
    private Set<Pair<Integer,Integer>> possibleMoves;
    private Map<Players,Set<Pair<Pawn,Integer>>> eatenPawn = new HashMap<>();
    private Pawn selectedPawn ;
    private Castling type;  
    private int turno;
    private Rook storedRook; 
    private List<Pair<Pawn, Pair<Integer,Integer>>> storedList = new ArrayList<>();
    private Pair<Integer, Integer> storedRookPosition;
    private boolean seePossibleMove=false; 
    private boolean seeThreatPawn=false;
    private boolean timeGame=false;

    /**
     *private constructor:
     *inizialize the kingObserver in Model
     *inizialize the Map of the eaten pawn
     */
    private ControllerImp() {
        GL.setKingObservers();		
        this.eatenPawn.put(GL.getActualPlayer(), new TreeSet<>(new PawnComparator()));
        this.eatenPawn.put(Players.opposite() , new TreeSet<>(new PawnComparator()));
    }

    public void timeCheck() {
        this.timeGame = activeDeactive(this.timeGame);
        if(timeGame){
            SwingUtilities.invokeLater(new Runnable () {  
                public void run() {
                    absTime = new TimeActive();  
                }  
            }); 
        }else{
            absTime = new TimeDisabled();
        }
    }

    public void movesCheck() {
        this.seePossibleMove = activeDeactive(this.seePossibleMove);
        absMove = this.seePossibleMove ? new MovesActive() : new MovesDisabled();	
    }

    public void threatCheck() {	
        seeThreatPawn=activeDeactive(this.seeThreatPawn);	
        absThreat = this.seeThreatPawn ? new ThreatActive() :  new ThreatDisabled();	
    }

    /**
     * change the value to a boolean with the opposite value
     * it needs to active o disabled the game settings
     * @param b the boolean value 
     * @return opposite value of the boolean in input
     */
    private boolean activeDeactive(final boolean b) {
        return  !b; 
    }

    public void gameStart() {
        BoardLayout.getLog().start();     
    }

    public void onChessboardHit(final Pair<Integer,Integer> hit){
        if((isSelectedPawn() &&( (!CH.isEmpty(hit) && !(GL.getActualPlayer().equals( CH.getPawnByCoordinate(hit).getPlayer()))) || (CH.isEmpty(hit)) ))){			
            move(hit); 	
        }else{ 
            if(!CH.isEmpty(hit)){	 
                if (CH.getPawnByCoordinate(hit).getPlayer().equals(GL.getActualPlayer()) ){  
                    VIEW.repaintTable();
                    this.selectedPawn=CH.getPawnByCoordinate(hit); 
                    getPossibleMoves(hit); 
                    absMove.checkPossibleMoves(this.possibleMoves);
                }
            }else{ 
                selectedPawn=null;
                this.possibleMoves=null;
            }	
        }
    }
    
    /**
     * move the selected pawn in the position in input, first must be call canMove to verify
     * if is a possible move for the pawn selected and verify if the move is a castling or there is a pawn promotion because
     * a simple pawn is arrive in final position. After the moves it update the view,model and call change turn
     * @param endPosition the position where the selected pawn want go
     */
    private void move(final Pair<Integer,Integer> endPosition){ 
        if(canMove(endPosition)){
            if(!CH.isEmpty(endPosition)){ 					
                eatPawn(endPosition);			
            }
            if(isKing() && isCastling(endPosition)){               
                castling(endPosition);
            }else{
                VIEW.setNextMove(selectedPawn, selectedPawn.getActualPosition() , endPosition);
                VIEW.repaintTable();
                this.storedList.add(new Pair<>(selectedPawn, selectedPawn.getActualPosition()));
                MEM.setNewMovements(this.storedList); 
                GL.setNextMove(selectedPawn, endPosition);
                if(isPawnOnLimit()){
                    VIEW.changePawnWith();
                }
            }
            changeTurn();
        }else{
            selectedPawn=null;
            VIEW.repaintTable();
        }
    }

    public void pawnPromotion(final PawnTypes type){
        GL.setPawnInLimit(selectedPawn, type, selectedPawn.getActualPosition() );
        MEM.setNewPawnAdded(CH.getPawnByCoordinate(selectedPawn.getActualPosition()), selectedPawn.getActualPosition());
        VIEW.reset();
        VIEW.setPawnsIntoChessBoard(CH.getMapOfPlayers());
    }

    public void undoMove(){
        if(turno>0 && !this.timeGame){
            VIEW.repaintTable();
            Pair<Pawn, Pair<Integer, Integer>> removedPawn = null;
            Pair<Pawn, Pair<Integer, Integer>> addedPawn = null;
            final List<Pair<Pawn, Pair<Pair<Integer,Integer>, Pair<Integer,Integer>>>> lastMoved = MEM.getLast();
            addedPawn = MEM.getAddedPawn();
            removedPawn = MEM.getRemovedPawn();
            for (final Pair<Pawn, Pair<Pair<Integer,Integer>, Pair<Integer,Integer>>> p : lastMoved){
                VIEW.setNextMove(p.getX(), p.getX().getActualPosition(), p.getY().getX());
                GL.restoreMoves(p.getX(), p.getY().getX());
            }
            if (removedPawn != null){
                GL.putRemovedPawn(removedPawn.getX(), removedPawn.getY());
                removePawnFromEaten(removedPawn.getX());
                PawnsRemoved.getLog().pawnsDisplay(eatenPawn);
            }
            if(addedPawn != null){
                GL.removeAddedPawn(addedPawn.getX(), addedPawn.getY());
            }
            changeTurn();
            turno-=2;
            VIEW.setPawnsIntoChessBoard(CH.getMapOfPlayers());
            this.storedList = new ArrayList<>();
            this.storedRook = null;
            this.storedRookPosition = null;
        }
    }

    /**
     * remove a pawn by the map of eaten pawn of each players.
     * it is called when you want to restore a past situation, so the
     * eaten pawn return on the chessboard
     * @param removedPawn the pawn who must be removed by the map of the eaten pawn
     */
    private void removePawnFromEaten(final Pawn removedPawn){       
        final Set<Pair<Pawn,Integer>> setp1 = new HashSet<>(eatenPawn.get(Players.playerWhite));
        final Set<Pair<Pawn,Integer>> setp2 = new HashSet<>(eatenPawn.get(Players.playerBlack));
        if(GL.getActualPlayer().equals(Players.playerWhite)){
            for(final Pair<Pawn,Integer> pw : eatenPawn.get(Players.playerWhite)){
                if(removedPawn.compare(pw.getX().getType())){
                    if(pw.getY()>1){
                        setp1.remove(pw);                      
                        setp1.add(new Pair<>(pw.getX(), pw.getY()-1));
                    }else{
                        setp1.remove(pw);
                    }
                }       
            }
        }else{
            for(final Pair<Pawn,Integer> pw : eatenPawn.get(Players.playerBlack)){
                if(removedPawn.compare(pw.getX().getType())){
                    if(pw.getY()>1){
                        setp2.remove(pw);                      
                        setp2.add(new Pair<>(pw.getX(), pw.getY()-1));
                    }else{
                        setp2.remove(pw);
                    }
                }
            }

        }
        eatenPawn.get(Players.playerWhite).removeAll(eatenPawn.get(Players.playerWhite));
        eatenPawn.get(Players.playerBlack).removeAll(eatenPawn.get(Players.playerBlack));
        eatenPawn.get(Players.playerWhite).addAll(setp1);
        eatenPawn.get(Players.playerBlack).addAll(setp2);
    }
    
    /**
     * check if a simple pawn is arrived in the limit of the chessboard
     * @return true if a simple pawn is in the limit, false otherwise
     */
    private boolean isPawnOnLimit(){
        return (selectedPawn.compare(PawnTypes.SimplePawn) && ( ((SimplePawn)selectedPawn).checkChange()));
    }

    /**
     * do the type castling of the king with the storedRook
     * update the situation in view & model
     * @param endPosition where the king must go after the castling
     */
    private void castling(final Pair<Integer,Integer> endPosition){
        this.storedRook=(Rook) CH.getPawnByCoordinate(((King)selectedPawn).getCastlingReference().get(GL.getCastlingMoves(selectedPawn.getPlayer()).get(endPosition)).getY());
        this.storedRookPosition = this.storedRook.getActualPosition();
        this.storedList.add(new Pair<>(selectedPawn,selectedPawn.getActualPosition()));
        this.storedList.add(new Pair<>(this.storedRook,storedRookPosition));
        MEM.setNewMovements(storedList);
        GL.setCastling(this.type, (King)selectedPawn);
        GL.setNextMove(selectedPawn, endPosition);
        VIEW.reset();
        VIEW.repaintTable();
        VIEW.setPawnsIntoChessBoard(CH.getMapOfPlayers());
    }

    /**
     * check if the selected pawn is a king
     * @return true if selected pawn is a king, false othrewise
     */
    private boolean isKing(){
        return this.selectedPawn.compare(PawnTypes.King);
    }

    /**
     * check if the moves is a type of castling, if it is true, save the castling type and return true
     * @param endPosition : the position where the player click for the hypothetical casting
     * @return true is a castling, false otherwise
     */
    private boolean isCastling(final Pair<Integer,Integer> endPosition){
        final Map<Pair<Integer,Integer>,Castling> castling = new HashMap<>(GL.getCastlingMoves(GL.getActualPlayer())); 
        for(final Pair<Integer,Integer> pos : castling.keySet()){
            if(pos.equals(endPosition)){
                this.type=castling.get(pos);
                return true;
            }		
        }
        return false;
    }
    
    /**
     * the selected pawn eat the pawn on the end position. update the map of eaten pawn and set the 
     * eat in the model & view
     * @param endPosition: the position where there is a pawn who will be eaten
     */
    private void eatPawn(final Pair<Integer,Integer> endPosition){
        final Pawn eaten = CH.getPawnByCoordinate(endPosition);
        addPawnInEaten(eaten);
        PawnsRemoved.getLog().pawnsDisplay(eatenPawn);
        MEM.setNewRemoved(CH.getPawnByCoordinate(endPosition), endPosition);
        CH.removePawn(endPosition, Players.opposite());
        VIEW.removePawn(endPosition);	
    }

    /**
     * add the pawn in the map of eaten pawn
     * @param eaten: the pawn who is eaten anda will be added to the map
     */
    private void addPawnInEaten(final Pawn eaten){
        boolean isInp1 = false;
        boolean isInp2 = false;    
        final Set<Pair<Pawn,Integer>> setp1 = new HashSet<>(eatenPawn.get(Players.playerWhite));
        final Set<Pair<Pawn,Integer>> setp2 = new HashSet<>(eatenPawn.get(Players.playerBlack));
        if(GL.getActualPlayer().equals(Players.playerBlack)){
            for(final Pair<Pawn,Integer> pw : eatenPawn.get(Players.playerWhite)){
                if(eaten.compare(pw.getX().getType())){
                    isInp1 = true;
                    setp1.remove(pw);
                    setp1.add(new Pair<>(pw.getX(), pw.getY()+1));
                }
            }
            if(!isInp1){
                setp1.add(new Pair<>(eaten,1));
            }
        }else{
            for(final Pair<Pawn,Integer> pw : eatenPawn.get(Players.playerBlack)){
                if(eaten.compare(pw.getX().getType())){
                    isInp2 = true;
                    setp2.remove(pw);
                    setp2.add(new Pair<>(pw.getX(), pw.getY()+1));
                }
            }
            if(!isInp2){
                setp2.add(new Pair<>(eaten,1));
            }
        }
        eatenPawn.get(Players.playerWhite).removeAll(eatenPawn.get(Players.playerWhite));
        eatenPawn.get(Players.playerBlack).removeAll(eatenPawn.get(Players.playerBlack));
        eatenPawn.get(Players.playerWhite).addAll(setp1);
        eatenPawn.get(Players.playerBlack).addAll(setp2);
    }
    
    /**
     * change the turn, update model and view and check if there is a player 
     * under check or a checkmate
     */
    private void changeTurn(){      
        this.storedList = new ArrayList<>();
        if(GL.isOppositePlayerUnderCheck()){
            VIEW.checkMated(GL.getActualPlayer());
        }
        if(turno==0){
            absTime.startTimer();
        }else{
            absTime.stopTimer();
        }
        GL.changePlayer();	
        VIEW.actualTurn(GL.getActualPlayer());	
        this.turno++;
        if(GL.isCheckMate()){  
            VIEW.checkMate(GL.getActualPlayer());
        }
        absThreat.viewThreatPawn(CH.threatenedPawnsInCoordinates());
        this.selectedPawn = null;	
    }

    public boolean getMoves(){
        return this.seePossibleMove;
    }

    public boolean getThreat(){
        return this.seeThreatPawn;
    }

    public boolean getTime(){
        return this.timeGame;
    }
    
    /**
     * check if is a possible move
     * @param endPosition: the position where the selected pawn want to go
     * @return true if is a possible move for the selected pawn
     */
    private boolean canMove(final  Pair<Integer,Integer> endPosition){
        return (possibleMoves.contains(endPosition));	
    }
    
    /**
     * get by the model a set of possible move of the pawn in position
     * @param position: the actual position of the pawn to move
     */
    private void getPossibleMoves(final Pair<Integer,Integer> position){
        possibleMoves =  new HashSet<>(GL.getPossibleMoves(position));
    }
    
    /**
     * check if there is a selected pawn
     * @return true if a pawn is selected, false otherwise
     */
    private boolean isSelectedPawn(){
        return selectedPawn!=null;
    }

    public void newGame(){
        GL.resetGame();
        this.seePossibleMove=false;
        this.seeThreatPawn=false;
        this.timeGame=false;
        this.absMove = new MovesDisabled();
        this.absThreat = new ThreatDisabled();
        this.absTime = new TimeDisabled();
        this.storedRook=null;
        this.storedList=new ArrayList<>();
        this.storedRookPosition=null;
        this.turno = 0;
        this.selectedPawn=null;
        this.type=null;
        this.possibleMoves=null;
        this.eatenPawn=new HashMap<>();
        this.eatenPawn.put(GL.getActualPlayer(), new TreeSet<>(new PawnComparator()));
        this.eatenPawn.put(Players.opposite() , new TreeSet<>(new PawnComparator()));         
    }
    
    /**
     * get the SINGLETON object of the class controller
     * @return the Controller Object
     */
    public synchronized static ControllerImp getLog(){
        if(controller==null){
            controller = new ControllerImp();
        }
        return controller;
    }
}
