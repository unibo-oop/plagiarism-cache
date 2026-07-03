package model.observers;

/**
 * Generic Observer interface of Observer Pattern
 */
public interface Observer<T> {
	public void update(final T t);
}
