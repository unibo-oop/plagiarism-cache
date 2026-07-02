package iuniversity.model.exams;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

import iuniversity.model.didactics.Course;
import iuniversity.model.exams.ExamCall.ExamType;
import iuniversity.model.user.Student;

public interface ExamsManager {

    /**
     * 
     * @return a set containing the published exam calls
     */
    Set<ExamCall> getExamCalls();

    /**
     * 
     * @return a set containing the published examReports
     */
    Set<ExamReport> getExamReports();

    /**
     * Creates a new Exam call.
     * 
     * @param callStart       start date and time of the exam call
     * @param course          the course of the exam call
     * @param examType        the type of the exam call
     * @param maximumStudents the maximum number of students that can register to
     *                        the exam call
     */
    void addExamCall(LocalDate callStart, Course course, ExamType examType, Integer maximumStudents);

    /**
     * Add a student exam report for a particular course.
     * 
     * @param course
     * @param student
     * @param result
     * @param date
     */
    void addExamReport(Course course, Student student, ExamResult result, LocalDate date);

    /**
     * 
     * @param examReport the report to be added
     */
    void addExamReport(ExamReport examReport);

    /**
     * 
     * @param examCall the examCall to be removed
     */
    void removeExamCall(ExamCall examCall);

    /**
     * 
     * @param examCall the exam call from which the student should be removed
     * @param student the student to remove
     * @return true is student was successfully withdrawn
     */
    boolean withdrawStudent(ExamCall examCall, Student student);

    /**
     * 
     * @param examCall the exam call to which the student should be registered
     * @param student the student to register
     * @return true if student was successfully registered
     */
    boolean registerStudent(ExamCall examCall, Student student);

    /**
     * 
     * @param examCall the exam to be checked
     * @return true if the examCall has been held already
     */
    boolean alreadyHeld(ExamCall examCall);

    /**
     * 
     * @param examReport the exam report to check
     * @return true if an exam report for the same student and course had been already added
     */
    boolean alreadyReportedSuccess(ExamReport examReport);

    /**
     * 
     * @param student the student to check if already has a particular report
     * @param course the course in which is beeing checked
     * @return true if the student already has a report for the course
     */
    boolean alreadyReportedSuccess(Student student, Course course);

    /**
     * 
     * @param examCalls sets the collection of exam calls
     */
    void setExamCalls(Collection<ExamCall> examCalls);

    /**
     * 
     * @param examReports sets the collection of exam reports
     */
    void setExamReports(Collection<ExamReport> examReports);

}
