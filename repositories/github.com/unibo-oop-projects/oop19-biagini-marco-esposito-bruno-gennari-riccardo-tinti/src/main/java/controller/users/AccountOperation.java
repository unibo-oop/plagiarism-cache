package controller.users;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import model.players.PlayerManager;
import model.players.Player;
import model.Model;
import model.players.HumanPlayer;

/**
 * Represents an implementation of {@link AccountManager}.
 * This class manages all controller operations related to players.
 *
 */
public class AccountOperation implements AccountManager {

    private final FileManager system;
    private final PlayerManager modelMng;

    /**
     * Initialize the {@link FileManager} to manage player operations on disc and
     * also the {@link PlayerManager} to manage the logical operations of the players. 
     * 
     * @param model
     *          the variable associated to the Model.
     */
    public AccountOperation(final Model model) {
        this.system = new FileSystemManager();
        this.modelMng = model.setPlayerManager(initAllUsers());
        this.createArtificial(model);
    }

    private Optional<List<Player>> initAllUsers() {
        if (this.system.loadUsers().isPresent()) {
            return this.system.loadUsers();
        } else {
            return Optional.of(new LinkedList<>());
        }
    }

    private void createArtificial(final Model model) {
        if (!this.modelMng.artificialExists()) {
            try {
                this.system.saveUser(model.getArtificialPlayer());
                this.modelMng.addArtificialPlayer(model.getArtificialPlayer());
            } catch (IllegalAccountArgumentException e) {
                throw new IllegalAccountArgumentException("Error: Artificial Player not saved.");
            }
        }
    }

    @Override
    public final void createAccount(final String userName, final String password) {
        try {
            Optional<Player> p = this.modelMng.addPlayer(userName, password);
            if (p.isPresent()) {
                this.system.saveUser(p.get());
            } else {
                throw new IllegalAccountArgumentException();
            }
        } catch (IllegalAccountArgumentException e) {
            throw new IllegalAccountArgumentException("Account already exists");
        }
    }

    @Override
    public final boolean logInAccount(final String userName, final String password) {
        try {
            return this.modelMng.setLogIn(userName, password);
        } catch (IllegalAccountArgumentException e) {
            throw new IllegalAccountArgumentException("Invalid Info: Account doesn't exists");
        }
    }

    @Override
    public final void logOutAccount(final String userName) {
        try {
            if (!this.modelMng.setLogOut(userName)) {
                throw new IllegalAccountArgumentException();
            }
        } catch (IllegalAccountArgumentException e) {
            throw new IllegalAccountArgumentException("Log out Failed");
        } finally {
            this.modelMng.getPlayers().get().forEach(x -> this.system.saveUser(x));
        }
    }

    @Override
    public final Optional<List<String>> getAllUsername() {
        return Optional.of(Collections.unmodifiableList(this.modelMng.getPlayers().get()
                .stream()
                .map(x -> x.getUsername())
                .collect(Collectors.toList())));
    }

    @Override
    public final void setWinner(final String userName, final Double scoreValue) {
        try {
            if (!this.modelMng.updateWinStats(userName, scoreValue)) {
                throw new IllegalAccountArgumentException();
            }
        } catch (IllegalAccountArgumentException e) {
            throw new IllegalAccountArgumentException("Invalid Winner Infos");
        } finally {
            this.modelMng.getPlayers().get().forEach(x -> this.system.saveUser(x));
        }
    }

    @Override
    public final void setLoser(final String userName, final Double scoreValue) {
        try {
            if (!this.modelMng.updateLosStats(userName, scoreValue)) {
                throw new IllegalAccountArgumentException();
            }
        } catch (IllegalAccountArgumentException e) {
            throw new IllegalAccountArgumentException("Invalid Loser Infos");
        } finally {
            this.modelMng.getPlayers().get().forEach(x -> this.system.saveUser(x));
        }
    }

    @Override
    public final void removeAccount(final String userName, final String password) {
        try {
            if (this.modelMng.removePlayer(userName, password)) {
                this.system.removeUser(new HumanPlayer(userName, password));
            } else {
                throw new IllegalAccountArgumentException();
            }
        } catch (IllegalAccountArgumentException e) {
            throw new IllegalAccountArgumentException("Invalid info: Account already not exists");
        }
    }

    @Override
    public final Optional<Map<String, Double>> getAccountStats(final String userName) {
        return Optional.of(Collections.unmodifiableMap(this.modelMng.getPlayerStats(userName).get()));
    }

}

