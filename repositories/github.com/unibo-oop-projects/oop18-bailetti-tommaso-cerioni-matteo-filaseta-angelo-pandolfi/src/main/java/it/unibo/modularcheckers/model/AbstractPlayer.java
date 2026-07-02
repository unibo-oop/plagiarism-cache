package it.unibo.modularcheckers.model;

/** 
 */
public abstract class AbstractPlayer implements Player {

    private final String playerName;

    /** Sole constructor.
     * @param playerName The name to give to the player.
     */
    public AbstractPlayer(final String playerName) {
        this.playerName = playerName;
    }

    /** Get the Player name.
     * @return the Player name. 
     */
    public final String getName() {
        return playerName;
    }

    /**{@inheritDoc}
     */
    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((playerName == null) ? 0 : playerName.hashCode());
        return result;
    }

    /**{@inheritDoc}
     */
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractPlayer other = (AbstractPlayer) obj;
        if (playerName == null) {
            if (other.playerName != null) {
                return false;
            }
        } else if (!playerName.equals(other.playerName)) {
            return false;
        }
        return true;
    }

}
