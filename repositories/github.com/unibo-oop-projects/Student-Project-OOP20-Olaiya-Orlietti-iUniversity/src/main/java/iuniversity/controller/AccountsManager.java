package iuniversity.controller;

import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;

import iuniversity.model.user.Student;
import iuniversity.model.user.Teacher;
import iuniversity.model.user.User;
import iuniversity.model.user.User.UserType;

public interface AccountsManager {

    /**
     * 
     * @param username
     * @param password
     * @return an optional with the type of user logged
     */
    Optional<Pair<UserType, Integer>> checkCredentials(String username, String password);

    /**
     * Stores the student credentials.
     * 
     * @param student
     * @param password
     * @param occurrencies
     */
    void registerStudentAccount(Student student, String password, int occurrencies);

    /**
     * Stores the teacher credentials.
     * 
     * @param teacher
     * @param password
     * @param occurrencies
     */
    void registerTeacherAccount(Teacher teacher, String password, int occurrencies);

    /**
     * @param userType
     * @param user
     * @param occurencies
     * @return the user username
     */
    String makeUsername(UserType userType, User user, int occurencies);

    String makeUsername(UserType userType, String firstName, String lastName, int occurencies);

    String createPassword();

}
