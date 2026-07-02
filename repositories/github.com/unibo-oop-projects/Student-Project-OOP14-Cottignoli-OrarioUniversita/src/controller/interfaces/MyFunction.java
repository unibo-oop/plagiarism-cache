package controller.interfaces;

import model.Days;

/**
 * Functional interface that takes in input the semester, the day and the hour and it gives back a generic type. 
 * It is used in the class {@link ViewController#setViewType(view.IView, Object)} in order to deal with certain 
 * type of view of the model.
 * 
 * @author Lorenzo Cottignoli
 *
 * @param <Y> Return’s generic type.
 */
public interface MyFunction<Y> {

	/**
	 * @param sem Semester.
	 * @param d Day.
	 * @param hour Hour.
	 * @return generic type.
	 */
	Y apply(int sem, Days d, int hour);
}
