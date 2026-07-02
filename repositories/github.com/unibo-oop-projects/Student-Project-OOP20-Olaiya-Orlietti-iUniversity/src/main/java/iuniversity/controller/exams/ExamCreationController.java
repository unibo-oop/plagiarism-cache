package iuniversity.controller.exams;

import java.time.LocalDate;

import iuniversity.model.didactics.Course;
import iuniversity.model.exams.ExamCall.ExamType;

public interface ExamCreationController {

    void initializeChoices();

    void initializeExamTypeChoices();

    void initilizeCourseChoices();

    void publishExamCall(LocalDate callStart, Course course, ExamType examType,
            int maximumStudents);

}
