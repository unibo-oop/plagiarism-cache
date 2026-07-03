package model.chessboard;






import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import utilities.Pair;
import model.logic.GameLogic;
import model.utilities.pawns.Generator;
import model.utilities.pawns.Pawn;
import model.utilities.pawns.PawnGenerator;
import model.utilities.pawns.PawnTypes;
import utilities.Players;
import model.utilities.pawns.SimplePawn;

/**
 * @author : Giulio Bacchilega.
 */
public final class Chessboard implements ChessboardModel {
    private static Chessboard chessboard;
    private static final int LIMIT = 7;
    private static final GameLogic GL = GameLogic.getLog();
    private final Generator pg = new PawnGenerator();
    private Map<Players, List<Pawn>> playersMap = new HashMap<>();
    private Map<Pair<Integer, Integer>, Pawn> pawnsMap = new HashMap<>();

    private Chessboard() {
        this.setNewGame();
    }

    /**
     * @param p , the given player
     * @return Integer
     */
    public Integer getLimit(final Players p) {
        if (p.equals(Players.playerBlack)) {
            return 0;
        }
        return LIMIT;
     }

    @Override
    public Map<Players, List<Pawn>> getMapOfPlayers() {
        return this.playersMap;
    }

    @Override
    public Pawn getPawnByCoordinate(final Pair<Integer, Integer> pair) {
        return this.pawnsMap.get(pair);
    }

    @Override
    public boolean isEmpty(final Pair<Integer, Integer> pair) {
        return !this.pawnsMap.containsKey(pair);
    }

    @Override
    public boolean isIncluded(final Pair<Integer, Integer> pair) {
        return (pair.getX() <= LIMIT && pair.getX() >= 0 && pair.getY() >= 0 && pair.getY() <= LIMIT);
    }

    @Override
    public Set<Pair<Integer, Integer>> threatenedCoordinates(final Players p) {
        Set<Pair<Integer, Integer>> set =  new HashSet<>();
        for (Pawn pawn : this.playersMap.get(p)) {
            if (pawn.compare(PawnTypes.SimplePawn)) {
                SimplePawn t = (SimplePawn) pawn;
                set.addAll(t.getDirectionsToEat());
            } else {
                set.addAll(pawn.getPossibleMoves());
            }
        }
        return set;
    }

    @Override
    public Set<Pair<Integer, Integer>> threatenedPawnsInCoordinates() {
        Set<Pair<Integer, Integer>> set = this.threatenedCoordinates(Players.opposite());
        return set.stream()
                .filter(x-> !this.isEmpty(x))
                .filter(y-> this.getPawnByCoordinate(y).compare(GL.getActualPlayer()))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Pair<Integer, Integer>> getPlayersCoordinates() {
        return this.pawnsMap.keySet();
    }

    @Override
    public void setMove(final Pawn pawn, final Pair<Integer, Integer> next) {
        this.pawnsMap.remove(pawn.getActualPosition());
        this.pawnsMap.put(next, pawn);
        pawn.setNewPosition(next);
    }

    @Override
    public void putPawn(final Pawn pawn, final Pair<Integer, Integer> position, final Players p) {
        this.pawnsMap.put(position, pawn);
        this.playersMap.get(p).add(pawn);
    }

    @Override
    public void removePawn(final Pair<Integer, Integer> pair, final Players p) {
        this.playersMap.get(p).remove(this.getPawnByCoordinate(pair));
        this.pawnsMap.remove(pair);
    }

    /**
    * @return the singleton of this class 
    */
    public static Chessboard getLog() {
        if (chessboard == null) {
            chessboard = new Chessboard();
        }
        return chessboard;
    }

    @Override
    public void pawnInLimit(final SimplePawn sp, final PawnTypes type) {
        Pair<Integer, Integer> actual = sp.getActualPosition();
        this.removePawn(actual, sp.getPlayer());
        this.putPawn(pg.build(type, sp.getPlayer(), actual), actual, sp.getPlayer());
    }

    @Override
    public Pawn getKingByPlayer(final Players p) {
        return this.playersMap.get(p).stream()
                                     .filter(x-> x.compare(PawnTypes.King))
                                     .findFirst().get();
    }

    @Override
    public void setNewGame() {
        Initializator.getLog().restoreChessboard();
        this.playersMap = Initializator.getLog().getPlayersMap();
        this.pawnsMap = Initializator.getLog().getPawnsMap();
    }
}
