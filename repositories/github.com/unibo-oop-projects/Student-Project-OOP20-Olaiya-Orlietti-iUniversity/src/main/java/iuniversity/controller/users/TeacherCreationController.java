package iuniversity.controller.users;

import java.time.LocalDate;
import java.util.Set;

import iuniversity.model.didactics.Course;
import iuniversity.model.user.User.Gender;

public interface TeacherCreationController {

    void createTeacher(String firstName, String lastName, LocalDate dateOfBirth, 
            Gender gender, String address, Set<Course> courses);

    void initializeChoices();
}
