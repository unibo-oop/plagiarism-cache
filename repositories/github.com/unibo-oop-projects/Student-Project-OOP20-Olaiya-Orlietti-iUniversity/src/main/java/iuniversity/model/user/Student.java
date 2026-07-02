package iuniversity.model.user;

import iuniversity.model.didactics.AcademicYear;
import iuniversity.model.didactics.DegreeProgramme;

public interface Student extends User {

    /** 
     * @return an int with the registration number
     */
    int getRegistrationNumber();
    /**
     * @return the student's degree programme
     */
    DegreeProgramme getDegreeProgramme();
}
