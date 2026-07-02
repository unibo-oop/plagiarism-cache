package view.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.Controller;
import model.Court;
import model.Day;
import model.Hour;
import model.Year;

/**
 * Utility class which prepares in the right way the information needed for the dialogs of the view.
 *
 */

public final class DialogManager {
    
    private DialogManager() {
        
    }
    
    /**
     * Method which provides to the view the values of the model to create the add dialog of a lesson.
     * @return The map containing a pair of a string and boolean containing the name of the information,
     *  if the JComboBox is writable or not, and the list of values.
     */
    
    public static Map<Pair<String, Boolean>, List<String>> getLessonsValues() {
        final Map<Pair<String, Boolean>, List<String>> returnValue = new HashMap<>();
        final List<String> days = new ArrayList<>();
        final List<String> hours = new ArrayList<>();
        for (int  i = 0; i < Day.values().length; i++) {
            days.add(Day.values()[i].getDay());
        }
        for (int  i = 0; i < Hour.values().length; i++) {
            hours.add(Hour.values()[i].getHour());
        }
        returnValue.put(new Pair<>("Name", false), Controller.getController().getCourseName());
        returnValue.put(new Pair<>("Prof.", true), Controller.getController().getProfessors());
        returnValue.put(new Pair<>("Duration", false), Arrays.asList("1", "2", "3", "4", "5"));
        returnValue.put(new Pair<>("Hour", false), hours);
        returnValue.put(new Pair<>("Day", false), days);
        returnValue.put(new Pair<>("Class", false), Controller.getController().getClassrooms());
        returnValue.put(new Pair<>("Semester", false), Arrays.asList("1", "2"));
        return returnValue;
    }
    
    /**
     * Method which provides to the view the values of the model to create the add dialog of courses.
     * @return The map containing a pair of a string and boolean containing the name of the information,
     *  if the JComboBox is writable or not, and the list of values.
     */
    
    public static Map<Pair<String, Boolean>, List<String>> getCoursesValues() {
        final Map<Pair<String, Boolean>, List<String>> returnValue = new HashMap<>();
        final List<String> courts = new ArrayList<>();
        final List<String> years = new ArrayList<>();
        for (int i = 0; i < Court.values().length; i++) {
            courts.add(Court.values()[i].getDef());
        }
        for (int i = 0; i < Year.values().length; i++) {
            years.add(Year.values()[i].getYear());
        }
        returnValue.put(new Pair<>("Teaching", true), Controller.getController().getCourseName());
        returnValue.put(new Pair<>("Year", false), years);
        returnValue.put(new Pair<>("Court", false), courts);
        return returnValue;
    }
    
    /**
     * Method which provides to the view the professors of the model for the delete dialog.
     * @return The map containing the string as the name of the information and the list of professors.
     */
    
    public static Map<String, List<String>> getProfessorValues() {
        final Map<String, List<String>> returnValue = new HashMap<>();
        returnValue.put("Select the professor to delete", Controller.getController().getProfessors());
        return returnValue;
    }
    
    /**
     * Method which provides to the view the teachings(courses) of the model for the delete dialog.
     * @return The map containing the string as the name of the information and the list of teachings.
     */
    
    public static Map<String, List<String>> getTeachingValues() {
        final Map<String, List<String>> returnValue = new HashMap<>();
        returnValue.put("Select the teaching to delete", Controller.getController().getCourseName());
        return returnValue;
    }

}
