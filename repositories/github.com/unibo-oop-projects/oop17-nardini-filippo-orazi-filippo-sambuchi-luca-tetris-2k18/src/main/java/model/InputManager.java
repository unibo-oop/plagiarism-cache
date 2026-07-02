package main.java.model;

import java.util.List;
import java.util.function.Consumer;

/**
 * Functional interface for an input resolver.
 */
public interface InputManager<T> {

	/**
	 * Produces a consumer for a given input.
	 */
	Consumer<T> resolve(Input i);
	
	default void resolveAll(final List<Input> inputs, T t) {
		inputs.stream()
		.map(this::resolve)
		.forEach((a) -> a.accept(t));
	}
}
