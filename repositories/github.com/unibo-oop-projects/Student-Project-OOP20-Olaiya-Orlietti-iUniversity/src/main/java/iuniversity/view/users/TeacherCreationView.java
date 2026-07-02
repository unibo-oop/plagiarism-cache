package iuniversity.view.users;

import java.util.Set;

import iuniversity.model.didactics.Course;
import iuniversity.model.user.User.Gender;

public interface TeacherCreationView {

    /*
     * Create the teacher
     */
    void createTeacher();
    /**
     * Show username and password
     * @param username
     * @param password
     */
    void showTeacherCredentials(String username, String password);
    /**
     * Load choices for gender
     * 
     * @param genders
     */
    void setGenderChoices(Set<Gender> genders);
    /**
     * Load choices for course
     * @param course
     */
    void setCourseChoices(Set<Course> course);
}
