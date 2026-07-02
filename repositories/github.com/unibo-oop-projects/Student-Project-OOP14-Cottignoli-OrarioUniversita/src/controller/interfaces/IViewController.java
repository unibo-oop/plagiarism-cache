package controller.interfaces;

import java.util.NoSuchElementException;

import model.Classrooms;
import model.Days;
import model.SubjectType;
import model.interfaces.IModel;
import model.interfaces.ISubject;
import view.interfaces.IView;
import controller.Controller;

/**
 * Interface which defines methods concerning commands that manage the type of view to be showed and undo/redo. 
 * This interface is necessary to make the implementation of certain commands independent, in this way it is 
 * possible to change the implementation of the listed commands (following the characteristics of this interface) 
 * without changing completely the class {@link Controller}.
 * 
 * @author Lorenzo Cottignoli
 *
 */
public interface IViewController {

	/**
	 * Method that allow to load a certain type of view on the IView passed in input depending on the 
	 * Object considered parameter.
	 * - if obj is null, it loads a total view of the timetable.
	 * - if obj is a {@link String} it corresponds to the name of a professor and therefore it loads a 
	 *   view of the timetable depending on the name of the professor.
	 * - if obj is a {@link Days} it loads a view of the timetable depending on the past day.
	 * - if obj is a {@link ISubject} it loads a view of the timetable depending on the selected subject.
	 * - if obj is a {@link Classrooms} it loads a view of the timetable depending on the classroom chosen.
 	 * - if obj is a {@link SubjectType} it loads a view of the timetable depending on the type of subject chosen.
	 * 
	 * @param v View on which the type chosen has to be loaded.
	 * @param ob Object which allow to decide the type.

	 */
	void setViewType(IView v, Object ob);
	
	/**
	 * Method necessary to define the model on which all the different operations must be made.
	 * 
	 * @param m Model to work on.
	 */
	void setModel(IModel m);
	
	/**
	 * It gives back the previous {@link IModel.IMemento} if it exists, namely the state in which the model was before of the last change.
	 * 
	 * @return Previous state of model.
	 * @throws NoSuchElementException if the previous model doesn’t exist.
	 */
	IModel.IMemento getPrevMemento();
	
	/**
	 * It gives back the successive {@link IModel.IMemento} if it exists, namely the state in which the model was before cancelling the last
	 * change.
	 * 
	 * @return Successive state of the model.
	 * @throws NoSuchElementException if the successive doesn’t exist.
	 */
	IModel.IMemento getSuccMemento();
	
	/**
	 * It uses the method {@link IModel#createMemento()} and it save it into a specific structure.
	 */
	void createMemento();
	
	/**
	 * It resets the structure which keeps all of the states of the model saved before.
	 */
	void resetCaretaker();
	
	/**
	 * It updates the IView passed as a parameter, choosing the type of view depending on the last  
	 * {@link #setViewType(IView, Object)} realized; if that method was never called, you manage it 
	 * as if obj is null.
	 * 
	 * @param v IView to be updated.
	 */
	void updateViews(IView v);
	
	/**
	 * Method which enable/disable commands undo/redo for the view, if it exist saved previous and/or successive 
	 * states of model.
	 * 
	 * @param v IView where commands undo/redo are enabled/disable.
.
	 */
	void undoRedo(IView v);
}
