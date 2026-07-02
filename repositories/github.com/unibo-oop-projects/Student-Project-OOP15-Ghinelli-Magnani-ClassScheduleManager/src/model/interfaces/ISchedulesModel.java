package model.interfaces;

import model.Court;
import model.Day;
import model.Hour;
import model.Semester;
import model.Year;

import java.util.List;
import java.util.NoSuchElementException;


/**
 * Interface that defines the complete weekly timetable 
 * and manage the list of every course of the faculty
 * of informatics science and technology in Cesena. 
 * It represents the Model in the architectural pattern MVC.
 * 
 * @author Martina Magnani
 *
 */
public interface ISchedulesModel extends java.io.Serializable {
  
  /**
   * Method that adds a professor in the list professors.
   * @param name
   *          the new professor
   * @return Professor
   */
  IProfessor addProfessor(final String name) throws IllegalArgumentException;
  
  /**
   * Method that returns the list professors.
   * @return
   *         the complete list of professors
   */
  List<IProfessor> getProfessorsList();
  
  /**
   * Method that adds a subject in the list of teachings.
   * @param name
   *          the name of the new subject
   * @param year
   *          the year of the subject
   * @param cour
   *          the court of the subject
   */
  void addTeaching(final String name, final Year year, final Court court) 
                   throws IllegalArgumentException;
  
  /**
   * Method that returns the list of teachings.
   * @return
   *         the complete list of subjects
   */
  List<ITeaching> getTeachingsList();
  
  /**
   * Method that adds a classroom in the list classrooms.
   * @param prof
   *          the new classroom
   */
  void addClassroom(final String name) throws IllegalArgumentException;
  
  /**
   * Method that returns the list of classrooms.
   * @return
   *         the complete list of class
   */
  List<String> getClassroomsList();
  
  /**
   * Method that add a lesson in the list of lessons.
   * @param prof
   *          prof of the lesson
   * @param teaching
   *          teching of the lesson
   * @param semester
   *          semester of the lesson
   * @param classroom
   *          classroom of the lesson 
   * @param hour
   *          hour of the lesson  
   * @param day
   *          day of the lesson
   * @param duration
   *          duration of the lesson  
   */
  int addLesson(final IProfessor prof, final ITeaching teaching, final Semester semester, 
                final String classroom, final Hour hour, final Day day, final int duration) 
                throws IllegalArgumentException, NoSuchElementException;
  
  /**
   * Method that add a lesson in the list of lessons.
   * @param prof
   *          prof of the lesson
   * @param teaching
   *          teaching of the lesson
   * @param semester
   *          semester of the lesson
   * @param classroom
   *          classroom of the lesson 
   * @param hour
   *          hour of the lesson  
   * @param day
   *          day of the lesson
   * @param durata
   *          durata of the lesson  
   */
  int addLesson(final String prof, final ITeaching teaching, final Semester semester, 
                final String classroom, final Hour hour, final Day day, final int duration) 
                throws IllegalArgumentException;
  
  /**
   * Method that add a lesson in the list of lessons.
   * @param lesson
   *          new lesson
   */
  int addLesson(final ILesson lesson) throws IllegalArgumentException;
  
  /**
   * Method that returns (if exists) the object Professor whose name matches the string professor.
   * @param prof
   *          string with the name of prof
   * @return 
   *        Professor
   */
  IProfessor getProfessor(final String prof);
  
  /**
   * Method that delete a lesson in the list of lesson.
   * @param lesson
   *          the lesson to delete
   * @return
   *          true 
   *                  if the lesson has been eliminated
   *          false
   *                  if lesson does not exist
   */
  boolean deleteLesson(final ILesson lesson) throws NoSuchElementException;
  
  /**
   * Method that delete a professor in the list of professor.
   * @param prof
   *          Professor to delete
   * @return
   *          true - if the professor was deleted
   *          false - if the professor wasn't deleted
   * @throws IllegalArgumentException
   *          if parameter is null
   * @throws NoSuchElementException
   *          if parameter is not found
   */
  boolean deleteProfessor(final IProfessor prof) 
                          throws IllegalArgumentException, NoSuchElementException;
  
