package aboidsim.controller;

import java.util.List;

import aboidsim.util.InputInfo;

/**
 * This interface is necessary to transform any input coming form the view into
 * an action performed by the model.
 *
 */
@FunctionalInterface
interface InputResolver {
	/**
	 * This method translate any input in an action performed by the model.
	 *
	 * @param input
	 *            an input
	 */
	void resolveInput(InputInfo input);

	/**
	 * Template method. Default method. We resolve any Input contained in a
	 * list.
	 *
	 * @param inputList
	 *            A list of inputs
	 */
	default void resolveInputList(final List<InputInfo> inputList) {
		if (!inputList.isEmpty()) {
			inputList.forEach(i -> this.resolveInput(i));
		}
	}
}
