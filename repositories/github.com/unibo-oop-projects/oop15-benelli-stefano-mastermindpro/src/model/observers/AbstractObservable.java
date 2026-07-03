package model.observers;

import java.util.ArrayList;
import java.util.List;

/**
 * Basic Implementation of Generic Observable
 */
public abstract class AbstractObservable<T> implements Observable<T> {
	
	private final List<Observer<T>> observers;
	
	protected AbstractObservable() {
		observers = new ArrayList<Observer<T>>();
	}
	
	@Override
	public void addObserver(final Observer<T> component) {
		observers.add(component);
	}
}
