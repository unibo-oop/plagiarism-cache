package aoc.utilities;

/**
 * This interface describes an object that can be observed by another object
 * and which can notify the observer when a certain event happens.
 */
public interface ObservedObject {

    /**
     * This method attach an Observer to the current object.
     * @param observer
     *            It is the EntityObserver that will be notified
     *            when a certain event occurs in this object.
     */
    void attach(ObjectObserver observer);
}
