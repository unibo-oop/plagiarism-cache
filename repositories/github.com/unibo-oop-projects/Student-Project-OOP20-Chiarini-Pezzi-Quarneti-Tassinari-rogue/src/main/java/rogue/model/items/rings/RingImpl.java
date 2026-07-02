package rogue.model.items.rings;

import rogue.model.creature.Player;
import rogue.model.items.EquipmentImpl.Memento;

public final class RingImpl implements Ring {

    private Memento memento;
    private final RingType ring;

    public RingImpl(final RingType ring) {
        this.ring = ring;
    }

    @Override
    public boolean use(final Player player) {
        if (player.getEquipment().getRing().isEmpty()) {
            /**
             * Save the actual equipment state in order to 
             * restore it when the ring will be detached.
             */
            this.memento = player.getEquipment().save();
            this.ring.getConsumer().accept(player.getEquipment());
            player.getEquipment().attachRing(this);
            return true;
        }
        return false;
    }

    @Override
    public void stopUsing(final Player player) {
        /**
         * Calling the detachRing method it will be restored 
         * the previous equipment state.
         */
        player.getEquipment().detachRing(this.memento);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ring == null) ? 0 : ring.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RingImpl)) {
            return false;
        }
        final RingImpl other = (RingImpl) obj;
        return ring == other.ring;
    }

    @Override
    public String toString() {
        return "RingImpl [ring=" + ring + "]";
    }

}
