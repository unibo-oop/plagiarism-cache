package model.objects.structures;

import java.util.List;

import model.managers.SkillTreeManager;
import model.objects.AbstractGameObject;
import model.objects.terrains.Terrain;
import model.objects.unit.Unit;
import model.objects.unit.vehicle.Vehicle;
import model.skilltree.ShipLevel;
import model.skilltree.SkillTreeAttribute;

/**
 * The VehicleProducer that produces ships.
 */
public class Harbor extends AbstractGameObject implements VehicleProducer {

    private static final String TERRAIN = "Water";
    private final SkillTreeManager skillTreeManager;

    /**
     * @param skillTreeManager the skilltreemanager for this harbor to create the correct lvl ship
     */
    public Harbor(final SkillTreeManager skillTreeManager) {
        this.skillTreeManager = skillTreeManager;
    }

    /** {@inheritDoc} **/
    @Override
    public Vehicle getVehicle(final Unit unit) {
        final List<SkillTreeAttribute> attributes = this.skillTreeManager.getAllPlayerAttributes(unit.getOwner().get());
        if (attributes.stream().filter(a -> a instanceof ShipLevel).findFirst().isPresent()) {
            return ((ShipLevel) (attributes.stream().filter(a -> a instanceof ShipLevel).findFirst().get()))
                    .getActualShip(unit);
        } else {
            throw new IllegalStateException();
        }
    }

    /** {@inheritDoc} **/
    @Override
    public boolean canBeBuilt(final Terrain terrain) {
        return terrain.getId().equals(TERRAIN);
    }

    /** {@inheritDoc} **/
    @Override
    public String getDescription() {
        return "Harbor\n" + super.getOwnerName();
    }
}
