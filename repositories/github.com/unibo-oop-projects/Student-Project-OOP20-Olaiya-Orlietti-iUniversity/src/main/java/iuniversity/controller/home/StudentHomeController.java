package iuniversity.controller.home;


import iuniversity.model.exams.ExamCall;

public interface StudentHomeController {

    /**
     * Set the student information on the view.
     */
    void displayStudentInfo();

    /**
     * Set the student statistics on the view.
     */
    void displayStudentStatistics();

    /**
     * Withdraw a student from an exam call.
     * @param examCall the exam call from which the student should be withdrawn
     */
    void withdrawStudent(ExamCall examCall);

    /**
     * Set the open and booked exam calls on the view.
     */
    void displayOpenBookedExamCalls();
}
