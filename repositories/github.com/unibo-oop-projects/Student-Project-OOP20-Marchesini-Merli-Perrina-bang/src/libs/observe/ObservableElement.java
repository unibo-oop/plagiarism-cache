package libs.observe;

import java.util.ArrayList;
import java.util.List;

/**
 * A class implementing an element that can be observed by an Observer.
 * 
 * @author Mattia Marchesini
 *
 * @param <E> the type of the observable element
 */
public class ObservableElement<E> implements Observable {

	private E element = null;
	private List<Observer> observers = new ArrayList<>();

	public ObservableElement(final E element) {
		this.element = element;
	}

	public ObservableElement() {
	}

	/**
	 * Returns the observed element.
	 * 
	 * @return element
	 */
	public E get() {
		return element;
	}

	/**
	 * Sets observed element and notifies the observables.
	 * 
	 * @param element
	 */
	public void set(final E element) {
		this.element = element;
		this.notifyObservers();
	}

	/**
	 * Sets observed elements without notifying the observables.
	 * 
	 * @param element
	 */
	public void setNoNotify(final E element) {
		this.element = element;
	}

	/**
	 * Returns true if the element is null
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return this.element == null;
	}

	@Override
	public void addObserver(Observer observer) {
		this.observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		this.observers.remove(observer);
	}

	@Override
	public void removeAllObservers() {
		this.observers.clear();
	}

	@Override
	public void notifyObservers() {
		this.observers.forEach(ob -> ob.update());
	}
}
