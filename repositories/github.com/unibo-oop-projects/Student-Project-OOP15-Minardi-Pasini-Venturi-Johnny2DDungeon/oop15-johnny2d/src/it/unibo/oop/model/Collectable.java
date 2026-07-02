package it.unibo.oop.model;

/**
 * Interface that represents an item placed in the map that can be collected
 * only by the {@link MainCharacter}.
 */
public interface Collectable {

    /**
     * In case of collision with the {@link MainCharacter} this
     * {@link Collectable} gets collected.
     * 
     * @param bonusCollector The {@link MainCharacter} that collected the {@link Collectable}
     */
    void collect(MainCharacter bonusCollector);

}
