package iuniversity.storage;

import java.util.Collection;

import iuniversity.model.didactics.Course;
import iuniversity.model.didactics.DegreeProgramme;
import iuniversity.model.exams.ExamCall;
import iuniversity.model.exams.ExamReport;
import iuniversity.model.user.Student;
import iuniversity.model.user.Teacher;

public interface DataStore {

    /**
     * 
     * @param students the students to be saved
     */
    void saveStudents(Collection<Student> students);

    /**
     * 
     * @return a collection of students
     */
    Collection<Student> loadStudents();

    /**
     * 
     * @param teachers the teachers to be saved
     */
    void saveTeachers(Collection<Teacher> teachers);

    /**
     * 
     * @return a collections off teachers
     */
    Collection<Teacher> loadTeachers();

    /**
     * 
     * @param courses the courses to be saved
     */
    void saveCourses(Collection<Course> courses);

    /**
     * 
     * @return a collection of courses
     */
    Collection<Course> loadCourses();

    /**
     * 
     * @param degreeProgrammes the degree programmes to be saved
     */
    void saveDegreeProgrammes(Collection<DegreeProgramme> degreeProgrammes);

    /**
     * 
     * @return a collection of degree programmes
     */
    Collection<DegreeProgramme> loadDegreeProgrammes();

    /**
     * 
     * @param examCalls the exam calls to be saved
     */
    void saveExamCalls(Collection<ExamCall> examCalls);

    /**
     * 
     * @return a collection of exam calls
     */
    Collection<ExamCall> loadExamCalls();

    /**
     * 
     * @param examReports the exam reports to be saved
     */
    void saveExamReports(Collection<ExamReport> examReports);

    /**
     * 
     * @return a collection of exam reports
     */
    Collection<ExamReport> loadExamReports();

}
