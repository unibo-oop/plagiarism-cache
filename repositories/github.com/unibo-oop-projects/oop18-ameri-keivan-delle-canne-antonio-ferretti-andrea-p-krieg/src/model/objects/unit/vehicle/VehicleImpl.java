package model.objects.unit.vehicle;

import java.util.List;
import java.util.Optional;

import model.Cost;
import model.objects.unit.Unit;
import model.objects.unit.UnitImpl;
import model.objects.unit.UnitType;

/**
 * The VehicleImpl is an abstract class that implements Vehicle and extends
 * UnitImpl. A vehicle has attributes similar to the generic unit.
 * This class has the getPassengerTypesAccept abstract method.
 */
public abstract class VehicleImpl extends UnitImpl implements Vehicle {

    private final Optional<Unit> passenger;

    /**
     * VehicleImpl constructor.
     * 
     * @param name               vehicle's name.
     * @param strength           vehicle's strength.
     * @param initialHp          vehicle's initial life.
     * @param moveRange          vehicle's movement range.
     * @param attackRange        vehicle's attack range.
     * @param possibleAttack     vehicle's possible attack in a turn.
     * @param canMoveAfterAttack if the vehicle can move after he has attacked someone.
     * @param moveOnKill         if the vehicle kills the opponent, he moves in the
     *                           opponent's cell.
     * @param vehicleCost        vehicle's cost.
     * @param vehicleUnlockCost  vehicle's unlock cost. If empty is already unlock.
     * @param passenger          vehicle's passenger.
     * @param vehicleType        vehicle's type.
     */
    public VehicleImpl(final String name, final int strength, final int initialHp, final int moveRange,
            final int attackRange, final int possibleAttack, final boolean canMoveAfterAttack, final boolean moveOnKill,
            final Cost vehicleCost, final Cost vehicleUnlockCost, final Optional<Unit> passenger,
            final UnitType vehicleType) {
        super(passenger.isPresent() ? passenger.get().getOwner() : Optional.empty(), name, strength, initialHp,
                moveRange, attackRange, possibleAttack, canMoveAfterAttack, moveOnKill, vehicleCost, vehicleUnlockCost,
                vehicleType);
        this.passenger = passenger;
    }

    /** {@inheritDoc} **/
    @Override
    public Optional<Unit> getPassenger() {
        return this.passenger;
    }

    /** {@inheritDoc} **/
    @Override
    public abstract List<UnitType> getPassengerTypesAccept();

    /** {@inheritDoc} **/
    @Override
    public String getDescription() {
        return super.getDescription() + passengerInfo();
    }

    private String passengerInfo() {
        return passenger.isPresent() ? "\nPassenger: " + passenger.get().getName() : "";
    }
}
