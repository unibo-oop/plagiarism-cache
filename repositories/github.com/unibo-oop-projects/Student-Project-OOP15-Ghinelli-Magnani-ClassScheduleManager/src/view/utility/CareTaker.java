package view.utility;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Class which manage the Memento objects for the pattern Memento.
 *
 */

public class CareTaker {
    
    private List<Memento> mementoList = new ArrayList<>();
    
    /**
     * Add a memento to the list.
     * @param state The memento object which is added.
     */

    public void add(final Memento state) {
       this.mementoList.add(state);
    }
    
    /**
     * Gives a determined memento taken from the internal list of the class.
     * @param index The index of the memento object inside the list.
     * @return The memento object.
     */

    public Memento get(final int index) {
       return this.mementoList.get(index);
    }
    
    /**
     * Gives the size of the list of memento objects.
     * @return An integer which represents the size.
     */
    
    public int mementoListSize() {
        return this.mementoList.size();
    }
    
    /**
     * Delete a memento from the list.
     * @param index The index of the memento object in the list.
     */
    
    public void removeUsedMemento(final int index) {
        this.mementoList.remove(index);
    }
    
    /**
     * Deletes all the memento object from the list.
     */
    
    public void cleanMementoList() {
        this.mementoList = new ArrayList<>();
    }
}
