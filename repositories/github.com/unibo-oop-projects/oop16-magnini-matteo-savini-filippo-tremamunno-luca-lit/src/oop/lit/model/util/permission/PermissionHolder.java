package oop.lit.model.util.permission;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import oop.lit.model.PlayerModel;

/**
 * A class used to hold permissions informations about something only certain players can perform. 
 */
public class PermissionHolder implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 882509198890285858L;
    private final String label;
    private final Map<PlayerModel, Permission> permissionInfo = new HashMap<>();
    /**
     * Types of permissions.
     */
    public enum Permission {
        /**
         * If a player doesn't have permission.
         */
        NEVER("Never"),
        /**
         * If a player always has permission.
         */
        ALWAYS("Always"),
        /**
         * If a player has permission during his turn only.
         */
        TURN_ONLY("Turn only");
        private final String readableName;
        Permission(final String readableName) {
            this.readableName = readableName;
        }
        @Override
        public String toString() {
            return this.readableName;
        }
    }

    /**
     * @param label
     *      a short description of what held permission information are about.
     */
    public PermissionHolder(final String label) {
        Objects.requireNonNull(label);
        this.label = label;
    }

    /**
     * @return
     *      a short description of what held permission information are about.
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * Sets provided player's permission.
     * @param player
     *      a player.
     * @param permission
     *      new provided player's permission.
     */
    public void setPlayerPermission(final PlayerModel player, final Permission permission) {
        permissionInfo.put(player, permission);
    }

    /**
     * @param player
     *      a player.
     * @return
     *      provided player's permission. If the provided player permission is not set it will be NEVER.
     */
    public Permission getPlayerPermission(final PlayerModel player) {
        return permissionInfo.getOrDefault(player, Permission.NEVER);
    }

    /**
     * @param player
     *      a player.
     * @param isPlayerTurn
     *      if it is the provided player turn.
     * @return
     *      if the provided player can do this.
     */
    public Boolean canPlayerDo(final PlayerModel player, final boolean isPlayerTurn) {
        final Permission playerPermission = this.getPlayerPermission(player);
        return playerPermission.equals(Permission.ALWAYS) || (playerPermission.equals(Permission.TURN_ONLY) && isPlayerTurn);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((label == null) ? 0 : label.hashCode());
        return result;
    }
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PermissionHolder other = (PermissionHolder) obj;
        if (label == null) {
            if (other.label != null) {
                return false;
            }
        } else if (!label.equals(other.label)) {
            return false;
        }
        return true;
    }
}
