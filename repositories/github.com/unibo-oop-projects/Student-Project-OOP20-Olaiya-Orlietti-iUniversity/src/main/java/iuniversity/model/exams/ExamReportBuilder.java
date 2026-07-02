package iuniversity.model.exams;

import iuniversity.model.didactics.Course;
import iuniversity.model.exams.ExamResult.ExamResultType;
import iuniversity.model.user.Student;

public interface ExamReportBuilder {

    /**
     * 
     * @param course sets the course
     * @return this builder
     */
    ExamReportBuilder course(Course course);

    /**
     * 
     * @param student sets the student
     * @return this builder
     */
    ExamReportBuilder student(Student student);

    /**
     * 
     * @param resultType sets the resultType
     * @return this builder
     */
    ExamReportBuilder resultType(ExamResultType resultType);

    /**
     * 
     * @param result sets the numeric result
     * @return this builder
     */
    ExamReportBuilder result(int result);

    /**
     * If a true value is provided the result is automatically set to the maximun
     * result.
     * 
     * @param laude set if student obtained laude
     * @return this builder
     */
    ExamReportBuilder laude(boolean laude);

    /**
     * 
     * @return the exam report
     * @throws IllegalStateException if report is badly constructed
     */
    ExamReport build();
}
