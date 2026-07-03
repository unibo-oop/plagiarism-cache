package model.chessboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.utilities.pawns.Generator;
import model.utilities.pawns.Pawn;
import model.utilities.pawns.PawnGenerator;
import model.utilities.pawns.PawnTypes;
import utilities.Pair;
import utilities.Players;
import utilities.StartingCoordinate;

/**
 * 
 * @author : Giulio Bacchilega.
 *
 */
public final class Initializator {

    private static Initializator initializator;
    private static final int LIMIT = 7;
    private final Generator pg = new PawnGenerator();
    private Map<Players, List<Pawn>> playersMap = new HashMap<>();
    private Map<Pair<Integer, Integer>, Pawn> pawnsMap = new HashMap<>();

    private Initializator() { }

    private void buildPawnsCoordinateMap() {
        Map<PawnTypes, List<Pair<Integer, Integer>>> pawnsWhiteMap = new HashMap<>();
        StartingCoordinate.getMap().keySet().forEach(x-> pawnsWhiteMap.put(x, StartingCoordinate.get(x)));
        this.setPlayersMap(pawnsWhiteMap);
    }

    private void setPlayersMap(final Map<PawnTypes, List<Pair<Integer, Integer>>> pawnsWhiteMap) {
        List<Pawn> player1 = new ArrayList<>();
        List<Pawn> player2 = new ArrayList<>();
        for (PawnTypes p : pawnsWhiteMap.keySet()) {
            pawnsWhiteMap.get(p).stream()
                                 .map(x-> pg.build(p, Players.playerWhite, x))
                                 .forEach(e-> player1.add(e));
        }
        player2 = this.getSpecular(player1);
        this.playersMap.put(Players.playerWhite, player1);
        this.playersMap.put(Players.playerBlack, player2); 
    }

    private List<Pawn> getSpecular(final List<Pawn> list) {
        List<Pawn> specular = new ArrayList<>();
        for (Pawn p : list) {
            Pair<Integer, Integer> specularPosition = new Pair<>(p.getActualPosition().getX(), LIMIT - p.getActualPosition().getY());
            Pawn pawn = this.pg.build(p.getType(), Players.playerBlack, specularPosition);
            specular.add(pawn);
        }
        return specular;
    }

    private void buildPawnsMap() {
        this.playersMap.values().forEach(x-> x.stream().forEach(y-> this.pawnsMap.put(y.getActualPosition(), y)));
    }

    /**
     * Return the playersMap for Chessboard.
     * @return Map<Players, List<Pawn>>
     */
    public Map<Players, List<Pawn>> getPlayersMap() {
        return this.playersMap;
    }

    /**
     * Return the pawn map for Chessboard.
     * @return Map<Pair<Integer, Integer>>
     */
    public Map<Pair<Integer, Integer>, Pawn> getPawnsMap() {
        return this.pawnsMap;
    }

    /**
     * Restore settings.
     */
    public void restoreChessboard() {
        this.playersMap = new HashMap<>();
        this.pawnsMap = new HashMap<>();
        this.buildPawnsCoordinateMap();
        this.buildPawnsMap();
    }

    /**
     * Return the Singleton of this class.
     * @return Initializator
     */
    public static synchronized Initializator getLog() {
        if (initializator == null) {
            initializator = new Initializator();
        }
        return initializator;
    }
}
