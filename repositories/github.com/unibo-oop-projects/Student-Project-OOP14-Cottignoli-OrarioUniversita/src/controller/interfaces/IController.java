package controller.interfaces;

import java.util.Set;

import model.Classrooms;
import model.Days;
import model.SubjectType;
import model.interfaces.IModel;
import model.interfaces.ISubject;
import view.interfaces.IView;

/**
 * Interface which define the Controller of the architectural pattern MVC, namely the operations
 * usable by the {@link IView} on the {@link IModel} by reducing as much as possible the dependence 
 * between these two entities. It must be able to manage more than one IView at a time.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public interface IController {
	
	/**
	 * Adds a view to this controller.
	 * 
	 * @param v the view to add
	 */
	void addView(IView v);
	
	/**
	 * Removes a view from this controller.
	 * 
	 * @param v the view to remove
	 */
	void removeView(IView v);
	
	/**
	 * Ask to the controller to load up a specific typology of view. More informations 
	 * {@link IViewController#setViewType(IView, Object)}.
	 * 
	 * @param ob Object for the choice of the type of view.
	 */
	void setViewType(Object ob);
	
	/**
	 *  Refreshes the status of each attached. More informations {@link IViewController#updateViews(IView)}.
	 */
	void updateViews();
	
	/**
	 * 
	 * @return The list of available subjects.
	 */
	Set<ISubject> getSubjectsList();
	
	/**
	 * 
	 * @return The list of available teachers.
	 */
	Set<String> getTeachersList();
	
	/**
	 * It creates a new model to work on.
	 */
	void commandNew();
	
	/**
	 * Creation of a new subject {@link IModel#addSubject(String, String, SubjectType)}.
	 * 	
	 * @param sub Name of the subject.
	 * @param teach Name of the teacher.
	 * @param type Type of subject.
	 */
	void commandAddSubject(String sub, String teach, SubjectType type);
	
	/**
	 Removal of a subject {@link IModel#removeSubject(ISubject)}.
	 * In case of unacceptable values in input, it informs the view of the error through the method
	 * {@link IView#commandFailed(String)}.
	 * 
	 * @param sub Subject to be removed from the list.
	 */
	void commandRemoveSubject(ISubject sub);
	
	/**
	 * Save of the list of subjects on file. In case of error during the save,
	 * it informs the view of the error through the method {@link IView#commandFailed(String)}.
	 * 
	 * @param fileName Name of the file where the list must be saved.
	 */
	void commandExportSubjectList(String fileName);
	
	/**
	 * Loading of the list of subjects from the file. In case of error during the loading,
	 * it informs the view of the error through the method {@link IView#commandFailed(String)}.
	 * 
	 * @param fileName Name of the file from which the list can be loaded.
	 */
	void commandImportSubjectList(String fileName);
	
	/**
	 * It adds a subject in the timetable {@link IModel#add(ISubject, int, Days, Classrooms, int, int)}.
	 * In case of unacceptable values in input, it informs the view of the error through the method
	 * {@link IView#commandFailed(String)}.
	 * 
	 * @param sub Subject to be added.
	 * @param sem Semester in which the subject is scheduled.
	 * @param d Day in which the subject must be inserted.
	 * @param room Classroom in which the course will take place.
	 * @param hour Subject’s starting time.
	 * @param n Number of consecutive hours where the subject must be inserted.
	 */
	void commandAdd(ISubject sub, int sem, final Days d, Classrooms room, int hour, int n);
	
	/**
	 * Remove a subject from the timetable {@link IModel#remove(int, Days, Classrooms, int, int)}.
	 * In case of unacceptable values in input, it informs the view of the error through the method
	 * {@link IView#commandFailed(String)}.
	 * 
	 * @param sem Semester in which a subject must be removed.
	 * @param d Day in which the subject must be removed.
	 * @param room Classroom in which the subject must be removed.
	 * @param hour Hours to be removed.
	 * @param n Number of consecutive hours to be removed.
	 */
	void commandRemove(int sem, Days d, Classrooms room, int hour, int n);
	
	/**
	 * Saving of the entire model on file. In case of error during the save,
	 * it informs the view of the error through the method {@link IView#commandFailed(String)}.
	 * 
	 * @param fileName Name of the file where the model must be saved.
	 */
	void commandSave(String fileName);
	
	/**
	 * Loading of the entire model from the file. In case of error during the loading
	 * it informs the view of the error through the method {@link IView#commandFailed(String)}.
	 * 
	 * @param fileName Nome del file da cui caricare il modello.
	 */
	void commandLoad(String fileName);
	
	/**
	 * Undo command.
	 */
	void commandUndo();
	
	/**
	 * Redo command.
	 */
	void commandRedo();
	 
}
