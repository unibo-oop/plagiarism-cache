package model.observers;

/**
 * Generic Observable interface of Observer Pattern
 */
public interface Observable<T> {
	
	public void addObserver(final Observer<T> component);
	public void removeObserver(final Observer<T> component);
	
	public void notifyObservers();
}
