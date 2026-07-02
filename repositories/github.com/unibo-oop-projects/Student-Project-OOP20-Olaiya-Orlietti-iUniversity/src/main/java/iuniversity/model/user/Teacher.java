package iuniversity.model.user;

import java.util.Set;

import iuniversity.model.didactics.Course;

public interface Teacher extends User {

    /**
     * @return an int with the registration number
     */
    int getRegistrationNumber();
    /**
     * @return the teacher's set of courses
     */
    Set<Course> getCourses();
}
