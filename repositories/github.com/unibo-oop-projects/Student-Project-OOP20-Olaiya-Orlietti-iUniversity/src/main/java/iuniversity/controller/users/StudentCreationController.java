package iuniversity.controller.users;

import java.time.LocalDate;

import iuniversity.model.didactics.DegreeProgramme;
import iuniversity.model.user.User.Gender;

public interface StudentCreationController {

    /**
     * Create the new student, his username and I have him create the password
     * @param firstName          
     * @param lastName
     * @param dateOfBirth
     * @param gender
     * @param address
     * @param degreeProgramme
     */
    void createStudent(String firstName, String lastName, LocalDate dateOfBirth, Gender gender, String address,
            DegreeProgramme degreeProgramme);
    
    /**
     * Initialize choices for the student(gender and degree programmes)
     */
    void initializeChoices();
}
