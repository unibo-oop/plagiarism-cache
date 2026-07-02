package iuniversity.view.users;

import java.util.List;

import iuniversity.model.didactics.DegreeProgramme;
import iuniversity.model.user.User.Gender;

public interface StudentCreationView {
    
    /**
     * Create the student
     */
    void createStudent();

    /**
     * Show username and password
     * @param username
     * @param password
     */
    void showStudentCredentials(String username, String password);

    /**
     * Load choices for gender
     * @param genders
     */
    void setGenderChoices(List<Gender> genders);

    /**
     * Load choices for degree programmes
     * @param degreeProgrammes
     */
    void setDegreeProgrammChoices(List<DegreeProgramme> degreeProgrammes);

}
