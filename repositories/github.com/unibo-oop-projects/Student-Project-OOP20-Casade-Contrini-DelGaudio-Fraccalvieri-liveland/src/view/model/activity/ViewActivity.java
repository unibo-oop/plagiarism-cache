package view.model.activity;

import java.util.Optional;

/**
 * ViewActivity defines objects in which the fields set by the user are saved,
 * regardless from the {@link ActivityType}: this is why ViewActivityImpl objects
 * have optional fields that may not be present (Optional). 
 * The class implementing this interface acts as a "bridge" from the view to the 
 * model, making it easier to move data inserted by the user to the
 * ActivityEnvironmentImpl where Fair and Profit activities are effectively built.
 */
public interface ViewActivity {

    /**
     * @return the activity type
     */
    ActivityType getActivityType();

    /**
     * @return the capacity, which is present if and only if 
     * activity type is Fair/Babyfair
     */
    Optional<Integer> getCapacity();

    /**
     * @return the minimum price chosen,  which is present 
     * if and only if activity type is Profit
     */
    Optional<Integer> getMinPrice();

    /**
     * @return the maximum price chosen,  which is present 
     * if and only if activity type is Profit
     */
    Optional<Integer> getMaxPrice();

    /**
     * @return activity name
     */
    String getName();

}
