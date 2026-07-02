package controller;

import java.util.List;

import model.Court;
import model.Day;
import model.Hour;
import model.Semester;
import model.Year;
import model.interfaces.ISchedulesModel;
import model.interfaces.ITeaching;

/**
 * 
 * Class of the interface IControllerViewManagerImpl.
 *
 */

public class ControllerViewManagerImpl implements IControllerViewManager {

    @Override
    public void addCourse(final List<String> values, final ISchedulesModel model) {
        Court court = null;
        Year year = null;
        String teaching = null;
        
        for (int i = 0; i < values.size(); i++) {
            for (int a = 0; a < Court.values().length; a++) {
                if (Court.values()[a].getDef().equals(values.get(i))) {
                    court = Court.values()[a];
                } else {
                    for (int b = 0; b < Year.values().length; b++) {
                        if (Year.values()[b].getYear().equals(values.get(i))) {
                            year = Year.values()[b];
                        } else {
                            if (teaching == null) {
                                teaching = values.get(i);
                            }
                        }
                    }
                }
            }
        }
        try {
            model.addTeaching(teaching, year, court);
        } catch (IllegalArgumentException e) {
            Controller.getController().errorMessage(e.getMessage());
        }
    }

    @Override
    public void addLesson(final List<String> values, final ISchedulesModel model) {
        /*String prof = null;
        ITeaching teaching = null;
        Semester semester = null;
        String classroom = null;
        Hour hour = null;
        Day day = null;
        for (int i = 0; i < values.size(); i++) {
            if (model.getTeachingsList().stream().filter(x -> x.getName().equals(values.get(i))).findFirst().isPresent()) {
                teaching = model.getTeachingsList().stream().filter(x -> x.getName().equals(values.get(i))).findFirst().get();
            } else {
                for (int a = 0; a < Day.values().length; a++) {
                    if (Day.values()[a].equals(values.get(i))) {
                        day = Day.values()[a];
                    } else {
                        for (int b = 0; b < Hour.values().length; b++) {
                            if (Hour.values()[b].equals(values.get(i))) {
                                hour = Hour.values()[b];
                            } else {
                                for (int c = 0; c < Controller.getController().getClassrooms().size(); c++) {
                                    if (Controller.getController().getClassrooms().get(c).equals(values.get(i))) {
                                        classroom = Controller.getController().getClassrooms().get(c);
                                    } else {
                                        
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }*/
        final ITeaching teaching = model.getTeachingsList().stream().filter(x -> x.getName().equals(values.get(0))).findFirst().get();
        Semester semester;
        Day day = null;
        Hour hour = null;
        final Integer duration = Integer.valueOf(values.get(2));
        if (values.get(6).equals("1")) {
            semester = Semester.values()[0];
        } else {
            semester = Semester.values()[1];
        }
        for (int i = 0; i < Day.values().length; i++) {
            if (values.get(4).equals(Day.values()[i].getDay())) {
                day = Day.values()[i];
            }
        }
        for (int i = 0; i < Hour.values().length; i++) {
            if (values.get(3).equals(Hour.values()[i].getHour())) {
                if (duration > Hour.values().length - i) {
                    Controller.getController().errorMessage("Limit of hours surpassed!");
                }
                int check;
                for (check = 0; check < duration; check++) {
                    hour = Hour.values()[i + check];
                    try {
                        model.addLesson(values.get(1), teaching, semester, values.get(5), hour, day, 1);
                    } catch (Exception e) {
                        Controller.getController().errorMessage(e.getMessage());
                    }
                }
            }
        }
    }

}