  /**
   * Method that delete a professor in the list of professor.
   * @param name
   *          name of the professor to delete
   * @return
   *          true - if the professor was deleted
   *          false - if the professor wasn't deleted
* @throws IllegalArgumentException
   *          if parameter is null
   * @throws NoSuchElementException
   *          if parameter is not found
   */
  boolean deleteProfessor(final String name) 
                          throws IllegalArgumentException, NoSuchElementException;
  
  /**
   * Method that delete a teaching in the list of professor.
   * @param teaching
   *          teaching to delete
   * @param name
   *          name of the teaching to delete
   * @return
   *          true - if the teaching was deleted
   *          false - if the teaching wasn't deleted
   * @throws IllegalArgumentException
   *            if parameter is null
   * @throws NoSuchElementException
   *            if parameter is not found
   */
  boolean deleteTeaching(final ITeaching teaching) 
                         throws IllegalArgumentException, NoSuchElementException;
  
  /**
   * Method that delete a teaching in the list of professor.
  * @param name
  *          name of the teaching to delete
  * @return
  *          true - if the teaching was deleted
  *          false - if the teaching wasn't deleted
  * @throws IllegalArgumentException
  *            if parameter is null
  * @throws NoSuchElementException
  *            if parameter is not found
  */
  boolean deleteTeaching(final String name) throws IllegalArgumentException, NoSuchElementException;
  
  /**
   * Method that returns the list of filtered lessons according to the parameters that are passed.
   * If these are NULL means that I do not want to filter according to those objects.
   * @param prof
   *          parameter that if non-NULL indicates that I want the lessons of this particular prof
   * @param teaching
   *          parameter that if non-NULL indicates that 
   *          I want the lessons of this particular teaching
   * @param classroom
   *          parameter that if non-NULL indicates that 
   *          I want the lessons conducted in this particular classroom
   * @param hour
   *          parameter that if non-NULL indicates that 
   *          I want the lessons conducted in this particular hour
   * @param day
   *          parameter that if non-NULL indicates that 
   *          I want the lessons conducted in this particular day
   * @return
   *         true - if the teaching was deleted
   *         false - if the teaching wasn't deleted
   */
  List<ILesson> getLessons(final String prof, final String teaching, 
                           final Year year, final Court court, final Semester semester, 
                           final String classroom, final Hour hour, final Day day);
  
  /**
   * Method that allows to obtain the list of the only professors who have active lessons.
   * @return
   *          list of professor
   */
  List<IProfessor> getProfessorsActive();

  /**
   * Method that allows to obtain the list of the only materials that are active in the lessons.
   * @return
   *          list of teaching
   */
  List<ITeaching> getTeachingActive();

  /**
   * Method that allows to obtain the list of only classrooms occupied by some lessons.
   * @return
   *          list of classroom
   */
  List<String> getClassRoomActive();
  
  /**
   * Method that check the changes proposed for some lessons. 
   * If they are correct, the list of classes is updated with the new ones. 
   * If even only one of them is wrong, the entire block is reset to the initial situation
   * @param lessonModified
   *          new list of lesson 
   * @return  
   *          true - if the operation is successful
   *          false - if the operation is not carried out because of conflicts
   * @throws IllegalArgumentException
   *          if parameter is null
   */
  boolean checkChanges(final List<ILesson> lessonModified, final Semester semester) 
                       throws IllegalArgumentException;
  
  /**
   * Method that return a lesson.
   * @param id
   *          id of the lesson we want find
   * @return
   *          lesson interested
   */
  ILesson getLesson(final int id);
  
  /**
   * Method that set model.
   * @param other
   *          new model
   * @return
   *          true - if the operation is completed
   *          false - if the operation isn't completed
   */
  public boolean copy(final ISchedulesModel other);
}
