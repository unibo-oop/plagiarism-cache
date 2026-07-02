package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the class Professor, passing the name, the surname and the courses kept by him 
 * 
 * @author Francesco Ceroni
 * 
 */

public class ProfessorImpl extends PersonImpl implements Professor {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Person prof;
    private static String name;
    private static String surname;
    private List<CoursesImpl> courses = new ArrayList<>();

    public ProfessorImpl(Person prof, List<CoursesImpl> courses) {
        super(name, surname);
        this.prof = prof;
        this.courses = courses;
    }

    public Person getPerson() {
        return prof;
    }

    public void setPerson(Person prof) {
        this.prof = prof;
    }

    public List<CoursesImpl> getCourses() {
        return this.courses;
    }

    public void setCourses(List<CoursesImpl> courses) {
        this.courses = courses;
    }

    public void addCourse(CoursesImpl c) {
        this.courses.add(c);
    }

    /**
     * @return Courses
     */
    
    private List<String> toStringCourses() {
        List<String> tmp = new ArrayList<>();
        for (Courses courses2 : courses) {
            tmp.add(courses2.getName());
        }
        return tmp;
    }

    public String toString() {
       return "name : " + this.prof.getName() + " cognome: " + this.prof.getSurname() + " Lista corsi: "
                + this.toStringCourses();
    }
}
