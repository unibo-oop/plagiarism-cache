package iuniversity.model.exams;

import java.time.LocalDate;

import iuniversity.model.didactics.Course;
import iuniversity.model.exams.ExamCall.ExamType;

public interface ExamCallBuilder {

    /**
     * 
     * @param callStart the start datetime of the exam call
     * @return instance of builder
     */
    ExamCallBuilder callStart(LocalDate callStart);

    /**
     * Sets the Exam call type.
     * 
     * @param examType the exam type of the exam call. See {@link ExamType}
     * @return instance of builder
     */
    ExamCallBuilder examType(ExamType examType);

    /**
     * Set the number of student that can register to the call.
     * 
     * @param maximumStudents the maximum number of students
     * @return instance of Builder
     */
    ExamCallBuilder maximumStudents(int maximumStudents);

    /**
     * Set the student registration strategy.
     * Default is set to at the end of list.
     * 
     * @param strategy the registration strategy
     * @return instance of Builder
     */
    ExamCallBuilder registrationStrategy(StudentRegistrationStrategy strategy);

    /**
     * Set the course of Exam call.
     * 
     * @param course the course of the exam call
     * @return instance of Builder
     */
    ExamCallBuilder course(Course course);

    /**
     * Build the exam call.
     * 
     * @return instance of ExamCall
     */
    ExamCall build();

}
