package iuniversity.view.home;

import java.util.Set;

import iuniversity.model.exams.ExamCall;

public interface TeacherHomeView {

    /**
     * 
     * @param firstName the teacher first name to be displayed
     */
    void setFirstName(String firstName);

    /**
     * 
     * @param lastName the teacher last name to be displayed
     */
    void setLastName(String lastName);

    /**
     * 
     * @param registrationNumber the teacher registration number to be displayed
     */
    void setRegistrationNumber(int registrationNumber);

    /**
     * 
     * @param promotedStudents the number of student that the teacher promoted to be displayed
     */
    void setPromotedStudents(int promotedStudents);

    /**
     * 
     * @param failedStudents the number of students that the teacher failed to be displayed
     */
    void setFailedStudents(int failedStudents);

    /**
     * 
     * @param withdrawnStudents the number of students that had withdrawn to be displayed
     */
    void setWithdrawnStudents(int withdrawnStudents);

    /**
     * 
     * @param cumLaudeStudents the number of students who received cum laude result to be displayed
     */
    void setCumLaudeStudents(int cumLaudeStudents);

    /**
     * 
     * @param meanResults the results average given to the students to be displayed
     */
    void setMeanResults(double meanResults);

    /**
     * 
     * @param examCalls the teacher opened exam call to be displayed
     */
    void setOpenExamCalls(Set<ExamCall> examCalls);

}
