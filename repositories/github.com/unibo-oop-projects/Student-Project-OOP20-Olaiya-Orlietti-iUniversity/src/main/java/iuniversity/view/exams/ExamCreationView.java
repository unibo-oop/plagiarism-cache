package iuniversity.view.exams;

import java.util.Set;

import iuniversity.model.didactics.Course;
import iuniversity.model.exams.ExamCall.ExamType;

public interface ExamCreationView {

    /**
     * The the possible exam types.
     * @param examTypes
     */
    void setExamTypeChoices(Set<ExamType> examTypes);

    /**
     * Set the possible course choices.
     * @param courses
     */
    void setCourseChoices(Set<Course> courses);

}
