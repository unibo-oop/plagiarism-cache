package model.interfaces;

import model.Day;
import model.Hour;
import model.Semester;

/**
 * Interface that represents a lesson used by the model.
 * @author Martina Magnani
 */
public interface ILesson extends java.io.Serializable {
  
  /**
   * Getting method of professor.
   * @return 
   *          Professor of the lesson
   */
  IProfessor getProfessor();
  
  /**
   * Setting method of professor.
   * @param prof
   *          the new professor
   */
  void setProfessor(final IProfessor prof );
  
  /**
   * Getting method of teaching.
   * @return
   *          teaching of the lesson
   */
  ITeaching getSubject();
  
  /**
   * Setting method of teaching.
   * @param teaching
   *            new teaching
   */
  void setSubject(final ITeaching teaching);
  
  /**
   * Getting method of semester.
   * @return
   *          semester of the lesson
   */
  Semester getSemester();
  
  /**
   * Setting method of semester.
   * @param semester
   *            new semester
   */
  void setSemester(final Semester semester);
  
  /**
   * Getting method of the classroom.
   * @return
   *          ClassRoom
   */
  String getClassRoom();
  
  /**
   * Setting method of class.
   * @param classroom
   *            new class
   */
  void setClassRoom(final String classroom);
  
  /**
   * Getting method of the Hour.
   * @return
   *          Hour
   */
  Hour getHour();
  
  /**
   * Setting method of hour.
   * @param hour
   *           new hour
   */
  void setHour(final Hour hour);
  
  /**
   * Getting method of the day.
   * @return
   *          Day
   */
  Day getDay();
  
  /**
   * Setting method of day.
   * @param day
   *           new day
   */
  void setDay(final Day day);
  
  /**
   * Getting method of the duration. 
   * @return
   *          duration
   */
  int getDuration();
  
  /**
   * Setting method of duration.
   * @param duration
   *            new duration
   */
  void setDuration(final int duration);
  
  /**
   * Getting method of the id of the lesson.
   * @return
   *        id
   */
  int getId();
}
