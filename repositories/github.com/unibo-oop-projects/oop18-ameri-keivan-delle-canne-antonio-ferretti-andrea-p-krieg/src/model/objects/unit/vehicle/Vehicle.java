package model.objects.unit.vehicle;

import java.util.List;
import java.util.Optional;

import model.objects.unit.Unit;
import model.objects.unit.UnitType;

/**
 * The Vehicle interface extends Unit interface. It represent a vehicle. A
 * vehicle is a GameObject and a particular Unit that can have a passenger that
 * is a Unit. A vehicle has a list of passenger types accept as passenger.
 */
public interface Vehicle extends Unit {

    /**
     * This method could be used to get the passenger of a vehicle.
     * 
     * @return the passenger if it is present.
     */
    Optional<Unit> getPassenger();

    /**
     * This method could be used to get the list of the Unit passenger types accept
     * as passenger.
     * 
     * @return the list of passenger types accept.
     */
    List<UnitType> getPassengerTypesAccept();
}
