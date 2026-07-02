package libs.observe;

import java.util.ArrayList;
import java.util.List;

/**
 * A class implementing an observable element.
 * 
 * @author Mattia Marchesini
 *
 */
public class SimpleObservable implements Observable {

	List<Observer> observers = new ArrayList<>();

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
