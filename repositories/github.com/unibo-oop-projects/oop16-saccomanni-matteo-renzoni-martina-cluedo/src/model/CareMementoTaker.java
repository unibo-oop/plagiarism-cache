package model;

/**
 * Singleton that keeps the current ModelMemento.
 */
public final class CareMementoTaker {

    private ModelMemento memento;
    private static final CareMementoTaker SINGLETON = new CareMementoTaker();

    private CareMementoTaker() {
    }

    /**
     * Returns the instance of CareMementoTaker.
     * 
     * @return the instance of CareMementoTaker
     */
    public static CareMementoTaker getInstance() {
        return SINGLETON;
    }

    /**
     * Sets the ModelMemento to save.
     * 
     * @param memento
     *            ModelMemento to save
     */
    public void setMemento(final ModelMemento memento) {
        this.memento = memento;
    }

    /**
     * Returns the ModelMemento saved.
     * 
     * @return the ModelMemento saved
     */
    public ModelMemento getMemento() {
        return this.memento;
    }
}