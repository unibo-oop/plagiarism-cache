package controller.matchsetup;

import java.util.Optional;
import application.Battleships;
import controller.Controller;
import javafx.util.Pair;
import model.enums.PlayerNumber;
import model.enums.PlayerType;
import model.gamemode.GameMode;
import model.match.players.MatchInfo;
import model.match.players.MatchInfoImpl;
import model.match.players.PlayerInfo;
import model.match.players.PlayerInfoImpl;
import view.scene.SceneName;

/**
 * Implementation of MatchInitializer interface.
 */
public final class MatchInitializerImpl implements MatchInitializer {

    private final Controller controller = Battleships.getController();
    private MatchInfo matchInfo;
    private PlayerInfo playerOne;
    private PlayerInfo playerTwo;

    @Override
    public void startNewMatch(final String username1, final Optional<String> username2, final PlayerType playerType, final GameMode gameMode) {
      createPlayers(username1, username2, playerType);
      createMatchInfo(playerOne, playerTwo, 5);
      updateMatchData(gameMode, matchInfo);
      controller.changeScene(SceneName.SHIP_DEPLOYMENT);
    }

    private void createPlayers(final String username1, final Optional<String> username2, final PlayerType playerType) {
        playerOne = new PlayerInfoImpl(username1, PlayerType.HUMAN, PlayerNumber.PLAYER_ONE);
        playerTwo = new PlayerInfoImpl(username2.orElse("AI"), playerType, PlayerNumber.PLAYER_TWO);
    }

    private void createMatchInfo(final PlayerInfo playerOne, final PlayerInfo playerTwo, final int shipNumber) {
        matchInfo = new MatchInfoImpl(new Pair<Integer, Integer>(10, 10), shipNumber, playerOne, playerTwo);
    }

    private void updateMatchData(final GameMode gameMode, final MatchInfo info) {
        controller.setGameMode(gameMode);
        controller.setCurrentPlayer(PlayerNumber.PLAYER_ONE);
        controller.setMatchInfo(info);
    }

}
