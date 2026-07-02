package model.objects.unit.vehicle;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import model.BasicCostImpl;
import model.Cost;
import model.objects.unit.GenericUnitType;
import model.objects.unit.Unit;
import model.objects.unit.UnitType;

/**
 * The ShipImpl class extends VehicleImpl abstract class and implements the
 * getPassengerTypesAccept abstract method. ShipImpl represent a ship, so it is
 * a water vehicle and it can accept all unit type, except VEHICLE, as
 * passenger. The ship must have a passenger.
 *
 */
public class ShipImpl extends VehicleImpl {

    /**
     * ShipImpl constructor.
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
     * @param vehicleUnlockCost  vehicle's unlock cost. If empty is already unlock.
     * @param passenger          is the vehicle's passenger.
     */
    public ShipImpl(final String name, final int strength, final int initialHp, final int moveRange,
            final int attackRange, final int possibleAttack, final boolean canMoveAfterAttack, final boolean moveOnKill,
            final Cost vehicleUnlockCost, final Optional<Unit> passenger) {
        super(name, strength, initialHp, moveRange, attackRange, possibleAttack, canMoveAfterAttack, moveOnKill,
                new BasicCostImpl(), vehicleUnlockCost, passenger, UnitType.WATER_VEHICLE);
    }

    /** {@inheritDoc} **/
    @Override
    public List<UnitType> getPassengerTypesAccept() {
        return Arrays.asList(UnitType.values()).stream()
                .filter(u -> !u.getGenericUnitType().equals(GenericUnitType.VEHICLE)).collect(Collectors.toList());
    }
}
