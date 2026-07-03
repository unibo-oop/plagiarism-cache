package view.interfaces;

import model.observers.Observer;
import view.navigator.Navigable;

public interface View<T> extends Navigable, Observer<T> {
	public void fillModel(T t) ;
}
