package view;

import java.util.List;
import java.util.Map;

import model.interfaces.Exercise;
import model.interfaces.User;

/**
 * 
 * @author Ste
 *
 */
public interface WorkoutTable {

	/**
	 * 
	 * @param list of exercises to show in the tree table view
	 */
	void setExerciseList(User user, Map<Exercise, List<Exercise>> m);

}
