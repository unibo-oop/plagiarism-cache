package model.skilltree;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import model.Cost;
import model.objects.unit.Unit;
import model.objects.unit.vehicle.Vehicle;

/**
 * The ShipLevel class extends LevelAttribute with Vehicle as generic type.
 * This class manage the ship level. The level of this class determines what
 * level the ships will be from the moment they are created.
 */
public class ShipLevel extends LevelAttribute<Vehicle> {

    private static final String ATTRIBUTE_NAME = "Ship level increase: unlock ";
    private static final String WHITELIST_PACKAGE = "model.objects.unit.vehicle";
    private static final String CLASSES_IMPLEMENTING = "model.objects.unit.vehicle.Vehicle";
    private static final String FILTER_START = "model.objects.unit.vehicle.Level";
    private static final String FILTER_END = "Ship";
    private static final int INITIAL_LEVEL = 0;

    /**
     * ShipLevel constructor.
     */
    public ShipLevel() {
        super(INITIAL_LEVEL, WHITELIST_PACKAGE, CLASSES_IMPLEMENTING, FILTER_START, FILTER_END);
    }

    /** {@inheritDoc} **/
    @Override
    protected Optional<Vehicle> getObject(final int level) {
        return getShipWithPassenger(level, Optional.empty());
    }

    private Optional<Vehicle> getShipWithPassenger(final int level, final Optional<Unit> passenger) {
        try {
            return Optional.of((Vehicle) Class.forName(getObjectClasses().get(level)).getConstructor(Optional.class)
                    .newInstance(passenger));
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException | ClassNotFoundException | IndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }

    /** {@inheritDoc} **/
    @Override
    public Cost getCost() {
        return this.getNewObject().get().getUnlockCost();
    }

    /** {@inheritDoc} **/
    @Override
    public String getAttributeName() {
        return ATTRIBUTE_NAME + "level " + (getCurrentValue() + 2) + " ship";
    }

    /**
     * This method can be use to take the ship of the current level.
     * 
     * @param passenger is the passenger of the ship.
     * @return the actual level ship.
     */
    public Vehicle getActualShip(final Unit passenger) {
        return getShipWithPassenger(getCurrentValue(), Optional.of(passenger)).get();
    }

}
