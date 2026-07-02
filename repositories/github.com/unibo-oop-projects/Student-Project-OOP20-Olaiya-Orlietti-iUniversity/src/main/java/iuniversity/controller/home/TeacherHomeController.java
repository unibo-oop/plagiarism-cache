package iuniversity.controller.home;



import iuniversity.model.exams.ExamCall;

public interface TeacherHomeController {

    /**
     * Sets the teacher information on the view.
     */
    void displayTeacherInfo();

    /**
     * Sets the teacher statistics on the view.
     */
    void displayTeacherStatistics();

    /**
     * Removes an exam call. All students will no longer be registered.
     * @param examCall the exam call to remove
     */
    void removeExamCall(ExamCall examCall);

    /**
     * Sets the theacher's opened exam calls.
     */
    void displayOpenExamCalls();
}
