package model.logic;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import utilities.Castling;
import utilities.Directions;
import model.chessboard.Chessboard;
import model.movement.Movement;
import model.utilities.pawns.King;
import utilities.Pair;
import model.utilities.pawns.Pawn;
import model.utilities.pawns.PawnTypes;
import utilities.Players;
import model.utilities.pawns.Rook;

/**
 * Class that contains the logic of movement of the king, and the all pawn in his horizon.
 * @author: Giulio Bacchilega
 */
public class KingObserver implements Observer {

    private static final Chessboard C = Chessboard.getLog();
    private static final GameLogic GL = GameLogic.getLog();
    private static final Movement M = Movement.getLog();
    private Map<Directions, Set<Pair<Integer, Integer>>> horizonSet = new HashMap<>();
    private Map<Directions, Optional<Pair<Integer, Integer>>> lastOfHorizon = new HashMap<>();
    private Map<Pawn, Set<Pair<Integer, Integer>>> checkMap = new HashMap<>();
    private Map< Pair<Integer, Integer>, Castling> castlingMoves = new HashMap<>();
    private boolean check;
    private final King king;

    /**
     * @param k the king to observe
     */
    public KingObserver(final King k) {
        this.king = k;
        this.check = false;
        this.createHorizon();
    }

    private void createHorizon() {
        this.king.getDirections().stream()
                                 .forEach(x-> Directions.get(x)
                                 .stream()
                                 .forEach(y-> this.horizonSet
                                                             .put(y, M
                                                             .getMoves(Arrays.asList(y), Optional.empty(), king.getActualPosition()))));
        this.horizonSet.keySet().stream()
                                .forEach(j-> this.horizonSet.get(j)
                                .stream()
                                .filter(z-> !C.isEmpty(z))
                                .forEach(s-> this.lastOfHorizon.put(j, Optional.of(C.getPawnByCoordinate(s).getActualPosition()))));
        this.horizonSet.put(Directions.knight_moves, new HashSet<>());
        this.updateAllDirections();
    }

    private void updateAllDirections() {
        king.getDirections().stream().forEach(x-> Directions.get(x).stream().forEach(y -> this.updateHorizonDirection(y)));
        this.horizonSet.replace(Directions.knight_moves, M.getMoves(Arrays.asList(Directions.knight_moves), Optional.empty(), king.getActualPosition()));
     }

    private void updateHorizonDirection(final Directions d) {
        horizonSet.replace(d, this.horizonSet.get(d), M.getMoves(Arrays.asList(d), Optional.empty(), king.getActualPosition()));
        Optional<Pair<Integer, Integer>> newPawnPosition = M.getMoves(Arrays.asList(d), Optional.empty(), king.getActualPosition())
                                                            .stream()
                                                            .filter(x-> !Chessboard.getLog().isEmpty(x))
                                                            .findFirst();
        this.lastOfHorizon.remove(d);
        this.lastOfHorizon.put(d, newPawnPosition);
    }

    private void updateAfterMoving(final Pair<Integer, Integer> start, final Pair<Integer, Integer> newPosition) {
        for (Directions d: this.horizonSet.keySet()) {
            if (this.horizonSet.get(d).contains(newPosition) && d.equals(Directions.knight_moves)) {
                Pawn p = C.getPawnByCoordinate(newPosition);
                if (!p.getPlayer().equals(this.king.getPlayer())) {
                    this.checkChess(p);
                }
            }
        }
        for (Optional<Directions> d: this.findDirectionToUpdate(start, newPosition)) {
              this.updateHorizonDirection(d.get());
              if (this.lastOfHorizon.get(d.get()).isPresent()) {
                  Pawn p = C.getPawnByCoordinate(this.lastOfHorizon.get(d.get()).get());
                  if (!p.getPlayer().equals(this.king.getPlayer())) {
                      this.checkChess(p);
                  }
              }
        }
        if (this.lastOfHorizon.containsKey(Directions.knight_moves)) {
            this.lastOfHorizon.remove(Directions.knight_moves);
        }
    }

    private List<Optional<Directions>> findDirectionToUpdate(final Pair<Integer, Integer> start, final Pair<Integer, Integer> next) {
        List<Optional<Directions>> list = new ArrayList<>();
        for (Directions d : this.lastOfHorizon.keySet()) {
            if (this.lastOfHorizon.get(d).isPresent()) {
                if (this.lastOfHorizon.get(d).get().equals(start)) {
                    list.add(Optional.of(d));
                }
            }
        }
        for (Directions d : this.horizonSet.keySet()) {
           if (this.horizonSet.get(d).contains(next)) {
                list.add(Optional.of(d));
           }
        }
        return list;
    };

    private Set<Pair<Integer, Integer>> getSameLineByDirection(final Directions d, final Pair<Integer, Integer> p) {
        return M.getMoves(Arrays.asList(Directions.getLineBySingleDirections(d)), Optional.empty(), p);
    }

