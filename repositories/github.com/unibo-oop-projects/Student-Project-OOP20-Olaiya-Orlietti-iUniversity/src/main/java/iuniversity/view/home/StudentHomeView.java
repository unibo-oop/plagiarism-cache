package iuniversity.view.home;

import java.util.Set;

import iuniversity.model.exams.ExamCall;

public interface StudentHomeView {

    /**
     * 
     * @param firstName the student first name to be displayed
     */
    void setFirstName(String firstName);

    /**
     * 
     * @param lastName the student last name to be displayed
     */
    void setLastName(String lastName);

    /**
     * 
     * @param registrationNumber the student registration numeber to be displayed
     */
    void setRegistrationNumber(int registrationNumber);

    /**
     * 
     * @param degreeProgramme the student degree programme to be displayed
     */
    void setDegreeProgramme(String degreeProgramme);

    /**
     * 
     * @param acquiredCredits the student acquired number of credits to be displayed
     */
    void setAcquiredCredits(int acquiredCredits);

    /**
     * 
     * @param meanExamResult the student mean results to be displayed
     */
    void setMeanExamResult(double meanExamResult);

    /**
     * 
     * @param highestExamResult the student highest result to be displayed
     */
    void setHighestExamResult(int highestExamResult);

    /**
     * 
     * @param lowestExamResult the student lowest result to be displayed
     */
    void setLowestExamResult(int lowestExamResult);

    /**
     * 
     * @param examCalls the student opened and booked exam call to be displayed
     */
    void setOpenBookedExamCalls(Set<ExamCall> examCalls);

}
