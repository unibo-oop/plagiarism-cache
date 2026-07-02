package model.armory.charger;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import model.armory.munition.FactoryMunition;
import model.armory.munition.Munition;

/**
 * Implementation of the {@link Charger} interface representing a drum magazine.
 * <p>
 * Manages a fixed-capacity collection of ammunition,
 * supports extracting ammunition and updating positions of contained munitions.
 */
public class Drum implements Charger {

    private List<Munition> munitions = new ArrayList<>();
    private int capacity;
    private Pair<Double, Double> pos;
    private FactoryMunition factory = new FactoryMunition();

    /**
     * Constructs a new Drum charger with specified capacity and position.
     * 
     * @param capacity the maximum number of munitions this drum can hold
     * @param pos      the initial position of the drum
     */
    public Drum(final int capacity, final Pair<Double, Double> pos) {
        this.capacity = capacity;
        this.pos = pos;
        this.fillMagazine();
    }

    /**
     * Fills the magazine to its full capacity with new munitions.
     * Each munition is created at the current position.
     */
    private void fillMagazine() {
        while (this.munitions.size() < capacity) {
            Munition newMunition = factory.createParabellum(this.pos);
            this.munitions.add(newMunition);
        }
    }

    /**
     * Extracts one unit of ammunition from the drum.
     * If the drum is empty, it is automatically refilled before extraction.
     * 
     * @return the extracted {@link Munition}
     */
    @Override
    public Munition extractAmmunition() {
        if (this.munitions.isEmpty()) {
            this.fillMagazine();
        }
        return munitions.remove(munitions.size() - 1);
    }

    /**
     * Updates the position of the drum and all contained munitions
     * that have not yet been fired.
     * 
     * @param pos the new position as a pair (x, y)
     */
    @Override
    public void setPos(final Pair<Double, Double> pos) {
        this.pos = pos;
        for (Munition m : munitions) {
            if (!m.isShoot()) {
                m.setPos(pos);
            }
        }
    }

    /**
     * Returns the current number of ammunition units loaded in the drum.
     * 
     * @return the count of munitions remaining
     */
    @Override
    public int getCurrentLoad() {
        return this.munitions.size();
    }

}
