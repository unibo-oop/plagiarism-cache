package model;

import model.interfaces.ILesson;
import model.interfaces.IProfessor;
import model.interfaces.ITeaching;

import java.io.Serializable;


/** 
 * The Lesson class shapes the object that will identify an unique lesson.
 * @author Martina Magnani
 *
 */
public class Lesson implements ILesson, Serializable {
  private static final long serialVersionUID = 1L;
  private IProfessor prof;
  private ITeaching teaching;
  private Semester semester;
  private String classroom;
  private Hour hour;
  private Day day;
  private int duration;
  private int id;
  
  /**
   * It creates a Lesson with passed parameters.
   * @param prof 
   *            professor of the lesson
   * @param teaching
   *            teaching of the lesson
   * @param semester
   *            semester of the lesson
   * @param classroom
   *            classroom of the lesson
   * @param hour
   *            hour of the lesson
   * @param day
   *            day of the lesson
   * @param duration
   *            duration of the lesson
   * @param id
   *            identifier of the lesson
   */
  public Lesson(final IProfessor prof, final ITeaching teaching, final Semester semester, 
      final String classroom, final Hour hour, final Day day, final int duration, final int id) {
    if (prof == null || teaching == null || semester == null || classroom == null 
        || hour == null || day == null || duration == 0) {
      throw new IllegalArgumentException("The values can't be null!"); 
    }
    this.prof = prof;
    this.teaching = teaching;
    this.semester = semester;
    this.classroom = classroom;
    this.hour = hour;
    this.day = day;
    this.duration = duration;
    this.id = id;
  }
  
  @Override
  public IProfessor getProfessor() {
    return this.prof;
  }
  
  @Override
  public ITeaching getSubject() {
    return this.teaching;
  }
  
  @Override
  public Semester getSemester() {
    return this.semester;
  }
  
  @Override
  public String getClassRoom() {
    return this.classroom;
  }
  
  @Override
  public Hour getHour() {
    return this.hour;
  }
  
  @Override
  public Day getDay() {
    return this.day;
  }
  
  @Override
  public int getDuration() {
    return this.duration;
  }
  
  @Override
  public int getId() {
    return this.id;
  }
  
  @Override
  public String toString() {
    return "Prof: " + this.prof + " | " 
            + "Teaching: " + this.teaching.toString() + " | " 
            + "Semester: " + this.semester + " | "            
            + "ClassRoom: " + this.classroom + " | "
            + "Day: " + this.day + " | "
            + "Hour: " + this.hour + " | "
            + "Duration: " + this.duration + " | ";
  }
  
  @Override
  public void setProfessor(IProfessor prof) {
    if (prof == null) {
      throw new IllegalArgumentException("The values can't be null!"); 
    }
    this.prof = prof;
  }
  
  @Override
  public void setSubject(ITeaching teaching) {
    if (teaching == null) {
      throw new IllegalArgumentException("The values can't be null!"); 
    }
    this.teaching = teaching;
  }
  
  @Override
  public void setSemester(Semester semester) {
    if (semester == null) {
      throw new IllegalArgumentException("The values can't be null!"); 
    }
    this.semester = semester;
  }
  
  @Override
  public void setClassRoom(String classroom) {
    if (classroom == null) {
      throw new IllegalArgumentException("The values can't be null!"); 
    }
    this.classroom = classroom;
  }
  
  @Override
  public void setHour(Hour hour) {
    if (hour == null) {
      throw new IllegalArgumentException("The values can't be null!"); 
    }
    this.hour = hour;
  }
  
  @Override
  public void setDay(Day day) {
    if (day == null) {
      throw new IllegalArgumentException("The values can't be null!"); 
    }
    this.day = day;
  }
  
  @Override
  public void setDuration(int duration) {
    if (duration < 1) {
      throw new IllegalArgumentException("The values can't be null!"); 
    }
    this.duration = duration;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Lesson other = (Lesson) obj;
    if (id != other.id) {
      return false;
    }
    return true;
  }
    
}
