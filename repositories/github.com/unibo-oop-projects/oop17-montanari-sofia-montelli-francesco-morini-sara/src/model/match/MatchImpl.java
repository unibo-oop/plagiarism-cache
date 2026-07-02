package model.match;


import model.game_data.GameData;
import model.ship.Ship;

/**
 * Basic implementation of {@link Match}.
 */
public final class MatchImpl implements Match {

    /**
     * first player.
     */
    private final GameData gameDataP1;
    /**
     * second player.
     */
    private final GameData gameDataP2;
    /**
     * size of the grid of the battle ground.
     */
    private final int gridSize;
    /**
     * field that determine if it's first player turn or it's second player turn. 
     */
    private boolean firstPlayerTurn;

    /**
     * 
     * @param player1 is the first player data.
     * @param player2 is the second player data.
     * @param gridSize is the size of the grid.
     */
    public MatchImpl(final GameData player1, final GameData player2, final int gridSize) {
        gameDataP1 = player1;
        gameDataP2 = player2;
        this.gridSize = gridSize;
        firstPlayerTurn = true;
    }

    @Override
    public int getGridSize() {
        return gridSize;
    }

    @Override
    public GameData getGameDataP1() {
        return gameDataP1;
    }

    @Override
    public GameData getGameDataP2() {
        return gameDataP2;
    }


    @Override
    public boolean isFirstPlayerTurn() {
        return firstPlayerTurn;
    }


    @Override
    public void changeTurn() {
        firstPlayerTurn = !firstPlayerTurn;
    }

    /**
     * Checks if the provided battle ground as a fully destroyed navy.
     * @param gd the game data to check
     * @return the result of the described operation
     */
    private boolean checkNavyDestruction(final GameData gd) {
        return gd.getBattleGround().get().getNavy().getShips().stream()
                                                              .map(s -> s.getStatus())
                                                              .allMatch(s -> s.equals(Ship.Status.SUNK));
    }

    @Override
    public boolean isEnded() {
        return checkNavyDestruction(gameDataP1)
               ||
               checkNavyDestruction(gameDataP2);
    }


    @Override
    public GameData getPlayer() {
        return firstPlayerTurn ? gameDataP1 : gameDataP2;
    }


    @Override
    public GameData getEnemy() {
        return firstPlayerTurn ? gameDataP2 : gameDataP1;
    }

}