    private Set<Pair<Integer, Integer>> checkPossibleCastling() {
        Set<Pair<Integer, Integer>> set = new HashSet<>();
        for (Castling type : Castling.values()) {
            Pair<Integer, Integer> rookPair = this.king.getCastlingReference().get(type).getY();
            if (!C.isEmpty(rookPair)) {
                if (C.getPawnByCoordinate(rookPair) instanceof Rook && C.getPawnByCoordinate(rookPair).compare(this.king.getPlayer())) {
                    if (GL.checkCastlingMoves(this.king, (Rook) C.getPawnByCoordinate(this.king.getCastlingReference().get(type).getY()), type)) {
                       Pair<Integer, Integer> toAdd = this.king.getCastlingReference().get(type).getX();
                        set.add(toAdd);
                        this.castlingMoves.put(toAdd, type);
                    }
                }
            }
        }
        return set;
    }

    @Override
    public void checkUpdates(final Pawn pawn, final Pair<Integer, Integer> start, final Pair<Integer, Integer> next) {
        if (this.check) {
            this.checkResolved();
        }
        if (pawn.compare(PawnTypes.King) && pawn.compare(this.king.getPlayer())) {
            this.updateAllDirections();
        } else {
             this.updateAfterMoving(start, next);
        }
    }

    @Override
    public Optional<Directions> getDirectionByCoordinate(final Pair<Integer, Integer> pair) {
        return this.horizonSet.keySet().stream().filter(x-> this.horizonSet.get(x).contains(pair)).findAny();
    }

    @Override
    public boolean checkChess(final Pawn pawn) {
        Set<Pair<Integer, Integer>> set = new HashSet<>();
        if (pawn.getPossibleMoves().contains(king.getActualPosition())) {
            this.check = true;
            if (pawn.compare(PawnTypes.Knight)) {
                set.add(pawn.getActualPosition());
                this.checkMap.put(pawn, set);
            } else {
                this.checkMap.put(pawn, this.horizonSet.get(this.getDirectionByCoordinate(pawn.getActualPosition()).get()));
            }
        }
        return this.check;
    }

    @Override
    public boolean isCheck(final Pawn pawn) {
        return (!pawn.getPlayer().equals(this.king.getPlayer()) && pawn.getPossibleMoves().contains(king.getActualPosition()));
    }

    @Override
    public boolean isUnderCheck() {
        return this.check;
    }

    @Override
    public Set<Pair<Integer, Integer>> getCheckPath() {
        if (this.checkMap.size() != 1) {
            return new HashSet<>();
        } else {
            return this.checkMap.values().stream().findAny().get();
        }
    }

    @Override
    public Set<Pair<Integer, Integer>> getLastHorizonCoordinate() {
        return this.lastOfHorizon.values()
                                 .stream()
                                 .filter(x-> x.isPresent()).map(y-> y.get()).collect(Collectors.toSet());
    }

    @Override
    public Set<Pair<Integer, Integer>> possibleMovesForPawn(final Pawn pawn, final Set<Pair<Integer, Integer>> possibleMoves) {
        Set<Pair<Integer, Integer>> set = new HashSet<>();
        Pair<Integer, Integer> startingPosition = pawn.getActualPosition();
        Directions ofHorizon = this.getDirectionByCoordinate(pawn.getActualPosition()).get();
        set.addAll(possibleMoves.stream().filter(x-> this.getSameLineByDirection(ofHorizon, startingPosition).contains(x)).collect(Collectors.toSet()));
        C.removePawn(pawn.getActualPosition(), GL.getActualPlayer());
        this.updateHorizonDirection(ofHorizon);
        if (this.lastOfHorizon.get(ofHorizon).isPresent()) {
            Pawn newPawnInHorizon = C.getPawnByCoordinate(this.lastOfHorizon.get(ofHorizon).get());
            if (this.isCheck(newPawnInHorizon)) {
                C.putPawn(pawn, startingPosition, GL.getActualPlayer());
                this.updateHorizonDirection(ofHorizon);
                return set;
            }
        }
        C.putPawn(pawn, startingPosition, GL.getActualPlayer());
        this.updateHorizonDirection(ofHorizon);
        return possibleMoves;
    }

    /**
     * 
     */
    public void checkResolved() {
        this.checkMap = new HashMap<>();
        this.check = false;
    }

    @Override
    public Set<Pair<Integer, Integer>> getPossibleMovesForKing(final Set<Pair<Integer, Integer>> possibleMoves) {
        Players opposite = this.king.getPlayer().equals(Players.playerBlack) ? Players.playerWhite : Players.playerBlack;
        C.removePawn(this.king.getActualPosition(), this.king.getPlayer());
        possibleMoves.removeAll(C.threatenedCoordinates(opposite));
        C.putPawn(this.king, this.king.getActualPosition(), this.king.getPlayer());
        possibleMoves.addAll(this.checkPossibleCastling());
        return possibleMoves;
    }

    @Override
    public Map<Pair<Integer, Integer>, Castling> getCastlingMoves() {
        return this.castlingMoves;
    }
}
