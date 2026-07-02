package iuniversity.model.exams;

import java.time.LocalDate;

import iuniversity.model.didactics.Course;
import iuniversity.model.user.Student;

public interface ExamReport {

    /**
     * 
     * @return the course to witch the evaluation is referred.
     */
    Course getCourse();

    /**
     * 
     * @return the student to witch the evaluation is referred.
     */
    Student getStudent();

    /**
     * 
     * @return the result.
     */
    ExamResult getResult();

    /**
     * 
     * @return the date in which the report was made
     */
    LocalDate getDate();

}
