package model.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import model.board.GameBoard;
import model.players.EmptyPlayerSingleton;
import model.players.Player;
import model.players.PlayerCircularQueue;
import model.utils.Boat;

/**
 * This is the implementation of PLayerStatistic.
 */
public class PlayersStatisticImpl implements PlayersStatistic {

    @Override
    public final List<String> getPlayersSituation(final GameBoard tileLine, final Boat<Player> boat,
            final PlayerCircularQueue pCQ) {
        final ArrayList<String> playerSituationsMessages = new ArrayList<String>();

        boat.stream().sorted((o1, o2) -> o1.getPlayerName().compareTo(o2.getPlayerName()))
                .forEach(x -> playerSituationsMessages.add("Player: " + x.getPlayerName()
                        + "\nColor:" + x.getPlayerColor().toString().toLowerCase(Locale.ENGLISH)
                        + "\nis carring: " + x.getPlayerTreasures().stream().count()
                        + " treasures\nIt has a score amount of : " + x.getPlayerScore()
                        + "\nnow is on boat"));

        tileLine.getTilesLine().values().stream().filter(x -> x.getPlayer().isPresent()).map(x -> x.getPlayer().get())
                .forEach(x -> playerSituationsMessages.add("Player: " + x.getPlayerName()
                        + "\nColor: " + x.getPlayerColor().toString().toLowerCase(Locale.ENGLISH)
                        + "\nis carring: " + x.getPlayerTreasures().stream().count()
                        + " treasures\nIt has a score amount of: " + x.getPlayerScore()
                        + "\nNow is diving direction: "
                        + x.getDirection().toString().replaceAll("_", " ").toLowerCase(Locale.ENGLISH)));

        pCQ.stream().forEach(x -> {

            if (!boat.contains(x) && !tileLine.getTilesLine().values().stream()
                    .map(e -> e.getPlayer()
                            .orElse(EmptyPlayerSingleton.getInstance()))
                    .collect(Collectors.toList()).contains(x)) {
                playerSituationsMessages.add("Player: " + x.getPlayerName()
                        + "\nColor: " + x.getPlayerColor().toString().toLowerCase(Locale.ENGLISH)
                        + "\nis carring: " + x.getPlayerTreasures().stream().count()
                        + " treasures\nIt has a score amount of: " + x.getPlayerScore()
                        + "\nJust launch from boat diving direction: "
                        + x.getDirection().toString().replaceAll("_", " ").toLowerCase(Locale.ENGLISH));
            }

        });

        return playerSituationsMessages;
    }

}
