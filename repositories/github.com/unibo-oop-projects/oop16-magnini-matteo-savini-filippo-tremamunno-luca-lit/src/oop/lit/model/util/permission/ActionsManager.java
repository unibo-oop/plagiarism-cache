package oop.lit.model.util.permission;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import oop.lit.model.Action;
import oop.lit.model.PlayerModel;
import oop.lit.model.util.permission.PermissionHolder.Permission;

/**
 * A permission manager supporting also actions permission information.
 * Permission information about actions are not serialized.
 */
public class ActionsManager extends PermissionManager {
    /**
     * 
     */
    private static final long serialVersionUID = 170203790665819768L;
    private static final String NO_ACTION_ERR = "Provided action was not added to this";
    private transient Map<Action, Pair<PermissionHolder, Integer>> permissionMap = new HashMap<>(); //uso l'intero per tenere l'ordine di aggiunta.
    private transient int curr; // = 0

    /**
     * Adds an action to the manager, if it wasn't already added.
     * Creates a new PermissionHolder for the provided action.
     * @param action
     *      an action to be added.
     * @return
     *      if the action was added.
     */
    public boolean addAction(final Action action) {
        return this.addAction(action, new PermissionHolder("(A) " + action.getLabel()));
    }
    /**
     * Adds an action to the manager, if it wasn't already added.
     * @param action
     *      an action to be added.
     * @param holder
     *      the PermissionHolder to be used for the provided Action.
     * @return
     *      if the action was added.
     */
    public boolean addAction(final Action action, final PermissionHolder holder) {
        if (this.permissionMap.containsKey(action)) {
            return false;
        }
        this.permissionMap.put(action, ImmutablePair.of(holder, this.curr++));
        return true;
    }

    /**
     * Sets player permission to perform the provided action. 
     * @param action
     *      an action that was added in this manager.
     * @param player
     *      a player.
     * @param permission
     *      the new player permission about the provided action.
     *
     * @throws IllegalArgumentException
     *      if the action was not previously added to this
     */
    public void setPermission(final Action action, final PlayerModel player, final Permission permission) {
        final Pair<PermissionHolder, Integer> value = this.permissionMap.get(action);
        if (value == null) {
            throw new IllegalArgumentException(NO_ACTION_ERR);
        }
        value.getLeft().setPlayerPermission(player, permission);
    }

    /**
     * @return
     *      All actions that were added to this.
     */
    public List<Action> getAllActions() {
        final List<Action> res = new ArrayList<>(this.permissionMap.keySet());
        res.sort(Comparator.comparing(action -> this.permissionMap.get(action).getRight()));
        return res;
    }

    /**
     * @param player
     *      a player.
     * @param isPlayerTurn
     *      if it is the provided player turn.
     * @return
     *      the actions the provided player can perform (according to set permission).
     */
    public List<Action> getActions(final PlayerModel player, final boolean isPlayerTurn) {
        final List<Action> res = this.permissionMap.entrySet().stream()
                .filter(entry -> entry.getValue().getLeft().canPlayerDo(player, isPlayerTurn))
                .map(Entry::getKey)
                .collect(Collectors.toList());
        res.sort(Comparator.comparing(action -> this.permissionMap.get(action).getRight()));
        return res;
    }

    /**
     * @param action
     *      an action.
     * @param player
     *      a player.
     * @param isPlayerTurn
     *      if it is the provided player turn.
     * @return
     *      if the provided player can perform the provided action (according to set permission).
     */
    public boolean canPlayerPerform(final Action action, final PlayerModel player, final boolean isPlayerTurn) {
        final Pair<PermissionHolder, Integer> pair = permissionMap.get(action);
        if (pair != null) {
            return pair.getLeft().canPlayerDo(player, isPlayerTurn);
        }
        return false;
    }

    /**
     * Gets the PermissionHolder associated with the provided action. 
     * @param action
     *      an action that was added in this manager.
     * @return
     *      the PermissionHolder associated with the provided action.
     *
     * @throws IllegalArgumentException
     *      if the action was not previously added to this
     */
    public PermissionHolder getActionPermissionHolder(final Action action) {
        final Pair<PermissionHolder, Integer> value = this.permissionMap.get(action);
        if (value == null) {
            throw new IllegalArgumentException(NO_ACTION_ERR);
        }
        return value.getLeft();
    }

    @Override
    protected PermissionEditActionBuilder getPermissionEditActionBuilder(final PlayerModel player) {
        final PermissionEditActionBuilder res = super.getPermissionEditActionBuilder(player);
        permissionMap.values().forEach(pair -> res.addPermissionHolder(pair.getLeft()));
        return res;
    }

    private void readObject(final ObjectInputStream in) throws ClassNotFoundException, IOException {
        in.defaultReadObject();
        this.permissionMap = new HashMap<>();
    }
}
