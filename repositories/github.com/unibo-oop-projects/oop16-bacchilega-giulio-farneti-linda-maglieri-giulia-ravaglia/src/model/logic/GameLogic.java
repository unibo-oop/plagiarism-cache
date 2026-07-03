package model.logic;

import java.util.HashMap;


import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import utilities.Castling;
import model.chessboard.Chessboard;
import model.memento.MementoImpl;
import model.utilities.pawns.King;
import utilities.Pair;
import model.utilities.pawns.Pawn;
import model.utilities.pawns.PawnTypes;
import utilities.Players;
import model.utilities.pawns.Rook;
import model.utilities.pawns.SimplePawn;

/**
 * GameLogic class.
 * @author: Giulio Bacchilega
 */
public final class GameLogic implements Logic {

    private Players currentPlayer;
    private static final Chessboard C = Chessboard.getLog();
    private static GameLogic log;
    private Map<Players, KingObserver> kingObservers = new HashMap<>();

    private GameLogic() {
        this.currentPlayer = Players.playerWhite;
    }

    @Override
    public Players getActualPlayer() {
        return this.currentPlayer;
    }

    @Override
    public void changePlayer() {
        this.currentPlayer = Players.opposite();
    }

    @Override
    public void setKingObservers() {
        C.getMapOfPlayers().keySet().stream()
                                    .forEach(x-> this.kingObservers.put(x, new KingObserver((King) C.getKingByPlayer(x))));

    }

    private Set<Pair<Integer, Integer>> getAlliedPawnInSet(final Set<Pair<Integer, Integer>> set, final Players p) {
        return set.stream()
                    .filter(x-> !C.isEmpty(x)).collect(Collectors.toSet())
                    .stream()
                    .filter(x-> C.getPawnByCoordinate(x).getPlayer().equals(p))
                    .collect(Collectors.toSet());
    }

    @Override
    public Set<Pair<Integer, Integer>> getPossibleMoves(final Pair<Integer, Integer> pair) {
        Set<Pair<Integer, Integer>> set = new HashSet<>();
        Pawn t = C.getPawnByCoordinate(pair);
        set = t.getPossibleMoves(); 
        if (this.kingObservers.get(this.currentPlayer).isUnderCheck()) {
            set = set.stream().filter(x-> this.kingObservers.get(this.currentPlayer).getCheckPath().contains(x)).collect(Collectors.toSet());
            if (t.compare(PawnTypes.King)) {
                set = this.kingObservers.get(t.getPlayer()).getPossibleMovesForKing(t.getPossibleMoves());
            }
        }
        if (t.compare(PawnTypes.King)) {
            if (t.compare(this.getActualPlayer())) {
                set = this.kingObservers.get(this.currentPlayer).getPossibleMovesForKing(set);
            }
        }
        set.removeAll(this.getAlliedPawnInSet(set, t.getPlayer()));
        if (this.kingObservers.get(this.currentPlayer).getLastHorizonCoordinate().contains(pair)) {
            set = this.kingObservers.get(this.currentPlayer).possibleMovesForPawn(t, set);
        }
      return set;
    }

    @Override
    public void setNextMove(final Pawn pawn, final Pair<Integer, Integer> next) {
        Pair<Integer, Integer> startingPosition = pawn.getActualPosition();
        C.setMove(pawn, next);
        this.notifyObservers(pawn, startingPosition, next);
        /*if (pawn.compare(PawnTypes.SimplePawn)) {
            SimplePawn sp = (SimplePawn) pawn;
            if (sp.checkChange()) {
                C.pawnInLimit(sp, PawnTypes.Queen);
                this.notifyObservers(C.getPawnByCoordinate(next), startingPosition, next);
            }
        }*/
    }

    @Override
    public void setPawnInLimit(final Pawn pawn, final PawnTypes type, final Pair<Integer, Integer> next) {
        Pair<Integer, Integer> startingPosition = pawn.getActualPosition();
        C.setMove(pawn, next);
        C.pawnInLimit((SimplePawn) pawn, type);
        this.notifyObservers(C.getPawnByCoordinate(next), startingPosition, next);
    }

    @Override
    public boolean checkCastlingMoves(final King k, final Rook r, final Castling type) {
        Set <Pair<Integer, Integer>> set = new HashSet<>();
        set.addAll(k.getCastlingMap().get(type));
        set.addAll(r.getCastlingPath());
        set.remove(k.getActualPosition());
        if (!k.isMoved() && !r.isMoved()) {
            if (this.checkFreePath(set) && this.checkChessPath(k.getCastlingMap().get(type))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkFreePath(final Set<Pair<Integer, Integer>> set) {
        boolean isAllowed = true;
        for (Pair<Integer, Integer> pair : set) {
            if (!C.isEmpty(pair)) {
                isAllowed = false;
            }
        }
        return isAllowed;
    }

    @Override
    public boolean checkChessPath(final Set<Pair<Integer, Integer>> set) {
        boolean isAllowed = true;
        Set<Pair<Integer, Integer>> threatened = C.threatenedCoordinates(Players.opposite());
        set.add(C.getKingByPlayer(this.currentPlayer).getActualPosition());
        for (Pair<Integer, Integer> pair : set) {
            if (threatened.contains(pair)) {
                isAllowed = false;
            }
        }
        return isAllowed;
    }

    @Override
    public Map<Pair<Integer, Integer>, Castling> getCastlingMoves(final Players p) {
        return this.kingObservers.get(p).getCastlingMoves();
    }

    @Override
    public void setCastling(final Castling type, final King k) {
        Rook p = (Rook) C.getPawnByCoordinate(k.getCastlingReference().get(type).getY());
        C.setMove(p, p.getCastlingPosition());
    }

    @Override
    public boolean isOppositePlayerUnderCheck() {
        return this.kingObservers.get(Players.opposite()).isUnderCheck();
    }

    @Override
    public boolean isCheckMate() {
        Set<Pair<Integer, Integer>> possibleMovesUnderCheck = new HashSet<>();
        List<Pair<Integer, Integer>> list = C.getMapOfPlayers().get(this.currentPlayer).stream().map(x-> x.getActualPosition()).collect(Collectors.toList());
        for (Pair<Integer, Integer> p : list) {
            possibleMovesUnderCheck.addAll(this.getPossibleMoves(p));
        }
        return possibleMovesUnderCheck.isEmpty();
    }

    @Override
    public void notifyObservers(final Pawn p, final Pair<Integer, Integer> first, final Pair<Integer, Integer> second) {
        this.kingObservers.values().forEach(x-> x.checkUpdates(p, first, second));
    }

    @Override
    public void putRemovedPawn(final Pawn removed, final Pair<Integer, Integer> pair) {
        C.putPawn(removed, pair, removed.getPlayer());
        this.notifyObservers(removed, pair, pair);
    }

    @Override
    public void removeAddedPawn(final Pawn added, final Pair<Integer, Integer> pair) {
        C.removePawn(added.getActualPosition(), added.getPlayer());
        this.notifyObservers(added, pair, pair);
    }

    @Override
    public void restoreMoves(final Pawn pawn, final Pair<Integer, Integer> next) {
        pawn.decrementMoves();
        this.setNextMove(pawn, next);
    }

    @Override
    public void resetGame() {
        C.setNewGame();
        this.currentPlayer = Players.playerWhite;
        this.setKingObservers();
        MementoImpl.getLog().resetMemento();
    }

    /**
     * @return singleton of this class
     */
    public static synchronized GameLogic getLog() {
        if (log == null) {
            log = new GameLogic();
        }
        return log;
    }
}
