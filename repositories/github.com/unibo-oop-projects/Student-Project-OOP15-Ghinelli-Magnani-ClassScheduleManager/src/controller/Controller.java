package controller;

import java.io.File;  
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import model.Court;
import model.SchedulesModel;
import model.Semester;
import model.Year;
import model.interfaces.ILesson;
import model.interfaces.ISchedulesModel;
import view.IView;

/**
 * 
 * Class which interacts between the view and the model, it follows the singleton pattern.
 *
 */

public final class Controller {
    
    private static Optional<Controller> singleton = Optional.empty();
    private ISchedulesModel model = new SchedulesModel();
    private Optional<IView> view = Optional.empty();
    private final IDataManager data = new DataManegerImpl();
    private final IControllerViewManager manager = new ControllerViewManagerImpl();
    private Semester sem = Semester.FIRST_SEMESTER;
    private String searchValue = "Total";

    private Controller() {
        this.readConfiguration();
    }
    
    /**
     * Method which return the instance of the controller, and if the controller doesn't exist yet, it creates a new one.
     * @return The controller.
     */

    public static Controller getController() {
        synchronized (Controller.class) {
            if (!singleton.isPresent()) {
                singleton = Optional.of(new Controller());
            }
        }
        return singleton.get();
    }
    
    /**
     * Method used to assign the view to the controller.
     * @param viewValue An instance of the view.
     */
    
    public void setView(final IView viewValue) {
        this.view = Optional.of(viewValue);
    }
    
    /**
     * Method which read the initial configuration for the program.
     */
    
    public void readConfiguration() {
        try {
            data.readConfig(this.model);
        } catch (IOException e) {
            this.errorMessage(e.getMessage());
        }
    }
    
    /**
     * Method which saves the data of the table in a file.
     * @param file The file where the data will be saved.
     */
    
    public void saveData(final File file) {
        try {
            data.saveFile(file.getPath(), model);
        } catch (IOException e) {
            this.errorMessage(e.getMessage());
        }
    }
    
    /**
     * Method which loads the data of the table in a file.
     * @param file The file where the data will be loaded.
     */
    
    public void loadData(final File file) {
        
        try {
            this.model = data.openFile(file.getPath());
        } catch (ClassNotFoundException e) {
            this.errorMessage(e.getMessage());
        } catch (IOException e) {
            this.errorMessage(e.getMessage());
        }
        this.searchBy(this.searchValue);
    }
    
    /**
     * Method which exports in a excel file the table.
     * @param workbook The workbook containing the file.
     */
    
    public void excelExport(final HSSFWorkbook workbook) {
        data.exportInExcel(workbook);
    }
    
    /**
     * Method which gives a list of the name of courses.
     * @return The list.
     */

    public List<String> getCourseName() {
        return model.getTeachingsList().stream().map(x -> x.getName()).sorted().collect(Collectors.toList());
    }
    
    /**
     * Method which gives a list of the name of professors.
     * @return The list.
     */
    
    public List<String> getProfessors() {
        return model.getProfessorsList().stream().map(x -> x.getName()).sorted().collect(Collectors.toList());
    }
    
    /**
     * Method which gives a list of the name of classrooms.
     * @return The list.
     */
    
    public List<String> getClassrooms() {
        return model.getClassroomsList();
    }
    
    /**
     * Method which gives the list of professors which have a lesson in the current moment.
     * @return The list of professors.
     */
    
    public List<String> getActiveProfessors() {
        return model.getProfessorsActive().stream().map(x -> x.getName()).collect(Collectors.toList());
    }
    
    /**
     * Method which gives the list of teachings which are present in the current moment.
     * @return The list of teachings.
     */
    
    public List<String> getActiveTeachings() {
        return model.getTeachingActive().stream().map(x -> x.getName()).collect(Collectors.toList());
    }
    
    /**
     * Method which add to the model a course providing all the information needed.
     * @param values The list of informations.
     */

    public void addCourse(final List<String> values) {
        manager.addCourse(values, model);
    }
    
    /**
     * Method which add to the model a lesson providing all the information needed.
     * @param values The list of informations.
     */
    
    public void addLesson(final List<String> values) {
        manager.addLesson(values, model);
        this.searchBy(this.searchValue);
    }
    
    /**
     * Method which change the table's type of view.
     * @param value The element of the type.
     */
    
    public void searchBy(final String value) {
        this.searchValue = value;
        Year year = null;
        for (int i = 0; i < Year.values().length; i++) {
            if (Year.values()[i].getYear().equals(value)) {
                year = Year.values()[i];
                this.view.get().addData(0, this.model.getLessons(null, null, year, null, this.sem, null, null, null));
                return;
            }
        }
        Court court = null;
        for (int i = 0; i < Court.values().length; i++) {
            if (Court.values()[i].getDef().equals(value)) {
                court = Court.values()[i];
                this.view.get().addData(0, this.model.getLessons(null, null, null, court, this.sem, null, null, null));
                return;
            }
        }
        for (int i = 0; i < this.getActiveProfessors().size(); i++) {
            if (this.getActiveProfessors().get(i).equals(value)) {
                this.view.get().addData(0, this.model.getLessons(value, null, null, null, this.sem, null, null, null));
                return;
            }
        }
        for (int i = 0; i < this.getActiveTeachings().size(); i++) {
            if (this.getActiveTeachings().get(i).equals(value)) {
                this.view.get().addData(0, this.model.getLessons(null, value, null, null, this.sem, null, null, null));
                return;
            }
        }
        for (int i = 0; i < this.getClassrooms().size(); i++) {
            if (this.getClassrooms().get(i).equals(value)) {
                this.view.get().addData(1, this.model.getLessons(null, null, null, null, this.sem, value, null, null));
                return;
            }
        }
        this.view.get().addData(0, this.model.getLessons(null, null, null, null, this.sem, null, null, null));
        this.view.get().refreshSearchList();
    }
    
    /**
     * Method which change the semester in the view's table.
     * @param semester The semester which will be seen.
     */
    
    public void setSemester(final int semester) {
        if (semester == 1) {
            this.sem = Semester.FIRST_SEMESTER;
            this.searchBy(this.searchValue);
        } else {
            this.sem = Semester.SECOND_SEMESTER;
            this.searchBy(this.searchValue);
        }
    }
    
    /**
     * Method which pass the view a message of a certain error.
     * @param error The message.
     */
    
    public void errorMessage(final String error) {
        this.view.get().errorDialog(error);
    }
    
    /**
     * Method used to confirm the changements in the view's table.
     * @param list The list of ILessons suitably changed.
     */
    
    public void setChangements(final List<ILesson> list) {
        try {
            this.model.checkChanges(list, this.sem);
        } catch (IllegalArgumentException e) {
            this.errorMessage(e.getMessage());
        }
        this.searchBy(searchValue);
    }
    
    /**
     * Method which deletes a professor from the list of professors.
     * @param prof The prof which needs to be deleted.
     */
    
    public void deleteProfessor(final String prof) {
        try {
            this.model.deleteProfessor(prof);
        } catch (Exception e) {
            this.errorMessage(e.getMessage());
        }
    }
    
    /**
     * Method which deletes a teaching from the list of teachings.
     * @param teaching The teaching which needs to be deleted.
     */
    
    public void deleteTeaching(final String teaching) {
        try {
            this.model.deleteTeaching(teaching);
        } catch (Exception e) {
            this.errorMessage(e.getMessage());
        }
    }
}
