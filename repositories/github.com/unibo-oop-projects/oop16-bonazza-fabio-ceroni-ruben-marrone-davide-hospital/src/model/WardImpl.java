package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import util.Enums.WardTypes;


/**
 * 
 * Ward the Patient is assigned to.
 *
 */
public final class WardImpl implements Ward, Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 6537322559767491122L;
    private final WardTypes name;
    private final int numRooms;
    private final Map<Integer, Patient> rooms = new  HashMap<>();

    /**
     * Constructor, creates an empty ward given name and number of rooms.
     * @param name String
     * @param numRooms int
     * @throws IllegalArgumentException e
     */
    public WardImpl(final String name, final int numRooms) throws IllegalArgumentException {
        super();
        if (numRooms <= 0) {
            throw new IllegalArgumentException("Invalid Input");
        }
        this.name = WardTypes.getFromString(name);
        this.numRooms = numRooms;
        for (int i = 1; i <= numRooms; i++) {
            rooms.put(i, null);
        }
    }
    @Override
    public Map<Integer, Patient> getOccupiedRooms() {
        return rooms.entrySet().stream()
                .filter(map -> map.getValue() != null)
                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));
    }
    @Override
    public String getName() {
        return this.name.getNome();

    }
    @Override
    public int getNumRooms() {
        return this.numRooms;
    }

    @Override
    public void enterPatient(final Patient p) throws IllegalStateException {
        if (this.getOccupiedRooms().size() == numRooms) {
            throw new IllegalStateException("Ward is full");
        }
        rooms.entrySet()
            .stream()
            .filter(r -> r.getValue() == null)
            .limit(1)
            .forEach(r -> r.setValue(p));
    }
    @Override
    public void removePatient(final Patient p) throws IllegalStateException {
        if (!rooms.containsValue(p)) {
            throw new IllegalStateException("Patient isn't present");
        }
        rooms.entrySet()
        .stream()
        .filter(r -> r.getValue() == p)
        .limit(1)
        .forEach(r -> r.setValue(null));
    }
    @Override
    public List<Patient> getPatients() {
        return this.getOccupiedRooms()
                .values()
                .stream()
                .collect(Collectors.toList());
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WardImpl other = (WardImpl) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return this.getName() + ", Occupied Rooms : " + this.getOccupiedRooms().size() + "/" + this.numRooms;
    }
}
