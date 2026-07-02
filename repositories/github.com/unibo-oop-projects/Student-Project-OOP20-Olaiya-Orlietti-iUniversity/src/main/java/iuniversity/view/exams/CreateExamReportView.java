package iuniversity.view.exams;

import java.util.Set;

import iuniversity.model.exams.ExamCall;
import iuniversity.model.user.Student;
import iuniversity.model.exams.ExamResult.ExamResultType;

public interface CreateExamReportView {

    /**
     * 
     * @param examCalls the examCalls to be displayed
     */
    void setExamCallChoices(Set<ExamCall> examCalls);

    /**
     * 
     * @param students the students to be displayed
     */
    void setStudentChoices(Set<Student> students);

    /**
     * 
     * @param examResultType the exam result type to be displayed
     */
    void setExamResultTypeChoices(Set<ExamResultType> examResultType);
}
