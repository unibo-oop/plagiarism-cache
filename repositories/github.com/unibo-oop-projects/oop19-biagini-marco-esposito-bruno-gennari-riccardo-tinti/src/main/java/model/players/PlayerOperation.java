package model.players;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import model.stats.LoserStatsCalculator;
import model.stats.Statistics;
import model.stats.WinnerStatsCalculator;

/**
 * This class is an implementation of {@link PlayerManager} that manages 
 * all operations on players.
 *
 */
public final class PlayerOperation implements PlayerManager {

    private final Optional<List<Player>> players;
    private Optional<Statistics> stats;

    public PlayerOperation(final Optional<List<Player>> initUsers) {
        this.players = initUsers;
        this.stats = Optional.empty();
    }

    private boolean usernameExists(final String userName) {
        return (this.players.get().stream()
                .map(x -> x.getUsername())
                .filter(x -> x.equalsIgnoreCase(userName))
                .count() == 1) ? true : false;
    }

    private boolean infoAreValid(final String userName, final String password) {
        return (usernameExists(userName)
                && (this.players.get().stream()
                        .filter(x -> x.getUsername().equalsIgnoreCase(userName))
                        .map(x -> x.getPassword())
                        .filter(x -> x.equals(password))
                        .count() == 1)) ? true : false;
    }

    @Override
    public Optional<Player> addPlayer(final String userName, final String password) {
        if (!usernameExists(userName)) {
            Player p = new HumanPlayer(userName, password);
            this.players.get().add(p);
            return Optional.ofNullable(p);
        }
        return Optional.empty();
    }

    @Override
    public boolean removePlayer(final String userName, final String password) {
        boolean canRemove = false;
        if (infoAreValid(userName, password)) {
            for (Player player : this.players.get()) {
                if (player instanceof HumanPlayer && player.getUsername().equals(userName)) {
                    this.players.get().remove(player);
                    canRemove = true;
                }
            }
        }
        return canRemove;
    }

    @Override
    public boolean setLogIn(final String userName, final String password) {
        if (infoAreValid(userName, password)) {
            this.players.get().forEach(x -> {
                if (x.getUsername().equalsIgnoreCase(userName)) {
                    x.setLogin(true);
                }
            });
            return true;
        }
        return false;
    }

    @Override
    public boolean setLogOut(final String userName) {
        if (usernameExists(userName)) {
            this.players.get().forEach(x -> {
                if (x.getUsername().equalsIgnoreCase(userName)) {
                    x.setLogin(false);
                }
            });
            return true;
        }
        return false;
    }

    @Override
    public Optional<List<Player>> getPlayers() {
        Optional<List<Player>> humanList = Optional.ofNullable(new LinkedList<>());
        for (Player player : this.players.get()) {
            if (player instanceof HumanPlayer) {
                humanList.get().add(player);
            }
        }
        return Optional.of(Collections.unmodifiableList(humanList.get()));
    }

    @Override
    public boolean updateWinStats(final String userName, final Double score) {
        if (usernameExists(userName)) {
            this.players.get().forEach(x -> {
                if (x instanceof HumanPlayer && x.getUsername().equalsIgnoreCase(userName)) {
                    this.stats = Optional.of(new WinnerStatsCalculator((HumanPlayer) x, score));
                } else if (x instanceof ArtificialPlayer && x.getUsername().equalsIgnoreCase(userName)) {
                    this.stats = Optional.of(new WinnerStatsCalculator((ArtificialPlayer) x, score));
                }
            });
            this.stats.get().basicStats();
            return true;
        }
        return false;
    }

    @Override
    public boolean updateLosStats(final String userName, final Double score) {
        if (usernameExists(userName)) {
            this.players.get().forEach(x -> {
                if (x instanceof HumanPlayer && x.getUsername().equalsIgnoreCase(userName)) {
                    this.stats = Optional.of(new LoserStatsCalculator((HumanPlayer) x, score));
                } else if (x instanceof ArtificialPlayer && x.getUsername().equalsIgnoreCase(userName)) {
                    this.stats = Optional.of(new LoserStatsCalculator((ArtificialPlayer) x, score));
                }
            });
            this.stats.get().basicStats();
            return true;
        }
        return false;
    }

    @Override
    public boolean artificialExists() {
        return this.players.get().stream().filter(x -> x instanceof ArtificialPlayer)
                .count() == 1 ? true : false;
    }

    @Override
    public void addArtificialPlayer(final ArtificialPlayer playerAI) {
        this.players.get().add(playerAI);
    }

    @Override
    public Optional<Map<String, Double>> getPlayerStats(final String userName) {
        if (this.usernameExists(userName)) {
            for (final Player pl : this.players.get()) {
                if (pl.getUsername().equals(userName)) {
                    return Optional.of(pl.getStatistics());
                }
            }
        }
        return Optional.empty();
    }

}
