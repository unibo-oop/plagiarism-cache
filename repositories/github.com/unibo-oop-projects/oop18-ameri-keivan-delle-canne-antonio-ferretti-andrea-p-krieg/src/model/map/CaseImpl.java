package model.map;

import java.util.Optional;

import model.objects.structures.OwnableStructure;
import model.objects.structures.Structure;
import model.objects.structures.VehicleProducer;
import model.objects.terrains.Terrain;
import model.objects.unit.Unit;
import model.objects.unit.vehicle.Vehicle;

/**
 * package protected.
 */
class CaseImpl implements Case {

    private final Terrain terrain;
    private Optional<Unit> unit = Optional.empty();
    private Optional<Structure> structure = Optional.empty();

    CaseImpl(final Terrain terrain) {
        this.terrain = terrain;
    }

    /** {@inheritDoc} **/
    @Override
    public Optional<Unit> getUnit() {
        return this.unit;
    }

    /** {@inheritDoc} **/
    @Override
    public boolean canStep(final Unit unit) {
        if (this.getUnit().isPresent()) {
            return false;
        }
        if (!(this.getStructure().isPresent() && this.getStructure().get() instanceof VehicleProducer)
                && (!unit.getAbilities().containsAll(this.getTerrain().getRequiredAbilities()))) {
            return false;
        }
        return true;
    }

    /** {@inheritDoc} **/
    @Override
    public Terrain getTerrain() {
        return this.terrain;
    }

    /** {@inheritDoc} **/
    @Override
    public void setStructure(final Structure structure) {
        if (this.structure.isPresent()) {
            throw new IllegalStateException("Structure " + structure.getDescription() + " already present");
        }
        if (!structure.canBeBuilt(this.terrain)) {
            throw new IllegalArgumentException(
                    "Can't build " + structure.getDescription() + " in " + terrain.getDescription());
        }
        this.structure = Optional.of(structure);
    }

    /** {@inheritDoc} **/
    @Override
    public Optional<Structure> getStructure() {
        return this.structure;
    }

    /** {@inheritDoc} **/
    @Override
    public void removeStructure() {
        if (!getUnit().isPresent()) {
            throw new IllegalStateException("can't remove a structure if it isn't o the case");
        }
        this.structure = Optional.empty();
    }

    // stuff to do when a unit is set on the case:
    // 1.conquer the structure if conquerable
    // 2.step on a vehicle if there is a vehicle producer
    private void onStep(final Unit unit) {
        if (!this.unit.isPresent()) {
            throw new IllegalStateException();
        }
        if (!this.unit.get().equals(unit)) {
            throw new IllegalArgumentException();
        }
        if (this.structure.isPresent()) {
            if (this.structure.get() instanceof VehicleProducer) {
                if (unit instanceof Vehicle) {
                    this.unit = Optional.of(
                            ((VehicleProducer) this.structure.get()).getVehicle(((Vehicle) unit).getPassenger().get()));
                } else {
                    this.unit = Optional.of(((VehicleProducer) this.structure.get()).getVehicle(unit));
                }
            } else if (this.structure.get() instanceof OwnableStructure) {
                final OwnableStructure oStructure = ((OwnableStructure) this.structure.get());
                if (!this.structure.get().getOwner().isPresent()
                        || !this.structure.get().getOwner().equals(unit.getOwner())) {
                    oStructure.initiateConquer(unit.getOwner().get());
                }
            }
        }
    }

    /** {@inheritDoc} **/
    @Override
    public void setUnit(final Unit unit) {
        if (this.unit.isPresent()) {
            throw new IllegalStateException();
        }
        Unit validUnit = unit;
        if (!canStep(unit)) {
            if (unit instanceof Vehicle && canStep(((Vehicle) unit).getPassenger().get())) { // TODO controllare che non
                                                                                             // sia empty
                validUnit = ((Vehicle) unit).getPassenger().get(); // TODO controllare che non sia empty
            } else {
                throw new IllegalArgumentException();
            }
        }
        this.unit = Optional.of(validUnit);
        onStep(validUnit);
    }

    /** {@inheritDoc} **/
    @Override
    public void removeUnit() {
        if (!getUnit().isPresent()) {
            throw new IllegalStateException("can't remove a structure if it isn't o the case");
        }
        this.unit = Optional.empty();
    }

}
