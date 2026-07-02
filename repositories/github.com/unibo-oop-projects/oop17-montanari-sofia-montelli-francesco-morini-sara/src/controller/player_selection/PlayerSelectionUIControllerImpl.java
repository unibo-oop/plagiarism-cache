package controller.player_selection;

import java.util.List;
import java.util.Optional;

import controller.AbstractController;
import model.player.Player;
import view.select_player.SelectionPlayerUIImpl;

/**
 * Implementation of {@link PlayerSelectionUIController}.
 */
public final class PlayerSelectionUIControllerImpl extends AbstractController implements PlayerSelectionUIController {
    /**
     * View associated with this controller.
     */
    private final SelectionPlayerUIImpl view;
    /**
     * List of saved players.
     */
    private List<String> list;
    /**
     * Name of the player chosen to play.
     */
    private Optional<String> name;
    /**
     * Password entered to play.
     */
    private Optional<String> password;
    /**
     * @param view the view connect to this controller
     */
    public PlayerSelectionUIControllerImpl(final SelectionPlayerUIImpl view) {
        super();
        this.view = view;
    }

    @Override
    public List<String> getPlayerNameList() {
        list = getModel().getPlayerNameListOrdered();
        removeUselessPlayer();
        return list;
    }

    private void removeUselessPlayer() {
        final Optional<Player> player = getModel().getGameData1().getPlayer();
        list.remove(player.isPresent() ? player.get().getName() : "");
    }

    @Override
    public void addPlayer(final String playerName) {
        if (list.contains(playerName)) {
            view.errorMessage("The name \"" + playerName + "\" is already existing");
        } else {
            try {
                getMasterController().addNewPlayer(playerName);
                view.update();
            } catch (IllegalArgumentException e) {
                view.errorMessage("It is not possible to create a player with the name \"Anonymous\"");
            }
        }
    }

    @Override
    public void setName(final String name) {
        this.name = Optional.of(name);
    }

    @Override
    public void setPassword(final String password) {
        this.password = Optional.of(password);
    }
    @Override
    public void confirmData() throws IllegalStateException {
        if (name.isPresent() && password.isPresent()) {
            getMasterController().confirmPlayerData(getModel().getPlayer(name.get()), password.get());
        } else {
            throw new IllegalStateException();
        }
    }
}
