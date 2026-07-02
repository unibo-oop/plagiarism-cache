package rogue.model.events;

import rogue.model.items.Equipment;

/**
 * A class representing an equipment change event.
 */
public final class EquipmentEvent implements Event {

    private final Equipment equipment;

    /**
     * Creates a new EquipmentEvent.
     * @param equipment
     *          the equipment which changed
     */
    public EquipmentEvent(final Equipment equipment) {
        this.equipment = equipment;
    }

    /**
     * @return the event related {@link Equipment} 
     */
    public Equipment getEquipment() {
        return this.equipment;
    }

    @Override
    public String toString() {
        return this.equipment.toString();
    }

}
