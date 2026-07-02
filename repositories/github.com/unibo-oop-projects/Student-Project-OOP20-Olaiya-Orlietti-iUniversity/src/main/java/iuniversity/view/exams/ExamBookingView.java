package iuniversity.view.exams;

import java.util.Set;

import iuniversity.model.exams.ExamCall;

public interface ExamBookingView {

    /**
     * 
     * @param examCalls the exam call to be displayed
     */
    void setAvailableExamCalls(Set<ExamCall> examCalls);

}
