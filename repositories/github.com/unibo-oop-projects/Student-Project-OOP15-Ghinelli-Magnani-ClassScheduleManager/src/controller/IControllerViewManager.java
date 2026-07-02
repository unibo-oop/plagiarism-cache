package controller;

import java.util.List;

import model.interfaces.ISchedulesModel;

/**
 * 
 * Interface responsible for adding the courses and lessons to the model.
 *
 */

public interface IControllerViewManager {
    
    /**
     * Method which add to the model a course providing all the information needed.
     * @param values The list of informations.
     * @param model The model of the program.
     */
    
    void addCourse(final List<String> values, final ISchedulesModel model);
    
    /**
     * Method which add to the model a lesson providing all the information needed.
     * @param values The list of informations.
     * @param model The model of the program.
     */
    
    void addLesson(final List<String> values, final ISchedulesModel model);
}
