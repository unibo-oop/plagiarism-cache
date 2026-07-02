package model;

import libs.observe.ObservableElement;

/**
 * Implements an observable that notifies the observers once and then removes
 * all of them. It's ideal for elements that need to be observed just for the
 * duration of a single turn.
 * 
 * @author Mattia Marchesini
 *
 * @param <E> the type of the observable element
 */
public class TurnObservable<E> extends ObservableElement<E> {

	@Override
	public void notifyObservers() {
		super.notifyObservers();
		this.removeAllObservers();
	}
}
