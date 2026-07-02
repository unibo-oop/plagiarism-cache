package model.objects;

import java.util.Optional;

import model.player.Player;

/**
 * implements the way the object id is retrieved. (NOTA: non ha metodi astratti)
 */
public abstract class AbstractGameObject implements GameObject {

    private static final String NEUTRAL = "Neutral";
    private Optional<Player> owner = Optional.empty();

    /** {@inheritDoc} **/
    @Override
    public Optional<Player> getOwner() {
        return this.owner;
    }

    /** {@inheritDoc} **/
    @Override
    public void set(final Optional<Player> player) {
        this.owner = player;
    }

    /** {@inheritDoc} **/
    @Override
    public void setOwner(final Player player) {
        set(Optional.of(player));
    }

    /** {@inheritDoc} **/
    @Override
    public void removeOwner() {
        set(Optional.empty());
    }

    /** {@inheritDoc} **/
    @Override
    public String getOwnerName() {
        return this.owner.isPresent() ? owner.get().getName() : NEUTRAL;
    }

}
