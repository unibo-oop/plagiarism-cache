package iuniversity.controller.exams;

import iuniversity.model.didactics.Course;
import iuniversity.model.exams.ExamCall;
import iuniversity.model.user.Student;
import iuniversity.model.exams.ExamResult.ExamResultType;

public interface CreateExamReportController {

    /**
     * Set the exam call choices on the view.
     */
    void displayExamCallChoices();

    /**
     * Set the result type choices on the view.
     */
    void displayResultTypeChoices();

    /**
     *  Set the student choices on the view.
     * @param examCall the exam call from which students should be retrieved
     */
    void displayStudentChoices(ExamCall examCall);

    /**
     * Create an examReport.
     * @param course the course to which the report is reffered
     * @param student the addressee of the student
     * @param resultType the result type of the report
     * @param result the numeric result
     * @param cumLaude if student received a laude
     */
    void createExamReport(Course course, Student student, ExamResultType resultType, int result, boolean cumLaude);

}
