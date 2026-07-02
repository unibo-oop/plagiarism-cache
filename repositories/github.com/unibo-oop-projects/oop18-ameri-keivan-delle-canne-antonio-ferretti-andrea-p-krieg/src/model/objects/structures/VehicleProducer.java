package model.objects.structures;
import model.objects.unit.Unit;
import model.objects.unit.vehicle.Vehicle;

/**
 * Models a Structure responsible of the production of vehicles.
 */
public interface VehicleProducer extends Structure {
    /**
     * @param unit the unit to put in the vehicle
     * 
     * @return the vehicle produced with the Unit inside
     */
    Vehicle getVehicle(Unit unit);
}
