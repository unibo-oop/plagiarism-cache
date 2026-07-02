package view.utility;

import java.util.ArrayList; 
import java.util.List;

import controller.Controller;
import model.Day;
import model.Hour;
import model.interfaces.ILesson;

/**
 * 
 * Utility class used to prepare the list of ILesson for the table or to change the values of an object ILesson
 * used to place it in the table.
 *
 */

public final class ObjectManager {
    
    private static final int COMPLETE = 0;
    private static final int PARTIAL = 1;
    private static final int EMPTY = 9;
    
    private ObjectManager() {
        
    }

    /**
     * Static method which returns a list of object which is combined with object ILesson and strings.
     * @param searchType Table's type of view used to understand which list needs to be returned.
     * @param list List of objects ILesson.
     * @return The list of objects used by the table.
     */
    
    public static List<List<Object>> getStruct(final int searchType, final List<ILesson> list) {
        final List<List<Object>> base = new ArrayList<>();
        if (searchType == ObjectManager.COMPLETE) {
            int check = 0;
            int check2 = 0;
            for (int i = 0; i < Day.values().length * Controller.getController().getClassrooms().size() + Day.values().length; i++) {
                 base.add(new ArrayList<>());
                 if (i == (Day.values().length * Controller.getController().getClassrooms().size() + Day.values().length) / Day.values().length * check) {
                     base.get(i).add(Day.values()[check].getDay());
                     for (int y = 1; y <= Hour.values().length; y++) {
                         base.get(i).add(Hour.values()[y - 1].getHour());
                     }
                     check++;
                 } else {
                     base.get(i).add(Controller.getController().getClassrooms().get(check2));
                     for (int y = 1; y < ObjectManager.EMPTY; y++) {
                         base.get(i).add("");
                     }
                     if (check2 == Controller.getController().getClassrooms().size() - 1) {
                         check2 = 0;
                     } else {
                         check2++;
                     }
                 }
            }
            
            if (list == null) {
                return base;
            }
            for (final ILesson lesson : list) {
                final String day = lesson.getDay().getDay();
                final String hour = lesson.getHour().getHour();
                final String classRoom = lesson.getClassRoom();
                int dayVal = 0;
                int hourVal = 0;
                int classVal = 0;
                int check3 = 0;
                for (int i = 0; i < Day.values().length; i++) {
                    if (day.equals(Day.values()[i].getDay())) {
                        dayVal = i * Day.values().length + check3 * 2 + 1;
                    }
                    check3++;
                }
                for (int i = 0; i < Hour.values().length; i++) {
                    if (hour.equals(Hour.values()[i].getHour())) {
                        hourVal = i + 1;
                    }
                }
                for (final String classValue : Controller.getController().getClassrooms()) {
                    if (classValue.equals(classRoom)) {
                        classVal = Controller.getController().getClassrooms().indexOf(classValue);
                    }
                }
                base.get(classVal + dayVal).set(hourVal, lesson);
            }
            return base;
        }
        
        if (searchType == ObjectManager.PARTIAL) {
            for (int i = 0; i < Day.values().length + 1; i++) {
                base.add(new ArrayList<>());
                if (i == 0) {
                    base.get(i).add("");
                    for (int y = 1; y <= Hour.values().length; y++) {
                        base.get(i).add(Hour.values()[y - 1].getHour());
                    }
                } else {
                    base.get(i).add(Day.values()[i - 1].getDay());
                    for (int y = 1; y < EMPTY; y++) {
                        base.get(i).add("");
                    }
                }
            }
            
            if (list == null) {
                return base;
            }
            
            for (final ILesson lesson : list) {
                final String day = lesson.getDay().getDay();
                final String hour = lesson.getHour().getHour();
                int dayVal = 0;
                int hourVal = 0;
                for (int i = 0; i < Day.values().length; i++) {
                    if (day.equals(Day.values()[i].getDay())) {
                        dayVal = i + 1;
                    }
                }
                for (int i = 0; i < Hour.values().length; i++) {
                    if (hour.equals(Hour.values()[i].getHour())) {
                        hourVal = i + 1;
                    }
                }
                base.get(dayVal).set(hourVal, lesson);
            }
            return base;
        }
        return null;
    }
    
    /**
     * Method which change the values of an object ILesson to place it in another cell of the table.
     * @param type Table's type of view used to understand the actual view of the table.
     * @param row Future row of the object in the table.
     * @param column Future column of the object in the table.
     * @param lesson The object ILesson which needs to be moved.
     * @return The object ILesson with the new fields used to change is position on the table.
     */
    
    public static ILesson setNewLessonValues(final int type, final int row, final int column, final ILesson lesson) {
        if (type == ObjectManager.COMPLETE) {
            lesson.setHour(Hour.values()[column - 1]);
            lesson.setClassRoom(Controller.getController().getClassrooms().get(row % (Controller.getController().getClassrooms().size() + 1) - 1));
            Controller.getController().getClassrooms().forEach(x -> {
                if (x.equals(lesson.getClassRoom())) {
                    lesson.setDay(Day.values()[(row - Controller.getController().getClassrooms().indexOf(x) - 1) / Controller.getController().getClassrooms().size()]);
                }
            });
        }
        
        if (type == ObjectManager.PARTIAL) {
            lesson.setDay(Day.values()[row - 1]);
            lesson.setHour(Hour.values()[column - 1]);
        }
        return lesson;
    }

}
