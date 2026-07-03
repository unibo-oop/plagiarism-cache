package model;

/**
 * 
 * Singleton that manages the actual saved {@link model.ModelMemento}.
 *
 */
public final class CareMementoTaker {

    private ModelMemento memento;
    private static final CareMementoTaker SINGLETON = new CareMementoTaker();

    private CareMementoTaker() {
    }

    /**
     * 
     * @return The instance of CareMementoTaker.
     */
    public static CareMementoTaker getInstance() {
        return SINGLETON;
    }

    /**
     * 
     * @param memento
     *            {@link model.ModelMemento} to save.
     */
    public void addMemento(final ModelMemento memento) {
        this.memento = memento;
    }

    /**
     * 
     * @return The {@link model.ModelMemento} stored.
     */
    public ModelMemento getMemento() {
        return this.memento;
    }
}
