package oop.lit.model.util.permission;

import oop.lit.model.Action;

/**
 * A builder for an action used to edit players permission.
 */
public interface PermissionEditActionBuilder {

    /**
     * Adds a permission holder.
     * @param holder
     *      a permission holder.
     * @return
     *      this.
     */
    PermissionEditActionBuilder addPermissionHolder(PermissionHolder holder);

    /**
     * @return
     *      the edit action.
     *
     * @throws IllegalStateException
     *      if no permission was set or if this builder was already used.
     */
    Action build();

}