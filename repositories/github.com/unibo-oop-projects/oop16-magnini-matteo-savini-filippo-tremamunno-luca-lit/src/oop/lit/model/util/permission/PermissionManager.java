package oop.lit.model.util.permission;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import oop.lit.model.Action;
import oop.lit.model.PlayerModel;

/**
 * A class used to keep track of various PermissionHolders and get a PermissionEdit action to modify them.
 */
public class PermissionManager implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -3964374890577068611L;
    private final List<PermissionHolder> addedPermission = new ArrayList<>();
    /**
     * Adds a permission holder.
     * @param holder
     *      the permission holder to be added.
     */
    public void addPermission(final PermissionHolder holder) {
        this.addedPermission.add(holder);
    }

    /**
     * @param player
     *      a player
     * @return
     *      an action used to modify permission for the provided player.
     */
    public Action getPermissionEditAction(final PlayerModel player) {
        return this.getPermissionEditActionBuilder(player).build();
    }

    /**
     * @param player
     *      a player
     * @return
     *      a PermissionEditActionBuilder for the provided player.
     */
    protected PermissionEditActionBuilder getPermissionEditActionBuilder(final PlayerModel player) {
        final PermissionEditActionBuilder builder = new PermissionEditActionBuilderImpl(player);
        this.addedPermission.forEach(builder::addPermissionHolder);
        return builder;
    }
}
