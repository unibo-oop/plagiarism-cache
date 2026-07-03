package oop.lit.model.elements;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import oop.lit.model.Action;
import oop.lit.model.PlayerModel;
import oop.lit.model.util.permission.ActionsManager;

/**
 * A GameElement managing who can perform actions thorugh an ActionsManager.
 * Subclasses should add actions to the ActionsManager.
 * Permission information about actions are not serialized.
 */
public abstract class PermissionsGameElement extends AbstractGameElement {
    /**
     * 
     */
    private static final long serialVersionUID = 1766808084011744877L;
    private transient ActionsManager am = new ActionsManager();
    /**
     * @param name
     *      the name of the element;
     */
    protected PermissionsGameElement(final Optional<String> name) {
        super(name);
    }

    /**
     * @return
     *      this element ActionsManager
     */
    protected ActionsManager getActionsManager() {
        return this.am;
    }
    @Override
    public List<Action> getActions(final PlayerModel playingPlayer, final List<? extends PlayerModel> turnPlayers) {
        return this.am.getActions(playingPlayer, turnPlayers.contains(playingPlayer)).stream()
                .filter(Action::canBePerformed).collect(Collectors.toList());
    }

    private void readObject(final ObjectInputStream in) throws ClassNotFoundException, IOException {
        in.defaultReadObject();
        this.am = new ActionsManager();
    }
}
